package com.example.nivetha.cust_sur;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by nivetha on 08/06/17.
 */

public class Session {
    private SharedPreferences prefs;
    Context _context;
    private static final String PREF_NAME = "Elected_Representative";
    int PRIVATE_MODE = 0;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        this._context = cntx;
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
//        prefs.Commit();
    }
    public void setpincode(String usename) {
        prefs.edit().putString("pincode", usename).commit();
//        prefs.Commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }
    public String getpincode() {
        String usename = prefs.getString("pincode","");
        return usename;
    }
    public void setusename1(String usename) {
        prefs.edit().putString("usename1", usename).commit();
//        prefs.Commit();
    }
    public void setpincode1(String usename) {
        prefs.edit().putString("pincode1", usename).commit();
//        prefs.Commit();
    }

    public String getusename1() {
        String usename = prefs.getString("usename1","");
        return usename;
    }
    public String getpincode1() {
        String usename = prefs.getString("pincode1","");
        return usename;
    }
    public void setLogin(String login) {
        if(!login.equals(""))
        prefs.edit().putString("login", login).commit();
        else
            prefs.edit().putString("login", "NotLogged").commit();

//        prefs.Commit();
    }
    public void setLogin1(String login) {
        if(!login.equals(""))
            prefs.edit().putString("login1", login).commit();
        else
            prefs.edit().putString("login1", "NotLogged").commit();

//        prefs.Commit();
    }


    public String getLogin() {
        String login = prefs.getString("login","");
        return login;
    }

    public String getLogin1() {
        String login = prefs.getString("login1","");
        return login;
    }
}
