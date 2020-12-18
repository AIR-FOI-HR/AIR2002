package com.responses.QuestionsHandler;

import com.google.gson.annotations.SerializedName;

public class QuestionRequest {

    @SerializedName("NumberOfQuestions")
    private String NumberOfQuestions;
    @SerializedName("DifficultyName")
    private String DifficultyName;
    @SerializedName("CategoryName")
    private String CategoryName;

    public void setNumberOfQuestions(String numberOfQuestions) {
        NumberOfQuestions = numberOfQuestions;
    }

    public void setDifficultyName(String difficultyName) {
        DifficultyName = difficultyName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
