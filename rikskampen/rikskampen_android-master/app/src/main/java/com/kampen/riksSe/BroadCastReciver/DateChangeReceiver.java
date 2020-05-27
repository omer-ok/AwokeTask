package com.kampen.riksSe.BroadCastReciver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.POWER_SERVICE;

public class DateChangeReceiver extends GCoreWakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        /*Intent i= new Intent(context, DailyStepsService.class);
        context.startService(i);*/

        PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
        wakeLock.acquire(60 * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String NextDayDateAndTime = sdf.format(new Date());
        String TodayDateAndTime = SaveSharedPreference.getStepDaySessionDate(context);
        if(!TodayDateAndTime.equals(NextDayDateAndTime)){
            Intent intent1 = new Intent();
            intent1.setAction("com.Rikskampen.CUSTOM_INTENT_ACTIVITY_DATE_CHANGE");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
            startAlarmReset(context);
            SaveSharedPreference.setStepDaySessionDate(context,NextDayDateAndTime);
        }
        wakeLock.release();
       /* try {
            Date currentTimeStartRange = sdf.parse("23:59:59");
            Date currentTimeEndRange = sdf.parse("24:01:00");
            Date CurrentTime = sdf.parse(currentTime);

            if(CurrentTime.after(currentTimeStartRange) || CurrentTime.before(currentTimeEndRange)){
                Intent intent1 = new Intent();
                intent1.setAction("com.Rikskampen.CUSTOM_INTENT_ACTIVITY_DATE_CHANGE");
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }



    public void startAlarmReset(Context context){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context,DateChangeReceiver.class);
        myIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(c.getTimeInMillis(),pendingIntent),pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }


    }

}
