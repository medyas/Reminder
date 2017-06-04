package com.murach.reminder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mohamed Yassine on 6/4/2017.
 */

public class ReminderService extends Service {

    private Timer timer;

    @Override
    public void onCreate() {
        Log.d("Reminder", "Service Started");
        startTimer();
    }

    @Override
    public void onDestroy() {
        Log.d("Reminder", "Service Stoped");
        stopTimer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startTimer() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.d("Reminder", "Look into the distance. It’s good for your eyes!");

                not();

            }
        };

        timer = new Timer(true);
        int delay = 1000*10;
        int interval = 1000*10;
        timer.schedule(task, delay, interval);
    }

    private void stopTimer() {
        if(timer != null)
            timer.cancel();
    }

    public void not() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "New Notification";
        CharSequence contentTitle = getText(R.string.app_name);
        CharSequence contentText = "Look into the distance. It’s good for your eyes!";

        Intent notificationIntent = new Intent(this, ReminderActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int flag = PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, flag);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setSmallIcon(icon)
                .setTicker(tickerText)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        manager.notify(1, notification);
    }
}
