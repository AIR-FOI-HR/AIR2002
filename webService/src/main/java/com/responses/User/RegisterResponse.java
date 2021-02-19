package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("Status")
    private Integer status;
    @SerializedName("Text")
    private String text;

    public Integer getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}
