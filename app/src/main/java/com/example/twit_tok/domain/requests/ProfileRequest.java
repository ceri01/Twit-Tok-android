package com.example.twit_tok.domain.requests;

import androidx.annotation.NonNull;
import androidx.room.Ignore;

import java.util.Objects;

import javax.inject.Inject;

public class ProfileRequest {
    private int uid;
    private String name;
    private String picture;
    private int pversion;

    public ProfileRequest(int uid, String name, String picture, int pversion) {
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

    public ProfileRequest(ProfileRequest profile) {
        this.name = profile.name;
        this.picture = profile.picture;
        this.pversion = profile.pversion;
        this.uid = profile.uid;
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