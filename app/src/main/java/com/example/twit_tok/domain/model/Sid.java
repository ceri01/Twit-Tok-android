package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public final class Sid {
    private final String sid;

    public Sid(String sid) {
        Objects.requireNonNull(sid, "Sid cannot be null");
        this.sid = sid;
    }

    public String sid() {
        return sid;
    }

    @NonNull
    @Override
    public String toString() {
        return "Sid[" +
                "sid=" + sid + ']';
    }

}
