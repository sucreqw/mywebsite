package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.dao.DroneMapper;
import com.sucre.cool.mywebsite.dto.DroneDTO;
import com.sucre.cool.mywebsite.entity.DroneDO;
import com.sucre.cool.mywebsite.info.DroneInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.service.IDroneService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
@Service
public class DroneServiceImpl extends ServiceImpl<DroneMapper, DroneDO> implements IDroneService {


    @Override
    public Integer createDrone(DroneDTO droneDTO) {
        DroneDO droneDO = new DroneDO();
        BeanUtils.copyProperties(droneDTO, droneDO);
        baseMapper.insert(droneDO);
        return droneDO.getId();

    }

    @Override
    public void deleteDrone(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateDrone(Integer id, DroneDTO droneDTO) {
        DroneDO droneDO = baseMapper.selectById(id);
        if (droneDO == null) {
            return;
        }
        BeanUtils.copyProperties(droneDTO, droneDO);
        baseMapper.updateById(droneDO);

    }

    @Override
    public Page<DroneInfo> listPage(Integer page, Integer pageSize, String query) {
        QueryWrapper<DroneDO> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("content", query);
        }

        wrapper.orderByDesc("id");
        Page<DroneDO> droneDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(droneDOPage, wrapper), DroneInfo.class);

    }

    @Override
    public DroneInfo getDrone(Integer id) {
        DroneDO droneDO = baseMapper.selectById(id);
        if (droneDO == null) {
            return null;
        }
        DroneInfo droneInfo = new DroneInfo();
        BeanUtils.copyProperties(droneDO, droneInfo);
        return droneInfo;

    }

    @Override
    public void like(Integer id) {
        DroneDO droneDO = baseMapper.selectById(id);
        if (droneDO != null) {
            Integer t = droneDO.getLikecount() + 1;
            droneDO.setLikecount(t);
            baseMapper.updateById(droneDO);
        }
    }
}
