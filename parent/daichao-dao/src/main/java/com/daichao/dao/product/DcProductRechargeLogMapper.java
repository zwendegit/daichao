package com.daichao.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.product.DcProductRechargeLog;

public interface DcProductRechargeLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcProductRechargeLog record);

    int insertSelective(DcProductRechargeLog record);

    DcProductRechargeLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcProductRechargeLog record);

    int updateByPrimaryKey(DcProductRechargeLog record);
    
    List<DcProductRechargeLog> queryProductRechargeLogList(@Param("name") String name);
}