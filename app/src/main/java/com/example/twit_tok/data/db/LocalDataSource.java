package com.example.twit_tok.data.db;

import com.example.twit_tok.App;
import com.example.twit_tok.common.Converters;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.google.common.util.concurrent.ListenableFuture;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalDataSource {
    public void addUserPicture(User user) {
        TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getPicturesDao().addUserPicture(
                user.uid(),
                user.name(),
                Converters.fromBase64ToBitmap(user.picture()),
                user.pversion()
        );
    }

    public void fetchLocalUserData(int uid, Callback<ProfileRequest> callback) {
        ListenableFuture<ProfileRequest> listenableFuturePicture = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getPicturesDao().getPictures(uid);
        listenableFuturePicture.addListener(() -> {
            try {
                callback.onResponse(null, Response.success(listenableFuturePicture.get()));
            } catch (Exception e) {
                callback.onFailure(null, e);
            }
        }, App.getInstance().getMainExecutor());
    }
}
