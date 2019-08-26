package com.example.surf_education.Data;

import android.content.SharedPreferences;

import java.util.Map;

public class User {

    private boolean isSign;
    private static User Instance;
    private static SharedPreferences user;

    private User(SharedPreferences user){
        this.user = user;
    }

    public static void setInstance(SharedPreferences user){
        Instance = new User(user);
    }

    public static User getInstance(){
        if(Instance == null) {
            return null;
        }
        return Instance;
    }

    public static SharedPreferences getUserPreferences(){
        if(Instance != null)
            return user;
        return null;
    }

    public static void pushData(String key, String value){
        if(Instance!=null){
            SharedPreferences.Editor editor = user.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, int value){
        if(Instance!=null){
            SharedPreferences.Editor editor = user.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, boolean value){
        if(Instance!=null){
            SharedPreferences.Editor editor = user.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, Long value){
        if(Instance!=null){
            SharedPreferences.Editor editor = user.edit();
            editor.putLong(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, float value){
        if(Instance!=null){
            SharedPreferences.Editor editor = user.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    public static String pullData(String key){

        if(user.contains(key)) {
            return user.getString(key, "");
        }
        return null;
    }






}
