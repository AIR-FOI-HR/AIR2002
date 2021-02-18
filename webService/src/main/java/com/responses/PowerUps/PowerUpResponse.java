package com.responses.PowerUps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PowerUpResponse implements Serializable {

    @SerializedName("Status")
    private int Status;
    @SerializedName("Text")
    private String Text;
    @SerializedName("UserPowerups")
    private List<PowerUp> UserPowerups;

    public int getStatus() {
        return Status;
    }

    public String getText() {
        return Text;
    }

    public List<PowerUp> getUserPowerups() {
        return UserPowerups;
    }
}
