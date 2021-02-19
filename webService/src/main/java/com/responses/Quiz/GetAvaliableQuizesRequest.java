package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class GetAvaliableQuizesRequest {

    @SerializedName("CategoryId")
    private Integer CategoryId;

    public GetAvaliableQuizesRequest(Integer categoryId){
        this.CategoryId = categoryId;
    }

    public void setCategoryId(Integer categoryId){
        this.CategoryId = categoryId;
    }
}
