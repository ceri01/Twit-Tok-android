package com.example.twit_tok.presentation.NewTwok;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.twit_tok.domain.model.NewTwok;
import com.example.twit_tok.common.Colors;

import java.util.Objects;

import javax.inject.Inject;

public class NewTwokViewModel extends ViewModel {
    private final NewTwok twok;

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
        this.hAlign = new MutableLiveData<>(newTwok.getHAlign());
        this.vAlign = new MutableLiveData<>(newTwok.getVAlign());
        this.fontType = new MutableLiveData<>(newTwok.getFontType());
        this.fontSize = new MutableLiveData<>(newTwok.getFontSize());
        this.bgColor = new MutableLiveData<>(Colors.fromHexStringToInt(newTwok.getBgColor()));
        this.fontColor = new MutableLiveData<>(Colors.fromHexStringToInt(newTwok.getFontColor()));
    }

    private Integer nextChoice(Integer choice) {
        return 1 + (choice % 3);
    }

    public String getText() {
        return twok.getText();
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
        var value = nextChoice(twok.getHAlign());
        this.twok.setHAlign(value);
        this.hAlign.setValue(value);
    }

    public MutableLiveData<Integer> onVAlignChange() {
        return this.vAlign;
    }

    public void setVAlign() {
        var value = nextChoice(twok.getVAlign());
        this.twok.setVAlign(value);
        this.vAlign.setValue(value);
    }

    public MutableLiveData<Integer> onFontTypeChange() {
        return this.fontType;
    }

    public void setFontType() {
        var value = nextChoice(twok.getFontType());
        this.twok.setFontType(value);
        this.fontType.setValue(value);
    }

    public MutableLiveData<Integer> onFontSizeChange() {
        return this.fontSize;
    }

    public void setFontSize() {
        var value = nextChoice(twok.getFontSize());
        this.twok.setFontSize(value);
        this.fontSize.setValue(value);
    }

    public MutableLiveData<Integer> onBgColorChange() {
        return this.bgColor;
    }

    public void setBgColor(Integer color) {
        this.twok.setBgColor(Colors.fromIntToHexString(color));
        this.bgColor.setValue(color);
    }

    public MutableLiveData<Integer> onFontColorChange() {
        return this.fontColor;
    }

    public void setFontColor(Integer color) {
        this.twok.setFontColor(Colors.fromIntToHexString(color));
        this.fontColor.setValue(color);
    }

    public void resetTwok() {
        this.twok.reset();
        this.bgColor.setValue(Colors.fromHexStringToInt(twok.getBgColor()));
        this.fontColor.setValue(Colors.fromHexStringToInt(twok.getFontColor()));
        this.twokText.setValue(twok.getText());
    }
}