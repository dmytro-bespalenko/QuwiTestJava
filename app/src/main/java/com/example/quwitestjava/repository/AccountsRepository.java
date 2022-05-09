package com.example.quwitestjava.repository;

import com.example.quwitestjava.data.LoginResponse;
import com.example.quwitestjava.data.response.channels.ChannelResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;

public class AccountsRepository {

    private final AccountsDataSource dataSource;

    @Inject
    public AccountsRepository(AccountsDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Call<LoginResponse> login(String email, String password) {
        return dataSource.login(email, password);
    }


    public Observable<ChannelResponse>  getChannels(String token) {

     return  dataSource.getChannels(token);
    }
}
