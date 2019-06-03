package com.daichao.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.daichao.bean.admin.DcAdminUser;

@Controller
public class BaseController {

	/**
	 * 获取用户信息
	 * @param request
	 * @return
	 */
	public static DcAdminUser getUser(HttpServletRequest request) {
		DcAdminUser user=(DcAdminUser) request.getSession().getAttribute("user");
		return user;
	}
	
}
