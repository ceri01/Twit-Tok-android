package com.example.twit_tok.domain.requests;

import java.util.Objects;

public class SidRequest {
    private String sid;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        Objects.requireNonNull(sid, "sid can't be null");
        if (sid.isBlank()) throw new IllegalArgumentException("Invalid sid");
        this.sid = sid;
    }
}
