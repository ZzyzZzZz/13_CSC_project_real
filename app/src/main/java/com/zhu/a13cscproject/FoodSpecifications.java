package com.zhu.a13cscproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FoodSpecifications extends AppCompatActivity {

    EditText food_qty, food_name, food_price, food_description;
    ImageButton add_input, subtract_input;
    Button add_to_sql_btn;
    String food_name_1, food_description_1;
    Float food_price_1;
    Integer food_qty_1;




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

                try{
                    foodDB.addfood(
                            food_name.getText().toString().trim(),
                            Float.parseFloat(food_price.getText().toString().trim()),
                            food_description.getText().toString().trim(),
                            Integer.valueOf(food_qty.getText().toString().trim())
                    );
                    Intent intent = new Intent(FoodSpecifications.this, FoodChoice_v2.class);
                    startActivity(intent);
                }catch (NumberFormatException e){
                    ValueErrorCatch();
                }
            }
        });
    }

    void ValueErrorCatch(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Value Error");
        builder.setMessage("please check format, food name and description should be texts and price shouldn't include anything but numbers, especially dollar signs. Accept decimals.");
        builder.setPositiveButton("Let me fix it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //nothing, puts user back to fix their mistake.
            }
        });
        builder.create().show();
    }
}