package com.example.twit_tok.domain.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.example.twit_tok.common.Colors;
import com.example.twit_tok.common.TwoksUtils;

import java.util.Objects;

public class RecivedTwok {
    private final String text;
    private final int uid;
    private String bgcol;
    private String fontcol;
    private int fontsize = 1;
    private int fonttype = 1;
    private int halign = 1;
    private int valign = 1;
    private Double lat = null;
    private Double lon = null;
    private String userName;
    private Bitmap userPicture;


    public RecivedTwok(String text, int uid, String bgcol, String fontcol, int fontsize, int fonttype, int halign, int valign, String userName, Bitmap userPicture) {
        Objects.requireNonNull(text, "text cannot be null");
        Objects.requireNonNull(fontcol, "fontcol cannot be null");
        Objects.requireNonNull(bgcol, "bgcol cannot be null");
        if (Colors.isValid(bgcol)) this.bgcol = "#" + bgcol;
        if (Colors.isValid(bgcol)) this.fontcol = "#" + fontcol;
        if (TwoksUtils.isValidParameter(fontsize)) this.fontsize = fontsize;
        if (TwoksUtils.isValidParameter(fonttype)) this.fonttype = fonttype;
        if (TwoksUtils.isValidParameter(valign)) this.valign = valign;
        if (TwoksUtils.isValidParameter(halign)) this.halign = halign;
        this.userPicture = userPicture;
        this.userName = userName;
        this.text = text;
        this.uid = uid;
    }

    public RecivedTwok(String text, int uid, String bgcol, String fontcol, int fontsize, int fonttype, int halign, int valign, String userName, Bitmap userPicture, Double lat, Double lon) {
        this(text, uid, bgcol, fontcol, fontsize, fonttype, halign, valign, userName, userPicture);
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

    public String getUserName() {
        return userName;
    }

    public Bitmap getUserPicture() {
        return userPicture;
    }

    @NonNull
    @Override
    public String toString() {
        return new StringBuilder()
                .append("Twok{")
                .append("text='")
                .append(text)
                .append('\'')
                .append(", uid='")
                .append(uid)
                .append('\'')
                .append(", bgcol='")
                .append(bgcol)
                .append('\'')
                .append(", fontcol='")
                .append(fontcol)
                .append('\'')
                .append(", fontsize=")
                .append(fontsize)
                .append(", fonttype=")
                .append(fonttype)
                .append(", halign=")
                .append(halign)
                .append(", valign=")
                .append(valign)
                .append(", lat=")
                .append(lat)
                .append(", lon=")
                .append(lon)
                .append('}')
                .toString();
    }
}
