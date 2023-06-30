package com.example.hostelgomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView=findViewById(R.id.hostelgo);
        auth=FirebaseAuth.getInstance();
        lottieAnimationView.animate().translationY(-150).setDuration(2700).setStartDelay(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(SplashScreen.this,auth.getCurrentUser().toString(),Toast.LENGTH_SHORT).show();
                if(auth.getCurrentUser()!=null){
                    String uid = auth.getCurrentUser().getUid();
                    FirebaseDatabase.getInstance().getReference("USER").child(uid).child("Category").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String category=snapshot.getValue(String.class);
                            if (category.equals("Student"))
                            {
                                Intent intent = new Intent(SplashScreen.this, student.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Intent intent= new Intent(SplashScreen.this, warden.class);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);}
            }
        },2000);

    }
}