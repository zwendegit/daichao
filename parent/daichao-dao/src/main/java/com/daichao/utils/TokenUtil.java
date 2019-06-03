package com.daichao.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenUtil {

	public synchronized static String getToken(String key) {
		try {
			return MD5.EncoderByMd5(key+new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
