package com.daichao.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.daichao.admin.input.regist.RegistStatisticsPageInput;
import com.daichao.admin.service.RegistStatisticsService;
import com.daichao.bean.admin.DcAdminUser;
import com.daichao.bean.output.PageOutput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.user.DcRegistStatistics;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="RegistStatisticsController",description="渠道注册统计管理")
@RequestMapping("/registStatistics")
@RestController
public class RegistStatisticsController extends BaseController{

	@Autowired
	private RegistStatisticsService registStatisticsService;
	@ApiOperation(value = "渠道统计列表", notes = "分页")
	@RequestMapping(value="/registStatisticsPage",method = RequestMethod.POST)
	public ResultOutput registStatisticsPage(RegistStatisticsPageInput input,HttpServletRequest request){
		DcAdminUser user=getUser(request);
		if(user.getType()==1) {
			return new ResultOutput("100001", "无权限");
		}
		Page<DcRegistStatistics> page = PageHelper.startPage(input.getPageNum(), input.getPageSize(), true);
        List<DcRegistStatistics> list=registStatisticsService.queryRegistStatisticsList(input,user.getProductId(),user.getType());
        PageOutput pageout=new PageOutput(page.getTotal(), input.getPageNum());
        pageout.setData(list);
		return new ResultOutput(pageout);
	}
}
