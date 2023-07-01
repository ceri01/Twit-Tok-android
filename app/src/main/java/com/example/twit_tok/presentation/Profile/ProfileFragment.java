package com.example.twit_tok.presentation.Profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.twit_tok.R;
import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.databinding.FragmentHomeBinding;
import com.example.twit_tok.databinding.FragmentProfileBinding;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.utils.Colors;
import com.example.twit_tok.utils.Converters;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ListView l;
    @Inject
    ProfileViewModel profileViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        profileViewModel.addUser(12, "211", "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", 0);
        for (User u : profileViewModel.getUsers()) {
            System.out.println(u);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
/*        TwokDatabase t = TwokDatabase.getInstance(getActivity().getApplicationContext());
        ListenableFuture<Sid> s = t.getSidDao().getSid();
        s.addListener(() -> {
            try {
                Sid sid = s.get();
                System.out.println(sid);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, getActivity().getMainExecutor());*/
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ImageView iv = binding.imageView;
        String as = profileViewModel.getUsers().iterator().next().getPicture();
        System.out.println(as);
        if(Objects.isNull(as)) {
            iv.setImageResource(R.mipmap.ic_default_picture_round);
        } else {
            Bitmap b = Converters.fromBase64ToBitmap(as);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    b.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    iv.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(b);
                        }
                    });
                }
            }).start();

        }
/*



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