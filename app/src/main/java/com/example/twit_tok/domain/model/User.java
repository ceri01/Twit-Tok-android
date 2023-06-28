package com.example.twit_tok.domain.model;

import android.graphics.BitmapFactory;

import com.example.twit_tok.utils.Converters;

import java.util.Objects;

public class User {
    private final String name;
    private final String picture;
    private final int uid;
    private final int pversion;

    public User(int uid, String name, String picture, int pversion) {
        Objects.requireNonNull(name, "Name can't be null");
        Objects.requireNonNull(picture, "picture can't be null");
        System.out.println(picture);

        if (name.isBlank()) {
            this.name = "unnamed";
        } else {
            this.name = name;
        }
        byte[] arr = Converters.fromBase64ToByte(picture);
        if (arr.length == 0 || arr.length > 137000) {
            this.picture = Converters.fromBitmapToBase64(BitmapFactory.decodeFile("mipmap-xxhdpi/ic_default_picture_round.png"));
        } else {
            this.picture = picture;
        }
        this.pversion = pversion;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public int getUid() {
        return uid;
    }

    public int getPversion() {
        return pversion;
    }
}