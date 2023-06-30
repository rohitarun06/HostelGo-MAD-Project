package com.example.hostelgomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class viewcomplaint extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ComplaintAdapter complaintAdapter;
    ArrayList list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomplaint);

        recyclerView=findViewById(R.id.complaintRecyc);
        databaseReference= FirebaseDatabase.getInstance().getReference("COMPLAINTS");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        complaintAdapter=new ComplaintAdapter(this,list);
        recyclerView.setAdapter(complaintAdapter);

        complaintAdapter.setOnItenClickListener(new ComplaintAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                list.remove(position);
                complaintAdapter.notifyItemRemoved(position);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    fetchComplaints complaintsList=dataSnapshot.getValue(fetchComplaints.class);
                    list.add(complaintsList);
                }
                Collections.reverse(list);
                complaintAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}