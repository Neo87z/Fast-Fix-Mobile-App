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

import com.example.madfinal.ClientSide.userInsideRequest;
import com.example.madfinal.Models.ServiceRequests;
import com.example.madfinal.Models.TechnicianServices;
import com.example.madfinal.R;
import com.example.madfinal.SessionManagement.SessionManagement;
import com.example.madfinal.ClientSide.UserRequestOfferedService;
import com.example.madfinal.ViewHolder.MyRequestViewHolder;
import com.example.madfinal.ViewHolder.TechnicianRequestViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
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


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        s1 = new SessionManagement(getContext());
        User = s1.getSessionUsername();

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("TechnicianOfferedServices");
        recyclerView = v.findViewById(R.id.RecView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<TechnicianServices> options =
                new FirebaseRecyclerOptions.Builder<TechnicianServices>()
                        .setQuery(ProductsRef, TechnicianServices.class)
                        .build();
        FirebaseRecyclerAdapter<TechnicianServices, TechnicianRequestViewHolder> adapter =
                new FirebaseRecyclerAdapter<TechnicianServices, TechnicianRequestViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull TechnicianRequestViewHolder holder, int position, @NonNull final TechnicianServices model) {

                        holder.Description.setText(model.getServicetyep());
                        holder.Title.setText(model.getPrice());
                    }

                    @NonNull
                    @Override
                    public TechnicianRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tech_request_layout, parent, false);
                        TechnicianRequestViewHolder holder = new TechnicianRequestViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        return v;
    }
}