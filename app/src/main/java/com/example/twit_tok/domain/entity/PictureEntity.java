package com.example.twit_tok.domain.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PictureEntity {
    @PrimaryKey
    public int uid;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public Bitmap picture;

    @ColumnInfo(name = "pversion")
    public int pversion;
}
