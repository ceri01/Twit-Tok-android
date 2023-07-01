package com.example.twit_tok.domain.model;

import java.util.Objects;

public record Sid(String sid) {
    public Sid {
        Objects.requireNonNull(sid, "Sid cannot be null");
    }
}
