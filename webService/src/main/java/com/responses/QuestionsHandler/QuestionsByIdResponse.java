package com.responses.QuestionsHandler;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsByIdResponse {
    @SerializedName("Status")
    private Integer Status;
    @SerializedName("Text")
    private String Text;
    @SerializedName("Question")
    private QuestionsListResponse Question;

    public Integer getStatus() {
        return Status;
    }

    public String getText() {
        return Text;
    }

    public QuestionsListResponse getQuestions() {
        return Question;
    }
}
