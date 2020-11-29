package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("text")
    private String text;

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}
