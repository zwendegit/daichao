package com.daichao.dao.advert;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.advert.DcAdvert;

public interface DcAdvertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAdvert record);

    int insertSelective(DcAdvert record);

    DcAdvert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAdvert record);

    int updateByPrimaryKey(DcAdvert record);
    
    List<DcAdvert> queryAdvertList(Map<String, Object> map);
    
    List<DcAdvert> queryAdvertByStatusAndLocation(@Param("location") Integer location);
    
    List<DcAdvert> queryAdvertByProductId(@Param("productId") Integer productId);
}