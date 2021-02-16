package com.responses.QuestionsHandler;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("QuestionId")
    private String QuestionId;
    @SerializedName("Id_Category")
    private String Id_Category;
    @SerializedName("Id_Difficulty")
    private String Id_Difficulty;
    @SerializedName("Question_text")
    private String Question_text;
    @SerializedName("Correct_answer")
    private String Correct_answer;
    @SerializedName("Incorrect_answer")
    private String Incorrect_answer;
    @SerializedName("Id_Question_Type")
    private String Id_Question_Type;

    public Question(String questionId, String id_Category, String id_Difficulty, String question_text, String correct_answer, String incorrect_answer, String id_question_type) {
        QuestionId = questionId;
        Id_Category = id_Category;
        Id_Difficulty = id_Difficulty;
        Question_text = question_text;
        Correct_answer = correct_answer;
        Incorrect_answer = incorrect_answer;
        Id_Question_Type = id_question_type;
    }

    public void setQuestionId(String questionId) {
        QuestionId = questionId;
    }

    public void setId_Category(String id_Category) {
        Id_Category = id_Category;
    }

    public void setId_Difficulty(String id_Difficulty) {
        Id_Difficulty = id_Difficulty;
    }

    public void setQuestion_text(String question_text) {
        Question_text = question_text;
    }

    public void setCorrect_answer(String correct_answer) {
        Correct_answer = correct_answer;
    }

    public void setIncorrect_answer(String incorrect_answer) {
        Incorrect_answer = incorrect_answer;
    }

    public void setId_Question_Type(String id_question_type) {
        Id_Question_Type = id_question_type;
    }
}
