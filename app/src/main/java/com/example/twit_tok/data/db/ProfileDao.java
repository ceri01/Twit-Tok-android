package com.example.twit_tok.data.db;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.twit_tok.domain.model.Profile;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.google.common.util.concurrent.ListenableFuture;

@Dao
public interface ProfileDao {
    @Query("SELECT * FROM ProfileEntity")
    ProfileRequest getProfile();

    @Query("UPDATE ProfileEntity SET picture=:picture WHERE uid=:uid")
    ListenableFuture<Void> updateProfilePicture(Bitmap picture, int uid);

    @Query("UPDATE ProfileEntity SET name=:name WHERE uid=:uid")
    ListenableFuture<Void> updateProfileName(String name, int uid);
}
