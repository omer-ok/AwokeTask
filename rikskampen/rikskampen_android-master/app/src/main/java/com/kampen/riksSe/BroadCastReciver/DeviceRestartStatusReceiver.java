package com.kampen.riksSe.BroadCastReciver;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.kampen.riksSe.R;
import com.kampen.riksSe.SplashActivity;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.POWER_SERVICE;


public class DeviceRestartStatusReceiver extends GCoreWakefulBroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        //Notification
        sendNotificationCongratulations(context);

        Intent i = new Intent(context, RestartAlarmsService.class);
        startWakefulService(context, i);
      /*  SaveSharedPreference.setStepsForeGroundServiceDestroyStatus(context, true);
        SaveSharedPreference.setStepCountingStopNotification(context,false);*/
        if(SaveSharedPreference.getStepsForeGroundServiceDestroyStatus(context)) {

        if(SaveSharedPreference.getStepCountingStopNotification(context)) {

            }
        }
    }


    private void sendNotificationCongratulations(Context context) {

        //String title = context.getResources().getString(R.string.General_Step_Count_Stop_Notification);
        String messageBody = "RiksKapen Team";
        Intent notificationIntent = new Intent(context, SplashActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("From","service");
        notificationIntent.putExtras(bundle);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {

            int NOTIFICATION_ID = 234;
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_MAX;
            @SuppressLint("WrongConstant")
            NotificationChannel mChannel = new NotificationChannel("006","sadasd", importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "006")
                .setSmallIcon(R.drawable.ic_app_launch)
                .setContentTitle("Device Restart Omer")
                .setContentText(messageBody)
                .setContentIntent(pIntent)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                .setAutoCancel(true);

        notificationManager.notify(006, builder.build());

    }


}
