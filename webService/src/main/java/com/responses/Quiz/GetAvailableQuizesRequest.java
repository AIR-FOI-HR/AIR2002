package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class GetAvailableQuizesRequest {

    @SerializedName("CategoryID")
    private int categoryId;

    public GetAvailableQuizesRequest(int categoryId){
        this.categoryId = categoryId;
    }

    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }
}
