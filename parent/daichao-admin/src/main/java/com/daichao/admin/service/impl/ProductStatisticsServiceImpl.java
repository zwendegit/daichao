package com.daichao.admin.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.product.statistics.StatisticsPageInput;
import com.daichao.admin.service.ProductStatisticsService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProductHistory;
import com.daichao.bean.product.DcThirdProductStatistics;
import com.daichao.dao.product.DcThirdProductHistoryMapper;
import com.daichao.dao.product.DcThirdProductStatisticsMapper;
import com.daichao.utils.DateUtil;
@Service
public class ProductStatisticsServiceImpl implements ProductStatisticsService{

	@Autowired
	private DcThirdProductStatisticsMapper dcThirdProductStatisticsMapper;
	@Autowired
	private DcThirdProductHistoryMapper dcThirdProductHistoryMapper;
	@Override
	public List<DcThirdProductStatistics> queryProductStatisticsList(StatisticsPageInput input,Integer productId,Integer type) {
		Map<String, Object> map=new HashMap<>();
		if(type==1) {
			map.put("productId", productId);
		}
		map.put("name", StringUtils.isNotEmpty(input.getProductName())?input.getProductName():null);
		if(StringUtils.isNotEmpty( input.getTime())) {
			map.put("startTime", input.getTime()+" 00:00:00");
			map.put("endTime", input.getTime()+" 23:59:59");
			map.put("sortValue", "a.distinct_clicks_count desc,");
		}
		return dcThirdProductStatisticsMapper.queryProductStatisticsList(map);
	}
	@Override
	public List<DcThirdProductHistory> productStatisticsDetailList(Integer productId, String time) {
		Map<String, Object> map=new HashMap<>();
		try {
			map.put("productId", productId!=null&&productId>0?productId:null);
			if(StringUtils.isNotEmpty(time)) {
				map.put("startTime", DateUtil.getStringDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time), "yyyy-MM-dd")+" 00:00:00");
				map.put("endTime", DateUtil.getStringDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time), "yyyy-MM-dd")+" 23:59:59");
			}
		} catch (ParseException e) {
			return new ArrayList<>();
		}
		return dcThirdProductHistoryMapper.queryProductHistoryByProductIdAndStatus(map);
	}
	@Override
	public ResultOutput productStatisticsUpdate(Integer count,Integer id) {
		if(id!=null&&id>0) {
			DcThirdProductStatistics statis= dcThirdProductStatisticsMapper.selectByPrimaryKey(id);
			if(statis==null) {
				return new ResultOutput("1", "记录不存在");
			}
			statis.setCpaCount(count);
			dcThirdProductStatisticsMapper.updateByPrimaryKeySelective(statis);
		}
		return new ResultOutput();
	}

}
