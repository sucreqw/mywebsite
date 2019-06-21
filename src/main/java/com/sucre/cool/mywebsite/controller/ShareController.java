package com.sucre.cool.mywebsite.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.dto.ShareDTO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.ShareInfo;
import com.sucre.cool.mywebsite.service.ICommentsService;
import com.sucre.cool.mywebsite.service.IShareService;
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
@RequestMapping("/shareDO")
public class ShareController {

    @Autowired
    private IShareService iShareService;
    @RequiredRole(RoleConstants.SYSUSER)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody ShareDTO shareDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = iShareService.createShare(shareDTO);
        result.setData(id);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody ShareDTO shareDTO) {
        iShareService.updateShare(id, shareDTO);
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iShareService.deleteShare(id);
    }

    @GetMapping("/{id}")
    public CommonResult<ShareInfo> get(@PathVariable Integer id) {
        CommonResult<ShareInfo> result = new CommonResult<>();
        ShareInfo shareInfo = iShareService.getShare(id);
        result.setData(shareInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<ShareInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query) {
        CommonResult<Page<ShareInfo>> result = new CommonResult<>();
        Page<ShareInfo> list = iShareService.listPage(page, pageSize, query);
        result.setData(list);
        return result;
    }
}

