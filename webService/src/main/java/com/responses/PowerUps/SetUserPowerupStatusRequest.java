package com.responses.PowerUps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SetUserPowerupStatusRequest implements Serializable {

    @SerializedName("username")
    private String username;
    @SerializedName("powerupId")
    private int powerupId;
    @SerializedName("Amount")
    private int amount;


    public SetUserPowerupStatusRequest(String username, int powerupId, int amount) {
        this.username = username;
        this.powerupId = powerupId;
        this.amount = amount;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPowerupId(int powerupId) {
        this.powerupId = powerupId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
