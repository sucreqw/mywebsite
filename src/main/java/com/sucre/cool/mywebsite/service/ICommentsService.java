package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.dto.DronecommentDTO;
import com.sucre.cool.mywebsite.entity.CommentsDO;
import com.sucre.cool.mywebsite.info.CommentsInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface ICommentsService extends IService<CommentsDO> {
    Integer createComments(CommentsDTO commentsDTO);

    void deleteComments(Integer id);

    void updateComments(Integer id, CommentsDTO commentsDTO);

    Page<CommentsInfo> listPage(Integer page, Integer pageSize, String query,String wid);

    CommentsInfo getComments(Integer id);
}
