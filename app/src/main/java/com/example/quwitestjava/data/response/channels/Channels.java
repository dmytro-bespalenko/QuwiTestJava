package com.example.quwitestjava.data.response.channels;

import java.util.ArrayList;
import java.util.Objects;

public class Channels {

    public MessageLast message_last;


    @Override
    public int hashCode() {
        return Objects.hash(message_last, id_users, pin_to_top);
    }

    public ArrayList<Integer> id_users;
    public boolean pin_to_top;

    public Channels(MessageLast last) {
        this.message_last = last;
    }


    public MessageLast getMessage_last() {
        return message_last;
    }
}
