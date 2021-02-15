package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class Quiz implements Serializable{

    @SerializedName("QuizId")
    private int quizID;
    @SerializedName("Name")
    private String name;
    @SerializedName("Start_Date")
    private DateTimeFormatter startDate;
    @SerializedName("Id_Category")
    private int idCategory;
    @SerializedName("QuestionIds")
    private String questionsIds;

    public Quiz(int quizID, String name, DateTimeFormatter startDate, int idCategory, String questionsIds){
        this.quizID = quizID;
        this.name = name;
        this.startDate = startDate;
        this.idCategory = idCategory;
        this.questionsIds = questionsIds;
    }

    public int getQuizID(){
        return quizID;
    }

    public String getName(){
        return name;
    }

    public DateTimeFormatter getStartDate(){
        return startDate;
    }

    public int getIdCategory(){
        return idCategory;
    }

    public String getQuestionsIds(){
        return questionsIds;
    }

}
