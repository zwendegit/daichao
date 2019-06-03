package com.daichao.dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.admin.DcAdminRole;

public interface DcAdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAdminRole record);

    int insertSelective(DcAdminRole record);

    DcAdminRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAdminRole record);

    int updateByPrimaryKey(DcAdminRole record);
    
    List<DcAdminRole> queryAdminUserByUserId(@Param("userId") Integer userId);
    
    List<DcAdminRole> queryAdminRoleList(Map<String, Object> map);
}