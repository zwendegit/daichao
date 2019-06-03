package com.daichao.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.banner.BannerInput;
import com.daichao.admin.input.banner.BannerPageInput;
import com.daichao.admin.service.BannerService;
import com.daichao.bean.banner.DcBanner;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="BannerController",description="banner管理")
@RequestMapping("/banner")
@RestController
public class BannerController extends BaseController{

	@Autowired
	private BannerService bannerService;
	@ApiOperation(value = "banner列表", notes = "分页")
	@RequestMapping(value="/bannerPage",method = RequestMethod.POST)
	public ResultOutput bannerPage(BannerPageInput input){
		Page<DcBanner> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
		List<DcBanner> list=bannerService.queryBannerList(input);
		PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
	    pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "banner管理", notes = "编辑")
	@RequestMapping(value="/bannerSave",method = RequestMethod.POST)
	public ResultOutput bannerSave(BannerInput input,HttpServletRequest request){
		 return bannerService.bannerSave(input,getUser(request).getId());
	}
}
