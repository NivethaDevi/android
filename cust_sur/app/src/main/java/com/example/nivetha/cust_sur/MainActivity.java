package com.example.nivetha.cust_sur;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button button;

    EditText field6;
    EditText field7;
    EditText field8;
    EditText field9;
    EditText field10;
    EditText field11;
    EditText field12;
    EditText field13;
    EditText field14;
    EditText field15;
    EditText field16;
    EditText field17;
    EditText field18;
    EditText field19;
    EditText field20;
    EditText field21;
    EditText field22;
    EditText field23;
    EditText field24;
    EditText field25;
    EditText field26;
    EditText field27;
    EditText field28;
    EditText field29;
    EditText field30;
    EditText field31;
    EditText field32;
    EditText field33;
    String field50;
    private Session session;//global variable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent myIntent = getIntent();
        session = new Session(getApplicationContext());

        field6= (EditText) findViewById(R.id.EditTextName6);
        field7= (EditText) findViewById(R.id.EditTextName7);
        field8= (EditText) findViewById(R.id.EditTextName8);
        field9= (EditText) findViewById(R.id.EditTextName9);
        field10= (EditText) findViewById(R.id.EditTextName10);
        field11= (EditText) findViewById(R.id.EditTextName11);
        field12= (EditText) findViewById(R.id.EditTextName12);
        field13= (EditText) findViewById(R.id.EditTextName13);
        field14= (EditText) findViewById(R.id.EditTextName14);
        field15= (EditText) findViewById(R.id.EditTextName15);
        field16= (EditText) findViewById(R.id.EditTextName16);
        field17= (EditText) findViewById(R.id.EditTextName17);
        field18= (EditText) findViewById(R.id.EditTextName18);
        field19= (EditText) findViewById(R.id.EditTextName19);
        field20= (EditText) findViewById(R.id.EditTextName20);
        field21= (EditText) findViewById(R.id.EditTextName21);
        field22= (EditText) findViewById(R.id.EditTextName22);
        field23= (EditText) findViewById(R.id.EditTextName23);
        field24= (EditText) findViewById(R.id.EditTextName24);
        field25= (EditText) findViewById(R.id.EditTextName25);
        field26= (EditText) findViewById(R.id.EditTextName26);
        field27= (EditText) findViewById(R.id.EditTextName27);
        field28= (EditText) findViewById(R.id.EditTextName28);
        field29= (EditText) findViewById(R.id.EditTextName29);
        field30= (EditText) findViewById(R.id.EditTextName30);
        field31= (EditText) findViewById(R.id.EditTextName31);
        field32= (EditText) findViewById(R.id.EditTextName32);
        field33= (EditText) findViewById(R.id.EditTextName33);

        if (myIntent.hasExtra("admin_id")) {
            field50 = myIntent.getStringExtra("admin_id");

        }

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
//                String str1 =field1.getText().toString();
//                String str2 =field2.getText().toString();
//                String str3 =field3.getText().toString();
//                String str4 =field4.getText().toString();
//                String str5 =field5.getText().toString();
                String str6 =field6.getText().toString();
                String str7 =field7.getText().toString();
                String str8 =field8.getText().toString();
                String str9 =field9.getText().toString();
                String str10 =field10.getText().toString();
                String str11 =field11.getText().toString();
                String str12=field12.getText().toString();
                String str13=field13.getText().toString();
                String str14=field14.getText().toString();
                String str15=field15.getText().toString();
                String str16=field16.getText().toString();
                String str17=field17.getText().toString();
                String str18 =field18.getText().toString();
                String str19 =field19.getText().toString();
                String str20 =field20.getText().toString();
                String str21 =field21.getText().toString();
                String str22 =field22.getText().toString();
                String str23 =field23.getText().toString();
                String str24 =field24.getText().toString();
                String str25 =field25.getText().toString();
                String str26 =field26.getText().toString();
                String str27 =field27.getText().toString();
                String str28 =field28.getText().toString();
                String str29 =field29.getText().toString();
                String str30 =field30.getText().toString();
                String str31 =field31.getText().toString();
                String str32 =field32.getText().toString();
                String str33 =field33.getText().toString();
//                i.putExtra("one",str1);
//                i.putExtra("two",str2);
//                i.putExtra("three",str3);
//                i.putExtra("four",str4);
//                i.putExtra("five",str5);
                i.putExtra("six",str6);
                i.putExtra("seven",str7);
                i.putExtra("eight",str8);
                i.putExtra("nine",str9);
                i.putExtra("ten",str10);
                i.putExtra("eleven",str11);
                i.putExtra("twelve",str12);
                i.putExtra("thirteen",str13);
                i.putExtra("fourteen",str14);
                i.putExtra("fifteen",str15);
                i.putExtra("sixteen",str16);
                i.putExtra("seventeen",str17);
                i.putExtra("eighteen",str18);
                i.putExtra("nineteen",str19);
                i.putExtra("twenty",str20);
                i.putExtra("tone",str21);
                i.putExtra("ttwo",str22);
                i.putExtra("tthree",str23);
                i.putExtra("tfour",str24);
                i.putExtra("tfive",str25);
                i.putExtra("tsix",str26);
                i.putExtra("tseven",str27);
                i.putExtra("teight",str28);
                i.putExtra("tnine",str29);
                i.putExtra("thirty",str30);
                i.putExtra("thone",str31);
                i.putExtra("thtwo",str32);
                i.putExtra("ththree",str33);
                i.putExtra("field50",field50);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                session.setLogin1("NotLogged");
                Intent intent=new Intent(MainActivity.this,Home.class);
                startActivity(intent);
                //logout code
                return true;
            case R.id.whatsap:
                String toNumber = field13.getText().toString(); // contains spaces.
                toNumber = toNumber.replace("+", "").replace(" ", "");
                String message="https://www.google.com";
                //Uri uri = Uri.parse("smsto:" + toNumber);
                //Intent sendIntent = new Intent("android.intent.action.MAIN");

                               Intent sendIntent = new Intent("android.intent.action.MAIN");
                sendIntent.putExtra("jid", toNumber + "@s.whatsapp.net");
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setPackage("com.whatsapp");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                //logout code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        networkConnectivityCheck.register(this);
//        syncSQLiteMySQLDB();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        networkConnectivityCheck.unregister(this);
//        syncSQLiteMySQLDB();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        networkConnectivityCheck.unregister(this);
//        syncSQLiteMySQLDB();
//    }

}
