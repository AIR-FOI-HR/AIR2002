package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

import java.time.format.DateTimeFormatter;

public class AvailableQuizListResponse {

    @SerializedName("QuizId")
    private int quizId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Start_Date")
    private DateTimeFormatter startDate;
    @SerializedName("Id_Category")
    private int idCategory;
    @SerializedName("QuestionIds")
    private String questionIds;

    public int getQuizId(){ return quizId; }

    public String getName(){ return name; }

    public DateTimeFormatter getStartDate(){ return startDate; }

    public String getQuestionIds(){ return questionIds; }

}
