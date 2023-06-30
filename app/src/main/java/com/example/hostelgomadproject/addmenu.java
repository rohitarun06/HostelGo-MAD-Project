package com.example.hostelgomadproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addmenu extends AppCompatActivity {

    EditText breakfast,lunch,dinner;
    Spinner weekDay;
    Button updateMenu;
    String day,dayNo;

    String order;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu);

        breakfast=findViewById(R.id.breakfastMenu);
        lunch=findViewById(R.id.lunchMenu);
        dinner=findViewById(R.id.dinnerMenu);
        weekDay=findViewById(R.id.weekday);
        updateMenu=findViewById(R.id.menuButton);

        String[] items = getResources().getStringArray(R.array.my_spinner_foodmenu);
        ArrayAdapter<String> adapter = new ArrayAdapter(addmenu.this, android.R.layout.simple_spinner_item, items){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                view.setPadding(20, 20, 0, 20); // Adjust the top and bottom padding as needed
                return view;
            }
        };;
        weekDay.setAdapter(adapter);

        weekDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where no item is selected, if needed
            }
        });

        updateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String breakfast1=breakfast.getText().toString();
                String lunch1=lunch.getText().toString();
                String  dinner1=dinner.getText().toString();

                HashMap<String,Object> data=new HashMap<String,Object>();
                data.put("Weekday",day);
                data.put("Breakfast",breakfast1);
                data.put("Lunch",lunch1);
                data.put("Dinner",dinner1);

                switch (day)
                {
                    case "Monday":order="1";break;
                    case "Tuesday":order="2";break;
                    case "Wednesday":order="3";break;
                    case "Thursday":order="4";break;
                    case "Friday":order="5";break;
                    case "Saturday":order="6";break;
                    case "Sunday":order="7";break;
                }

                FirebaseDatabase.getInstance().getReference().child("FoodMenu").child(order).setValue(data);
//                Toast.makeText(addmenu.this,"Menu Updated For "+day,Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(addmenu.this);
//                alertDialog.setTitle("Alert");
                alertDialog.setMessage(day+"'s Menu Updated");

// Create the AlertDialog
                final AlertDialog dialog = alertDialog.create();
                dialog.show();

// Delayed dismissal of the AlertDialog after a certain time interval
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                }, 2000);

                breakfast.setText("");
                lunch.setText("");
                dinner.setText("");

            }
        });

    }
}