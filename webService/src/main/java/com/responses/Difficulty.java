package com.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Difficulty implements Serializable {
    @SerializedName("difficultyId")
    private String difficultyId;
    @SerializedName("name")
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
