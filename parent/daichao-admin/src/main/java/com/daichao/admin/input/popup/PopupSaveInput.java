package com.daichao.admin.input.popup;

import java.util.Date;

import com.daichao.bean.input.BaseInput;

import lombok.Data;
@Data
public class PopupSaveInput extends BaseInput{

	private Integer id;

    private String name;

    private String startTime;

    private String endTime;

    private Integer status;

    private String picImg;

    private Integer type;

    private String productUrl;

    private Integer productId;
}
