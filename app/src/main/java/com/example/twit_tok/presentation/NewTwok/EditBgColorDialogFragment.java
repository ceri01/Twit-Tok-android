package com.example.twit_tok.presentation.NewTwok;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.presentation.NoticeDialogBgColorListener;

import top.defaults.colorpicker.ColorObserver;
import top.defaults.colorpicker.ColorPickerView;

public class EditBgColorDialogFragment extends DialogFragment {
    NoticeDialogBgColorListener listener;
    private int newColor = Constants.DEFAULT_NEW_TWOK_BACKGROUND_COLOR;

    public EditBgColorDialogFragment(NoticeDialogBgColorListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View dv = inflater.inflate(R.layout.color_picker_dialog, null);
        ColorPickerView cpv = dv.findViewById(R.id.color_picker);
        cpv.setInitialColor(Constants.DEFAULT_NEW_TWOK_BACKGROUND_COLOR);
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
                        listener.onBgColorDialogPositiveClick(d, newColor);
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
