package com.example.micha.retrofitextrapractice;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int id;

    private int UserId;

    private String title;

    @SerializedName("Body")
    private String text;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return UserId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
