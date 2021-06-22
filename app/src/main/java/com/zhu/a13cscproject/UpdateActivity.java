package com.zhu.a13cscproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText food_qty;
    Button update_btn;
    TextView food_name, food_price, food_description;
    ImageButton add_input, subtract_input;

    String id, name, price, qty, description;
    Integer qty_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        add_input = findViewById(R.id.add_btn2); //the add qty button, in this case it can be used to change the qty of that particular food. Or you can enter it, up to you.
        add_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty_int = Integer.parseInt(qty);

                if (qty_int < 10) {
                    qty_int += 1;
                    qty = Integer.toString(qty_int);
                    food_qty.setText(qty);
                }else{
                    Toast.makeText(UpdateActivity.this, "leave some " + name + "for others!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        subtract_input = findViewById(R.id.subtract_btn2);//the subtract qty button, just like add button.
        subtract_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty_int = Integer.parseInt(qty);

                if (qty_int >0) {
                    qty_int -= 1;
                    qty = Integer.toString(qty_int);
                    food_qty.setText(qty);
                }else{
                    Toast.makeText(UpdateActivity.this, "You can't have negative amount of " + name , Toast.LENGTH_SHORT).show();
                }

            }
        });



        food_name = findViewById(R.id.food_name2);
        food_price = findViewById(R.id.food_price2);
        food_qty = findViewById(R.id.food_qty2);
        food_description = findViewById(R.id.food_description2);


        //setting data first
        getAndSetIntentData();


        update_btn = findViewById(R.id.update_sql_btn2);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodDatabase foodDB = new FoodDatabase(UpdateActivity.this);
                //update data. Data should be set before update.
                foodDB.updateData(id, qty);
                //so you don't click twice by accident, also, to update the recyclerview as well, every time FoodChoice_v2 was called it goes through the load process of recyclerview, which includes the new data
                // not the most efficient way, but let's let this version slide.
                Intent intent = new Intent(UpdateActivity.this, FoodChoice_v2.class);
                startActivity(intent);
            }
        });



    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("price")
                && getIntent().hasExtra("qty") && getIntent().hasExtra("description")){
            // getting data from previous intent, or food_choice_v2
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");
            qty = getIntent().getStringExtra("qty");
            description = getIntent().getStringExtra("description");

            //setting Intent data to this java class, or activity to be seen and edited.
            food_name.setText(name);
            food_price.setText(price);
            food_qty.setText(qty);
            food_description.setText(description);

        }else{
            Toast.makeText(this,"No Data, please check on SQL dataset", Toast.LENGTH_SHORT).show();
        }
    }
}