package com.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {
    @SerializedName("categoryId")
    private String categoryId;
    @SerializedName("name")
    private String name;

    public String getId() {
        return categoryId;
    }

    public String getTitle() {
        return name;
    }
}

