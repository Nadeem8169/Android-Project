package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<informationn> info;

    public MyAdapter(ArrayList<informationn> info){
        this.context=context;
        this.info=info;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.template,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(info.get(i).getFullname());
        myViewHolder.email.setText(info.get(i).getEmail());
        myViewHolder.password.setText(info.get(i).getPassword());
        myViewHolder.username.setText(info.get(i).getUsername());
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,email,password,username;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            username=(TextView)itemView.findViewById(R.id.username);
            email=(TextView)itemView.findViewById(R.id.email);
            password=(TextView)itemView.findViewById(R.id.password);
        }
    }
}
