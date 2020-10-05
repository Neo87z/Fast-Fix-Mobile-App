package com.example.madfinal.AdminNavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.example.madfinal.AdminSide.TechCompleteClose;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewTechRequestList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewTechRequestList extends Fragment {
    private DatabaseReference ReadAllRef;
    private ListView User_Reqeusts;
    private ArrayList<String> ServiceRequests1= new ArrayList<String>();
    SessionManagement UserSession;
    Fragment fragment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewTechRequestList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewTechRequestList.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewTechRequestList newInstance(String param1, String param2) {
        ViewTechRequestList fragment = new ViewTechRequestList();
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
        View v = inflater.inflate(R.layout.fragment_view_tech_request_list, container, false);
        ReadAllRef= FirebaseDatabase.getInstance().getReference().child("ServiceRequests");
        User_Reqeusts=  v.findViewById(R.id.TechView);
        UserSession = new  SessionManagement(getContext());
        UserSession.SaveSessioNkey("1");


        final ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,ServiceRequests1 );
        User_Reqeusts.setAdapter(arrayAdapter);
        User_Reqeusts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getContext(), "Item Clicked" +arrayAdapter.getItem(i), Toast.LENGTH_SHORT).show();
                Intent i1= new Intent(getContext(), TechCompleteClose.class);
                i1.putExtra("RequestID",arrayAdapter.getItem(i));
                startActivity(i1);
            }
        });

        ReadAllRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                try{

                    String key=UserSession.getSessionkey();
                    if(!(key.equals("null"))){
                        String Username1=UserSession.GetTechSession();
                        if(dataSnapshot.hasChildren()){

                            String Skipval=dataSnapshot.child("skipval").getValue().toString();
                            int skip=Integer.parseInt(Skipval);
                            if(skip != 1 ){
                                String Username=dataSnapshot.child("assingedto").getValue().toString();
                                if(Username.equals(Username1)){
                                    String value=dataSnapshot.child("requestID").getValue().toString();
                                    ServiceRequests1.add(value);
                                    arrayAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }

                }catch (Exception e){
                    Toast.makeText(getContext(), "Faoil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return (v);
    }


}