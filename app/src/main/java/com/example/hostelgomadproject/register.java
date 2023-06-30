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

public class register extends AppCompatActivity {

    EditText email,password,repass;
    Button wardenRegister;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.studentEmail);
        password=findViewById(R.id.studentPassword);
        repass=findViewById(R.id.studentRrepass);
        wardenRegister=findViewById(R.id.studentAcc);
        auth=FirebaseAuth.getInstance();

        wardenRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getText().toString();
                String password1=password.getText().toString();
                String repass1=repass.getText().toString();



                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(repass1))
                {
                    Toast.makeText(register.this,"Field is Empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password1.equals(repass1))
                    {
                        registerWarden(email1,password1);
                        email.setText("");
                        password.setText("");
                        repass.setText("");
                    }
                    else
                    {
                        Toast.makeText(register.this,"Password Doesn't Match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    void registerWarden(String email,String password)
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(register.this,"Warden Account Created",Toast.LENGTH_SHORT).show();
                    HashMap<String,Object> data=new HashMap<String,Object>();
                    data.put("EmailID",email);
                    data.put("Category","Warden");
                    data.put("userId",auth.getUid());
                    FirebaseDatabase.getInstance().getReference().child("USER").child(auth.getUid()).setValue(data);
                    Intent intent = new Intent(register.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(register.this,"Account Creation Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}