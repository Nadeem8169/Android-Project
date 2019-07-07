package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class FoodAdapter  extends RecyclerView.Adapter<FoodAdapter.MyviewHolder> {
    ArrayList<foodInfo> arrFood;
    Context context;

    public FoodAdapter(Context context,ArrayList<foodInfo> arrFood) {

        this.arrFood = arrFood;
        this.context=context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
        View view =layoutInflater.inflate(R.layout.row,viewGroup,false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
        myviewHolder.Email.setText("Email:-"+arrFood.get(i).getEmail());
        myviewHolder.Password.setText("Password:-"+arrFood.get(i).getPassword());
        myviewHolder.FoodName.setText("Food name:-"+arrFood.get(i).getFoodname());
        myviewHolder.FoodTaste.setText("Food Taste:-"+arrFood.get(i).getFoodtaste());


    }

    @Override
    public int getItemCount() {
        return arrFood.size();
    }

    class MyviewHolder extends  RecyclerView.ViewHolder{
        TextView FoodName,FoodTaste,Email,Password;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            Email=(TextView)itemView.findViewById(R.id.txtemail);
            Password=(TextView)itemView.findViewById(R.id.txtpassword);
            FoodName=(TextView)itemView.findViewById(R.id.foodName);
            FoodTaste=(TextView)itemView.findViewById(R.id.foodTaste);
        }
    }
}
