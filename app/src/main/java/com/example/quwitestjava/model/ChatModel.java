package com.example.quwitestjava.model;

import com.example.quwitestjava.data.response.MessageLast;
import com.example.quwitestjava.data.response.UserChat;

public class ChatModel {
    public int id;
    public UserChat user;
    public String dtaCreate;
    public int isRead;
    public String text;

    public ChatModel(int id, UserChat user, String dtaCreate, int isRead, String text) {
        this.id = id;
        this.user = user;
        this.dtaCreate = dtaCreate;
        this.isRead = isRead;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserChat getUser() {
        return user;
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


    public static ChatModel mapToChatModel(MessageLast messageLast) {

        return new ChatModel(messageLast.getId(), messageLast.getUser(), messageLast.getDtaCreate(),
                messageLast.getIsRead(), messageLast.getText());
    }
}
