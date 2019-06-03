package com.daichao.dao.channel;

import com.daichao.bean.channel.DcChannelUvLog;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface DcChannelUvLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcChannelUvLog record);

    int insertSelective(DcChannelUvLog record);

    DcChannelUvLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcChannelUvLog record);

    int updateByPrimaryKey(DcChannelUvLog record);
    
    Integer queryCountByCodeAndCreateTime(Map<String, Object> map);

    int selectCountToday(@Param("channelCode") String channelCode,@Param("deviceId") String deviceId);
}