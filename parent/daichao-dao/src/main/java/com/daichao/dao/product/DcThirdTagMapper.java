package com.daichao.dao.product;

import com.daichao.bean.product.DcThirdTag;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DcThirdTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcThirdTag record);

    int insertSelective(DcThirdTag record);

    DcThirdTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcThirdTag record);

    int updateByPrimaryKey(DcThirdTag record);
    
    List<DcThirdTag> queryDcThirdTagList(Map<String, Object> map);

    List<DcThirdTag> selectIndexThirdTag();
    
    List<DcThirdTag> selectTagByProductId(@Param("productId") Integer productId);
}