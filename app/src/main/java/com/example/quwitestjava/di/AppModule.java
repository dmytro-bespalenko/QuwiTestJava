package com.example.quwitestjava.di;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.quwitestjava.BuildConfig;
import com.example.quwitestjava.data.api.ApiService;
import com.example.quwitestjava.helper.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
abstract public class AppModule {

    @Provides
    public static String provideBaseUrl() {
        return BuildConfig.API_URL;
    }


    @Singleton
    @Provides
    public static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(Constants.PERSISTENT_STORAGE_NAME, Context.MODE_PRIVATE);
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
