package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class CreateQuizRequest {

    @SerializedName("CategoryId")
    private Integer categoryId;

    @SerializedName("Name")
    private String name;


    public CreateQuizRequest(Integer categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;
    }

    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }

    public void setName(String name){
        this.name = name;
    }
}
