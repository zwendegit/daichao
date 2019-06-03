package com.daichao.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.advert.AdvertInput;
import com.daichao.admin.input.advert.AdvertPageInput;
import com.daichao.admin.service.DcAdvertService;
import com.daichao.bean.advert.DcAdvert;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="AdvertController",description="广告位管理")
@RequestMapping("/advert")
@RestController
public class AdvertController extends BaseController{

	@Autowired
	private DcAdvertService dcAdvertService;
	
	@ApiOperation(value = "广告位产品列表", notes = "分页")
	@RequestMapping(value="/advertPage",method = RequestMethod.POST)
	public ResultOutput advertPage(AdvertPageInput input){
        Page<DcAdvert> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcAdvert> list=dcAdvertService.queryAdvertList(input.getLocation());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "广告位添加", notes = "添加")
	@RequestMapping(value="/advertSave",method = RequestMethod.POST)
	public ResultOutput advertSave(AdvertInput input,HttpServletRequest request){
		return dcAdvertService.advertSave(input, getUser(request).getId());
	}
	
	@ApiOperation(value = "广告位产品下架", notes = "下架")
	@RequestMapping(value="/advertUupate",method = RequestMethod.POST)
	public ResultOutput advertUupate(@RequestParam Integer id,HttpServletRequest request){
		return dcAdvertService.advertUupate(id, getUser(request).getId());
	}
	
	@ApiOperation(value = "广告位产品可上架列表", notes = "列表")
	@RequestMapping(value="/advertProductList",method = RequestMethod.POST)
	public ResultOutput advertProductList(@RequestParam Integer location){
		return dcAdvertService.advertProductList(location) ;
	}
}
