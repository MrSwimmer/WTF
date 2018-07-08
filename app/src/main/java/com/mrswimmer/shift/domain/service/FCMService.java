package com.mrswimmer.shift.domain.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mrswimmer.shift.R;
import com.mrswimmer.shift.presentation.main.activity.MainActivity;


public class FCMService extends FirebaseMessagingService {

    private final String TAG = "code";
    int count = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i("code", "data " + remoteMessage.getData().toString());
        sendNotification(remoteMessage.getData().get("first"));
        count++;
    }

    private void sendNotification(String body) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.icon))
                .setContentTitle(this.getString(R.string.app_name))
                .setContentText(body + count)
                .addAction(R.drawable.ic_yes, "Верно", pendingIntent)
                .addAction(R.drawable.ic_no, "Неверно", pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}
