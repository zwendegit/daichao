package com.daichao.service.impl;

import com.daichao.bean.product.DcProductConfig;
import com.daichao.dao.product.DcProductConfigMapper;
import com.daichao.service.DcProductConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DcProductConfigServiceImpl implements DcProductConfigService {

    @Resource
    private DcProductConfigMapper dcProductConfigMapper;

    @Override
    public DcProductConfig selectByChannelId(Integer channelId) {
        return dcProductConfigMapper.selectByChannelId(channelId);
    }
}
