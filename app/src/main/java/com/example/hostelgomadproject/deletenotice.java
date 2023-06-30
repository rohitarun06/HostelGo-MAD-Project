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

public class deletenotice extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    wardenNoticeAdapter wardenNoticeAdapter;
    ArrayList list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletenotice);

        recyclerView=findViewById(R.id.dltNoticeRecyc);
        databaseReference= FirebaseDatabase.getInstance().getReference("NOTICE");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        wardenNoticeAdapter=new wardenNoticeAdapter(this,list);
        recyclerView.setAdapter(wardenNoticeAdapter);

        wardenNoticeAdapter.setOnItenClickListener(new wardenNoticeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                list.remove(position);
                wardenNoticeAdapter.notifyItemRemoved(position);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    fetchNotice noticeList=dataSnapshot.getValue(fetchNotice.class);
                    list.add(noticeList);
                }
                Collections.reverse(list);
                wardenNoticeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}