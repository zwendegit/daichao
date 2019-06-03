package com.daichao.bean.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DcThirdProduct implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4973426317434216241L;

	private Integer id;

    private String name;

    private BigDecimal amountLimitMin;

    private BigDecimal amountLimitMax;

    private Date effectTime;

    private String tagId;

    private String iconUrl;

    private String linkUrl;

    private String rate;

    private String timeLimit;

    private String lendingTime;

    private String repayStyle;

    private String aptitude;

    private String material;

    private Integer lendingSuccessCount;

    private String successRate;

    private Integer status;

    private String description;

    private String remark;

    private Integer sort;

    private Integer priceType;

    private Integer priceModel;

    private BigDecimal price;

    private BigDecimal uv6Privce;

    private BigDecimal amount;

    private BigDecimal rechargeAmount;

    private String productUser;

    private Integer createId;

    private Integer updateId;

    private Date createTime;

    private Date updateTime;

    private String customerServicePhone;
    private String cd;

    private String suffix;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getAmountLimitMin() {
        return amountLimitMin;
    }

    public void setAmountLimitMin(BigDecimal amountLimitMin) {
        this.amountLimitMin = amountLimitMin;
    }

    public BigDecimal getAmountLimitMax() {
        return amountLimitMax;
    }

    public void setAmountLimitMax(BigDecimal amountLimitMax) {
        this.amountLimitMax = amountLimitMax;
    }

    public Date getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit == null ? null : timeLimit.trim();
    }

    public String getLendingTime() {
        return lendingTime;
    }

    public void setLendingTime(String lendingTime) {
        this.lendingTime = lendingTime == null ? null : lendingTime.trim();
    }

    public String getRepayStyle() {
        return repayStyle;
    }

    public void setRepayStyle(String repayStyle) {
        this.repayStyle = repayStyle == null ? null : repayStyle.trim();
    }

    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude == null ? null : aptitude.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public Integer getLendingSuccessCount() {
        return lendingSuccessCount;
    }

    public void setLendingSuccessCount(Integer lendingSuccessCount) {
        this.lendingSuccessCount = lendingSuccessCount;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate == null ? null : successRate.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public BigDecimal getUv6Privce() {
        return uv6Privce;
    }

    public void setUv6Privce(BigDecimal uv6Privce) {
        this.uv6Privce = uv6Privce;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getProductUser() {
        return productUser;
    }

    public void setProductUser(String productUser) {
        this.productUser = productUser == null ? null : productUser.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerServicePhone() {
        return customerServicePhone;
    }

    public void setCustomerServicePhone(String customerServicePhone) {
        this.customerServicePhone = customerServicePhone == null ? null : customerServicePhone.trim();
    }

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
    
}