package com.example.twit_tok.domain.model;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.example.twit_tok.App;
import com.example.twit_tok.utils.Converters;

import java.util.Objects;

public class User {
    private final String name;
    private final String picture;
    private final int uid;
    private final int pversion;

    public User(int uid, String name, String picture, int pversion) {
        Objects.requireNonNull(name, "Name cannot be null");

        if (name.isBlank()) {
            this.name = "unnamed";
        } else {
            this.name = name;
        }
        this.picture = picture;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", uid=" + uid +
                ", pversion=" + pversion +
                '}';
    }
}