package com.daichao.admin.service;

import java.util.List;

import com.daichao.bean.channel.DcChannelStatisticsHistory;
import com.daichao.bean.output.ResultOutput;

public interface StatisticsService {

	ResultOutput statistics(String code);
	/**
	 * 执行预测快照
	 * @return
	 */
	ResultOutput statisticsTask();
	
	List<DcChannelStatisticsHistory> statisticsHistory(String code,String time);
	
	DcChannelStatisticsHistory channelStatistics(String code,String time);
}
