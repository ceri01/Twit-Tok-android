package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.inject.Named;

public class TwokToShowBuffer implements Iterable<TwokToShow> {
    private final Map<Integer, TwokToShow> recivedTwoks;

    @Inject
    public TwokToShowBuffer() {
        this.recivedTwoks = new LinkedHashMap<>();
    }

    public TwokToShow getByPosition(int position) {
        return (TwokToShow) this.recivedTwoks.values().toArray()[position];
    }

    public void insert(TwokToShow t) {
        Objects.requireNonNull(t, "twok cannot be null");
        this.recivedTwoks.put(t.getTid(), t);
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
        return new Iterator<>() {
            final Iterator<TwokToShow> i = recivedTwoks.values().iterator();

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
