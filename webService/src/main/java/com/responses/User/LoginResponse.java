package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("text")
    private String text;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("score")
    private String score;
    @SerializedName("life")
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
