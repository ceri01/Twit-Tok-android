package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;
import androidx.room.Ignore;

import com.example.twit_tok.domain.requests.ProfileRequest;

import java.util.Objects;

import javax.inject.Inject;

public class Profile {
    private int uid;
    private String name;
    private String picture;
    private int pversion;

    @Inject
    public Profile() {
        this.uid = 1;
        this.name = "Unnamed";
        this.picture = null;
        this.pversion = 0;
    }

    @Ignore
    public Profile(int uid, String name, String picture, int pversion) {
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

    @Ignore
    public Profile(Profile profile) {
        this.name = profile.name;
        this.picture = profile.picture;
        this.pversion = profile.pversion;
        this.uid = profile.uid;
    }

    public void changeProfile(ProfileRequest p) {
        if (!Objects.isNull(p)) {
            this.uid = p.uid();
            this.picture = p.picture();
            this.pversion = p.pversion();
            this.name = p.name();
        }
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

    @NonNull
    @Override
    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder("User{uid='");
        sb.append(uid)
                .append("', pversion='")
                .append(pversion)
                .append("', name='")
                .append(name)
                .append("', picture='")
                .append(picture)
                .append("', picture='")
                .append(picture);
        return sb.toString();
    }
}