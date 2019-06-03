package com.daichao.constant;

/**
 * Created by hook on 2017/8/19.
 * <p>
 * 项目全局变量
 */
public class Global {
    // 默认锁的时间和重新尝试次数
    public static final Integer DEF_LOCK_TIME = 600;  // 锁10分钟
    public static final Integer DEF_LOCK_TRY_TIMES = 3; // 重试3次
    public static final Integer DEF_LOCK_SLEEP_TIME = 2000; // 如果被锁了就过2秒再尝试

    // 默认渠道
    public static final String DEF_CHANNEL = "local";
    // 其他渠道
    public static final String OTHER_CHANNEL = "other";

    // 该项目盐的长度
    public static final Integer SALT_LEN = 16;
    // 管理员默认密码
    public static final String DEF_ADMIN_PWD = "111111111";

    // 分页默认开始页码和每页条数
    public static final String PAGE_NUM = "1";
    public static final String PAGE_SIZE = "10";


    // 账号密码错误次数和时间（如果连续错误5次，则1小时内无法登录，防止穷举）
    public static final Integer ERROR_USER_PASSWORD_MAX_TIMES = 5;
    public static final Integer ERROR_USER_PASSWORD_EXPIRE_TIME = 3600;

    // 默认缓存时间、默认消息保存时间
    public static final Integer CACHE_TIME = 3600;
    public static final Integer MESSAGE_EXPIRE_TIME = 30; //30天

    // 验证码长度、有效期(6位，5分钟)
    public static final String KEY_VERIFICATION_CODE_LEN = "verification_code_len";
    public static final String KEY_VERIFICATION_CODE_VALID_PERIOD = "verification_code_valid_period";
    public static final Integer VERIFICATION_CODE_LEN = 6;
    public static final Integer VERIFICATION_CODE_VALID_PERIOD = 300;
    public static final Integer VERIFICATION_CODE_EXPIRE = 60;

    // OSS
    public static final Integer OSS_EXPIRE_TIME = 30; // 30S
    public static final Long OSS_MAX_SIZE = 1024 * 1024 * 3L; // 限制最大上传 3M

    //短信内容
    public static final String MSG_CONTENT = "";

    //图形验证码
    public static final String VALIDATE_DATE = "rand";

}