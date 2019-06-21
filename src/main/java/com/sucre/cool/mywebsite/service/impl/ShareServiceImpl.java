package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.common.UserUtil;
import com.sucre.cool.mywebsite.dao.CommentsMapper;
import com.sucre.cool.mywebsite.dao.ShareMapper;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.dto.ShareDTO;
import com.sucre.cool.mywebsite.entity.CommentsDO;
import com.sucre.cool.mywebsite.entity.ShareDO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.sucre.cool.mywebsite.info.ShareInfo;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.sucre.cool.mywebsite.service.ICommentsService;
import com.sucre.cool.mywebsite.service.IShareService;
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
public class ShareServiceImpl extends ServiceImpl<ShareMapper, ShareDO> implements IShareService {


    @Override
    public Integer createShare(ShareDTO shareDTO) {
        ShareDO shareDO = new ShareDO();
        BeanUtils.copyProperties(shareDTO, shareDO);
        baseMapper.insert(shareDO);
        return shareDO.getId();

    }

    @Override
    public void deleteShare(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateShare(Integer id, ShareDTO shareDTO) {
        ShareDO shareDO = baseMapper.selectById(id);
        if (shareDO == null) {
            return;
        }
        BeanUtils.copyProperties(shareDTO, shareDO);
        baseMapper.updateById(shareDO);

    }

    @Override
    public Page<ShareInfo> listPage(Integer page, Integer pageSize, String query) {
        QueryWrapper<ShareDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("content", query);
        }
        wrapper.orderByDesc("id");
        Page<ShareDO> shareDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(shareDOPage, wrapper), ShareInfo.class);

    }

    @Override
    public ShareInfo getShare(Integer id) {
        ShareDO shareDO = baseMapper.selectById(id);
        if (shareDO == null) {
            return null;
        }
        ShareInfo shareInfo = new ShareInfo();
        BeanUtils.copyProperties(shareDO, shareInfo);
        return shareInfo;
    }
}