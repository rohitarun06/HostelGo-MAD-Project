package com.example.hostelgomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class DropComplaint extends AppCompatActivity {

    EditText date,roomNo,content;
    Button riseComplaint;

    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropcomplaint);

        date=findViewById(R.id.complaintDate);
        roomNo=findViewById(R.id.complaintRoomNo);
        content=findViewById(R.id.complaintDesc);
        riseComplaint=findViewById(R.id.complaintButton);

        databaseReference=FirebaseDatabase.getInstance().getReference("COMPLAINTS");

        riseComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date1=date.getText().toString();
                String roomNo1=roomNo.getText().toString();
                String content1=content.getText().toString();

                if(date1.isEmpty())
                {
                    Toast.makeText(DropComplaint.this, "Enter the Date Field", Toast.LENGTH_SHORT).show();
                } else if (roomNo1.isEmpty())
                {
                    Toast.makeText(DropComplaint.this, "Enter the Title Field", Toast.LENGTH_SHORT).show();
                } else if (content1.isEmpty())
                {
                    Toast.makeText(DropComplaint.this, "Enter the Content Field", Toast.LENGTH_SHORT).show();
                }else {
                    String id = databaseReference.push().getKey();
                    HashMap<String,Object> data=new HashMap<String,Object>();
                    data.put("Date",date1);
                    data.put("RoomNo",roomNo1);
                    data.put("Content",content1);
                    data.put("ID",id);
                    databaseReference.child(id).setValue(data);
                    Toast.makeText(DropComplaint.this, "Complaint Uploaded Successfully", Toast.LENGTH_SHORT).show();

                    date.setText("");
                    roomNo.setText("");
                    content.setText("");
                }
            }
        });

    }
}