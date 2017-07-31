package com.example.nivetha.cust_sur;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by nivetha on 08/06/17.
 */

public class Session1 {
    private SharedPreferences prefs1;
    Context _context;
    private static final String PREF_NAME = "Customer";
    int PRIVATE_MODE = 0;

    public Session1(Context cntx) {
        // TODO Auto-generated constructor stub
        this._context = cntx;
        prefs1 = PreferenceManager.getDefaultSharedPreferences(cntx);
        _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public void setusename(String usename) {
        prefs1.edit().putString("usename", usename).commit();
//        prefs.Commit();
    }
    public void setpincode(String usename) {
        prefs1.edit().putString("pincode", usename).commit();
//        prefs.Commit();
    }

    public String getusename() {
        String usename = prefs1.getString("usename","");
        return usename;
    }
    public String getpincode() {
        String usename = prefs1.getString("pincode","");
        return usename;
    }
    public void setLogin(String login) {
        if(!login.equals(""))
        prefs1.edit().putString("login", login).commit();
        else
            prefs1.edit().putString("login", "NotLogged").commit();

//        prefs.Commit();
    }

    public String getLogin() {
        String login = prefs1.getString("login","");
        return login;
    }
}
