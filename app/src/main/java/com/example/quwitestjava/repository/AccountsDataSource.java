package com.example.quwitestjava.repository;

import com.example.quwitestjava.data.LoginResponse;
import com.example.quwitestjava.data.api.ApiService;
import com.example.quwitestjava.data.response.channels.ChannelResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;

public class AccountsDataSource {

    private final ApiService service;

    @Inject
    public AccountsDataSource(ApiService service) {
        this.service = service;
    }

    public Call<LoginResponse> login(String email, String password) {
        return service.login(email, password);
    }


    public Observable<ChannelResponse> getChannels(String token) {
        return service.getChannels("Bearer " + token);

    }
}
