package com.example.retrofit.rest;

import com.example.retrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApiService {

    @GET("/posts")
    Call<List<Post>> getPosts();
}
