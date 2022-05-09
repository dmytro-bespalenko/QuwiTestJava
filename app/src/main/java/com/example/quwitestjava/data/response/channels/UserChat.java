package com.example.quwitestjava.data.response.channels;

public class UserChat {
    public String name;
    public String avatar_url;

    public UserChat(String name, String avatar_url) {
        this.name = name;
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}


