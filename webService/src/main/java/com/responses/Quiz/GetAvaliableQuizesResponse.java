package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAvaliableQuizesResponse {

    @SerializedName("Status")
    private String status;
    @SerializedName("Text")
    private String text;
    @SerializedName("QuizList")
    private List<Quiz> quizList;

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }
}
