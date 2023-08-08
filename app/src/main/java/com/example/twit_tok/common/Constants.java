package com.example.twit_tok.common;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.Function;

public class Constants {
    public static final String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
    public static final Integer DEFAULT_VERTICAL_ALIGNAMENT = 2;
    public static final Integer DEFAULT_HORIZONTAL_ALIGNAMENT = 2;
    public static final Typeface DEFAULT_FONT_FAMILY = Typeface.DEFAULT;
    public static final Integer DEFAULT_FONT_SIZE = 25;
    public static final int DEFAULT_NEW_TWOK_BACKGROUND_COLOR = Color.WHITE;
    public static final int DEFAULT_NEW_TWOK_TEXT_COLOR = Color.BLACK;


    public static final Map<Integer, Integer> FONTSIZE = ImmutableMap.of(
            1, 20,
            2, 25,
            3, 30
    );

    public static final Map<Integer, Typeface> FONTTYPE = ImmutableMap.of(
            1, Typeface.DEFAULT,
            2, Typeface.MONOSPACE,
            3, Typeface.SERIF
    );

    public static final Map<Integer, Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>> HALIGN = ImmutableMap.of(
            1, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.rightToRight = -1;
                layoutParams.leftToLeft = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            2, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.leftToLeft = ConstraintSet.PARENT_ID;
                layoutParams.rightToRight = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            3, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.leftToLeft = -1;
                layoutParams.rightToRight = ConstraintSet.PARENT_ID;
                return layoutParams;
            }
    );

    public static final Map<Integer, Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>> VALIGN = ImmutableMap.of(
            1, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.bottomToBottom = -1;
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                Log.d("HOMEFRAGMENT", layoutParams.toString());
                return layoutParams;
            },
            2, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
                Log.d("HOMEFRAGMENT", layoutParams.toString());
                return layoutParams;
            },
            3, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.topToTop = -1;
                layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
                Log.d("HOMEFRAGMENT", layoutParams.toString());
                return layoutParams;
            }
    );

    public static final Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams> IDENTITY_FUNCTION = Function.identity();
}
