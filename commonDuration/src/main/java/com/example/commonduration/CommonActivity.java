package com.example.commonduration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

public class CommonActivity extends AppCompatActivity {


    private long startTimeStamp = 0;
    private TextView garageTextView;
    private TextView totalTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        MyTimeLogger.initHelper(getApplicationContext());
        garageTextView = findViewById(R.id.garage_textView);
        totalTimeTextView = findViewById(R.id.time_textView);

        downloadGarage();
    }

    private void downloadGarage() {
        new GarageController().fetchGarage(new GarageController.CallBack_Garage() {
            @Override
            public void garage(Garage garage) {
                garageTextView.setText(garage.toString());
            }
        });
    }


    protected void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    protected void saveLog(String tag,float duration) {
        MyTimeLogger.getInstance().addTlogTime(tag, (int) duration);
    }

    protected void getTotalAppDuration(String tag) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyTimeLogger.getInstance().getTotalDuration(tag, new MyTimeLogger.CallBack_Time() {
                    @Override
                    public void dataReady(long time) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                long seconds = time / 1000;
                                long minutes = seconds / 60;
                                long hours = minutes / 60;
                                long days = hours / 24;
                                String timeString ="days:" +days + " hours:" + hours % 24 + " mins:" + minutes % 60 + " secs:" + seconds % 60;
                                totalTimeTextView.setText(tag+" total time: " + timeString);
                            }
                        });
                    }
                });
            }
        }).start();
    }
}