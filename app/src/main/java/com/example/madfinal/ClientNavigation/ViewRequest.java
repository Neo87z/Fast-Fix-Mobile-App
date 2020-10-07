package com.example.madfinal.ClientNavigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.madfinal.ClientSide.ChangeMyRequest;
import com.example.madfinal.Models.ServiceRequests;
import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.example.madfinal.ClientSide.userInsideRequest;
import com.example.madfinal.ViewHolder.MyRequestViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewRequest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewRequest extends Fragment {
    SessionManagement UserSession;
    Fragment fragment;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SessionManagement s1;
    String User;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewRequest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewRequest.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewRequest newInstance(String param1, String param2) {
        ViewRequest fragment = new ViewRequest();
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
        final View v = inflater.inflate(R.layout.fragment_view_request, container, false);

        s1=  new SessionManagement(getContext());
        User=s1.getSessionUsername();

                ProductsRef = FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child(User);
                recyclerView = v.findViewById(R.id.RecView);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                FirebaseRecyclerOptions<ServiceRequests> options =
                        new FirebaseRecyclerOptions.Builder<ServiceRequests>()
                                .setQuery(ProductsRef, ServiceRequests.class)
                                .build();
                FirebaseRecyclerAdapter<ServiceRequests, MyRequestViewHolder> adapter =
                        new FirebaseRecyclerAdapter<ServiceRequests, MyRequestViewHolder>(options) {
                            @Override
                            protected void onBindViewHolder(@NonNull MyRequestViewHolder holder, int position, @NonNull final ServiceRequests model)
                            {
                                holder.Description.setText(model.getDescription());
                                holder.Title.setText(model.getTitle());
                                holder.UpdateButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        ViewRequestUser(model.getRequestID());
                                    }
                                });

                                holder.RemoveButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        CloseRequest(model.getRequestID());
                                    }
                                });
                            }
                            @NonNull
                            @Override
                            public MyRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                            {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_request_layout, parent, false);
                                MyRequestViewHolder holder = new MyRequestViewHolder(view);
                                return holder;
                            }
                        };
                recyclerView.setAdapter(adapter);
                adapter.startListening();
        return  v;
    }

    public void ViewRequestUser(String ID){
        Intent i1= new Intent(getContext(), ChangeMyRequest.class);
        i1.putExtra("ID",ID);
        startActivity(i1);
    }
    public void CloseRequest(String ID){
        DatabaseReference Deleteref= FirebaseDatabase.getInstance().getReference().child("ServiceRequests").child(User).child(ID);
        Deleteref.removeValue();

    }
}