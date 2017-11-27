package com.bender.mypantry.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bender.mypantry.model.Pantry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bender on 21/11/17.
 */

public class PantryDAO extends SQLiteOpenHelper {


    public PantryDAO(Context context) {
        super(context, "pantry", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE pantry (id INTEGER PRIMARY KEY, name TEXT, supermarket TEXT) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql= "DROP TABLE IF EXISTS pantry";
        db.execSQL(sql);
        onCreate(db);
    }

    public void create(Pantry pantry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContentValues(pantry);
        db.insert("pantry", null, data);
    }

    public List<Pantry> readListPantry(){
        String sql = "SELECT * FROM pantry";
        SQLiteDatabase db = getReadableDatabase();

        List<Pantry> pantries = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            Pantry pantry = new Pantry();
            pantry.setId(cursor.getLong(cursor.getColumnIndex("id")));
            pantry.setName(cursor.getString(cursor.getColumnIndex("name")));
            pantry.setSupermarket(cursor.getString(cursor.getColumnIndex("supermarket")));
            pantries.add(pantry);
        }
        cursor.close();
        return pantries;
    }

    public void update(Pantry pantry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContentValues(pantry);
        String[] params = new String[] { pantry.getId().toString() };
        db.update("pantry", data, "id = ?", params);
    }

    public void delete(Pantry pantry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContentValues(pantry);
        String[] params = new String[] { pantry.getId().toString() };
        db.update("pantry", data, "id = ?", params);
    }

    private ContentValues getContentValues(Pantry pantry) {
        ContentValues data = new ContentValues();
        data.put("id", pantry.getId());
        data.put("name", pantry.getName());
        data.put("supermarket", pantry.getSupermarket());
        return data;
    }
}
