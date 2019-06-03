package com.daichao.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.daichao.admin.input.admin.AdminUserInput;
import com.daichao.admin.input.admin.AdminUserPageInput;
import com.daichao.bean.admin.DcAdminUser;
import com.daichao.bean.output.ResultOutput;

public interface AdminUserService {

	List<DcAdminUser> queryAdminUserList(AdminUserPageInput input);
	
	ResultOutput adminUserSave(DcAdminUser user);
	
	List<Integer> adminUserRoleList(Integer userId);
	
	ResultOutput adminUserRoleUpdate(Integer userId,String roleIds);
	
	List<DcAdminUser> queryAdminUserByUserNameOrMobile(String name,String password);
	
	ResultOutput adminUserLogin(AdminUserInput input,HttpServletRequest request);
}
