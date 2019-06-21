package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.common.UserUtil;
import com.sucre.cool.mywebsite.dao.CommentsMapper;
import com.sucre.cool.mywebsite.dao.ThirdPlatformMapper;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.dto.ThirdPlatformDTO;
import com.sucre.cool.mywebsite.entity.CommentsDO;
import com.sucre.cool.mywebsite.entity.ThirdPlatformDO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.sucre.cool.mywebsite.info.ThirdPlatformInfo;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.sucre.cool.mywebsite.service.ICommentsService;
import com.sucre.cool.mywebsite.service.IThirdPlatformService;
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
public class ThirdPlatformImpl extends ServiceImpl<ThirdPlatformMapper, ThirdPlatformDO> implements IThirdPlatformService {


    @Override
    public Integer createThirdPlatform(ThirdPlatformDTO thirdPlatformDTO) {
        ThirdPlatformDO thirdPlatformDO = new ThirdPlatformDO();
        BeanUtils.copyProperties(thirdPlatformDTO, thirdPlatformDO);
        baseMapper.insert(thirdPlatformDO);
        return thirdPlatformDO.getId();

    }

    @Override
    public void deleteThirdPlatform(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateThirdPlatform(Integer id, ThirdPlatformDTO thirdPlatformDTO) {
        ThirdPlatformDO thirdPlatformDO = baseMapper.selectById(id);
        if (thirdPlatformDO == null) {
            return;
        }
        BeanUtils.copyProperties(thirdPlatformDTO, thirdPlatformDO);
        baseMapper.updateById(thirdPlatformDO);

    }

    @Override
    public Page<ThirdPlatformInfo> listPage(Integer page, Integer pageSize, String query) {
        QueryWrapper<ThirdPlatformDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("name", query);
        }
        Page<ThirdPlatformDO> thirdPlatformDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(thirdPlatformDOPage, wrapper), ThirdPlatformInfo.class);

    }

    @Override
    public ThirdPlatformInfo getThirdPlatform(Integer id) {
        ThirdPlatformDO thirdPlatformDO = baseMapper.selectById(id);
        if (thirdPlatformDO == null) {
            return null;
        }
        ThirdPlatformInfo thirdPlatformInfo = new ThirdPlatformInfo();
        BeanUtils.copyProperties(thirdPlatformDO, thirdPlatformInfo);
        return thirdPlatformInfo;
    }

    @Override
    public ThirdPlatformInfo getThirdPlatformByName(String name) {
        QueryWrapper<ThirdPlatformDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.eq("name", name);
        }
        ThirdPlatformDO thirdPlatformDO = baseMapper.selectOne(wrapper);
        ThirdPlatformInfo thirdPlatformInfo = new ThirdPlatformInfo();
        BeanUtils.copyProperties(thirdPlatformDO, thirdPlatformInfo);
        return thirdPlatformInfo;
    }
}