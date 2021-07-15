package com.zhu.a13cscproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class FoodDatabase_OrderConfirm extends SQLiteOpenHelper{

    //@@
    //This is very similar to CustomAdapter, comments there.
    //@@

    private static final String DATABASE_NAME = "FoodChosen.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "FoodChosen";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "food_name_1";
    private static final String COLUMN_PRICE = "food_price_1";
    private static final String COLUMN_QUANTITY = "food_qty_1"; //stick with string for now
    private static final String COLUMN_DESCRIPTION = "description_1";

    FoodDatabase_OrderConfirm(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME; //SQL query, select all from table_name, which is my database table.
        SQLiteDatabase db = this.getReadableDatabase(); //database object

        db.execSQL("DELETE FROM " + TABLE_NAME);



        Cursor cursor; //define cursor
            cursor = db.rawQuery(query, null);// all data in cursor
        return cursor;//not used, but I'll leave it here for future uses, when I can do something about the data.
    }

}
