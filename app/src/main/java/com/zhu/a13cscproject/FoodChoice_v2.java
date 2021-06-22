package com.zhu.a13cscproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class FoodChoice_v2 extends AppCompatActivity {

    RecyclerView recyclerview;
    FloatingActionButton to_cart_fbtn, to_addfood_fbtn;

    FoodDatabase foodDB; //get my database
    ArrayList<String> food_id, food_name, food_price, food_qty, food_description;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice_v2);

        recyclerview = findViewById(R.id.food_recyclerview);
        to_cart_fbtn = findViewById(R.id.to_cart);
        to_cart_fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodChoice_v2.this, OrderConfirm.class);
                startActivity(intent);
            }
        });
        to_addfood_fbtn = findViewById(R.id.to_addfood);
        to_addfood_fbtn.show(); // TODO REMEMBER TO CHANGE TO HIDE IN FINAL VERSION. For current testing purpose I will leave it showing.
        to_addfood_fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodChoice_v2.this, FoodSpecifications.class);
                startActivity(intent);

            }
        });

        foodDB = new FoodDatabase(FoodChoice_v2.this); // here are my variables for RecyclerView
        food_id = new ArrayList<>();
        food_name = new ArrayList<>();
        food_price = new ArrayList<>();
        food_qty = new ArrayList<>();
        food_description = new ArrayList<>();

        storeDataArrays();

        customAdapter = new CustomAdapter(this, food_id, food_name, food_price, food_qty, food_description);
        recyclerview.setAdapter(customAdapter);//set adapter to my own adapter
        recyclerview.setLayoutManager(new LinearLayoutManager(FoodChoice_v2.this));//linear layout for a vertical scroll
        //what is LayoutManager? See link below for description:
        //https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.LayoutManager
    }

    void storeDataArrays(){// get the data from SQL and add them to the predefined variables that holds these values.
        Cursor cursor = foodDB.readAllData();
        if(cursor.getCount() == 0) {//if getCount() == 0 then there are no data
            Toast.makeText(this, "No Food data, please load SQLdataset", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                food_id.add(cursor.getString(0)); //column count, basically arrange where they are.
                food_name.add(cursor.getString(1));
                food_price.add(cursor.getString(2));
                food_qty.add(cursor.getString(3));
                food_description.add(cursor.getString(4));
            }
        }
    }
}