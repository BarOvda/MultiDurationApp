package com.example.multidurationapp;

import android.graphics.Color;
import android.os.Bundle;

import com.example.commonduration.CommonActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class MainActivity extends CommonActivity {
    private static final String APP_TAG = "APP 1";
    private long startTimeStamp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        super.setActivityBackgroundColor(Color.BLUE);
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