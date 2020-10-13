package com.example.madfinal.ClientNavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MakeRequest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MakeRequest extends Fragment {
    TextView Displayame;
    EditText Title,Desc,Phone;
    Button Save;
    DatePicker date;
    SessionManagement user;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MakeRequest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MakeRequest.
     */
    // TODO: Rename and change types and number of parameters
    public static MakeRequest newInstance(String param1, String param2) {
        MakeRequest fragment = new MakeRequest();
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
        final View v= inflater.inflate(R.layout.fragment_make_request, container, false);
        Displayame=v.findViewById(R.id.Displayname);
        user= new SessionManagement(getContext());

        String user1=user.getSessionUsername();
        Displayame.setText(user1);

        Save=v.findViewById(R.id.MakeOffer_button);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeOffer(v);
            }
        });

        return v;
    }

    public void makeOffer(View view){
        int datechek=0;
        Title=view.findViewById(R.id.ex_makeoferTitle);
        Desc=view.findViewById(R.id.ex_make_description);
        Phone=view.findViewById(R.id.Ex_makeofferPhone);
        date=view.findViewById(R.id.Ex_DOB);
        try{
            if(TextUtils.isEmpty(Title.getText().toString())){
                Toast.makeText(getContext(), "Please Enter Title", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(Desc.getText().toString())){
                Toast.makeText(getContext(), "Please Enter Description", Toast.LENGTH_SHORT).show();
            }else{
                int phone1;
                phone1=Integer.parseInt(Phone.getText().toString());
                if(CheckPhone(phone1) == 0){
                    final String date1 =CheckDate(date);
                    if (date1.equals("null")){
                        datechek=1;
                    }
                    if(datechek == 0){
                        DatabaseReference readref= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child("IntitalRequest");
                        readref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChildren()){
                                    String Username=user.getSessionUsername().toString();
                                    ServiceRequests SRequest=new ServiceRequests();
                                    String CurrentID=dataSnapshot.child("requestID").getValue().toString();
                                    int ID=Integer.parseInt(CurrentID);
                                    ID++;
                                    CurrentID=Integer.toString(ID);
                                    DatabaseReference InsertRef=FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child(Username);
                                    SRequest.setDescription(Desc.getText().toString());
                                    SRequest.setTitle(Title.getText().toString());
                                    SRequest.setUser(Username);
                                    SRequest.setPhone(Phone.getText().toString());
                                    SRequest.setDate(date1);
                                    SRequest.setRequestID(CurrentID);
                                    SRequest.setSkipval("0");
                                    SRequest.setAssingedto("Request no yet assinged");
                                    InsertRef.child(CurrentID).setValue(SRequest);
                                    Toast.makeText(getContext(), "Request Made Sucessfully", Toast.LENGTH_SHORT).show();
                                    DatabaseReference Insertref2= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child("IntitalRequest");
                                    Insertref2.child("requestID").setValue(CurrentID);
                                    DatabaseReference InsertRef2=FirebaseDatabase.getInstance().getReference().child("TechSideClientRequets");
                                    InsertRef2.child(CurrentID).setValue(SRequest);
                                    ClearControls();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }


                }

            }

        }catch (NumberFormatException e){
            Toast.makeText(getContext(), "Invalid Phone number, Check again. Number of digits must be less than 10", Toast.LENGTH_SHORT).show();

        }
    }

    public void ClearControls(){
        Title.setText("");
        Desc.setText("");
        Phone.setText("");
    }
    public String CheckDate(DatePicker date){
        int   day  = date.getDayOfMonth();
        int   month= date.getMonth();
        int   year = date.getYear();

        String day1;
        String Month1;
        String year1;
        day1=Integer.toString(day);
        Month1=Integer.toString(month);
        year1=Integer.toString(year);
        String finaldate=day1+"-"+Month1+"-"+year1;

        return(finaldate);
    }

    public int CheckPhone(int phone){
        int length=String.valueOf(phone).length();
        if(length == 7){
            return 0;
        }
        else if(length < 7){
            Toast.makeText(getContext(), "Invalid Phone number, Check again ", Toast.LENGTH_SHORT).show();
            return 1;
        }
        else if(length >= 8){
            Toast.makeText(getContext(), "Invalid Phone number, Check again ", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

}