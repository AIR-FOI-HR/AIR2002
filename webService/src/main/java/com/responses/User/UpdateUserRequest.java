package com.responses.User;

import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest {

    @SerializedName("Username")
    private String username;
    @SerializedName("Life")
    private Integer life;
    @SerializedName("Score")
    private Integer score;

    public UpdateUserRequest(String username, Integer life, Integer score) {
        this.username = username;
        this.life = life;
        this.score = score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLives(Integer life) {
        this.life = life;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
