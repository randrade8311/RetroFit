package com.example.retrofit.rest;

import com.example.retrofit.model.User;
import com.example.retrofit.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApiService {

    @GET("/users")
    Call<List<User>> getUsers();

}
