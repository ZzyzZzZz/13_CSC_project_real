package com.zhu.a13cscproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FoodChoice_v2 extends AppCompatActivity {

    RecyclerView recyclerview;
    FloatingActionButton to_cart_fbtn, to_addfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice_v2);

        recyclerview = findViewById(R.id.food_recyclerview);
        to_cart_fbtn = findViewById(R.id.to_cart);
        to_addfood = findViewById(R.id.to_addfood);
        to_addfood.show(); // TODO REMEMBER TO CHANGE TO HIDE IN FINAL VERSION. For current testing purpose I will leave it showing.
        to_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodChoice_v2.this, FoodSpecifications.class);
                startActivity(intent);
            }
        });


    }
}