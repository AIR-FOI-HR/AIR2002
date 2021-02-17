package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class GetAvailableQuizesRequest {

    @SerializedName("CategoryID")
    private String categoryId;

    public GetAvailableQuizesRequest(String categoryId){
        this.categoryId = categoryId;
    }

    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }
}
