package com.example.quwitestjava.model;

import com.example.quwitestjava.data.response.Channels;
import com.example.quwitestjava.data.response.MessageLast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelsModel {

    public MessageLast messageLast;

    public ArrayList<Integer> idUsers;

    public boolean pinToTop;

    public ChannelsModel(MessageLast messageLast, ArrayList<Integer> idUsers, boolean pinToTop) {
        this.messageLast = messageLast;
        this.idUsers = idUsers;
        this.pinToTop = pinToTop;
    }

    public MessageLast getMessageLast() {
        return messageLast;
    }

    public void setMessageLast(MessageLast messageLast) {
        this.messageLast = messageLast;
    }

    public ArrayList<Integer> getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(ArrayList<Integer> idUsers) {
        this.idUsers = idUsers;
    }

    public boolean isPinToTop() {
        return pinToTop;
    }

    public void setPinToTop(boolean pinToTop) {
        this.pinToTop = pinToTop;
    }


    public static List<ChannelsModel> mapToLastChat(List<Channels> channelsList) {
        return channelsList.stream().map(channels ->
                new ChannelsModel(channels.getMessageLast(), channels.getIdUsers(), channels.isPinToTop())).collect(Collectors.toList());

    }

}
