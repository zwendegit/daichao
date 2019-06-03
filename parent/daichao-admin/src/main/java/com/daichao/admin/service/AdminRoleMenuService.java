package com.daichao.admin.service;

import java.util.List;

import com.daichao.bean.admin.DcAdminMenu;
import com.daichao.bean.admin.DcAdminRoleMenu;
import com.daichao.bean.output.ResultOutput;

public interface AdminRoleMenuService {

	List<DcAdminMenu> queryAdminRoleMenuListByRoleId(Integer roleId);
	
	List<DcAdminMenu> adminMenuList();
	
	List<DcAdminMenu> queryMenuListByUserId(Integer userId);
	
	ResultOutput menuSave(DcAdminMenu menu);
	
	ResultOutput adminRoleMenuUpdate(Integer roleId,String ids);
}
