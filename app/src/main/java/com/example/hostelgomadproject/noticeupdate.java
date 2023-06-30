package com.example.hostelgomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class noticeupdate extends AppCompatActivity {

    EditText date,title,content;
    Button update,deleteNotice;

    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticeupdate);

        date=findViewById(R.id.noticeDate);
        title=findViewById(R.id.noticeTitle);
        content=findViewById(R.id.noticeContent);
        update=findViewById(R.id.menuButton);
        deleteNotice=findViewById(R.id.deleteNoticeButton);

        databaseReference=FirebaseDatabase.getInstance().getReference("NOTICE");

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date1=date.getText().toString();
                String title1=title.getText().toString();
                String content1=content.getText().toString();

                if(date1.isEmpty())
                {
                    Toast.makeText(noticeupdate.this, "Enter the Date Field", Toast.LENGTH_SHORT).show();
                } else if (title1.isEmpty())
                {
                    Toast.makeText(noticeupdate.this, "Enter the Title Field", Toast.LENGTH_SHORT).show();
                } else if (content1.isEmpty())
                {
                    Toast.makeText(noticeupdate.this, "Enter the Content Field", Toast.LENGTH_SHORT).show();
                }else {
                    String id = databaseReference.push().getKey();
                    HashMap<String,Object> data=new HashMap<String,Object>();
                    data.put("Date",date1);
                    data.put("Title",title1);
                    data.put("Content",content1);
                    data.put("ID",id);
                    databaseReference.child(id).setValue(data);
                    Toast.makeText(noticeupdate.this, "Notice Uploaded Successfully", Toast.LENGTH_SHORT).show();

                    title.setText("");
                    date.setText("");
                    content.setText("");
                }
            }
        });
        deleteNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(noticeupdate.this, deletenotice.class);
                startActivity(intent);
            }
        });

    }

    }
