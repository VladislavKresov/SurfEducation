package com.example.surf_education.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("accessToken")
    @Expose
    private String accsessToken;

    @SerializedName("userInfo")
    @Expose
    private UserInfo mUserInfo;

    public String getAccsessToken() {
        return accsessToken;
    }

    public void setAccsessToken(String accsessToken) {
        this.accsessToken = accsessToken;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }
}
