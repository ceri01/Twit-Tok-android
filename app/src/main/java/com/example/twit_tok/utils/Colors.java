package com.example.twit_tok.utils;

import android.graphics.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colors {
    public static boolean isValid(String color) {
        Pattern pattern = Pattern.compile("^[0-9A-Fa-f]{6}$");
        Matcher matcher = pattern.matcher(color);
        return matcher.matches();
    }
}
