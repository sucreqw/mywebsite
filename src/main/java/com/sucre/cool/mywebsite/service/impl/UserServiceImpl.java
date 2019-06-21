package com.sucre.cool.mywebsite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.common.PasswordEncoderUtil;
import com.sucre.cool.mywebsite.common.UserUtil;
import com.sucre.cool.mywebsite.dao.UserMapper;
import com.sucre.cool.mywebsite.dto.UserDTO;
import com.sucre.cool.mywebsite.entity.UserDO;
import com.sucre.cool.mywebsite.enums.ResultCodeEnum;
import com.sucre.cool.mywebsite.exception.BizException;
import com.sucre.cool.mywebsite.info.ThirdPlatformInfo;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.service.IThirdPlatformService;
import com.sucre.cool.mywebsite.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements IUserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    IThirdPlatformService iThirdPlatformService;
    /*@Value("${apiKey.sinaKey}")
    private String APPID;
    @Value("${apiKey.sinaSecret}")
    private String APPSECRET;
    @Value("${apiKey.redirectURI}")
    private String REDIRECT_URI;*/


    @Override
    public Integer createUser(UserDTO userDTO) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO, userDO);

        LocalDateTime date = LocalDateTime.now();
        userDO.setRegday(date.toString());
        userDO.setPsd(PasswordEncoderUtil.encoder(userDO.getPsd()));

        baseMapper.insert(userDO);
        return userDO.getId();

    }

    @Override
    public void deleteUser(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateUser(Integer id, UserDTO userDTO) {
        UserDO userDO = baseMapper.selectById(id);
        if (userDO == null) {
            return;
        }
        BeanUtils.copyProperties(userDTO, userDO);
        baseMapper.updateById(userDO);

    }

    @Override
    public Page<UserInfo> listPage(Integer page, Integer pageSize, String query) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("nickname", query);
        }
        Page<UserDO> userDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(userDOPage, wrapper), UserInfo.class);

    }

    @Override
    public UserInfo getUser(Integer id) {
        UserDO userDO = baseMapper.selectById(id);
        if (userDO == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userDO, userInfo);
        return userInfo;

    }

    @Override
    public UserInfo getUserByToken(String token) {
        UserInfo userInfo = new UserInfo();
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(token)) {
            wrapper.eq("token", token);
        }
        UserDO userDO = baseMapper.selectOne(wrapper);
        if (userDO == null) {
            return null;
        }
        BeanUtils.copyProperties(userDO, userInfo);
        return userInfo;
    }

    @Override
    public UserInfo callBack(String code) {
        ThirdPlatformInfo thirdPlatformInfo=iThirdPlatformService.getThirdPlatformByName("sina");
        String url = "https://api.weibo.com/oauth2/access_token";
        //设置Http Header
        HttpHeaders headers = new HttpHeaders();
        //设置请求媒体数据类型
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //设置返回媒体数据类型
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("client_id", thirdPlatformInfo.getAppId());
        param.add("client_secret", thirdPlatformInfo.getAppSecret());
        param.add("grant_type", "authorization_code");
        param.add("redirect_uri", thirdPlatformInfo.getRedirect());
        param.add("code", code);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
        JSONObject sinaToken;
        try {
            sinaToken = restTemplate.postForObject(url, httpEntity, JSONObject.class);
        } catch (Exception e) {
            throw new BizException(600, e.getMessage());
        }

        if (sinaToken == null) {
            throw new BizException(ResultCodeEnum.SINAOAUTH_ERROR);
        }
        String access_token = sinaToken.getString("access_token");
        String uid = sinaToken.getString("uid");
        UserInfo userInfo = new UserInfo();
        UserDO userDO = new UserDO();
        //判断这个有用户是否已经有登录过。
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("token", access_token);
        userDO = baseMapper.selectOne(wrapper);
        //没登录过，取新浪个人信息，插入数据库。
        if (userDO == null) {
            url = "https://api.weibo.com/2/users/show.json?access_token=" + access_token + "&uid=" + uid + "&source" + thirdPlatformInfo.getAppId();
            JSONObject sinaUserInfo;
            try {
                sinaUserInfo = restTemplate.getForObject(url, JSONObject.class);
            } catch (Exception e) {
                throw new BizException(600, e.getMessage());
            }
            if (sinaUserInfo == null) {
                throw new BizException(ResultCodeEnum.SINAPROFILE_ERROR);
            }
            String nickName = sinaUserInfo.getString("screen_name");
            String avater = sinaUserInfo.getString("avatar_large");
            LocalDateTime date = LocalDateTime.now();

            userDO.setPsd(access_token);
            userDO.setNickname(nickName);
            userDO.setRegday(date.toString());
            userDO.setIsblock(0);
            userDO.setIsmanager(0);
            userDO.setUid(uid);
            userDO.setToken(access_token);
            userDO.setAvater(avater);
            baseMapper.insert(userDO);
        }
        //注册过，直接返回。
        BeanUtils.copyProperties(userDO, userInfo);
        return userInfo;
    }
}
