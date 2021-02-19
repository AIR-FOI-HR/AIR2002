package com.responses;

import com.responses.Leaderboard.QuizScoreboardRequest;
import com.responses.Leaderboard.QuizScoreboardResponse;
import com.responses.PowerUps.PowerUpRequest;
import com.responses.PowerUps.PowerUpResponse;
import com.responses.PowerUps.SetUserPowerupStatusRequest;
import com.responses.PowerUps.SetUserPowerupStatusResponse;
import com.responses.QuestionsHandler.QuestionByIdRequest;
import com.responses.QuestionsHandler.Question;
import com.responses.QuestionsHandler.QuestionRequest;
import com.responses.QuestionsHandler.QuestionsByIdResponse;
import com.responses.QuestionsHandler.QuestionsResponse;
import com.responses.User.LoginRequest;
import com.responses.User.LoginResponse;
import com.responses.User.RegisterRequest;
import com.responses.User.RegisterResponse;
import com.responses.User.UpdateUserRequest;
import com.responses.User.UpdateUserResponse;
import com.responses.Quiz.*;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

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

    @POST("Questions/GetQuestionById")
    Call<QuestionsByIdResponse> GetQuestionsById(@Body QuestionByIdRequest questionRequest);

    //Kvizovi

    @POST("Quizs/CreateQuiz")
    Call<CreateQuizResponse> CreateQuiz(@Body CreateQuizRequest createQuizRequest);

    @POST("Quizs/GetAvaliableQuizes")
    Call<GetAvaliableQuizesResponse> GetAvaliableQuizes(@Body GetAvaliableQuizesRequest getAvaliableQuizesRequest);

    @POST("Quizs/SetUserToQuiz")
    Call<SetUserToQuizResponse> SetUserToQuiz(@Body SetUserToQuizRequest setUserToQuizRequest);

    @POST("Quizs/GetUsersOnQuiz")
    Call<GetUsersOnQuizResponse> getUsersOnQuiz(@Body GetUsersOnQuizRequest getUsersOnQuizRequest);

    //Powerupovi
    @POST("powerups/GetUserPowerups")
    Call<PowerUpResponse> GetPowerUps(@Body PowerUpRequest powerUpRequest);

    @POST("powerups/SetUserPowerupStatus")
    Call<SetUserPowerupStatusResponse> SetPowerUps(@Body SetUserPowerupStatusRequest setUserPowerupStatusRequest);

    //Scoreboard
    @POST("quizs/GetQuizScoreboard")
    Call<QuizScoreboardResponse> getScoreboard(@Body QuizScoreboardRequest quizScoreboardRequest);

}
