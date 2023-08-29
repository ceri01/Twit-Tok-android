package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Sid {
    private final String sid;
    private String uid;

    public Sid(String sid) {
        Objects.requireNonNull(sid, "Sid cannot be null");
        this.sid = sid;
    }

    public String sid() {
        return sid;
    }

    public String uid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @NonNull
    @Override
    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder("Sid{sid='");
        sb.append(sid);
        return sb.toString();
    }

}
