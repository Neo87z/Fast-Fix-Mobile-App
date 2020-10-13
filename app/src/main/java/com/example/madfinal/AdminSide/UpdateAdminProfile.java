package com.example.madfinal.AdminSide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madfinal.HomeActivity;
import com.example.madfinal.Models.User;
import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateAdminProfile extends AppCompatActivity {

    Button S1;
    String pass;
    EditText fname,lnmae,dob,email,contact;
    TextView uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin_profile);
        S1=findViewById(R.id.SaveButton);
        fname=findViewById(R.id.userfname);
        lnmae=findViewById(R.id.userlname);
        uname=findViewById(R.id.useruname);
        dob=findViewById(R.id.userdob);
        email=findViewById(R.id.useremail);
        contact=findViewById(R.id.usercno);
        SessionManagement s1= new SessionManagement(getApplicationContext());
        DatabaseReference Readref= FirebaseDatabase.getInstance().getReference().child("Technician").child(s1.GetTechSession());
        Readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fname.setText(dataSnapshot.child("firstName").getValue().toString());
                lnmae.setText(dataSnapshot.child("lastName").getValue().toString());
                uname.setText(dataSnapshot.child("displayName").getValue().toString());
                dob.setText(dataSnapshot.child("dob").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                contact.setText(dataSnapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        S1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateAccountDetails(pass);
            }
        });
    }

    public void UpdateAccountDetails(String pass){
        fname=findViewById(R.id.userfname);
        lnmae=findViewById(R.id.userlname);
        uname=findViewById(R.id.useruname);
        dob=findViewById(R.id.userdob);
        email=findViewById(R.id.useremail);
        contact=findViewById(R.id.usercno);
        User newUser = new User();
        newUser.setFirstName(fname.getText().toString().trim());
        newUser.setLastName(lnmae.getText().toString().trim());
        newUser.setUserName(uname.getText().toString().trim());
        newUser.setPassword(pass);
        newUser.setEmail(email.getText().toString().trim());
        newUser.setPhone(contact.getText().toString().trim());
        newUser.setDOB(dob.getText().toString().trim());
        SessionManagement s1=new SessionManagement(getApplicationContext());
        DatabaseReference updateref=FirebaseDatabase.getInstance().getReference().child("Technician").child(s1.GetTechSession());
        updateref.setValue(newUser);
        Toast.makeText(this, "Account Updated", Toast.LENGTH_SHORT).show();
        Intent i1 = new Intent(this, TechnicianHome.class);
        startActivity(i1);

    }
}