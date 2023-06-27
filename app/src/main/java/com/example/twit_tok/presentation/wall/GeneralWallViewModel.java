package com.example.twit_tok.presentation.wall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GeneralWallViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GeneralWallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}