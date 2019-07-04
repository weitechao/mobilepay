package com.bracelet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5 {
	
	public static String encryption(String src) {
		 StringBuffer sb = new StringBuffer();
		try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
                 '6', '7' , '8', '9', 'a', 'b', 'c', 'd', 'e','f' };
           
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
		return sb.toString();
    }
	
	 public static String md5(String text, String key) throws Exception {
	        //加密后的字符串
	        String encodeStr=DigestUtils.md5Hex(text + key);
	     
	        return encodeStr;
	        }
	
	public static void main(String[] args) throws Exception {
        // 需要加密的字符串
        String src = "123456";
     System.out.println(md5(src,"92eb189042af75a2a7f852a564285b87"));
    }
	
	
}
