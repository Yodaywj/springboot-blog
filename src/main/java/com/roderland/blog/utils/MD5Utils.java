package com.roderland.blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
    @author: Roderland
    @create: 2020-09-04---17:41
*/
public class MD5Utils {
    public static void main(String[] args) {
        System.out.println(code("123456"));
    }


    public static String code(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte[] byteDigest = md.digest();
            int i;
            StringBuffer buffer = new StringBuffer("");
            for (byte b : byteDigest) {
                i = b;
                if (i < 0) i += 256;
                if (i < 16) buffer.append("0");
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString();
            //16位加密
            //return buffer.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
