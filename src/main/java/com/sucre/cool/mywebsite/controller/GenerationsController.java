package com.sucre.cool.mywebsite.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.GenerationsDTO;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.GenerationsInfo;
import com.sucre.cool.mywebsite.service.IGenerationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-06-14
 */
@RestController
@RequestMapping("/generationsDO")
public class GenerationsController {

    @Autowired
    private IGenerationsService iGenerationsService;


    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody GenerationsDTO generationsDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = iGenerationsService.createGenerations(generationsDTO);
        result.setData(id);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody GenerationsDTO generationsDTO) {
        iGenerationsService.updateGenerations(id, generationsDTO);
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iGenerationsService.deleteGenerations(id);
    }


    @GetMapping("/{id}")
    public CommonResult<GenerationsInfo> get(@PathVariable Integer id) {
        CommonResult<GenerationsInfo> result = new CommonResult<>();
        GenerationsInfo generationsInfo = iGenerationsService.getGenerations(id);
        result.setData(generationsInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<GenerationsInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query,String father) {
        CommonResult<Page<GenerationsInfo>> result = new CommonResult<>();
        Page<GenerationsInfo> list = iGenerationsService.listPage(page, pageSize, query,father);
        result.setData(list);
        return result;
    }

    @GetMapping("/allFather/{id}")
    public CommonResult<List> getAllFather(@PathVariable Integer id) {
        CommonResult<List> result = new CommonResult<>();
        List<String> list = iGenerationsService.allFather(id);
        result.setData(list);
        return result;
    }


}

