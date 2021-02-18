package com.responses.PowerUps;

import com.google.gson.annotations.SerializedName;
import com.responses.Quiz.Quiz;

import java.io.Serializable;
import java.util.List;

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
