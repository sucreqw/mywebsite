package com.sucre.cool.mywebsite.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.annotation.RequiredRole;
import com.sucre.cool.mywebsite.common.RoleConstants;
import com.sucre.cool.mywebsite.dto.CardDTO;
import com.sucre.cool.mywebsite.info.CardInfo;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cardDO")
public class CardController {
    @Autowired
    private ICardService cardService;

    @RequiredRole(RoleConstants.SYSUSER)
    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody CardDTO cardDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = cardService.createCard(cardDTO);
        result.setData(id);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody CardDTO cardDTO) {
        cardService.updateCard(id, cardDTO);
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        cardService.deleteCard(id);
    }

    @RequiredRole(RoleConstants.SYSUSER)
    @GetMapping("/{id}")
    public CommonResult<CardInfo> get(@PathVariable Integer id) {
        CommonResult<CardInfo> result = new CommonResult<>();
        CardInfo cardInfo = cardService.getCard(id);
        result.setData(cardInfo);
        return result;
    }
    @RequiredRole(RoleConstants.SYSUSER)
    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<CardInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, String query) {
        CommonResult<Page<CardInfo>> result = new CommonResult<>();
        Page<CardInfo> list = cardService.listPage(page, pageSize, query);
        result.setData(list);
        return result;
    }
}
