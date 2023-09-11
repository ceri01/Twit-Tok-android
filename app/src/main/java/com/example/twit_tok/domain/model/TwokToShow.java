package com.example.twit_tok.domain.model;

public class TwokToShow extends RawTwok {
    private final String username;
    private final String picture;

    public TwokToShow(RawTwok rawTwok, String username, String picture, boolean isFollowed) {
        super(rawTwok.getUid(), rawTwok.getPversion(), rawTwok.getTid(), rawTwok.getText(), rawTwok.getBgcol(), rawTwok.getFontcol(), rawTwok.getFontsize(), rawTwok.getFonttype(), rawTwok.getHalign(), rawTwok.getValign(), rawTwok.getLat(), rawTwok.getLon());
        this.username = username;
        this.picture = picture;
    }

    public String getUserName() {
        return username;
    }

    public String getUserPicture() {
        return picture;
    }
}
