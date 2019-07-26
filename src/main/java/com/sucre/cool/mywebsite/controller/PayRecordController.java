package com.sucre.cool.mywebsite.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sucre.cool.mywebsite.dto.PayRecordDTO;
import com.sucre.cool.mywebsite.info.CommonResult;
import com.sucre.cool.mywebsite.info.PayRecordInfo;
import com.sucre.cool.mywebsite.service.IPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/payRecordDo")
public class PayRecordController {
    @Autowired
    private IPayRecordService payRecordService;


    @PostMapping("/")
    public CommonResult<Integer> create(@RequestBody PayRecordDTO payRecordDTO) {
        CommonResult<Integer> result = new CommonResult<>();
        Integer id = payRecordService.createPayRecord(payRecordDTO);
        result.setData(id);
        return result;
    }


    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody PayRecordDTO payRecordDTO) {
        payRecordService.updatePayRecord(id, payRecordDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        payRecordService.deletePayRecord(id);
    }

    @GetMapping("/{id}")
    public CommonResult<PayRecordInfo> get(@PathVariable Integer id) {
        CommonResult<PayRecordInfo> result = new CommonResult<>();
        PayRecordInfo payRecordInfo = payRecordService.getPayRecord(id);
        result.setData(payRecordInfo);
        return result;
    }

    @GetMapping("/page/{page}/{pageSize}")
    public CommonResult<Page<PayRecordInfo>> listPage(@PathVariable Integer page, @PathVariable Integer pageSize, Integer cardId, String query, String startTime, String endTime) {
        CommonResult<Page<PayRecordInfo>> result = new CommonResult<>();
        Page<PayRecordInfo> list = payRecordService.listPage(page, pageSize, cardId,query,startTime,endTime);
        result.setData(list);
        return result;
    }
}
