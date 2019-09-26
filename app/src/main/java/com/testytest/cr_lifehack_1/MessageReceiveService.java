package com.testytest.cr_lifehack_1;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MessageReceiveService extends FirebaseMessagingService {


    public static final String TAG = "myLog";
    public static final String CHANNEL_ID = "my_channel_01";


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.w(TAG, "onNewToken: " + s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();
        String url = remoteMessage.getData().get("url");


        Log.w(TAG, "onMessageReceived: ");
        Intent intentOpen = new Intent(this, StartActivity.class);
        intentOpen.putExtra("url", url);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intentOpen, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, title, NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription("EZ");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(mChannel);
            mBuilder.setChannelId(CHANNEL_ID);
        }
        mBuilder.setContentTitle(title);
        mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText(body));
        mBuilder.setContentText(body);
        mBuilder.setAutoCancel(true);
        mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(sound);
        mBuilder.setSmallIcon(R.drawable.ne_plati_icon_1);
        mBuilder.setContentIntent(pi);
        notificationManager.notify(String.valueOf(Long.toString(System.currentTimeMillis())), 1, mBuilder.build());
    }


}