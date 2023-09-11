package com.example.twit_tok.domain.requests;

import androidx.annotation.NonNull;

import java.util.Objects;

public class DBProfileRequest {
    private final int uid;
    private final String name;
    private final String picture;
    private final int pversion;

    public DBProfileRequest(int uid, String name, String picture, int pversion) {
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
