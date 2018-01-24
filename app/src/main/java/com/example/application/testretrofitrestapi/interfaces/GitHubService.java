package com.example.application.testretrofitrestapi.interfaces;

import com.example.application.testretrofitrestapi.models.Post;
import com.example.application.testretrofitrestapi.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("posts")
    Call<List<Post>> getUsersPost(@Query("userId") String userId);
}
