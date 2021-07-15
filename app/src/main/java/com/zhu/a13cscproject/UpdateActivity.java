package com.zhu.a13cscproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UpdateActivity extends AppCompatActivity {

    EditText food_qty;
    Button update_btn;
    TextView food_name, food_price, food_description;
    ImageButton add_input, subtract_input;
    FloatingActionButton delete_f_btn;

    String id;
    String name;
    String price;
    String qty;
    String description;
    GlobalClass gc;
    Integer qty_int, dev_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        GlobalClass globalClass = (GlobalClass)getApplicationContext(); //dev mode
        this.gc = globalClass;
        dev_mode = gc.getDev_mode();

        add_input = findViewById(R.id.add_btn2); //the add qty button, in this case it can be used to change the qty of that particular food. Or you can enter it, up to you.
        add_input.setOnClickListener(v -> {
            qty_int = Integer.parseInt(qty);// first change qty of selected food to int

            if (qty_int < 10) {// then do calculations
                qty_int += 1;
                qty = Integer.toString(qty_int);// new value to string
                food_qty.setText(qty); // then set the original qty variable to the new number.
            }else{
//                    Toast.makeText(UpdateActivity.this, "leave some " + name + "for others!", Toast.LENGTH_SHORT).show();
                tooManyItem();
            }

        });
        subtract_input = findViewById(R.id.subtract_btn2);//the subtract qty button, just like add button.
        subtract_input.setOnClickListener(v -> {
            qty_int = Integer.parseInt(qty);

            if (qty_int >0) {
                qty_int -= 1;
                qty = Integer.toString(qty_int);
                food_qty.setText(qty);
            }else{
//                    Toast.makeText(UpdateActivity.this, "You can't have negative amount of " + name , Toast.LENGTH_SHORT).show();
                tooFewItem();
            }

        });



        food_name = findViewById(R.id.food_name2);
        food_price = findViewById(R.id.food_price2);
        food_qty = findViewById(R.id.food_qty2);
        food_description = findViewById(R.id.food_description2);
        delete_f_btn = findViewById(R.id.delete_item);// only show in dev_mode enabled.
        if(dev_mode == 1){
            delete_f_btn.show();//show if dev_mode enabled
        }else{
            delete_f_btn.hide();//other wise hide
        }
        delete_f_btn.setOnClickListener(v -> { // to delete items in SQLite as a whole
            confirmDeleteDialog();
        });




        //setting data first
        getAndSetIntentData();




        update_btn = findViewById(R.id.update_sql_btn2);
        update_btn.setOnClickListener(v -> {
            FoodDatabase foodDB = new FoodDatabase(UpdateActivity.this);
            //update data. Data should be set before update.
            foodDB.updateData(id, qty);
            //so you don't click twice by accident, also, to update the recyclerview as well, every time FoodChoice_v2 was called it goes through the load process of recyclerview, which includes the new data
            // not the most efficient way, but let's let this version slide.

            foodDB.close();
            finish();
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

    void confirmDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + "?");
        builder.setMessage("Delete " + name + " permanently?");
        //I am leaving this for ease of use
        builder.setPositiveButton("Yes", (dialog, which) -> {
            FoodDatabase db = new FoodDatabase(UpdateActivity.this); // FoodDatabase object
            db.deleteOneRow(id); // exe the delete row with the respective id.
            finish();
        });
        //leaving this for ease of use
        builder.setNegativeButton("No", (dialog, which) -> {
            //nothing since we don't want to do anything.
        });
        builder.create().show();//or alert msg won't show. Tested.
    }

    void tooManyItem(){//maximum qty of food overflow
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//building alert message to inform user
        builder.setTitle("More than 10 " + name + "!");
        builder.setMessage("10 " + name + "! You got some impressive skills there to finish all these. Unfortunately you got to leave some for others.");
        //button clicked, back to ordering food.
        builder.setPositiveButton("Fine, I'll leave some for others", (dialog, which) -> {
            //nothing here, only a reminder msg
        });
        builder.create().show();//must have for all alertDialog
    }

    void tooFewItem(){//minimum qty of food prevention.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//building alert message to inform user
        builder.setTitle("You ain't selling no " + name + " to me");
        builder.setMessage("How do I know? Well, that's a story for another time.");
        //button clicked, back to ordering food.
        builder.setPositiveButton("Yeah, right.", (dialog, which) -> {
            //nothing here as well, back to food selection.
        });
        builder.create().show();//must have for all alertDialog
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"Changes Not Saved", Toast.LENGTH_SHORT).show();
    }
}
