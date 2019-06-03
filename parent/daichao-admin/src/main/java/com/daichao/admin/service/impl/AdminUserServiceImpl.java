package com.daichao.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.input.admin.AdminUserInput;
import com.daichao.admin.input.admin.AdminUserPageInput;
import com.daichao.admin.service.AdminRoleMenuService;
import com.daichao.admin.service.AdminUserService;
import com.daichao.bean.admin.DcAdminMenu;
import com.daichao.bean.admin.DcAdminRole;
import com.daichao.bean.admin.DcAdminUser;
import com.daichao.bean.admin.DcAdminUserRole;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.admin.DcAdminMenuMapper;
import com.daichao.dao.admin.DcAdminRoleMapper;
import com.daichao.dao.admin.DcAdminUserMapper;
import com.daichao.dao.admin.DcAdminUserRoleMapper;
import com.daichao.utils.TokenUtil;
@Service
public class AdminUserServiceImpl implements AdminUserService{

	@Autowired
	private DcAdminUserMapper dcAdminUserMapper;
	@Autowired
	private DcAdminRoleMapper dcAdminRoleMapper;
	@Autowired
	private DcAdminUserRoleMapper dcAdminUserRoleMapper;
	@Autowired
	private AdminRoleMenuService adminRoleMenuService;
	@Autowired
	private DcAdminMenuMapper dcAdminMenuMapper;
	@Override
	public List<DcAdminUser> queryAdminUserList(AdminUserPageInput input) {
		Map<String, Object> map=new HashMap<>();
		map.put("mobile", StringUtils.isNotEmpty(input.getMobile())?input.getMobile():null );
		map.put("userName", StringUtils.isNotEmpty(input.getUserName())?input.getUserName():null);
		map.put("company", StringUtils.isNotEmpty(input.getCompany())?input.getCompany():null);
		map.put("userType", input.getUserType()!=null?input.getUserType():null);
		return dcAdminUserMapper.queryAdminUserList(map);
	}
	
	@Override
	public ResultOutput adminUserSave(DcAdminUser user) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("mobile", user.getMobile());
		List<DcAdminUser> userList=dcAdminUserMapper.queryAdminUserList(map);
		if(user.getId()!=null&&user.getId()>0) {
			if(!CollectionUtils.isEmpty(userList)) {
				for (DcAdminUser dcAdminUser : userList) {
					if(dcAdminUser.getId()!=user.getId()) {
						return new ResultOutput("1", "手机号已存在");
					}
				}
			}
			dcAdminUserMapper.updateByPrimaryKeySelective(user);
		} else {
			if(!CollectionUtils.isEmpty(userList)) {
				return new ResultOutput("1", "手机号已存在");
			}
			user.setCreateTime(new Date());
			dcAdminUserMapper.insertSelective(user);
			return new ResultOutput(user);
		}
		 
		return new ResultOutput();
	}

	@Override
	public List<Integer> adminUserRoleList(Integer userId) {
		return dcAdminUserRoleMapper.queryAdminUserByUserId(userId);
	}

	@Override
	public ResultOutput adminUserRoleUpdate(Integer userId, String roleIds) {
		if(StringUtils.isNotEmpty(roleIds)) {
			String roles[]=roleIds.split(",");
			if(roles!=null&&roles.length>0) {
				dcAdminUserRoleMapper.deleteByUserId(userId);
				for (String str : roles) {
					DcAdminUserRole role=new DcAdminUserRole();
					role.setRoleId(Integer.parseInt(str));
					role.setStatus(1);
					role.setUserId(userId);
					role.setCreateTime(new Date());
					dcAdminUserRoleMapper.insert(role);
				}
			}
		}
		return new ResultOutput();
	}

	@Override
	public List<DcAdminUser> queryAdminUserByUserNameOrMobile(String name, String password) {
		Map<String, Object> map=new HashMap<>();
		map.put("name", name);
		map.put("password", password);
		return dcAdminUserMapper.queryAdminUserByUserNameOrMobile(map);
	}

	@Override
	public ResultOutput adminUserLogin(AdminUserInput input,HttpServletRequest request) {
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isEmpty(input.getUsername()) ||StringUtils.isEmpty(input.getPassword())) {
			return new ResultOutput("1", "用户名和密码不能为空");
		}
        List<DcAdminUser> userlist=this.queryAdminUserByUserNameOrMobile(input.getUsername(), input.getPassword());
		if(CollectionUtils.isEmpty(userlist)) {
			return new ResultOutput("1", "用户名或密码不正确");
		}
		if(userlist.size()>1) {
			return new ResultOutput("1", "系统异常");
		}
		DcAdminUser user=userlist.get(0);
		user.setAccessToken(TokenUtil.getToken(user.getUsername()));
		user.setLastLoginTime(new Date());
		dcAdminUserMapper.updateByPrimaryKeySelective(user);
		
		//查询菜单
		List<DcAdminMenu> menuList= adminRoleMenuService.queryMenuListByUserId(user.getId());
		map.put("menuList", menuList);
		request.getSession().setAttribute("menuList", dcAdminMenuMapper.queryMenuListByUserId(user.getId()));
		request.getSession().setAttribute("user", user);
		user.setPassword("");
		map.put("user", user);
		return new ResultOutput(map);
	}

}
