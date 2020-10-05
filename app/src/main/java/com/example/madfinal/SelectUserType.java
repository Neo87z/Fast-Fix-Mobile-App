package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madfinal.AdminSide.ActivityTechSignup;
import com.example.madfinal.ClientSide.ActivitySignupUser;

public class SelectUserType extends AppCompatActivity {

    Button User,Tech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user_type);
        User=findViewById(R.id.ButtonUser);
        Tech=findViewById(R.id.ButtonTech);
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitiateUserSignup();
            }
        });

        Tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitiatetechSignup();
            }
        });

    }


    public void InitiateUserSignup(){
        Intent i1= new Intent(this, ActivitySignupUser.class);
        startActivity(i1);

    }

    public void InitiatetechSignup(){
        Intent i1= new Intent(this, ActivityTechSignup.class);
        startActivity(i1);

    }
}