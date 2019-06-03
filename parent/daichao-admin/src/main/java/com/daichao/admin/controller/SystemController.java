package com.daichao.admin.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.picUpload.PicUploadInput;
import com.daichao.admin.input.statistics.StatisticsHistoryPageInput;
import com.daichao.admin.input.system.SystemConfigInput;
import com.daichao.admin.input.system.SystemConfigListInput;
import com.daichao.admin.input.system.SystemConfigPage;
import com.daichao.admin.service.DcSystemConfigService;
import com.daichao.admin.service.StatisticsService;
import com.daichao.admin.service.impl.PicUploadServiceImpl;
import com.daichao.bean.channel.DcChannelStatisticsHistory;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.system.DcSystemConfig;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="SystemController",description="系统管理")
@RequestMapping("/system")
@RestController
public class SystemController extends BaseController{

	@Autowired
	private PicUploadServiceImpl picUploadService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private DcSystemConfigService dcSystemConfigService;
	@RequestMapping(value="picUpload",method=RequestMethod.POST)
    @ApiOperation(value = "图片上传", notes = "图片上传")
	public ResultOutput picUpload(PicUploadInput input) {
		return picUploadService.picUpload(input);
	}

	@RequestMapping(value="statistics",method=RequestMethod.POST)
    @ApiOperation(value = "预测统计", notes = "统计")
	public ResultOutput statistics(@ApiParam("渠道名称") @RequestParam(required=false) String code) {
		return statisticsService.statistics(code); 
	}
	
	@RequestMapping(value="statisticsHistory",method=RequestMethod.POST)
    @ApiOperation(value = "预测统计历史", notes = "分页")
	public ResultOutput statisticsHistory(StatisticsHistoryPageInput input) {
		Page<DcChannelStatisticsHistory> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcChannelStatisticsHistory> list=statisticsService.statisticsHistory(input.getCode(),input.getTime());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@RequestMapping(value="channelStatistics",method=RequestMethod.POST)
    @ApiOperation(value = "预测统计历史统计", notes = "统计")
	public ResultOutput channelStatistics(StatisticsHistoryPageInput input) {
        DcChannelStatisticsHistory statistics=statisticsService.channelStatistics(input.getCode(),input.getTime());
		return new ResultOutput(statistics);
	}
	
	@RequestMapping(value="configPage",method=RequestMethod.POST)
    @ApiOperation(value = "系统配置列表", notes = "分页")
	public ResultOutput configPage(SystemConfigPage input) {
		Page<DcSystemConfig> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcSystemConfig> list=dcSystemConfigService.configPage(input);
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@RequestMapping(value="configList",method=RequestMethod.POST)
    @ApiOperation(value = "系统配置列表", notes = "列表")
	public ResultOutput configList(SystemConfigListInput config) {
		return new ResultOutput(dcSystemConfigService.configList(config)) ; 
	}
	
	@RequestMapping(value="configEdit",method=RequestMethod.POST)
    @ApiOperation(value = "系统配置编辑", notes = "编辑")
	public ResultOutput configEdit(SystemConfigInput config,HttpServletRequest request) {
		return dcSystemConfigService.configEdit(config,getUser(request).getId()); 
	}
}
