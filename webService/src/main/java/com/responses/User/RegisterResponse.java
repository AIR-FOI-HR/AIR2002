package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

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
