package com.sucre.cool.mywebsite.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.DroneDTO;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.DroneInfo;
import com.sucre.cool.mywebsite.service.IDroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
@RestController
@RequestMapping("/droneDO")
public class DroneController {

    @Autowired
    private IDroneService iDroneService;
    @RequiredRole(RoleConstants.SYSUSER)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody DroneDTO droneDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = iDroneService.createDrone(droneDTO);
        result.setData(id);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody DroneDTO droneDTO) {
        iDroneService.updateDrone(id, droneDTO);
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iDroneService.deleteDrone(id);
    }

    @GetMapping("/{id}")
    public CommonResult<DroneInfo> get(@PathVariable Integer id) {
        CommonResult<DroneInfo> result = new CommonResult<>();
        DroneInfo droneInfo = iDroneService.getDrone(id);
        result.setData(droneInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<DroneInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query) {
        CommonResult<Page<DroneInfo>> result = new CommonResult<>();
        Page<DroneInfo> list = iDroneService.listPage(page, pageSize, query);
        result.setData(list);
        return result;
    }
    @PutMapping("/like/{id}")
    public void like(@PathVariable Integer id) {
        iDroneService.like(id);
    }
}

