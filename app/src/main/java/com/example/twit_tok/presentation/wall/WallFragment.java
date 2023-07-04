package com.example.twit_tok.presentation.wall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.twit_tok.R;
import com.example.twit_tok.databinding.FragmentWallBinding;
import com.example.twit_tok.domain.model.Twok;
import com.example.twit_tok.domain.model.Twoks;
import com.example.twit_tok.utils.PictureUtils;

import java.util.List;

public class WallFragment extends Fragment {

    private FragmentWallBinding binding;
    private ViewPager2 viewPager;
    private Twoks twoks = new Twoks();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WallViewModel wallViewModel =
                new ViewModelProvider(this).get(WallViewModel.class);

        twoks.insert(new Twok("Ciao", 0, "#AAAAAA", "#AAAAAA", 0, 0, 0, 0, "A", PictureUtils.getDefaultPicture()));
        twoks.insert(new Twok("Come", 1, "#AAAAAA", "#AAAAAA", 0, 0, 0, 0, "B", PictureUtils.getDefaultPicture()));
        twoks.insert(new Twok("va", 2, "#AAAAAA", "#AAAAAA", 0, 0, 0, 0, "C", PictureUtils.getDefaultPicture()));

        binding = FragmentWallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager = root.findViewById(R.id.wall);
        WallAdapter wa = new WallAdapter(this.getContext(), twoks);

        viewPager.setAdapter(wa);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Log.d("Main", "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("Main", "onPageSelected");
            }

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}