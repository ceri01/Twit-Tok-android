package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;
import androidx.compose.ui.graphics.drawscope.Stroke;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import dagger.Module;

public class Users implements Iterable<User> {
    private final Map<Integer, User> user;

    @Inject
    public Users() {
        this.user = new HashMap<>();
    }

    public void insert(User t) {
        Objects.requireNonNull(t, "twok cannot be null");
        this.user.put(t.uid(), t);
    }

    public int getlength() {
        return this.user.size();
    }

    public void reset() {
        this.user.clear();
    }

    @NonNull
    @Override
    public Iterator<User> iterator() {
        return new Iterator<User>() {
            final Iterator<User> i = user.values().iterator();
            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public User next() {
                return i.next();
            }
        };
    }
}

