package com.daichao.dao.user;

import java.util.List;
import java.util.Map;


import com.daichao.bean.user.DcRegistStatistics;

public interface DcRegistStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcRegistStatistics record);

    int insertSelective(DcRegistStatistics record);

    DcRegistStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcRegistStatistics record);

    int updateByPrimaryKey(DcRegistStatistics record);

    DcRegistStatistics selectRecord(String channelCode);
    
    List<DcRegistStatistics> queryRegistStatisticsList(Map<String, Object> map);
    
    DcRegistStatistics queryCountByChannelCodeAndCreatTime(Map<String, Object> map);
    
    void updateRegistStatisticsRegistCount(Map<String, Object> map);
    
}