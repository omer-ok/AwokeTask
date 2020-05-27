package com.kampen.riksSe.FCM;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.Map;


import static com.kampen.riksSe.FCM.Constants.CHANNEL_ID;
import static com.kampen.riksSe.FCM.Constants.CHANNEL_NAME;

public class MyNotificationManager  {

    private Context mCtx;
    private static MyNotificationManager mInstance;
    int ChatValueCounter=0;

    private MyNotificationManager(Context context) {
        mCtx = context;
    }

    public static synchronized MyNotificationManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }




    public void displayNotificationPIE(String title, String body, Map<String,String> activityValue){

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

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {



                String Description = "This is my channel";
                int importance = NotificationManager.IMPORTANCE_MAX;
                @SuppressLint("WrongConstant")
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(false);
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

            }
            else if(activityValue.containsKey("chat")){

                Intent resultIntent = new Intent(mCtx, ChatActivity.class);
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
