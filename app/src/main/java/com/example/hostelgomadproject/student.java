package com.example.hostelgomadproject;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class student extends AppCompatActivity {
    CardView messmenu;
    CardView Notice;
    CardView logout;
    CardView maintenance;
    CardView council;
    CardView dropcomplaint;
    CardView emergency;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        auth=FirebaseAuth.getInstance();

        messmenu = findViewById(R.id.viewmenuCard);

        messmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student.this, viewmenu.class);
                startActivity(intent);
            }
        });

        maintenance = findViewById(R.id.viewmaintenanceCard);

        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student.this, maintenance.class);
                startActivity(intent);
            }
        });
        Notice = findViewById(R.id.viewNoticeCard);

        Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student.this, viewnotice.class);
                startActivity(intent);
            }
        });
        council = findViewById(R.id.viewCouncilCard);

        council.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student.this, viewcouncil.class);
                startActivity(intent);
            }
        });
        dropcomplaint = findViewById(R.id.dropcomplaintCard);

        dropcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student.this, DropComplaint.class);
                startActivity(intent);
            }
        });
        emergency = findViewById(R.id.EmergencyCard);

        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(student.this, emergencycontact.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logoutCard);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(student.this);
//                alertDialog.setTitle("Exit App");
                alertDialog.setMessage("Are you sure you want to LogOut?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        auth.signOut();

                        Intent intent = new Intent(student.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
//                        finishAffinity();
//                        return;
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(student.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
//                return;
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}