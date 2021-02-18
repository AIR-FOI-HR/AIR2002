package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class CreateQuizRequest {

    @SerializedName("CategoryId")
    private String categoryId;

    @SerializedName("Name")
    private String name;


    public CreateQuizRequest(String categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;
    }

    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }

    public void setName(String name){
        this.name = name;
    }
}
