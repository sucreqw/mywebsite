package com.sucre.cool.mywebsite.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sucre.cool.mywebsite.dto.PayRecordDTO;
import com.sucre.cool.mywebsite.entity.PayRecordDO;
import com.sucre.cool.mywebsite.info.PayRecordInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface IPayRecordService extends IService<PayRecordDO> {
    Integer createPayRecord(PayRecordDTO payRecordDTO);

    void deletePayRecord(Integer id);

    void updatePayRecord(Integer id, PayRecordDTO payRecordDTO);

    Page<PayRecordInfo> listPage(Integer page, Integer pageSize, Integer cardId, String query, String startTime, String endTime);

    PayRecordInfo getPayRecord(Integer id);

    Integer payRecordCount(Wrapper<PayRecordDO> queryWrapper);

    Map<String,Object> payRecordSum(Wrapper<PayRecordDO> queryWrapper);
}
