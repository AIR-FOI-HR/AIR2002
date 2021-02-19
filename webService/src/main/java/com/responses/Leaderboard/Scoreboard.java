package com.responses.Leaderboard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Scoreboard implements Serializable {

    @SerializedName("Username")
    private String Username;
    @SerializedName("Score")
    private int Score;

    public String getUsername() {
        return Username;
    }

    public int getScore() {
        return Score;
    }
}
