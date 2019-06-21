package com.sucre.cool.mywebsite.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.DronecommentDTO;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.DronecommentInfo;
import com.sucre.cool.mywebsite.service.IDronecommentService;
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
@RequestMapping("/dronecommentDO")
public class DronecommentController {

    @Autowired
    private IDronecommentService iDronecommentService;

    @RequiredRole(RoleConstants.EVERYONE)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody DronecommentDTO dronecommentDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = iDronecommentService.createDronecomment(dronecommentDTO);
        result.setData(id);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody DronecommentDTO dronecommentDTO) {
        iDronecommentService.updateDronecomment(id, dronecommentDTO);
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iDronecommentService.deleteDronecomment(id);
    }

    @GetMapping("/{id}")
    public CommonResult<DronecommentInfo> get(@PathVariable Integer id) {
        CommonResult<DronecommentInfo> result = new CommonResult<>();
        DronecommentInfo dronecommentInfo = iDronecommentService.getDronecomment(id);
        result.setData(dronecommentInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<DronecommentInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query,Integer id) {
        CommonResult<Page<DronecommentInfo>> result = new CommonResult<>();
        Page<DronecommentInfo> list = iDronecommentService.listPage(page, pageSize, query,id);
        result.setData(list);
        return result;
    }
}

