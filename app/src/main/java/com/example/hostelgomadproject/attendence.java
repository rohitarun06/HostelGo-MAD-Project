package com.example.hostelgomadproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.HashMap;

public class attendence extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AttendanceAdapter attendanceAdapter;
    ArrayList list;
    EditText date;
    Button view,submit;
    String date1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        date=findViewById(R.id.date);
        submit=findViewById(R.id.submitAttend);
        view=findViewById(R.id.viewAttend);

        list=new ArrayList<>();
        recyclerView=findViewById(R.id.attendanceRecyc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        attendanceAdapter=new AttendanceAdapter(this,list);
        recyclerView.setAdapter(attendanceAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference("StudentList");


        attendanceAdapter.setOnItenClickListener(new AttendanceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,String selectedOption,String Name,String RoomNo) {

                String date1=date.getText().toString();

                String inputFormat = "dd/mm/yyyy";
                String outputFormat = "dd-mm-yyyy";

                String outputDate = convertDateFormat(date1, inputFormat, outputFormat);

                if(date1.isEmpty())
                {
                    Toast.makeText(attendence.this,"Enter the Date",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    HashMap<String,Object> data=new HashMap<String,Object>();
                    data.put("Date",outputDate);
                    data.put("Name",Name);
                    data.put("RoomNo",RoomNo);
                    data.put("Status",selectedOption);

                    FirebaseDatabase.getInstance().getReference().child("AttendenceSheet").child(outputDate).child(Name).setValue(data);

                }
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    fetchAtted studnetList=dataSnapshot.getValue(fetchAtted.class);
                    list.add(studnetList);
                }
                attendanceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date1=date.getText().toString();
                if(!(date1.isEmpty()))
                {
                    Toast.makeText(attendence.this,"Attendence Sheet Submitted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(attendence.this, viewAttendSheet.class);
                startActivity(intent);
            }
        });

    }

    String convertDateFormat(String inputDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
            Date date = inputDateFormat.parse(inputDate);
            return outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}