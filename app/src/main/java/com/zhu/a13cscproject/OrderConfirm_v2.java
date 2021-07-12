package com.zhu.a13cscproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class OrderConfirm_v2 extends AppCompatActivity {

    RecyclerView recyclerview;//declear variables
    Button to_senddata_btn;
    Float price_calculation;
    Float final_cost;

    Integer qty_calculation;


    TextView total_txt, total_price_txt;

    FoodDatabase_OrderConfirm foodDB; //get my database
    FoodDatabase foodDB_first;
    ArrayList<String> food_id, food_name, food_price, food_qty, food_description;//arraylist needed for database transition
    ArrayList<String> food_id_1, food_name_1, food_price_1, food_qty_1, food_description_1;
    CustomAdapter_OrderConfirm customAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm_v2);



        recyclerview = findViewById(R.id.food_recyclerview);//find recyclerview


        total_txt = findViewById(R.id.total_txt);//uhh, just leave it here. Nothing is done.


        foodDB_first = new FoodDatabase(OrderConfirm_v2.this); // here are my variables for RecyclerView
        food_id = new ArrayList<>();
        food_name = new ArrayList<>();
        food_price = new ArrayList<>();
        food_qty = new ArrayList<>();
        food_description = new ArrayList<>();

        storeDataArrays_first();//it is actually reading data but whatever the naming



        foodDB = new FoodDatabase_OrderConfirm(OrderConfirm_v2.this); // here are my variables for RecyclerView
        food_id_1 = new ArrayList<>();
        food_name_1 = new ArrayList<>();
        food_price_1 = new ArrayList<>();
        food_qty_1 = new ArrayList<>();
        food_description_1 = new ArrayList<>();


        food_id_1 = food_id;//override data from first to second database
        food_name_1 = food_name;
        food_price_1 = food_price;
        food_qty_1 = food_qty;
        food_description_1 = food_description;

        int size = food_qty.size();//size of the database modification
        loopForDataRemoval(size);//gets rid of all the data without a qty of 1 or larger, means user didn't select
        int size_1 = food_qty_1.size();//size post modification
        loopForCostCalculation(size_1, final_cost);//calculate price need accurate size



        customAdapter = new CustomAdapter_OrderConfirm(OrderConfirm_v2.this, this, food_id_1, food_name_1, food_price_1, food_qty_1, food_description_1);//use my adapter
        recyclerview.setAdapter(customAdapter);//set adapter to my own adapter
        recyclerview.setLayoutManager(new LinearLayoutManager(OrderConfirm_v2.this));//linear layout for a vertical scroll
        //what is LayoutManager? See link below for description:
        //https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.LayoutManager

        storeDataArrays();//read second database


        to_senddata_btn = findViewById(R.id.to_order_finished);
        to_senddata_btn.setOnClickListener(v -> {
            foodDB.close();
            foodDB_first.close();
            if (size_1 == 0){
                noFooditemSelected();
                return;
            }
            Intent intent = new Intent(OrderConfirm_v2.this, SendData.class);
//                intent.putExtra(FOOD_COST, final_cost);//Todo carry over a variable to display final amount.
            startActivity(intent);
        });



    }

    void storeDataArrays() {// get the data from SQL and add them to the predefined variables that holds these values.
        foodDB.readAllData();
    }


    void storeDataArrays_first(){// get the data from SQL and add them to the predefined variables that holds these values.
        Cursor cursor = foodDB_first.readAllData();
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


    void loopForDataRemoval(int size){
        for (int counter = 0; counter < size; counter++) {//I made two seperate loops here for that I need to repeat the check again for another purpose
            //the reason I can't use it as else is purely due to the messed up order for id it creates. I will need to clean it up and then run again.
            if (food_qty_1.get(counter).equals("0")) {
                size -= 1;
                food_id_1.remove(counter);
                food_name_1.remove(counter);
                food_price_1.remove(counter);
                food_qty_1.remove(counter);
                food_description_1.remove(counter);
                loopForDataRemoval(size);
                break;
            }
        }
    }
    float loopForCostCalculation(int size_1, Float final_cost){
        final_cost = 0.0f;
        float final_cost_append = 0.0f;//idk why float need f but there it is
        for (int counter = 0; counter < size_1; counter++) {
            price_calculation = Float.parseFloat(String.valueOf(food_price_1.get(counter)));
            qty_calculation = Integer.parseInt(String.valueOf(food_qty_1.get(counter)));
            final_cost_append = price_calculation * qty_calculation;
            final_cost = final_cost + final_cost_append;
            System.out.println(final_cost);
        }
        total_price_txt = findViewById(R.id.total_cost);
        total_price_txt.setText(String.valueOf(final_cost));
        return final_cost;
    }

    void noFooditemSelected(){//minimum qty of food prevention.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("You didn't select any food!");
        builder.setMessage("We have removed 'nothing' as an item due to the poor sells number...");
        builder.setPositiveButton("Yeah, right.", new DialogInterface.OnClickListener() {//button clicked, back to ordering food.
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();//must have for all alertDialog
    }
}