package com.zxw.utils;

import com.zxw.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: ZXW
 * Date: 13-6-14
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class UserHolder {
    public static final String USER_SESSION_KEY = "_USER_SESSION_KEY";

    private static ThreadLocal<User> tl = new ThreadLocal<User>();

    public static void setUser(User User){
        tl.set(User);
    }

    public static User getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }

    public static void clear(){
        tl.remove();
    }
}
