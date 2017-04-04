package com.example.chen.ex4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by Chen on 2016/10/20.
 */

public class DynamicReceiver extends BroadcastReceiver {
    private static String DYNAMICACTION = "com.example.chen.ex4.dynamicreceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DYNAMICACTION)) {
            Bundle bundle = intent.getExtras();
            String message = bundle.getString("message");
            Bitmap largerIcon = bundle.getParcelable("largerIcon");
            NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("动态广播")
                    .setContentText(message)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.dynamic)
                    .setLargeIcon(largerIcon)
                    .setAutoCancel(true);

            Intent intent1 = new Intent(context, mainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
            builder.setContentIntent(pendingIntent);

            Notification notification = builder.build();
            manager.notify(0, notification);
        }
    }
}
