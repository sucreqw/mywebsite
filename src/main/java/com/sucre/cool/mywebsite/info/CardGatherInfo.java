package com.sucre.cool.mywebsite.info;

import java.math.BigDecimal;
import java.util.Map;

public class CardGatherInfo
{
    private Integer id;

    private Integer monthCount;

    private Integer yearCount;

    private Map<String,Object> monthSum;

    private Map<String,Object> yearSum;

    private String cardName;

    private String cardNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public Integer getYearCount() {
        return yearCount;
    }

    public void setYearCount(Integer yearCount) {
        this.yearCount = yearCount;
    }


    public Map<String, Object> getMonthSum() {
        return monthSum;
    }

    public void setMonthSum(Map<String, Object> monthSum) {
        this.monthSum = monthSum;
    }

    public Map<String, Object> getYearSum() {
        return yearSum;
    }

    public void setYearSum(Map<String, Object> yearSum) {
        this.yearSum = yearSum;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
