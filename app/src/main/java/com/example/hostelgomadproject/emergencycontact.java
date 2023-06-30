package com.example.hostelgomadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class emergencycontact extends AppCompatActivity {
    CardView reception;
    CardView principal;
    CardView viceprincipal;
    CardView swo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencycontact);

        CardView reception = findViewById(R.id.receptionCard);
        reception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer("0824-2277222"); // Replace with the desired phone number
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView principal = findViewById(R.id.principalCard);
        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer("9448100123"); // Replace with the desired phone number
            }
        });
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView viceprincipal= findViewById(R.id.viceprincipalCard);
        viceprincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer("9448211164"); // Replace with the desired phone number
            }
        });
         @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView swo= findViewById(R.id.swoCard);
        swo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer("9449845906"); // Replace with the desired phone number
            }
        });

    }
    private void openDialer(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

}