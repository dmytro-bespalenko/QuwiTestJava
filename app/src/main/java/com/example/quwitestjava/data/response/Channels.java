package com.example.quwitestjava.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Channels {

    @SerializedName("message_last")
    public MessageLast messageLast;

    public ArrayList<Integer> idUsers;

    @SerializedName("pin_to_top")
    public boolean pinToTop;

    public void setMessageLast(MessageLast messageLast) {
        this.messageLast = messageLast;
    }

    public ArrayList<Integer> getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(ArrayList<Integer> id_users) {
        this.idUsers = id_users;
    }

    public boolean isPinToTop() {
        return pinToTop;
    }

    public void setPinToTop(boolean pinToTop) {
        this.pinToTop = pinToTop;
    }


    public Channels(MessageLast last) {
        this.messageLast = last;
    }


    public MessageLast getMessageLast() {
        return messageLast;
    }
}
