package com.example.twit_tok.data.db;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.twit_tok.domain.model.Picture;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.requests.DBProfileRequest;
import com.example.twit_tok.domain.requests.ProfileRequest;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Dao
public interface PicturesDao {
    @Query("SELECT * FROM PictureEntity")
    ListenableFuture<List<DBProfileRequest>> getPictures();

    @Query("SELECT * FROM PictureEntity WHERE uid == :uid")
    ListenableFuture<DBProfileRequest> getPictures(int uid);

    @Query("INSERT OR REPLACE INTO PictureEntity VALUES(:uid, :name, :picture, :pversion)")
    ListenableFuture<Void> addUserPicture(int uid, String name, Bitmap picture, int pversion);
}
