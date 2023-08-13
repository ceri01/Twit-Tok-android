package com.example.twit_tok.domain.model;

import androidx.annotation.NonNull;
import androidx.compose.ui.graphics.drawscope.Stroke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
        this.user = new LinkedHashMap<>();
    }

    public Users(Users users) {
        this.user = new LinkedHashMap<>();
        for (User u : users) {
            this.user.put(u.uid(), new User(u));
        }
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
        List<User> tmp = new ArrayList<User>(this.user.values());
        return tmp.get(position);
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

