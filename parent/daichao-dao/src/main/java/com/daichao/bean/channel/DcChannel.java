package com.daichao.bean.channel;

import java.math.BigDecimal;

public class DcChannel {
    private Integer id;

    private String name;

    private String code;

    private Integer type;

    private Integer status;

    private String url;
    private BigDecimal price;
    private Integer threshold;
    private BigDecimal deductionRate;
    private Integer urlStatus;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Integer getUrlStatus() {
		return urlStatus;
	}

	public void setUrlStatus(Integer urlStatus) {
		this.urlStatus = urlStatus;
	}
    
}