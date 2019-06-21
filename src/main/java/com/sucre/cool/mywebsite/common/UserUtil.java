package com.sucre.cool.mywebsite.common;

import com.sucre.cool.mywebsite.info.BaseUser;
import com.sucre.cool.mywebsite.info.UserInfo;

public class UserUtil {
    private static final ThreadLocal<BaseUser> USER = new ThreadLocal<>();

    public static Integer getUserId() {
        return USER.get().getId();
    }

    public static BaseUser getBaseUser() {
        return USER.get();
    }

    public static void setCurrUser(BaseUser o) {
        USER.set(o);
    }

}
