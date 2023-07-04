package com.example.twit_tok.presentation.Profile;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.twit_tok.R;

import java.util.Objects;

public class ChangeTextDialogFragment extends DialogFragment {

    NoticeDialogTextListener listener;



    public ChangeTextDialogFragment(NoticeDialogTextListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        builder.setView(inflater.inflate(R.layout.text_dialog, null))
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog d = (Dialog) dialog;
                        EditText editText = d.findViewById(R.id.new_text_dialog);
                        listener.onTextDialogPositiveClick(ChangeTextDialogFragment.this, editText.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onTextDialogNegativeClick(ChangeTextDialogFragment.this);
                        Objects.requireNonNull(ChangeTextDialogFragment.this.getDialog()).cancel();
                    }
                });
        return builder.create();
    }
}
