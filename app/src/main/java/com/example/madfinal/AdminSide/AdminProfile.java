package com.example.madfinal.AdminSide;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.madfinal.ClientSide.UpdateAccpunt;
import com.example.madfinal.MainActivity;
import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminProfile extends Fragment {
    TextView fname,lname,uname,dob,email,contact;
    Button Del;
    Button update;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminProfile newInstance(String param1, String param2) {
        AdminProfile fragment = new AdminProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_admin_profile, container, false);
        update=v.findViewById(R.id.SaveButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getContext(), UpdateAdminProfile.class);
                startActivity(i2);
            }
        });
        fname=v.findViewById(R.id.userfname);
        lname=v.findViewById(R.id.userlname);
        uname=v.findViewById(R.id.useruname);
        dob=v.findViewById(R.id.userdob);
        email=v.findViewById(R.id.useremail);
        contact=v.findViewById(R.id.usercno);
        Del=v.findViewById(R.id.del);
        final SessionManagement s1= new SessionManagement(getContext());
        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference del=FirebaseDatabase.getInstance().getReference().child("Technician").child(s1.GetTechSession());
                del.removeValue();
                Intent i1= new Intent(getContext(), MainActivity.class);
                startActivity(i1);
            }
        });




        DatabaseReference readrfef= FirebaseDatabase.getInstance().getReference().child("Technician").child(s1.GetTechSession());
        readrfef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fname.setText(dataSnapshot.child("firstName").getValue().toString());
                lname.setText(dataSnapshot.child("lastName").getValue().toString());
                uname.setText(dataSnapshot.child("displayName").getValue().toString());
                dob.setText(dataSnapshot.child("dob").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                contact.setText(dataSnapshot.child("phone").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;


    }
}