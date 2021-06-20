package com.zhu.a13cscproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class FoodSpecifications extends AppCompatActivity {

    EditText food_qty, food_name, food_price, food_description;
    ImageButton add_input, subtract_input;
    Button add_to_sql_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_specifications);

        food_name = findViewById(R.id.food_name);
        food_price = findViewById(R.id.food_price);
        food_description = findViewById(R.id.food_description);
        food_qty = findViewById(R.id.food_qty);
        add_input = findViewById(R.id.add_btn);
        subtract_input = findViewById(R.id.subtract_btn);
        add_to_sql_btn = findViewById(R.id.update_sql_btn);
        add_to_sql_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDatabase foodDB = new FoodDatabase(FoodSpecifications.this);
                foodDB.addfood(
                        food_name.getText().toString().trim(),
                        Float.valueOf(food_price.getText().toString().trim()),
                        food_description.getText().toString().trim(),
                        Integer.valueOf(food_qty.getText().toString().trim())
                        );


            }
        });


    }
}