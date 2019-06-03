package com.daichao.dao.admin;

import java.util.List;
import java.util.Map;

import com.daichao.bean.admin.DcAdminUser;

public interface DcAdminUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcAdminUser record);

    int insertSelective(DcAdminUser record);

    DcAdminUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcAdminUser record);

    int updateByPrimaryKey(DcAdminUser record);
    
    List<DcAdminUser> queryAdminUserList(Map<String, Object> map);
    
    List<DcAdminUser> queryAdminUserByUserNameOrMobile(Map<String, Object> map);
    
    DcAdminUser queryAdminUserByAccessToken(String access_token);
}