package com.example.twit_tok.common;

import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.domain.model.RawTwok;

import java.util.Objects;

public class TwoksUtils {
    public static boolean isValidParameter(Integer value) {
        return (!Objects.isNull(value) && value >= 0 && value < 3);
    }

    public static boolean isValidCoord(Double lat, Double lon) {
        return (!Objects.isNull(lat) && !Objects.isNull(lon) && (lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180));
    }

    public static boolean isValidText(String text) {
        return !(Objects.isNull(text) || text.isBlank());
    }

    public static boolean isValidRecivedTwok(RawTwok twok) {
        return !Objects.isNull(twok) &&
                isValidText(twok.getText()) &&
                isValidParameter(twok.getFontsize()) &&
                isValidParameter(twok.getHalign()) &&
                isValidParameter(twok.getValign()) &&
                isValidParameter(twok.getFonttype()) &&
                Colors.isValid(twok.getFontcol()) &&
                Colors.isValid(twok.getBgcol()) &&
                ((Objects.isNull(twok.getLat()) && Objects.isNull(twok.getLon())) || isValidCoord(twok.getLat(), twok.getLon()));
    }

    public static boolean isValidTwokToSend(NewTwok twok) {
        return !Objects.isNull(twok) &&
                isValidText(twok.getText()) &&
                isValidParameter(twok.getFontsize()) &&
                isValidParameter(twok.getHalign()) &&
                isValidParameter(twok.getValign()) &&
                isValidParameter(twok.getFonttype()) &&
                Colors.isValid(twok.getFontcol()) &&
                Colors.isValid(twok.getBgcol()) &&
                ((Objects.isNull(twok.getLat()) && Objects.isNull(twok.getLon())) || isValidCoord(twok.getLat(), twok.getLon()));
    }
}
