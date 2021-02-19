package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

import java.time.format.DateTimeFormatter;

public class AvaliableQuizListResponse {

    @SerializedName("QuizId")
    private String quizId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Start_Date")
    private DateTimeFormatter startDate;
    @SerializedName("Id_Category")
    private String idCategory;
    @SerializedName("QuestionIds")
    private String questionIds;

    public String getQuizId(){ return quizId; }

    public String getName(){ return name; }

    public DateTimeFormatter getStartDate(){ return startDate; }

    public String getQuestionIds(){ return questionIds; }

}
