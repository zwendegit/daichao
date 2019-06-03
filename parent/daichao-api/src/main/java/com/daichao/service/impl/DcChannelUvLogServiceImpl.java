package com.daichao.service.impl;

import com.daichao.bean.channel.DcChannelUvLog;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.system.DcSystemConfig;
import com.daichao.dao.channel.DcChannelUvLogMapper;
import com.daichao.dao.system.DcSystemConfigMapper;
import com.daichao.service.DcChannelUvLogService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DcChannelUvLogServiceImpl implements DcChannelUvLogService {

    @Resource
    private DcChannelUvLogMapper dcChannelUvLogMapper;
    @Autowired
    private DcSystemConfigMapper dcSystemConfigMapper;

    @Override
    public ResultOutput StatisticsChannel(String channelCode, String deviceId) {
    	if(StringUtils.isNotEmpty(channelCode)) {
    		Pattern p = Pattern.compile("[^0-9a-zA-Z\\u4e00-\\u9fa5.，,。？“”]+");
	        Matcher m = p.matcher(channelCode);
	        channelCode = m.replaceAll("");
    	}
        int count = dcChannelUvLogMapper.selectCountToday(channelCode, deviceId);
        if(count==0){
            DcChannelUvLog dcChannelUvLog = new DcChannelUvLog();
            dcChannelUvLog.setChannelCode(channelCode);
            dcChannelUvLog.setCreateTime(new Date());
            dcChannelUvLog.setH5DeviceId(deviceId);
            dcChannelUvLogMapper.insertSelective(dcChannelUvLog);
        }
        Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", 2);
		map.put("status", 1);
		List<DcSystemConfig> configList=dcSystemConfigMapper.querySystemConfigList(map);
		return new ResultOutput(configList);
    }
}
