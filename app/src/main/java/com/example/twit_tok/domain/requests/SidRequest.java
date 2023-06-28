package com.example.twit_tok.domain.requests;

import android.hardware.biometrics.BiometricManager;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SidRequest {
    private final String sid;

    public SidRequest(String sid) {
        Objects.requireNonNull(sid, "sid cannot be null");
        if (sid.isBlank()) throw new IllegalArgumentException("Invalid sid");
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }
}
