package com.example.surf_education.data;

public class UserStorage {

    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_USER_FIRST_NAME = "user_first_name";
    public static final String KEY_USER_LAST_NAME = "user_last_name";
    public static final String KEY_USER_DESCRIPTION = "user_description";

    public static void saveUserData(String key, String data){
        SharedPrefUtil.pushData(key,data);
    }

    public static String getUserData(String key){
        return SharedPrefUtil.pullData(key);
    }

}
