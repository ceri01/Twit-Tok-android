package com.example.twit_tok.domain;

import java.util.Objects;

public class Sid {
    private final String sid;

    public Sid(String sid) {
        Objects.requireNonNull(sid, "Sid can't be null");
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }
}
