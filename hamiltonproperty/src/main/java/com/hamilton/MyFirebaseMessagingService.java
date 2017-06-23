package com.hamilton;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Iterator;
import java.util.Map;


/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        Map<String, String> meMap = remoteMessage.getData();
        String message = null, title = null, createdAt, subtitle;
        Iterator myVeryOwnIterator = meMap.keySet().iterator();
        while (myVeryOwnIterator.hasNext()) {
            String key = (String) myVeryOwnIterator.next();
            String value = (String) meMap.get(key);
            if (key.equals("message")) {
                message = value;
            }

            if (key.equals("title")) {
                title = value;
            }


        }

        handleNotification(title, message);
        Log.d(TAG, "title=" + title + "\n message=" + message);

       /* // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());

            String remoteData = "{\"title\":\"Addzii Push Notification\",\"subtitle\":\"Test subtitle\",\"message\":\"Hello testing message\",\"type\":\"notification\",\"u\":3455,\"n\":2344}";

            Toast.makeText(AddziiApp.getInstance().getApplicationContext(), "" + remoteMessage.getData(), Toast.LENGTH_SHORT).show();

            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                handleDataMessage(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }*/
    }

    private void handleNotification(String title, String message) {

        try {
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            pushNotification.putExtra("title", title);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendBroadcast(new Intent(Config.PUSH_NOTIFICATION).putExtra("refresh", true));

        generateNotification(getApplicationContext(), message, title, null);
        /*if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message


            generateNotification(getApplicationContext(), title, message, null);
        } else {
            // If the app is in background, firebase itself handles the notification
        }*/
    }


    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }


    protected void generateNotification(Context context, String message, String title, String subTitle) {

        Intent launchIntent = new Intent(context, HomeActivity.class).putExtra("callNotification", true)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int id = (int) (System.currentTimeMillis());
        showNotification(context, message, launchIntent, id, title, subTitle);

    }

    @SuppressLint("NewApi")
    protected void showNotification(Context context, String message, Intent launchIntent, int id, String title, String subTitle) {
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification.Builder mBuilder = new Notification.Builder(this);

            mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(message);
            mBuilder.setWhen(System.currentTimeMillis());
            mBuilder.setContentIntent(PendingIntent.getActivity(context, 0, launchIntent, 0));
            mBuilder.setAutoCancel(true);
            mBuilder.setDefaults(Notification.DEFAULT_ALL);
            mBuilder.setPriority(Notification.PRIORITY_HIGH);
            Notification notification = new Notification.BigTextStyle(mBuilder).bigText(message).build();
            notificationManager.notify(0, notification);
        } else {
            ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(
                    id,
                    new NotificationCompat.Builder(context).setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher).setTicker(message)
                            .setContentTitle(title).setContentText(message)
                            .setContentIntent(PendingIntent.getActivity(context, 0, launchIntent, 0))
                            .setAutoCancel(true).build());
        }
    }

}