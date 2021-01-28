package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.commonduration.CommonActivity;

public class MainActivity extends CommonActivity {
    private static final String APP_TAG = "APP 2";
    private long startTimeStamp = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setActivityBackgroundColor(Color.GREEN);
        super.getTotalAppDuration(APP_TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimeStamp = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        long duration = System.currentTimeMillis() - startTimeStamp;
        super.saveLog(APP_TAG,duration);
    }
}