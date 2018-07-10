package com.mrswimmer.shift.domain.service;

import android.app.Notification;
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

import java.util.Map;
import java.util.Random;


public class FCMService extends FirebaseMessagingService {

    private final String TAG = "code";
    int count = 0;
    int curtasks = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i("code", "data " + remoteMessage.getData().toString());
        Map<String, String> data = remoteMessage.getData();
        String fio = data.get("first") + " " + data.get("second") + " " + data.get("third");
        String id = data.get("id");
        Log.i("code", "data id " + id);
        sendNotification(id, fio);
        count++;
        curtasks++;
    }

    private void sendNotification(String id, String fio) {

        Intent intentYes = new Intent(this, SendResultService.class);
        intentYes.putExtra("result", 1);
        intentYes.putExtra("id", id);

        Log.i("code", "getex " + intentYes.getStringExtra("id"));

        Intent intentNo = new Intent(this, SendResultService.class);
        intentNo.putExtra("result", 0);
        intentNo.putExtra("id", id);

        PendingIntent pendingIntentYes = PendingIntent.getService(this, 0, intentYes, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentNo = PendingIntent.getService(this, 0, intentNo, PendingIntent.FLAG_UPDATE_CURRENT);
        /*Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.icon))
                .setContentTitle(this.getString(R.string.app_name))
                .setContentText(fio)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_yes, "Верно", pendingIntentYes)
                .addAction(R.drawable.ic_no, "Неверно", pendingIntentNo);

        notificationBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(count, notificationBuilder.build());
    }
}
