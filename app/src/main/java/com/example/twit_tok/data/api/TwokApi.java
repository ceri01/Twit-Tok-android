package com.example.twit_tok.data.api;

import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.Profile;
import com.example.twit_tok.domain.model.Twok;
import com.example.twit_tok.domain.requests.BasicDebugRequest;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.SidRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;
import com.example.twit_tok.domain.requests.AddTwokRequest;
import com.example.twit_tok.domain.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TwokApi {
    @POST("register")
    Call<SidRequest> getSid();

    @POST("getProfile")
    Call<Profile> getProfile(@Body SidRequest sid);

    @POST("setProfile")
    Call<Void> setProfileName(@Body UpdateProfileNameRequest profile);

    @POST("setProfile")
    Call<Void> setProfilePicture(@Body UpdateProfilePictureRequest profile);

    @POST("getTwok")
    Call<Twok> getTwokWithUid(@Body BasicDebugRequest data);

    @POST("getTwok")
    Call<Twok> getTwokWithUid(@Body BasicDataRequest data);

    @POST("getTwok")
    Call<Twok> getRandomTwok(@Body SidRequest sid);

    @POST("addTwok")
    Call<Void> addTwok(@Body AddTwokRequest twok);

    @POST("getPicture")
    Call<User> getPicture(@Body BasicDataRequest data);

    @POST("follow")
    Call<Void> follow(@Body BasicDataRequest data);

    @POST("unfollow")
    Call<Void> unfollow(@Body BasicDataRequest data);

    @POST("getFollowed")
    Call<List<User>> getFollowed(@Body SidRequest sid);

    @POST("isFollowed")
    Call<IsFollowed> isFollowed(@Body BasicDataRequest data);
}
