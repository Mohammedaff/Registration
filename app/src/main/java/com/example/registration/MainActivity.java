package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button c_riy, c_jed, c_dam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c_jed= (Button) findViewById(R.id.c_jeddah);
        c_dam = (Button) findViewById(R.id.c_dammam);
        c_riy = (Button) findViewById(R.id.c_riyadh);

        c_riy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_riy();
            }

        });

        c_jed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_jed();
            }

        });

        c_dam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_dam();
            }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    int id = item.getItemId();
    if(id == R.id.subitem_riyadh) {
        Intent intent = new Intent(this, c_riyadh.class);
        startActivity(intent);
        return true;
    }
        if(id == R.id.subitem_jeddah) {
            Intent intent = new Intent(this, c_jeddah.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.subitem_dammam) {
            Intent intent = new Intent(this, c_dammam.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void open_riy() {
        Intent intent = new Intent(this,c_status.class);
        startActivity(intent);
    }

    public void open_jed() {
        Intent intent = new Intent(this,c_status.class);
        startActivity(intent);
    }

    public void open_dam() {
        Intent intent = new Intent(this,c_status.class);
        startActivity(intent);
    }

}











