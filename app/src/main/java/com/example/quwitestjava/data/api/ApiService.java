package com.example.quwitestjava.data.api;

import com.example.quwitestjava.data.LoginResponse;
import com.example.quwitestjava.data.response.ChannelResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    Observable<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("chat-channels")
    Observable<ChannelResponse> getChannels(@Header("Authorization") String token);


}
