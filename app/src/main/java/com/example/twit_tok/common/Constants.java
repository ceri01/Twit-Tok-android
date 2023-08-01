package com.example.twit_tok.common;

import android.graphics.Typeface;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Constants {
    public static final String BASE_URL = "https://develop.ewlab.di.unimi.it/mc/twittok/";

    public static final Map<Integer, Integer> TEXTSIZES = ImmutableMap.of(
            1, 20,
            2, 30,
            3, 40
    );

    public static final Map<Integer, Typeface> FONTTYPE = ImmutableMap.of(
            1, Typeface.DEFAULT,
            2, Typeface.MONOSPACE,
            3, Typeface.SERIF
    );

    public static final Map<Integer, Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>> HALIGN = ImmutableMap.of(
            1, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.leftToLeft = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            2, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.leftToLeft = ConstraintSet.PARENT_ID;
                layoutParams.rightToRight = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            3, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.rightToRight = ConstraintSet.PARENT_ID;
                return layoutParams;
            }
    );

    public static final Map<Integer, Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>> VALIGN = ImmutableMap.of(
            1, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            2, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.topToTop = ConstraintSet.PARENT_ID;
                layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
                return layoutParams;
            },
            3, (Function<ConstraintLayout.LayoutParams, ConstraintLayout.LayoutParams>) layoutParams -> {
                layoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
                return layoutParams;
            }
    );
}
