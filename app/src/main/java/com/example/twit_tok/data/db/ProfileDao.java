package com.example.twit_tok.data.db;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.twit_tok.domain.model.Profile;

@Dao
public interface ProfileDao {
    @Query("SELECT * FROM ProfileEntity")
    Profile getProfile();

    @Query("UPDATE ProfileEntity SET picture=:picture WHERE uid=:uid")
    void updateProfilePicture(Bitmap picture, int uid);

    @Query("UPDATE ProfileEntity SET name=:name WHERE uid=:uid")
    void updateProfileName(String name, int uid);
}
