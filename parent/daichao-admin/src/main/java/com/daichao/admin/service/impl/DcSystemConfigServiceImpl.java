package com.daichao.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.input.system.SystemConfigInput;
import com.daichao.admin.input.system.SystemConfigListInput;
import com.daichao.admin.input.system.SystemConfigPage;
import com.daichao.admin.service.DcSystemConfigService;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.system.DcSystemConfig;
import com.daichao.dao.system.DcSystemConfigMapper;
@Service
public class DcSystemConfigServiceImpl implements DcSystemConfigService {

	@Autowired
	private DcSystemConfigMapper dcSystemConfigMapper;
	@Override
	public ResultOutput configEdit(SystemConfigInput config,Integer userId) {
		if(Objects.isNull(config.getType())) {
			return new ResultOutput("1", "type必选");
		}
		DcSystemConfig systemConfig=new DcSystemConfig();
		BeanUtils.copyProperties(config, systemConfig);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", config.getType());
		map.put("code", config.getCode());
		List<DcSystemConfig> configList=dcSystemConfigMapper.querySystemConfigList(map);
		if(Objects.isNull(config.getId())) {
			if(!CollectionUtils.isEmpty(configList)) return new ResultOutput("1", "code已 存在");
			else {
				systemConfig.setCreateTime(new Date());
				systemConfig.setUpdateUid(userId);
				dcSystemConfigMapper.insertSelective(systemConfig);
			}	
		}else {
			if(!CollectionUtils.isEmpty(configList)) {
				for (DcSystemConfig dcSystemConfig : configList) {
					if(dcSystemConfig.getId()!=config.getId()) {
						return new ResultOutput("1", "code已 存在");
					}
				}
			}
			systemConfig.setUpdateUid(userId);
			systemConfig.setUpdateTime(new Date());
			dcSystemConfigMapper.updateByPrimaryKeySelective(systemConfig);
		}
		return new ResultOutput();
	}
	@Override
	public List<DcSystemConfig> configPage(SystemConfigPage input) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", input.getType());
		map.put("status", input.getStatus());
		return dcSystemConfigMapper.querySystemConfigList(map);
	}
	@Override
	public List<DcSystemConfig> configList(SystemConfigListInput config) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", config.getType());
		map.put("status", StringUtils.isNotEmpty(config.getCode())?config.getCode():null);
		map.put("status", 1);
		return dcSystemConfigMapper.querySystemConfigList(map);
	}

}
