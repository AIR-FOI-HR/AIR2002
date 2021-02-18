package com.responses.PowerUps;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SetUserPowerupStatusResponse implements Serializable {

    @SerializedName("Status")
    private int Status;
    @SerializedName("Text")
    private String Text;


    public int getStatus() {
        return Status;
    }

    public String getText() {
        return Text;
    }

}
