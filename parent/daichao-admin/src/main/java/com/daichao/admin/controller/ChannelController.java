package com.daichao.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.channel.ChannelPageInput;
import com.daichao.admin.input.channel.ProductConfigInput;
import com.daichao.admin.service.ChannelServie;
import com.daichao.bean.channel.DcChannel;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ChannelController",description="渠道管理")
@RequestMapping("/channel")
@RestController
public class ChannelController extends BaseController{

	@Autowired
	private ChannelServie channelService;
	@ApiOperation(value = "渠道列表", notes = "分页")
	@RequestMapping(value="/channelPage",method = RequestMethod.POST)
	public ResultOutput channelPage(ChannelPageInput input){
        Page<DcChannel> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcChannel> list=channelService.queryChannelList(input);
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "渠道列表", notes = "列表")
	@RequestMapping(value="/channelList",method = RequestMethod.POST)
	public ResultOutput channelList(ChannelPageInput input){
        List<DcChannel> list=channelService.queryChannelList(input);
		return new ResultOutput(list);
	}
	
	@ApiOperation(value = "渠道code列表", notes = "列表")
	@RequestMapping(value="/channelCodeList",method = RequestMethod.POST)
	public ResultOutput channelList(@RequestParam(required=false) Integer status ){
        List<String> list=channelService.queryChannelCodeList(status);
		return new ResultOutput(list);
	}
	
	@ApiOperation(value = "渠道编辑", notes = "编辑")
	@RequestMapping(value="/channelSave",method = RequestMethod.POST)
	public ResultOutput channelSave(DcChannel input){
		return channelService.channelEdit(input);
	}
	
	@ApiOperation(value = "渠道链接开关编辑", notes = "编辑")
	@RequestMapping(value="/channelUrlStatusUpdate",method = RequestMethod.POST)
	public ResultOutput channelUrlStatusUpdate(@RequestParam Integer status,@RequestParam Integer id){
		return channelService.channelUrlStatusUpdate(status,id);
	}
	
	@ApiOperation(value = "渠道扣量规则详情", notes = "详情")
	@RequestMapping(value="/channelConfigDetail",method = RequestMethod.POST)
	public ResultOutput channelConfigDetail(Integer channelId){
		return channelService.channelConfigDetail(channelId);
	}
	
	@ApiOperation(value = "渠道扣量规则编辑", notes = "编辑")
	@RequestMapping(value="/channelConfigSave",method = RequestMethod.POST)
	public ResultOutput channelConfigSave(ProductConfigInput config,HttpServletRequest request){
		return channelService.channelConfigSave(config,getUser(request).getId());
	}
}
