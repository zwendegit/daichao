package com.daichao.dao.channel;

import com.daichao.bean.channel.DcChannel;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DcChannelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcChannel record);

    int insertSelective(DcChannel record);

    DcChannel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcChannel record);

    int updateByPrimaryKey(DcChannel record);
    
    List<DcChannel> queryChannelList(Map<String, Object> map);

    DcChannel selectByCode(String channel);
    
    List<String> queryChannelCodeList(@Param("status") Integer status);
}