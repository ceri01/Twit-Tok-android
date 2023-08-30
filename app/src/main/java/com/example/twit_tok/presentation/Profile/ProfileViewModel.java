package com.example.twit_tok.presentation.Profile;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.App;
import com.example.twit_tok.common.Converters;
import com.example.twit_tok.common.PictureUtils;
import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.data.repository.ProfileRepositoryImpl;
import com.example.twit_tok.domain.model.Profile;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;
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
    private final MutableLiveData<Integer> fetchedFollowedAmount;
    private final ProfileRepositoryImpl profileRepository = new ProfileRepositoryImpl();


    @Inject
    public ProfileViewModel(Users users, Profile profile) {
        Objects.requireNonNull(users, "Given model is null");
        this.users = users;
        this.profile = profile;
        profileNameChanged = new MutableLiveData<>("");
        fetchedFollowedAmount = new MutableLiveData<>();
        profilePictureChanged = new MutableLiveData<>(null);
    }

    public MutableLiveData<String> getProfileNameChanged() {
        return profileNameChanged;
    }

    public MutableLiveData<Bitmap> getProfilePictureChanged() {
        return profilePictureChanged;
    }

    public MutableLiveData<Integer> isReady() {
        return fetchedFollowedAmount;
    }

    public User getUsersByPosition(int position) {
        return new User(this.users.getByPosition(position));
    }

    public Profile getProfile() {
        return new Profile(this.profile);
    }

    private void addUser(int uid, String name, String picture, int pversion) {
        if (!PictureUtils.isValidPicture(picture)) {
            users.insert(new User(uid, name, null, pversion, true));
        } else {
            users.insert(new User(uid, name, picture, pversion, true));
        }
    }

    public void retriveProfileData() {
        ListenableFuture<Sid> lfSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        lfSid.addListener(() -> {
            try {
                Sid sid = new Sid(lfSid.get().sid());
                profileRepository.fetchProfileData(sid, new Callback<ProfileRequest>() {
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
            } catch (Exception e) {
                // TODO: gestisci errore in caso non ri riesca a prendere sid da db
            }
        }, App.getInstance().getMainExecutor());
    }

    public void retriveFollowedData() {
        ListenableFuture<Sid> lfSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        lfSid.addListener(() -> {
            try {
                Sid sid = new Sid(lfSid.get().sid());
                profileRepository.fetchFollowedUsers(sid, new Callback<List<User>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                        for (User u : Objects.requireNonNull(response.body())) {
                            if (!Objects.isNull(u)) {
                                profileRepository.fetchUserPicturesLocally(u.uid(), new Callback<DBProfileRequest>() {
                                    @Override
                                    public void onResponse(@NonNull Call<DBProfileRequest> call, @NonNull Response<DBProfileRequest> response) {
                                        DBProfileRequest localUserData = response.body();
                                        if (Objects.isNull(localUserData)) {
                                            BasicDataRequest bdr = new BasicDataRequest(sid.sid(), String.valueOf(u.uid()));
                                            profileRepository.fetchRemoteUserData(bdr, new Callback<ProfileRequest>() {
                                                @Override
                                                public void onResponse(@NonNull Call<ProfileRequest> call, @NonNull Response<ProfileRequest> response) {
                                                    if (!Objects.isNull(response.body())) {
                                                        User user = new User(Objects.requireNonNull(response.body()).uid(), response.body().name(), response.body().picture(), response.body().pversion(), true);
                                                        profileRepository.saveUserDataLocally(user);
                                                        addUser(user.uid(), user.name(), user.picture(), user.pversion());
                                                        fetchedFollowedAmount.postValue(users.getlength());
                                                    }
                                                }

                                                @Override
                                                public void onFailure(@NonNull Call<ProfileRequest> call, @NonNull Throwable t) {
                                                    // TODO gestisci errore connessione
                                                }
                                            });
                                        } else {
                                            addUser(Objects.requireNonNull(localUserData).uid(), localUserData.name(), Converters.fromBitmapToBase64(localUserData.picture()), localUserData.pversion());
                                            fetchedFollowedAmount.postValue(users.getlength());
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<DBProfileRequest> call, @NonNull Throwable t) {
                                        t.printStackTrace();
                                        // TODO gestisci errore connessione
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        // TODO: Gestisci errore in caso faili la richiesta di rete
                    }
                });
            } catch (Exception e) {
                // TODO: gestisci errore in caso non ri riesca a prendere sid da db
            }
        }, App.getInstance().getMainExecutor());
    }

    public void setProfileName(String name) {
        profile.changeProfile(new ProfileRequest(profile.uid(), name, profile.picture(), profile.pversion()));
        ListenableFuture<Sid> sid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        sid.addListener(() -> {
            try {
                profileRepository.setProfileNameLocally(sid.get(), profile.uid(), name, new Callback<UpdateProfileNameRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<UpdateProfileNameRequest> call, @NonNull Response<UpdateProfileNameRequest> response) {
                        profileRepository.setProfileName(response.body(), new Callback<Void>() {
                            @Override
                            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                profileNameChanged.setValue(profile.name());
                            }

                            @Override
                            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                // TODO gestisci errore connessione
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NonNull Call<UpdateProfileNameRequest> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, App.getInstance().getMainExecutor());
    }

    public void setProfilePicture(String picture) {
        String verifiedPicture;
        Bitmap convertedPicture;
        if (PictureUtils.isValidPicture(picture)) {
            convertedPicture = Converters.fromBase64ToBitmap(picture);
            verifiedPicture = picture;
            profile.changeProfile(new ProfileRequest(profile.uid(), profile.name(), verifiedPicture, profile.pversion()));

        } else {
            convertedPicture = null;
            verifiedPicture = null;
            profile.changeProfile(new ProfileRequest(profile.uid(), profile.name(), null, profile.pversion()));
        }

        ListenableFuture<Sid> sid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        sid.addListener(() -> {
            try {
                profileRepository.setProfilePictureLocally(sid.get(), profile.uid(), convertedPicture, verifiedPicture, new Callback<UpdateProfilePictureRequest>() {
                    @Override
                    public void onResponse(@NonNull Call<UpdateProfilePictureRequest> call, @NonNull Response<UpdateProfilePictureRequest> response) {
                        profileRepository.setProfilePicture(response.body(), new Callback<Void>() {
                            @Override
                            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                                profilePictureChanged.setValue(convertedPicture);
                            }

                            @Override
                            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                                // TODO gestisci errore di rete
                            }
                        });
                    }

                    @Override
                    public void onFailure(@NonNull Call<UpdateProfilePictureRequest> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, App.getInstance().getMainExecutor());
    }

    public void unfollowUser(int uid) {
        ListenableFuture<Sid> sid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
        sid.addListener(() -> {
            try {
                BasicDataRequest bdr = new BasicDataRequest(sid.get().sid(), String.valueOf(uid));
                profileRepository.unfollowUser(bdr, new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        users.remove(uid);
                        fetchedFollowedAmount.postValue(users.getlength());
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        // TODO: Gestisci errore in caso faili la richiesta di rete
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, App.getInstance().getMainExecutor());
    }
}