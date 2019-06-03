package com.daichao.dao.product;

import java.util.List;
import java.util.Map;

import com.daichao.bean.product.DcThirdProductHistory;
import org.apache.ibatis.annotations.Param;

public interface DcThirdProductHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcThirdProductHistory record);

    int insertSelective(DcThirdProductHistory record);

    DcThirdProductHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcThirdProductHistory record);

    int updateByPrimaryKey(DcThirdProductHistory record);
    
    List<DcThirdProductHistory> queryProductHistoryByProductIdAndStatus(Map<String, Object> map);

    int selectByUserIdAndProductId(@Param("userId")Integer userId, @Param("productId") Integer productId);
    
    List<DcThirdProductHistory> selectCountByUserIdAndDay(Map<String, Object> map);
}