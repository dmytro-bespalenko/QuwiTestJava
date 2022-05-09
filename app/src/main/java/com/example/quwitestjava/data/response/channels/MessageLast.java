package com.example.quwitestjava.data.response.channels;

import com.google.gson.annotations.SerializedName;

public class MessageLast {
    public int id;
    @SerializedName("user")
    public UserChat user;
    public String dta_create;
    public int is_read;
    public String text;

    public MessageLast(UserChat user) {
        this.user = user;
    }

    public UserChat getUser() {
        return user;
    }
}


