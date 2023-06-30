package com.example.hostelgomadproject;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class warden extends AppCompatActivity {
    CardView addStudent;
    CardView addNotice;
    CardView logout;
    CardView attendence;
    CardView mess;
    CardView viewcomplaint;
    FirebaseAuth auth;
    String wemail,wpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden);

        addStudent = findViewById(R.id.AddStudentCard);
        addNotice = findViewById(R.id.NoticeCard);
        logout = findViewById(R.id.LogoutCard);
        attendence = findViewById(R.id.attendenceCard);
        viewcomplaint = findViewById(R.id.viewComplaintCard);
        mess = findViewById(R.id.menuCard);
        auth=FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=getIntent().getExtras();
                Bundle bundle1= new Bundle();
                bundle1.putString("email",bundle.getString("email"));
                bundle1.putString("password",bundle.getString("password"));
                Intent intent = new Intent(warden.this, addStudents.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });

        addNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(warden.this, noticeupdate.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(warden.this);
//                alertDialog.setTitle("Exit App");
                alertDialog.setMessage("Are you sure you want to LogOut?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        auth.signOut();

                        Intent intent = new Intent(warden.this, MainActivity.class);
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

        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(warden.this, attendence.class);
                startActivity(intent);
            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(warden.this, addmenu.class);
                startActivity(intent);
            }
        });

        viewcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(warden.this, viewcomplaint.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(warden.this);
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