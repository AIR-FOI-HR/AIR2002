package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUsersOnQuizResponse {

    @SerializedName("Status")
    private String status;
    @SerializedName("Text")
    private String text;
    @SerializedName("Usernames")
    private List<String> usernames;

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public List<String> getUsernames(){
        return usernames;
    }
}
