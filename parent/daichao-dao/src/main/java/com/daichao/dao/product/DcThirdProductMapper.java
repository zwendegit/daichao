package com.daichao.dao.product;

import com.daichao.bean.product.DcThirdProduct;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DcThirdProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcThirdProduct record);

    int insertSelective(DcThirdProduct record);

    DcThirdProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcThirdProduct record);

    int updateByPrimaryKey(DcThirdProduct record);
    
    List<DcThirdProduct> queryProductList(Map<String, Object> map);
    
    void updateProductAmountAndRechargeAmount(Map<String, Object> map);

    List<DcThirdProduct> selectThirdProduct();
    
    List<DcThirdProduct> queryProductListBySort(@Param("sort") Integer sort);
    
    List<DcThirdProduct> getAdvertProduct(@Param("location") Integer location);
    
    List<DcThirdProduct> getAdvertOtherProduct(@Param("location") Integer location);
}