package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse {
    @SerializedName("Status")
    private String status;
    @SerializedName("Text")
    private String text;
    @SerializedName("Username")
    private String username;
    @SerializedName("Life")
    private Integer life;
    @SerializedName("Score")
    private Integer score;

    public String getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public Integer getLife() {
        return life;
    }

    public Integer getScore() {
        return score;
    }
}
