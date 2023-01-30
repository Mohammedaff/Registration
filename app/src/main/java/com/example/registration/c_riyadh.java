package com.example.registration;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class c_riyadh extends AppCompatActivity {
    Button container1;
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.c_riyadh);
    container1 = (Button) findViewById(R.id.riyadh_c1);

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

