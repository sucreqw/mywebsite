package com.sucre.cool.mywebsite.common;

import org.springframework.util.DigestUtils;

public class PasswordEncoderUtil {

    public static final String KEY="mywebsite_key_1989-ch";

    public static String encoder(String password){
        return DigestUtils.md5DigestAsHex((password+KEY).getBytes());
    }

    public static boolean eq(String password,String hexPassword){
       return hexPassword.equals(encoder(password));
    }

}
