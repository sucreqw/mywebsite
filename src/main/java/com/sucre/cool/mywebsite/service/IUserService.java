package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.UserDTO;
import com.sucre.cool.mywebsite.entity.UserDO;
import com.sucre.cool.mywebsite.info.UserInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface IUserService extends IService<UserDO> {
    Integer createUser(UserDTO userDTO);

    void deleteUser(Integer id);

    void updateUser(Integer id, UserDTO userDTO);

    Page<UserInfo> listPage(Integer page, Integer pageSize, String query);

    UserInfo getUser(Integer id);

    UserInfo getUserByToken(String token);

    UserInfo callBack(String code);
}
