package com.example.twit_tok.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

import com.example.twit_tok.App;
import com.example.twit_tok.R;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureUtils {
    public static boolean isValidFormat(String s) {
        Pattern pattern = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static boolean isValidDimension(String s) {
        return s.getBytes().length / 1000 < 100;
    }

    public static Bitmap getDefaultPicture() {
        Drawable image = ResourcesCompat.getDrawable(App.getInstance().getResources(), R.mipmap.ic_default_picture_round, null);
        return Bitmap.createBitmap(image.getIntrinsicWidth(), image.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    }
}
