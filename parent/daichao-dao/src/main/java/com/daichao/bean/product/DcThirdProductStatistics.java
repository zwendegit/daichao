package com.daichao.bean.product;

import java.math.BigDecimal;
import java.util.Date;

public class DcThirdProductStatistics {
    private Integer id;

    private Integer thirdProductId;

    private Integer dailyClicksCount;

    private Integer distinctClicksCount;

    private Integer cpaCount;

    private Date createTime;
    
    private String productName;
    private Integer priceType;
    private BigDecimal productPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThirdProductId() {
        return thirdProductId;
    }

    public void setThirdProductId(Integer thirdProductId) {
        this.thirdProductId = thirdProductId;
    }

    public Integer getDailyClicksCount() {
        return dailyClicksCount;
    }

    public void setDailyClicksCount(Integer dailyClicksCount) {
        this.dailyClicksCount = dailyClicksCount;
    }

    public Integer getDistinctClicksCount() {
        return distinctClicksCount;
    }

    public void setDistinctClicksCount(Integer distinctClicksCount) {
        this.distinctClicksCount = distinctClicksCount;
    }

    public Integer getCpaCount() {
        return cpaCount;
    }

    public void setCpaCount(Integer cpaCount) {
        this.cpaCount = cpaCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
    
}