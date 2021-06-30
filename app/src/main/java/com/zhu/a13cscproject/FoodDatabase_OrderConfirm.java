package com.zhu.a13cscproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class FoodDatabase_OrderConfirm extends SQLiteOpenHelper{

    private Context context;
    String food_qty_temp;
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

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME; //SQL query, select all from table_name, which is my database table.
        SQLiteDatabase db = this.getReadableDatabase(); //database object

        db.execSQL("DELETE FROM " + TABLE_NAME);



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

//    void OnlyQtyOverZero(){//didn't work, use something else. Maybe got to do with the way I called the database
//        getWritableDatabase().delete(TABLE_NAME, COLUMN_QUANTITY+"=?",new String[]{"0"});
//    }

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
