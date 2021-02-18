package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class SetUserToQuizRequest {

    @SerializedName("quizid")
    private int quizId;
    @SerializedName("username")
    private String username;
    @SerializedName("score")
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
