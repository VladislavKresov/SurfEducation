package com.example.surf_education.data;

import android.content.SharedPreferences;

public class SharedPrefUtil {

    private boolean isSign;
    private static SharedPrefUtil instance;
    private static SharedPreferences sSharedPreferences;

    private SharedPrefUtil(SharedPreferences sharedPreferences){
        this.sSharedPreferences = sharedPreferences;
    }

    public static void setInstance(SharedPreferences user){
        instance = new SharedPrefUtil(user);
    }

    public static SharedPrefUtil getInstance(){
        if(instance == null) {
            return null;
        }
        return instance;
    }

    public static SharedPreferences getUserPreferences(){
        if(instance != null)
            return sSharedPreferences;
        return null;
    }

    public static void pushData(String key, String value){
        if(instance !=null){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, int value){
        if(instance !=null){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, boolean value){
        if(instance !=null){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, Long value){
        if(instance !=null){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putLong(key, value);
            editor.apply();
        }
    }

    public static void pushData(String key, float value){
        if(instance !=null){
            SharedPreferences.Editor editor = sSharedPreferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    public static String pullData(String key){

        if(sSharedPreferences.contains(key)) {
            return sSharedPreferences.getString(key, "");
        }
        return null;
    }
}
