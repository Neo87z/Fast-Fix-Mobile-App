package com.example.madfinal.ClientSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinal.HomeActivity;
import com.example.madfinal.Models.ServiceRequests;
import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeMyRequest extends AppCompatActivity {
    TextView Displayame;
    EditText Title,Desc,Phone;
    Button Update;
    DatePicker date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_my_request);
        Intent i1= getIntent();
        final String Id=i1.getStringExtra("ID").toString();
        SessionManagement s1= new SessionManagement(ChangeMyRequest.this);
        final String user= s1.getSessionUsername();
        Update=findViewById(R.id.MakeOffer_button);
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData(Id,user);

            }
        });
        DatabaseReference Readref= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child(user).child(Id);
        Readref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Title=findViewById(R.id.ex_makeoferTitle);
                Desc=findViewById(R.id.ex_make_description);
                Phone=findViewById(R.id.Ex_makeofferPhone);
                Displayame=findViewById(R.id.Displayname);
                Displayame.setText(user);
                Title.setText(dataSnapshot.child("title").getValue().toString());
                Desc.setText(dataSnapshot.child("description").getValue().toString());
                Phone.setText(dataSnapshot.child("phone").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void UpdateData(String ID,String user){

        DatabaseReference UpdateRef= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child(user).child(ID);
        Title=findViewById(R.id.ex_makeoferTitle);
        Desc=findViewById(R.id.ex_make_description);
        Phone=findViewById(R.id.Ex_makeofferPhone);
        ServiceRequests s1= new ServiceRequests();
        s1.setAssingedto("Request no yet assinged");
        s1.setDate("2-11-1926");
        s1.setDescription(Desc.getText().toString());
        s1.setPhone(Phone.getText().toString());
        s1.setRequestID(ID);
        s1.setSkipval("0");
        s1.setTitle(Title.getText().toString());
        s1.setUser(user);
        UpdateRef.setValue(s1);
        Toast.makeText(this, "Request Sucessfully Updated", Toast.LENGTH_SHORT).show();
        Intent i1= new Intent(ChangeMyRequest.this, HomeActivity.class);
        startActivity(i1);


    }
}