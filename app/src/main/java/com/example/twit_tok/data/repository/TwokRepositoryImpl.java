package com.example.twit_tok.data.repository;

import com.example.twit_tok.data.api.RemoteDataSource;
import com.example.twit_tok.data.db.LocalDataSource;
import com.example.twit_tok.domain.model.IsFollowed;
import com.example.twit_tok.domain.model.RawTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.BasicDataRequest;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;

import retrofit2.Callback;

public class TwokRepositoryImpl implements TwokRepository {
    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;

    public TwokRepositoryImpl() {
        this.localDataSource = new LocalDataSource();
        this.remoteDataSource = new RemoteDataSource();
    }

    @Override
    public void saveUserDataLocally(User user) {
        localDataSource.addUserPicture(user);
    }

    @Override
    public void isFollowed(BasicDataRequest bdr, Callback<IsFollowed> callback) {
        remoteDataSource.fetchisFollowed(bdr, callback);
    }

    @Override
    public void fetchRandomTwok(Sid sid, Callback<RawTwok> callback) {
        remoteDataSource.fetchRandomTwok(sid, callback);
    }

    @Override
    public void fetchRemoteUserData(BasicDataRequest bdr, Callback<ProfileRequest> callback) {
        remoteDataSource.fetchUserData(bdr, callback);
    }

    @Override
    public void fetchUserPicturesLocally(int uid, Callback<DBProfileRequest> callback) {
        localDataSource.fetchUserPicturesLocally(uid, callback);
    }
}
