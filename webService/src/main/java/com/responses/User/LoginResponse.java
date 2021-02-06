package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("Status")
    private String status;
    @SerializedName("Text")
    private String text;
    @SerializedName("Username")
    private String username;
    @SerializedName("Email")
    private String email;
    @SerializedName("Firstname")
    private String firstname;
    @SerializedName("Lastname")
    private String lastname;
    @SerializedName("Score")
    private String score;
    @SerializedName("Life")
    private String  life;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getScore() {
        return score;
    }

    public String getLife() {
        return life;
    }

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}
