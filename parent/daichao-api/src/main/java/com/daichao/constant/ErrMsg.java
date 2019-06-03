package com.daichao.constant;

import java.io.Serializable;

public class ErrMsg implements Serializable {

    public static final String ERR_NOT_SIGN_IN_CODE="300001";

    public static final String ERR_NOT_SIGN_IN_MSG="请先登陆";

    public static final String ERR_VERIFY_IN_VALID_TIME_CODE="300002";

    public static final String ERR_VERIFY_IN_VALID_TIME_MSG="验证码发送过于频繁，请稍后发送";

    public static final String ERR_BAD_VERIFICATION_CODE="300003";

    public static final String ERR_BAD_VERIFICATION_MSG="短信验证码错误";

    public static final String ERR_BAD_HAD_REGIST_CODE="300004";

    public static final String ERR_BAD_HAD_REGIST_MSG="您已注册，请到app登陆";

    public static final String ERR_USER_NOT_EXIST_CODE="300005";

    public static final String ERR_USER_NOT_EXIST_MSG="用户不存在，请先注册";

    public static final String ERR_PASSWORD_CODE="300006";

    public static final String ERR_PASSWORD_MSG="密码错误，请重新输入";

    public static final String ERR_PASSWORD_UNLIKE_CODE="300007";

    public static final String ERR_PASSWORD_UNLIKE_MSG="新密码和确认新密码输入不一致，请重新输入";

    public static final String ERR_VALIDATE_CODE_CODE="300008";

    public static final String ERR_VALIDATE_CODE_MSG="图形验证码错误";

    public static final String ERR_PASSWORD_MATCH_CODE="300009";

    public static final String ERR_PASSWORD_MATCH_MSG="密码不规范，请输入6-16位英文数字组合";

    public static final String ERR_PRODUCT_SOLD_OUT_CODE="300010";

    public static final String ERR_PRODUCT_SOLD_OUT_MSG="产品已下架";

    public static final String ERR_SECRET_CODE="300011";

    public static final String ERR_SECRET_MSG="验签失败";

    public static final String ERR_PHONE_CODE="300012";

    public static final String ERR_PHONE_MSG="手机号码格式不正确";
}
