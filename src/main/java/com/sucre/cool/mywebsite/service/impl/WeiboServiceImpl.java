package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.common.UserUtil;
import com.sucre.cool.mywebsite.dao.WeiboMapper;
import com.sucre.cool.mywebsite.dto.WeiboDTO;
import com.sucre.cool.mywebsite.entity.WeiboDO;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.sucre.cool.mywebsite.info.WeiboInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.service.IWeiboService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
@Service
public class WeiboServiceImpl extends ServiceImpl<WeiboMapper, WeiboDO> implements IWeiboService {

    @Override
    public Integer createWeibo(WeiboDTO weiboDTO) {
        WeiboDO weiboDO = new WeiboDO();
        UserInfo userInfo= (UserInfo) UserUtil.getBaseUser();

        BeanUtils.copyProperties(weiboDTO, weiboDO);
        LocalDateTime date=LocalDateTime.now();
        weiboDO.setPostday(date.toString());
        weiboDO.setPostip(userInfo.getIp());
        weiboDO.setToken(userInfo.getToken());
        weiboDO.setAvater(userInfo.getAvater());
        weiboDO.setNickname(userInfo.getNickname());

        baseMapper.insert(weiboDO);
        return weiboDO.getId();

    }

    @Override
    public void deleteWeibo(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateWeibo(Integer id, WeiboDTO weiboDTO) {
        WeiboDO weiboDO = baseMapper.selectById(id);
        if (weiboDO == null) {
            return;
        }
        BeanUtils.copyProperties(weiboDTO, weiboDO);
        baseMapper.updateById(weiboDO);

    }

    @Override
    public Page<WeiboInfo> listPage(Integer page, Integer pageSize, String query) {
        QueryWrapper<WeiboDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("content", query);

        }
        wrapper.orderByDesc("id");
        Page<WeiboDO> weiboDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(weiboDOPage, wrapper), WeiboInfo.class);

    }

    @Override
    public WeiboInfo getWeibo(Integer id) {
        WeiboDO weiboDO = baseMapper.selectById(id);
        if (weiboDO == null) {
            return null;
        }
        WeiboInfo weiboInfo = new WeiboInfo();
        BeanUtils.copyProperties(weiboDO, weiboInfo);
        return weiboInfo;

    }

    @Override
    public void like(Integer id) {
        WeiboDO weiboDO = baseMapper.selectById(id);
        if (weiboDO != null) {
            Integer t = Integer.parseInt(weiboDO.getMid()) + 1;
            weiboDO.setMid(t.toString());
            baseMapper.updateById(weiboDO);
        }
    }
}
