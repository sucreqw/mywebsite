package com.sucre.cool.mywebsite.controller;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.PasswordEncoderUtil;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.UserDTO;
import com.sucre.cool.mywebsite.enums.ResultCodeEnum;
import com.sucre.cool.mywebsite.exception.BizException;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.sucre.cool.mywebsite.service.IUserService;
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
@RequestMapping("/userDO")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequiredRole(RoleConstants.SYSUSER)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody UserDTO userDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        //userDTO.setPsd(PasswordEncoderUtil.encoder(userDTO.getPsd()));
        Integer id = iUserService.createUser(userDTO);
        result.setData(id);
        return result;
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        iUserService.updateUser(id, userDTO);
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        iUserService.deleteUser(id);
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @GetMapping("/{id}")
    public CommonResult<UserInfo> get(@PathVariable Integer id) {
        CommonResult<UserInfo> result = new CommonResult<>();
        UserInfo userInfo = iUserService.getUser(id);
        result.setData(userInfo);
        return result;
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<UserInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query) {
        CommonResult<Page<UserInfo>> result = new CommonResult<>();
        Page<UserInfo> list = iUserService.listPage(page, pageSize, query);
        result.setData(list);
        return result;
    }


    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody UserDTO userDTO){
        CommonResult<String> result=new CommonResult<>();
        UserInfo userInfo=iUserService.getUserByToken(userDTO.getToken());
        if(userInfo==null){
            throw new BizException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if (!PasswordEncoderUtil.eq(userDTO.getPsd(), userInfo.getPsd())) {
            throw new BizException(ResultCodeEnum.PASSWORD_ERROR);
        }
        /*String token;
        if(userInfo.getIsmanager()==1) {*/
           String token = JWT.create().withClaim("sysUser", JSON.toJSONString(userInfo)).sign(Algorithm.HMAC256(PasswordEncoderUtil.KEY));
       /* }else{
            token=JWT.create().withClaim("user", JSON.toJSONString(userInfo)).sign(Algorithm.HMAC256(PasswordEncoderUtil.KEY));
        }*/
        result.setData(token);
        return result;
    }

    @GetMapping("/callback/{code}")
    public CommonResult<String> callback(@PathVariable String code) {
        CommonResult<String> result = new CommonResult<>();
        UserInfo userInfo = iUserService.callBack(code);
        String token = JWT.create().withClaim("sysUser", JSON.toJSONString(userInfo)).sign(Algorithm.HMAC256(PasswordEncoderUtil.KEY));
        result.setData(token);
        return result;
    }
}

