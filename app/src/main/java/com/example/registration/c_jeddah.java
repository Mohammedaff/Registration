package com.example.registration;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class c_jeddah extends AppCompatActivity {
    Button container1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_jeddah);
        container1 = (Button) findViewById(R.id.jeddah_c1);

        container1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_status();
            }

        });
    }

    public void open_status() {
        Intent intent = new Intent(this, c_status.class);
        startActivity(intent);
    }
}