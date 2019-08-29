package com.sucre.cool.mywebsite.controller;

import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.info.CardGatherInfo;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.service.ICardGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cardGatherDO")
public class CardGatherController {
    @Autowired
    ICardGatherService iCardGatherService;
    @RequiredRole(RoleConstants.SYSUSER)
    @GetMapping("/")
    public CommonResult<List<CardGatherInfo>> getSum() {
        CommonResult<List<CardGatherInfo>> result = new CommonResult<>();
        List<CardGatherInfo> list=new ArrayList<>();
        list=iCardGatherService.getSum();
        result.setData(list);
        return result;
    }
}
