package com.zhu.a13cscproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{// custom adapter to display data

    private Context context;
    private ArrayList food_id, food_name, food_price, food_qty, food_description;
    private MyViewHolder mHolder;
    private int mPosition;

    //when this class (activity) is initialized in FoodChoice, pass all ArrayLists created here to that class. Then set global variables so we can access them in other classes.
    CustomAdapter(Context context,
                  ArrayList food_id,
                  ArrayList food_name,
                  ArrayList food_price,
                  ArrayList food_qty
//                  ArrayList food_description
    ){
        this.context = context;
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_qty = food_qty;
//        this.food_description = food_description;
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
    public void onBindViewHolder(@NonNull @NotNull CustomAdapter.MyViewHolder holder, int position) {
        holder.food_id_txt.setText(String.valueOf(food_id.get(position)));//position being in their position in the dataset. See link:
        holder.food_name_txt.setText(String.valueOf(food_name.get(position)));//https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter#onFailedToRecycleView(VH)
        holder.food_price_txt.setText(String.valueOf(food_price.get(position)));//keyword: onBindViewHolder
        holder.food_qty_txt.setText(String.valueOf(food_qty.get(position)));

    }

    @Override
    public int getItemCount() {
        return food_id.size();// count how many items are in the RecyclerView dataset.
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{//inner class extends ViewHolder


        TextView food_id_txt, food_name_txt, food_price_txt, food_qty_txt, food_description_txt;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            food_id_txt = itemView.findViewById(R.id.food_id_txt);
            food_name_txt = itemView.findViewById(R.id.food_name_txt);
            food_price_txt = itemView.findViewById(R.id.food_price_txt);
            food_qty_txt = itemView.findViewById(R.id.food_qty_txt);
//            food_description_txt = itemView.findViewById(R.id.food_description_txt);

        }
    }

}
