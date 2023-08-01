package com.example.twit_tok.presentation.Profile;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.App;
import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.data.api.TwokApiInstance;
import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.domain.model.Profile;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.example.twit_tok.domain.requests.SidRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;
import com.example.twit_tok.utils.Converters;
import com.example.twit_tok.utils.PictureUtils;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileViewModel extends ViewModel {
    private final Users users;
    private final Profile profile;

    private final MutableLiveData<String> profileNameChanged;
    private final MutableLiveData<Bitmap> profilePictureChanged;
    private final MutableLiveData<Boolean> followedChanged;

    @Inject
    public ProfileViewModel(Users users, Profile profile) {
        Objects.requireNonNull(users, "Given model is null");
        this.users = users;
        this.profile = profile;
        profileNameChanged = new MutableLiveData<>("");
        followedChanged = new MutableLiveData<>(false);
        profilePictureChanged = new MutableLiveData<>(null);
        retriveProfileData();
        retriveFollowedData();
        Log.d("RESPONSE", this.getProfile().name());
    }

    public MutableLiveData<Boolean> getFollowedChanged() {
        return followedChanged;
    }

    public MutableLiveData<String> getProfileNameChanged() {
        return profileNameChanged;
    }

    public MutableLiveData<Bitmap> getProfilePictureChanged() {
        return profilePictureChanged;
    }

    private void addUser(int uid, String name, String picture, int pversion, boolean isFollowed) {
        if (!PictureUtils.isValidFormat(picture)) {
            users.insert(new User(uid, name, null, pversion, isFollowed));
        } else {
            users.insert(new User(uid, name, picture, pversion, isFollowed));
        }
    }

    private void retriveProfileData() {
        ListenableFuture<Sid> lfSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        lfSid.addListener(() -> {
            try {
                SidRequest sid = new SidRequest(lfSid.get().sid());
                Call<ProfileRequest> followed = TwokApiInstance.getTwokApi().getProfile(sid);
                followed.enqueue(new Callback<ProfileRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<ProfileRequest> call, @NonNull Response<ProfileRequest> response) {
                        ProfileRequest p = response.body();
                        profile.changeProfile(p);
                        profileNameChanged.setValue(profile.name());
                        if (!Objects.isNull(profile.picture())) {
                            profilePictureChanged.setValue(Converters.fromBase64ToBitmap(profile.picture()));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ProfileRequest> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        // TODO: Gestisci errore in caso faili la richiesta di rete
                    }
                });
            } catch (Exception e){
                // TODO: gestisci errore in caso non ri riesca a prendere sid da db
            }
        }, App.getInstance().getMainExecutor());
    }

    private void retriveFollowedData() {
        ListenableFuture<Sid> lfSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        lfSid.addListener(() -> {
            try {
                SidRequest sid = new SidRequest(lfSid.get().sid());
                Call<List<User>> followed = TwokApiInstance.getTwokApi().getFollowed(sid);
                followed.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        if (!Objects.isNull(response)) {
                            for (User u : response.body()) {
                                if (!Objects.isNull(u)) {
                                    addUser(u.uid(), u.name(), u.picture(), u.pversion(), true);
                                }
                            }
                        }
                        followedChanged.postValue(false);
                        followedChanged.setValue(true);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        // TODO: Gestisci errore in caso faili la richiesta di rete
                    }
                });
            } catch (Exception e){
                // TODO: gestisci errore in caso non ri riesca a prendere sid da db
            }
        }, App.getInstance().getMainExecutor());
    }

    public void setProfileName(String name) {
        profile.changeProfile(new ProfileRequest(profile.uid(), name, profile.picture(), profile.pversion()));
        ListenableFuture<Sid> lfSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        lfSid.addListener(() -> {
            try {
                ListenableFuture<Void> updateNameRequest = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getProfileDao().updateProfileName(profile.name(), profile.uid());
                updateNameRequest.addListener(() -> {
                    try {
                        UpdateProfileNameRequest pr = new UpdateProfileNameRequest();
                        pr.setSid(lfSid.get().sid());
                        pr.setName(profile.name());
                        Call<Void> changeName = TwokApiInstance.getTwokApi().setProfileName(pr);
                        changeName.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                profileNameChanged.setValue(profile.name());
                            }

                            @Override
                            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                Log.d("RESPONSE", "no " + t);
                                t.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, App.getInstance().getMainExecutor());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, App.getInstance().getMainExecutor());
    }

    public void setProfilePicture(String picture) {
        String verifiedPicture;
        Bitmap convertedPicture;
        if (PictureUtils.isValidFormat(picture) && PictureUtils.isValidDimension(picture)) {
            convertedPicture = Converters.fromBase64ToBitmap(picture);
            verifiedPicture = picture;
            profile.changeProfile(new ProfileRequest(profile.uid(), profile.name(), verifiedPicture, profile.pversion()));

        } else {
            convertedPicture = null;
            verifiedPicture = null;
            profile.changeProfile(new ProfileRequest(profile.uid(), profile.name(), null, profile.pversion()));
        }

        ListenableFuture<Sid> lfSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        lfSid.addListener(() -> {
            try {
                if (Objects.isNull(convertedPicture)) {
                    Log.d("IMG", "null");
                } else {
                    Log.d("IMG", convertedPicture.toString());
                }
                ListenableFuture<Void> updateNameRequest = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getProfileDao().updateProfilePicture(convertedPicture, profile.uid());
                updateNameRequest.addListener(() -> {
                    try {
                        UpdateProfilePictureRequest pr = new UpdateProfilePictureRequest();
                        pr.setSid(lfSid.get().sid());
                        pr.setPicture(verifiedPicture);
                        Call<Void> changePicture = TwokApiInstance.getTwokApi().setProfilePicture(pr);
                        changePicture.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                Log.d("IMG", response.raw().toString());
                                profilePictureChanged.setValue(convertedPicture);
                            }

                            @Override
                            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                Log.d("RESPONSE", "no " + t);
                                t.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, App.getInstance().getMainExecutor());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, App.getInstance().getMainExecutor());
    }

    public Users getUsers() {
        return users;
    }

    public Profile getProfile() {
        return new Profile(this.profile);
    }
}