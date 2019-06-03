package com.daichao.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.input.regist.RegistStatisticsPageInput;
import com.daichao.admin.service.RegistStatisticsService;
import com.daichao.bean.channel.DcChannel;
import com.daichao.bean.user.DcRegistStatistics;
import com.daichao.dao.channel.DcChannelMapper;
import com.daichao.dao.user.DcRegistStatisticsMapper;
@Service
public class RegistStatisticsServiceImpl implements RegistStatisticsService{

	@Autowired
	private DcRegistStatisticsMapper dcRegistStatisticsMapper;
	@Autowired
	private DcChannelMapper dcChannelMapper;
	@Override
	public List<DcRegistStatistics> queryRegistStatisticsList(RegistStatisticsPageInput input,Integer channelId,Integer type) {
		if(type==2) {
			DcChannel channel=dcChannelMapper.selectByPrimaryKey(channelId);
			if(channel==null) {
				return new ArrayList<DcRegistStatistics>();
			}
			input.setCode(channel.getCode());
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("code", StringUtils.isNotEmpty(input.getCode())?input.getCode():null);
		if(StringUtils.isNotEmpty(input.getTime())) {
			map.put("startTime", input.getTime()+" 00:00:00");
			map.put("endTime", input.getTime()+" 23:59:59");
		}
		List<DcRegistStatistics> statis=dcRegistStatisticsMapper.queryRegistStatisticsList(map);
		if(!CollectionUtils.isEmpty(statis)) {
			for (DcRegistStatistics dcRegistStatistics : statis) {
				if(type==2) {
					dcRegistStatistics.setRegistCount(doubleToInt(dcRegistStatistics.getRegistDeductionCount()));
					dcRegistStatistics.setRegistDeductionCount(null);
				}else dcRegistStatistics.setRegistDeductionCount(Math.floor(dcRegistStatistics.getRegistDeductionCount()));
			}
		}
		return statis;
	}

    public static int doubleToInt(Double value) {
    	if(value<1) return 1;
    	else return value.intValue() ;
    }
}
