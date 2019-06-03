package com.daichao.dao.channel;

import java.util.List;
import java.util.Map;


import com.daichao.bean.channel.DcChannelStatisticsHistory;

public interface DcChannelStatisticsHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcChannelStatisticsHistory record);

    int insertSelective(DcChannelStatisticsHistory record);

    DcChannelStatisticsHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcChannelStatisticsHistory record);

    int updateByPrimaryKey(DcChannelStatisticsHistory record);
    
    List<DcChannelStatisticsHistory> statisticsHistory(Map<String, Object> map);
    
    DcChannelStatisticsHistory channelStatistics(Map<String, Object> map);
}