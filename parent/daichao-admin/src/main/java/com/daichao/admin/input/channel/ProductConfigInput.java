package com.daichao.admin.input.channel;

import java.math.BigDecimal;
import java.util.Date;

import com.daichao.bean.input.BaseInput;

import lombok.Data;
@Data
public class ProductConfigInput extends BaseInput{

	private Integer id;

    private Integer channelId;

    private Integer productId;

    private Integer type;

    private String startTime;

    private String endTime;

    private BigDecimal deductionRate;

    private Integer updateId;

    private Date updateTime;
}
