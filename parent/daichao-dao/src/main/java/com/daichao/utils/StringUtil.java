package com.daichao.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


/**
 * Created by hook on 2017/2/17.
 * <p>
 * 字符串工具类
 */
public class StringUtil {
    /**
     * list转为逗号隔开的字符串
     *
     * @param list 数据列表
     * @return 字符串格式数据
     */
    public static <T extends Serializable> String list2String(List<T> list) {
        StringBuffer sb = new StringBuffer();
        boolean isFirst = true;
        for (T o : list) {
            if (!isFirst) {
                sb.append(",");
            }
            sb.append(o);
            isFirst = false;
        }

        return sb.toString();
    }

    public static String bigDecimal2String(BigDecimal amount, Integer newScale) {
        return String.valueOf(amount.setScale(newScale, BigDecimal.ROUND_DOWN));
    }

    public static Map<String, Object> getPhoneOrUserId(String account) {
        Map<String, Object> map = new HashMap<>();
        if (null == account || "".equals(account)) {
            return map;
        }
        if (StringUtil.isPhone(account)) {
            map.put("phone", account);
        } else {
            try {
                map.put("account", Integer.valueOf(account));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    // 字符串是否为空
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    // 是否所有的字符串为空
    public static boolean isAllBlank(CharSequence... css) {
        for (CharSequence cs : css) {
            if (!isBlank(cs)) {
                return false;
            }
        }

        return true;
    }

    // 是否有字符串为空
    public static boolean isHaveBlank(CharSequence... css) {
        for (CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }

        return false;
    }

    // 字符串是否是数字
    public static boolean isNumeric(String text) {
        if (text == null) {
            return false;
        } else {
            int sz = text.length();

            for (int i = 0; i < sz; ++i) {
                if (!Character.isDigit(text.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    // 字符串是否是手机号
    public static boolean isPhone(String text) {
        return !(isBlank(text) || (!isNumeric(text))) && text.length() == 11;
    }

    public static String hidePhone(String phone) {
        if (!isPhone(phone)) {
            return null;
        }

        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }

    public static List<Long> getIds(String ids) {
        List<Long> idInts = new ArrayList<>();

        if (isBlank(ids)) {
            return idInts;
        }

        String[] idStrs = ids.split(",");
        for (String idStr : idStrs) {
            if (isNumeric(idStr)) {
                try {
                    idInts.add(Long.valueOf(idStr));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return idInts;
    }

    // 产生一个随机字符串
    public static String getRandomString(int length) {
        return getRandomString(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
    }

    public static String getRandomString(int length, String base) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String getRandomNumber(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }

    public static String getVerifyCode(int num) {
        String str = "";
        Random r = new Random();
        for (int i = 0; i < num; i++) {
            int itmp = r.nextInt(9);
            str += String.valueOf(itmp);
        }
        return str;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static List<String> getParams(String params) {
        List<String> str = new ArrayList<>();

        if (isBlank(params)) {
            return str;
        }

        String[] param = params.split(",");
        for (String pa : param) {
            if (!isBlank(pa)) {
                str.add(pa);
            }
        }
        return str;
    }
}