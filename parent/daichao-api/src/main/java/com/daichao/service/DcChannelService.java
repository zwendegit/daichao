package com.daichao.service;

import com.daichao.bean.channel.DcChannel;

public interface DcChannelService {

    DcChannel selectByCode(String channel);
}
