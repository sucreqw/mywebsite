package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.dto.ShareDTO;
import com.sucre.cool.mywebsite.entity.CommentsDO;
import com.sucre.cool.mywebsite.entity.ShareDO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.sucre.cool.mywebsite.info.ShareInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface IShareService extends IService<ShareDO> {
    Integer createShare(ShareDTO shareDTO);

    void deleteShare(Integer id);

    void updateShare(Integer id, ShareDTO shareDTO);

    Page<ShareInfo> listPage(Integer page, Integer pageSize, String query);

    ShareInfo getShare(Integer id);
}
