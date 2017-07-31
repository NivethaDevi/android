package com.example.nivetha.cust_sur;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.StrictMode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import android.view.*;


/**
 * Created by nivetha on 26/07/17.
 */

public class MyFeedback extends AppCompatActivity {
    private Session1 session1;//global variable
    LinkedHashMap<String, String> queryValues = new LinkedHashMap<String, String>();
    ArrayList<LinkedHashMap<String, String>> userList =new  ArrayList<>();
    String ticker_id;
    //String feedback_image;
    TextView t1,t2,t3,t4,t5;
    String status;
    TableLayout tl ;
    View v;
    String feedback_image,created_at,closed_at;
    ImageView ivImage;
    Bitmap myBitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myfeedback);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        session1 = new Session1(getApplicationContext());
        queryValues.put("customer_id",session1.getusename());
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
        final ProgressDialog progress = new ProgressDialog(MyFeedback.this);
        progress.setTitle("Loading");
        progress.setMessage("Loading your Feedbacks.....");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        client.post("http://poolana9027.cloudapp.net/android_sms/getallfeedback.php", params, new AsyncHttpResponseHandler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(String response) {
                System.out.println("pri"+response);
                try {
                    JSONArray arr = new JSONArray(response);
                    System.out.println(arr.length());
                    TableRow row1= new TableRow(MyFeedback.this);
                    TableLayout.LayoutParams tableRowParams1=
                            new TableLayout.LayoutParams
                                    (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
                    tableRowParams1.setMargins(15, 15, 15, 15);
                    row1.setLayoutParams(tableRowParams1);
                    t1 = new TextView(MyFeedback.this);
                    t2 = new TextView(MyFeedback.this);
                    t3 = new TextView(MyFeedback.this);
                    t4 = new TextView(MyFeedback.this);
                    t5 = new TextView(MyFeedback.this);
                    t1.setText("Ticket ID ");
                    t2.setText("Feedback Image");
                    t2.setGravity(Gravity.CENTER);
                    t3.setText("Status");
                    t4.setText("Created At  ");
                    t5.setText("Closed At ");
                    t1.setTextSize(20.0f);
                    t1.setTextColor(Color.BLACK);
                    t2.setTextSize(20.0f);
                    t2.setTextColor(Color.BLACK);
                    t3.setTextSize(20.0f);
                    t3.setTextColor(Color.BLACK);
                    t4.setTextSize(20.0f);
                    t4.setTextColor(Color.BLACK);
                    t5.setTextSize(20.0f);
                    t5.setTextColor(Color.BLACK);
                    row1.addView(t1);
                    row1.addView(t2);
                    row1.addView(t3);
                    row1.addView(t4);
                    row1.addView(t5);
                    tl.addView(row1,0);
                    t1.setTextSize(20.0f);
                    for (int i = 0; i < arr.length(); i++){
                        try {

                            JSONObject obj = (JSONObject) arr.get(i);
                            ticker_id = obj.get("ticker_id").toString();
                            feedback_image = obj.get("feedback_image").toString();
                            status = obj.get("status").toString();
                            created_at=obj.get("created_at").toString();
                            closed_at=obj.get("closed_at").toString();
                            System.out.println("feedback_image:"+feedback_image);
//                            String afterDecode = URLDecoder.decode(feedback_image, "UTF-8");
//                            System.out.println("afterDecode:"+afterDecode);
//                            URL url = new URL(afterDecode);
//                            System.out.println("url:"+url);
                            TableRow row= new TableRow(MyFeedback.this);
                            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                            TableLayout.LayoutParams tableRowParams=
                                    new TableLayout.LayoutParams
                                            (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
                            tableRowParams.setMargins(15, 15, 15, 15);
                            row.setLayoutParams(tableRowParams);
                            //row.setLayoutParams(lp);
                            t1 = new TextView(MyFeedback.this);
                            t1.setText(ticker_id);
                            t2 = new TextView(MyFeedback.this);
                            t3 = new TextView(MyFeedback.this);
                            t4 = new TextView(MyFeedback.this);
                            ivImage=new ImageView(MyFeedback.this);
                            ivImage.setImageBitmap(Utils.getResizedBitmap(getBitmapFromURL(feedback_image),500,500));
                             v = new View(MyFeedback.this);
                            v.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                            v.setBackgroundColor(Color.rgb(51, 51, 51));
                            if(status.equals("0")){
                                t2.setText("Pending...");
                                t2.setTextColor(Color.RED);

                            }else{
                                t2.setText("Completed!!!");
                                t2.setTextColor(Color.GREEN);
                            }
                            t1.setGravity(Gravity.CENTER);
                            t2.setGravity(Gravity.CENTER);
                            t1.setTextColor(Color.BLACK);
                            t3.setText(created_at);
                            t4.setText(closed_at);
                            t3.setTextColor(Color.BLACK);
                            t4.setTextColor(Color.BLACK);
                            row.addView(t1);
                            row.addView(ivImage);
                            row.addView(t2);
                            row.addView(t3);
                            row.addView(t4);
                            tl.addView(row,i+1);
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
                session1.setLogin("NotLogged");
                Intent intent=new Intent(MyFeedback.this,Home_citizen_0.class);
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