package com.example.nivetha.cust_sur;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Handler;

import static java.io.File.*;

/**
 * Created by nivetha on 23/07/17.
 */

public class Home_citizen extends AppCompatActivity {
    Button button1,button2,button3,button4;
    private int PICK_IMAGE=1;  Bitmap bm=null;
    String customer_id,pincode;
    ImageView ivImage;
    ArrayList<LinkedHashMap<String, String>> userList =new  ArrayList<>();
    Uri  mMakePhotoUri;
    File m_file;
    private static final int SELECT_PICTURE = 10,CAMERA_REQUEST=100,REQUEST_PERMISSION_PHONE_STATE=10;
    LinkedHashMap<String, String> queryValues = new LinkedHashMap<String, String>();

    private Session1 session1;//global variable


    protected void onCreate(Bundle savedInstanceState) {
        showPhoneWritePermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.citizen);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        ivImage=(ImageView)findViewById(R.id.ivImage);
        session1 = new Session1(getApplicationContext());
        Intent myIntent = getIntent();
        if (myIntent.hasExtra("customer_id")) {
            customer_id = myIntent.getStringExtra("customer_id");

        }
        if (myIntent.hasExtra("pincode")) {
            pincode = myIntent.getStringExtra("pincode");

        }
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Context context=getApplicationContext();
                String path = context.getFilesDir().getAbsolutePath();
                //File f = new File(path+"/undefined.png");
                //mMakePhotoUri = Uri.fromFile(f);
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST);


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               if(userList.isEmpty()){
                   Toast.makeText(getApplicationContext(), "Choose an image to submit feedback", Toast.LENGTH_LONG).show();
               }
               else{
                   if (isNetworkAvailable()) {
                       submitFeedback();

                   }else {
                       Toast.makeText(getApplicationContext(), "Sorry,Network unavailable",
                               Toast.LENGTH_LONG).show();
                   }
               }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Home_citizen.this, Home_0.class);
                startActivity(i);
            }
        });

    }

public void submitFeedback(){
    AsyncHttpClient client = new AsyncHttpClient();
    RequestParams params = new RequestParams();
    //System.out.println("Userlist:"+userList.get());
    Gson gson = new GsonBuilder().create();
    String resp=gson.toJson(userList);
    System.out.println(resp);

    params.put("usersJSON", resp);
    final ProgressDialog progress = new ProgressDialog(Home_citizen.this);
    progress.setTitle("Loading");
    progress.setMessage("Submitting your Feedback.....");
    progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
    progress.show();

    client.post("http://poolana9027.cloudapp.net/android_sms/feedback_submit.php", params, new AsyncHttpResponseHandler() {
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
                            if(obj.get("error").toString().equals("true")){
                                Toast.makeText(getApplicationContext(), "Error in Feedback Submission", Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(getApplicationContext(), "Feedback Submitted Successfully", Toast.LENGTH_LONG).show();
                                Intent ib = new Intent(Home_citizen.this, AndroidTabLayoutActivity.class);
                                startActivity(ib);
                            }
                        }
                    } catch (JSONException  e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    progress.dismiss();
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("hi");
        if (resultCode == RESULT_OK) {
            System.out.println("hi");

            if (requestCode == SELECT_PICTURE || requestCode == CAMERA_REQUEST ) {
                System.out.println("hi");

                try{
                    System.out.println("hi");
                    System.out.println("Iam hi");
                    //showPhoneWritePermission();
                    Uri selectedImageUri=data.getData();

                    if(requestCode==CAMERA_REQUEST) {
                        Bundle extras = data.getExtras();
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        selectedImageUri=getImageUri(getApplicationContext(), imageBitmap);
                       // ivImage.setImageBitmap(imageBitmap);
                    }
                    //Imview.setImageBitmap(imageBitmap);

                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP

                    if(requestCode==SELECT_PICTURE){
                        selectedImageUri=data.getData();
                    }

                if (null != selectedImageUri) {
                    System.out.println("Iam in");

                    InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
                 /*   byte[] inputData = Utils.getBytes(iStream);
                    Bitmap bm=Utils.getImage(inputData);
//                    ivImage.setImageURI(selectedImageUri);
                    queryValues.put("pincode",session1.getpincode());
                    queryValues.put("feedback_image",inputData.toString());
                    queryValues.put("status","0");
                    userList.add(queryValues);*/
                    byte[] inputData = Utils.getBytes(iStream);
                    System.out.println("inputdatabyte:"+inputData);
                    Bitmap bmm=Utils.getImage(inputData);
                 String feedback=Utils.getImageBytes(bmm);
                System.out.println("feedback:"+feedback);

                  //  String test=inputData.toString();

//                    byte[] test1=test.getBytes();
//                    System.out.println("test1byte:"+test);
//                    Bitmap test2=Utils.getImage(test1);
//                    String test3=Utils.getImageBytes(test2);
//                    System.out.println("test3:"+test3);


//                    byte[] decodedString = Base64.decode(test3, Base64.DEFAULT);
//                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    // Bitmap bm=Utils.getImage(inputData);
//                    ivImage.setImageURI(selectedImageUri);
                    queryValues.put("customer_id",session1.getusename());
                    queryValues.put("pincode",session1.getpincode());
                    queryValues.put("feedback_image",feedback);
                    queryValues.put("status","0");
                    userList.add(queryValues);
                Gson gson = new GsonBuilder().create();
                String resp=gson.toJson(userList);
                    System.out.println(resp);
                    ivImage.setImageBitmap(Utils.getResizedBitmap(bmm,1000,1000));
                    // Saving to Database...
//                    if (saveImageInDB(selectedImageUri)) {
//                    }
                }
                }catch(Exception e){
                    System.out.println(e);
                }

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.logout:
                session1.setLogin("NotLogged");
                Intent intent=new Intent(Home_citizen.this,Home_citizen_0.class);
                startActivity(intent);
                //logout code
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
