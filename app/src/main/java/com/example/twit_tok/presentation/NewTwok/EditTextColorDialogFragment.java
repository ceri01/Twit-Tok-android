package com.example.twit_tok.presentation.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.presentation.NoticeDialogTextColorListener;

import top.defaults.colorpicker.ColorObserver;
import top.defaults.colorpicker.ColorPickerView;

public class EditTextColorDialogFragment extends DialogFragment {
    NoticeDialogTextColorListener listener;
    private int newColor = Constants.DEFAULT_NEW_TWOK_TEXT_COLOR;

    public EditTextColorDialogFragment(NoticeDialogTextColorListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View dv = inflater.inflate(R.layout.color_picker_dialog, null);
        ColorPickerView cpv = dv.findViewById(R.id.color_picker);
        cpv.setInitialColor(Constants.DEFAULT_NEW_TWOK_TEXT_COLOR);
        cpv.subscribe(new ColorObserver() {
            @Override
            public void onColor(int color, boolean fromUser, boolean shouldPropagate) {
                newColor = color;
            }
        });

        builder.setView(dv)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog d = (Dialog) dialog;
                        listener.onTextColorDialogPositiveClick(d, newColor);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
