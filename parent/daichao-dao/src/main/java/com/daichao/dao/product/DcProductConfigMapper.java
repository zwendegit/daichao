package com.daichao.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.product.DcProductConfig;

public interface DcProductConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcProductConfig record);

    int insertSelective(DcProductConfig record);

    DcProductConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcProductConfig record);

    int updateByPrimaryKey(DcProductConfig record);

    DcProductConfig selectByChannelId(@Param("channelId") Integer channelId);
    
    List<DcProductConfig> queryByChannleId(@Param("channelId") Integer channelId);
}