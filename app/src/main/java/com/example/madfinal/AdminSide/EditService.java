package com.example.madfinal.AdminSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.madfinal.Models.TechnicianServices;
import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditService extends AppCompatActivity {
    TextView Stype,eta,avgprice,abtservice;
    Button Edit;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);
        final Intent i1= getIntent();
        Edit=findViewById(R.id.button4);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateChanges(i1.getStringExtra("ID").toString());
            }
        });
        SessionManagement s1=new  SessionManagement(getApplicationContext());
        String id= i1.getStringExtra("ID").toString();
        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("TechnicianWiseViewServies").child(s1.GetTechSession()).child(id);
        readref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Stype=findViewById(R.id.SType);
                eta=findViewById(R.id.eta);
                avgprice=findViewById(R.id.avgprice);
                abtservice=findViewById(R.id.abservice);

                Stype.setText(dataSnapshot.child("servicetyep").getValue().toString());
                eta.setText(dataSnapshot.child("estimatedDelivery").getValue().toString());
                avgprice.setText(dataSnapshot.child("price").getValue().toString());
                abtservice.setText(dataSnapshot.child("aboutServivce").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void UpdateChanges(String ID){
        SessionManagement s1= new SessionManagement(getApplicationContext());
        Stype=findViewById(R.id.SType);
        eta=findViewById(R.id.eta);
        avgprice=findViewById(R.id.avgprice);
        abtservice=findViewById(R.id.abservice);
        TechnicianServices TS= new TechnicianServices();
        TS.setAboutServivce(abtservice.getText().toString());
        TS.setEstimatedDelivery(eta.getText().toString());
        TS.setServicetyep(Stype.getText().toString());
        TS.setPrice(avgprice.getText().toString());
        TS.setDeliverydate("0");
        TS.setServiceID(ID);
        TS.setSkip("0");
        TS.setTechncianName(s1.GetTechSession());
        DatabaseReference UpdateRef1= FirebaseDatabase.getInstance().getReference().child("TechnicianWiseViewServies").child(s1.GetTechSession()).child(ID);
        DatabaseReference UpdateRef2= FirebaseDatabase.getInstance().getReference().child("TechnicianOfferedServices").child(ID);
        UpdateRef1.setValue(TS);
        UpdateRef2.setValue(TS);
    }
}