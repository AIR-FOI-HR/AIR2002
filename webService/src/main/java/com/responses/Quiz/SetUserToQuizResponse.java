package com.responses.Quiz;

import com.google.gson.annotations.SerializedName;

public class SetUserToQuizResponse {

    @SerializedName("Status")
    private String status;
    @SerializedName("Text")
    private String text;

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}
