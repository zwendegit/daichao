package com.daichao.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 记录请求参数
 * @author 
 *
 */
@Component
public class ParamHandleInterceptor extends HandlerInterceptorAdapter {
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		return true;
	}
}
