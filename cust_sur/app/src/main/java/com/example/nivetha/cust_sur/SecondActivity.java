package com.example.nivetha.cust_sur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by nivetha on 22/05/17.
 */

public class SecondActivity extends AppCompatActivity {
    Button button;
    String field1,field2,field3,field4,field5;
    String field6,field7,field8,field9,field10,field11,field12,field13,field14,field15,field16,field17,field18,field19,field20;
    String field21,field22,field23,field24,field25,field26,field27,field28,field29,field30,field31,field32,field33,field50;
    EditText field34,field35,field36,field37,field38,field39,field40,field41,field42,field43,field44,field45,field46,field47,field48,field49;
    DBController controller = new DBController(this);
    private Session session;
    int check=0;
    NetworkChangeReceiver ob=new NetworkChangeReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        button=(Button)findViewById(R.id.button1);
        session = new Session(getApplicationContext());
        //userName = (EditText) findViewById(R.id.EditTextName34);
        //userName1 = (EditText) findViewById(R.layout.activity_main);
        Intent myIntent = getIntent();
        field34=(EditText)findViewById(R.id.EditTextName34);
        field35=(EditText)findViewById(R.id.EditTextName35);
        field36=(EditText)findViewById(R.id.EditTextName36);
        field37=(EditText)findViewById(R.id.EditTextName37);
        field38=(EditText)findViewById(R.id.EditTextName38);
        field39=(EditText)findViewById(R.id.EditTextName39);
        field40=(EditText)findViewById(R.id.EditTextName40);
        field41=(EditText)findViewById(R.id.EditTextName41);
        field42=(EditText)findViewById(R.id.EditTextName42);
        field43=(EditText)findViewById(R.id.EditTextName43);
        field44=(EditText)findViewById(R.id.EditTextName44);
        field45=(EditText)findViewById(R.id.EditTextName45);
        field46=(EditText)findViewById(R.id.EditTextName46);
        field47=(EditText)findViewById(R.id.EditTextName47);
        field48=(EditText)findViewById(R.id.EditTextName48);
        field49=(EditText)findViewById(R.id.EditTextName49);


//        if (myIntent.hasExtra("one"))
//            field1=myIntent.getStringExtra("one");
//        if (myIntent.hasExtra("two"))
//            field2=myIntent.getStringExtra("two");
//        if (myIntent.hasExtra("three"))
//            field3=myIntent.getStringExtra("three");
//        if (myIntent.hasExtra("four"))
//            field4=myIntent.getStringExtra("four");
//        if (myIntent.hasExtra("five"))
//            field5=myIntent.getStringExtra("five");
        if (myIntent.hasExtra("six"))
            field6=myIntent.getStringExtra("six");
        if (myIntent.hasExtra("seven"))
            field7=myIntent.getStringExtra("seven");
        if (myIntent.hasExtra("eight"))
            field8=myIntent.getStringExtra("eight");
        if (myIntent.hasExtra("nine"))
            field9=myIntent.getStringExtra("nine");
        if (myIntent.hasExtra("ten"))
            field10=myIntent.getStringExtra("ten");
        if (myIntent.hasExtra("eleven"))
            field11=myIntent.getStringExtra("eleven");
        if (myIntent.hasExtra("twelve"))
            field12=myIntent.getStringExtra("twelve");
        if (myIntent.hasExtra("thirteen"))
            field13=myIntent.getStringExtra("thirteen");
        if (myIntent.hasExtra("fourteen"))
            field14=myIntent.getStringExtra("fourteen");
        if (myIntent.hasExtra("fifteen"))
            field15=myIntent.getStringExtra("fifteen");
        if (myIntent.hasExtra("sixteen"))
            field16=myIntent.getStringExtra("sixteen");
        if (myIntent.hasExtra("seventeen"))
            field17=myIntent.getStringExtra("seventeen");
        if (myIntent.hasExtra("eighteen"))
            field18=myIntent.getStringExtra("eighteen");
        if (myIntent.hasExtra("nineteen"))
            field19=myIntent.getStringExtra("nineteen");
        if (myIntent.hasExtra("twenty"))
            field20=myIntent.getStringExtra("twenty");
        if (myIntent.hasExtra("tone"))
            field21=myIntent.getStringExtra("tone");
        if (myIntent.hasExtra("ttwo"))
            field22=myIntent.getStringExtra("ttwo");
        if (myIntent.hasExtra("tthree"))
            field23=myIntent.getStringExtra("tthree");
        if (myIntent.hasExtra("tfour"))
            field24=myIntent.getStringExtra("tfour");
        if (myIntent.hasExtra("tfive"))
            field25=myIntent.getStringExtra("tfive");
        if (myIntent.hasExtra("tsix"))
            field26=myIntent.getStringExtra("tsix");
        if (myIntent.hasExtra("tseven"))
            field27=myIntent.getStringExtra("tseven");
        if (myIntent.hasExtra("teight"))
            field28=myIntent.getStringExtra("teight");
        if (myIntent.hasExtra("tnine"))
            field29=myIntent.getStringExtra("tnine");
        if (myIntent.hasExtra("thirty"))
            field30=myIntent.getStringExtra("thirty");
        if (myIntent.hasExtra("thone"))
            field31=myIntent.getStringExtra("thone");
        if (myIntent.hasExtra("thtwo"))
            field32=myIntent.getStringExtra("thtwo");
        if (myIntent.hasExtra("ththree"))
            field33=myIntent.getStringExtra("ththree");
        if (myIntent.hasExtra("field50")) {
            //field50 = myIntent.getStringExtra("field50");
            System.out.println("session:"+session.getusename1());

            field50=session.getusename1();
        }
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(addUser(v)) {
                    if (isNetworkAvailable()) {
                        Toast.makeText(getApplicationContext(), "Submitted Successfully",
                                Toast.LENGTH_LONG).show();
                        syncSQLiteMySQLDB();
                    } else {
                        Toast.makeText(getApplicationContext(), "Submission failed",
                                Toast.LENGTH_LONG).show();
                    }
                }
                if(check==1) {
                    Intent i = new Intent(SecondActivity.this, AndroidTabLayoutActivity1.class);
                    startActivity(i);
                }
            }
        });

    }

    public boolean addUser(View view) {
        LinkedHashMap<String, String> queryValues = new LinkedHashMap<String, String>();
        if (field6 != null && field6.trim().length() != 0) {
            if (field7 != null && field7.trim().length() != 0) {
                if (field8 != null && field8.trim().length() != 0) {
                    if (field9!= null && field9.trim().length() != 0) {
                        if (field10 != null && field10.trim().length() != 0) {
                            if (field11 != null && field11.trim().length() != 0) {
                                if (field24 != null && field24.trim().length() != 0) {
                                    if (field25 != null && field25.trim().length() != 0) {
                                        if (field26 != null && field26.trim().length() != 0) {
                                            if ((field26.trim().length() == 6)) {
                                                if (field27 != null && field27.trim().length() != 0) {
                                                    queryValues.put("field6", field6);
                                                    queryValues.put("field7", field7);
                                                    queryValues.put("field8", field8);
                                                    queryValues.put("field9", field9);
                                                    queryValues.put("field10", field10);
                                                    queryValues.put("field11", field11);
                                                    queryValues.put("field12", field12);
                                                    queryValues.put("field13", field13);
                                                    queryValues.put("field14", field14);
                                                    queryValues.put("field15", field15);
                                                    queryValues.put("field16", field16);
                                                    queryValues.put("field17", field17);
                                                    queryValues.put("field18", field18);
                                                    queryValues.put("field19", field19);
                                                    queryValues.put("field20", field20);
                                                    queryValues.put("field21", field21);
                                                    queryValues.put("field22", field22);
                                                    queryValues.put("field23", field23);
                                                    queryValues.put("field24", field24);
                                                    queryValues.put("field25", field25);
                                                    queryValues.put("field26", field26);
                                                    queryValues.put("field27", field27);
                                                    queryValues.put("field28", field28);
                                                    queryValues.put("field29", field29);
                                                    queryValues.put("field30", field30);
                                                    queryValues.put("field31", field31);
                                                    queryValues.put("field32", field32);
                                                    queryValues.put("field33", field33);
                                                    queryValues.put("field34", field34.getText().toString());
                                                    queryValues.put("field35", field35.getText().toString());
                                                    queryValues.put("field36", field36.getText().toString());
                                                    queryValues.put("field37", field37.getText().toString());
                                                    queryValues.put("field38", field38.getText().toString());
                                                    queryValues.put("field39", field39.getText().toString());
                                                    queryValues.put("field40", field40.getText().toString());
                                                    queryValues.put("field41", field41.getText().toString());
                                                    queryValues.put("field42", field42.getText().toString());
                                                    queryValues.put("field43", field43.getText().toString());
                                                    queryValues.put("field44", field44.getText().toString());
                                                    queryValues.put("field45", field45.getText().toString());
                                                    queryValues.put("field46", field46.getText().toString());
                                                    queryValues.put("field47", field47.getText().toString());
                                                    queryValues.put("field48", field48.getText().toString());
                                                    queryValues.put("field49", field49.getText().toString());
                                                    queryValues.put("field50", field50);
//                                                Toast.makeText(getApplicationContext(), field6,
//                                                        Toast.LENGTH_LONG).show();
                                                    check = 1;
                                                    controller.insertUser(queryValues);
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Please enter Country",
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Please enter Valid Postal Code",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                        }
                                         else{
                                            Toast.makeText(getApplicationContext(), "Please enter  Postal Code",
                                                    Toast.LENGTH_LONG).show();
                                            }
                                    }else {
                                                Toast.makeText(getApplicationContext(), "Please enter State",
                                                        Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Please enter District",
                                            Toast.LENGTH_LONG).show();
                                }

                            } else {
                            Toast.makeText(getApplicationContext(), "Please enter Voter Complete Name",
                                    Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Consttituency Name",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter Assembly Name",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter Booth No. / Name",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter Voter Card No",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter Aadhar Card No",
                    Toast.LENGTH_LONG).show();
        }
        if(check==1)
        return true;
        else
            return false;
    }
    public void syncSQLiteMySQLDB(){
        //Create AsycHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        ArrayList<LinkedHashMap<String, String>> userList =  controller.getAllUsers();
        if(userList.size()!=0){
            if(controller.dbSyncCount() != 0){
//                Toast.makeText(getApplicationContext(), controller.composeJSONfromSQLite(),
//                        Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(),userList.size(),
//                        Toast.LENGTH_LONG).show();
                System.out.println("print"+controller.composeJSONfromSQLite());
                params.put("usersJSON", controller.composeJSONfromSQLite());
                client.post("http://poolana9027.cloudapp.net/android_sms/insert_user.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getApplicationContext(), "second",
//                                Toast.LENGTH_LONG).show();
                        System.out.println("pri"+response);
//                        String resp=response;
//                        String resp = "{\"id\" :"+response+"}";
//                        System.out.println(resp);
                        try {
                            JSONArray arr = new JSONArray(response);
                            //System.out.println("hi1");

                            System.out.println(arr.length());
                            //System.out.println("hi2");

                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = (JSONObject) arr.get(i);
                                System.out.println(obj.get("id"));
                                System.out.println(obj.get("status"));
                                controller.updateSyncStatus(obj.get("id").toString(), obj.get("status").toString());
                            }
                            Toast.makeText(getApplicationContext(), "DB Sync completed!", Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                       @Override
                    public void onFailure(int statusCode, Throwable error,
                                          String content) {
                        // TODO Auto-generated method stub
                        if (statusCode == 404) {
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                        } else if (statusCode == 500) {
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else{
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
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
                Intent intent=new Intent(SecondActivity.this,Home.class);
                startActivity(intent);
                //logout code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}