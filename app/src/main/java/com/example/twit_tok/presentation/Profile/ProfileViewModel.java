package com.example.twit_tok.presentation.Profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.App;
import com.example.twit_tok.R;
import com.example.twit_tok.domain.model.User;
import com.example.twit_tok.domain.model.Users;
import com.example.twit_tok.utils.Converters;
import com.example.twit_tok.utils.PictureConditions;

import java.util.Objects;

import javax.inject.Inject;


public class ProfileViewModel extends ViewModel {
    private Users users;
    private User profile;

    @Inject
    public ProfileViewModel(Users users) {
        Objects.requireNonNull(users, "Given model is null");
        this.users = users;
    }

    public void addUser(int uid, String name, String picture, int pversion, boolean isFollowed) {
        byte[] arr = Converters.fromBase64ToByte(picture);
        if (!PictureConditions.isValidFormat(picture)) {
            users.insert(new User(uid, name, null, pversion, isFollowed));
        } else {
            users.insert(new User(uid, name, picture, pversion, isFollowed));
        }
    }

    public Users getUsers() {
        return users;
    }
}