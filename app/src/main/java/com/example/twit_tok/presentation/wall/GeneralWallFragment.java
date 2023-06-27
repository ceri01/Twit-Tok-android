package com.example.twit_tok.presentation.wall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.twit_tok.databinding.FragmentGeneralwallBinding;

public class GeneralWallFragment extends Fragment {

    private FragmentGeneralwallBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GeneralWallViewModel generalWallViewModel =
                new ViewModelProvider(this).get(GeneralWallViewModel.class);

        binding = FragmentGeneralwallBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        generalWallViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}