package com.example.nivetha.cust_sur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * Created by nivetha on 26/07/17.
 */

public class MyFeedback1 extends AppCompatActivity {
    private Session session1;//global variable
    LinkedHashMap<String, String> queryValues1 = new LinkedHashMap<String, String>();
    ArrayList<LinkedHashMap<String, String>> userList1 =new  ArrayList<>();
    LinkedHashMap<String, String> queryValues = new LinkedHashMap<String, String>();
    ArrayList<LinkedHashMap<String, String>> userList =new  ArrayList<>();
    String ticker_id;
    //String feedback_image;
    TextView t1,t2,t3,t4,t5;
    Button button1;
    String status;
    TableLayout tl ;
    View v;
    String feedback_image,created_at,closed_at;
    ImageView ivImage;
    ImageButton ibutton;
    Bitmap myBitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeedback);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        session1 = new Session(getApplicationContext());
        queryValues.put("pincode",session1.getpincode1());
        userList.add(queryValues);
        tl = (TableLayout) findViewById(R.id.tl1);
  //      ivImage=(ImageView)findViewById(R.id.ivImage);
//        t1=(TextView)findViewById(R.id.TextView01);
//        t2=(TextView)findViewById(R.id.TextView02);
        if(isNetworkAvailable()) {
            submitFeedback();
        }else{
            Toast.makeText(getApplicationContext(), "Device  not  connected to Internet", Toast.LENGTH_LONG).show();

        }
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
    public void submitFeedback() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        //System.out.println("Userlist:"+userList.get());
        Gson gson = new GsonBuilder().create();
        String resp = gson.toJson(userList);
        System.out.println(resp);
        params.put("usersJSON", resp);
        final ProgressDialog progress = new ProgressDialog(MyFeedback1.this);
        progress.setTitle("Loading");
        progress.setMessage("Loading your Customer Queries.....");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        client.post("http://poolana9027.cloudapp.net/android_sms/getallfeedback_ep.php", params, new AsyncHttpResponseHandler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(String response) {
                System.out.println("pri"+response);
                try {
                    JSONArray arr = new JSONArray(response);
                    System.out.println(arr.length());
                    TableRow row1= new TableRow(MyFeedback1.this);
                    TableLayout.LayoutParams tableRowParams1=
                            new TableLayout.LayoutParams
                                    (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
                    tableRowParams1.setMargins(15, 15, 15, 15);
                    row1.setLayoutParams(tableRowParams1);
                    t1 = new TextView(MyFeedback1.this);
                    t2 = new TextView(MyFeedback1.this);
                    t3 = new TextView(MyFeedback1.this);
                    t4 = new TextView(MyFeedback1.this);
                    t5 = new TextView(MyFeedback1.this);
                    t1.setText("Ticket ID ");
                    t2.setText("Feedback Image");
                    t2.setGravity(Gravity.CENTER);
                    t3.setText("Current Status");

                    t1.setTextSize(20.0f);
                    t1.setTextColor(Color.BLACK);
                    t2.setTextSize(20.0f);
                    t2.setTextColor(Color.BLACK);
                    t3.setTextSize(20.0f);
                    t3.setTextColor(Color.BLACK);
                    t4.setText("Change Status");
                    row1.addView(t1);
                    row1.addView(t2);
                    row1.addView(t3);

                    tl.addView(row1,0);
                    t1.setTextSize(20.0f);
                    for (int i = 0; i < arr.length(); i++){
                        try {

                            JSONObject obj = (JSONObject) arr.get(i);
                            ticker_id = obj.get("ticker_id").toString();
                            feedback_image = obj.get("feedback_image").toString();
                            status = obj.get("status").toString();
                            System.out.println("feedback_image:"+feedback_image);
//                            String afterDecode = URLDecoder.decode(feedback_image, "UTF-8");
//                            System.out.println("afterDecode:"+afterDecode);
//                            URL url = new URL(afterDecode);
//                            System.out.println("url:"+url);
                            TableRow row= new TableRow(MyFeedback1.this);
                            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                            TableLayout.LayoutParams tableRowParams=
                                    new TableLayout.LayoutParams
                                            (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
                            tableRowParams.setMargins(15, 15, 15, 15);
                            row.setLayoutParams(tableRowParams);
                            //row.setLayoutParams(lp);
                            t1 = new TextView(MyFeedback1.this);
                            t1.setText(ticker_id);
                            t2 = new TextView(MyFeedback1.this);
                            t3 = new TextView(MyFeedback1.this);
                            t4 = new TextView(MyFeedback1.this);
                            button1=new Button(MyFeedback1.this);
                            button1.setText("Change Status to Completed");
                            ibutton=new ImageButton(MyFeedback1.this);
                            ibutton.setImageBitmap(Utils.getResizedBitmap(getBitmapFromURL(feedback_image),500,500));
                            ibutton.setClickable(true);
//                            ibutton.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                                    zoomOut = true;
//                                    System.out.println("Iam innnnnnn");
//                                    Intent intent = new Intent(MyFeedback1.this, ImageFull.class);
//                                    intent.putExtra("BitmapImage",Utils.getResizedBitmap(getBitmapFromURL(feedback_image),50,50));
//                                    startActivity(intent);
//                                }
//                            });
                             v = new View(MyFeedback1.this);
                            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                            v.setBackgroundColor(Color.rgb(51, 51, 51));
                            //button1.setVisibility(View.INVISIBLE);
                            if(status.equals("0")){
                                t2.setText("Pending...");
                                t2.setTextColor(Color.RED);
                                button1.setEnabled(true);
                            }else{
                                t2.setText("Completed!!!");
                                t2.setTextColor(Color.GREEN);
                                button1.setEnabled(false);
                                button1.setBackgroundColor(Color.GRAY);
                               // button1.setVisibility(View.INVISIBLE);
                            }
                            t1.setGravity(Gravity.CENTER);
                            t2.setGravity(Gravity.CENTER);
                            t1.setTextColor(Color.BLACK);

                            row.addView(t1);
                            row.addView(ibutton);
                            row.addView(t2);
                            row.addView(button1);
                            tl.addView(row,i+1);
                            button1.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    if(button1.isEnabled()){
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyFeedback1.this);
                                        alertDialogBuilder.setMessage("Are you sure to change status to complete");
                                                alertDialogBuilder.setPositiveButton("yes",
                                                        new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface arg0, int arg1) {


                                                                AsyncHttpClient client1 = new AsyncHttpClient();
                                                                RequestParams params1 = new RequestParams();
                                                                //System.out.println("Userlist:"+userList.get());
                                                                queryValues1.put("ticker_id",ticker_id);
                                                                userList1.add(queryValues1);
                                                                Gson gson1 = new GsonBuilder().create();
                                                                String resp1 = gson1.toJson(userList1);
                                                                System.out.println(resp1);
                                                                params1.put("usersJSON", resp1);

                                                                client1.post("http://poolana9027.cloudapp.net/android_sms/updatestatus.php", params1, new AsyncHttpResponseHandler() {
                                                                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                                                    @Override
                                                                    public void onSuccess(String response) {
                                                                        final ProgressDialog progress1= new ProgressDialog(MyFeedback1.this);
                                                                        progress1.setTitle("Loading");
                                                                        progress1.setMessage("Changing Status");
                                                                        progress1.setCancelable(false); // disable dismiss by tapping outside of the dialog
                                                                        progress1.show();
                                                                        System.out.println("print"+response);
                                                                        try {
                                                                            System.out.println("hi");
                                                                            JSONArray arr1 = new JSONArray(response);
                                                                            System.out.println(arr1.length());

                                                                            for (int i = 0; i < arr1.length(); i++){
                                                                                try {

                                                                                    JSONObject obj = (JSONObject) arr1.get(i);
                                                                                    progress1.dismiss();
                                                                                    if(obj.get("error").equals("true")){
                                                                                        Toast.makeText(getApplicationContext(), "Status not updated successfully", Toast.LENGTH_LONG).show();


                                                                                    }else{
                                                                                        Toast.makeText(getApplicationContext(), "Status updated successfully", Toast.LENGTH_LONG).show();
                                                                                        Intent in = new Intent(MyFeedback1.this, AndroidTabLayoutActivity1.class);
                                                                                        in.putExtra("tab_index","1");
                                                                                        startActivity(in);

                                                                                    }
                                                                                }catch(Exception e){
                                                                                    System.out.println(e);
                                                                                }
                                                                            }
                                                                        }catch (JSONException e) {
                                                                            // TODO Auto-generated catch block
                                                                            // Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                                                                            // e.printStackTrace();
                                                                        }
                                                                    }
                                                                    // Aut

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
                                                        });

                                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getApplicationContext(), "Status not changed", Toast.LENGTH_LONG).show();
                                            }
                                        });

                                        AlertDialog alertDialog = alertDialogBuilder.create();
                                        alertDialog.show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Query status already updated", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                        }catch(Exception e){
                            System.out.println(e);
                        }
                    }
                }catch (JSONException e) {
                    // TODO Auto-generated catch block
                    // Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    // e.printStackTrace();
                }
                progress.dismiss();
            }
            // Aut

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
//    public void getBitmap(String src){
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(src, new AsyncHttpResponseHandler() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onSuccess(String response) {
//                System.out.println(response);
//                InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_16));
//                 Bitmap myBitmap1 = BitmapFactory.decodeStream(stream);
//                myBitmap=myBitmap1;
//            }
//            @Override
//            public void onFailure(int statusCode, Throwable error,
//                                  String content) {
//                // TODO Auto-generated method stub
//                if (statusCode == 404) {
//                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
//                } else if (statusCode == 500) {
//                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        return myBitmap;
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                session1.setLogin1("NotLogged");
                Intent intent=new Intent(MyFeedback1.this,Home.class);
                startActivity(intent);
                //logout code
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public  Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}