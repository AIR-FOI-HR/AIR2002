package com.responses.PowerUps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SetUserPowerupStatusRequest implements Serializable {

    @SerializedName("username")
    private int username;
    @SerializedName("powerupId")
    private String powerupId;
    @SerializedName("Amount")
    private String amount;


    public SetUserPowerupStatusRequest(int username, String powerupId, String amount) {
        this.username = username;
        this.powerupId = powerupId;
        this.amount = amount;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public void setPowerupId(String powerupId) {
        this.powerupId = powerupId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
