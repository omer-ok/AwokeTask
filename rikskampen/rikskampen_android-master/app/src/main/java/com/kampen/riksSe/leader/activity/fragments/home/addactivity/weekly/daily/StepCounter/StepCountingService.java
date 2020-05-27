package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter;


import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kampen.riksSe.BroadCastReciver.DailyActivitySyncService;
import com.kampen.riksSe.BroadCastReciver.StepCountingStop;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.R;
import com.kampen.riksSe.SplashActivity;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.map.tracking.NotificationClass;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

import static androidx.legacy.content.WakefulBroadcastReceiver.startWakefulService;
import static com.kampen.riksSe.utils.Constants.DATE_FORMAT;
import static com.kampen.riksSe.utils.UtilityTz.CaloriesCalulatorFromSteps;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;
import static com.kampen.riksSe.utils.UtilityTz.getDistanceNow;
import static com.kampen.riksSe.utils.UtilityTz.getYesterdayDateString;

// _________ Extend Service class & implement Service lifecycle callback methods. _________ //
public class StepCountingService extends IntentService implements SensorEventListener {

/*   public SensorManager sensorManager;
   public Sensor stepCounterSensor;
    Sensor stepDetectorSensor;*/
    private StepsCountResult stepsCountResult;
    //int currentStepCount;
    int currentStepsDetected;
    private FirebaseAnalytics mFirebaseAnalytics;
    int stepCounter;
    int newStepCounter;
    Context c = this;
    boolean serviceStopped; // Boolean variable to control the repeating timer.

    private  String mNotificationName = "my_channel_01";

    private  int mNotificationId = 001;
    NotificationManager notificationManager;
    NotificationClass nc = new NotificationClass();
    // --------------------------------------------------------------------------- \\
    // _ (1) declare broadcasting element variables _ \\
    // Declare an instance of the Intent class.
    Intent intent;
    // A string that identifies what kind of action is taking place.
    private static final String TAG = "StepService";
    public static final String BROADCAST_ACTION = "com.websmithing.yusuf.mybroadcast";
    // Create a handler - that will be used to broadcast our data, after a specified amount of time.
    private final Handler handler = new Handler();
    // Declare and initialise counter - for keeping a record of how many times the service carried out updates.
    int counter = 0;
    int countSteps1;
    private Realm_User mUser;

    String currentDateandTime;
    boolean stepCounterSensorstatus,stepDetectorSensorstatus;

    private FitnessOptions fitnessOptions;
    OnDataPointListener mListener;

    private static final String TAG1 = "GoogleFitServices";


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param //name Used to name the worker thread, important only for debugging.
     */
    public StepCountingService() {
        super("StepCountingService");

    }

    // ___________________________________________________________________________ \\
    static IntentFilter  s_intentFilter;
    static {
        s_intentFilter = new IntentFilter();
        /*s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);*/
        s_intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
    }
    static IntentFilter  s_intentFilter1;
    static {
        s_intentFilter1 = new IntentFilter();
        /*s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);*/
        s_intentFilter1.addAction(Intent.ACTION_SCREEN_ON);

    }
    Realm_User userGlobal;

    /** Called when the service is being created. */
    @Override
    public void onCreate() {
        super.onCreate();

        ForegroundServiceInitialize();

         notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // --------------------------------------------------------------------------- \\
        // ___ (2) create/instantiate intent. ___ \\
        // Instantiate the intent declared globally, and pass "BROADCAST_ACTION" to the constructor of the intent.
       /* intent = new Intent(Constants.START_ACTION);*/

        c.registerReceiver(m_timeChangedReceiver, s_intentFilter);
        //c.registerReceiver(ScreenOpen, s_intentFilter1);
        //c.registerReceiver(StepCountingBroadCast, "com.Rikskampen.CUSTOM_INTENT_STEPSSENSOR");
/*        LocalBroadcastManager.getInstance(c).registerReceiver(StepCountingBroadCast,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_STEPSSENSOR"));*/

        LocalBroadcastManager.getInstance(c).registerReceiver(DateChangeReceiver,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_ACTIVITY_DATE_CHANGE"));

        LocalBroadcastManager.getInstance(c).registerReceiver(GoogleFitSteps,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_GOOGLE_FIT_STEPS"));

        // ___________________________________________________________________________ \\


    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.v("Service", "Start");

       SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
       Sensor stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
       Sensor stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        userGlobal = provideUserLocal(c);
        if(intent.getAction().equals(Constants.START_ACTION))
        {
            ForegroundServiceInitialize();
            stepCounterSensorstatus=sensorManager.registerListener(StepCountingService.this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
            stepDetectorSensorstatus=sensorManager.registerListener(StepCountingService.this, stepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else
        {
            sensorManager.unregisterListener(StepCountingService.this,stepCounterSensor);
            sensorManager.unregisterListener(StepCountingService.this,stepDetectorSensor);

            stopForeground(true);
            stopSelf();
            return START_NOT_STICKY;
        }

        if(stepCounterSensorstatus=true){
            SaveSharedPreference.setStepCounterPermission(c,true);
        }else{
            SaveSharedPreference.setStepCounterPermission(c,false);
            stopForeground(true);
            stopSelf();
            return START_NOT_STICKY;
        }
        Intent i = new Intent(c, StepCountingStop.class);
        startWakefulService(c,i);
        currentStepsDetected = 0;
        stepCounter = 0;
        newStepCounter = 0;
        serviceStopped = false;

        return START_STICKY;
    }




    public class LocalBinder extends Binder {
        public   StepCountingService getService() {
            // Return this instance of LocalService so clients can call public methods
            return StepCountingService.this;
        }
    }
    /** A client is binding to the service with bindService() */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }


    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.v("Service", "Stop");
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());


        serviceStopped = true;
        notificationManager.cancel(mNotificationId);
        sendNotificationCongratulations(c);
        SaveSharedPreference.setStepsForeGroundService(c,false);
        SaveSharedPreference.setStepsForeGroundServiceDestroyStatus(c,true);
        SaveSharedPreference.setStepsDateTimeToFetechHistoryFromGoogleFit(c,currentDateandTime);
        SaveSharedPreference.setStepCountingStopNotification(c,true);
        Intent i = new Intent(c, StepCountingStop.class);
        startWakefulService(c,i);

        stopForeground(true);
        stopSelf();

        //dismissNotification();
    }

    /** Called when the overall system is running low on memory, and actively running processes should trim their memory usage. */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /////////////////__________________ Sensor Event. __________________//////////////////
    @Override
    public void onSensorChanged(SensorEvent event) {
        // STEP_COUNTER Sensor.
        // *** Step Counting does not restart until the device is restarted - therefore, an algorithm for restarting the counting must be implemented.
        /*if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int countSteps = (int) event.values[0];

            // -The long way of starting a new step counting sequence.-
            *//**
             int tempStepCount = countSteps;
             int initialStepCount = countSteps - tempStepCount; // Nullify step count - so that the step cpuinting can restart.
             currentStepCount += initialStepCount; // This variable will be initialised with (0), and will be incremented by itself for every Sensor step counted.
             stepCountTxV.setText(String.valueOf(currentStepCount));
             currentStepCount++; // Increment variable by 1 - so that the variable can increase for every Step_Counter event.
             *//*

            // -The efficient way of starting a new step counting sequence.-
            if (stepCounter == 0) { // If the stepCounter is in its initial value, then...
                stepCounter = (int) event.values[0]; // Assign the StepCounter Sensor event value to it.
            }
            newStepCounter = countSteps - stepCounter; // By subtracting the stepCounter variable from the Sensor event value - We start a new counting sequence from 0. Where the Sensor event value will increase, and stepCounter value will be only initialised once.




        }

        // STEP_DETECTOR Sensor.
        // *** Step Detector: When a step event is detect - "event.values[0]" becomes 1. And stays at 1!
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            int detectSteps = (int) event.values[0];
            currentStepsDetected += detectSteps; //steps = steps + detectSteps; // This variable will be initialised with the STEP_DETECTOR event value (1), and will be incremented by itself (+1) for as long as steps are detected.
        }

        Log.v("Service Counter", String.valueOf(newStepCounter));*/
        try{

            if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

                countSteps1 = (int) event.values[0];

            }
                /*if(stepCounterSensorstatus){
                    if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

                        countSteps1 = (int) event.values[0];

                    }
                }else if(stepDetectorSensorstatus){
                   if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {

                        countSteps1 = (int) event.values[0];
                    }
                }*/

            if(stepsCountResult != null) {
                stepsCountResult.onStepsService(countSteps1);
            }
            if (countSteps1 == 0) {
                ActivityDaily activityDaily = Repository.getStepCounterLocalV3();
                if (activityDaily != null) {
                    if (activityDaily.getmSteps() != 0) {
                        SaveSharedPreference.setUserLogInSteps(c, activityDaily.getmSteps());
                    }
                }
                SaveSharedPreference.setSensorStaticSteps(c, countSteps1);
            }

            if(SaveSharedPreference.getStepsForeGroundServiceDestroyStatus(c)){
                Competition CompitionDate = Repository.getCompitionDate();
                if(SaveSharedPreference.getGoogleFitStatus(c)){
                    SaveSharedPreference.setSensorStaticSteps(c, countSteps1);
                    int GoogleTodaySteps =  SaveSharedPreference.getGoogleFitTodaySteps(c);
                    int todaySteps = 0;
                    activityAdapterListModel todayActivity =  Repository.GetTodayActivityV1(c,convertUTCToLocalTime(CompitionDate.getStartDate()));
                    if(todayActivity!=null){
                        todaySteps = todayActivity.getmSteps();
                    }else{
                        todaySteps = 0;
                    }
                    if(GoogleTodaySteps>todaySteps){
                        SaveSharedPreference.setUserLogInSteps(c,GoogleTodaySteps);
                    }else{
                        int TotalSteps = todaySteps + GoogleTodaySteps;
                        SaveSharedPreference.setUserLogInSteps(c,TotalSteps);
                    }
                    SaveSharedPreference.setStepsForeGroundServiceDestroyStatus(c,false);
                }
            }

            UpdateStepsFromSensor(countSteps1);



        }catch ( Exception e){
            Log.i("SensorStepsEx",e.toString());
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(c);
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(e.getStackTrace()));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            SaveSharedPreference.setStepCounterPermission(c,false);
            stopForeground(true);
            stopSelf();
        }
    }

    private final GCoreWakefulBroadcastReceiver StepCountingBroadCast = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int steps = intent.getIntExtra("StepsSensor",0);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                String currentDateandTime = sdf.format(new Date());
                String UserLogInDate = SaveSharedPreference.getUserLogInDate(c);
                if(SaveSharedPreference.getLoggedInStepsFirstTime(c)){
                    SaveSharedPreference.setLoggedInStepsFirstTime(c,false);
                    SaveSharedPreference.setSensorStaticSteps(c,steps);
                }
                if(UserLogInDate.equals(currentDateandTime)){
                    int LoginSteps = Math.abs(SaveSharedPreference.getUserLogInSteps(c));


                    steps =  LoginSteps + (steps - SaveSharedPreference.getSensorStaticSteps(c));

                    if(steps>=0){
                        UpdateStepsFromSensor(steps);
                    }
                }else{

                    ActivityDaily activityDaily = new ActivityDaily();
                    activityDaily.setmSteps(0);
                    activityDaily.setmStars(0);
                    activityDaily.setmCalories(0);
                    activityDaily.setmDistance(0);
                    activityDaily.setmDate(convertTimeToUTC());
                    activityDaily.setSyncedWithServer(false);
                    Repository.AddStepCounterLocalV3(activityDaily);

                    SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
                    String currentDateandTime2 = sdf2.format(new Date());
                    // SensorSteps = steps;
                    int StaticSensorSteps = SaveSharedPreference.getSensorStaticSteps(c);
                    if(steps>StaticSensorSteps){
                        SaveSharedPreference.setSensorStaticSteps(c,steps);
                    }
                    Realm_User user = provideUserLocal(c);
                    if(user!=null){
                        SaveSharedPreference.setPostActivityNotificationStatus(context,false);
                        SaveSharedPreference.setUserLogInSteps(c,0);
                        SaveSharedPreference.setUsertodaySteps(c,0);
                        SaveSharedPreference.setUserStarCount(c,0);
                        SaveSharedPreference.setWaistToday(c,"0");
                        SaveSharedPreference.setWeightToday(c,"0");
                        SaveSharedPreference.setDailyDate(c,"");
                        SaveSharedPreference.setDailyImage(c,"");
                        SaveSharedPreference.setLocation(c,"");
                        updateStarUserLocal("0",user.getId());
                        updateStepsUserLocal("0",user.getId());
                        updateActivityDataLocal(null,null,null,user.getId());
                        SaveSharedPreference.setUserLogInDate(c,currentDateandTime2);
                        SaveSharedPreference.setStepDaySessionDate(c,currentDateandTime2);
                        //ForegroundServiceInitialize();
                        UpdateStepCounter();
                    }

                }

            }catch (Exception ex){
                Log.i("StepsCountEx",ex.toString());
                //Toast.makeText(c, "StepsCountEx", Toast.LENGTH_SHORT).show();
                mFirebaseAnalytics = FirebaseAnalytics.getInstance(c);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(ex.getStackTrace()));
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                SaveSharedPreference.setStepCounterPermission(c,false);
                stopForeground(true);
                stopSelf();
            }
        }
    };

/*    private final GCoreWakefulBroadcastReceiver ScreenOpen = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(Intent.ACTION_SCREEN_ON) *//*||
                    action.equals(Intent.ACTION_TIMEZONE_CHANGED)*//*) {
                UpdateStepCounter();
            }
        }
    };*/

    private final GCoreWakefulBroadcastReceiver GoogleFitSteps = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try{

            }catch (Exception ex){

            }
        }
    };

    private final GCoreWakefulBroadcastReceiver DateChangeReceiver = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            //Toast.makeText(context, "DateChanged BraodCast Lives Steps", Toast.LENGTH_SHORT).show();

            try{
                PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
                wakeLock.acquire(60 * 1000L);

                Realm_User user= provideUserLocal(c);

                if(user!=null){
                    Competition CompitionDate = Repository.getCompitionDate();
                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTime = sdf.format(new Date());
                    if(CompitionDate!=null){
                        if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                            Boolean ContestStatus = getCompitionStartDate(c,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                            Boolean ContestEndStatus = getCompitionStartDate(c,convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                            if(ContestStatus && !ContestEndStatus){

                                if(SaveSharedPreference.getLoggedInFirstTime(c)){
                                    ActivityDaily activityDaily = new ActivityDaily();
                                    activityDaily.setmSteps(0);
                                    activityDaily.setmStars(0);
                                    activityDaily.setmCalories(0);
                                    activityDaily.setmDistance(0);
                                    activityDaily.setmDate(convertTimeToUTC());
                                    activityDaily.setSyncedWithServer(false);
                                    Repository.AddStepCounterLocalV3(activityDaily);
                                    SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
                                    String currentDateandTime1 = sdf1.format(new Date());
                                    SaveSharedPreference.setSensorSteps(c,countSteps1);
                                    SaveSharedPreference.setGoogleFitTodayStepsSteps(c,0);
                                    int StaticSensorSteps = SaveSharedPreference.getSensorStaticSteps(c);

                                    if(countSteps1==0){
                                        SaveSharedPreference.setSensorStaticSteps(c,countSteps1);
                                    }
                                    if(countSteps1>StaticSensorSteps){
                                        SaveSharedPreference.setSensorStaticSteps(c,countSteps1);
                                    }
                                    SaveSharedPreference.setPostActivityNotificationStatus(context,false);
                                    SaveSharedPreference.setUserLogInSteps(c,0);
                                    SaveSharedPreference.setUsertodaySteps(c,0);
                                    SaveSharedPreference.setUserStarCount(c,0);
                                    SaveSharedPreference.setWaistToday(c,"0");
                                    SaveSharedPreference.setWeightToday(c,"0");
                                    SaveSharedPreference.setDailyDate(c,"");
                                    SaveSharedPreference.setDailyImage(c,"");
                                    SaveSharedPreference.setLocation(c,"");
                                    updateStarUserLocal("0",user.getId());
                                    updateStepsUserLocal("0",user.getId());
                                    updateActivityDataLocal(null,null,null,user.getId());

                                    //ForegroundServiceInitialize();
                                    UpdateStepCounter();

                                }else{

                                }
                            }else if(ContestEndStatus){

                            }else if(!ContestStatus){

                            }
                        }else{

                        }
                    }else{
                    }
                }
                wakeLock.release();
            }catch (Exception e){
                mFirebaseAnalytics = FirebaseAnalytics.getInstance(c);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(e.getStackTrace()));
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                SaveSharedPreference.setStepCounterPermission(c,false);
                stopForeground(true);
                stopSelf();
            }
        }
    };

    private final GCoreWakefulBroadcastReceiver m_timeChangedReceiver = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try{
                final String action = intent.getAction();
                if (action.equals(Intent.ACTION_DATE_CHANGED) /*||
                    action.equals(Intent.ACTION_TIMEZONE_CHANGED)*/) {
                    //doWorkSon();
                    /*SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                    String NextDayDateAndTime = sdf.format(new Date());
                    String TodayDateAndTime = SaveSharedPreference.getUserLogInDate(context);
                    if(!TodayDateAndTime.equals(NextDayDateAndTime)){

                    }*/
                    PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
                    PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
                    wakeLock.acquire(60 * 1000L);


                    Realm_User user= provideUserLocal(c);

                    if(user!=null){
                        Competition CompitionDate = Repository.getCompitionDate();
                        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                        String currentDateandTime = sdf.format(new Date());


                        if(CompitionDate!=null){
                            if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                                Boolean ContestStatus = getCompitionStartDate(c,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                                Boolean ContestEndStatus = getCompitionStartDate(c,convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                                if(ContestStatus && !ContestEndStatus){

                                    if(SaveSharedPreference.getLoggedInFirstTime(c)){

                                        ActivityDaily activityDaily = new ActivityDaily();
                                        activityDaily.setmSteps(0);
                                        activityDaily.setmStars(0);
                                        activityDaily.setmCalories(0);
                                        activityDaily.setmDistance(0);
                                        activityDaily.setmDate(convertTimeToUTC());
                                        activityDaily.setSyncedWithServer(false);
                                        Repository.AddStepCounterLocalV3(activityDaily);

                                        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
                                        String currentDateandTime1 = sdf1.format(new Date());
                                        SaveSharedPreference.setGoogleFitTodayStepsSteps(c,0);
                                        SaveSharedPreference.setSensorSteps(c,countSteps1);
                                        int StaticSensorSteps = SaveSharedPreference.getSensorStaticSteps(c);
                                        if(countSteps1==0){
                                            SaveSharedPreference.setSensorStaticSteps(c,countSteps1);
                                        }
                                        if(countSteps1>StaticSensorSteps){
                                            SaveSharedPreference.setSensorStaticSteps(c,countSteps1);
                                        }
                                        SaveSharedPreference.setPostActivityNotificationStatus(context,false);
                                        SaveSharedPreference.setUserLogInSteps(c,0);
                                        SaveSharedPreference.setUsertodaySteps(c,0);
                                        SaveSharedPreference.setUserStarCount(c,0);
                                        SaveSharedPreference.setWaistToday(c,"0");
                                        SaveSharedPreference.setWeightToday(c,"0");
                                        SaveSharedPreference.setDailyDate(c,"");
                                        SaveSharedPreference.setDailyImage(c,"");
                                        SaveSharedPreference.setLocation(c,"");
                                        updateStarUserLocal("0",user.getId());
                                        updateStepsUserLocal("0",user.getId());
                                        updateActivityDataLocal(null,null,null,user.getId());
                                        UpdateStepCounter();

                                    }else{

                                    }

                                }else if(ContestEndStatus){


                                }else if(!ContestStatus){


                                }
                            }else{


                            }
                        }else{


                        }
                    }
                    wakeLock.release();
                }
            }catch (Exception e){
                mFirebaseAnalytics = FirebaseAnalytics.getInstance(c);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(e.getStackTrace()));
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                SaveSharedPreference.setStepCounterPermission(c,false);
                stopForeground(true);
                stopSelf();
            }
        }
    };

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
    // ___________________________________________________________________________ \\


    // --------------------------------------------------------------------------- \\


    public void ForegroundServiceInitialize(){


        startForeground(mNotificationId, getNotification());

        SaveSharedPreference.setStepsForeGroundService(c,true);


    }


    public Notification  getNotification(){


        Intent notificationIntent = new Intent(StepCountingService.this, SplashActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("From","service");
        notificationIntent.putExtras(bundle);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pIntent = PendingIntent.getActivity(StepCountingService.this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification=null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //Creating Channel
            nc.createMainNotificationChannel(this);

             notification = new Notification.Builder(getApplicationContext(), nc.getMainNotificationId())
                    .setContentTitle(getResources().getString(R.string.General_Today_Steps)+" "+SaveSharedPreference.getUserTodaySteps(c)+"")
                    .setContentText(getResources().getString(R.string.General_PedoMeter_session))
                    .setSmallIcon(R.drawable.ic_app_launch)
                    .setContentIntent(pIntent)
                    .setOngoing(true).build();



        } else {
            //for devices less than API Level 26
             notification = new Notification.Builder(getApplicationContext())
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(getResources().getString(R.string.General_Today_Steps)+" "+SaveSharedPreference.getUserTodaySteps(c)+"")
                    .setContentText(getResources().getString(R.string.General_PedoMeter_session))
                    .setSmallIcon(R.drawable.ic_app_launch)
                    .setContentIntent(pIntent)
                    .setOngoing(true).build();
        }


        return notification;

    }


    private void UpdateStepCounter() {


        notificationManager.notify(mNotificationId, getNotification());
    }



    private void dismissNotification() {

        notificationManager.cancel(0);
    }
    // ______________________________________________________________________________________ \\


    // --------------------------------------------------------------------------- \\
    // ___ (4) repeating timer ___ \\
    /*private Runnable updateBroadcastData = new Runnable() {
        public void run() {
            if (!serviceStopped) { // Only allow the repeating timer while service is running (once service is stopped the flag state will change and the code inside the conditional statement here will not execute).
                // Call the method that broadcasts the data to the ActivityDaily..
                broadcastSensorValue();
                // Call "handler.postDelayed" again, after a specified delay.
                handler.postDelayed(this, 5000);
            }
        }
    };*/
    // ___________________________________________________________________________ \\

    // --------------------------------------------------------------------------- \\
    // ___ (5) add  data to intent ___ \\
   /* private void broadcastSensorValue() {
        Log.d(TAG, "Data to ActivityDaily");
        // add step counter to intent.
        intent.putExtra("Counted_Step_Int", newStepCounter);
        intent.putExtra("Counted_Step", String.valueOf(newStepCounter));
        // add step detector to intent.
        intent.putExtra("Detected_Step_Int", currentStepsDetected);
        intent.putExtra("Detected_Step", String.valueOf(currentStepsDetected));
        // call sendBroadcast with that intent  - which sends a message to whoever is registered to receive it.
        sendBroadcast(intent);
    }*/
    // ___________________________________________________________________________ \\



    public void setstepsCountStepListener(StepsCountResult listener)
    {
        stepsCountResult=listener;
    }


    public  interface  StepsCountResult
    {
        void  onStepsService(int steps);
    }

    public void updateStarUserLocal( String starCount,String userID)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        try {
            final Realm_User updateStarLocal= mLocalService.where(Realm_User.class)
                    .equalTo("id",userID)
                    .findFirst();

            if(updateStarLocal != null){

                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Realm_User db_user = updateStarLocal;

                        db_user.setStars_count(starCount);

                        mLocalService.insertOrUpdate(db_user);
                    }
                });
            }




        }catch (Exception ex)
        {
            ex.toString();

        }

    }


    public void updateStepsUserLocal( String StepCount,String userID)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        try {
            final Realm_User updateStarLocal= mLocalService.where(Realm_User.class)
                    .equalTo("id",userID)
                    .findFirst();

            if(updateStarLocal != null){

                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Realm_User db_user = updateStarLocal;

                        db_user.setSteps_count(StepCount);

                        mLocalService.insertOrUpdate(db_user);

                    }
                });
            }
        }catch (Exception ex)
        {
            ex.toString();

        }

    }

    public void updateActivityDataLocal( String activityImage,String activityLocation,String ActivityTime,String userID)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        try {
            final Realm_User updateActivityDataLocal= mLocalService.where(Realm_User.class)
                    .equalTo("id",userID)
                    .findFirst();

            if(updateActivityDataLocal != null){

                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Realm_User db_user = updateActivityDataLocal;

                        db_user.setActivity_image(activityImage);
                        db_user.setActivity_location(activityLocation);
                        db_user.setActivityTime(ActivityTime);

                        mLocalService.insertOrUpdate(db_user);
                    }
                });
            }




        }catch (Exception ex)
        {
            ex.toString();

        }

    }

    /*private void  setUpDB()
    {
        try {
           Realm mRealm = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        }catch (Exception e){

        }

    }*/

    public Realm_User provideUserLocal(Context context)
    {

        try{
            String [] params= SaveSharedPreference.getLoggedParams(context);

            Realm_User user=Repository.provideUserLocal(params[0],params[1]);
            return user;
        }catch (Exception e){
            Log.i("RealmUser",e.toString());
        }


        return  null;
    }
    private void sendNotificationCongratulations(Context context) {

        String title = getResources().getString(R.string.General_Step_Count_Stop_Notification);
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
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "006")
                .setSmallIcon(R.drawable.ic_app_launch)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        notificationManager.notify(006, builder.build());
    }

    public void UpdateStepsFromSensor(int steps){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            String UserLogInDate = SaveSharedPreference.getUserLogInDate(c);
            if(SaveSharedPreference.getLoggedInStepsFirstTime(c)){
                SaveSharedPreference.setLoggedInStepsFirstTime(c,false);
                SaveSharedPreference.setSensorStaticSteps(c,steps);
            }
            if(UserLogInDate.equals(currentDateandTime)){
                int LoginSteps = Math.abs(SaveSharedPreference.getUserLogInSteps(c));
                steps =  LoginSteps + (steps - SaveSharedPreference.getSensorStaticSteps(c));

                if(steps>=0){
                    Intent intent2 = new Intent();
                    intent2.putExtra("Steps",steps);
                    intent2.setAction("com.Rikskampen.CUSTOM_INTENT_STEPS");
                    LocalBroadcastManager.getInstance(c).sendBroadcast(intent2);

                    String DisCountDB=null;
                    int DisValueDB=0;
                    String CalCountDB=null;
                    int CalValueDB=0;
                    if(steps>0){
                        DisCountDB = String.format("%.0f",Math.abs(getDistanceNow(steps,userGlobal.getHeight_in_cm())));
                        DisValueDB = Integer.parseInt(DisCountDB);
                        CalCountDB = String.format("%.0f", Math.abs(CaloriesCalulatorFromSteps(userGlobal.getHeight_in_cm(),userGlobal.getWeight(),steps)));
                        CalValueDB = Integer.parseInt(CalCountDB);
                    }else{
                        DisValueDB =0;
                        CalValueDB =0;
                    }
                    ActivityDaily activityDaily = new ActivityDaily();
                    activityDaily.setmSteps(steps);
                    activityDaily.setmStars(Integer.parseInt(userGlobal.getStars_count()));
                    activityDaily.setmCalories(CalValueDB);
                    activityDaily.setmDistance(DisValueDB);
                    activityDaily.setmDate(convertTimeToUTC());
                    activityDaily.setSyncedWithServer(false);
                    Repository.AddStepCounterLocalV3(activityDaily);

                    SaveSharedPreference.setUsertodaySteps(c,Math.abs(steps));

                    SaveSharedPreference.setDistance(c, getDistanceNow(steps,userGlobal.getHeight_in_cm())+"");

                    UpdateStepCounter();
                }
            }else{

                ActivityDaily activityDaily = new ActivityDaily();
                activityDaily.setmSteps(0);
                activityDaily.setmStars(0);
                activityDaily.setmCalories(0);
                activityDaily.setmDistance(0);
                activityDaily.setmDate(convertTimeToUTC());
                activityDaily.setSyncedWithServer(false);
                Repository.AddStepCounterLocalV3(activityDaily);

                SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
                String currentDateandTime2 = sdf2.format(new Date());
                // SensorSteps = steps;
                int StaticSensorSteps = SaveSharedPreference.getSensorStaticSteps(c);
                if(steps>StaticSensorSteps){
                    SaveSharedPreference.setSensorStaticSteps(c,steps);
                }
                Realm_User user = provideUserLocal(c);
                if(user!=null){
                    SaveSharedPreference.setPostActivityNotificationStatus(c,false);
                    SaveSharedPreference.setUserLogInSteps(c,0);
                    SaveSharedPreference.setUsertodaySteps(c,0);
                    SaveSharedPreference.setUserStarCount(c,0);
                    SaveSharedPreference.setWaistToday(c,"0");
                    SaveSharedPreference.setWeightToday(c,"0");
                    SaveSharedPreference.setDailyDate(c,"");
                    SaveSharedPreference.setDailyImage(c,"");
                    SaveSharedPreference.setLocation(c,"");
                    updateStarUserLocal("0",user.getId());
                    updateStepsUserLocal("0",user.getId());
                    updateActivityDataLocal(null,null,null,user.getId());
                    SaveSharedPreference.setUserLogInDate(c,currentDateandTime2);
                    SaveSharedPreference.setStepDaySessionDate(c,currentDateandTime2);
                    //ForegroundServiceInitialize();
                    UpdateStepCounter();
                }

            }

        }catch (Exception ex){
            Log.i("StepsCountEx",ex.toString());
            //Toast.makeText(c, "StepsCountEx", Toast.LENGTH_SHORT).show();
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(c);
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(ex.getStackTrace()));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            SaveSharedPreference.setStepCounterPermission(c,false);
            stopForeground(true);
            stopSelf();
        }
    }

}
