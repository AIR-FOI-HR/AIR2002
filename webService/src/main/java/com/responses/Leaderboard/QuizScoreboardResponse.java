package com.responses.Leaderboard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuizScoreboardResponse implements Serializable {

    @SerializedName("Status")
    private int Status;
    @SerializedName("Text")
    private String Text;
    @SerializedName("Scoreboard")
    private List<com.responses.Leaderboard.Scoreboard> Scoreboard;

    public int getStatus() {
        return Status;
    }

    public String getText() {
        return Text;
    }

    public List<com.responses.Leaderboard.Scoreboard> getScoreboard() {
        return Scoreboard;
    }
}
