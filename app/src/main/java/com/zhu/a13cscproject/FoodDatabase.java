package com.zhu.a13cscproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class FoodDatabase extends SQLiteOpenHelper{

    private final Context context;//declaring variables
    private static final String DATABASE_NAME = "FoodListLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "FoodList";//field names. Just for me though.
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

    void addFood(String name, float price, String description, int quantity){//HERE is how to add items
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);//put values into database in the .insert below.
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

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME; //SQL query, select all from table_name, which is my database table.
        SQLiteDatabase db = this.getReadableDatabase(); //database object


        Cursor cursor = null; //define cursor
        if(db != null) { //not null means there are some data
            cursor = db.rawQuery(query, null);// all data in cursor
        }
//
//        }else{f
//            Toast.makeText(context, "failed to obtain data from SQLite. Check if data is available", Toast.LENGTH_SHORT).show(); // catch error
//        }
        return cursor;

    }

    void updateData(String row_id, String qty){// for users, add or subtract qty of food items
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUANTITY, qty);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update SQLite database", Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(context, "Changes saved", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    void deleteOneRow(String row_id){ //to delete data in dev mode, add or subtract food items
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
