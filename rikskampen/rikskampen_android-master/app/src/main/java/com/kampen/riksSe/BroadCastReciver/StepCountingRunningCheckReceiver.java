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
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.POWER_SERVICE;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_VALUE;


public class StepCountingRunningCheckReceiver extends GCoreWakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Boolean isLogIn = SaveSharedPreference.getLoggedStatus(context);
        if(isLogIn){
            /*PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
            wakeLock.acquire(10*60*1000L);*/


            if(!Constants.isMyServiceRunning(StepCountingService.class, context)) {

               /*ActivityFragment.newInstance().startGPSService(context);*/
                sendNotificationCongratulations(context);
            }
            startRepatingAlarm(context);

            //wakeLock.release();

        }else{

        }


    }

    public void startRepatingAlarm(Context context) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            String[] hours = currentDateandTime.split(":");

            int hoursInt = Integer.parseInt(hours[0]);
            int miniuteInt = Integer.parseInt(String.valueOf(hours[1]));
            int secondsInt = Integer.parseInt(String.valueOf(hours[2]));


            hoursInt+=2;
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent myIntent = new Intent(context,MyBroadcastReceiver.class);
            myIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, hoursInt);
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

        }catch (Exception e){

    }

    }
    private void sendNotificationCongratulations(Context context) {

        String title = "Open RiksKampen to not lose you steps";
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

        notificationManager.notify(004, builder.build());
    }
    public void cancelAlarmIfExists(Context mContext,int requestCode){
        try {
            Intent myIntent = new Intent(mContext, StepCountingRunningCheckReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, requestCode, myIntent,FLAG_UPDATE_CURRENT);
            AlarmManager am=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            pendingIntent.cancel();
            am.cancel(pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
