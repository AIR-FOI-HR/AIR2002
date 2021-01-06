package com.responses.QuestionsHandler;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionsResponse {

    @SerializedName("Status")
    private String Status;
    @SerializedName("Text")
    private String Text;
    @SerializedName("Questions")
    private List<QuestionsListResponse> Questions;


    public String getStatus() {
        return Status;
    }

    public String getText() {
        return Text;
    }

    public List<QuestionsListResponse> getQuestions() {
        return Questions;
    }
}
