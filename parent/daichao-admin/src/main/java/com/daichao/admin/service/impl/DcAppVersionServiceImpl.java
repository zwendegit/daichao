package com.daichao.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daichao.admin.input.app.AppVersionConfigPageInput;
import com.daichao.admin.input.app.AppVersionPageInput;
import com.daichao.admin.service.DcAppVersionService;
import com.daichao.bean.app.DcAppVersion;
import com.daichao.bean.app.DcAppVersionConfig;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.app.DcAppVersionConfigMapper;
import com.daichao.dao.app.DcAppVersionMapper;
import com.daichao.utils.BeanUtil;
@Service
public class DcAppVersionServiceImpl implements DcAppVersionService{

	@Autowired
	private DcAppVersionMapper dcAppVersionMapper;
	@Autowired
	private DcAppVersionConfigMapper dcAppVersionConfigMapper;
	@Override
	public List<DcAppVersion> queryAppVersionList(AppVersionPageInput input) {
		Map<String, Object> map=new HashMap<>();
		map.put("versionName", StringUtils.isNotEmpty(input.getVersionName())?input.getVersionName():null);
		map.put("deviceType", StringUtils.isNotEmpty(input.getDeviceType())?input.getDeviceType():null);
		return dcAppVersionMapper.queryAppVersionList(map);
	}
	@Override
	public ResultOutput appVersionUpdate(DcAppVersion version) {
		try {
			BeanUtil.beanAttributeValueTrim(version);
		} catch (Exception e1) {
			e1.printStackTrace();
			return new ResultOutput("1", "参数异常");
		}
		if(version.getId()!=null&&version.getId()>0) dcAppVersionMapper.updateByPrimaryKeySelective(version);
		else dcAppVersionMapper.insertSelective(version);
		return new ResultOutput();
	}
	@Override
	public List<DcAppVersionConfig> queryAppVersionConfigList(AppVersionConfigPageInput input) {
		Map<String, Object> map=new HashMap<>();
		map.put("version", StringUtils.isNotEmpty(input.getVersion())?input.getVersion():null);
		map.put("deviceType", StringUtils.isNotEmpty(input.getDeviceType())?input.getDeviceType():null);
		map.put("packageType", StringUtils.isNotEmpty(input.getPackageType())?input.getPackageType():null);
		return dcAppVersionConfigMapper.getVersionStatus(map);
	}
	@Override
	public ResultOutput appVersionConfigUpdate(DcAppVersionConfig config,Integer userId) {
		if(config.getId()!=null&&config.getId()>0) {
			config.setUpdateId(userId);
			config.setUpdateTime(new Date());
			dcAppVersionConfigMapper.updateByPrimaryKeySelective(config);
		} 
		else {
			config.setCreateId(userId);
			config.setCreateTime(new Date());
			dcAppVersionConfigMapper.insertSelective(config);
		} 
		return new ResultOutput();
	}

}
