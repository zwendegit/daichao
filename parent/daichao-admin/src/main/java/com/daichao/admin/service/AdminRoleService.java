package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.admin.AdminRoleInput;
import com.daichao.bean.admin.DcAdminRole;
import com.daichao.bean.output.ResultOutput;

public interface AdminRoleService {

	List<DcAdminRole> queryAdminRoleList(AdminRoleInput input);
	ResultOutput adminRoleUpdate(DcAdminRole role);
}
