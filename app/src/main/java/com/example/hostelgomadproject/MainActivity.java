package com.example.hostelgomadproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView signupText;
    public EditText email,password;
    Spinner type;
    FirebaseAuth auth;
    DatabaseReference dbase;
    String userType;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button)findViewById(R.id.studentAcc);
        signupText= (TextView) findViewById(R.id.signupText);
        email=findViewById(R.id.studentEmail);
        password=findViewById(R.id.studentPassword);
        auth=FirebaseAuth.getInstance();
        type=findViewById(R.id.userType);

        if(auth.getCurrentUser()!=null){
            String uid = auth.getCurrentUser().getUid();
            FirebaseDatabase.getInstance().getReference("USER").child(uid).child("Category").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String category=snapshot.getValue(String.class);
                    if (category.equals("Student"))
                    {
                        Intent intent = new Intent(MainActivity.this, student.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent= new Intent(MainActivity.this, warden.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        String[] items = getResources().getStringArray(R.array.my_spinner_items);
        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, items){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                view.setPadding(20, 20, 0, 20); // Adjust the top and bottom padding as needed
                return view;
            }
        };
        type.setAdapter(adapter);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected, if needed
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1=email.getText().toString();
                String password1=password.getText().toString();


                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1))
                {
                    Toast.makeText(MainActivity.this,"Field is Empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login(email1,password1,userType);
                    email.setText("");
                    password.setText("");
                }
            }
        });




        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
//                return;
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    void login(String email,String password,String user)
    {
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(MainActivity.this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseDatabase.getInstance().getReference().child("USER").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                for (DataSnapshot snapshot1: snapshot.getChildren())
                                {
                                    userType userType1=snapshot1.getValue(userType.class);
                                    String emailID=userType1.getEmailID();
                                    String category=userType1.getCategory();
                                    if(email.equals(emailID))
                                    {
                                        if(user.equals(category))
                                        {
                                            Toast.makeText(MainActivity.this,"LoggedIn Successfully",Toast.LENGTH_SHORT).show();
                                            if(user.equals("Student"))
                                            {
                                                Intent intent = new Intent(MainActivity.this, student.class);
                                                startActivity(intent);
                                            }
                                            else
                                            {
                                                Bundle bundle= new Bundle();
                                                bundle.putString("email",email);
                                                bundle.putString("password",password);
                                                Intent intent = new Intent(MainActivity.this, warden.class);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                            break;
                                        }
                                        else
                                        {
                                            Toast.makeText(MainActivity.this,"Invalid Login Type",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,"Account Not Created",Toast.LENGTH_SHORT).show();
                }
            });


    }
}