package com.responses.QuestionsHandler;

import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.google.gson.annotations.SerializedName;

public class QuestionsRequest {

    @SerializedName("NumberOfQuestions")
    private Integer NumberOfQuestions;
    @SerializedName("DifficultyName")
    private String DifficultyName;
    @SerializedName("CategoryName")
    private String CategoryName;

    public QuestionsRequest(Integer numberOfQuestions, String difficultyName, String categoryName){
        this.NumberOfQuestions = numberOfQuestions;
        this.DifficultyName = difficultyName;
        this.CategoryName = categoryName;
    }

    public void setNumberOfQuestions(Integer numberOfQuestions) {
        this.NumberOfQuestions = numberOfQuestions;
    }

    public void setDifficultyName(String difficultyName) {
        DifficultyName = difficultyName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
