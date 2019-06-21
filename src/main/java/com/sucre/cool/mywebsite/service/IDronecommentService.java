package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.DronecommentDTO;
import com.sucre.cool.mywebsite.entity.DronecommentDO;
import com.sucre.cool.mywebsite.info.DronecommentInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
public interface IDronecommentService extends IService<DronecommentDO> {
    Integer createDronecomment(DronecommentDTO dronecommentDTO);

    void deleteDronecomment(Integer id);

    void updateDronecomment(Integer id, DronecommentDTO dronecommentDTO);

    Page<DronecommentInfo> listPage(Integer page, Integer pageSize,String query,Integer id);

    DronecommentInfo getDronecomment(Integer id);
}
