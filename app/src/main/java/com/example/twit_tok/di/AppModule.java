package com.example.twit_tok.di;

import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.domain.model.TwokToShowBuffer;
import com.example.twit_tok.domain.model.Users;

import javax.inject.Named;
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
    public NewTwok provideNewTwok() {
        return new NewTwok();
    }

    @Provides
    @Singleton
    @Named("userTwokBuffer")
    public TwokToShowBuffer provideUserTwokBuffer() {
        return new TwokToShowBuffer();
    }

    @Provides
    @Singleton
    @Named("twokBuffer")
    public TwokToShowBuffer provideTwokBuffer() {
        return new TwokToShowBuffer();
    }
}
