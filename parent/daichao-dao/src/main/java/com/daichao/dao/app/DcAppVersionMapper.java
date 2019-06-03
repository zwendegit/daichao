package com.daichao.dao.app;

import com.daichao.bean.app.DcAppVersion;

import java.util.List;
import java.util.Map;

public interface DcAppVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAppVersion record);

    int insertSelective(DcAppVersion record);

    DcAppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAppVersion record);

    int updateByPrimaryKey(DcAppVersion record);

    DcAppVersion getVersion(Map<String, String> map);
    
    List<DcAppVersion> queryAppVersionList(Map<String, Object> map);
}