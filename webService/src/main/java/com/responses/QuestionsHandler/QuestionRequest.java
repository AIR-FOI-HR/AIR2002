package com.responses.QuestionsHandler;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.google.gson.annotations.SerializedName;

public class QuestionRequest {

    @SerializedName("NumberOfQuestions")
    private Integer NumberOfQuestions;
    @SerializedName("DifficultyName")
    private String DifficultyName;
    @SerializedName("CategoryName")
    private String CategoryName;

    public QuestionRequest(Integer numberOfQuestions, String difficultyName, String categoryName){
        NumberOfQuestions = numberOfQuestions;
        DifficultyName = difficultyName;
        CategoryName = categoryName;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        NumberOfQuestions = numberOfQuestions;
    }

    public void setDifficultyName(String difficultyName) {
        DifficultyName = difficultyName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
