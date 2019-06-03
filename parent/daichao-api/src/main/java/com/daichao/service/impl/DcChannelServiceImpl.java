package com.daichao.service.impl;

import com.daichao.bean.channel.DcChannel;
import com.daichao.dao.channel.DcChannelMapper;
import com.daichao.service.DcChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DcChannelServiceImpl implements DcChannelService {

    @Resource
    private DcChannelMapper dcChannelMapper;

    @Override
    public DcChannel selectByCode(String channel) {
        return dcChannelMapper.selectByCode(channel);
    }
}
