package com.example.madfinal.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinal.R;
import com.example.madfinal.Models.ServiceRequests;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcceptDeclineOfferTech extends AppCompatActivity {
    TextView Cusname,Cnum,Issue;
    Button Accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_decline_offer_tech);
        Intent i1=getIntent();
        String ID=i1.getStringExtra("RequestID").toString();
        Accept=findViewById(R.id.btn_Accept);
        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("TechOffers").child(ID);
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cusname=findViewById(R.id.Custname);
                Cnum=findViewById(R.id.CoNum);
                Issue=findViewById(R.id.Issue);
                Cusname.setText(dataSnapshot.child("customerName").getValue().toString());
                Cnum.setText(dataSnapshot.child("cnum").getValue().toString());
                Issue.setText(dataSnapshot.child("issue").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptOffer();

            }
        });
    }


    public void AcceptOffer(){
        Cusname=findViewById(R.id.Custname);
        Cnum=findViewById(R.id.CoNum);
        Issue=findViewById(R.id.Issue);
        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child("IntitalRequest");
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    SessionManagement tech=new SessionManagement(AcceptDeclineOfferTech.this);
                    String tech1=tech.GetTechSession();
                    ServiceRequests SRequest=new ServiceRequests();
                    String CurrentID=dataSnapshot.child("ID").getValue().toString();
                    int ID=Integer.parseInt(CurrentID);
                    ID++;
                    CurrentID=Integer.toString(ID);
                    DatabaseReference InsertRef=FirebaseDatabase.getInstance().getReference().child("ServiceRequests");
                    SRequest.setDescription(Issue.getText().toString());
                    SRequest.setTitle("Requested Directly Via Technician Requests ");
                    SRequest.setUser(Cusname.getText().toString());
                    SRequest.setPhone(Cnum.getText().toString());
                    SRequest.setDate("Requested Directly Via Technician Requests ");
                    SRequest.setRequestID(CurrentID);
                    SRequest.setSkipval("0");
                    SRequest.setAssingedto(tech1);
                    InsertRef.child(CurrentID).setValue(SRequest);
                    Toast.makeText(AcceptDeclineOfferTech.this, "Request Made Sucessfully", Toast.LENGTH_SHORT).show();
                    DatabaseReference Insertref2= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child("IntitalRequest");
                    Insertref2.child("ID").setValue(CurrentID);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

