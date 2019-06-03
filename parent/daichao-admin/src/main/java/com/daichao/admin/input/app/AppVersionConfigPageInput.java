package com.daichao.admin.input.app;

import com.daichao.bean.input.PageInput;

import io.swagger.annotations.ApiParam;
import lombok.Data;
@Data
public class AppVersionConfigPageInput extends PageInput{

	@ApiParam("版本号")
	private String version;
	@ApiParam("设备类型 ios android")
	private String deviceType;
	@ApiParam("包类型(ios用0主包1马甲包1号2马甲包2号)")
	private String packageType;
}
