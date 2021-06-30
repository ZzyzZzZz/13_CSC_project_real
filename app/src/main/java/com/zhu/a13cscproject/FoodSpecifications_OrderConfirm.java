package com.zhu.a13cscproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FoodSpecifications_OrderConfirm extends AppCompatActivity {

    EditText food_qty_1, food_name_1, food_price_1, food_description_1;
    ImageButton add_input, subtract_input;
    Button add_to_sql_btn;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_specifications);


        food_name_1 = findViewById(R.id.food_name);
        food_price_1 = findViewById(R.id.food_price);
        food_description_1 = findViewById(R.id.food_description);
        food_qty_1 = findViewById(R.id.food_qty);
        add_input = findViewById(R.id.add_btn);
        subtract_input = findViewById(R.id.subtract_btn);
        add_to_sql_btn = findViewById(R.id.update_sql_btn);
        add_to_sql_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDatabase_OrderConfirm foodDB = new FoodDatabase_OrderConfirm(FoodSpecifications_OrderConfirm.this);

                try{
                    foodDB.addfood(
                            food_name_1.getText().toString().trim(),
                            Float.parseFloat(food_price_1.getText().toString().trim()),
                            food_description_1.getText().toString().trim(),
                            Integer.valueOf(food_qty_1.getText().toString().trim())
                    );
                    Intent intent = new Intent(FoodSpecifications_OrderConfirm.this, FoodChoice_v2.class);
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