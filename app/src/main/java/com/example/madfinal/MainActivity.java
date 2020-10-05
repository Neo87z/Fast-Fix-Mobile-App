package com.example.madfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinal.AdminSide.TechnicianHome;
import com.example.madfinal.Models.Technician;
import com.example.madfinal.Models.User;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button SingupButton;
    Button LoginButton;
    EditText Username,Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SingupButton=findViewById(R.id.SignupBoton1);
        SingupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartSignupProcess();
            }
        });
        LoginButton=findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckLoginDetails();

            }
        });

    }


    public void StartSignupProcess(){
        Intent startIntent= new Intent(this,SelectUserType.class);
        startActivity(startIntent);

    }

    public void CheckLoginDetails(){

        Username=findViewById(R.id.Ex_Username);
        Password=findViewById(R.id.Ex_password);
        try{

            if(TextUtils.isEmpty(Username.getText().toString())){
                Toast.makeText(this, "Please enter the Username", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(Password.getText().toString())){
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            }
            else{
                DatabaseReference readRef= FirebaseDatabase.getInstance().getReference().child("User").child(Username.getText().toString());
                final DatabaseReference readRefTech= FirebaseDatabase.getInstance().getReference().child("Technician").child(Username.getText().toString());
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String pass = null;
                        if(dataSnapshot.hasChildren()){
                            pass=dataSnapshot.child("password").getValue().toString();
                            if(pass.equals(Password.getText().toString())){
                                Toast.makeText(MainActivity.this, "Logged in as a User", Toast.LENGTH_SHORT).show();
                                User SessionUser= new User();
                                SessionUser.setUserName(Username.getText().toString());
                                SessionManagement sessionManagement= new SessionManagement(MainActivity.this);
                                sessionManagement.SaveSession(SessionUser);
                                Intent i1=new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(i1);


                            }
                            else{
                                Toast.makeText(MainActivity.this, "Password Does not matach the Given Username", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            readRefTech.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                    if(dataSnapshot1.hasChildren()){
                                        String pass2=null;
                                        pass2=dataSnapshot1.child("password").getValue().toString();
                                        if(pass2.equals(Password.getText().toString())){
                                                Technician tech= new Technician();
                                            tech.setDisplayName(Username.getText().toString());
                                            SessionManagement sessionManagement= new SessionManagement(MainActivity.this);
                                            sessionManagement.SavetechSession(tech);
                                            Intent i1=new Intent(getApplicationContext(), TechnicianHome.class);
                                            Toast.makeText(MainActivity.this, "Logged in As a Technician", Toast.LENGTH_SHORT).show();
                                            startActivity(i1);
                                        }
                                        else{
                                            Toast.makeText(MainActivity.this, "Password Does not match the Technician Name", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "Invalid Username. No such Technician or User. Please Register with the system", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                });
            }
        }
        catch (Exception e){

        }


    }
}