package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {

    private Button btnForgot;
    private EditText inputEmail;
    private String email;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        auth = FirebaseAuth.getInstance();

        btnForgot = (Button) findViewById(R.id.btnForgot);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
//        progressBar = findViewById(R.id.progressBar);

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

        TextView btn=findViewById(R.id.alreadyHaveAccount);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotActivity.this,LoginActivity.class));
            }
        });
    }

    private void validateData() {
        email = inputEmail.getText().toString();
        if (email.isEmpty()){
            inputEmail.setError("Required");
            inputEmail.requestFocus();
        }
        else {
            forgetPass();
        }
    }

    private void forgetPass() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotActivity.this, "Check your Email", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(ForgotActivity.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}