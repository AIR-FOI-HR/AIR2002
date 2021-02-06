package com.responses;

import com.responses.QuestionsHandler.Question;
import com.responses.QuestionsHandler.QuestionRequest;
import com.responses.QuestionsHandler.QuestionsResponse;
import com.responses.User.LoginRequest;
import com.responses.User.LoginResponse;
import com.responses.User.RegisterRequest;
import com.responses.User.RegisterResponse;
import com.responses.User.UpdateUserRequest;
import com.responses.User.UpdateUserResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface GetDataService {
    @GET("Categories")
    Call<List<Category>> getCategories();

    @GET("difficulties")
    Call<List<Difficulty>> getDifficulty();


    //Korisnik
    @POST("users/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("users/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("users/UpdateUserStatus")
    Call<UpdateUserResponse> updateUserStatus(@Body UpdateUserRequest updateUserRequest);

    //Pitanja
    @GET("Questions")
    Call<List<Question>> getQuestions();

    @GET("Questions/{id}")
    Call<Question> getQuestions(@Path("id") int id);

    //@PUT("Questions/{id}")
    //Call<> PutCategory(@Path("id") int id, Question question);

    //@DELETE("Questions/{id}")
    //Call<> DeleteCategory(@Path("id") int id);

    @POST("Questions/GetQuestions")
    Call<QuestionsResponse> GetQuestionsByCategoryAndDifficulty(@Body QuestionRequest questionRequest);

}
