package com.mrswimmer.shift.domain.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FCMService extends FirebaseMessagingService {

    private final String TAG = "code";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.i(TAG, "Body: " + remoteMessage.getNotification().getBody());
            // do with Notification payload...
            // remoteMessage.getNotification().getBody()
        }

        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Data: " + remoteMessage.getData());
            // do with Data payload...
            // remoteMessage.getData()
        }
    }
}
