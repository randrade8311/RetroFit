package com.example.retrofit.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "posts")
public class Post {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    private int uId;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "body")
    @SerializedName("body")
    private String body;

    public Post(int uId, int id, String title, String body) {
        this.uId = uId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
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
