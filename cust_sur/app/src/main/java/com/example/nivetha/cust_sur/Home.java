package com.example.nivetha.cust_sur;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by nivetha on 04/06/17.
 */

public class Home  extends AppCompatActivity {
    Button button,button1,button2;
    EditText field1;
    EditText field2;
    EditText field3;
    EditText field4;
    EditText field5;
    EditText field6;
    EditText field7;
    int check1=0;
    int otp_check=0;
    Boolean isFirstTime;
    String admin_id,pincode;
    private Session session;//global variable
//    SharedPreferences app_preferences = PreferenceManager
           // .getDefaultSharedPreferences(Home.this);

    //SharedPreferences.Editor editor = app_preferences.edit();


    DBController controller = new DBController(this);
    LinkedHashMap<String, String> queryValues1 = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String> queryValues2 = new LinkedHashMap<String, String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        session = new Session(getApplicationContext());
        button1.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        field1 =(EditText) findViewById(R.id.EditTextName1);
        field2= (EditText) findViewById(R.id.EditTextName2);
        field3= (EditText) findViewById(R.id.EditTextName3);
        field4= (EditText) findViewById(R.id.EditTextName4);
        field5= (EditText) findViewById(R.id.EditTextName5);
        field6= (EditText) findViewById(R.id.EditTextName6);
        field7= (EditText) findViewById(R.id.EditTextName7);

        // isFirstTime = app_preferences.getBoolean("isFirstTime", true);

//        if (isFirstTime) {
//
////implement your first time logic
//            editor.putBoolean("isFirstTime", false);
//            editor.commit();
//
//        }else{
////app open directly
//            Intent i = new Intent(Home.this, MainActivity.class);
//            startActivity(i);
//        }
        if(session.getLogin1().equals("Logged")){
            System.out.print("You are logged in");
            Intent i = new Intent(Home.this, AndroidTabLayoutActivity1.class);
            i.putExtra("tab_index","0");
            startActivity(i);
        }

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (isNetworkAvailable()) {
                    System.out.println("cantu");
                    boolean check=true;
                    check=addAdmin(v);

if(check) {
    check1 = requestOTP();
    queryValues2.put("field5", field5.getText().toString());
}
                }else {
                    Toast.makeText(getApplicationContext(), "Sorry,Network unavailable",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Home.this, Home_0.class);
                startActivity(i);
            }
        });
//        do {
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (check1 == 1) {
                        //Toast.makeText(getApplicationContext(),field5.getText() , Toast.LENGTH_LONG).show();
                        if (field5 != null && field5.getText().length() != 0) {
                            if (isNetworkAvailable()) {
                                queryValues2.put("field5", field5.getText().toString());
                                verifyOTP();

                                //otp_check=verifyOTP();

                            }else {
                                Toast.makeText(getApplicationContext(), "Sorry,Network unavailable",
                                        Toast.LENGTH_LONG).show();
                            }

                        }else {
                            Toast.makeText(getApplicationContext(), "Enter OTP to proceed",
                                    Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid OTP",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
//        }while(!(field5!= null && field5.getText().length() != 0));
    }
    public boolean addAdmin(View view) {
        if (field1.getText()!= null && field1.getText().length() != 0) {
            if (field2!= null && field2.getText().length() != 0) {
                if (field3!= null && field3.getText().length() != 0) {
                    if (field4!= null && field4.getText().length() != 0) {
                       if (field6!= null && field6.getText().length() != 0  && field6.getText().length() ==6) {
                           if (field7!= null && field7.getText().length() != 0) {

                               queryValues1.put("field1", field1.getText().toString());
                            queryValues1.put("field2", field2.getText().toString());
                            queryValues1.put("field3", field3.getText().toString());
                            queryValues1.put("field4", field4.getText().toString());
                        queryValues1.put("field6", field6.getText().toString());
                           queryValues1.put("field7", field7.getText().toString());
                               queryValues1.put("field8", "1");
                               return true;

                           }else {
                               Toast.makeText(getApplicationContext(), "Please enter Voter ID",
                                       Toast.LENGTH_LONG).show();
                           }
                       }else {
                        Toast.makeText(getApplicationContext(), "Please enter valid pincode",
                                Toast.LENGTH_LONG).show();
                         }
                    }else {
                        Toast.makeText(getApplicationContext(), "Please enter Agent email",
                                Toast.LENGTH_LONG).show();}
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter Agent Mobile number",
                            Toast.LENGTH_LONG).show();}

            }else {
                Toast.makeText(getApplicationContext(), "Please enter Agent Name",
                        Toast.LENGTH_LONG).show();}

        }else {
            Toast.makeText(getApplicationContext(), "Please enter Administrator details",
                    Toast.LENGTH_LONG).show();}
        return false;


    }
    public void verifyOTP(){
        //Create AsycHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        ArrayList<LinkedHashMap<String, String>> userList =new  ArrayList<>();
        userList.add(queryValues2);
        Gson gson = new GsonBuilder().create();
        String resp=gson.toJson(userList);
        System.out.println("letusseeotp;"+resp);
//                System.out.println("print"+controller.composeJSONfromSQLite());
        params.put("usersJSON", resp);
        client.post("http://poolana9027.cloudapp.net/android_sms/verify_otp.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
//                        Toast.makeText(getApplicationContext(), "second",
//                                Toast.LENGTH_LONG).show();
                System.out.println("printotp:"+response);
//                        String resp=response;
//                        String resp = "{\"id\" :"+response+"}";
//                        System.out.println(resp);
                try {
                    JSONObject obj=new JSONObject(response);
                    System.out.println(obj.get("error"));
                    String check=obj.get("error").toString();
                    String message=obj.get("message").toString();
//                                controller.updateSyncStatus(obj.get("id").toString(), obj.get("status").toString());
                    if(check.equals("false")&&message.contains("User created successfully!")){
                       // Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();

                        otp_check=1;
//                                e.printStackTrace();
                    }else{
                        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                        otp_check=0;
                    }
                    if(otp_check==1) {
                        Toast.makeText(getApplicationContext(), "Successfully signed in", Toast.LENGTH_LONG).show();
                        System.out.println("session_login:"+session.getLogin1());
                        session.setLogin1("Logged");
                        Intent i = new Intent(Home.this, AndroidTabLayoutActivity1.class);
                        i.putExtra("tab_index","0");
                        i.putExtra("admin_id",admin_id);
                        i.putExtra("pincode",pincode);
                        startActivity(i);

                        Home.this.finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "OTP Verification failed", Toast.LENGTH_LONG).show();






                    }
                }

                catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Enter valid OTP", Toast.LENGTH_LONG).show();
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


    }
    public int requestOTP(){
        //Create AsycHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        ArrayList<LinkedHashMap<String, String>> userList =new  ArrayList<>();
        userList.add(queryValues1);
                Gson gson = new GsonBuilder().create();
                String resp=gson.toJson(userList);
        System.out.println("letussee;"+resp);
//                System.out.println("print"+controller.composeJSONfromSQLite());
                params.put("usersJSON", resp);
                client.post("http://poolana9027.cloudapp.net/android_sms/request_sms.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getApplicationContext(), "second",
//                                Toast.LENGTH_LONG).show();
                        System.out.println("pri"+response);
//                        String resp=response;
//                        String resp = "{\"id\" :"+response+"}";
//                        System.out.println(resp);
                        try {
                            JSONObject obj=new JSONObject(response);
                                System.out.println(obj.get("error"));
                            String check=obj.get("error").toString();
                            String message=obj.get("message").toString();
                             admin_id=obj.get("id").toString();
                            pincode=obj.get("pincode").toString();

                            session = new Session(getBaseContext()); //in oncreate
//and now we set sharedpreference then use this like

                            session.setusename1(admin_id);

                            session.setpincode1(pincode);
                            System.out.println("session:"+session.getusename1());

//                                controller.updateSyncStatus(obj.get("id").toString(), obj.get("status").toString());
                            if(check.equals("false")&&message.contains("SMS request is initiated! You will be receiving it shortly.")&&!message.contains("Sorry! Error occurred in registration.")){
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();

                                check1=1;
                                queryValues2.put("field5", field5.getText().toString());

//                                e.printStackTrace();
                            }else{
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                                check1=0;
                            }
                            }

                         catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_LONG).show();
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

        return 0;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
