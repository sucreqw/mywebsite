package com.sucre.cool.mywebsite.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.CommentsDTO;
import com.sucre.cool.mywebsite.info.CommentsInfo;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.service.ICommentsService;
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
@RequestMapping("/commentsDO")
public class CommentsController {

    @Autowired
    private ICommentsService iCommentsService;
    @RequiredRole(RoleConstants.EVERYONE)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody CommentsDTO commentsDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = iCommentsService.createComments(commentsDTO);
        result.setData(id);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody CommentsDTO commentsDTO) {
        iCommentsService.updateComments(id, commentsDTO);
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iCommentsService.deleteComments(id);
    }

    @GetMapping("/{id}")
    public CommonResult<CommentsInfo> get(@PathVariable Integer id) {
        CommonResult<CommentsInfo> result = new CommonResult<>();
        CommentsInfo commentsInfo = iCommentsService.getComments(id);
        result.setData(commentsInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<CommentsInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query,String wid) {
        CommonResult<Page<CommentsInfo>> result = new CommonResult<>();
        Page<CommentsInfo> list = iCommentsService.listPage(page, pageSize, query,wid);
        result.setData(list);
        return result;
    }
}

