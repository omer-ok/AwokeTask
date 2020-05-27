package com.kampen.riksSe.BroadCastReciver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static android.content.Context.POWER_SERVICE;


public class SetTodayLiveVideoCallNotification extends GCoreWakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Boolean isLogIn = SaveSharedPreference.getLoggedStatus(context);
        if(isLogIn){
            PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
            wakeLock.acquire(60 * 1000L);
            Intent i = new Intent(context,SetTodaySchduleAlarmsService.class);
            startWakefulService(context,i);
            startAlarmReset(context);
            wakeLock.release();
        }else{

        }


    }

    public void startAlarmReset(Context context){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context, SetTodayLiveVideoCallNotification.class);
        myIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 1);
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
    public void cancelAlarmIfExists(Context mContext,int requestCode){
        try {
            Intent myIntent = new Intent(mContext, SetTodayLiveVideoCallNotification.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, requestCode, myIntent,FLAG_UPDATE_CURRENT);
            AlarmManager am=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            pendingIntent.cancel();
            am.cancel(pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
