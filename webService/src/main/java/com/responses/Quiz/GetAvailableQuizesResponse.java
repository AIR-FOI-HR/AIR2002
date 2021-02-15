package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAvailableQuizesResponse {

    @SerializedName("Status")
    private String status;
    @SerializedName("Text")
    private String text;
    @SerializedName("QuizList")
    private List<AvailableQuizListResponse> quizList;

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public List<AvailableQuizListResponse> getQuizList() {
        return quizList;
    }
}
