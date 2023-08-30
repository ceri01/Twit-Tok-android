package com.example.twit_tok.presentation.wall;

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

import com.example.twit_tok.R;
import com.example.twit_tok.common.TwoksUtils;
import com.example.twit_tok.databinding.FragmentWallBinding;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.presentation.WallEventListener;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WallFragment extends Fragment implements WallEventListener {
    private final Handler handler = new Handler();
    @Inject
    WallViewModel wallViewModel;
    private FragmentWallBinding binding;
    private ProgressBar progressBar;
    private WallAdapter wa;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (wallViewModel.getBufferLength() <= 0) {
            wallViewModel.getTwoks();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = root.findViewById(R.id.buffering);

        ViewPager2 viewPager = root.findViewById(R.id.wall);
        this.wa = new WallAdapter(requireContext(), wallViewModel.getBuffer(), WallFragment.this);
        viewPager.setAdapter(wa);

        wallViewModel.isReady().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer position) {
                wa.notifyItemInserted(position);
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
                if (position >= 50 && wallViewModel.getBufferLength() >= 50) {
                    showBufferingWhileLoading();
                    wallViewModel.resetBuffer();
                    wa.resetTwokBuffer(wallViewModel.getBuffer());
                    wa.notifyDataSetChanged();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hideBufferingWhenLoaded();
                        }
                    }, 3000);
                } else if (position >= (wallViewModel.getBufferLength() - 3)) {
                    wallViewModel.getTwoks();
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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

    private void showBufferingWhileLoading() {
        binding.getRoot().findViewById(R.id.wall).setVisibility(View.GONE);
        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideBufferingWhenLoaded() {
        progressBar.setVisibility(View.GONE);
        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
        binding.getRoot().findViewById(R.id.wall).setVisibility(View.VISIBLE);
    }

    @Override
    public void onFollowButtonPressed(User user) {
        wallViewModel.followUser(user);
    }

    @Override
    public void onUnfollowButtonPressed(int uid) {
        wallViewModel.unfollowUser(uid);
    }
}