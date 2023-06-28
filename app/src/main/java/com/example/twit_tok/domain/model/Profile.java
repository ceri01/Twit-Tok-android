package com.example.twit_tok.domain.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

public class Profile {
    private final String name;
    private final String picture;
    private final int uid;
    private final int pversion;

    public Profile(String name, String picture, String uid, int pversion) {
        Objects.requireNonNull(name, "Name can't be null");
        Objects.requireNonNull(picture, "picture can't be null");
        Objects.requireNonNull(uid, "uid can't be null");

        if (name.isBlank()) {
            this.name = "unnamed";
        } else {
            this.name = name;
        }

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
        if (uid.isBlank())throw new IllegalArgumentException("Uid can't be blank");
        this.uid = Integer.parseInt(uid);
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