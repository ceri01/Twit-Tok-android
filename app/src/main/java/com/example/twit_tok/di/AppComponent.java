package com.example.twit_tok.di;

import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.domain.model.TwokToShow;
import com.example.twit_tok.domain.model.TwokToShowBuffer;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;

import javax.inject.Named;

import dagger.Component;

@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(Users users);
    void inject(NewTwok newTwok);
    void inject(TwokToShowBuffer twokToShowBuffer);
}
