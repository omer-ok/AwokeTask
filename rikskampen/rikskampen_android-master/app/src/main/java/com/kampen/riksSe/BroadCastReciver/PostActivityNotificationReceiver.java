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

import androidx.core.app.NotificationCompat;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.R;
import com.kampen.riksSe.SplashActivity;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.Calendar;

import io.realm.Realm;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.POWER_SERVICE;


public class PostActivityNotificationReceiver extends GCoreWakefulBroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        Boolean isLogIn = SaveSharedPreference.getLoggedStatus(context);
        if(isLogIn){
            PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
            wakeLock.acquire(60 * 1000L);

            if(SaveSharedPreference.getDailyImage(context).isEmpty() && !SaveSharedPreference.getPostActivityNotificationStatus(context)) {
                sendNotificationCongratulations(context);
                SaveSharedPreference.setPostActivityNotificationStatus(context,true);
            }
            startAlarmPostActivityChange(context);

            wakeLock.release();

        }else{

        }


    }

    public void startAlarmPostActivityChange(Context context) {


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context,MyBroadcastReceiver.class);
        myIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 20);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(c.getTimeInMillis(),pendingIntent),pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }


    private void sendNotificationCongratulations(Context context) {

        String title = context.getResources().getString(R.string.General_Activity_Reminder_Notification)+" \uD83D\uDCF8";
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
            NotificationChannel mChannel = new NotificationChannel("005","sadasd", importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "005")
                .setSmallIcon(R.drawable.ic_app_launch)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        notificationManager.notify(003, builder.build());
    }
    public void cancelAlarmIfExists(Context mContext,int requestCode){
        try {
            Intent myIntent = new Intent(mContext, PostActivityNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, requestCode, myIntent,FLAG_UPDATE_CURRENT);
            AlarmManager am=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            pendingIntent.cancel();
            am.cancel(pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
