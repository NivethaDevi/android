package com.example.nivetha.cust_sur;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.support.v7.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by nivetha on 24/07/17.
 */

public class Home_citizen_0 extends AppCompatActivity {
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
    String customer_id,pincode;
    private static final int SELECT_PICTURE = 10,CAMERA_REQUEST=100,REQUEST_PERMISSION_PHONE_STATE=10;
    private Session1 session1;//global variable
//    SharedPreferences app_preferences = PreferenceManager
    // .getDefaultSharedPreferences(Home.this);

    //SharedPreferences.Editor editor = app_preferences.edit();


    DBController controller = new DBController(this);
    LinkedHashMap<String, String> queryValues1 = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String> queryValues2 = new LinkedHashMap<String, String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citizen_0);
        showPhoneWritePermission();
        button=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        session1 = new Session1(getApplicationContext());
        button1.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        field1 =(EditText) findViewById(R.id.EditTextName1);
        field2= (EditText) findViewById(R.id.EditTextName2);
        field3= (EditText) findViewById(R.id.EditTextName3);
        field4= (EditText) findViewById(R.id.EditTextName4);
        field5= (EditText) findViewById(R.id.EditTextName5);
        field6= (EditText) findViewById(R.id.EditTextName6);
        field7= (EditText) findViewById(R.id.EditTextName7);
        if(session1.getLogin().equals("Logged")){
            System.out.print("You are logged in");
            Intent i = new Intent(Home_citizen_0.this, AndroidTabLayoutActivity.class);
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

                    check1= requestOTP();
                    queryValues2.put("field5", field5.getText().toString());
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
                Intent i = new Intent(Home_citizen_0.this,Home_0.class);
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
                                queryValues1.put("field8","2");
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
                        Toast.makeText(getApplicationContext(), "Please enter  email",
                                Toast.LENGTH_LONG).show();}
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter  Mobile number",
                            Toast.LENGTH_LONG).show();}

            }else {
                Toast.makeText(getApplicationContext(), "Please enter Name",
                        Toast.LENGTH_LONG).show();}

        }else {
            Toast.makeText(getApplicationContext(), "Please enter Administrator details",
                    Toast.LENGTH_LONG).show();}
        return false;
    }
    private void showPhoneWritePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                showExplanation("Permission Needed", "Rationale", Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_PHONE_STATE);
            } else {
                requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_PERMISSION_PHONE_STATE);
            }
        } else {
            // Toast.makeText(Home_citizen.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }
    }
    private void showExplanation(String title,
                                 String message,
                                 final String permission,
                                 final int permissionRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestPermission(permission, permissionRequestCode);
                    }
                });
        builder.create().show();
    }

    private void requestPermission(String permissionName, int permissionRequestCode) {
        ActivityCompat.requestPermissions(this,
                new String[]{permissionName}, permissionRequestCode);
    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String permissions[],
            int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_PHONE_STATE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(Home_citizen.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(Home_citizen.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
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
                        System.out.println("session_login:"+session1.getLogin());
                        session1.setLogin("Logged");
                        Intent i = new Intent(Home_citizen_0.this, AndroidTabLayoutActivity.class);
                        i.putExtra("customer_id",customer_id);
                        i.putExtra("pincode",pincode);
                        startActivity(i);

                        Home_citizen_0.this.finish();
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
                    customer_id=obj.get("id").toString();
                    pincode=obj.get("pincode").toString();
                    session1 = new Session1(getBaseContext()); //in oncreate
//and now we set sharedpreference then use this like

                    session1.setusename(customer_id);
                    session1.setpincode(pincode);
                    System.out.println("session:"+session1.getusename());

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
