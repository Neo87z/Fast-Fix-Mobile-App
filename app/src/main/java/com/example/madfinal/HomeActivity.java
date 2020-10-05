package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.madfinal.SessionManagement.SessionManagement;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {
    TextView UserName;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SessionManagement sessionManagement= new SessionManagement(HomeActivity.this);
        String username=sessionManagement.getSessionUsername();
        //UserName=findViewById(R.id.userrname);
       // UserName.setText(username);

        final DrawerLayout drawerLayout= findViewById(R.id.drawerLayout);
        findViewById(R.id.imgmanue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView=findViewById(R.id.NavigationView);
        navigationView.setItemIconTintList(null);

        NavController navController= Navigation.findNavController(this,R.id.navigationHost);
        NavigationUI.setupWithNavController(navigationView,navController);




    }

}