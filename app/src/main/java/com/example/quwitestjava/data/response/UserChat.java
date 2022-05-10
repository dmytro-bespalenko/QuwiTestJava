package com.example.quwitestjava.data.response;

import com.google.gson.annotations.SerializedName;

public class UserChat {
    public String name;
    @SerializedName("avatar_url")
    public String avatarUrl;

    public UserChat(String name, String avatarUrl) {
        this.name = name;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}


