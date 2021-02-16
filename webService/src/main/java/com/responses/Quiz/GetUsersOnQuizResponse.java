package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GetUsersOnQuizResponse {

    @SerializedName("Status")
    private String status;
    @SerializedName("Text")
    private String text;
    @SerializedName("Usersnames")
    private ArrayList<String> usernames;

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getUsernames(){
        return usernames;
    }
}
