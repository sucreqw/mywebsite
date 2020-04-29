package com.sucre.cool.mywebsite.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.PasswordEncoderUtil;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.common.UserUtil;
import com.sucre.cool.mywebsite.enums.ResultCodeEnum;
import com.sucre.cool.mywebsite.exception.BizException;
import com.sucre.cool.mywebsite.info.UserInfo;
import com.sucre.cool.mywebsite.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    IUserService iUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //没注解的直接返回可以访问
        if (this.hasPermission(handler, request)) {
            return true;
        }

        /*String user = decodedJWT.getClaim("user").asString();
        if (user != null) {
            UserUtil.setCurrUser(JSON.parseObject(user, UserInfo.class));
        }*/
        //System.out.println("jwt");
        return true;
    }

    /**
     * 是否有权限
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler, HttpServletRequest request) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredRole requiredRole = handlerMethod.getMethod().getAnnotation(RequiredRole.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredRole == null) {
                requiredRole = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredRole.class);
            }
            // 如果标记了注解，则判断权限
            if (requiredRole != null && StringUtils.isNotBlank(requiredRole.value())) {
                if (this.hasJWT(handler, request)) {
                    Integer id = UserUtil.getUserId();
                    UserInfo userInfo = iUserService.getUser(id);
                    switch (requiredRole.value()) {
                        case RoleConstants.SYSUSER:
                            if (userInfo.getIsmanager() != 1 || userInfo.getIsblock() == 1) {
                                throw new BizException(ResultCodeEnum.AUTH_ERROR);
                            }
                            break;
                        case RoleConstants.EVERYONE:
                            if (userInfo.getIsblock() == 1) {
                                throw new BizException(ResultCodeEnum.AUTH_ERROR);
                            }
                            break;
                    }
                }
                return true;
            }
        }
        return true;
    }

    private boolean hasJWT(Object handler, HttpServletRequest request) {
        // redis或数据库 中获取该用户的权限信息 并判断是否有权限
        String token = request.getHeader("X-Auth");
        if (token == null) {
            throw new BizException(ResultCodeEnum.AUTH_ERROR);
        }
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.decode(token);
            Algorithm.HMAC256(PasswordEncoderUtil.KEY).verify(decodedJWT);
        } catch (Exception e) {//SignatureVerificationException
            throw new BizException(ResultCodeEnum.AUTH_ERROR);
        }
        String sysUser = decodedJWT.getClaim("sysUser").asString();
        if (sysUser != null) {
            UserUtil.setCurrUser(JSON.parseObject(sysUser, UserInfo.class));
            UserInfo userInfo=(UserInfo) UserUtil.getBaseUser();
            userInfo.setIp(request.getRemoteAddr());
            UserUtil.setCurrUser(userInfo);
        }
        return true;
    }
}
