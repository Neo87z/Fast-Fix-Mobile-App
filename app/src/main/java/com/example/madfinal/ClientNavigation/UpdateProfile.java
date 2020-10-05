package com.example.madfinal.ClientNavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.madfinal.R;

public class UpdateProfile extends AppCompatActivity {
    TextView fname,lname,uname,dob,email,contact;
    Button Del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        fname=findViewById(R.id.userfname);
        lname=findViewById(R.id.userlname);
        uname=findViewById(R.id.useruname);
        dob=findViewById(R.id.userdob);
        email=findViewById(R.id.useremail);
        contact=findViewById(R.id.usercno);
        Del=findViewById(R.id.saveButton2);

    }
}