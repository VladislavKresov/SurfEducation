package com.example.surf_education.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemesResponse {     //POJO class - нужен для обработки JSON ответа сервера

    @SerializedName("id")   // Имя переменной, которую возвращает сервер
    @Expose
    private int memeId; // Переменная, в которую кладется значение

    @SerializedName("title")
    @Expose
    private String memeTitle;

    @SerializedName("description")
    @Expose
    private String memeDescription;

    @SerializedName("isFavorite")
    @Expose
    private boolean memeIsFavorite;

    @SerializedName("createdDate")
    @Expose
    private int memeDate;

    @SerializedName("photoUtl")
    @Expose
    private String memeURL;

    //////////////////////////////Getter's && setter's////////////////////////////

    public int getMemeId() {
        return memeId;
    }

    public void setMemeId(int memeId) {
        this.memeId = memeId;
    }

    public String getMemeTitle() {
        return memeTitle;
    }

    public void setMemeTitle(String memeTitle) {
        this.memeTitle = memeTitle;
    }

    public String getMemeDescription() {
        return memeDescription;
    }

    public void setMemeDescription(String memeDescription) {
        this.memeDescription = memeDescription;
    }

    public boolean isMemeIsFavorite() {
        return memeIsFavorite;
    }

    public void setMemeIsFavorite(boolean memeIsFavorite) {
        this.memeIsFavorite = memeIsFavorite;
    }

    public int getMemeDate() {
        return memeDate;
    }

    public void setMemeDate(int memeDate) {
        this.memeDate = memeDate;
    }

    public String getMemeURL() {
        return memeURL;
    }

    public void setMemeURL(String memeURL) {
        this.memeURL = memeURL;
    }
}
