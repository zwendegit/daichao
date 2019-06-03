package com.daichao.dao.app;

import java.util.List;
import java.util.Map;

import com.daichao.bean.app.DcAppVersionConfig;

public interface DcAppVersionConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAppVersionConfig record);

    int insertSelective(DcAppVersionConfig record);

    DcAppVersionConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAppVersionConfig record);

    int updateByPrimaryKey(DcAppVersionConfig record);
    
    List<DcAppVersionConfig> getVersionStatus(Map<String, Object> map);
}