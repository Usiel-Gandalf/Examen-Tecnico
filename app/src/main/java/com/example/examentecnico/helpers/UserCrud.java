package com.example.examentecnico.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.examentecnico.db.DbHelper;

public class UserCrud extends DbHelper {

    Context context;

    public UserCrud(@Nullable Context context) {
        super(context);
        this.context = context;
    }



    public long insertUser(String usuario, String pais, String estado, String genero){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("usuario", usuario);
            contentValues.put("pais", pais);
            contentValues.put("estado", estado);
            contentValues.put("genero", genero);
            id = db.insert("tableUser", null, contentValues);
        }catch (Exception e){
            e.toString();
        }

        return id;
    }
}
