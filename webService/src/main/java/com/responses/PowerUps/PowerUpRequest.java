package com.responses.PowerUps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PowerUpRequest implements Serializable {
    @SerializedName("Username")
    private String Username;


    public PowerUpRequest(String username) {
        Username = username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
