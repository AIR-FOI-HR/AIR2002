package com.responses;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("questionId")
    private String questionId;
    @SerializedName("question_text")
    private String question_text;
    @SerializedName("correct_answer")
    private String correct_answer;
    @SerializedName("incorrect_answer")
    private String incorrect_answer;

    public Question(String questionId, String question_text, String correct_answer, String incorrect_answer) {
        this.questionId = questionId;
        this.question_text = question_text;
        this.correct_answer = correct_answer;
        this.incorrect_answer = incorrect_answer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getIncorrect_answer() {
        return incorrect_answer;
    }
}
