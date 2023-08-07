package com.example.twit_tok.data.api;

import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.RecivedTwok;
import com.example.twit_tok.domain.requests.BasicDebugRequest;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.SidRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;
import com.example.twit_tok.domain.requests.AddTwokRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TwokApi {
    @POST("register")
    Call<SidRequest> getSid();

    @POST("getProfile")
    Call<ProfileRequest> getProfile(@Body SidRequest sid);

    @POST("setProfile")
    Call<Void> setProfileName(@Body UpdateProfileNameRequest profile);

    @POST("setProfile")
    Call<Void> setProfilePicture(@Body UpdateProfilePictureRequest profile);

    @POST("getTwok")
    Call<RecivedTwok> getTwokWithUid(@Body BasicDebugRequest data);

    @POST("getTwok")
    Call<RecivedTwok> getTwokWithUid(@Body BasicDataRequest data);

    @POST("getTwok")
    Call<RecivedTwok> getRandomTwok(@Body SidRequest sid);

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
