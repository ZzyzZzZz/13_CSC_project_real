package com.zhu.a13cscproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderConfirm extends AppCompatActivity {

    ListView order_confirm_list;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        Intent intent_to_orderconfirm = getIntent();
        int chips_qty_num = intent_to_orderconfirm.getIntExtra(FoodChoice.CHIPS_QTY, 0);// to get the value we need from last activity
        int hotdog_qty_num = intent_to_orderconfirm.getIntExtra(FoodChoice.HOTDOG_QTY, 0);
        int kebab_qty_num = intent_to_orderconfirm.getIntExtra(FoodChoice.KEBAB_QTY, 0);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);

        textView1.setText("" + chips_qty_num);// I used "" + number to turn it into a string to display correctly
        textView2.setText("" + hotdog_qty_num);
        textView3.setText("" + kebab_qty_num);

        if (chips_qty_num == 0) {// not optimised
            textView1.setVisibility(View.INVISIBLE);// setting visibility if this item is not chosen
        }
        if (hotdog_qty_num == 0){
            textView2.setVisibility(View.INVISIBLE);
            // the problem with this setup is that I won't be able to replace one that's chosen
        }
        if (kebab_qty_num == 0){
            textView3.setVisibility(View.INVISIBLE);
        }

        order_confirm_list = findViewById(R.id.order_confirm_list); // this is the creation of a list view,
        // where I list everything in order and exclude things that aren't chosen.
        ArrayList<String> order_confirm_array = new ArrayList<>(); //I turned int to String so I can display both String and Int.

        if (chips_qty_num != 0){
            order_confirm_array.add("chips" + chips_qty_num);
        }
        if (hotdog_qty_num != 0){
            order_confirm_array.add("hotdog" + hotdog_qty_num);
        }
        if (kebab_qty_num != 0){
            order_confirm_array.add("kebab" + kebab_qty_num);
        }
        ArrayAdapter<String> order_confirm_list_adaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order_confirm_array);

        order_confirm_list.setAdapter(order_confirm_list_adaptor);

//        // hashmap
//        Map<String, Integer> numberMapping = new HashMap<>();
//
//        // Adding key-value pairs to a HashMap
//        numberMapping.put("CHIPS", chips_qty_num);
//        numberMapping.put("HOTDOG", hotdog_qty_num);
//        numberMapping.put("KEBAB", kebab_qty_num);
//
//        // Add a new key-value pair only if the key does not exist in the HashMap, or is mapped to `null`
//        //numberMapping.putIfAbsent("Four", 4);
//
//        System.out.println(numberMapping);



    }// First research on how to carry variable over, then use if else to decisively display only the ones chosen.
}//afterwards it should be done as first version, then move onto second version.
// json write to my file storage and read it then push it to firebase