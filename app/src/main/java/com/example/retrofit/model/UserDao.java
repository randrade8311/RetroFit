package com.example.retrofit.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Query("SELECT COUNT(*) FROM users")
    int count();

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE password is :password AND username is :username")
    User getUser(String password, String username);
}
