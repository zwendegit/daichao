package com.daichao.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hook on 2017/8/20.
 * <p>
 * 通用REST前端控制器
 */
public class CommonController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected HttpServletResponse response;

    protected Integer getUserId() {
        try {
            return Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }

        return null;
    }

}
