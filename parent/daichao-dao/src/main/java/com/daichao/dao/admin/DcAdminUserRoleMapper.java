package com.daichao.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.daichao.bean.admin.DcAdminUserRole;

public interface DcAdminUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAdminUserRole record);

    int insertSelective(DcAdminUserRole record);

    DcAdminUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAdminUserRole record);

    int updateByPrimaryKey(DcAdminUserRole record);
    
    void deleteByUserId(@Param("userId") Integer userId);
    
    List<Integer> queryAdminUserByUserId(@Param("userId") Integer userId);
}