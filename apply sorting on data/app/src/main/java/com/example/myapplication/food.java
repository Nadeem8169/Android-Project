package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class food extends AppCompatActivity {

    EditText foodName, foodTaste, edtemail, edtpassword;
    Button btnAdd;
    foodInfo information;
    FirebaseDatabase firebaseDatabase1;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        FirebaseApp.initializeApp(this);

        foodName = (EditText) findViewById(R.id.foodname);
        foodTaste = (EditText) findViewById(R.id.foodtaste);
        edtemail = (EditText) findViewById(R.id.email);
        edtpassword = (EditText) findViewById(R.id.password);
        btnAdd = (Button) findViewById(R.id.add);
        firebaseDatabase1 = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase1.getReference("Food");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String FoodName = foodName.getText().toString();
                final String FoodTaste = foodTaste.getText().toString();
                final String email = edtemail.getText().toString();
                final String password = edtpassword.getText().toString();

                if (TextUtils.isEmpty(FoodName) || TextUtils.isEmpty(FoodTaste) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(food.this, "Pleasse Enter the emplty field", Toast.LENGTH_SHORT).show();
                } else {


                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(food.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        information = new foodInfo(FoodName, FoodTaste, email, password);
                                        firebaseDatabase1.getReference("Food")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(food.this, "Resgitration finish", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                        startActivity(new Intent(food.this, food_info.class));


                                                    }
                                                });

                                    } else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(food.this, "Resgitration failed,You alredy register", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    // ...
                                }


                            });

                }
            }
        });
    }
}



