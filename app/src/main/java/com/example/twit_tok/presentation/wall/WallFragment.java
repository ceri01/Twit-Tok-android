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

        twoks.insert(new Twok("a", 0, "000000", "AA00CC", 1, 1, 1, 1, "A", null));
        twoks.insert(new Twok("b", 1, "FFFFFF", "AA00CC", 2, 2, 1, 2, "B", null));
        twoks.insert(new Twok("c", 2, "FFFFFF", "AA00CC", 1, 3, 1, 3, "C", null));
        twoks.insert(new Twok("d", 0, "000000", "AA00CC", 1, 1, 2, 1, "D", null));
        twoks.insert(new Twok("e", 1, "FFFFFF", "AA00CC", 2, 2, 2, 2, "E", null));
        twoks.insert(new Twok("f", 2, "FFFFFF", "AA00CC", 1, 3, 2, 3, "F", null));
        twoks.insert(new Twok("g", 0, "000000", "AA00CC", 1, 1, 3, 1, "G", null));
        twoks.insert(new Twok("h", 1, "FFFFFF", "AA00CC", 2, 2, 3, 2, "H", null));
        twoks.insert(new Twok("i", 2, "FFFFFF", "AA00CC", 1, 3, 3, 3, "I", null));

        binding = FragmentWallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager = root.findViewById(R.id.wall);
        WallAdapter wa = new WallAdapter(this.getContext(), twoks);
        System.out.println(wa);

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