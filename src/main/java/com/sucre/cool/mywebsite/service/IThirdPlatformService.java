package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.dto.ThirdPlatformDTO;
import com.sucre.cool.mywebsite.entity.CommentsDO;
import com.sucre.cool.mywebsite.entity.ThirdPlatformDO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.sucre.cool.mywebsite.info.ThirdPlatformInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface IThirdPlatformService extends IService<ThirdPlatformDO> {
    Integer createThirdPlatform(ThirdPlatformDTO thirdPlatformDTO);

    void deleteThirdPlatform(Integer id);

    void updateThirdPlatform(Integer id, ThirdPlatformDTO thirdPlatformDTO);

    Page<ThirdPlatformInfo> listPage(Integer page, Integer pageSize, String query);

    ThirdPlatformInfo getThirdPlatform(Integer id);

    ThirdPlatformInfo getThirdPlatformByName(String name);
}
