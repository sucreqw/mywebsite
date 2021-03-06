package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sucre.cool.mywebsite.common.PageUtil;
import com.sucre.cool.mywebsite.dao.PayRecordMapper;
import com.sucre.cool.mywebsite.dto.PayRecordDTO;
import com.sucre.cool.mywebsite.entity.PayRecordDO;
import com.sucre.cool.mywebsite.info.PayRecordInfo;
import com.sucre.cool.mywebsite.service.IPayRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayRecordServiceImpl extends ServiceImpl<PayRecordMapper, PayRecordDO> implements IPayRecordService {
    @Override
    public Integer createPayRecord(PayRecordDTO payRecordDTO) {
        PayRecordDO payRecordDO = new PayRecordDO();
        BeanUtils.copyProperties(payRecordDTO, payRecordDO);
        baseMapper.insert(payRecordDO);
        return payRecordDO.getId();
    }

    @Override
    public void deletePayRecord(Integer id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void updatePayRecord(Integer id, PayRecordDTO payRecordDTO) {
        PayRecordDO payRecordDO = baseMapper.selectById(id);
        if (payRecordDO == null) {
            return;
        }
        //System.out.println(payRecordDTO.getPayDate());
        BeanUtils.copyProperties(payRecordDTO, payRecordDO);
        //System.out.println(payRecordDO.getPayDate());
        baseMapper.updateById(payRecordDO);
    }

    @Override
    public Page<PayRecordInfo> listPage(Integer page, Integer pageSize, Integer cardId, String query, String startTime, String endTime) {
        QueryWrapper<PayRecordDO> wrapper = new QueryWrapper<>();
        if (cardId != null) {
            wrapper.eq("card_id", cardId);
        }
        if (StringUtils.isNotBlank(query)) {
            wrapper.like("detail", query);
        }
        if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
            wrapper.between("pay_date", startTime, endTime);
        }

        wrapper.orderByDesc("id");
        Page<PayRecordDO> commentsDOPage = new Page<>(page, pageSize);
        return PageUtil.buildPage(baseMapper.selectPage(commentsDOPage, wrapper), PayRecordInfo.class);
    }

    @Override
    public PayRecordInfo getPayRecord(Integer id) {
        PayRecordDO payRecordDO = baseMapper.selectById(id);
        if (payRecordDO == null) {
            return null;
        }
        PayRecordInfo payRecordInfo = new PayRecordInfo();
        BeanUtils.copyProperties(payRecordDO, payRecordInfo);
        return payRecordInfo;
    }

    @Override
    public Integer payRecordCount(Wrapper<PayRecordDO> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Map<String, Object> payRecordSum(Wrapper<PayRecordDO> queryWrapper) {
        List<PayRecordDO> list = baseMapper.selectList(queryWrapper);
        Map<String, Object> result = new HashMap<>();
        BigDecimal amountUS = new BigDecimal("0.00");
        BigDecimal amount = new BigDecimal("0.00");
        for (PayRecordDO temp : list) {
            if (temp.getCurrency() == 1) {
                amountUS = amountUS.add(temp.getAmount());
            } else {
                amount = amount.add(temp.getAmount());
            }
        }
        result.put("RMB", amount);
        result.put("USD", amountUS);

        return result;
    }


}
