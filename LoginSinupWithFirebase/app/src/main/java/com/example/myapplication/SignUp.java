package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {


    EditText fullName,userName,passWord,emAil;
    Button register;
    RadioGroup radioGroup;
    RadioButton student,teacher,admin;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String type;
    FirebaseAuth mAuth;
    TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Signup Form");


        login=(TextView)findViewById(R.id.login);

        fullName=(EditText)findViewById(R.id.fullname);
        userName=(EditText)findViewById(R.id.username);
        passWord=(EditText)findViewById(R.id.password);
        emAil=(EditText)findViewById(R.id.email);

        register=(Button)findViewById(R.id.register);
        student=(RadioButton)findViewById(R.id.student);
        teacher=(RadioButton)findViewById(R.id.teacher);
        admin =(RadioButton)findViewById(R.id.student);
        FirebaseApp.initializeApp(this);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Information");
        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,MainActivity.class));
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  final String fullname = fullName.getText().toString();
                final String password = passWord.getText().toString();
               final  String email = emAil.getText().toString();
                final String username = userName.getText().toString();

                if (student.isChecked()) {
                    type = "Student";
                } else {
                    if (teacher.isChecked()) {
                        type = "Teacher";
                    } else {
                        type = "Admin";
                    }
                }

                if (TextUtils.isEmpty(fullname)||TextUtils.isEmpty(email)||TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(type)) {
                    Toast.makeText(SignUp.this, "Please enter the empty field", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUp.this,
                                    new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        informationn info = new informationn(fullname, username, email, password, type);
                                        firebaseDatabase.getReference("information")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(info).addOnCompleteListener(
                                                        new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(SignUp.this, "Resgitration finish", Toast.LENGTH_SHORT).show();
                                                finish();
                                                startActivity(new Intent(SignUp.this, MainActivity.class));


                                            }
                                        });

                                    } else {
                                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                            Toast.makeText(SignUp.this, "Resgitration failed,You alredy register", Toast.LENGTH_SHORT).show();
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