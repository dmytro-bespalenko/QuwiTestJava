package com.example.quwitestjava.data;


import com.example.quwitestjava.data.response.UserResponse;
import com.google.gson.annotations.SerializedName;


public class LoginResponse {

    @SerializedName("token")
    private String token;

    private UserResponse user;

    public LoginResponse(UserResponse user) {
        this.user = user;
    }


    public String getToken() {
        return token;
    }
}
