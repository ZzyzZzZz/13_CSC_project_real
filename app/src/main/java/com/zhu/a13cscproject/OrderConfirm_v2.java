package com.zhu.a13cscproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class OrderConfirm_v2 extends AppCompatActivity {

    RecyclerView recyclerview;
    FloatingActionButton to_senddata_btn, to_addfood_fbtn;
    GlobalClass gc; //for dev mode
    Integer dev_mode;// for dev mode

    FoodDatabase foodDB; //get my database
    ArrayList<String> food_id, food_name, food_price, food_qty, food_description;
    CustomAdapter_OrderConfirm customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm_v2);

//        GlobalClass globalClass = (GlobalClass)getApplicationContext(); //dev mode
//        this.gc = globalClass;
//        dev_mode = gc.getDev_mode();

        recyclerview = findViewById(R.id.food_recyclerview);
        to_senddata_btn = findViewById(R.id.to_cart);
        to_senddata_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirm_v2.this, SendData.class);
                startActivity(intent);
            }
        });
//        to_addfood_fbtn = findViewById(R.id.to_addfood);//this option will only show up if dev_mode is enabled.
//        if (dev_mode == 1){//this hide function don't work perfectly and I am fine with it, I am the only person needed to know how to use it...
//            to_addfood_fbtn.show();
//        }else{
//            to_addfood_fbtn.hide();
//        }
//
//        to_addfood_fbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(OrderConfirm_v2.this, FoodSpecifications.class);
//                startActivity(intent);
//
//            }
//        });


        foodDB = new FoodDatabase(OrderConfirm_v2.this); // here are my variables for RecyclerView
        food_id = new ArrayList<>();
        food_name = new ArrayList<>();
        food_price = new ArrayList<>();
        food_qty = new ArrayList<>();
        food_description = new ArrayList<>();

        storeDataArrays();

        customAdapter = new CustomAdapter_OrderConfirm(OrderConfirm_v2.this, this, food_id, food_name, food_price, food_qty, food_description);
        recyclerview.setAdapter(customAdapter);//set adapter to my own adapter
        recyclerview.setLayoutManager(new LinearLayoutManager(OrderConfirm_v2.this));//linear layout for a vertical scroll
        //what is LayoutManager? See link below for description:
        //https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.LayoutManager
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataArrays(){// get the data from SQL and add them to the predefined variables that holds these values.
        Cursor cursor = foodDB.readAllData();
        if(cursor.getCount() == 0) {//if getCount() == 0 then there are no data
            Toast.makeText(this, "No Food data, please load SQLdataset", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                food_id.add(cursor.getString(0)); //column count, basically arrange where they are in SQLite.
                food_name.add(cursor.getString(1));
                food_price.add(cursor.getString(2));
                food_qty.add(cursor.getString(3));
                food_description.add(cursor.getString(4));
            }
        }
    }
}