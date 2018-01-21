package com.example.application.testretrofitrestapi.interfaces;

import com.example.application.testretrofitrestapi.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {

    @GET("users")
    Call<List<User>> getUsers();
}
