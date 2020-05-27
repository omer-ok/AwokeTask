package com.kampen.riksSe.FCM;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kampen.riksSe.BroadCastReciver.NotificationAlertService;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;


import static com.kampen.riksSe.FCM.Constants.CHANNEL_ID;
import static com.kampen.riksSe.FCM.Constants.CHANNEL_NAME;
import static com.kampen.riksSe.utils.Constants.setBadge;

public class NotificationService extends FirebaseMessagingService {

    Map<String,String> customData;
    String title = null;
    String body = null;
    private LocalBroadcastManager broadcaster;

    int ChatValueCounter;
    int badge;
    String StatusKey,weekStatus;

    private static final int MY_NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    private Notification myNotification;

    @Override
    public void onNewToken(String token) {
        //Log.d("T", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
       // sendRegistrationToServer(token);

        SaveSharedPreference.setUserFcmToken(this,token);


    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("", "From: " + remoteMessage.getFrom());

       /* // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            title = remoteMessage.getNotification().getTitle();
            body = remoteMessage.getNotification().getBody();

            //Log.e("notificationNUmber",":"+badge);



            SaveSharedPreference.setChatNotificationCounter(this,badge);
            //setBadge(getApplicationContext(), badge);
            //MyNotificationManager.getInstance(this).displayNotificationPIE(title,body,remoteMessage.getData());
        }*/
       Boolean ChatActivityStatus =false;
       ChatActivityStatus = SaveSharedPreference.getChatActivityState(this);

       if(!ChatActivityStatus){

           if(remoteMessage.getData() != null) {
               title = remoteMessage.getData().get("title");
               body = remoteMessage.getData().get("body");


               //Log.e("notificationNUmber",":"+badge);

               if (remoteMessage.getData().containsKey("chat")) {
                   badge = Integer.parseInt(remoteMessage.getData().get("chat_count"));
                   StatusKey = "chat";
                   ShortcutBadger.applyCount(this, badge); //for 1.1.4+
                   setBadge(this, badge);
                   SaveSharedPreference.setChatNotificationCounter(this, badge);
                   //ShortcutBadger.with(this).count(badge);
                   //ShortcutBadger.
               } else if (remoteMessage.getData().containsKey("nutrition")) {
                   StatusKey = "nutrition";
                   weekStatus = remoteMessage.getData().get("week");

               } else if (remoteMessage.getData().containsKey("workout")) {
                   StatusKey = "workout";
                   weekStatus = remoteMessage.getData().get("week");
               }


               //setBadge(getApplicationContext(), badge);
               //MyNotificationManager.getInstance(this).displayNotificationPIE(title,body,remoteMessage.getData());

               Boolean AppStatus = false;

               AppStatus = SaveSharedPreference.getAppKilledState(this);

               if (AppStatus) {



                   Intent i = new Intent(this, NotificationAlertService.class);
                   i.putExtra("title", title);
                   i.putExtra("body", body);
                   i.putExtra("Data", StatusKey);
                   i.putExtra("week", weekStatus);
                   this.startService(i);



               } else {
                   displayNotificationPIE(title, body, remoteMessage.getData(), this);
               }
           }

        }else{
           if (remoteMessage.getData().containsKey("chat")) {
               badge = Integer.parseInt(remoteMessage.getData().get("chat_count"));
               SaveSharedPreference.setChatNotificationCounter(this, badge);
           }
       }
        //Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }



    public void displayNotificationPIE(String title, String body, Map<String,String> activityValue,Context mCtx){

        int NOTIFICATION_ID = 234;

        NotificationManager notificationManager = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);

        Boolean AppStatus = false;

        AppStatus = SaveSharedPreference.getAppKilledState(mCtx);

        if(AppStatus){

            if(activityValue.containsKey("chat")){


                Intent intent = new Intent();

                SaveSharedPreference.setChatNotifictationCounterZero(mCtx,true);
                int badgeCount = SaveSharedPreference.getChatNotificationCounter(mCtx);

                intent.putExtra("ChatBadge",badgeCount);
                intent.setAction("com.rikskampen.CUSTOM_INTENT_CHAT_BADGE");
                LocalBroadcastManager.getInstance(mCtx).sendBroadcast(intent);
                /*badgeCount +=1;
                SaveSharedPreference.setChatNotificationCounter(mCtx,badgeCount);*/


            }

        }else{

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {



                String Description = "This is my channel";
                int importance = NotificationManager.IMPORTANCE_MAX;
                @SuppressLint("WrongConstant")
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(true);
                notificationManager.createNotificationChannel(mChannel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mCtx, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_app_launch)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true);


            if(activityValue.containsKey("nutrition")){


                int weekID = 0;
                String nutrition = activityValue.get("week");
                if(nutrition== null){
                    weekID =1;
                }else{
                    weekID = Integer.parseInt(nutrition);
                }
                Intent resultIntent = new Intent(mCtx, MainLeaderActivity.class);
                resultIntent.putExtra("From", "nutrition");
                resultIntent.putExtra("selected_week", weekID);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(mCtx);
                stackBuilder.addParentStack(MainLeaderActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
                notificationManager.notify(NOTIFICATION_ID, builder.build());



                /*Intent intent = new Intent();
                //intent.putExtra("ChatBadge",badgeCount);
                intent.setAction("com.rikskampen.CUSTOM_INTENT_NOTIFICATION");
                LocalBroadcastManager.getInstance(mCtx).sendBroadcast(intent);*/


/*
                Intent intent = new Intent();
                intent.setAction("com.rikskampen.CUSTOM_INTENT_NUTRITION_REFRESH_NOTIFICATION");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);*/
            }
            else if(activityValue.containsKey("workout")){

                int weekID = 0;
                String nutrition = activityValue.get("week");
                if(nutrition== null){
                    weekID =1;
                }else{
                    weekID = Integer.parseInt(nutrition);
                }
                Intent resultIntent = new Intent(mCtx, MainLeaderActivity.class);
                resultIntent.putExtra("From", "workout");
                resultIntent.putExtra("selected_week", weekID);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(mCtx);
                stackBuilder.addParentStack(MainLeaderActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
                notificationManager.notify(NOTIFICATION_ID, builder.build());

               /* Intent intent = new Intent();
                //intent.putExtra("ChatBadge",badgeCount);
                intent.setAction("com.rikskampen.CUSTOM_INTENT_NOTIFICATION");
                LocalBroadcastManager.getInstance(mCtx).sendBroadcast(intent);*/

                Intent intent = new Intent();
                intent.setAction("com.rikskampen.CUSTOM_INTENT_NUTRITION_REFRESH_NOTIFICATION");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

            }
            else if(activityValue.containsKey("chat")){

                Intent resultIntent = new Intent(mCtx, MainLeaderActivity.class);
                resultIntent.putExtra("From", "chat");
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(mCtx);
                stackBuilder.addParentStack(MainLeaderActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
                notificationManager.notify(NOTIFICATION_ID, builder.build());

                Intent intent = new Intent();

                SaveSharedPreference.setChatNotifictationCounterZero(mCtx,true);
                int badgeCount = SaveSharedPreference.getChatNotificationCounter(mCtx);

                intent.putExtra("ChatBadge",badgeCount);
                intent.setAction("com.rikskampen.CUSTOM_INTENT_CHAT_BADGE");
                LocalBroadcastManager.getInstance(mCtx).sendBroadcast(intent);
                /*badgeCount +=1;
                SaveSharedPreference.setChatNotificationCounter(mCtx,badgeCount);*/

            }else {

                Intent resultIntent = new Intent(mCtx, MainLeaderActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(mCtx);
                stackBuilder.addParentStack(MainLeaderActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(resultPendingIntent);
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }


        }



    }



}
