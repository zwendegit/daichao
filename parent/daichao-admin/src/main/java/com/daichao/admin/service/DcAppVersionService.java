package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.app.AppVersionConfigPageInput;
import com.daichao.admin.input.app.AppVersionPageInput;
import com.daichao.bean.app.DcAppVersion;
import com.daichao.bean.app.DcAppVersionConfig;
import com.daichao.bean.output.ResultOutput;

public interface DcAppVersionService {

	List<DcAppVersion> queryAppVersionList(AppVersionPageInput input);
	
	ResultOutput appVersionUpdate(DcAppVersion version);
	
	List<DcAppVersionConfig> queryAppVersionConfigList(AppVersionConfigPageInput input);
	
	ResultOutput appVersionConfigUpdate(DcAppVersionConfig config,Integer userId);
}
