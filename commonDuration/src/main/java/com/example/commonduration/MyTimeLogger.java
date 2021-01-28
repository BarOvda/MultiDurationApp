package com.example.commonduration;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class MyTimeLogger extends AppCompatActivity {
    private static com.example.commonduration.MyTimeLogger instance;
    private static com.example.commonduration.AppDatabase appDatabase;

    private MyTimeLogger(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), com.example.commonduration.AppDatabase.class, "TlogsDB.db")
                // allow queries on the main thread.
                // Don't do this on a real app! See PersistenceBasicSample for an example.
                // .allowMainThreadQueries()
                .build();
    }

    public static com.example.commonduration.MyTimeLogger getInstance() {
        return instance;
    }

    static com.example.commonduration.MyTimeLogger initHelper(Context context) {
        if (instance == null) {
            instance = new com.example.commonduration.MyTimeLogger(context);
        }

        return instance;
    }


    public interface CallBack_Logs {
        void dataReady(List<TLog> tLogs);
    }

    public interface CallBack_Time {
        void    dataReady(long time);
    }

    public void getTotalDuration(String tag, CallBack_Time callBack_time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TLog> tLogs = appDatabase.tLogDao().getAllByTag(tag);
                long sum = 0;
                for (TLog tLog : tLogs) {
                    sum += tLog.duration;
                }

                if (callBack_time != null) {
                    callBack_time.dataReady( sum );
                }
            }
        }).start();
    }

    public void addTlogTime(String tag, int durationSec) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.tLogDao().insertAll(new TLog(tag, System.currentTimeMillis(), durationSec));
            }
        }).start();
    }


    public void getAllLogs(CallBack_Logs callBack_logs) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TLog> tLogs = appDatabase.tLogDao().getAll();
                if (callBack_logs != null) {
                    callBack_logs.dataReady(tLogs);
                }
            }
        }).start();
    }
}
