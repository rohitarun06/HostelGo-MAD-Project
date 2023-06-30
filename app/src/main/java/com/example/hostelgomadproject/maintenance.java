package com.example.hostelgomadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class maintenance extends AppCompatActivity {
    CardView manager;
    CardView tech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView manager = findViewById(R.id.mmanagerCard);
       manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer("0824-2277222"); // Replace with the desired phone number
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView tech = findViewById(R.id.ftechCard);
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer("7348947312"); // Replace with the desired phone number
            }
        });
    }
    private void openDialer(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

}