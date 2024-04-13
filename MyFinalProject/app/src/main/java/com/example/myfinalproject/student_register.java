package com.example.myfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class student_register extends AppCompatActivity {

    private Button Register;
    private EditText passWord, name, email;
    private TextView login;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = findViewById(R.id.have);
        passWord = findViewById(R.id.inputPassword);
        email = findViewById(R.id.inputEmail);
        name = findViewById(R.id.inputUser);
        Register = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_register.this, student_login.class);
                startActivity(intent);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = email.getText().toString();
                String password = passWord.getText().toString();
                String user = name.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(user)) {
                    Toast.makeText(student_register.this, "Input Email, Username and password", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(username, password);
                }
            }
        });
    }
    private void registerUser(String username, String password) {
        auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(student_register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(student_register.this,student_login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(student_register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void toLogin(View v){
        startActivity(new Intent(this, student_login.class));
    }
}

