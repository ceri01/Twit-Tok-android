package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import com.example.twit_tok.common.Colors;
import com.example.twit_tok.common.TwoksUtils;

import java.util.Objects;

public class RawTwok {
    private final int uid;
    private final int pversion;
    private String text;
    private boolean isFollowed = false;
    private String bgcol;
    private String fontcol;
    private int fontsize = 1;
    private int fonttype = 1;
    private int halign = 1;
    private int valign = 1;
    private Double lat = null;
    private Double lon = null;

    public RawTwok(int uid, int pversion, String text, String bgcol, String fontcol, int fontsize, int fonttype, int halign, int valign) {
        if (TwoksUtils.isValidText(text)) this.text = text;
        if (Colors.isValid(bgcol)) this.bgcol = bgcol;
        if (Colors.isValid(bgcol)) this.fontcol = fontcol;
        if (TwoksUtils.isValidParameter(fontsize)) this.fontsize = fontsize;
        if (TwoksUtils.isValidParameter(fonttype)) this.fonttype = fonttype;
        if (TwoksUtils.isValidParameter(valign)) this.valign = valign;
        if (TwoksUtils.isValidParameter(halign)) this.halign = halign;
        this.pversion = pversion;
        this.uid = uid;
    }

    public RawTwok(int uid, int pversion, String text, String bgcol, String fontcol, int fontsize, int fonttype, int halign, int valign, Double lat, Double lon) {
        this(uid, pversion, text, bgcol, fontcol, fontsize, fonttype, halign, valign);
        if (TwoksUtils.isValidCoord(lat, lon)) {
            this.lat = lat;
            this.lon = lon;
        }
    }

    public int getUid() {
        return uid;
    }

    public String getText() {
        return text;
    }

    public String getBgcol() {
        return bgcol;
    }

    public String getFontcol() {
        return fontcol;
    }

    public int getFontsize() {
        return fontsize;
    }

    public int getFonttype() {
        return fonttype;
    }

    public int getHalign() {
        return halign;
    }

    public int getValign() {
        return valign;
    }

    public Double getLat() {
        if (Objects.isNull(lat)) {
            return null;
        }
        return lat;
    }

    public Double getLon() {
        if (Objects.isNull(lon)) {
            return null;
        }
        return lon;
    }

    public boolean getIsFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    public int getPversion() {
        return this.pversion;
    }

    @NonNull
    @Override
    public String toString() {
        //noinspection StringBufferReplaceableByString
        StringBuilder sb = new StringBuilder("Twok{uid='");
        sb.append(uid)
                .append("', pversion='")
                .append(pversion)
                .append("', text='")
                .append(text)
                .append("', bgcol='")
                .append(bgcol)
                .append("', fontcol='")
                .append(fontcol)
                .append("', fontsize='")
                .append(fontsize)
                .append("', fonttype='")
                .append(fonttype)
                .append("', halign='")
                .append(halign)
                .append("', valign='")
                .append(valign)
                .append("', lat='")
                .append(lat)
                .append("', lon='")
                .append(lon);
        return sb.toString();
    }
}
