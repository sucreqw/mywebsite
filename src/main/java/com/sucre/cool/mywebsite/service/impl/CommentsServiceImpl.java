package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.common.UserUtil;
import com.sucre.cool.mywebsite.dao.CommentsMapper;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.entity.CommentsDO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.sucre.cool.mywebsite.service.ICommentsService;
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
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, CommentsDO> implements ICommentsService {


    @Override
    public Integer createComments(CommentsDTO commentsDTO) {
        CommentsDO commentsDO = new CommentsDO();
        UserInfo userInfo = (UserInfo) UserUtil.getBaseUser();

        BeanUtils.copyProperties(commentsDTO, commentsDO);

        LocalDateTime date = LocalDateTime.now();
        commentsDO.setPostday(date.toString());
        commentsDO.setPostip(userInfo.getIp());
        commentsDO.setAvater(userInfo.getAvater());
        commentsDO.setToken(userInfo.getToken());
        commentsDO.setNickname(userInfo.getNickname());

        baseMapper.insert(commentsDO);
        return commentsDO.getId();

    }

    @Override
    public void deleteComments(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateComments(Integer id, CommentsDTO commentsDTO) {
        CommentsDO commentsDO = baseMapper.selectById(id);
        if (commentsDO == null) {
            return;
        }
        BeanUtils.copyProperties(commentsDTO, commentsDO);
        baseMapper.updateById(commentsDO);

    }

    @Override
    public Page<CommentsInfo> listPage(Integer page, Integer pageSize, String query, String wid) {
        QueryWrapper<CommentsDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("content", query);
        }
        if (StringUtils.isNotBlank(wid)) {
            wrapper.eq("wid", wid);
        }
        wrapper.orderByDesc("id");
        Page<CommentsDO> commentsDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(commentsDOPage, wrapper), CommentsInfo.class);

    }

    @Override
    public CommentsInfo getComments(Integer id) {
        CommentsDO commentsDO = baseMapper.selectById(id);
        if (commentsDO == null) {
            return null;
        }
        CommentsInfo commentsInfo = new CommentsInfo();
        BeanUtils.copyProperties(commentsDO, commentsInfo);
        return commentsInfo;
    }
}