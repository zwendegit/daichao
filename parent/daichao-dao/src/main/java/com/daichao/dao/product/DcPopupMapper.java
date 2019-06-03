package com.daichao.dao.product;

import com.daichao.bean.product.DcPopup;

import java.util.List;
import java.util.Map;

public interface DcPopupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcPopup record);

    int insertSelective(DcPopup record);

    DcPopup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcPopup record);

    int updateByPrimaryKey(DcPopup record);
    
    List<DcPopup> queryPopupList(Map<String, Object> map);

    DcPopup selectPopup();
}