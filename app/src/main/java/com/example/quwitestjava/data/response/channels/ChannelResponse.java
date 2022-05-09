package com.example.quwitestjava.data.response.channels;

import java.util.List;

public class ChannelResponse {
    List<Channels> channels;

    public ChannelResponse(List<Channels> channels) {
        this.channels = channels;
    }

    public List<Channels> getChannels() {
        return channels;
    }
}
