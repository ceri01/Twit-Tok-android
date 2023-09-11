package com.example.twit_tok.presentation.user_wall;

import static androidx.navigation.Navigation.findNavController;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.twit_tok.MainActivity;
import com.example.twit_tok.R;
import com.example.twit_tok.common.TwoksUtils;
import com.example.twit_tok.databinding.FragmentUserWallBinding;
import com.example.twit_tok.presentation.UserWallEventListener;
import com.example.twit_tok.presentation.wall.DisplayLocationDialogFragment;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserWallFragment extends Fragment implements UserWallEventListener {
    private final Handler handler = new Handler();
    @Inject
    UserWallViewModel userWallViewModel;
    private FragmentUserWallBinding binding;
    private ProgressBar progressBar;
    private UserWallAdapter wa;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (userWallViewModel.getBufferLength() <= 0) {
            userWallViewModel.getTwoks(this.requireArguments().getInt("uid"));
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserWallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.buffering);

        int uid = this.requireArguments().getInt("uid");

        MaterialButton offlineButton = root.findViewById(R.id.offline_button);

        ViewPager2 viewPager = root.findViewById(R.id.userwall_viewpager);
        this.wa = new UserWallAdapter(requireContext(), userWallViewModel.getBuffer(), UserWallFragment.this);
        viewPager.setAdapter(wa);

        userWallViewModel.isReady().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                wa.notifyItemInserted(position);
            }
        });

        userWallViewModel.isOffline().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isOffline) {
                root.findViewById(R.id.userwall_viewpager).setVisibility(View.GONE);
                root.findViewById(R.id.buffering_userwall).setVisibility(View.GONE);
                root.findViewById(R.id.offline_userwall).setVisibility(View.VISIBLE);
            }
        });

        offlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).restart();
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position >= 50 && userWallViewModel.getBufferLength() >= 50) {
                    showBufferingWhileLoading();
                    userWallViewModel.resetBuffer(uid);
                    wa.resetTwokBuffer(userWallViewModel.getBuffer());
                    wa.notifyDataSetChanged();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideBufferingWhenLoaded();
                        }
                    }, 3000);
                } else if (position >= (userWallViewModel.getBufferLength() - 3)) {
                    userWallViewModel.getTwoks(uid);
                }
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        userWallViewModel.emptyBuffer();
    }

    private void showBufferingWhileLoading() {
        binding.getRoot().findViewById(R.id.wall_viewpager).setVisibility(View.GONE);
        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideBufferingWhenLoaded() {
        progressBar.setVisibility(View.GONE);
        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        binding.getRoot().findViewById(R.id.wall_viewpager).setVisibility(View.VISIBLE);
    }

    private void showDispayLocationDialog(Double latitude, Double longitude) {
        DialogFragment dialog = new DisplayLocationDialogFragment(latitude, longitude);
        dialog.show(getParentFragmentManager(), "DisplayLocationDialogFragment.java");
    }

    @Override
    public void onMapButtonPressed(Double latitude, Double longitude) {
        if (TwoksUtils.isValidCoord(latitude, longitude)) {
            showDispayLocationDialog(latitude, longitude);
        } else {
            Toast.makeText(requireContext(), R.string.coordinates_not_available, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackButtonPressed() {
        findNavController(this.requireView()).popBackStack();
    }
}
