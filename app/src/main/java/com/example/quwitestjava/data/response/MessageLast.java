package com.example.quwitestjava.data.response;

import com.google.gson.annotations.SerializedName;

public class MessageLast {
    public int id;
    @SerializedName("user")
    public UserChat user;
    @SerializedName("dta_create")
    public String dtaCreate;
    @SerializedName("is_read")
    public int isRead;
    public String text;

    public MessageLast(UserChat user) {
        this.user = user;
    }

    public UserChat getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(UserChat user) {
        this.user = user;
    }

    public String getDtaCreate() {
        return dtaCreate;
    }

    public void setDtaCreate(String dtaCreate) {
        this.dtaCreate = dtaCreate;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


