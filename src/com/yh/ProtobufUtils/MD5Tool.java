package com.yh.ProtobufUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tool {

	public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
       System.out.println("success");
       
        return md5code;
    }
	public static void main(String[] args) {
	
		String accountid="";
		String nonce="";
		String timestamp="";
		String getToken="";
		String md5str= stringToMD5(accountid.concat(nonce).concat(timestamp).concat(getToken));
	}
	
	
}
