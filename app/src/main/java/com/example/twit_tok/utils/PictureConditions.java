package com.example.twit_tok.utils;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureConditions {
    public static boolean isValidFormat(String s) {
        Pattern pattern = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
