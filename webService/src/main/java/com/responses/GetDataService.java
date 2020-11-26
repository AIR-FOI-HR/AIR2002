package com.responses;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;

public interface GetDataService {
    @GET("Categories")
    Call<List<Category>> getCategories();

    @GET("questions")
    Call<List<Question>> getQuestions();

    @GET("difficulties")
    Call<List<Difficulty>> getDifficulty();

}
