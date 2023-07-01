package com.example.twit_tok.di;

import android.app.Application;

import com.example.twit_tok.domain.model.Users;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public Users provideUsers() {
        return new Users();
    }
}
