package com.example.examentecnico.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = Environment.getExternalStorageDirectory() + "/bd/respaldo.db";
    private static final String TABLE_USER = "tableUser";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USER + "(" + "usuario TEXT NOT NULL," + "pais TEXT NOT NULL," + "estado TEXT NOT NULL," + "genero TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_USER);
        onCreate(db);
    }
}
