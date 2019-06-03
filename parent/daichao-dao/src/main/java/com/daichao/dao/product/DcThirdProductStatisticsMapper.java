package com.daichao.dao.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.product.DcThirdProductStatistics;

public interface DcThirdProductStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcThirdProductStatistics record);

    int insertSelective(DcThirdProductStatistics record);

    DcThirdProductStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcThirdProductStatistics record);

    int updateByPrimaryKey(DcThirdProductStatistics record);
    
    List<DcThirdProductStatistics> queryProductStatisticsList(Map<String, Object> map);

    DcThirdProductStatistics selectRecordToday(@Param("productId") Integer productId);
}