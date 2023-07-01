package com.example.twit_tok.domain.model;

import java.util.Objects;

public record User(int uid, String name, String picture, int pversion) {
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