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

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToDay;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToHour;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToMiniute;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToMonth;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToSecond;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToYear;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class SetTodaySchduleAlarmsService extends IntentService {




    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param //name Used to name the worker thread, important only for debugging.
     */
    public SetTodaySchduleAlarmsService() {
        super("DailyStepsService");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        final  Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDate = sdf1.format(new Date());
            List<ScheduledLiveVideoCall> mTodayScheduledLiveVideoCallList = Repository.getContestantTodaySchdule(currentDate);
            if(mTodayScheduledLiveVideoCallList!=null){
                startAlarmTodayLiveVideoChatActivity(SetTodaySchduleAlarmsService.this,mTodayScheduledLiveVideoCallList.get(0));
            }
        }catch (Exception e){
            Log.i("",e.toString());
            Toast.makeText(SetTodaySchduleAlarmsService.this, e.toString(), Toast.LENGTH_SHORT).show();
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


    public void startAlarmTodayLiveVideoChatActivity(Context mContext,ScheduledLiveVideoCall todaySchdule) {

        int year = Integer.parseInt(convertDateTimeToYear((convertUTCToLocalTime(todaySchdule.getSessionStartsAt()))));
        int month = Integer.parseInt(convertDateTimeToMonth((convertUTCToLocalTime(todaySchdule.getSessionStartsAt()))));
        int day = Integer.parseInt(convertDateTimeToDay((convertUTCToLocalTime(todaySchdule.getSessionStartsAt()))));
        int hour = Integer.parseInt(convertDateTimeToHour(convertUTCToLocalTime(todaySchdule.getSessionStartsAt())));
        int miniute = Integer.parseInt(convertDateTimeToMiniute(convertUTCToLocalTime(todaySchdule.getSessionStartsAt())));
        int second = Integer.parseInt(convertDateTimeToSecond(convertUTCToLocalTime(todaySchdule.getSessionStartsAt())));



        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, miniute);
        c.set(Calendar.SECOND, second);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(mContext, PostLiveVideoCallSchduleNotificationReceiver.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1, intent,FLAG_UPDATE_CURRENT);

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
