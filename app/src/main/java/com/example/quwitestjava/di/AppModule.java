package com.example.quwitestjava.di;

import com.example.quwitestjava.data.api.ApiService;
import com.example.quwitestjava.helper.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
abstract public class AppModule {

    @Provides
    public static String provideBaseUrl() {
        return Constants.BASE_URL;
    }



    @Provides
    @Singleton
    public static ApiService provideRetrofitInstance(String BASE_URL) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);

    }

}
