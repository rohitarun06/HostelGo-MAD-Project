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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class viewAttendSheet extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    viewSheetAdapter sheetAdapter;
    ArrayList list;
    EditText date;
    Button view;
    String date1;

    ImageView refresh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attend_sheet);

        date=findViewById(R.id.viewSheetDate);
        view=findViewById(R.id.viewSheetBtn);
        refresh=findViewById(R.id.refreshimageView);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(viewAttendSheet.this, viewAttendSheet.class);
//                startActivity(intent);
                list.clear();
                sheetAdapter.notifyDataSetChanged();
                date.setText("");
            }
        });

        list=new ArrayList<>();
        recyclerView=findViewById(R.id.viewSheetRecyc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sheetAdapter=new viewSheetAdapter(this,list);
        recyclerView.setAdapter(sheetAdapter);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date1=date.getText().toString();

                String inputFormat = "dd/mm/yyyy";
                String outputFormat = "dd-mm-yyyy";

//                String outputDate = convertDateFormat(date1, inputFormat, outputFormat);

                if(date1.isEmpty())
                {
                    Toast.makeText(viewAttendSheet.this,"Enter the Date",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String outputDate = convertDateFormat(date1, inputFormat, outputFormat);

                    databaseReference= FirebaseDatabase.getInstance().getReference("AttendenceSheet").child(outputDate);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot:snapshot.getChildren())
                            {
                                fetchViewAttendence viewSheet=dataSnapshot.getValue(fetchViewAttendence.class);
                                list.add(viewSheet);
                            }
                            sheetAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });

                }

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