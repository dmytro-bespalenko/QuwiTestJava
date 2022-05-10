package com.example.quwitestjava.repository;

import com.example.quwitestjava.data.LoginResponse;
import com.example.quwitestjava.data.response.ChannelResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AccountsRepository {

    private final AccountsDataSource dataSource;

    @Inject
    public AccountsRepository(AccountsDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Observable<LoginResponse> login(String email, String password) {
        return dataSource.login(email, password);
    }


    public Observable<ChannelResponse>  getChannels(String token) {

     return  dataSource.getChannels(token);
    }
}
