package com.responses.PowerUps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PowerUp implements Serializable {

    @SerializedName("PowerupId")
    private int PowerupId;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Amount")
    private int Amount;

    public int getPowerupId() {
        return PowerupId;
    }

    public String getName() {
        return Name;
    }

    public int getAmount() {
        return Amount;
    }
}
