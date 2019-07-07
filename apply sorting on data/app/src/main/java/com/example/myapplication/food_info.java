package com.example.myapplication;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class food_info extends AppCompatActivity {

    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    FoodAdapter foodAdapter;
    RecyclerView recyclerView1;
    ArrayList<foodInfo> list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        getSupportActionBar().setTitle("Food Content");

        sharedPreferences=this.getSharedPreferences("My_Data",MODE_PRIVATE);
        recyclerView1=(RecyclerView)findViewById(R.id.myFood);

        //set the positon on the oon layout by using linearLyoutmanager
        //LinearLayoutManager wil be used to show the layout linearly in vertical.
        recyclerView1.setLayoutManager(new LinearLayoutManager(food_info.this));
        list1=new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();

        firebaseDatabase=FirebaseDatabase.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();






        firebaseDatabase.getReference("Food").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                foodInfo information=dataSnapshot.getValue(foodInfo.class);
                list1.add(information);
                foodAdapter=new FoodAdapter(food_info.this,list1);
                recyclerView1.setAdapter(foodAdapter);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.By_FoodName:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Sort", "ascending");
                editor.apply();
                Collections.sort(list1,foodInfo.ByFoodName);
                foodAdapter=new FoodAdapter(food_info.this,list1);
                recyclerView1.setAdapter(foodAdapter);

                break;
            case R.id.By_FoodTaste:
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString("Sort", "ascending");
                editor1.apply();
                Collections.sort(list1,foodInfo.ByFoodTaste);
                foodAdapter=new FoodAdapter(food_info.this,list1);
                recyclerView1.setAdapter(foodAdapter);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
