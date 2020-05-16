package com.example.jioleh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //added comment to test github commit
    private EditText email;
    private EditText password;
    private Button login;
    private TextView register;
    private FirebaseAuth database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialise();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(email.getText().toString(), password.getText().toString());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(MainActivity.this, RegisterPage.class);
                startActivity(nextActivity);
            }
        });
    }

    private void initialise() {
        email = findViewById(R.id.etEmailAddress);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.tvRegister);
        database = FirebaseAuth.getInstance();
    }

    private void checkUser(String email, String password) {
        database.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent nextActivity = new Intent(MainActivity.this, PostLoginPage.class);
                    startActivity(nextActivity);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}