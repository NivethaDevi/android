package com.example.nivetha.cust_sur;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by nivetha on 26/07/17.
 */

public class AndroidTabLayoutActivity extends TabActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        TabHost tabHost = getTabHost();

        // Tab for Photos
        TabHost.TabSpec enter_feedback = tabHost.newTabSpec("Enter Feedback");
        // setting Title and Icon for the Tab
        enter_feedback.setIndicator("Enter Feedback");
        Intent enter_feedback_intent = new Intent(this,Home_citizen.class);
        enter_feedback.setContent(enter_feedback_intent);

        // Tab for Songs


        // Tab for Videos
        TabHost.TabSpec my_feedback = tabHost.newTabSpec("My Feedbacks");
        my_feedback.setIndicator("My Feedbacks");
        Intent videosIntent = new Intent(this,MyFeedback.class);
        my_feedback.setContent(videosIntent);
        tabHost.addTab(enter_feedback); // Adding photos tab
        tabHost.addTab(my_feedback); //
        // Adding all TabSpec to TabHost
        // Adding videos tab

    }
}
