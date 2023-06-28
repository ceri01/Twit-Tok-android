package com.example.twit_tok.presentation.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.databinding.FragmentProfileBinding;
import com.example.twit_tok.domain.Sid;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ListView l;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("TEST", getActivity().getApplicationContext().toString());
        TwokDatabase t = TwokDatabase.getInstance(getActivity().getApplicationContext());
        ListenableFuture<Sid> s = t.getSidDao().getSid();
        s.addListener(() -> {
            try {
                Sid sid = s.get();
                System.out.println(sid);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, getActivity().getMainExecutor());
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
/*
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
*/

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}