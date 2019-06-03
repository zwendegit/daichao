package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.product.statistics.StatisticsPageInput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProductHistory;
import com.daichao.bean.product.DcThirdProductStatistics;

public interface ProductStatisticsService {

	List<DcThirdProductStatistics> queryProductStatisticsList(StatisticsPageInput input,Integer productId,Integer type);
	
	List<DcThirdProductHistory> productStatisticsDetailList(Integer productId,String time);
	
	ResultOutput productStatisticsUpdate(Integer count,Integer id);
}
