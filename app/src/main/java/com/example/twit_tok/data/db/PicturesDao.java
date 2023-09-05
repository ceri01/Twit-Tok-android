package com.example.twit_tok.data.db;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

@Dao
public interface PicturesDao {
    @Query("SELECT * FROM PictureEntity")
    ListenableFuture<List<DBProfileRequest>> getPictures();

    @Query("SELECT * FROM PictureEntity WHERE uid == :uid")
    ListenableFuture<DBProfileRequest> getPictures(int uid);

    @Query("INSERT OR REPLACE INTO PictureEntity VALUES(:uid, :name, :picture, :pversion)")
    ListenableFuture<Void> addUserPicture(int uid, String name, String picture, int pversion);
}
