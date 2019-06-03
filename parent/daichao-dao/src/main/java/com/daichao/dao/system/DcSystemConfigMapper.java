package com.daichao.dao.system;

import java.util.List;
import java.util.Map;

import com.daichao.bean.system.DcSystemConfig;

public interface DcSystemConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcSystemConfig record);

    int insertSelective(DcSystemConfig record);

    DcSystemConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcSystemConfig record);

    int updateByPrimaryKey(DcSystemConfig record);
    
    List<DcSystemConfig> querySystemConfigList(Map<String, Object> map);
}