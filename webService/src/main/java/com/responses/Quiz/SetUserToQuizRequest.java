package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class SetUserToQuizRequest {

    @SerializedName("QuizId")
    private int quizId;
    @SerializedName("Username")
    private String username;
    @SerializedName("Score")
    private int score;

    public SetUserToQuizRequest(int quizId, String username, int score){
        this.quizId = quizId;
        this.username = username;
        this.score = score;
    }

    public void setQuizId(int quizId){
        this.quizId = quizId;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setScore(int score){
        this.score = score;
    }

}
