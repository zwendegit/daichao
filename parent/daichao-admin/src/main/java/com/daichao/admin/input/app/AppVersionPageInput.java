package com.daichao.admin.input.app;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class AppVersionPageInput extends PageInput{

	@ApiParam("版本号")
	private String versionName;
	@ApiParam("类型")
	private String deviceType;
}
