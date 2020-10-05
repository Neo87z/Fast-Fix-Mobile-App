package com.example.madfinal.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewInsideServiceRequest extends AppCompatActivity {
    TextView Stype,eta,avgprice,abtservice;
    Button Edit;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inside_service_request);

        Intent i1=getIntent();
         id =i1.getStringExtra("ID").toString();

        Edit=findViewById(R.id.EditService);
        SessionManagement s1= new SessionManagement(getApplicationContext());
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editservice(id);
            }
        });
        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("TechnicianWiseViewServies").child(s1.GetTechSession()).child(id);
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void Editservice(String ID){
        Intent I1= new Intent(getApplicationContext(),EditService.class);
        I1.putExtra("ID",ID);
        startActivity(I1);

    }
}