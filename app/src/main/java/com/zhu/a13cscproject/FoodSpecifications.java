package com.zhu.a13cscproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class FoodSpecifications extends AppCompatActivity {

    EditText food_qty, food_name, food_price, food_description;//declaring variables
    ImageButton add_input, subtract_input;
    Button add_to_sql_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_specifications);//layout file

        food_name = findViewById(R.id.food_name);//find all the things in layout.xml
        food_price = findViewById(R.id.food_price);
        food_description = findViewById(R.id.food_description);
        food_qty = findViewById(R.id.food_qty);

        add_input = findViewById(R.id.add_btn);
        subtract_input = findViewById(R.id.subtract_btn);//these buttons will not have anything else than onclick function, therefore unused.

        add_to_sql_btn = findViewById(R.id.update_sql_btn);
        add_to_sql_btn.setOnClickListener(v -> {//if button pressed add recorded information to sqlite database
            FoodDatabase foodDB = new FoodDatabase(FoodSpecifications.this);//calling add_food function to add food to database

            try{
                foodDB.addFood(
                        food_name.getText().toString().trim(),//collect all information entered by intended personal.
                        Float.parseFloat(food_price.getText().toString().trim()),
                        food_description.getText().toString().trim(),
                        Integer.parseInt(food_qty.getText().toString().trim())
                );
                Intent intent = new Intent(FoodSpecifications.this, FoodChoice_v2.class);
                startActivity(intent);
            }catch (NumberFormatException e){
                ValueErrorCatch();//error if anything entered was not intended.
            }
            foodDB.close();//close database to prevent database leak.
        });
    }

    void ValueErrorCatch(){//if value error caught, show alert message and back to editing.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Value Error");
        builder.setMessage("please check format, food name and description should be texts and price shouldn't include anything but numbers, especially dollar signs. Accept decimals.");
        //leave it like this for ease of understanding.
        builder.setPositiveButton("Let me fix it", (dialog, which) -> {
            //nothing, puts user back to fix their mistake.
        });
        builder.create().show();
    }


}