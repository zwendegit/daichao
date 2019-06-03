package com.daichao.admin.input.banner;

import java.util.Date;

import com.daichao.bean.input.BaseInput;

import lombok.Data;
@Data
public class BannerInput extends BaseInput{

	private Integer id;

    private String title;

    private String picUrl;

    private String linkUrl;

    private String startTime;

    private String endTime;

    private Integer status;

    private Integer type;

    private String productIds;

    private String remark;

    private Integer sort;
    private String productName;
}
