package com.daichao.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/*
 * 此处使用AES-128-ECB加密模式，key需要为16位。
*/
public class AesUtils {

	private final static String key="4Ct7TyXZa19rgQKK";
	/**
	 * 返回16进制字符串
	 * @param sSrc
	 * @param sKey
	 * @return
	 * @throws Exception
	 */
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
        	sKey=key;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return toHexString1(encrypted);
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
            	sKey=key;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1=hexStringToByteArray(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    
    
    /**
     * 数组转成十六进制字符串
     * @param byte[]
     * @return HexString
     */
    public static String toHexString1(byte[] b){
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < b.length; ++i){
            buffer.append(toHexString1(b[i]));
        }
        return buffer.toString();
    }
    public static String toHexString1(byte b){
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1){
            return "0" + s;
        }else{
            return s;
        }
    }
    
    public static byte[] hexStringToByteArray(String s) {
		//十六进制转byte数组
		int len = s.length();
		byte[] bs = new byte[len/2];
		for(int i = 0;i < len;i+=2) {
		bs[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
		}
		return bs;
    }

    public static void main(String[] args) throws Exception {
        
        // 加密
        String enString = AesUtils.Encrypt("https://zujin.58fangdai.com/h5/register5/index?linksource=zxcx001", "4Ct7TyXZa19rgQKK");
        System.out.println("加密后的字串是：" + enString);

        // 解密
        String DeString = AesUtils.Decrypt("ed33893cdfc4f357a89696824092a2e1ca959f8a8f3cd7acee9d2985fe45749f", "4Ct7TyXZa19rgQKK");
        System.out.println("解密后的字串是：" + DeString);
    }
}
