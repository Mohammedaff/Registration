package com.example.registration;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class c_status extends AppCompatActivity {
    Button cGps;
    TextView cPer, cName;
    FirebaseDatabase fDatabase;
    DatabaseReference dRef;
    static String lat;
    static String lng;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_status);
        cName = findViewById(R.id.cName);
        cGps = findViewById(R.id.cLocation);
        cPer = findViewById(R.id.cPercentage);

        fDatabase = FirebaseDatabase.getInstance();

        dRef = fDatabase.getReference().child("account1");

        dRef.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    cName.setText(snapshot.getValue(String.class));
                } else {
                    cName.setText("Not Found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dRef.child("prc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cPer.setText(snapshot.getValue(int.class).toString());
                String prca = snapshot.getValue(int.class).toString();
                int num = Integer.parseInt(prca);
                if (num == 100) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationChannel channel = new NotificationChannel("myCh", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager = getSystemService(NotificationManager.class);
                        manager.createNotificationChannel(channel);
                    }
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(c_status.this, "myCh");
                    builder.setContentTitle("Charity Contnainer Monitor");
                    builder.setContentText("Container is full");
                    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(c_status.this);
                    notificationManagerCompat.notify(1, builder.build());
                } // if notfition
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String lat = snapshot.child("lat").getValue(double.class).toString();
                String lng = snapshot.child("lng").getValue(double.class).toString();

                cGps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gotoUrl("https://maps.google.com/?q=" + lat + "," + lng);
                    }

                    public void gotoUrl(String s) {
                        Uri uri = Uri.parse(s);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


//        builder.setContentTitle("My Title");
//        builder.setContentText("This is Notification");
//        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
//        builder.setAutoCancel(true);
//
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
//        notificationManagerCompat.notify(1, builder.build());
//    }


}
