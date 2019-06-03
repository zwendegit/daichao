package com.daichao.service.impl;

import com.daichao.bean.app.DcAppVersion;
import com.daichao.bean.app.DcAppVersionConfig;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.app.DcAppVersionConfigMapper;
import com.daichao.dao.app.DcAppVersionMapper;
import com.daichao.service.DcAppVersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DcAppVersionServiceImpl implements DcAppVersionService {

    @Resource
    private DcAppVersionMapper dcAppVersionMapper;
    @Autowired
    private DcAppVersionConfigMapper dcAppVersionConfigMapper;

    @Override
    public ResultOutput getVersion(String deviceType, String packageType) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("deviceType",deviceType);
        map.put("packageType",packageType);
        DcAppVersion dcAppVersion = dcAppVersionMapper.getVersion(map);
        return new ResultOutput(dcAppVersion);
    }

	@Override
	public ResultOutput getVersionStatus(String deviceType, String packageType, String version) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("deviceType",deviceType);
        map.put("packageType",packageType);
        map.put("version",version);
        List<DcAppVersionConfig>  dcAppVersionConfigList = dcAppVersionConfigMapper.getVersionStatus(map);
        if(!CollectionUtils.isEmpty(dcAppVersionConfigList)) {
        	return new ResultOutput(dcAppVersionConfigList.get(0));
        }
        DcAppVersionConfig dcAppVersionConfig=new DcAppVersionConfig();
        dcAppVersionConfig.setStatus(0);
        return new ResultOutput(dcAppVersionConfig);
	}
}
