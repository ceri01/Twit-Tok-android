package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Twoks implements Iterable<Twok> {
    private final List<Twok> twoks;

    public Twoks() {
        this.twoks = new LinkedList<>();
    }

    public Twok getByPosition(int position) {
        return this.twoks.get(position);
    }

    public void insert(Twok t) {
        Objects.requireNonNull(t, "twok cannot be null");
        this.twoks.add(t);
    }

    public int getlength() {
        return this.twoks.size();
    }

    public void reset() {
        this.twoks.clear();
    }

    @NonNull
    @Override
    public Iterator<Twok> iterator() {
        return new Iterator<Twok>() {
            final Iterator<Twok> i = twoks.iterator();
            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public Twok next() {
                return i.next();
            }
        };
    }
}
