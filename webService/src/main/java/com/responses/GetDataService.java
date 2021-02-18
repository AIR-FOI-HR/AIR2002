package com.responses;

import com.responses.PowerUps.PowerUpRequest;
import com.responses.PowerUps.PowerUpResponse;
import com.responses.PowerUps.SetUserPowerupStatusRequest;
import com.responses.PowerUps.SetUserPowerupStatusResponse;
import com.responses.QuestionsHandler.Question;
import com.responses.QuestionsHandler.QuestionRequest;
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

    //Kvizovi

<<<<<<< HEAD
    @POST("quizs/CreateQuiz") //ne valja
    Call<CreateQuizResponse> CreateQuiz(@Body CreateQuizRequest createQuizRequest);

    @POST("quizs/GetAvailableQuizes/{categoryId}")
    Call<List<Quiz>> GetAvailableQuizes(@Path("CategoryID") int categoryId);
=======
    @POST("quizs/CreateQuiz")
    Call<CreateQuizResponse> CreateQuiz(@Body CreateQuizRequest createQuizRequest);

    @POST("quizs/GetAvaliableQuizes")
    Call<GetAvaliableQuizesResponse> GetAvaliableQuizes(@Body GetAvaliableQuizesRequest getAvaliableQuizesRequest);
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf

    @POST("quizs/SetUserToQuiz")
    Call<SetUserToQuizResponse> SetUserToQuiz(@Body SetUserToQuizRequest setUserToQuizRequest);

<<<<<<< HEAD
    @POST("quizs/GetUsersOnQui/{QuizId}")
    Call<GetUsersOnQuizResponse> getUsersOnQuiz(@Path("QuizId") int quizId);


    //Powerupovi
    @POST("powerups/GetUserPowerups")
    Call<PowerUpResponse> GetPowerUps(@Body PowerUpRequest powerUpRequest);

    @POST("powerups/SetUserPowerupStatus")
    Call<SetUserPowerupStatusResponse> SetPowerUps(@Body SetUserPowerupStatusRequest setUserPowerupStatusRequest);

=======
    @POST("quizs/GetUsersOnQuiz")
    Call<GetUsersOnQuizResponse> getUsersOnQuiz(@Path("QuizId") int quizId);


>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
}
