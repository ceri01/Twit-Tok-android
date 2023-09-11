package com.example.twit_tok.presentation.wall;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.App;
import com.example.twit_tok.common.Constants;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WallViewModel extends ViewModel {
    private final Users users;
    private final MutableLiveData<Integer> lastElementInserted;
    private final MutableLiveData<Boolean> isOffline = new MutableLiveData<>();
    private final @Named("twokBuffer") TwokToShowBuffer buffer;
    private final TwokRepositoryImpl twokRepository = new TwokRepositoryImpl();

    @Inject
    public WallViewModel(Users users, @Named("twokBuffer") TwokToShowBuffer buffer) {
        this.users = users;
        this.buffer = buffer;
        this.lastElementInserted = new MutableLiveData<>(buffer.getlength());
    }

    // TODO: gestisci null in entrambi i casi
    public void followUser(User user) {
        ListenableFuture<Sid> listenableFutureSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        listenableFutureSid.addListener(() -> {
            try {
                Sid sid = new Sid(listenableFutureSid.get().sid());
                BasicDataRequest bdr = new BasicDataRequest(sid.sid(), String.valueOf(user.uid()));
                Call<Void> followCall = TwokApiInstance.getTwokApi().follow(bdr);
                followCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        users.insert(user);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        offlineCheck();
                    }
                });
            } catch (Exception e) {
                // TODO: DATABASE
            }
        }, App.getInstance().getMainExecutor());
    }

    public void unfollowUser(int uid) {
        ListenableFuture<Sid> listenableFutureSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        listenableFutureSid.addListener(() -> {
            try {
                Sid sid = new Sid(listenableFutureSid.get().sid());
                BasicDataRequest bdr = new BasicDataRequest(sid.sid(), String.valueOf(uid));
                Call<Void> unfollowCall = TwokApiInstance.getTwokApi().unfollow(bdr);
                unfollowCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        users.remove(uid);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        offlineCheck();
                    }
                });
            } catch (Exception e) {
                // TODO: DATABASE
            }
        }, App.getInstance().getMainExecutor());
    }

    private void retrieveTwoks() {
        ListenableFuture<Sid> listenableFutureSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        listenableFutureSid.addListener(() -> {
            try {
                String sid = listenableFutureSid.get().sid();
                System.out.println(listenableFutureSid.get().uid());
                BasicDataRequest bdr = new BasicDataRequest(sid);
                twokRepository.fetchRandomTwok(bdr, new Callback<RawTwok>() {
                    @Override
                    public void onResponse(@NonNull Call<RawTwok> call, @NonNull Response<RawTwok> response) {
                        RawTwok rawTwok = response.body();
                        if (TwoksUtils.isValidRecivedTwok(rawTwok)) {
                            Log.d("TID", String.valueOf(rawTwok.toString()));
                            BasicDataRequest bdr = new BasicDataRequest(sid, String.valueOf(Objects.requireNonNull(rawTwok).getUid()));
                            twokRepository.fetchUserPicturesLocally(rawTwok.getUid(), new Callback<DBProfileRequest>() {
                                @Override
                                public void onResponse(@NonNull Call<DBProfileRequest> call, @NonNull Response<DBProfileRequest> response) {
                                    final TwokToShow[] twok = new TwokToShow[1];

                                    if (!Objects.isNull(response.body()) && rawTwok.getPversion() == Objects.requireNonNull(response.body()).pversion()) {
                                        DBProfileRequest user = response.body();
                                        twokRepository.isFollowed(bdr, new Callback<IsFollowed>() {
                                            @Override
                                            public void onResponse(@NonNull Call<IsFollowed> call, @NonNull Response<IsFollowed> response) {
                                                boolean isFollowed = Objects.requireNonNull(response.body()).isFollowed();
                                                twok[0] = new TwokToShow(rawTwok, user.name(), user.picture(), isFollowed);
                                                buffer.insert(twok[0]);
                                                lastElementInserted.setValue(buffer.getlength());
                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<IsFollowed> call, @NonNull Throwable t) {
                                                offlineCheck();
                                            }
                                        });
                                    } else {
                                        twokRepository.fetchRemoteUserData(bdr, new Callback<ProfileRequest>() {
                                            @Override
                                            public void onResponse(@NonNull Call<ProfileRequest> call, @NonNull Response<ProfileRequest> response) {
                                                User user;
                                                if (!Objects.isNull(Objects.requireNonNull(response.body()).picture()) && PictureUtils.isValidPicture(response.body().picture().replace("\n", ""))) {
                                                    user = new User(Objects.requireNonNull(response.body()).uid(), response.body().name(), response.body().picture().replace("\n", ""), response.body().pversion(), false);
                                                } else {
                                                    user = new User(Objects.requireNonNull(response.body()).uid(), response.body().name(), null, response.body().pversion(), false);
                                                }
                                                twokRepository.isFollowed(bdr, new Callback<IsFollowed>() {
                                                    @Override
                                                    public void onResponse(@NonNull Call<IsFollowed> call, @NonNull Response<IsFollowed> response) {
                                                        boolean isFollowed = Objects.requireNonNull(response.body()).isFollowed();
                                                        if (PictureUtils.isValidPicture(user.picture())) {
                                                            twokRepository.saveUserDataLocally(user);
                                                            twok[0] = new TwokToShow(rawTwok, user.name(), user.picture(), isFollowed);
                                                        } else {
                                                            twok[0] = new TwokToShow(rawTwok, user.name(), null, isFollowed);
                                                        }
                                                        buffer.insert(twok[0]);
                                                        lastElementInserted.setValue(buffer.getlength());
                                                    }

                                                    @Override
                                                    public void onFailure(@NonNull Call<IsFollowed> call, @NonNull Throwable t) {
                                                        offlineCheck();
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<ProfileRequest> call, @NonNull Throwable t) {
                                                offlineCheck();
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(@NonNull Call<DBProfileRequest> call, @NonNull Throwable t) {
                                    offlineCheck();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RawTwok> call, @NonNull Throwable t) {
                        offlineCheck();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                // TODO: DATABASE
            }
        }, App.getInstance().getMainExecutor());
    }

    public void getTwoks() {
        for (int i = 0; i < Constants.DEFAULT_AMOUNT_OF_NEW_TWOKS; i++) {
            retrieveTwoks();
        }
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

    public void resetBuffer() {
        lastElementInserted.setValue(0);
        buffer.reset();
        getTwoks();
    }

    public int getBufferLength() {
        return this.buffer.getlength();
    }

    // TODO metti anche retrieveNewTwoks con tid
}