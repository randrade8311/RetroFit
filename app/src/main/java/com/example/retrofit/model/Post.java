package com.example.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("userId")
    private int uId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    public Post(int uId, int id, String title, String body) {
        this.uId = uId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
