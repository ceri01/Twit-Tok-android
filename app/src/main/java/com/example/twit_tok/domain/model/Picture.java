package com.example.twit_tok.domain.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Entity;

import com.example.twit_tok.utils.Converters;

import org.jetbrains.annotations.NotNull;

import java.util.Base64;
import java.util.Objects;

public class Picture {
    private final int uid;
    private final String picture;
    private final int pversion;

    public Picture(int uid, String picture, int pversion) {
        Objects.requireNonNull(picture, "picture cannot be null");

        byte[] arr = Converters.fromBase64ToByte(picture);
        if (arr.length == 0 || arr.length > 137000) {
            this.picture = Converters.fromBitmapToBase64(BitmapFactory.decodeFile("mipmap-xxhdpi/ic_default_picture_round.png"));
        } else {
            this.picture = picture;
        }
        this.pversion = pversion;
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public String getPicture() {
        return picture;
    }

    public int getPversion() {
        return pversion;
    }
}
