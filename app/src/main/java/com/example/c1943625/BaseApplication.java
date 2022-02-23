package com.example.c1943625;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class BaseApplication extends Application {
    public static final String CHANNEL_1_ID = "insertdata";

    //The notifications are based here because this is where the app is created, so we can call them whenever we need to
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Journal Entry",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Congrats! You get made a journal entry, hope to see you again!");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
