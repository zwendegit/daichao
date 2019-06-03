package com.daichao.admin.input.product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.daichao.bean.input.BaseInput;
import com.daichao.bean.product.DcThirdProduct;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class ProductEditInput extends BaseInput{

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

    private Integer priceModel=1;

    private BigDecimal price;

    private BigDecimal amount;

    private BigDecimal rechargeAmount;

    private String productUser;

    private Integer createId;

    private Integer updateId;

    private Date createTime;

    private Date updateTime;
    private BigDecimal uv6Privce;

    private String customerServicePhone;
	@ApiParam("新增的tag标签id集合,分割")
	private String tagAddIds;
	@ApiParam("要删除的tag标签id集合,分割")
	private String tagDelIds;
	
}
