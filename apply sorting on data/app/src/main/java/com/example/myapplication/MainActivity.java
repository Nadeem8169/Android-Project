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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    Button btnSignIn,btnSignUp;
    EditText edtPassword,edtEmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Login Form");

        btnSignIn=(Button)findViewById(R.id.signIn);
        btnSignUp=(Button)findViewById(R.id.signUp);
        edtPassword=(EditText) findViewById(R.id.edtPassword);
        edtEmail=(EditText)findViewById(R.id.edtEmail);

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email=edtEmail.getText().toString();
                String password=edtPassword.getText().toString();

                if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please enter the empty field", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent1 = new Intent(MainActivity.this, Login.class);
                                        startActivity(intent1);
                                        Toast.makeText(MainActivity.this, "Sucessfull login", Toast.LENGTH_SHORT).show();
                                        edtEmail.setText("");
                                        edtPassword.setText("");

                                    } else {
                                        Toast.makeText(MainActivity.this, "Incorrecct email or password", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }


            }
        });


    }
}
