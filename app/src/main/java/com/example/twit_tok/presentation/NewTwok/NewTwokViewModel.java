package com.example.twit_tok.presentation.NewTwok;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.App;
import com.example.twit_tok.common.Colors;
import com.example.twit_tok.common.TwoksUtils;
import com.example.twit_tok.data.db.TwokDatabase;
import com.example.twit_tok.data.repository.TwokRepositoryImpl;
import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.domain.model.Sid;
import com.example.twit_tok.domain.requests.AddTwokRequest;
import com.example.twit_tok.presentation.NewTwokEventListener;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.Objects;

import javax.inject.Inject;

public class NewTwokViewModel extends ViewModel {
    private final NewTwok twok;
    private final TwokRepositoryImpl twokRepository = new TwokRepositoryImpl();

    private final MutableLiveData<String> twokText;
    private final MutableLiveData<Integer> hAlign;
    private final MutableLiveData<Integer> vAlign;
    private final MutableLiveData<Integer> fontType;
    private final MutableLiveData<Integer> fontSize;
    private final MutableLiveData<Integer> bgColor;
    private final MutableLiveData<Integer> fontColor;

    @Inject
    public NewTwokViewModel(@NonNull NewTwok newTwok) {
        this.twok = newTwok;
        this.twokText = new MutableLiveData<>(newTwok.getText());
        this.hAlign = new MutableLiveData<>(newTwok.getHalign());
        this.vAlign = new MutableLiveData<>(newTwok.getValign());
        this.fontType = new MutableLiveData<>(newTwok.getFonttype());
        this.fontSize = new MutableLiveData<>(newTwok.getFontsize());
        this.bgColor = new MutableLiveData<>(Colors.fromHexStringToInt(newTwok.getBgcol()));
        this.fontColor = new MutableLiveData<>(Colors.fromHexStringToInt(newTwok.getFontcol()));
    }

    private Integer nextChoice(Integer choice) {
        if (choice == 2) return 0;
        return 1 + (choice % 2);
    }

    public MutableLiveData<String> onChangeText() {
        return this.twokText;
    }

    public void setTwokText(String text) {
        if (Objects.isNull(text) || text.isBlank()) this.twok.setText("New Twok");
        if (text.length() > 99) this.twok.setText(text.substring(0, 98));
        this.twokText.setValue(text);
        this.twok.setText(text);
    }

    public MutableLiveData<Integer> onHAlignChange() {
        return this.hAlign;
    }

    public void setHAlign() {
        var value = nextChoice(twok.getHalign());
        this.twok.setHalign(value);
        this.hAlign.setValue(value);
    }

    public MutableLiveData<Integer> onVAlignChange() {
        return this.vAlign;
    }

    public void setVAlign() {
        var value = nextChoice(twok.getValign());
        this.twok.setValign(value);
        this.vAlign.setValue(value);
    }

    public MutableLiveData<Integer> onFontTypeChange() {
        return this.fontType;
    }

    public void setFontType() {
        var value = nextChoice(twok.getFonttype());
        this.twok.setFonttype(value);
        this.fontType.setValue(value);
    }

    public MutableLiveData<Integer> onFontSizeChange() {
        return this.fontSize;
    }

    public void setFontSize() {
        var value = nextChoice(twok.getFontsize());
        this.twok.setFontsize(value);
        this.fontSize.setValue(value);
    }

    public MutableLiveData<Integer> onBgColorChange() {
        return this.bgColor;
    }

    public void setBgColor(Integer color) {
        this.twok.setBgcol(Colors.fromIntToHexString(color));
        this.bgColor.setValue(color);
    }

    public MutableLiveData<Integer> onFontColorChange() {
        return this.fontColor;
    }

    public void setFontColor(Integer color) {
        this.twok.setFontcol(Colors.fromIntToHexString(color));
        this.fontColor.setValue(color);
    }

    public void setCoordinates(Location location) {
        if (!Objects.isNull(location)) {
            this.twok.setLat(location.getLatitude());
            this.twok.setLon(location.getLongitude());
        }
    }

    public void resetTwok() {
        this.twok.reset();
        this.bgColor.setValue(Colors.fromHexStringToInt(twok.getBgcol()));
        this.fontColor.setValue(Colors.fromHexStringToInt(twok.getFontcol()));
        this.twokText.setValue(twok.getText());
    }

    public void addTwok(NewTwokEventListener listener) {
        if (TwoksUtils.isValidTwokToSend(twok)) {
            ListenableFuture<Sid> lfSid = TwokDatabase.getInstance(App.getInstance().getApplicationContext()).getSidDao().getSid();
            lfSid.addListener(() -> {
                try {
                    Sid sid = new Sid(lfSid.get().sid());
                    AddTwokRequest atr = new AddTwokRequest(sid.sid(), twok);
                    twokRepository.addTwok(atr);
                } catch (Exception e) {
                    // TODO: gestisci errore in caso non ri riesca a prendere sid da db
                }
            }, App.getInstance().getMainExecutor());
        } else {
            listener.OnInvalidTwokSend();
        }
    }
}