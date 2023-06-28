package com.example.twit_tok.domain.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Picture {
    private final int uid;
    private final Bitmap picture;
    private final int pversion;

    public Picture(int uid, @NotNull String picture, int pversion) {
        Objects.requireNonNull(picture, "picture can't be null");

        // from string to byte[]
        byte[] picInByte = picture.getBytes(StandardCharsets.UTF_8);

        if (picInByte.length == 0 || picInByte.length > 137000) {
            this.picture = BitmapFactory.decodeFile("mipmap-xxhdpi/ic_default_picture_round.png");
        } else {
            byte[] b = Base64.getDecoder().decode(picture);
            this.picture = BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        this.pversion = pversion;
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public int getPversion() {
        return pversion;
    }
}
