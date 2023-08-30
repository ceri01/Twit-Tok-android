package com.example.twit_tok.data.repository;

import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.AddTwokRequest;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;

import java.io.IOException;

import retrofit2.Callback;

public interface TwokRepository {
    void isFollowed(BasicDataRequest bdr, Callback<IsFollowed> callback);

    void fetchRandomTwok(Sid sid, Callback<RawTwok> callback);

    void fetchRemoteUserData(BasicDataRequest bdr, Callback<ProfileRequest> callback);

    void fetchUserPicturesLocally(int uid, Callback<DBProfileRequest> callback);

    void saveUserDataLocally(User user);

    void addTwok(AddTwokRequest twok, Callback<Void> callback);
}
