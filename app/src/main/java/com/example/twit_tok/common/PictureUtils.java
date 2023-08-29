package com.example.twit_tok.common;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.service.autofill.FieldClassification;
import android.util.Log;

import androidx.core.content.res.ResourcesCompat;

import com.example.twit_tok.App;
import com.example.twit_tok.R;

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

    public static Bitmap getDefaultPicture() {
        Drawable image = ResourcesCompat.getDrawable(App.getInstance().getResources(), R.mipmap.ic_default_picture_round, null);
        return Bitmap.createBitmap(image.getIntrinsicWidth(), image.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    }
}
