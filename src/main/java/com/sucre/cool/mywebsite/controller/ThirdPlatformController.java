package com.sucre.cool.mywebsite.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.dto.ThirdPlatformDTO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.ThirdPlatformInfo;
import com.sucre.cool.mywebsite.service.ICommentsService;
import com.sucre.cool.mywebsite.service.IThirdPlatformService;
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
@RequestMapping("/thirdPlatformDO")
public class ThirdPlatformController {

    @Autowired
    private IThirdPlatformService iThirdPlatformService;

    @RequiredRole(RoleConstants.SYSUSER)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody ThirdPlatformDTO platformDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = iThirdPlatformService.createThirdPlatform(platformDTO);
        result.setData(id);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody ThirdPlatformDTO thirdPlatformDTO) {
        iThirdPlatformService.updateThirdPlatform(id, thirdPlatformDTO);
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iThirdPlatformService.deleteThirdPlatform(id);
    }

    @GetMapping("/{id}")
    public CommonResult<ThirdPlatformInfo> get(@PathVariable Integer id) {
        CommonResult<ThirdPlatformInfo> result = new CommonResult<>();
        ThirdPlatformInfo thirdPlatformInfo = iThirdPlatformService.getThirdPlatform(id);
        result.setData(thirdPlatformInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<ThirdPlatformInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query) {
        CommonResult<Page<ThirdPlatformInfo>> result = new CommonResult<>();
        Page<ThirdPlatformInfo> list = iThirdPlatformService.listPage(page, pageSize, query);
        result.setData(list);
        return result;
    }
}

