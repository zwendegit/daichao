package com.daichao.admin.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.daichao.admin.service.StatisticsService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class TaskController {

	
	@Autowired
	private StatisticsService statisticsService;
	/**
	 * 凌晨00：10执行预测快照
	 */
	@Scheduled(cron="0 10 00 * * ?") 
//	@Scheduled(cron="0 25 11 * * ?") 
	public void statisticsTask() {
		Date date=new Date();
		log.info("预测快照任务开始------------");
		statisticsService.statisticsTask();
		log.info("预测快照任务结束------------,耗时："+(new Date().getTime()-date.getTime())/1000+"秒！");
	}
}
