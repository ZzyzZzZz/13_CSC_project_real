package com.zhu.a13cscproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderConfirm extends AppCompatActivity {

    TextView textView1, textView2, textView3;

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


    }// First research on how to carry variable over, then use if else to decisively display only the ones chosen.
}//afterwards it should be done as first version, then move onto second version.
// json write to my file storage and read it then push it to firebase