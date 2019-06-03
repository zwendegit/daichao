package com.daichao.service;

import com.daichao.bean.output.ResultOutput;

public interface DcChannelUvLogService {

    ResultOutput StatisticsChannel(String channelCode, String deviceId);
}
