package com.daichao.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.admin.DcAdminRoleMenu;

public interface DcAdminRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAdminRoleMenu record);

    int insertSelective(DcAdminRoleMenu record);

    DcAdminRoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAdminRoleMenu record);

    int updateByPrimaryKey(DcAdminRoleMenu record);
    
    List<DcAdminRoleMenu> queryAdminRoleMenuListByRoleId(@Param("roleId") Integer roleId);
    
    int deleteByRoleId(@Param("roleId") Integer roleId);
}