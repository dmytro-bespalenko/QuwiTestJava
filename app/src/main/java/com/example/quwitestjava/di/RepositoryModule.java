package com.example.quwitestjava.di;

import com.example.quwitestjava.repository.AccountsDataSource;
import com.example.quwitestjava.repository.AccountsRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract public class RepositoryModule {

    @Provides
    public static AccountsRepository provideAccountsRepository(AccountsDataSource dataSource) {
        return new AccountsRepository(dataSource);
    }

}
