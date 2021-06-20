package com.zhu.a13cscproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class FoodDatabase extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "FoodListLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "FoodList";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "food_name";
    private static final String COLUMN_PRICE = "food_price";
    private static final String COLUMN_QUANTITY = "food_qty"; //stick with string for now
    private static final String COLUMN_DESCRIPTION = "description";

    FoodDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //this is how a table in SQLite database is set up. Not a whole lot to be explained.
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_PRICE + " FLOAT, " +
                        COLUMN_QUANTITY + " INTEGER, " +
                        COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addfood(String name, float price, String description, int quantity){// todo HERE is how to add items, but since I didn't set items as edit text maybe I need a seperate activity just for developer edit.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_QUANTITY, quantity);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }

    }
}