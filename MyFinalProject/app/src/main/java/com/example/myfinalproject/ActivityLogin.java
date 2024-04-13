package com.example.myfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ActivityLogin extends AppCompatActivity {
    private Button Login, Register;
    private EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email = findViewById(R.id.inputUser);
        password = findViewById(R.id.inputPassword);
        Login = findViewById(R.id.btnLogin);
        Register = findViewById(R.id.btnRegister);

        auth = FirebaseAuth.getInstance();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, student_register.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(ActivityLogin.this, "Input username and password", Toast.LENGTH_SHORT).show();
                }else {
                    loginUser(username, pass);
                }

            }

        });
    }

    private void loginUser(String username, String pass) {
        auth.signInWithEmailAndPassword(username, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ActivityLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    task.getException();
                    Intent intent = new Intent(ActivityLogin.this, ProductActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }else {
                    task.getException();
                    Toast.makeText(ActivityLogin.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
