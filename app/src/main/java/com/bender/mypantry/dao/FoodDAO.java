package com.bender.mypantry.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.bender.mypantry.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Bender on 19/11/2017.
 */

public class FoodDAO extends SQLiteOpenHelper {
    public FoodDAO(Context context) {
        super(context, "food", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE food (id INTEGER PRIMARY KEY, name TEXT, value REAL DEFAULT 0, qtd REAL DEFAULT 0, type TEXT, pantryId INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS food";
        db.execSQL(sql);
        onCreate(db);
    }

    public void create(Food food) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContentValues(food);
        db.insert("Food", null, data);
    }

    public List<Food> readListFood(Long pantryId) {
        String sql = "SELECT * FROM Food";
        String[] params = null;

        if (pantryId != null) {
            sql += " Where pantryId = ? ";
            params = new String[] { pantryId.toString() };
        }

        SQLiteDatabase db = getReadableDatabase();

        List<Food> listFood = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, params);
        while(cursor.moveToNext()) {
            Food food = new Food();
            food.setId(cursor.getLong(cursor.getColumnIndex("id")));
            food.setName(cursor.getString(cursor.getColumnIndex("name")));
            food.setQtd(cursor.getDouble(cursor.getColumnIndex("qtd")));
            food.setType(cursor.getString(cursor.getColumnIndex("type")));
            food.setValue(cursor.getDouble(cursor.getColumnIndex("value")));
            food.setPantryId(cursor.getLong(cursor.getColumnIndex("pantryId")));
            listFood.add(food);
        }
        cursor.close();
        return listFood;
    }

    public void update(Food food) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContentValues(food);
        String[] params = new String[]{ food.getId().toString() };
        db.update("Food", data, "id = ? ", params);
    }

    public void delete(Food food) {
        SQLiteDatabase db = getReadableDatabase();
        String[] params = new String[] { food.getId().toString() };
        db.delete("Food", "id = ?", params);
    }

    @NonNull
    private ContentValues getContentValues(Food food) {
        ContentValues data = new ContentValues();
        data.put("name", food.getName());
        data.put("value", food.getValue());
        data.put("qtd", food.getQtd());
        data.put("type", food.getType());
        data.put("pantryId", food.getPantryId());
        return data;
    }
}