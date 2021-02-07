package com.responses;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("CategoryId")
    private String categoryId;
    @SerializedName("Name")
    private String name;

    public Category(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getId() {
        return categoryId;
    }

    public void setId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }
}
