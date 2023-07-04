package com.example.twit_tok.presentation.Profile;

import androidx.fragment.app.DialogFragment;

public interface NoticeDialogTextListener {
    public void onTextDialogPositiveClick(DialogFragment dialog, String value);
    public void onTextDialogNegativeClick(DialogFragment dialog);
}