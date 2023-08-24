package com.example.twit_tok.di;

import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.domain.model.Profile;
import com.example.twit_tok.domain.model.TwokToShowBuffer;
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

    @Provides
    @Singleton
    public Profile provideProfile() {
        return new Profile();
    }

    @Provides
    @Singleton
    public NewTwok provideNewTwok() {
        return new NewTwok();
    }

    @Provides
    @Singleton
    public TwokToShowBuffer provideTwokBuffer() {
        return new TwokToShowBuffer();
    }
}
