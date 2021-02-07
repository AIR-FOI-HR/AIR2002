package com.responses;

import com.google.gson.annotations.SerializedName;

public class Difficulty {
    @SerializedName("DifficultyId")
    private String difficultyId;
    @SerializedName("Name")
    private String name;

    public Difficulty(String difficultyId, String name) {
        this.difficultyId = difficultyId;
        this.name = name;
    }

    public String getDifficultyId() {
        return difficultyId;
    }

    public String getName() {
        return name;
    }
}
