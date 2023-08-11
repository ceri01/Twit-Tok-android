package com.example.twit_tok.presentation.wall;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.twit_tok.R;
import com.example.twit_tok.common.TwoksUtils;
import com.example.twit_tok.databinding.FragmentWallBinding;
import com.example.twit_tok.domain.model.RecivedTwok;
import com.example.twit_tok.domain.model.RecivedTwoks;
import com.example.twit_tok.presentation.EventListener;
import com.google.android.material.button.MaterialButton;

import java.time.Duration;

public class WallFragment extends Fragment implements EventListener {
    private FragmentWallBinding binding;
    private ViewPager2 viewPager;
    private RecivedTwoks recivedTwoks = new RecivedTwoks();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WallViewModel wallViewModel =
                new ViewModelProvider(this).get(WallViewModel.class);

        recivedTwoks.insert(new RecivedTwok("a", 0, "000000", "AA00CC", 1, 1, 1, 1, "A", null, true, 12.0,45.34));
        recivedTwoks.insert(new RecivedTwok("b", 1, "AC34DD", "AA00CC", 2, 2, 1, 2, "B", null, false, 145.0,-78.34));
        recivedTwoks.insert(new RecivedTwok("c", 2, "FFFFFF", "AA00CC", 1, 3, 1, 3, "C", null, true));
        recivedTwoks.insert(new RecivedTwok("d", 0, "000000", "AA00CC", 1, 1, 2, 1, "D", null, true));
        recivedTwoks.insert(new RecivedTwok("e", 1, "FFFFFF", "AA00CC", 2, 2, 2, 2, "E", null, true));
        recivedTwoks.insert(new RecivedTwok("f", 2, "FFFFFF", "AA00CC", 1, 3, 2, 3, "F", null, false, 222.2342,45.34));
        recivedTwoks.insert(new RecivedTwok("g", 0, "000000", "AA00CC", 1, 1, 3, 1, "G", null, false));
        recivedTwoks.insert(new RecivedTwok("h", 1, "FFFFFF", "AA00CC", 2, 2, 3, 2, "H", null, false));
        recivedTwoks.insert(new RecivedTwok("i", 2, "FFFFFF", "AA00CC", 1, 3, 3, 3, "I", null, true));

        binding = FragmentWallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager = root.findViewById(R.id.wall);
        WallAdapter wa = new WallAdapter(this.getContext(), recivedTwoks, WallFragment.this);


        viewPager.setAdapter(wa);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

        });
        return root;
    }

    private void showDispayLocationDialog(Double latitude, Double longitude) {
        DialogFragment dialog = new DisplayLocationDialogFragment(latitude, longitude);
        dialog.show(getParentFragmentManager(), "DisplayLocationDialogFragment.java");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
    public void onFollowUnfollowButtonPressed(boolean followUnfollow, int uid) {
        if (followUnfollow) {
            // fai chiamata per seguire
        } else {
            // fai chiamata per smettere di seguire seguire
        }
    }
}