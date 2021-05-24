package com.zhu.a13cscproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FoodChoice extends AppCompatActivity {
    TextView chips_text, hotdog_text, kebab_text, chips_qty, hotdog_qty, kebab_qty;
    Button  chips_minus, chips_plus, hotdog_minus, hotdog_plus, kebab_minus, kebab_plus;
    Integer chips_qty_num, hotdog_qty_num, kebab_qty_num;
    Integer test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice);

        chips_qty_num = 0;
        hotdog_qty_num = 0;
        kebab_qty_num = 0;
        test = 10;

        chips_text = findViewById(R.id.chips_text);
        chips_qty = findViewById(R.id.chips_qty);
        hotdog_text = findViewById(R.id.hotdog_text);
        hotdog_qty = findViewById(R.id.hotdog_qty);
        kebab_text = findViewById(R.id.kebab_text);
        kebab_qty = findViewById(R.id.kebab_qty);

        chips_minus = findViewById(R.id.chips_minus);
        chips_plus = findViewById(R.id.chips_plus);
        hotdog_minus = findViewById(R.id.hotdog_minus);
        hotdog_plus = findViewById(R.id.hotdog_plus);
        kebab_minus = findViewById(R.id.kebab_minus);
        kebab_plus = findViewById(R.id.kebab_plus);

        chips_qty.setText(String.valueOf(chips_qty_num));
        hotdog_qty.setText(String.valueOf(hotdog_qty_num));
        kebab_qty.setText(String.valueOf(kebab_qty_num));

//** Chips button **

        chips_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chips_qty_num <= 0){
                    Toast.makeText(FoodChoice.this, "you can't have less than 0 chips", Toast.LENGTH_LONG).show();
                }else{
                    chips_qty_num -= 1;
                    chips_qty.setText(Integer.toString(chips_qty_num));
                    Toast.makeText(FoodChoice.this,"yeet",Toast.LENGTH_SHORT).show();

            }}
        });

        chips_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chips_qty_num >= 10){
                    Toast.makeText(FoodChoice.this, "save some chips for others!", Toast.LENGTH_LONG).show();
                }else{
                    chips_qty_num += 1;
                    chips_qty.setText(Integer.toString(chips_qty_num));
                    Toast.makeText(FoodChoice.this,"yeet",Toast.LENGTH_SHORT).show();// test, remove
                }}
        });

//** Hotdog button **

        hotdog_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hotdog_qty_num <= 0){
                    Toast.makeText(FoodChoice.this, "you can't have less than 0 hotdog", Toast.LENGTH_LONG).show();
                }else{
                    hotdog_qty_num -= 1;
                    hotdog_qty.setText(Integer.toString(hotdog_qty_num));
                    Toast.makeText(FoodChoice.this,"yeet",Toast.LENGTH_SHORT).show();

                }}
        });

        hotdog_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hotdog_qty_num >= 10){
                    Toast.makeText(FoodChoice.this, "save some hotdogs for others!", Toast.LENGTH_LONG).show();
                }else{
                    hotdog_qty_num += 1;
                    hotdog_qty.setText(Integer.toString(hotdog_qty_num));
                }}
        });

//** kebab button **

        kebab_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kebab_qty_num <= 0){
                    Toast.makeText(FoodChoice.this, "you can't have less than 0 kebab", Toast.LENGTH_LONG).show();
                }else{
                    kebab_qty_num -= 1;
                    kebab_qty.setText(Integer.toString(kebab_qty_num));
                    Toast.makeText(FoodChoice.this,"yeet",Toast.LENGTH_SHORT).show();

                }}
        });

        kebab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kebab_qty_num >= 10){
                    Toast.makeText(FoodChoice.this, "save some kebabs for others!", Toast.LENGTH_LONG).show();
                }else{
                    kebab_qty_num += 1;
                    kebab_qty.setText(Integer.toString(kebab_qty_num));
                }}
        });






    }
}