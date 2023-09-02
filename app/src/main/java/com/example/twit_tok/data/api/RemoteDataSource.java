package com.example.twit_tok.data.api;

import com.example.twit_tok.common.Constants;
import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.AddTwokRequest;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RemoteDataSource {
    public void fetchRandomTwok(BasicDataRequest bdr, Callback<RawTwok> callback) {
        if (Constants.tidSequence != -1) {
            bdr = new BasicDataRequest(bdr.getSid(), bdr.getUid(), String.valueOf(Constants.tidSequence));
            Constants.tidSequence++;
        }
        Call<RawTwok> rawTwokCall = TwokApiInstance.getTwokApi().getRandomTwok(bdr);
        rawTwokCall.enqueue(callback);
        if (Constants.tidSequence != -1) {
            Constants.tidSequence++;
        }
    }

    public void fetchRandomUserTwok(BasicDataRequest bdr, Callback<RawTwok> callback) {
        Call<RawTwok> rawTwokCall = TwokApiInstance.getTwokApi().getRandomTwok(bdr);
        rawTwokCall.enqueue(callback);
    }

    public void fetchIsFollowed(BasicDataRequest basicDataRequest, Callback<IsFollowed> callback) {
        Call<IsFollowed> isFollowedCall = TwokApiInstance.getTwokApi().isFollowed(basicDataRequest);
        isFollowedCall.enqueue(callback);
    }

    public void fetchUserData(BasicDataRequest basicDataRequest, Callback<ProfileRequest> callback) {
        Call<ProfileRequest> userCall = TwokApiInstance.getTwokApi().getUserData(basicDataRequest);
        userCall.enqueue(callback);
    }

    public void fetchFollowedUsers(Sid sid, Callback<List<User>> callback) {
        Call<List<User>> followedCall = TwokApiInstance.getTwokApi().getFollowed(sid);
        followedCall.enqueue(callback);
    }

    public void fetchProfileData(Sid sid, Callback<ProfileRequest> callback) {
        Call<ProfileRequest> profileRequestCall = TwokApiInstance.getTwokApi().getProfile(sid);
        profileRequestCall.enqueue(callback);
    }

    public void setProfileName(UpdateProfileNameRequest pnr, Callback<Void> callback) {
        Call<Void> setProfileNameCall = TwokApiInstance.getTwokApi().setProfileName(pnr);
        setProfileNameCall.enqueue(callback);
    }

    public void setProfilePicture(UpdateProfilePictureRequest ppr, Callback<Void> callback) {
        Call<Void> setProfilePictureCall = TwokApiInstance.getTwokApi().setProfilePicture(ppr);
        setProfilePictureCall.enqueue(callback);
    }

    public void unfollowUser(BasicDataRequest bdr, Callback<Void> callback) {
        Call<Void> unfollowCall = TwokApiInstance.getTwokApi().unfollow(bdr);
        unfollowCall.enqueue(callback);
    }

    public void addTwok(AddTwokRequest atr, Callback<Void> callback) {
        Call<Void> addTwokCall = TwokApiInstance.getTwokApi().addTwok(atr);
        addTwokCall.enqueue(callback);
    }
}
