package com.responses.QuestionsHandler;

import com.google.gson.annotations.SerializedName;

public class QuestionByIdRequest {
    @SerializedName("QuestionId")
    private Integer QuestionId;

    public QuestionByIdRequest(Integer questionId){
        this.QuestionId = questionId;
    }
}
