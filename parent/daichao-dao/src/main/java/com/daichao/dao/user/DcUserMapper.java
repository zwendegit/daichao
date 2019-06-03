package com.daichao.dao.user;

import com.daichao.bean.user.DcUser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DcUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcUser record);

    int insertSelective(DcUser record);

    DcUser selectByPhone(String phone);

    DcUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcUser dcUser);

    DcUser selectByToken(String token);
    
    int selectCountByRegistTime(Map<String, Object> map);
    
    int selectCountByFirstLoginTime(Map<String, Object> map);
    
    int selectCountByRegistFirstLoginTime(Map<String, Object> map);

    int selectChannelRegistToday(@Param("channel") String channel);
    
    List<DcUser> userList(Map<String, Object> map);
}