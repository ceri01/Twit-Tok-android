package com.example.twit_tok.presentation.wall;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    @Inject
    WallViewModel wallViewModel;
    private FragmentWallBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        wallViewModel.getTwoks();
        ViewPager2 viewPager = root.findViewById(R.id.wall);
        WallAdapter wa = new WallAdapter(requireContext(), wallViewModel.getBuffer(), WallFragment.this);
        viewPager.setAdapter(wa);

        wallViewModel.isReady().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isReady) {
                if (isReady) {
                    wa.notifyDataSetChanged();
                }
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
/*                if (wallViewModel.getBufferLength() >= 100) {
                    wallViewModel.resetBuffer();
                } else if (position > (wallViewModel.getBufferLength() / 2) + 1) {
                    wallViewModel.getNewTwoks();
                }*/
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
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

    @Override
    public boolean onFollowButtonPressed(User user) {
        return wallViewModel.followUser(user);
    }

    @Override
    public void onUnfollowButtonPressed(int uid) {
        wallViewModel.unfollowUser(uid);
    }
}