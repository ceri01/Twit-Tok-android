package com.example.twit_tok.data.repository;

import com.example.twit_tok.data.api.RemoteDataSource;
import com.example.twit_tok.data.db.LocalDataSource;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.example.twit_tok.domain.requests.UpdateProfileNameRequest;
import com.example.twit_tok.domain.requests.UpdateProfilePictureRequest;

import java.util.List;

import retrofit2.Callback;

public class ProfileRepositoryImpl implements ProfileRepository {
    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;

    public ProfileRepositoryImpl() {
        this.localDataSource = new LocalDataSource();
        this.remoteDataSource = new RemoteDataSource();
    }

    @Override
    public void fetchFollowedUsers(Sid sid, Callback<List<User>> callback) {
        remoteDataSource.fetchFollowedUsers(sid, callback);
    }

    @Override
    public void fetchProfileData(Sid sid, Callback<ProfileRequest> callback) {
        remoteDataSource.fetchProfileData(sid, callback);
    }

    @Override
    public void fetchUserPicturesLocally(int uid, Callback<DBProfileRequest> callback) {
        localDataSource.fetchUserPicturesLocally(uid, callback);
    }

    @Override
    public void fetchRemoteUserData(BasicDataRequest bdr, Callback<ProfileRequest> callback) {
        remoteDataSource.fetchUserData(bdr, callback);
    }

    @Override
    public void setProfileName(UpdateProfileNameRequest pnr, Callback<Void> callback) {
        remoteDataSource.setProfileName(pnr, callback);
    }

    @Override
    public void setProfilePicture(UpdateProfilePictureRequest ppr, Callback<Void> callback) {
        remoteDataSource.setProfilePicture(ppr, callback);
    }

    @Override
    public void unfollowUser(BasicDataRequest bdr, Callback<Void> callback) {
        remoteDataSource.unfollowUser(bdr, callback);
    }

    @Override
    public void saveUserDataLocally(User user) {
        localDataSource.addUserPicture(user);
    }

    @Override
    public void setProfileNameLocally(Sid sid, String name, Callback<UpdateProfileNameRequest> callback) {
        localDataSource.setProfileName(sid, name, callback);

    }

    @Override
    public void setProfilePictureLocally(Sid sid, String stringPicture, Callback<UpdateProfilePictureRequest> callback) {
        localDataSource.setProfilePicture(sid, stringPicture, callback);
    }
}
