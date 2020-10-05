package com.example.madfinal.AdminNavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.example.madfinal.Models.TechnicianServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TechnicianMakeOffer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TechnicianMakeOffer extends Fragment {
    TextView DisplayName;
    EditText ServiceType,AboutService,EstimateDel,PriceRange;
    SessionManagement techSession;
    Button Save;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TechnicianMakeOffer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TechnicianMakeOffer.
     */
    // TODO: Rename and change types and number of parameters
    public static TechnicianMakeOffer newInstance(String param1, String param2) {
        TechnicianMakeOffer fragment = new TechnicianMakeOffer();
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
        final View v= inflater.inflate(R.layout.fragment_technician_make_offer, container, false);
        techSession=new SessionManagement(getContext());
        String name=techSession.GetTechSession();
        DisplayName=v.findViewById(R.id.displayName);
        DisplayName.setText(name);
        Save=v.findViewById(R.id.SaevButton);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveTechService(v);
            }
        });


        return (v);
    }

    public void SaveTechService(View view){

        DisplayName=view.findViewById(R.id.displayName);
        //DisplayName.setText(name);
        ServiceType=view.findViewById(R.id.Ex_ServicEtype);
        AboutService=view.findViewById(R.id.Ex_AbotService);
        EstimateDel=view.findViewById(R.id.Ex_EsitamtedDeliver);
        PriceRange=view.findViewById(R.id.Ex_priceRange);
        try{
            if(TextUtils.isEmpty(ServiceType.getText().toString())){
                Toast.makeText(getContext(), "Please Enter Service Type", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty((AboutService.getText().toString()))){
                Toast.makeText(getContext(), "Please enter About Service", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(EstimateDel.getText().toString())){
                Toast.makeText(getContext(), "Please enter Estimated Delivery", Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(PriceRange.getText().toString())){
                Toast.makeText(getContext(), "Please enter price Range", Toast.LENGTH_SHORT).show();
            }else{

                final DatabaseReference insertRef= FirebaseDatabase.getInstance().getReference().child("TechnicianOfferedServices");
                DatabaseReference ReadRef= FirebaseDatabase.getInstance().getReference().child("TechnicianOfferedServices").child("InitialVal");
                ReadRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ID=dataSnapshot.child("serviceid").getValue().toString();
                        int ID1= Integer.parseInt(ID);
                        ID1++;
                        ID=Integer.toString(ID1);
                        TechnicianServices TS= new TechnicianServices();
                        TS.setTechncianName(DisplayName.getText().toString());
                        TS.setServiceID(ID);
                        TS.setAboutServivce(AboutService.getText().toString());
                        TS.setEstimatedDelivery(EstimateDel.getText().toString());
                        TS.setDeliverydate(DisplayName.getText().toString());
                        TS.setSkip("0");
                        TS.setServicetyep(ServiceType.getText().toString());
                        TS.setPrice(PriceRange.getText().toString());
                        insertRef.child(ID).setValue(TS);
                        DatabaseReference Inser2Ref=FirebaseDatabase.getInstance().getReference().child("TechnicianOfferedServices").child("InitialVal");
                        DatabaseReference Inser2Ref2=FirebaseDatabase.getInstance().getReference().child("TechnicianWiseViewServies").child(TS.getTechncianName());
                        Inser2Ref2.child(ID).setValue(TS);
                        Inser2Ref.child("serviceid").setValue(ID);
                        Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                        Clearcontrols();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }catch (Exception e){

        }

    }
    public void Clearcontrols (){

        ServiceType.setText("");
        AboutService.setText("");
        EstimateDel.setText("");
        PriceRange.setText("");
    }
}