package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextView profilename, profiletype;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    Button btnlogout;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Profile");

        profilename = (TextView) findViewById(R.id.textView);
        profiletype = (TextView) findViewById(R.id.textView2);
        btnlogout = (Button) findViewById(R.id.btnlogout);


        firebaseDatabase=FirebaseDatabase.getInstance();
        user=FirebaseAuth.getInstance().getCurrentUser();
        String uid=user.getUid();

        firebaseDatabase.getReference("information").child(uid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        informationn info=dataSnapshot.getValue(informationn.class);
                        String name=info.getFullname();
                        String type=info.getType();

                        profilename.setText("Name:"+name);
                        profiletype.setText("Im "+type);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(Login.this,databaseError.getCode(),Toast.LENGTH_SHORT).show();
                    }
                });


        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(Login.this, MainActivity.class));
            }

        });


    }


}
