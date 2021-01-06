package com.responses.QuestionsHandler;

import com.google.gson.annotations.SerializedName;

public class QuestionsListResponse {

    @SerializedName("QuestionDifficulty")
    private String QuestionDifficulty;
    @SerializedName("QuestionCategory")
    private String QuestionCategory;
    @SerializedName("QuestionText")
    private String QuestionText;
    @SerializedName("CorrectAnswer")
    private String CorrectAnswer;
    @SerializedName("IncorrectAnswers")
    private String IncorrectAnswers;

    public String getQuestionDifficulty() {
        return QuestionDifficulty;
    }

    public String getQuestionCategory() {
        return QuestionCategory;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public String getIncorrectAnswers() {
        return IncorrectAnswers;
    }
}
