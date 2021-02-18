package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class CreateQuizRequest {

<<<<<<< HEAD
    @SerializedName("categoryId")
    private int categoryId;

    @SerializedName("name")
    private String name;


    public CreateQuizRequest(int categoryId, String name){
=======
    @SerializedName("CategoryId")
    private String categoryId;

    @SerializedName("Name")
    private String name;


    public CreateQuizRequest(String categoryId, String name){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
        this.categoryId = categoryId;
        this.name = name;
    }

<<<<<<< HEAD
    public void setCategoryId(int categoryId){
=======
    public void setCategoryId(String categoryId){
>>>>>>> 2d99536cd75aea94beabc182528de442bb91a6bf
        this.categoryId = categoryId;
    }

    public void setName(String name){
        this.name = name;
    }
}
