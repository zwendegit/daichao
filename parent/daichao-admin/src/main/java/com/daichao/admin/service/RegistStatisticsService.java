package com.daichao.admin.service;

import java.util.List;


import com.daichao.admin.input.regist.RegistStatisticsPageInput;
import com.daichao.bean.user.DcRegistStatistics;

public interface RegistStatisticsService {

	List<DcRegistStatistics> queryRegistStatisticsList(RegistStatisticsPageInput input,Integer channel,Integer type);
}
