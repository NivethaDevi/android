package com.example.nivetha.cust_sur;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by nivetha on 26/07/17.
 */

public class AndroidTabLayoutActivity1 extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        TabHost tabHost = getTabHost();

        // Tab for Photos
        TabHost.TabSpec enter_feedback = tabHost.newTabSpec("Survey");
        // setting Title and Icon for the Tab
        enter_feedback.setIndicator("Survey");
        Intent enter_feedback_intent = new Intent(this,MainActivity.class);
        enter_feedback.setContent(enter_feedback_intent);

        // Tab for Songs


        // Tab for Videos
        TabHost.TabSpec my_feedback = tabHost.newTabSpec("Queries/Complaints");
        my_feedback.setIndicator("Queries/Complaints");
        Intent videosIntent = new Intent(this,MyFeedback1.class);
        my_feedback.setContent(videosIntent);
        tabHost.addTab(enter_feedback); // Adding photos tab
        tabHost.addTab(my_feedback); //

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("tab_index");
            if(value.equals("1")){
                tabHost.setCurrentTab(Integer.valueOf(value));
            }
            //and so on ...

        }
        // Adding all TabSpec to TabHost
        // Adding videos tab

    }
}
