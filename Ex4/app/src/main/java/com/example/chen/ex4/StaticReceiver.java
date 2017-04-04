package com.example.chen.ex4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.Settings;

/**
 * Created by Chen on 2016/10/20.
 */

public class StaticReceiver extends BroadcastReceiver {
    private static final String STATICACTION = "com.example.chen.ex4.staticreceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(STATICACTION)) {
            Bundle bundle = intent.getExtras();
            String name = bundle.getString("name");
            Bitmap picture = bundle.getParcelable("largerIcon");

            NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("静态广播")
                    .setContentText(name)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.orange)
                    .setLargeIcon(picture)
                    .setAutoCancel(true);

            Intent intent1 = new Intent(context, mainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
            builder.setContentIntent(pendingIntent);

            Notification notification = builder.build();
            manager.notify(0, notification);
        }
    }
}
