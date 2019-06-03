package com.daichao.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.product.ProductHistoryPageInput;
import com.daichao.admin.input.product.statistics.StatisticsPageInput;
import com.daichao.admin.service.ProductStatisticsService;
import com.daichao.bean.admin.DcAdminUser;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProductHistory;
import com.daichao.bean.product.DcThirdProductStatistics;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ProductStatisticsController",description="贷超统计管理")
@RequestMapping("/statistics")
@RestController
public class ProductStatisticsController extends BaseController{

	@Autowired
	private ProductStatisticsService productStatisticsService;
	@ApiOperation(value = "贷超统计列表", notes = "分页")
	@RequestMapping(value="/productStatisticsPage",method = RequestMethod.POST)
	public ResultOutput productStatisticsPage(StatisticsPageInput input,HttpServletRequest request){
		DcAdminUser user=getUser(request);
		if(user.getType()==2) {
			return new ResultOutput("100001", "无权限");
		}
		Page<DcThirdProductStatistics> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcThirdProductStatistics> list=productStatisticsService.queryProductStatisticsList(input,user.getProductId(),user.getType());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "贷超统计明细", notes = "明细分页")
	@RequestMapping(value="/productStatisticsDetailList",method = RequestMethod.POST)
	public ResultOutput productStatisticsDetailList(ProductHistoryPageInput input){
		Page<DcThirdProductHistory> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcThirdProductHistory> list=productStatisticsService.productStatisticsDetailList(input.getProductId(), input.getTime());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
	
	@ApiOperation(value = "cpa个数修改", notes = "cpa个数修改")
	@RequestMapping(value="/productStatisticsUpdate",method = RequestMethod.POST)
	public ResultOutput productStatisticsUpdate(@RequestParam Integer count,@RequestParam Integer id){
		return productStatisticsService.productStatisticsUpdate(count, id);
	}
}
