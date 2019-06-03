package com.daichao.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.admin.DcAdminMenu;

public interface DcAdminMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAdminMenu record);

    int insertSelective(DcAdminMenu record);

    DcAdminMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAdminMenu record);

    int updateByPrimaryKey(DcAdminMenu record);
    
    List<DcAdminMenu> queryAdminMenuList();
    
    List<DcAdminMenu> queryMenuListByUserId(@Param("userId") Integer userId);
    
    Integer queryCountByUrl(@Param("url") String url);
    
    List<DcAdminMenu> queryAdminMenuListByRoleId(@Param("roleId") Integer roleId);
    
    
}