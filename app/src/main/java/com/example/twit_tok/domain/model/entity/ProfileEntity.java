package com.example.twit_tok.domain.model.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfileEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public Bitmap picture;

    @ColumnInfo(name = "pversion")
    public int pversion;
}

