package com.example.twit_tok.data.db;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.twit_tok.App;
import com.example.twit_tok.common.Converters;
import com.example.twit_tok.common.PictureUtils;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;
import com.google.common.util.concurrent.ListenableFuture;

import retrofit2.Callback;
import retrofit2.Response;

public class LocalDataSource {
    public void addUserPicture(User user) {
        if (!PictureUtils.isValidPicture(user.picture())) {
            TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getPicturesDao().addUserPicture(
                    user.uid(),
                    user.name(),
                    null,
                    user.pversion()
            );
        } else {
            TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getPicturesDao().addUserPicture(
                    user.uid(),
                    user.name(),
                    Converters.fromBase64ToBitmap(user.picture()),
                    user.pversion()
            );
        }
    }

    public void fetchUserPicturesLocally(int uid, Callback<DBProfileRequest> callback) {
        ListenableFuture<DBProfileRequest> listenableFuturePicture = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getPicturesDao().getPictures(uid);
        listenableFuturePicture.addListener(() -> {
            try {
                callback.onResponse(null, Response.success(listenableFuturePicture.get()));
            } catch (Exception e) {
                callback.onFailure(null, e);
            }
        }, App.getInstance().getMainExecutor());
    }

    public void setProfileName(Sid sid, String name, Callback<UpdateProfileNameRequest> callback) {
        UpdateProfileNameRequest pr = new UpdateProfileNameRequest();
        pr.setSid(sid.sid());
        pr.setName(name);
        ListenableFuture<Void> updateNameRequest = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getProfileDao().updateProfileName(name, Integer.parseInt(sid.uid()));
        updateNameRequest.addListener(() -> {
            try {
                callback.onResponse(null, Response.success(pr));
            } catch (Exception e) {
                callback.onFailure(null, e);
            }
        }, App.getInstance().getMainExecutor());
    }

    public void setProfilePicture(Sid sid, Bitmap bitmapPicture, String stringPicture, Callback<UpdateProfilePictureRequest> callback) {
        UpdateProfilePictureRequest pr = new UpdateProfilePictureRequest();
        pr.setSid(sid.sid());
        pr.setPicture(stringPicture);
        ListenableFuture<Void> listenableFuturePicture = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getProfileDao().updateProfilePicture(bitmapPicture, Integer.parseInt(sid.uid()));
        listenableFuturePicture.addListener(() -> {
            try {
                callback.onResponse(null, Response.success(pr));
            } catch (Exception e) {
                callback.onFailure(null, e);
            }
        }, App.getInstance().getMainExecutor());
    }
}
