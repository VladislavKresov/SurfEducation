package com.example.surf_education.network.response;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class MemesInfo implements Serializable {


    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("isFavorite")
    private boolean isFavorite;

    @SerializedName("createdDate")
    private long createdDate;

    @SerializedName("photoUtl")
    private String photoUrl;


    public MemesInfo(long id, String title, String description, boolean isFavorite, long createdDate, String photoUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
        this.createdDate = createdDate;
        this.photoUrl = photoUrl;
    }

    public MemesInfo(String title, String description, boolean isFavorite, long createdDate, String photoUrl) {
        this.title = title;
        this.description = description;
        this.isFavorite = isFavorite;
        this.createdDate = createdDate;
        this.photoUrl = photoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
