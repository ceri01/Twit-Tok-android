package com.example.twit_tok.data.repository;

import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;

import retrofit2.Callback;

public interface TwokRepository {
    void saveUserDataLocally(User user);
    void isFollowed(BasicDataRequest bdr, Callback<IsFollowed> callback);
    void fetchRandomTwok(Sid sid, Callback<RawTwok> callback);
    void fetchRemoteUserData(BasicDataRequest bdr, Callback<ProfileRequest> callback);
    void fetchLocalUserData(int uid, Callback<ProfileRequest> callback);
}
