package com.example.twit_tok.data.db;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.twit_tok.domain.model.Picture;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Dao
public interface PicturesDao {
    @Query("SELECT * FROM PictureEntity")
    ListenableFuture<List<Picture>> getPictures();

    @Query("SELECT * FROM PictureEntity WHERE uid == :uid")
    ListenableFuture<Picture> getPicture(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Picture picture);

    @Query("INSERT INTO PictureEntity VALUES(:uid, :picture, :pversion)")
    ListenableFuture<Void> addUserPicture(int uid, Bitmap picture, int pversion);
}
