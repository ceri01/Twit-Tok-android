package com.example.twit_tok.domain.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Picture {
    private final String uid;
    private final String picture;
    private int pversion;

    public Picture(@NotNull String uid, @NotNull String picture, int pversion) {
        Objects.requireNonNull(picture, "picture can't be null");
        Objects.requireNonNull(uid, "uid can't be null");

        // from string to byte[]
        byte[] picInByte = picture.getBytes(StandardCharsets.UTF_8);

        if (picInByte.length == 0 || picInByte.length > 137000) {
            Bitmap bm = BitmapFactory.decodeFile("mipmap-xxhdpi/ic_default_picture_round.png");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            this.picture = Base64.getEncoder().encodeToString(b);
        } else {
            this.picture = picture;
        }
        this.pversion = pversion;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public String getPicture() {
        return picture;
    }

    public int getPversion() {
        return pversion;
    }
}
