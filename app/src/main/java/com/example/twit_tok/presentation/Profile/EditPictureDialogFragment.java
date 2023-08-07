package com.example.twit_tok.presentation.Profile;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.twit_tok.App;
import com.example.twit_tok.R;
import com.example.twit_tok.presentation.NoticeDialogPictureListener;

import java.io.IOException;
import java.util.Objects;

public class EditPictureDialogFragment extends DialogFragment {

    NoticeDialogPictureListener listener;
    private final MutableLiveData<Bitmap> pictureToShow = new MutableLiveData<>();
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri selectedImageUri = data.getData();
                        if (selectedImageUri != null) {
                            ImageDecoder.Source source = ImageDecoder.createSource(App.getInstance().getContentResolver(), selectedImageUri);
                            try {
                                pictureToShow.setValue(ImageDecoder.decodeBitmap(source));
                            } catch (IOException e) {
                                pictureToShow.setValue(null);
                            }
                        }
                    }
                }
            });;


    public EditPictureDialogFragment(NoticeDialogPictureListener listener, Bitmap pictureToShow) {
        this.listener = listener;
        this.pictureToShow.setValue(pictureToShow);
    }

    private void selectImage(ImageView imageView) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        Log.d("IMG", "Sono dentro");
        activityResultLauncher.launch(intent);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.picture_dialog, null);
        Button openGallery = alertLayout.findViewById(R.id.choose_picture_dialog_button);
        ImageView editPicture = alertLayout.findViewById(R.id.new_picture_dialog);
        if (Objects.isNull(pictureToShow.getValue())) {
            editPicture.setImageResource(R.mipmap.ic_default_picture_round);
        } else {
            editPicture.setImageBitmap(pictureToShow.getValue());
        }

        pictureToShow.observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                if (Objects.isNull(bitmap)) {
                    editPicture.setImageResource(R.mipmap.ic_default_picture_round);
                } else {
                    editPicture.setImageBitmap(bitmap);
                }
            }
        });

        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(editPicture);
            }
        });

        builder.setView(alertLayout)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        pictureToShow.observe(EditPictureDialogFragment.this, new Observer<Bitmap>() {
                            @Override
                            public void onChanged(Bitmap bitmap) {
                                listener.onPictureDialogPositiveClick(EditPictureDialogFragment.this, bitmap);
                            }
                        });
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
