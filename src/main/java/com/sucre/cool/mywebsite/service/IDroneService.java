package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.DroneDTO;
import com.sucre.cool.mywebsite.entity.DroneDO;
import com.sucre.cool.mywebsite.info.DroneInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface IDroneService extends IService<DroneDO> {
    Integer createDrone(DroneDTO droneDTO);

    void deleteDrone(Integer id);

    void updateDrone(Integer id, DroneDTO droneDTO);

    Page<DroneInfo> listPage(Integer page, Integer pageSize, String query);

    DroneInfo getDrone(Integer id);

    void like(Integer id);
}
