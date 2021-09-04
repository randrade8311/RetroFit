package com.example.retrofit.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void addPost(Post post);

    @Query("SELECT COUNT(*) FROM posts")
    int count();

    @Query("SELECT * FROM posts")
    List<Post> getAll();

    @Query("SELECT * FROM posts WHERE userId is :userId")
    Post getPost(int userId);
}
