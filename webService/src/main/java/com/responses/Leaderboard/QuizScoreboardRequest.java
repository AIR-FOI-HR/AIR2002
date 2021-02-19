package com.responses.Leaderboard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuizScoreboardRequest implements Serializable {

    @SerializedName("quizid")
    private int Quizid;


    public QuizScoreboardRequest(int quizid) {
        Quizid = quizid;
    }

    public void setQuizid(int quizid) {
        Quizid = quizid;
    }
}
