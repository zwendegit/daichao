package com.daichao.admin.interceptor;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.daichao.bean.admin.DcAdminMenu;
import com.daichao.bean.admin.DcAdminUser;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.admin.DcAdminMenuMapper;
import com.daichao.dao.admin.DcAdminUserMapper;


/**
 * 记录请求参数
 * @author 
 *
 */
@Component
public class ParamHandleInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private DcAdminUserMapper dcAdminUserMapper;
	@Autowired
	private DcAdminMenuMapper dcAdminMenuMapper;
	
	private final String urls[]= {"v2/api-docs","admin/adminUserLogin","swagger"};
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url=request.getRequestURL().toString(); 
		String path=request.getServletPath();
		String access_token=request.getParameter("access_token");
		if(StringUtils.isEmpty(url)) {
			returnValue(response,"1");
			return false;
		}
		for (String u : this.urls) {
			if(url.contains(u)) return true;
		}
		
		if(!StringUtils.isEmpty(access_token)) {
				DcAdminUser user= dcAdminUserMapper.queryAdminUserByAccessToken(access_token);
				if(user==null) {
					returnValue(response,"1");
					return false;
				}else {
					request.getSession().setAttribute("user", user);
					if(user.getUsername().equals("admin")) {
						return true;
					}
					//校验是否有权限
					List<DcAdminMenu> menuList=(List<DcAdminMenu>) request.getSession().getAttribute("menuList");
					if(CollectionUtils.isEmpty(menuList)) {
						returnValue(response,"1");
						return false;
					}else {
						for (DcAdminMenu dcAdminMenu : menuList) {
							if(dcAdminMenu.getParentId()==0) {
								continue;
							}
							if(path.contains(dcAdminMenu.getUrl())) {
								return true;
							}
						}
						//过滤非菜单请求
						Integer count=dcAdminMenuMapper.queryCountByUrl(path.replace(".htm", ""));
						if(count==0) {
							return true;
						}
						returnValue(response,"2");
						return false;
					}
				}
		}else {
			returnValue(response,"1");
			return false;
		} 
	}
	
	private void returnValue(HttpServletResponse response,String type)
			throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		ResultOutput output=new ResultOutput();
		if("1".equals(type)) {
			output.setCode("100000");
			output.setResult("未登录");
		}else if("2".equals(type)) {
			output.setCode("100001");
			output.setResult("无权限");
		}
		response.getWriter().write(JSON.toJSONString(output));
	}

}
