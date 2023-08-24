package com.example.twit_tok.data.api;

import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;

import retrofit2.Call;
import retrofit2.Callback;

public class RemoteDataSource {
    public void fetchRandomTwok(Sid sid, Callback<RawTwok> callback) {
        Call<RawTwok> rawTwokCall = TwokApiInstance.getTwokApi().getRandomTwok(sid);
        rawTwokCall.enqueue(callback);
    }

    public void fetchisFollowed(BasicDataRequest basicDataRequest, Callback<IsFollowed> callback) {
        Call<IsFollowed> isFollowedCall = TwokApiInstance.getTwokApi().isFollowed(basicDataRequest);
        isFollowedCall.enqueue(callback);
    }

    public void fetchUserData(BasicDataRequest basicDataRequest, Callback<ProfileRequest> callback) {
        Call<ProfileRequest> userCall = TwokApiInstance.getTwokApi().getUserData(basicDataRequest);
        userCall.enqueue(callback);
    }
}
