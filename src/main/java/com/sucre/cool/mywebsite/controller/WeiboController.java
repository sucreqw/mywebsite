package com.sucre.cool.mywebsite.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.WeiboDTO;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.WeiboInfo;
import com.sucre.cool.mywebsite.service.IWeiboService;
import io.swagger.models.auth.In;
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
@RequestMapping("/weiboDO")
public class WeiboController {

    @Autowired
    private IWeiboService iWeiboService;

    @RequiredRole(RoleConstants.EVERYONE)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody WeiboDTO weiboDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = iWeiboService.createWeibo(weiboDTO);
        result.setData(id);
        return result;
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody WeiboDTO weiboDTO) {
        iWeiboService.updateWeibo(id, weiboDTO);
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iWeiboService.deleteWeibo(id);
    }

    @GetMapping("/{id}")
    public CommonResult<WeiboInfo> get(@PathVariable Integer id) {
        CommonResult<WeiboInfo> result = new CommonResult<>();
        WeiboInfo weiboInfo = iWeiboService.getWeibo(id);
        result.setData(weiboInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<WeiboInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query) {
        CommonResult<Page<WeiboInfo>> result = new CommonResult<>();
        Page<WeiboInfo> list = iWeiboService.listPage(page, pageSize, query);
        result.setData(list);
        return result;
    }

    @PutMapping("/like/{id}")
    public void like(@PathVariable Integer id) {
        iWeiboService.like(id);
    }
}

