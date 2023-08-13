package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class RecivedTwoksBuffer implements Iterable<RecivedTwok> {
    private final List<RecivedTwok> recivedTwoks;

    public RecivedTwoksBuffer() {
        this.recivedTwoks = new LinkedList<>();
    }

    public RecivedTwok getByPosition(int position) {
        return this.recivedTwoks.get(position);
    }

    public void insert(RecivedTwok t) {
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
    public Iterator<RecivedTwok> iterator() {
        return new Iterator<RecivedTwok>() {
            final Iterator<RecivedTwok> i = recivedTwoks.iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public RecivedTwok next() {
                return i.next();
            }
        };
    }
}
