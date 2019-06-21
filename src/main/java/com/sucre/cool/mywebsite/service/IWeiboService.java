package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.WeiboDTO;
import com.sucre.cool.mywebsite.entity.WeiboDO;
import com.sucre.cool.mywebsite.info.WeiboInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface IWeiboService extends IService<WeiboDO> {
    Integer createWeibo(WeiboDTO weiboDTO);

    void deleteWeibo(Integer id);

    void updateWeibo(Integer id, WeiboDTO weiboDTO);

    Page<WeiboInfo> listPage(Integer page, Integer pageSize, String query);

    WeiboInfo getWeibo(Integer id);

    void like(Integer id);
}
