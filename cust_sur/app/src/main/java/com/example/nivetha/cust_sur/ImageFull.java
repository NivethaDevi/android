package com.example.nivetha.cust_sur;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by nivetha on 29/07/17.
 */

public class ImageFull extends Activity {
    ImageView img;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageview);
        Intent intent=new Intent();
        img=(ImageView)findViewById(R.id.Img);
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        img.setImageBitmap(Utils.getResizedBitmap(bitmap,1000,500));

    }

}
