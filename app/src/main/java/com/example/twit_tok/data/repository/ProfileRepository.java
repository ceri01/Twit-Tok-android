package com.example.twit_tok.data.repository;

import android.graphics.Bitmap;

import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;

import java.util.List;

import retrofit2.Callback;

public interface ProfileRepository {
    void fetchFollowedUsers(Sid sid, Callback<List<User>> callback);

    void fetchProfileData(Sid sid, Callback<ProfileRequest> callback);

    void fetchRemoteUserData(BasicDataRequest bdr, Callback<ProfileRequest> callback);

    void setProfileName(UpdateProfileNameRequest pnr, Callback<Void> callback);

    void setProfilePicture(UpdateProfilePictureRequest ppr, Callback<Void> callback);

    void unfollowUser(BasicDataRequest bdr, Callback<Void> callback);

    void fetchUserPicturesLocally(int uid, Callback<DBProfileRequest> callback);

    void saveUserDataLocally(User user);

    void setProfileNameLocally(Sid sid, int uid, String name, Callback<UpdateProfileNameRequest> callback);

    void setProfilePictureLocally(Sid sid, int uid, Bitmap bitmapPicture, String stringPicture, Callback<UpdateProfilePictureRequest> callback);
}
