package com.daichao.bean.channel;

import java.math.BigDecimal;
import java.util.Date;

public class DcChannelStatisticsHistory {
    private Integer id;

    private String code;

    private String codeName;

    private Integer channelUvCount;

    private Integer registCountDay;

    private Integer loginCountDay;

    private Integer registLoginCountDay;

    private Integer uvSumDay;

    private BigDecimal moneySum;

    private BigDecimal zcMoney;

    private BigDecimal jsPrice;

    private Integer threshold;

    private BigDecimal deductionRate;

    private Integer registCount;

    private Integer registDeductionCount;

    private BigDecimal roi;

    private BigDecimal channelPrice;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName == null ? null : codeName.trim();
    }

    public Integer getChannelUvCount() {
        return channelUvCount;
    }

    public void setChannelUvCount(Integer channelUvCount) {
        this.channelUvCount = channelUvCount;
    }

    public Integer getRegistCountDay() {
        return registCountDay;
    }

    public void setRegistCountDay(Integer registCountDay) {
        this.registCountDay = registCountDay;
    }

    public Integer getLoginCountDay() {
        return loginCountDay;
    }

    public void setLoginCountDay(Integer loginCountDay) {
        this.loginCountDay = loginCountDay;
    }

    public Integer getRegistLoginCountDay() {
        return registLoginCountDay;
    }

    public void setRegistLoginCountDay(Integer registLoginCountDay) {
        this.registLoginCountDay = registLoginCountDay;
    }

    public Integer getUvSumDay() {
        return uvSumDay;
    }

    public void setUvSumDay(Integer uvSumDay) {
        this.uvSumDay = uvSumDay;
    }

    public BigDecimal getMoneySum() {
        return moneySum;
    }

    public void setMoneySum(BigDecimal moneySum) {
        this.moneySum = moneySum;
    }

    public BigDecimal getZcMoney() {
        return zcMoney;
    }

    public void setZcMoney(BigDecimal zcMoney) {
        this.zcMoney = zcMoney;
    }

    public BigDecimal getJsPrice() {
        return jsPrice;
    }

    public void setJsPrice(BigDecimal jsPrice) {
        this.jsPrice = jsPrice;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(BigDecimal deductionRate) {
        this.deductionRate = deductionRate;
    }

    public Integer getRegistCount() {
        return registCount;
    }

    public void setRegistCount(Integer registCount) {
        this.registCount = registCount;
    }

    public Integer getRegistDeductionCount() {
        return registDeductionCount;
    }

    public void setRegistDeductionCount(Integer registDeductionCount) {
        this.registDeductionCount = registDeductionCount;
    }

    public BigDecimal getRoi() {
        return roi;
    }

    public void setRoi(BigDecimal roi) {
        this.roi = roi;
    }

    public BigDecimal getChannelPrice() {
        return channelPrice;
    }

    public void setChannelPrice(BigDecimal channelPrice) {
        this.channelPrice = channelPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}