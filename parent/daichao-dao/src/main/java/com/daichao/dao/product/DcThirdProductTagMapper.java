package com.daichao.dao.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.product.DcThirdProductTag;

public interface DcThirdProductTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcThirdProductTag record);

    int insertSelective(DcThirdProductTag record);

    DcThirdProductTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcThirdProductTag record);

    int updateByPrimaryKey(DcThirdProductTag record);
    
    List<DcThirdProductTag> queryProductTagList(Map<String, Object> map);
    
    void deleteByTagId(@Param("tagId") Integer tagId);
    
    List<DcThirdProductTag> queryProductTagListByProductId(@Param("productId") Integer productId);
    
    void updateStatusByTagIdAndProductId(Map<String, Object> map);
}