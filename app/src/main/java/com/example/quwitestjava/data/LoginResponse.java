package com.example.quwitestjava.data;


import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("token")
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
