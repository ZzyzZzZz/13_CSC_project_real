package com.zhu.a13cscproject;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomAdapter_OrderConfirm extends RecyclerView.Adapter<CustomAdapter_OrderConfirm.MyViewHolder>{// custom adapter to display data

    private final Context context;
    private ArrayList food_id, food_name, food_price, food_qty, food_description;//not fixing these errors since they works logically since I already have check information somewhere else.

    //@@
    //This is very similar to CustomAdapter, comments there.
    //@@


    //when this class (activity) is initialized in OrderConfirm_v2, pass all ArrayLists created here to that class. Then set global variables so we can access them in other classes.
    CustomAdapter_OrderConfirm(
            OrderConfirm_v2 orderConfirm_v2, Context context,
            ArrayList food_id,
            ArrayList food_name,
            ArrayList food_price,
            ArrayList food_qty,
            ArrayList food_description
    ){
        this.context = context;
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_qty = food_qty;
        this.food_description = food_description;
    }

    //following 3 methods are implemented automatically by
    // android studio for my RecyclerView


    @NonNull
    @NotNull
    @Override

    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);//https://stackoverflow.com/questions/4576330/what-does-it-mean-to-inflate-a-view-from-an-xml-file
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view); //return the view
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomAdapter_OrderConfirm.MyViewHolder holder, int position) {

        holder.food_id_txt.setText(String.valueOf(food_id.get(position)));//position being in their position in the dataset. See link:
        holder.food_name_txt.setText(String.valueOf(food_name.get(position)));//https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter#onFailedToRecycleView(VH)
        holder.food_price_txt.setText(String.valueOf(food_price.get(position)));//keyword: onBindViewHolder
        holder.food_qty_txt.setText(String.valueOf(food_qty.get(position)));
        //when items in recyclerview was clicked, we are going to transfer to our activity where
//all the variables saved in SQL for that particular id will be carried over for modification.
        holder.mainLayout.setOnClickListener(v -> {//this chunk of code is for click response, I don't allow edit here.
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(food_id.get(position))); //start intent with this variable carried over. We need to fetch the data from
//                //this one specifically so we can edit the data in it through request to SQLite.
//                intent.putExtra("name", String.valueOf(food_name.get(position)));
//                intent.putExtra("price", String.valueOf(food_price.get(position)));
//                intent.putExtra("qty", String.valueOf(food_qty.get(position)));
//                intent.putExtra("description", String.valueOf(food_description.get(position)));
//                activity.startActivityForResult(intent,1);
        });

    }

    @Override
    public int getItemCount() {
        return food_id.size();// count how many items are in the RecyclerView dataset.
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{//inner class extends ViewHolder


        TextView food_id_txt, food_name_txt, food_price_txt, food_qty_txt, food_description_txt;//recyclerview does not have description but I am moving data as blocks to cause less confusion.
        LinearLayout mainLayout; //again, linear for vertical scroll. Don't want to mess it up by changing it. Might as well keep it.

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            food_id_txt = itemView.findViewById(R.id.food_id_txt);
            food_name_txt = itemView.findViewById(R.id.food_name_txt);
            food_price_txt = itemView.findViewById(R.id.food_price_txt);
            food_qty_txt = itemView.findViewById(R.id.food_qty_txt);
            food_description_txt = itemView.findViewById(R.id.food_description_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }

}
