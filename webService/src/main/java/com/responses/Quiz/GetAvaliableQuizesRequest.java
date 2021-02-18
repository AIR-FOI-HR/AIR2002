package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class GetAvaliableQuizesRequest {

    @SerializedName("categoryId")
    private String CategoryID;

    public GetAvaliableQuizesRequest(String categoryId){
        this.CategoryID = categoryId;
    }

    public void setCategoryId(String categoryId){
        this.CategoryID = categoryId;
    }
}
