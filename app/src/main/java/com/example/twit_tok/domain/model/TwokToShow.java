package com.example.twit_tok.domain.model;

import android.graphics.Bitmap;

public class TwokToShow extends RawTwok {
    private final String username;
    private final Bitmap picture;
    private final boolean isFollowed;

    public TwokToShow(RawTwok rawTwok, String username, Bitmap picture, boolean isFollowed) {
        super(rawTwok.getUid(), rawTwok.getPversion(), rawTwok.getText(), rawTwok.getBgcol(), rawTwok.getFontcol(), rawTwok.getFontsize(), rawTwok.getFonttype(), rawTwok.getHalign(), rawTwok.getValign(), rawTwok.getLat(), rawTwok.getLon());
        this.username = username;
        this.picture = picture;
        this.isFollowed = isFollowed;
    }

    public String getUserName() {
        return username;
    }

    public Bitmap getUserPicture() {
        return picture;
    }

    public boolean isFollowed() {
        return isFollowed;
    }
}
