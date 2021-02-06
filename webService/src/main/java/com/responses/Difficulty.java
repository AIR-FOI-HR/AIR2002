package com.responses;

import com.google.gson.annotations.SerializedName;

public class Difficulty {
    @SerializedName("difficultyId")
    private String DifficultyId;
    @SerializedName("name")
    private String Name;

    public Difficulty(String difficultyId, String name) {
        this.DifficultyId = difficultyId;
        this.Name = name;
    }

    public String getDifficultyId() {
        return DifficultyId;
    }

    public String getName() {
        return Name;
    }
}
