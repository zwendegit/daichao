package com.daichao.service;

import com.daichao.bean.product.DcProductConfig;

public interface DcProductConfigService {

    DcProductConfig selectByChannelId(Integer channelId);
}
