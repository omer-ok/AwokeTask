package com.kampen.riksSe.BroadCastReciver;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_DISTANCE;
import static com.kampen.riksSe.data_manager.Repository.DEVICE_MODEL;
import static com.kampen.riksSe.data_manager.Repository.STATUS_SUCCESS;
import static com.kampen.riksSe.data_manager.Repository.TIME_ZONE;
import static com.kampen.riksSe.data_manager.Repository.USERID;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;

public class RestartAlarmsService extends IntentService {




    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param //name Used to name the worker thread, important only for debugging.
     */
    public RestartAlarmsService() {
        super("DailyStepsService");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final  Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try {
            Toast.makeText(RestartAlarmsService.this, "Alarms Restarted", Toast.LENGTH_SHORT).show();
            startAlarm(RestartAlarmsService.this);
            startAlarmWakeUIAtMidNight(RestartAlarmsService.this);
            startAlarmDateChange(RestartAlarmsService.this);
            startAlarmUpdateActivityWithGoogle(RestartAlarmsService.this);


        }catch (Exception e){

            Log.i("",e.toString());
            Toast.makeText(RestartAlarmsService.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        return START_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //MyBroadcastReceiver.completeWakefulIntent(intent);
    }

    public void startAlarm(Context context) {

        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent,FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(c.getTimeInMillis(),pendingIntent),pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

    public void startAlarmWakeUIAtMidNight(Context context) {


        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DeviceWakeUpMidNightReceiver.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent,FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(c.getTimeInMillis(),pendingIntent),pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }

    }

    public void startAlarmDateChange(Context context) {


        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, DateChangeReceiver.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent,FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(c.getTimeInMillis(),pendingIntent),pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
            // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),5000, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }

    public void startAlarmUpdateActivityWithGoogle(Context context) {

        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 57);
        c.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, UpdateTodayActivityWithGoogleReceiver.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent,FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(c.getTimeInMillis(),pendingIntent),pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
    }



}
