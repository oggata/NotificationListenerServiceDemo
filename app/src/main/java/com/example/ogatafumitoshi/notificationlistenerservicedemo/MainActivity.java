package com.example.ogatafumitoshi.notificationlistenerservicedemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends Activity {

    //notification
    private NotificationReceiver nReceiver;

    //ovelay
    private WindowManager windowManager;
    public OverlayView overlayView;
    private OverlayView.Listener overlayListener = new OverlayView.Listener(){};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get Intent from BroadcastReceiver
        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.ogatafumitoshi.NOTIFICATION_LISTENER_SERVICE_DEMO");
        registerReceiver(nReceiver, filter);

        //window
        windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        overlayView = OverlayView.create(this,overlayListener);
        windowManager.addView(overlayView, OverlayView.createLayoutParams(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }

    class NotificationReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("notification_msg") + "\n";
            overlayView.addComment(msg);
            overlayView.addStamp();
        }
    }
}