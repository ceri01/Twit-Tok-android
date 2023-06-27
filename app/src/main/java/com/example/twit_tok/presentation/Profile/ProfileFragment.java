package com.example.twit_tok.presentation.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.twit_tok.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ListView l;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // l = (ListView) binding.getRoot().getViewById(R.id.followedList);
        List<String> myList = new ArrayList<>();

        myList.add("Ciao");
        myList.add("come");
        myList.add("stai");
        myList.add("io");
        myList.add("bene");
        myList.add("e");
        myList.add("tu");


        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}