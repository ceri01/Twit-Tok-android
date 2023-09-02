package com.example.twit_tok.data.api;

import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.AddTwokRequest;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.BasicDebugRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TwokApi {
    @POST("register")
    Call<Sid> getSid();

    @POST("getProfile")
    Call<ProfileRequest> getProfile(@Body Sid sid);

    @POST("setProfile")
    Call<Void> setProfileName(@Body UpdateProfileNameRequest profile);

    @POST("setProfile")
    Call<Void> setProfilePicture(@Body UpdateProfilePictureRequest profile);

    @POST("getTwok")
    Call<RawTwok> getRandomTwok(@Body BasicDataRequest data);

    @POST("addTwok")
    Call<Void> addTwok(@Body AddTwokRequest twok);

    @POST("getPicture")
    Call<ProfileRequest> getUserData(@Body BasicDataRequest data);

    @POST("follow")
    Call<Void> follow(@Body BasicDataRequest data);

    @POST("unfollow")
    Call<Void> unfollow(@Body BasicDataRequest data);

    @POST("getFollowed")
    Call<List<User>> getFollowed(@Body Sid sid);

    @POST("isFollowed")
    Call<IsFollowed> isFollowed(@Body BasicDataRequest data);
}
