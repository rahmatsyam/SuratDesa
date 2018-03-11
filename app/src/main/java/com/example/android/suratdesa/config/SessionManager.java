package com.example.android.suratdesa.config;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.android.suratdesa.LoginUser;

import java.util.HashMap;

/**
 * Created by user on 11/3/2018.
 */

public class SessionManager {

    //Shared Preferences
    private SharedPreferences pref;

    //Editor for Shared preferences
    private SharedPreferences.Editor editor;

    //Context
    private Context _context;

    //nama sharepreference
    private static final String PREF_NAME = "Sesi";

    //All Shared Preferences Keys
    private static final String IS_LOGIN_USER = "IsLoggedIn";


    public static final String KEY_NAMA = "nama";



    //Constructor
    public SessionManager(Context context){
        this._context = context;
        int PRIVATE_MODE = 0;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    //Create Login session
    public void createLoginSession(String nama){
        //Storing login value as true
        editor.putBoolean(IS_LOGIN_USER, true);

        editor.putString(KEY_NAMA,nama);


        editor.commit();
    }




    /**
     *Check login method will check user login status
     *If false it will redirect user to login page
     *Else won't do anything
     **/

    public void checkLogin(){
        //check login status
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginUser.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }



    /**
     *Get stored session data
     **/



    public HashMap<String, String> getUserDetails() {
        HashMap<String,String> User= new HashMap<>();
        User.put(KEY_NAMA, pref.getString(KEY_NAMA,null));

        return User;
    }


    /**
     * Clear session details
     **/


    public void logOut() {
        //Clearing all data from shared preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginUser.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN_USER, false);
    }


}
