package com.sucre.cool.mywebsite.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sucre.cool.mywebsite.entity.PayRecordDO;
import com.sucre.cool.mywebsite.info.CardGatherInfo;
import com.sucre.cool.mywebsite.info.CardInfo;
import com.sucre.cool.mywebsite.service.ICardGatherService;
import com.sucre.cool.mywebsite.service.ICardService;
import com.sucre.cool.mywebsite.service.IPayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 信用卡信息汇总.
 */
@Service
public class CardGatherImpl implements ICardGatherService {
    @Autowired
    ICardService iCardService;
    @Autowired
    IPayRecordService iPayRecordService;

    @Override
    public List<CardGatherInfo> getSum() {
        int id = 1;
        CardInfo cardInfo = null;
        List<CardGatherInfo> cardGatherInfos = new ArrayList<>();

        Date date = new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        do {
            cardInfo = iCardService.getCard(id);
            if (cardInfo != null) {
                CardGatherInfo cardGatherInfo = new CardGatherInfo();

                cardGatherInfo.setId(cardInfo.getId());
                cardGatherInfo.setCardName(cardInfo.getCardName());
                cardGatherInfo.setCardNum(cardInfo.getCardNum());
                //查询此卡当月刷卡次数.
                QueryWrapper<PayRecordDO> monthWrapper = new QueryWrapper<>();
                monthWrapper.eq("card_id", cardInfo.getId());
                String startTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) ) + "-" + (Integer.parseInt(cardInfo.getBillDate())+1);
                String endTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + cardInfo.getBillDate();
                monthWrapper.between("pay_date", startTime, endTime);
                Integer monthCount = iPayRecordService.payRecordCount(monthWrapper);
                Map<String,Object> monthSum=iPayRecordService.payRecordSum(monthWrapper);
                cardGatherInfo.setMonthCount(monthCount);
                cardGatherInfo.setMonthSum(monthSum);
                //查询此卡当年刷卡次数.
                QueryWrapper<PayRecordDO> yearWrapper = new QueryWrapper<>();
                yearWrapper.eq("card_id", cardInfo.getId());
                startTime = calendar.get(Calendar.YEAR) + "-01-01";
                endTime = calendar.get(Calendar.YEAR) + "-12-31";
                yearWrapper.between("pay_date", startTime, endTime);
                Integer yearCount = iPayRecordService.payRecordCount(yearWrapper);
                Map<String,Object> yearSum=iPayRecordService.payRecordSum(yearWrapper);
                cardGatherInfo.setYearCount(yearCount);
                cardGatherInfo.setYearSum(yearSum);


                cardGatherInfos.add(cardGatherInfo);
            }
            id++;
        } while (cardInfo != null);


        return cardGatherInfos;
    }
}
