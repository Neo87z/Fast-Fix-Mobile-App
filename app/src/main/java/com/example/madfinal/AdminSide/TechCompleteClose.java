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

public class TechCompleteClose extends AppCompatActivity {
    TextView RequestId1,Requestedby,title,desc,deadline,phone,assingedto;
    Button CompleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_complete_close);
        final Intent i1=getIntent();
        final SessionManagement s1= new SessionManagement(getApplicationContext());
        RequestId1=findViewById(R.id.RequestID);
        CompleteButton=findViewById(R.id.button);
        CompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CompleteRequest(s1.GetTechSession(),i1.getStringExtra("ID").toString(),i1.getStringExtra("User").toString());
            }
        });

        RequestId1.setText(i1.getStringExtra("ID").toString());
        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("TechnicianAcceptRequets").child(s1.GetTechSession()).child(RequestId1.getText().toString());
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestId1.setText(i1.getStringExtra("ID").toString());
                Requestedby=findViewById(R.id.RequestedBy);
                title=findViewById(R.id.Title);
                desc=findViewById(R.id.Description);
                deadline=findViewById(R.id.Deadline);
                phone=findViewById(R.id.Phone);
                assingedto=findViewById(R.id.Asssingedto);
                Requestedby.setText(dataSnapshot.child("user").getValue().toString());
                title.setText(dataSnapshot.child("title").getValue().toString());
                desc.setText(dataSnapshot.child("description").getValue().toString());
                deadline.setText(dataSnapshot.child("date").getValue().toString());
             //   assingedto.setText(dataSnapshot.child("assingedto").getValue().toString());
//                phone.setText(dataSnapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public  void CompleteRequest(String tech,String ID,String User){
        DatabaseReference Delteref1= FirebaseDatabase.getInstance().getReference().child("TechnicianAcceptRequets").child(tech).child(ID);
        DatabaseReference Delteref2= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child(ID);
        Delteref1.removeValue();
        Delteref2.removeValue();
    }
}