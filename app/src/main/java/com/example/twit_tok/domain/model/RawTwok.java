package com.example.twit_tok.domain.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.twit_tok.common.Colors;
import com.example.twit_tok.common.TwoksUtils;

import java.util.Objects;

public class RawTwok {
    private final int uid;
    private final int pversion;
    private final String text;
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
        Log.d("SIUM1", ""+pversion);
        Objects.requireNonNull(text, "text cannot be null");
        Objects.requireNonNull(fontcol, "fontcol cannot be null");
        Objects.requireNonNull(bgcol, "bgcol cannot be null");
        if (Colors.isValid(bgcol)) this.bgcol = bgcol;
        if (Colors.isValid(bgcol)) this.fontcol = fontcol;
        if (TwoksUtils.isValidParameter(fontsize)) this.fontsize = fontsize;
        if (TwoksUtils.isValidParameter(fonttype)) this.fonttype = fonttype;
        if (TwoksUtils.isValidParameter(valign)) this.valign = valign;
        if (TwoksUtils.isValidParameter(halign)) this.halign = halign;
        this.pversion = pversion;
        this.text = text;
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
        return "Twok{" +
                "text='" +
                text +
                '\'' +
                ", uid='" +
                uid +
                '\'' +
                ", bgcol='" +
                bgcol +
                '\'' +
                ", fontcol='" +
                fontcol +
                '\'' +
                ", fontsize=" +
                fontsize +
                ", fonttype=" +
                fonttype +
                ", halign=" +
                halign +
                ", valign=" +
                valign +
                ", lat=" +
                lat +
                ", lon=" +
                lon +
                '}';
    }
}
