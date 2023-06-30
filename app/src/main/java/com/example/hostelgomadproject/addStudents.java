package com.example.hostelgomadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addStudents extends AppCompatActivity {
     EditText studentName,usn,roomNo;
     Button addStudent,studentAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);

        studentName=findViewById(R.id.studentName);
        usn=findViewById(R.id.studentUSN);
        roomNo=findViewById(R.id.studentRoomNo);
        addStudent=findViewById(R.id.addStudent);
        studentAcc=findViewById(R.id.createStudentAcc);

        studentAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=getIntent().getExtras();
                Bundle bundle1= new Bundle();
                bundle1.putString("email",bundle.getString("email"));
                bundle1.putString("password",bundle.getString("password"));
                Intent intent = new Intent(addStudents.this, studentRegister.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName1=studentName.getText().toString();
                String usn1=usn.getText().toString();
                String roomNo1=roomNo.getText().toString();

                HashMap<String,Object> data=new HashMap<String,Object>();
                data.put("Name",studentName1);
                data.put("USN",usn1);
                data.put("RoomNo",roomNo1);
                FirebaseDatabase.getInstance().getReference().child("StudentList").push().setValue(data);
                Toast.makeText(addStudents.this,"Student: Added Successfully",Toast.LENGTH_SHORT).show();
                Toast.makeText(addStudents.this,"Create Account for the Student: "+studentName1,Toast.LENGTH_SHORT).show();

                roomNo.setText("");
                usn.setText("");
                studentName.setText("");
            }
        });

    }
}