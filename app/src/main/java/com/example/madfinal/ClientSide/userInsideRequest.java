package com.example.madfinal.ClientSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userInsideRequest extends AppCompatActivity {
    TextView RequestId1,Requestedby,title,desc,deadline,phone,assingedto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_inside_request);

        Intent i1=getIntent();
        RequestId1=findViewById(R.id.RequestedBy);
        SessionManagement user= new SessionManagement(userInsideRequest.this);


        RequestId1.setText(i1.getStringExtra("ID").toString());
        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child(user.getSessionUsername().toString()).child(RequestId1.getText().toString());
        readref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                phone.setText(dataSnapshot.child("phone").getValue().toString());
               assingedto.setText(dataSnapshot.child("assingedto").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}