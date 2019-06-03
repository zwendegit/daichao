package com.daichao.dao.message;

import com.daichao.bean.message.DcMessageLog;

public interface DcMessageLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcMessageLog record);

    int insertSelective(DcMessageLog record);

    DcMessageLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcMessageLog record);

    int updateByPrimaryKey(DcMessageLog record);
}