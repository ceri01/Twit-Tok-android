package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

public class Users implements Iterable<User> {
    private final Map<Integer, User> user;

    @Inject
    public Users() {
        this.user = new LinkedHashMap<>();
    }

    public void insert(User t) {
        Objects.requireNonNull(t, "twok cannot be null");
        this.user.put(t.uid(), t);
    }

    public void remove(int uid) {
        User u = this.user.get(uid);
        this.user.remove(uid, u);
    }

    public int getlength() {
        return this.user.size();
    }

    public User getByPosition(int position) {
        List<User> tmp = new ArrayList<>(this.user.values());
        return tmp.get(position);
    }

    @NonNull
    @Override
    public Iterator<User> iterator() {
        return new Iterator<>() {
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

