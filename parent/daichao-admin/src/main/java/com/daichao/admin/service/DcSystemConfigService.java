package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.system.SystemConfigInput;
import com.daichao.admin.input.system.SystemConfigListInput;
import com.daichao.admin.input.system.SystemConfigPage;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.system.DcSystemConfig;

public interface DcSystemConfigService {

	ResultOutput configEdit(SystemConfigInput config,Integer userId);
	
	List<DcSystemConfig> configPage(SystemConfigPage input);
	
	List<DcSystemConfig> configList(SystemConfigListInput config);
}
