package com.example.twit_tok.presentation.user_wall;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.App;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.common.Converters;
import com.example.twit_tok.common.NetUtils;
import com.example.twit_tok.common.PictureUtils;
import com.example.twit_tok.common.TwoksUtils;
import com.example.twit_tok.data.api.TwokApiInstance;
import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.data.repository.TwokRepositoryImpl;
import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.TwokToShow;
import com.example.twit_tok.domain.model.TwokToShowBuffer;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserWallViewModel extends ViewModel {
    private final MutableLiveData<Integer> lastElementInserted;
    private final MutableLiveData<Boolean> isOffline = new MutableLiveData<>();
    private final @Named("userTwokBuffer") TwokToShowBuffer buffer;
    private final TwokRepositoryImpl twokRepository = new TwokRepositoryImpl();

    @Inject
    public UserWallViewModel(@Named("userTwokBuffer") TwokToShowBuffer buffer) {
        this.buffer = buffer;
        this.lastElementInserted = new MutableLiveData<>(buffer.getlength());
    }

    private void retrieveTwoks(String sid, int uid, User user) {
        BasicDataRequest bdr = new BasicDataRequest(sid, String.valueOf(uid));
        twokRepository.fetchRandomTwok(bdr, new Callback<RawTwok>() {
            @Override
            public void onResponse(@NonNull Call<RawTwok> call, @NonNull Response<RawTwok> response) {
                RawTwok rawTwok = response.body();
                if (TwoksUtils.isValidRecivedTwok(rawTwok)) {
                    TwokToShow twok;
                    if (Objects.isNull(user.picture())) {
                        twok = new TwokToShow(rawTwok, user.name(), null, false);
                    } else {
                        twok = new TwokToShow(rawTwok, user.name(), Converters.fromBase64ToBitmap(user.picture()), false);
                    }
                    buffer.insert(twok);
                    lastElementInserted.setValue(buffer.getlength());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RawTwok> call, @NonNull Throwable t) {
                offlineCheck();
            }
        });
    }

    public void getTwoks(int uid) {
        ListenableFuture<Sid> listenableFutureSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        listenableFutureSid.addListener(() -> {
            try {
                Sid sid = new Sid(listenableFutureSid.get().sid());
                BasicDataRequest bdr = new BasicDataRequest(sid.sid(), String.valueOf(uid));
                twokRepository.fetchRemoteUserData(bdr, new Callback<ProfileRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<ProfileRequest> call, @NonNull Response<ProfileRequest> response) {
                        User user;
                        if (!Objects.isNull(Objects.requireNonNull(response.body()).picture()) && PictureUtils.isValidPicture(response.body().picture().replace("\n", ""))) {
                            user = new User(Objects.requireNonNull(response.body()).uid(), response.body().name(), response.body().picture().replace("\n", ""), response.body().pversion(), false);
                        } else {
                            user = new User(Objects.requireNonNull(response.body()).uid(), response.body().name(), null, response.body().pversion(), false);
                        }
                        for (int i = 0; i < Constants.DEFAULT_AMOUNT_OF_NEW_TWOKS; i++) {
                            retrieveTwoks(sid.sid(), uid, user);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProfileRequest> call, @NonNull Throwable t) {
                        offlineCheck();
                    }
                });

            }   catch (Exception e) {

            }
        }, App.getInstance().getMainExecutor());
    }

    private void offlineCheck() {
        if (!NetUtils.isNetworkConnected()) {
            isOffline.postValue(true);
        }
    }

    public MutableLiveData<Boolean> isOffline() {
        return isOffline;
    }

    public MutableLiveData<Integer> isReady() {
        return lastElementInserted;
    }

    public TwokToShowBuffer getBuffer() {
        return buffer;
    }

    public void resetBuffer(int uid) {
        lastElementInserted.setValue(0);
        buffer.reset();
        getTwoks(uid);
    }

    public void emptyBuffer() {
        lastElementInserted.setValue(0);
        buffer.reset();
    }

    public int getBufferLength() {
        return this.buffer.getlength();
    }
}
