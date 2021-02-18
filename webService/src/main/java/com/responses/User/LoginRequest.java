package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("Username")
    private String username;
    @SerializedName("Password")
    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
