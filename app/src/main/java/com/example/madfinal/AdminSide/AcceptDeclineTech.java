package com.example.madfinal.AdminSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinal.AdminNavigation.Hometech;
import com.example.madfinal.R;
import com.example.madfinal.Models.ServiceRequests;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcceptDeclineTech extends AppCompatActivity {
    TextView RequestID,Requestby,title,desc,dead,phone,ass;
    Button Accept;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_decline_tech);

        final Intent i1=getIntent();
        RequestID=findViewById(R.id.RequestID);
        id=i1.getStringExtra("ID").toString();
        RequestID.setText(i1.getStringExtra("ID").toString());
        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("TechSideClientRequets").child(RequestID.getText().toString());
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Requestby=findViewById(R.id.RequestedBy);
                title=findViewById(R.id.Title);
                desc=findViewById(R.id.Description);
                dead=findViewById(R.id.Deadline);
                phone=findViewById(R.id.Phone);
                ass=findViewById(R.id.Asssingedto   );
                Requestby.setText(dataSnapshot.child("user").getValue().toString());
                title.setText(dataSnapshot.child("title").getValue().toString());
                desc.setText(dataSnapshot.child("description").getValue().toString());
                dead.setText(dataSnapshot.child("date").getValue().toString());
                phone.setText(dataSnapshot.child("phone").getValue().toString());
                ass.setText(dataSnapshot.child("assingedto").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Accept=findViewById(R.id.approove);
        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcceptOffer(i1.getStringExtra("ID").toString());
            }
        });

    }

    public void AcceptOffer(String ID){
        SessionManagement s1= new SessionManagement(getApplicationContext());
        ass=findViewById(R.id.Asssingedto);
        DatabaseReference InsertRef= FirebaseDatabase.getInstance().getReference().child("TechnicianAcceptRequets").child(s1.GetTechSession());
        Requestby=findViewById(R.id.RequestedBy);
        title=findViewById(R.id.Title);
        desc=findViewById(R.id.Description);
        dead=findViewById(R.id.Deadline);
        phone=findViewById(R.id.Phone);
        ass=findViewById(R.id.Asssingedto);
        SessionManagement tech=new SessionManagement(AcceptDeclineTech.this);
        String tech1=tech.GetTechSession();
        ServiceRequests SRequest=new ServiceRequests();
        SRequest.setRequestID(id);
        SRequest.setAssingedto(tech1);
        SRequest.setUser(Requestby.getText().toString());
        SRequest.setTitle(title.getText().toString());
        SRequest.setDescription(desc.getText().toString());
        SRequest.setDate(dead.getText().toString());
        SRequest.setPhone(phone.getText().toString());
        SRequest.setSkipval("0");
        InsertRef.child(id).setValue(SRequest);
        DatabaseReference DeleteRef= FirebaseDatabase.getInstance().getReference().child("TechSideClientRequets").child(ID);
        DeleteRef.removeValue();
    }
}