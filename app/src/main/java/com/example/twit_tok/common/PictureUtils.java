package com.example.twit_tok.common;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureUtils {
    public static boolean isValidFormat(String s) {
        Pattern patternPath = Pattern.compile("^.*\\\\.[a-zA-Z0-9]+$");
        Pattern patternBase64 = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
        Matcher matcherPath = patternPath.matcher(s);
        Matcher matcherBase64 = patternBase64.matcher(s);
        return matcherBase64.matches() && !matcherPath.matches();
    }

    public static boolean isValidDimension(String s) {
        return s.getBytes().length / 1000 < 100;
    }

    public static boolean isValidPicture(String s) {
        return !Objects.isNull(s) && isValidFormat(s) && isValidDimension(s);
    }
}
