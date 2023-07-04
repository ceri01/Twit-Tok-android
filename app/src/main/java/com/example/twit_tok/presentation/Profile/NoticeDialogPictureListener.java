package com.example.twit_tok.presentation.Profile;

import android.graphics.Bitmap;

import androidx.fragment.app.DialogFragment;

public interface NoticeDialogPictureListener {
    public void onPictureDialogPositiveClick(DialogFragment dialog, Bitmap value);
    public void onPictureDialogNegativeClick(DialogFragment dialog);
}
