package com.daichao.service;

import com.daichao.bean.user.DcRegistStatistics;

public interface DcRegistStatisticsService {

    DcRegistStatistics selectRecord(String channelCode);

    void insertSelective(DcRegistStatistics dcRegistStatistics);

    void updateByPrimaryKeySelective(DcRegistStatistics dcRegistStatistics);
}
