package com.example.twit_tok.common;

import android.graphics.Color;
import android.graphics.Typeface;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.function.Function;

public class Constants {
    public static final String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";
    public static final Integer DEFAULT_VERTICAL_ALIGNAMENT = 1;
    public static final Integer DEFAULT_HORIZONTAL_ALIGNAMENT = 1;
    public static final Typeface DEFAULT_FONT_FAMILY = Typeface.DEFAULT;
    public static final Integer DEFAULT_FONT_SIZE = 25;
    public static final int DEFAULT_NEW_TWOK_BACKGROUND_COLOR = Color.WHITE;
    public static final int DEFAULT_NEW_TWOK_TEXT_COLOR = Color.BLACK;


    public static final Map<Integer, Integer> FONTSIZE = ImmutableMap.of(
            0, 20,
            1, 25,
            2, 30
    );

    public static final Map<Integer, Typeface> FONTTYPE = ImmutableMap.of(
            0, Typeface.DEFAULT,
            1, Typeface.MONOSPACE,
            2, Typeface.SERIF
    );

    public static final Map<Integer, Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>> HALIGN = ImmutableMap.of(
            0, layoutParams -> {
                layoutParams.rightToRight = -1;
                layoutParams.leftToLeft = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            1, layoutParams -> {
                layoutParams.leftToLeft = ConstraintSet.PARENT_ID;
                layoutParams.rightToRight = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            2, layoutParams -> {
                layoutParams.leftToLeft = -1;
                layoutParams.rightToRight = ConstraintSet.PARENT_ID;
                return layoutParams;
            }
    );

    public static final Map<Integer, Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>> VALIGN = ImmutableMap.of(
            0, layoutParams -> {
                layoutParams.bottomToBottom = -1;
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            1, layoutParams -> {
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            2, layoutParams -> {
                layoutParams.topToTop = -1;
                layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
                return layoutParams;
            }
    );

    public static final Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams> IDENTITY_FUNCTION = Function.identity();
    public static final int DEFAULT_AMOUNT_OF_NEW_TWOKS = 8;
}
