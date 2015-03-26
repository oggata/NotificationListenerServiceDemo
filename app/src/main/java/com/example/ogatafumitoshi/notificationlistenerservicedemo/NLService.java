package com.example.ogatafumitoshi.notificationlistenerservicedemo;

/**
 * Created by ogata.fumitoshi on 2015/03/26.
 */

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
        Intent i = new  Intent("com.example.ogatafumitoshi.NOTIFICATION_LISTENER_SERVICE_DEMO");
        i.putExtra("notification_msg",""
                + "ID :" + sbn.getId() + "\n"
                + "Txt :" + sbn.getNotification().tickerText + "\n"
                + "Pkg :" + sbn.getPackageName() + "\n"
                + "Tim :" + sbn.getPostTime() + "\n"
                + "\n");
        i.putExtra("getNotification",sbn.getNotification().tickerText);
        sendBroadcast(i);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText +"\t" + sbn.getPackageName());
        Intent i = new  Intent("com.example.ogatafumitoshi.NOTIFICATION_LISTENER_SERVICE_DEMO");
        i.putExtra("notification_msg",""
                + "ID :" + sbn.getId()
                + "Txt :" + sbn.getNotification()
                + "Pkg :" + sbn.getPackageName()
                + "Tim :" + sbn.getPostTime()
                + "\n");
        sendBroadcast(i);
    }
}