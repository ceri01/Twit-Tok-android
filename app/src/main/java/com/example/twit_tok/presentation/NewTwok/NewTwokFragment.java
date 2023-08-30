package com.example.twit_tok.presentation.NewTwok;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Constants;
import com.example.twit_tok.common.LocationUtils;
import com.example.twit_tok.data.repository.TwokRepositoryImpl;
import com.example.twit_tok.databinding.FragmentNewTwokBinding;
import com.example.twit_tok.domain.requests.AddTwokRequest;
import com.example.twit_tok.presentation.NewTwokEventListener;
import com.example.twit_tok.presentation.NoticeDialogBgColorListener;
import com.example.twit_tok.presentation.NoticeDialogLocationListener;
import com.example.twit_tok.presentation.NoticeDialogPermissionListener;
import com.example.twit_tok.presentation.NoticeDialogTextColorListener;
import com.example.twit_tok.presentation.NoticeDialogTextListener;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewTwokFragment extends Fragment implements NoticeDialogTextListener, NoticeDialogBgColorListener, NoticeDialogTextColorListener, NoticeDialogPermissionListener, NoticeDialogLocationListener, NewTwokEventListener {
    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
        } else {
            showPermissionErrorDialog();
        }
    });
    @Inject
    NewTwokViewModel newTwokViewModel;
    private FragmentNewTwokBinding binding;

    private void askPermission() {
        this.requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNewTwokBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MaterialButton cancelButton = root.findViewById(R.id.add_twok_cancel_button);
        MaterialButton hAlignButton = root.findViewById(R.id.add_twok_align_horizontal);
        MaterialButton vAlignButton = root.findViewById(R.id.add_twok_align_vertical);
        MaterialButton editTextSizeButton = root.findViewById(R.id.add_twok_edit_font_size);
        MaterialButton editTextFontButton = root.findViewById(R.id.add_twok_edit_font_family);
        MaterialButton addTextButton = root.findViewById(R.id.add_twok_insert_text);
        MaterialButton addPositionButton = root.findViewById(R.id.add_twok_coordinates);
        MaterialButton confirmButton = root.findViewById(R.id.add_twok_confirm_buttond);
        MaterialButton editTextColorButton = root.findViewById(R.id.add_twok_edit_text_color);
        MaterialButton editBackgroundColorButton = root.findViewById(R.id.add_twok_edit_background_color);
        TextView text = root.findViewById(R.id.add_twok_text);
        ConstraintLayout textContainer = root.findViewById(R.id.add_twok_text_container);
        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(text.getLayoutParams());

        newTwokViewModel.onChangeText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                text.setText(s);
            }
        });

        newTwokViewModel.onHAlignChange().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer hAlign) {
                Objects.requireNonNull(Constants.HALIGN.getOrDefault(hAlign, Constants.IDENTITY_FUNCTION)).apply(lp);
                text.setLayoutParams(lp);
            }
        });

        newTwokViewModel.onVAlignChange().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer vAlign) {
                Objects.requireNonNull(Constants.VALIGN.getOrDefault(vAlign, Constants.IDENTITY_FUNCTION)).apply(lp);
                text.setLayoutParams(lp);
            }
        });

        newTwokViewModel.onFontSizeChange().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer fontSize) {
                var value = Objects.requireNonNull(Constants.FONTSIZE.getOrDefault(fontSize, Constants.DEFAULT_FONT_SIZE));
                text.setTextSize(value);
            }
        });

        newTwokViewModel.onFontTypeChange().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer fontType) {
                var value = Objects.requireNonNull(Constants.FONTTYPE.getOrDefault(fontType, Constants.DEFAULT_FONT_FAMILY));
                text.setTypeface(value);
            }
        });

        newTwokViewModel.onBgColorChange().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer color) {
                textContainer.setBackgroundColor(color);
            }
        });

        newTwokViewModel.onFontColorChange().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer color) {
                text.setTextColor(color);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTwokViewModel.resetTwok();
                Objects.requireNonNull(Constants.VALIGN.get(Constants.DEFAULT_VERTICAL_ALIGNAMENT)).apply(lp);
                Objects.requireNonNull(Constants.HALIGN.get(Constants.DEFAULT_HORIZONTAL_ALIGNAMENT)).apply(lp);
                text.setTextSize(Constants.DEFAULT_FONT_SIZE);
                text.setTypeface(Constants.DEFAULT_FONT_FAMILY);
                text.setLayoutParams(lp);
            }
        });

        hAlignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTwokViewModel.setHAlign();
            }
        });

        vAlignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTwokViewModel.setVAlign();
            }
        });

        editTextSizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTwokViewModel.setFontSize();
            }
        });

        editTextFontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTwokViewModel.setFontType();
            }
        });

        addTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTwokTextDialog();
            }
        });

        addPositionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LocationUtils.hasLocationPermissions(requireContext())) {
                    showPositionDialog();
                } else {
                    askPermission();
                }
            }
        });

        editTextColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTextColorDialog();
            }
        });

        editBackgroundColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditBgColorDialog();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTwokViewModel.addTwok(NewTwokFragment.this);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showEditTwokTextDialog() {
        DialogFragment dialog = new EditTwokTextDialogFragment(NewTwokFragment.this);
        dialog.show(getParentFragmentManager(), "EditTwokTextDialogFragment");
    }

    private void showEditBgColorDialog() {
        DialogFragment dialog = new EditBgColorDialogFragment(NewTwokFragment.this);
        dialog.show(getParentFragmentManager(), "EditBgColorDialogFragment");
    }

    private void showEditTextColorDialog() {
        DialogFragment dialog = new EditTextColorDialogFragment(NewTwokFragment.this);
        dialog.show(getParentFragmentManager(), "EditTextColorDialogFragment");
    }

    private void showPositionDialog() {
        DialogFragment dialog = new SetLocationDialogFragment(NewTwokFragment.this);
        dialog.show(getParentFragmentManager(), "SetLocationDialogFragment");
    }

    private void showPermissionErrorDialog() {
        DialogFragment dialog = new PermissionErrorDialog();
        dialog.show(getParentFragmentManager(), "PermissionErrorDialog");
    }

    @Override
    public void onTextDialogPositiveClick(DialogFragment dialog, String value) {
        this.newTwokViewModel.setTwokText(value);
        TextView t = binding.getRoot().findViewById(R.id.add_twok_text);
        t.setText(value);
    }

    @Override
    public void onBgColorDialogPositiveClick(Dialog dialog, int color) {
        this.newTwokViewModel.setBgColor(color);
    }

    @Override
    public void onTextColorDialogPositiveClick(Dialog dialog, int color) {
        this.newTwokViewModel.setFontColor(color);
    }

    @Override
    public void onPermissionDialogPositiveClick(Dialog dialog) {
        askPermission();
    }

    @Override
    public void onSetLocationPositiveClick(Location location) {
        this.newTwokViewModel.setCoordinates(location);
    }

    @Override
    public void OnInvalidTwokSend() {
        Toast.makeText(requireContext(), R.string.Invalid_twok, Toast.LENGTH_SHORT).show();
    }
}