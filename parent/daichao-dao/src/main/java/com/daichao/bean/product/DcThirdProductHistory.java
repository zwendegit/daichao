package com.daichao.bean.product;

import java.math.BigDecimal;
import java.util.Date;

public class DcThirdProductHistory {
    private Integer id;

    private Integer thirdProductId;

    private Integer userId;

    private String phone;

    private Date createTime;
    
    private Integer priceType;
    
    private Integer priceModel;
    private BigDecimal price;
    private BigDecimal uv6Price;
    
    private Date registTime;
    private String registChannel;
    private Date firstLoginTime;
    private String firstLoginChannel;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public Integer getPriceModel() {
		return priceModel;
	}

	public void setPriceModel(Integer priceModel) {
		this.priceModel = priceModel;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getUv6Price() {
		return uv6Price;
	}

	public void setUv6Price(BigDecimal uv6Price) {
		this.uv6Price = uv6Price;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public String getRegistChannel() {
		return registChannel;
	}

	public void setRegistChannel(String registChannel) {
		this.registChannel = registChannel;
	}

	public Date getFirstLoginTime() {
		return firstLoginTime;
	}

	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}

	public String getFirstLoginChannel() {
		return firstLoginChannel;
	}

	public void setFirstLoginChannel(String firstLoginChannel) {
		this.firstLoginChannel = firstLoginChannel;
	}
    
}