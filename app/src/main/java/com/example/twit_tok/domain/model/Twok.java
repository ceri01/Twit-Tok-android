package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import com.example.twit_tok.utils.Colors;
import com.example.twit_tok.utils.TwoksConditions;

import java.util.Objects;

public class Twok {
    private final String text;
    private final int uid;
    private String bgcol;
    private String fontcol;
    private int fontsize = 1;
    private int fonttype = 1;
    private int halign = 1;
    private int valign = 1;
    private Double lat;
    private Double lon;

    public Twok(String text, int uid, String bgcol, String fontcol, int fontsize, int fonttype, int halign, int valign) {
        Objects.requireNonNull(text, "text cannot be null");
        Objects.requireNonNull(fontcol, "fontcol cannot be null");
        Objects.requireNonNull(bgcol, "bgcol cannot be null");
        if (Colors.isValid(bgcol)) this.bgcol = bgcol;
        if (Colors.isValid(bgcol)) this.fontcol = fontcol;
        if (TwoksConditions.isValidParameter(fontsize)) this.fontsize = fontsize;
        if (TwoksConditions.isValidParameter(fonttype)) this.fonttype = fonttype;
        if (TwoksConditions.isValidParameter(valign)) this.valign = valign;
        if (TwoksConditions.isValidParameter(halign)) this.halign = halign;

        this.text = text;
        this.uid = uid;
    }

    public Twok(String text, int uid, String bgcol, String fontcol, int fontsize, int fonttype, int halign, int valign, Double lat, Double lon) {
        this(text, uid, bgcol, fontcol, fontsize, fonttype, halign, valign);
        if (!Objects.isNull(lat) && !Objects.isNull(lon) && TwoksConditions.isValidCoord(lat, lon)) {
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

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
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
