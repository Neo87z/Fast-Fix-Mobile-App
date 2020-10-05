package com.example.madfinal.ClientSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinal.Models.TechOffers;
import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRequestOfferedService extends AppCompatActivity {
    TextView TechName,Stype,abtService,esitametdel,prange,custname;
    EditText Cnum,Issue;
    SessionManagement USer;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request_offered_service);

        Intent i1= getIntent();
        final String ID=i1.getStringExtra("RequestID").toString();
        Save=findViewById(R.id.SaveButton);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MakeOffer();
            }
        });
        DatabaseReference readTech= FirebaseDatabase.getInstance().getReference().child("TechnicianOfferedServices").child(ID);
        readTech.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    TechName=findViewById(R.id.TechName);
                    Stype=findViewById(R.id.SerType);
                    abtService=findViewById(R.id.abtService);
                    esitametdel=findViewById(R.id.EstimaetdDel);
                    prange=findViewById(R.id.abtService);
                    TechName.setText(dataSnapshot.child("techncianName").getValue().toString());
                    Stype.setText(dataSnapshot.child("servicetyep").getValue().toString());
                    abtService.setText(dataSnapshot.child("aboutServivce").getValue().toString());
                    esitametdel.setText(dataSnapshot.child("estimatedDelivery").getValue().toString());
                    prange.setText(dataSnapshot.child("price").getValue().toString());
                    USer=new SessionManagement(UserRequestOfferedService.this);
                    String USer1=USer.getSessionUsername();
                    custname=findViewById(R.id.CustNmae);
                    custname.setText(USer1);


                }catch (Exception e){
                    Toast.makeText(UserRequestOfferedService.this, "asd", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    public void MakeOffer(){
        Cnum=findViewById(R.id.ContacNum);
        Issue=findViewById(R.id.Ex_issue);
        TechName=findViewById(R.id.TechName);
        custname=findViewById(R.id.CustNmae);
        if(TextUtils.isEmpty(Cnum.getText().toString())){
            Toast.makeText(UserRequestOfferedService.this, "Please Enter Customer Number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Issue.getText().toString())) {
            Toast.makeText(UserRequestOfferedService.this, "Please Enter Issue", Toast.LENGTH_SHORT).show();
        }else{
            DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("TechOffers").child("InitialVal");
            readref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    if(dataSnapshot.hasChildren()){
                        TechOffers Toffer= new TechOffers();
                        String CurrentID=dataSnapshot.child("offerid").getValue().toString();
                        int ID=Integer.parseInt(CurrentID);
                        ID++;
                        DatabaseReference InsertRef=FirebaseDatabase.getInstance().getReference().child("TechOffers");
                        CurrentID=Integer.toString(ID);
                        Toffer.setCustomerName(custname.getText().toString());
                        Toffer.setCnum(Cnum.getText().toString());
                        Toffer.setIssue(Issue.getText().toString());
                        Toffer.setTechName(TechName.getText().toString());
                        Toffer.setSkip("0");
                        Toffer.setOfferID(CurrentID);
                        InsertRef.child(CurrentID).setValue(Toffer);
                        DatabaseReference InsertOffer=FirebaseDatabase.getInstance().getReference().child("TechOffers");
                        Toast.makeText(UserRequestOfferedService.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                        DatabaseReference Insertref2= FirebaseDatabase.getInstance().getReference().child("TechOffers").child("InitialVal");
                        Insertref2.child("offerid").setValue(CurrentID);
                        ClearControls();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }

    public  void ClearControls(){
        Cnum.setText("'");
        Issue.setText("'");

    }
}