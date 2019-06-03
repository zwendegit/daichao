package com.daichao.auth;

import com.daichao.bean.output.ResultOutput;
import com.daichao.constant.ErrMsg;
import com.daichao.utils.StringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;


/**
 * Created by hook on 2017/2/17.
 * <p>
 * 根据方法的注解对利用session对一些方法做出拦截
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            // required user access token
            if (require(method, RequireUser.class)) {
                // session中
                String userId = String.valueOf(request.getSession().getAttribute("userId"));
                if (StringUtil.isBlank(userId) || !StringUtil.isNumeric(userId)) {
                    errorResp(response, ErrMsg.ERR_NOT_SIGN_IN_CODE, ErrMsg.ERR_NOT_SIGN_IN_MSG);
                    return false;
                }
            }

        }

        return super.preHandle(request, response, handler);
    }

    private void errorResp(HttpServletResponse resp, String errorCode, String errorMsg) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(new ResultOutput(errorCode, errorMsg));
    }

    private boolean require(HandlerMethod method, Class<? extends Annotation> annotationClass) {
        return method.getMethod().isAnnotationPresent(annotationClass)
                || method.getBean().getClass().isAnnotationPresent(annotationClass);
    }
}