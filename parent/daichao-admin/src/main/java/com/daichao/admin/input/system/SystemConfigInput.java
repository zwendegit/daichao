package com.daichao.admin.input.system;

import com.daichao.bean.input.BaseInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class SystemConfigInput extends BaseInput{

	private Integer id;

	@ApiParam("类型 1： h5标识 0:其他")
    private Integer type;

	@ApiParam("标识")
    private String code;

	@ApiParam("值")
    private String value;

	@ApiParam("描述")
    private String remark;

	@ApiParam("1有效 0无效")
    private Integer status;
}
