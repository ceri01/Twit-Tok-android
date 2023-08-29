package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class User {
    private final int uid;
    private final String name;
    private final String picture;
    private final int pversion;
    private final boolean isFollowed;

    public User(int uid, String name, String picture, int pversion, boolean followed) {
        Objects.requireNonNull(name, "Name cannot be null"); // questa roba causa errore probabilmente
        this.isFollowed = followed;
        if (name.isBlank()) {
            this.name = "unnamed";
        } else {
            this.name = name;
        }
        this.picture = picture;
        this.pversion = pversion;
        this.uid = uid;
    }

    public User(User user) {
        this.name = user.name;
        this.isFollowed = user.isFollowed;
        this.picture = user.picture;
        this.pversion = user.pversion;
        this.uid = user.uid;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", uid=" + uid +
                ", pversion=" + pversion +
                '}';
    }

    public int uid() {
        return uid;
    }

    public String name() {
        return name;
    }

    public String picture() {
        return picture;
    }

    public int pversion() {
        return pversion;
    }
}