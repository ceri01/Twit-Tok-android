package com.example.twit_tok.data.db;

import android.graphics.Bitmap;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.twit_tok.domain.model.Picture;
import com.example.twit_tok.domain.model.Profile;

import java.util.List;

@Dao
public interface PicturesDao {
    @Query("SELECT * FROM PictureEntity")
    List<Picture> getPictures();

    @Query("SELECT * FROM PictureEntity WHERE uid == :uid")
    Picture getPicture(String uid);

    @Query("UPDATE PictureEntity SET picture=:picture, pversion=:pversion WHERE uid=:uid")
    Profile updateUserPicture(Bitmap picture, int uid, int pversion);

    @Query("INSERT INTO PictureEntity VALUES(:uid, :picture, :pversion) DUPLICATE KEY UPDATE")
    Profile addUserPicture(int uid, Bitmap picture, int pversion);
}
