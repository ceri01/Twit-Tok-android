package com.example.twit_tok.presentation.Profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twit_tok.R;
import com.example.twit_tok.common.Converters;
import com.example.twit_tok.databinding.FragmentProfileBinding;
import com.example.twit_tok.presentation.NoticeDialogPictureListener;
import com.example.twit_tok.presentation.NoticeDialogTextListener;
import com.example.twit_tok.presentation.ProfileEventListener;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment implements NoticeDialogTextListener, NoticeDialogPictureListener, ProfileEventListener {

    private final NoticeDialogTextListener listener = null;
    @Inject
    ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FollowedAdapter fa = new FollowedAdapter(requireContext(), profileViewModel.getUsers());

        Button editNameButton = root.findViewById(R.id.edit_name);
        Button editPictureButton = root.findViewById(R.id.edit_picture);

        TextView nameView = root.findViewById(R.id.name);
        ImageView pictureView = root.findViewById(R.id.picture);

        profileViewModel.getProfileNameChanged().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String name) {
                nameView.setText(name);
            }
        });

        profileViewModel.getProfilePictureChanged().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap picture) {
                if (Objects.isNull(picture)) {
                    pictureView.setImageResource(R.mipmap.ic_default_picture_round);
                } else {
                    pictureView.setImageBitmap(picture);
                }
            }
        });

        editNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeTextDialog();
            }
        });

        editPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePictureDialog();
            }
        });

        RecyclerView rc = binding.followedList.findViewById(R.id.followed_list);
        rc.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rc.setAdapter(fa);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void showChangeTextDialog() {
        DialogFragment dialog = new EditTextDialogFragment(ProfileFragment.this);
        dialog.show(getParentFragmentManager(), "EditTextDialogFragment");
    }

    public void showChangePictureDialog() {
        DialogFragment dialog = new EditPictureDialogFragment(ProfileFragment.this, profileViewModel.getProfilePictureChanged().getValue());
        dialog.show(getParentFragmentManager(), "EditPictureDialogFragment");
    }

    @Override
    public void onTextDialogPositiveClick(DialogFragment dialog, String value) {
        this.profileViewModel.setProfileName(value);
        TextView t = binding.getRoot().findViewById(R.id.name);
        t.setText(value);
    }

    @Override
    public void onPictureDialogPositiveClick(DialogFragment dialog, Bitmap value) {
        ImageView i = binding.getRoot().findViewById(R.id.picture);
        if (Objects.isNull(value)) {
            i.setImageResource(R.mipmap.ic_default_picture_round);
            profileViewModel.setProfilePicture("");
        } else {
            i.setImageBitmap(value);
            profileViewModel.setProfilePicture(Converters.fromBitmapToBase64(value));
        }
    }

    @Override
    public void onUnfollowButtonPressed(int uid) {
        profileViewModel.unfollow(uid);
    }
}