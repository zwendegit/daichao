package com.daichao.service.impl;

import com.daichao.bean.user.DcRegistStatistics;
import com.daichao.dao.user.DcRegistStatisticsMapper;
import com.daichao.service.DcRegistStatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DcRegistStatisticsServiceImpl implements DcRegistStatisticsService {

    @Resource
    private DcRegistStatisticsMapper dcRegistStatisticsMapper;

    @Override
    public DcRegistStatistics selectRecord(String channelCode) {
        return dcRegistStatisticsMapper.selectRecord(channelCode);
    }

    @Override
    public void insertSelective(DcRegistStatistics dcRegistStatistics) {
        dcRegistStatisticsMapper.insertSelective(dcRegistStatistics);
    }

    @Override
    public void updateByPrimaryKeySelective(DcRegistStatistics dcRegistStatistics) {
        dcRegistStatisticsMapper.updateByPrimaryKeySelective(dcRegistStatistics);
    }
}
