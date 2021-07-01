package com.zhu.a13cscproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodChoice extends AppCompatActivity {
//    TextView chips_text, hotdog_text, kebab_text, chips_qty, hotdog_qty, kebab_qty;
//    Button chips_minus, chips_plus, hotdog_minus, hotdog_plus, kebab_minus, kebab_plus, to_checkout;
//    Integer chips_qty_num, hotdog_qty_num, kebab_qty_num;
//    public static final String CHIPS_QTY = "com.zhu.a13cscproject.CHIPS_QTY";// not optimised, could be more efficient
//    public static final String HOTDOG_QTY = "com.zhu.a13cscproject.HOTDOG_QTY";
//    public static final String KEBAB_QTY = "com.zhu.a13cscproject.KEBAB_QTY";
    FloatingActionButton to_cart_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_food_choice);
//
//        chips_qty_num = 0;
//        hotdog_qty_num = 0;
//        kebab_qty_num = 0;
//
//
//        chips_text = findViewById(R.id.chips_text);
//        chips_qty = findViewById(R.id.chips_qty);
//        hotdog_text = findViewById(R.id.hotdog_text);
//        hotdog_qty = findViewById(R.id.hotdog_qty);
//        kebab_text = findViewById(R.id.kebab_text);
//        kebab_qty = findViewById(R.id.kebab_qty);
//
//        chips_minus = findViewById(R.id.chips_minus);
//        chips_plus = findViewById(R.id.chips_plus);
//        hotdog_minus = findViewById(R.id.hotdog_minus);
//        hotdog_plus = findViewById(R.id.hotdog_plus);
//        kebab_minus = findViewById(R.id.kebab_minus);
//        kebab_plus = findViewById(R.id.kebab_plus);
//
//        chips_qty.setText(String.valueOf(chips_qty_num));
//        hotdog_qty.setText(String.valueOf(hotdog_qty_num));
//        kebab_qty.setText(String.valueOf(kebab_qty_num));
//
//        cont_OrderConfirm();
////** Chips button **
//
//        chips_minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (chips_qty_num <= 0) {
//                    Toast.makeText(FoodChoice.this, "you can't have less than 0 chips", Toast.LENGTH_LONG).show();
//                } else {
//                    chips_qty_num -= 1;
//                    chips_qty.setText(Integer.toString(chips_qty_num));
////                    Toast.makeText(FoodChoice.this, "yeet", Toast.LENGTH_SHORT).show(); //test
//
//                }
//            }
//        });
//
//        chips_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (chips_qty_num >= 10) {
//                    Toast.makeText(FoodChoice.this, "save some chips for others!", Toast.LENGTH_LONG).show();
//                } else {
//                    chips_qty_num += 1;
//                    chips_qty.setText(Integer.toString(chips_qty_num));
//                    Toast.makeText(FoodChoice.this, "yeet", Toast.LENGTH_SHORT).show();// test, remove
//                }
//            }
//        });
//
////** Hotdog button **
//
//        hotdog_minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (hotdog_qty_num <= 0) {
//                    Toast.makeText(FoodChoice.this, "you can't have less than 0 hotdog", Toast.LENGTH_LONG).show();
//                } else {
//                    hotdog_qty_num -= 1;
//                    hotdog_qty.setText(Integer.toString(hotdog_qty_num));
//                    Toast.makeText(FoodChoice.this, "yeet", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//        hotdog_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (hotdog_qty_num >= 10) {
//                    Toast.makeText(FoodChoice.this, "save some hotdogs for others!", Toast.LENGTH_LONG).show();
//                } else {
//                    hotdog_qty_num += 1;
//                    hotdog_qty.setText(Integer.toString(hotdog_qty_num));
//                }
//            }
//        });
//
////** kebab button **
//
//        kebab_minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (kebab_qty_num <= 0) {
//                    Toast.makeText(FoodChoice.this, "you can't have less than 0 kebab", Toast.LENGTH_LONG).show();
//                } else {
//                    kebab_qty_num -= 1;
//                    kebab_qty.setText(Integer.toString(kebab_qty_num));
//                    Toast.makeText(FoodChoice.this, "yeet", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//
//        kebab_plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (kebab_qty_num >= 10) {
//                    Toast.makeText(FoodChoice.this, "save some kebabs for others!", Toast.LENGTH_LONG).show();
//                } else {
//                    kebab_qty_num += 1;
//                    kebab_qty.setText(Integer.toString(kebab_qty_num));
//                }
//            }
//        });
//
//
//    }
//
//    private void cont_OrderConfirm() {
//        to_checkout = findViewById(R.id.to_checkout_btn);
//        to_checkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_to_orderconfirm = new Intent(FoodChoice.this, OrderConfirm.class);
//                intent_to_orderconfirm.putExtra(CHIPS_QTY, chips_qty_num);// not optimised, could use a loop
//                intent_to_orderconfirm.putExtra(HOTDOG_QTY, hotdog_qty_num);
//                intent_to_orderconfirm.putExtra(KEBAB_QTY, kebab_qty_num);
//                startActivity(intent_to_orderconfirm);
//            }
//        });
//    }
//
//
//}

    to_cart_btn = findViewById(R.id.to_order_finished);



    }
}