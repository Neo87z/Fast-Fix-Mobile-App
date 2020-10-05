package com.example.madfinal.SessionManagement;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.madfinal.Models.Technician;
import com.example.madfinal.Models.User;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String Session_UserName="session";
    String Session_User_FirstName="Session_User";
    String Session_User_LastName="SessionUser";
    String Session_Tech_username="SessionUser";

    String ViewKey="1";


    public SessionManagement(Context context){
        sharedPreferences=context.getSharedPreferences(Session_UserName,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void SaveSession(User user){
        String Username=user.getUserName();

        editor.putString(Session_UserName,Username).commit();

    }

    public String getSessionUsername(){
        return sharedPreferences.getString(Session_UserName,"null");

    }
    public void SavetechSession(Technician tech){
        String Username=tech.getDisplayName();

        editor.putString(Session_Tech_username,Username).commit();

    }

    public String GetTechSession(){
        return sharedPreferences.getString(Session_Tech_username,"null");

    }

    public void SaveSessioNkey(String key){


        editor.putString(ViewKey,key).commit();


    }

    public String getSessionkey(){
        return sharedPreferences.getString(ViewKey,"null");

    }


}
