package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class TwokToShowBuffer implements Iterable<TwokToShow> {
    private final List<TwokToShow> recivedTwoks;

    @Inject
    public TwokToShowBuffer() {
        this.recivedTwoks = new LinkedList<>();
    }

    public TwokToShow getByPosition(int position) {
        return this.recivedTwoks.get(position);
    }

    public void insert(TwokToShow t) {
        Objects.requireNonNull(t, "twok cannot be null");
        this.recivedTwoks.add(t);
    }

    public int getlength() {
        return this.recivedTwoks.size();
    }

    public void reset() {
        this.recivedTwoks.clear();
    }

    @NonNull
    @Override
    public Iterator<TwokToShow> iterator() {
        return new Iterator<TwokToShow>() {
            final Iterator<TwokToShow> i = recivedTwoks.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public TwokToShow next() {
                return i.next();
            }
        };
    }
}
