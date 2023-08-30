package com.example.twit_tok.domain.requests;

import com.example.twit_tok.domain.model.NewTwok;

public class AddTwokRequest {
    private String sid;
    private NewTwok twok;

    public AddTwokRequest(String sid, NewTwok twok) {
        this.sid = sid;
        this.twok = twok;
    }

    public String getSid() {
        return sid;
    }

    public NewTwok getTwok() {
        return twok;
    }
}
