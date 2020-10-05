package com.example.madfinal.ClientSide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madfinal.MainActivity;
import com.example.madfinal.Models.User;
import com.example.madfinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class ActivitySignupUser extends AppCompatActivity {

    EditText FirstName,LastName,UserName,Password,CPassword,Email,Phone;
    DatePicker date;
    DatabaseReference dbref;
    User user1;
    User newUser;
    Button Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_user);
        Save=findViewById(R.id.btn_saveuser);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveAllData();

            }
        });

    }

    public void SaveAllData(){
        int x=1;
        int datechek=0;
        newUser= new User();
        FirstName=findViewById(R.id.Ex_FirstName);
        LastName=findViewById(R.id.Ex_LastName);
        UserName=findViewById(R.id.Ex_UserName);
        Password=findViewById(R.id.Ex_Password);
        CPassword=findViewById(R.id.Ex_ConPassword);
        date=findViewById(R.id.Ex_DOB);
        Email=findViewById(R.id.Ex_Email);
        Phone=findViewById(R.id.Ex_Phone);


        try{
            dbref= FirebaseDatabase.getInstance().getReference().child("User");
            if(TextUtils.isEmpty(FirstName.getText().toString())){
                Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                x=0;

            }
            else if(TextUtils.isEmpty(LastName.getText().toString())){
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                x=0;

            }
            else if(TextUtils.isEmpty(UserName.getText().toString())){
                Toast.makeText(this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
                x=0;

            }
            else if(TextUtils.isEmpty(Password.getText().toString())){
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                x=0;

            }
            else if(TextUtils.isEmpty(CPassword.getText().toString())){
                Toast.makeText(this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                x=0;

            }

            else if(TextUtils.isEmpty(Email.getText().toString())){
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                x=0;

            }
            else if(TextUtils.isEmpty(Phone.getText().toString())){
                Toast.makeText(this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                x=0;
            }

            if (x ==1){
                int phone1;

                phone1=Integer.parseInt(Phone.getText().toString());
                if(CheckPhone(phone1) == 0){
                    final String date1 =CheckDate(date);
                    if (date1.equals("null")){
                        datechek=1;
                    }
                    if(datechek == 0){
                        if(checkPassword(Password.getText().toString(),CPassword.getText().toString() )== 1){

                            if (x == 1){
                                if(checkPassword(Password.getText().toString()  ) == 0){
                                    DatabaseReference readRef=FirebaseDatabase.getInstance().getReference().child("User").child(UserName.getText().toString());
                                    readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if(dataSnapshot.hasChildren()){
                                                Toast.makeText(ActivitySignupUser.this, "Username Already taken", Toast.LENGTH_SHORT).show();

                                            }
                                            else {
                                                DatabaseReference readRef1=FirebaseDatabase.getInstance().getReference().child("Technician").child(UserName.getText().toString());
                                                readRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                                        if(dataSnapshot1.hasChildren()){
                                                            Toast.makeText(ActivitySignupUser.this, "Username Already taken", Toast.LENGTH_SHORT).show();
                                                        }
                                                        else{
                                                            newUser.setFirstName(FirstName.getText().toString().trim());
                                                            newUser.setLastName(LastName.getText().toString().trim());
                                                            newUser.setUserName(UserName.getText().toString().trim());
                                                            newUser.setPassword(Password.getText().toString().trim());
                                                            newUser.setEmail(Email.getText().toString().trim());
                                                            newUser.setPhone(Phone.getText().toString().trim());
                                                            newUser.setDOB(date1);
                                                            dbref.child(newUser.getUserName()).setValue(newUser);
                                                            ClearControls();
                                                            Intent i1= new Intent(getApplicationContext(), MainActivity.class);
                                                            Toast.makeText(getApplicationContext()  , "User Created", Toast.LENGTH_SHORT).show();
                                                            startActivity(i1);
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });

                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

                                }


                            }
                        }
                    }


                }


            }

        }
        catch (NumberFormatException e){

            Toast.makeText(this, "Invalid Phone number, Check again. Number of digits must be less than 10", Toast.LENGTH_SHORT).show();
        }

    }
    public void ClearControls(){
        FirstName.setText("");
        LastName.setText("");
        UserName.setText("");
        Password.setText("");
        CPassword.setText("");
        Email.setText("");
        Phone.setText("");

    }
    public int checkPassword(String pw,String cPw){
        if(pw.equals(cPw)){
            return 1;
        }
        else{

            Toast.makeText(this, "Password mismatch, Please renter the confirmation password", Toast.LENGTH_SHORT).show();
            return 0;
        }
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
        if(year > 2020){
            Toast.makeText(this, "Date of Birth entered is wrong, you can enter a date in teh future", Toast.LENGTH_SHORT).show();
            finaldate="null";
        }
        else if(year > 2002){
            Toast.makeText(this, "You must be atleast 18 years old to use this application", Toast.LENGTH_SHORT).show();
            finaldate="null";
        }
        else{
            return(finaldate);
        }
        return(finaldate);
    }

    public int CheckPhone(int phone){
        int length=String.valueOf(phone).length();
        if(length == 7){
            return 0;
        }
        else if(length < 7){
            Toast.makeText(this, "Invalid Phone number, Check again ", Toast.LENGTH_SHORT).show();
            return 1;
        }
        else if(length >= 8){
            Toast.makeText(this, "Invalid Phone number, Check again ", Toast.LENGTH_SHORT).show();
            return 1;
        }
        return 0;
    }

    public int checkPassword(String password){
        int length=String.valueOf(password).length();
        if(length < 8){
            Toast.makeText(this, "Password Must be 8 characters", Toast.LENGTH_SHORT).show();
            return 1;
        }
        else{
            return 0;
        }
    }
}