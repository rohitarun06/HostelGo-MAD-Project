package com.example.hostelgomadproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class studentRegister extends AppCompatActivity {

    EditText email,password,repass;
    Button createAcc;
    FirebaseAuth auth;
    String wemail,wpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        email=findViewById(R.id.studentEmail);
        password=findViewById(R.id.studentPassword);
        repass=findViewById(R.id.studentRrepass);
        createAcc=findViewById(R.id.studentAcc);
        auth=FirebaseAuth.getInstance();

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getText().toString();
                String password1=password.getText().toString();
                String repass1=repass.getText().toString();


                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(repass1))
                {
                    Toast.makeText(studentRegister.this,"Field is Empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password1.equals(repass1))
                    {
                        registerStudent(email1,password1);
                        email.setText("");
                        password.setText("");
                        repass.setText("");
                    }
                    else
                    {
                        Toast.makeText(studentRegister.this,"Password Doesn't Match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    void registerStudent(String email,String password)
    {

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(studentRegister.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(studentRegister.this,"Student Account Created",Toast.LENGTH_SHORT).show();
                    HashMap<String,Object> data=new HashMap<String,Object>();
                    data.put("EmailID",email);
                    data.put("Category","Student");
                    data.put("userId",auth.getUid());
                    FirebaseDatabase.getInstance().getReference().child("USER").child(auth.getUid()).setValue(data);
                    auth.signOut();
                    Bundle bundle=getIntent().getExtras();
                    wemail=bundle.getString("email");
                    wpass=bundle.getString("password");
                    auth.signInWithEmailAndPassword(wemail,wpass);
                }
                else
                {
                    Toast.makeText(studentRegister.this,"Account Creation Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}