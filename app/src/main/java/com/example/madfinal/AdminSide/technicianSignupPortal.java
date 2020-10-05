package com.example.madfinal.AdminSide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madfinal.MainActivity;
import com.example.madfinal.Models.Technician;
import com.example.madfinal.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class technicianSignupPortal extends AppCompatActivity {
    Button Save;

    CheckBox Android,IOS,Laptops,Camera,Television,Other,Agree;
    EditText About,price;

    Technician NewTech;
    DatabaseReference DbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_signup_portal);
        Save=findViewById(R.id.btn_Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAllData();

            }
        });



    }

    public void SaveAllData(){
        int x=1;
        int p=1;
        int agree=0;
        int selection=0;
        Android=findViewById(R.id.Chk_Android);
        IOS=findViewById(R.id.chk_Ios2);
        Laptops=findViewById(R.id.chk_laptops);
        Camera=findViewById(R.id.chk_camera);
        Television=findViewById(R.id.chk_televsion);
        Other=findViewById(R.id.chk_other);
        Agree=findViewById(R.id.chk_agree);
        About=findViewById(R.id.Edx_aboutyou);
        price=findViewById(R.id.Edx_price);
        try{
            if(TextUtils.isEmpty(About.getText().toString())){
                Toast.makeText(this, "Please Enter About you", Toast.LENGTH_SHORT).show();
                x=0;
            }
            else if(TextUtils.isEmpty(price.getText().toString())){
                Toast.makeText(this, "Please input price", Toast.LENGTH_SHORT).show();
                x=0;

            }
            else if(!(Android.isChecked()) && (!(IOS.isChecked())) && (!(Laptops.isChecked())) && (!(Camera.isChecked())) && (!(Television.isChecked())) && (!(Other.isChecked()))){
                selection=1;
            }

            if(selection == 1){
                Toast.makeText(this, "Please atleast select other option", Toast.LENGTH_SHORT).show();
            }
            if(!(Agree.isChecked()) && selection == 0  ){
                Toast.makeText(this, "Please click Agreee Button to continue", Toast.LENGTH_SHORT).show();
                agree=1;
            }
            if(selection == 0 && agree== 0){
                DbRef= FirebaseDatabase.getInstance().getReference().child("Technician");
                NewTech= new Technician();
                Intent i1=getIntent();
                NewTech.setFirstName(i1.getStringExtra("FName").toString().trim());
                NewTech.setLastName(i1.getStringExtra("LName").toString().trim());
                NewTech.setDisplayName(i1.getStringExtra("DName").toString().trim());
                NewTech.setPassword(i1.getStringExtra("Pass").toString().trim());
                NewTech.setConPassword(i1.getStringExtra("CPass").toString().trim());
                NewTech.setDob(i1.getStringExtra("Date").toString().trim());
                NewTech.setEmail(i1.getStringExtra("Email").toString().trim());
                NewTech.setPhone(i1.getStringExtra("Phone").toString().trim());
                NewTech.setAbout(About.getText().toString().trim());
                NewTech.setPrice(Integer.parseInt(price.getText().toString()));
                if(Android.isChecked()){
                    NewTech.setIOS("True");
                }
                if(IOS.isChecked()){
                    NewTech.setIOS("True");
                }
                if(Laptops.isChecked()){
                    NewTech.setLaptops("True");
                }
                if(Camera.isChecked()){
                    NewTech.setCamera("True");
                }
                if(Television.isChecked()){
                    NewTech.setTelevision("True");
                }
                if(Other.isChecked()){
                    NewTech.setOther("True");
                }
                DbRef.child(NewTech.getDisplayName()).setValue(NewTech);
                Intent i2= new Intent(this, MainActivity.class);
                Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
                startActivity(i2);
            }


        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

        }



    }
}