package com.example.nivetha.cust_sur;

/**
 * Created by nivetha on 30/05/17.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

   // MainActivity ob=new MainActivity();
    boolean check=false;
    String sta="Not connected to Internet";
    @Override
    public void onReceive(final Context context, Intent intent) {

        String status = NetworkUtils.getConnectivityStatusString(context);
       // Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        sta=status;
        if(status.equals("Wifi enabled")|| status.equals("Mobile data enabled")) {
            //ob.syncSQLiteMySQLDB();

            //Toast.makeText(context, status, Toast.LENGTH_LONG).show();
//            SyncService ob=new SyncService();
//            ob.execute();
             intent = new Intent(context, SyncService.class);
            context.startService(intent);
        }

    }
//    public boolean check_network(){
//        if(sta.equals("Wifi enabled")|| sta.equals("Mobile data enabled")) {
//            //ob.syncSQLiteMySQLDB();
//            return true;
//        }
//        return false;
//    }
}