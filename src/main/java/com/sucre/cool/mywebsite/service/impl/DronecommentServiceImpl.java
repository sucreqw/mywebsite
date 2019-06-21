package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.common.UserUtil;
import com.sucre.cool.mywebsite.dao.DronecommentMapper;
import com.sucre.cool.mywebsite.dto.DronecommentDTO;
import com.sucre.cool.mywebsite.entity.DronecommentDO;
import com.sucre.cool.mywebsite.info.DronecommentInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.sucre.cool.mywebsite.service.IDronecommentService;
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
public class DronecommentServiceImpl extends ServiceImpl<DronecommentMapper, DronecommentDO> implements IDronecommentService {

    @Override
    public Integer createDronecomment(DronecommentDTO dronecommentDTO) {
        DronecommentDO dronecommentDO = new DronecommentDO();
        UserInfo userInfo= (UserInfo) UserUtil.getBaseUser();
        BeanUtils.copyProperties(dronecommentDTO, dronecommentDO);
        LocalDateTime date=LocalDateTime.now();

        dronecommentDO.setAvater(userInfo.getAvater());
        dronecommentDO.setNickname(userInfo.getNickname());
        dronecommentDO.setPostip(userInfo.getIp());
        dronecommentDO.setPostday(date.toString());
        dronecommentDO.setToken(userInfo.getToken());

        baseMapper.insert(dronecommentDO);
        return dronecommentDO.getId();

    }

    @Override
    public void deleteDronecomment(Integer id) {
        baseMapper.deleteById(id);

    }

    @Override
    public void updateDronecomment(Integer id, DronecommentDTO dronecommentDTO) {
        DronecommentDO dronecommentDO = baseMapper.selectById(id);
        if (dronecommentDO == null) {
            return;
        }
        BeanUtils.copyProperties(dronecommentDTO, dronecommentDO);
        baseMapper.updateById(dronecommentDO);

    }

    @Override
    public Page<DronecommentInfo> listPage(Integer page, Integer pageSize, String query,Integer id) {
        QueryWrapper<DronecommentDO> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(query)){
            wrapper.like("content",query);
        }
        if (id!=null) {
            wrapper.eq("mid", id);
        }
        wrapper.orderByDesc("id");
        Page<DronecommentDO> dronecommentDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(dronecommentDOPage, wrapper), DronecommentInfo.class);

    }

    @Override
    public DronecommentInfo getDronecomment(Integer id) {
        DronecommentDO dronecommentDO = baseMapper.selectById(id);
        if (dronecommentDO == null) {
            return null;
        }
        DronecommentInfo dronecommentInfo = new DronecommentInfo();
        BeanUtils.copyProperties(dronecommentDO, dronecommentInfo);
        return dronecommentInfo;

    }
}
