package com.responses;

import com.responses.User.LoginRequest;
import com.responses.User.LoginResponse;
import com.responses.User.RegisterRequest;
import com.responses.User.RegisterResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface GetDataService {
    @GET("Categories")
    Call<List<Category>> getCategories();

    @GET("questions")
    Call<List<Question>> getQuestions();

    @GET("difficulties")
    Call<List<Difficulty>> getDifficulty();

    @POST("users/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("users/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

}
