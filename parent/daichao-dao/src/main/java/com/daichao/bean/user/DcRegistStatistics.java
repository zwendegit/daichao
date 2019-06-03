package com.daichao.bean.user;

import java.math.BigDecimal;
import java.util.Date;

public class DcRegistStatistics {
    private Integer id;

    private Integer channelId;

    private String channelCode;

    private String channelName;

    private Integer registCount;

    private Double registDeductionCount;

    private Date createTime;
    
    private BigDecimal channelPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public Integer getRegistCount() {
        return registCount;
    }

    public void setRegistCount(Integer registCount) {
        this.registCount = registCount;
    }

    public Double getRegistDeductionCount() {
        return registDeductionCount;
    }

    public void setRegistDeductionCount(Double registDeductionCount) {
        this.registDeductionCount = registDeductionCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public BigDecimal getChannelPrice() {
		return channelPrice;
	}

	public void setChannelPrice(BigDecimal channelPrice) {
		this.channelPrice = channelPrice;
	}
    
}