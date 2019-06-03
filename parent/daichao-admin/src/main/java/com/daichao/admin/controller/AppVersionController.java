package com.daichao.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.app.AppVersionConfigPageInput;
import com.daichao.admin.input.app.AppVersionPageInput;
import com.daichao.admin.service.DcAppVersionService;
import com.daichao.bean.app.DcAppVersion;
import com.daichao.bean.app.DcAppVersionConfig;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="AppVersionController",description="app版本管理")
@RequestMapping("/version")
@RestController
public class AppVersionController extends BaseController{

	@Autowired
	private DcAppVersionService dcAppVersionService;
	@ApiOperation(value = "app版本列表", notes = "分页")
	@RequestMapping(value="/AppVersionPage",method = RequestMethod.POST)
	public ResultOutput AppVersionPage(AppVersionPageInput input){
		Page<DcAppVersion> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
		List<DcAppVersion> list=dcAppVersionService.queryAppVersionList(input);
		PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
	    pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "app版本编辑", notes = "编辑")
	@RequestMapping(value="/AppVersionUpdate",method = RequestMethod.POST)
	public ResultOutput AppVersionUpdate(DcAppVersion input,String access_token){
		return dcAppVersionService.appVersionUpdate(input);
	}
	
	@ApiOperation(value = "app版本开关控制列表", notes = "分页")
	@RequestMapping(value="/AppVersionConfigPage",method = RequestMethod.POST)
	public ResultOutput AppVersionConfigPage(AppVersionConfigPageInput input){
		Page<DcAppVersionConfig> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
		List<DcAppVersionConfig> list=dcAppVersionService.queryAppVersionConfigList(input);
		PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
	    pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "app版本开关控制编辑", notes = "编辑")
	@RequestMapping(value="/AppVersionConfigUpdate",method = RequestMethod.POST)
	public ResultOutput AppVersionConfigUpdate(DcAppVersionConfig input,HttpServletRequest request){
		return dcAppVersionService.appVersionConfigUpdate(input,getUser(request).getId());
	}
}
