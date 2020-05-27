package com.kampen.riksSe.leader.activity;


import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.service.autofill.Dataset;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.crashlytics.android.Crashlytics;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kampen.riksSe.BroadCastReciver.DateChangeReceiver;
import com.kampen.riksSe.BroadCastReciver.DeviceRestartStatusReceiver;
import com.kampen.riksSe.BroadCastReciver.DeviceWakeUpMidNightReceiver;
import com.kampen.riksSe.BroadCastReciver.MyBroadcastReceiver;
import com.kampen.riksSe.BroadCastReciver.PostActivityNotificationReceiver;
import com.kampen.riksSe.BroadCastReciver.SetTodayLiveVideoCallNotification;
import com.kampen.riksSe.BroadCastReciver.StepCountingRunningCheckReceiver;
import com.kampen.riksSe.BroadCastReciver.UpdateTodayActivityWithGoogleReceiver;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.UpdateAppVersion.AppUpdate;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.api.remote_api.V2_api_model.SyncTable;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateId;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateTable;
import com.kampen.riksSe.data_manager.AppVersionDB_Handler;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.LeaderBoardFragment;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ProfileFragment;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riksSe.leader.activity.fragments.home.HomeFragment;

//import com.kampen.riksSe.leader.activity.fragments.map.Map.MapFragmentFine;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.NutritionNewUIMealActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;

import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.WorkOutNewUIDyActivity;
import com.kampen.riksSe.leader.activity.fragments.map.Map.MapFragmentNew;
import com.kampen.riksSe.leader.activity.fragments.map.tracking.GPS_Service;
import com.kampen.riksSe.leader.activity.modelV3.Permissions;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.module.DB_User_Module;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;


import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static com.kampen.riksSe.utils.Constants.DATE_TIME_FORMAT;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_VALUE;
import static com.kampen.riksSe.utils.Constants.getAndroidVersion;
import static com.kampen.riksSe.utils.UtilityTz.CaloriesCalulatorFromSteps;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.convertDynamicTimeToTimeMili;
import static com.kampen.riksSe.utils.UtilityTz.convertDynamicTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertDynamicTimeToUTCtoTimeMili;
import static com.kampen.riksSe.utils.UtilityTz.convertStaticTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeDateTimeFormat;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeFormat;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;
import static com.kampen.riksSe.utils.UtilityTz.getDateAndTimeFromMiliSeconds;
import static com.kampen.riksSe.utils.UtilityTz.getDateWeekBefore;
import static com.kampen.riksSe.utils.UtilityTz.getDistanceNow;
import static com.kampen.riksSe.utils.UtilityTz.getYesterdayDateString;


public class MainLeaderActivity extends AppCompatActivity implements  MainActivityContract.View, MainActivityContract.OnTransactionCallback {


    private Fragment[] mFragments=new Fragment[4];
    private ViewPager mViewPager;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private   View mChatHeadView;

    WindowManager mWindowManager;


    private final static int CLICK_ACTION_THRESHOLD = 25;

    String NotificationString;
    int weekID;

    public  static Context context;

    String type;

    private boolean flag= false; //global variable

     private  MainActivityPresenter   mainActivityPresenter;

    SwipeRefreshLayout pullToRefresh;

    int requestCode=0;

    boolean lock = false;
    private Realm_User mUser;
    String version;
    AppUpdateManager mAppUpdateManager;

    private FitnessOptions fitnessOptions;
    OnDataPointListener mListener;

    private FirebaseAnalytics mFirebaseAnalytics;

    int RC_APP_UPDATE = 001;

    CountDownTimer cdt;
    static IntentFilter  s_intentFilter;
    static {
        s_intentFilter = new IntentFilter();
        /*s_intentFilter.addAction(Intent.ACTION_TIME_TICK);*/
      /*s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);*/
        s_intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
    }

 /*   static IntentFilter  s_intentFilter1;
    static {
        s_intentFilter1 = new IntentFilter();
        *//*s_intentFilter.addAction(Intent.ACTION_TIME_TICK);*//*
      *//*s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);*//*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            s_intentFilter1.addAction(Intent.ACTION_LOCKED_BOOT_COMPLETED);
        }
        s_intentFilter1.addAction(Intent.ACTION_BOOT_COMPLETED);
    }*/

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            if(lock){
                return true;
            }
            lock = true;

            try{
                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        try {
                        if (mFragments[0] == null) {
                            mFragments[0] = HomeFragment.newInstance();
                        }
                        addFragment(mFragments[0]);
                            //Toast.makeText(MainLeaderActivity.this, "Home Click", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setAction("com.rikskampen.CUSTOM_INTENT_HOME_CLICK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent);
                            Intent intent1 = new Intent();
                            intent1.setAction("com.tutorialspoint.CUSTOM_LEADERBOARD_STAET_CHECK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent1);
                            break;
                } catch (Exception ex) {
                    ex.toString();
                    Log.i("EX Tab",ex.toString());
                }

                    case R.id.navigation_map:


                        try {
                            if (mFragments[1] == null) {
                                mFragments[1] = MapFragmentNew.newInstance();
                            }
                            addFragment(mFragments[1]);
                            Intent intent = new Intent();
                            intent.setAction("com.rikskampen.CUSTOM_INTENT_ViDEO_OFF_CLICK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent);
                            Intent intent1 = new Intent();
                            intent1.setAction("com.tutorialspoint.CUSTOM_LEADERBOARD_STAET_CHECK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent1);
                            break;
                        } catch (Exception ex) {
                            ex.toString();
                            Log.i("EX Ta",ex.toString());
                        }

/*                case R.id.navigation_order_history:
                    if(mFragments[2]==null) {
                        mFragments[2]= OrderHistoryFragment.newInstance();
                    }
                    addFragment(mFragments[2]);
                    return  true;*/

                    case R.id.navigation_profile:

                        try {
                        if (mFragments[2] == null) {
                            mFragments[2] = ProfileFragment.newInstance();
                        }
                        addFragment(mFragments[2]);
                            Intent intent = new Intent();
                            intent.setAction("com.rikskampen.CUSTOM_INTENT_ViDEO_OFF_CLICK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent);
                            Intent intent1 = new Intent();
                            intent1.setAction("com.tutorialspoint.CUSTOM_LEADERBOARD_STAET_CHECK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent1);

                            break;
                } catch (Exception ex) {
                    ex.toString();
                    Log.i("EX Ta",ex.toString());
                }

                    case R.id.navigation_me:

                        try {

                        if (mFragments[3] == null) {
                            mFragments[3] = LeaderBoardFragment.newInstance();
                        }

                        addFragment(mFragments[3]);
                            Intent intent = new Intent();
                            intent.setAction("com.rikskampen.CUSTOM_INTENT_ViDEO_OFF_CLICK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent);
                            Intent intent1 = new Intent();
                            intent1.setAction("com.tutorialspoint.CUSTOM_LEADERBOARD_REFRESH_CLICK");
                            LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(intent1);

                        break;
                } catch (Exception ex) {
                    ex.toString();
                    Log.i("EX Ta",ex.toString());
                }


                }


                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        lock = false;
                    }
                }, 500);


                return true;
        }
        catch(Exception ex){
            Log.i("EX Ta",ex.toString());
        }
                return false;


            }


    };


    public void getAllDataCallFromFragment()
    {
        if(mainActivityPresenter!=null)
        {

            //mainActivityPresenter.getAllDataFromServer(context);
            mainActivityPresenter.SyncAllDataFromServer(context);
            mainActivityPresenter.getTodayQuestions(context);

        }
    }

    protected void displayFragment(Fragment selectedFragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fagmentToReplace=getSupportFragmentManager().findFragmentByTag(selectedFragment.getClass().getName());
        if (selectedFragment.isAdded()) { // if the fragment is already in container
            ft.show(selectedFragment);
        } else { // fragment needs to be added to frame container
            //ft.add(R.id.flContainer, fragmentA, "A");
            ft.add(R.id.frame_layout, selectedFragment, selectedFragment.getClass().getName());
        }
        // Hide fragment B
        if (fagmentToReplace.isAdded()) {
            ft.hide(fagmentToReplace);
        }
        // Hide fragment C
        //if (fragmentC.isAdded()) { ft.hide(fragmentC); }
        // Commit changes
        ft.commit();
    }

    private  void  addFragment( Fragment selectedFragment)
    {

        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment fagmentToReplace=getSupportFragmentManager().findFragmentByTag(selectedFragment.getClass().getName());*/

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      /*  if (fragmentA.isAdded()) { // if the fragment is already in container
            ft.show(fragmentA);
        } else { // fragment needs to be added to frame container
            transaction.add(R.id.frame_layout, selectedFragment, selectedFragment.getClass().getName());
        }
        // Hide fragment B
        if (fragmentB.isAdded()) { ft.hide(fragmentB); }
        // Hide fragment C
        if (fragmentC.isAdded()) { ft.hide(fragmentC); }
        // Commit changes
        ft.commit()*/;

        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            Fragment fagmentToReplace=getSupportFragmentManager().findFragmentByTag(selectedFragment.getClass().getName());

            if (fagmentToReplace != null) {
                if(fagmentToReplace.isAdded())
                {
                    transaction.replace(R.id.frame_layout, fagmentToReplace, selectedFragment.getClass().getName());
                    //transaction.hide(fagmentToReplace);
                }
            } else {
                transaction.add(R.id.frame_layout, selectedFragment, selectedFragment.getClass().getName());
                //transaction.show(selectedFragment);
            }
            transaction.commit();
        }catch(Exception ex){
            Log.i("EX Tab Frag",ex.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_leader);
        //onNewIntent(getIntent());
        context=MainLeaderActivity.this;
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)// Enables Crashlytics debugger
                .build();
        Fabric.with(fabric);

        ComponentName receiver = new ComponentName(context, DeviceRestartStatusReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        /*final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)// Enables Crashlytics debugger
                .build();
        Fabric.with(fabric);*/

       /* Button crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));*/

        /*Intent s_intentFilter = new Intent();
        s_intentFilter.setAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.setAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.setAction(Intent.ACTION_TIME_CHANGED);
        s_intentFilter.setAction("com.tutorialspoint.CUSTOM_DATE_CHANGE");
        LocalBroadcastManager.getInstance(MainLeaderActivity.this).sendBroadcast(s_intentFilter);*/

        MyApplication myApplication = new MyApplication();
        Tracker mTracker = myApplication.getDefaultTracker();
        Log.i("Analysis Android", "Setting screen name: " + "MainLeaderActivity");
        mTracker.setScreenName("Image~" + "MainLeaderActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);




        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mainActivityPresenter=new MainActivityPresenter(MainLeaderActivity.this);

        NotificationString = getIntent().getStringExtra("From");




        if (SaveSharedPreference.getGoogleApi(context)) {
            if (!Constants.isMyServiceRunning(StepCountingService.class, context)) {
                if(SaveSharedPreference.getGoogleFitStatus(context)){
                    fitnessOptions = FitnessOptions.builder()
                            .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                            .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                            .build();

                    fitSignIn();
                    SaveSharedPreference.setStepsForeGroundServiceDestroyStatus(context,true);
                    SaveSharedPreference.setGoogleApi(context,false);
                }
            }
        }

        /*if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 5);
            }
        }*/


        /*if(SaveSharedPreference.getStepsForeGroundServiceDestroyStatus(context)){
            if (!Constants.isMyServiceRunning(StepCountingService.class, context)) {
                SaveSharedPreference.setSensorStaticSteps(context,0);
            }
        }*/


        weekID =getIntent().getIntExtra("selected_week",1);

        pullToRefresh = findViewById(R.id.pullToRefresh);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                SaveSharedPreference.setLocationPermissionForeground(context,false);
            }else{
                SaveSharedPreference.setLocationPermissionForeground(context,true);
            }
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                SaveSharedPreference.setCameraPermission(context,false);
            }else{
                SaveSharedPreference.setCameraPermission(context,true);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)  {
                    SaveSharedPreference.setLocationPermissionBackground(context,false);
                }else{
                    SaveSharedPreference.setLocationPermissionBackground(context,true);
                }
            }
        }

        try{



            SaveSharedPreference.setLoggedInSplash(MainLeaderActivity.this,false);

            String FCMToken = SaveSharedPreference.getUserFcmToken(context);

            Boolean tokenUpdate = SaveSharedPreference.getLoggedInFCM(context);
            List<ActivityDaily> pastActivities = mainActivityPresenter.GetPastActivities(context);
            if(pastActivities!=null){
                mainActivityPresenter.SyncPastActivities(context,pastActivities);
            }else{

            }
            if(tokenUpdate){
                mainActivityPresenter.getAllQuestions(context);
                mainActivityPresenter.uploadFcmToken(context,FCMToken);

                //mainActivityPresenter.getBadgeCountToken(context,FCMToken);
            }
            mainActivityPresenter.getTodayQuestions(context);
        }catch (Exception e){

        }



        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
            Log.i("AppVersion",version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        try{
            String AppVersion = SaveSharedPreference.getAppVersion(context);

            if(!version.equals(AppVersion)){
                SaveSharedPreference.setApiSyncedDate(context,convertStaticTimeToUTC());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Stuff that updates the UI
                        //ProgressManager.showProgress(context, "Vänta...");
                        //mainActivityPresenter.getAllDataFromServer(context);
                        mainActivityPresenter.SyncAllDataFromServer(context);

                    }
                });
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Stuff that updates the UI
                        //ProgressManager.showProgress(context, "Vänta...");
                        //mainActivityPresenter.getAllDataFromServer(context);
                        mainActivityPresenter.SyncAllDataFromServer(context);
                    }
                });
            }
        }catch(Exception e){

        }


        try{
            CounterContest(true);
            if(mainActivityPresenter!=null){
                mUser=mainActivityPresenter.provideUserLocal(context);
                if(mUser!=null){
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
                    String currentDateandTime = sdf.format(new Date());
                    Competition CompitionDate = Repository.getCompitionDate();
                    if(CompitionDate!=null) {
                        if (CompitionDate.getStartDate() != null) {
                            Boolean ContestStatus = getCompitionStartDate(context,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                            if(ContestStatus) {
                                startAlarm();
                                startAlarmWakeUIAtMidNight();
                                startAlarmDateChange();
                                //startAlarmUpdateActivityWithGoogle();
                                startRepatingAlarm();
                                startAlarmPostActivityChange();
                                startAlarmTodayLiveVideoCall();
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            Log.i("Alarm Error",e.toString());
        }

        mainActivityPresenter.getUpdateAppVersion();


        try{
            String version = null;
            try {

                PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
                version = pInfo.versionName;
                Log.i("AppVersion",version);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            mainActivityPresenter.UpdateUserPermissionAndVersion(context,mUser,version);
        }catch (Exception e){

        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        try {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        } catch (Exception ex) {
            ex.toString();
        }

            Intent   navigationContext  =   getIntent();

            String  navigationFrom=navigationContext.getStringExtra(NAVIGATING_FROM_TAG);

            //navigationFrom=NAVIGATING_FROM_VALUE;


            if(navigationFrom!=null && navigationFrom.equals(NAVIGATING_FROM_VALUE))
            {
                /*try {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    mFragments[1] = MapFragmentNew.newInstance();
                    transaction.replace(R.id.frame_layout, mFragments[1]);
                    transaction.commit();
                } catch (Exception ex) {
                    ex.toString();
                }*/

                navigation.setSelectedItemId(R.id.navigation_map);

            }

            else {
                try {
                    Bundle b = new Bundle();
                    b.putString("W_Plans", "abc");
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    mFragments[0] = HomeFragment.newInstance();
                    mFragments[0].setArguments(b);
                    transaction.add(R.id.frame_layout, mFragments[0],HomeFragment.class.getName());
                    transaction.commit();
                } catch (Exception ex) {
                    ex.toString();
                    Log.i("FragmentEX",ex.toString());
                }
            }
    }



    InstallStateUpdatedListener installStateUpdatedListener = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED){
                        popupSnackbarForCompleteUpdate(context);
                    } else if (state.installStatus() == InstallStatus.INSTALLED){
                        if (mAppUpdateManager != null){
                            mAppUpdateManager.unregisterListener(installStateUpdatedListener);
                        }

                    } else {
                        //Log.i(TAG, "InstallStateUpdatedListener: state: " + state.installStatus());
                    }
                }
            };

    private void popupSnackbarForCompleteUpdate(Context context) {

        View rootView=((AppCompatActivity)context).findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, "New app is ready!", Snackbar.LENGTH_INDEFINITE);
        //MyApplication.showSimpleSnackBarSucess(context,"New app is ready!");

        snackbar.setAction("Install", view -> {
            if (mAppUpdateManager != null){
                mAppUpdateManager.completeUpdate();
            }
        });


        snackbar.setActionTextColor(getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        NotificationString = intent.getStringExtra("From");

        weekID =intent.getIntExtra("selected_week",1);

        //ProgressManager.showProgress(MainLeaderActivity.this, "Vänta ...");

        getAllDataCallFromFragment();

    }



    @Override
    protected void onStart() {
        super.onStart();


        LocalBroadcastManager.getInstance(context).registerReceiver(mBroadcastReceiverNotificationRefresh,
                new IntentFilter("com.rikskampen.CUSTOM_INTENT_NOTIFICATION"));
        //Toast.makeText(context, "Main ActivityDaily", Toast.LENGTH_SHORT).show();
        LocalBroadcastManager.getInstance(context).registerReceiver(NutritionRefreshNotificationForeground,
                new IntentFilter("com.rikskampen.CUSTOM_INTENT_NUTRITION_REFRESH_NOTIFICATION"));

        LocalBroadcastManager.getInstance(context).registerReceiver(DateChangeReceiver,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_ACTIVITY_DATE_CHANGE"));

        registerReceiver(m_timeChangedReceiver, s_intentFilter);
    }

    private final BroadcastReceiver NutritionRefreshNotificationForeground = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here




        }
    };

    private final BroadcastReceiver m_timeChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

           if (action.equals(Intent.ACTION_DATE_CHANGED) /*||
                    action.equals(Intent.ACTION_TIMEZONE_CHANGED)*/) {
                //doWorkSon();
                mainActivityPresenter.getTodayQuestions(context);
            }
        }
    };

    private final BroadcastReceiver BeforeRestart = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(Intent.ACTION_BOOT_COMPLETED) /*||
                    action.equals(Intent.ACTION_TIMEZONE_CHANGED)*/) {
                //doWorkSon();
                Toast.makeText(context, "Restsart Works", Toast.LENGTH_LONG).show();
            }
            if(action.equals(Intent.ACTION_LOCKED_BOOT_COMPLETED)){
                Toast.makeText(context, "Locked Restsart Works", Toast.LENGTH_LONG).show();
            }
        }
    };
    private final BroadcastReceiver DateChangeEvent = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here
            String DateStatus = intent.getStringExtra("DateStatus");
            if(DateStatus.equals("True")){

                 //SaveSharedPreference.setUserLogInDate(mContext,currentDateandTime);
                //StepsResetDAteChanged();

            }
        }
    };

    private final GCoreWakefulBroadcastReceiver DateChangeReceiver = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

           // Toast.makeText(context, "DateChanged BraodCast Lives UI", Toast.LENGTH_LONG).show();

            //updateNotify();
            //updateNotifyDiary();
            //mainActivityPresenter.getTodayQuestions(context);

        }
    };

    public void cancelAlarmIfExists(Context mContext){
        try {
            Intent myIntent = new Intent(mContext,MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 1, myIntent,FLAG_UPDATE_CURRENT);
            AlarmManager am=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
            pendingIntent.cancel();
            am.cancel(pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startAlarm() {

        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

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

    public void startAlarmWakeUIAtMidNight() {


        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

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

    public void startAlarmDateChange() {


        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

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

    public void startAlarmUpdateActivityWithGoogle() {

        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 57);
        c.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

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


    public void startRepatingAlarm() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            String[] hours = currentDateandTime.split(":");

            int hoursInt = Integer.parseInt(hours[0]);
            int miniuteInt = Integer.parseInt(String.valueOf(hours[1]));
            int secondsInt = Integer.parseInt(String.valueOf(hours[2]));

            Calendar c = Calendar.getInstance();

            hoursInt+=2;
            c.set(Calendar.HOUR_OF_DAY, hoursInt);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(context, StepCountingRunningCheckReceiver.class);
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

        }catch (Exception e){

        }
    }



    public void startAlarmPostActivityChange() {


        Calendar c = Calendar.getInstance();

        c.set(Calendar.HOUR_OF_DAY, 20);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, PostActivityNotificationReceiver.class);
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

    public void startAlarmTodayLiveVideoCall(){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context, SetTodayLiveVideoCallNotification.class);
        myIntent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
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

    @Override
    protected void onPostResume() {
        super.onPostResume();

        /*type = getIntent().getStringExtra("From");
        if(type.equals("Chat")){
            ProgressManager.hideProgress();
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(intent);
        }*/
        //Toast.makeText(MainLeaderActivity.this, "Notification Now on PostResume::".concat(type), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        try {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_layout);
        if(f instanceof HomeFragment)
        {
            if(mFragments[0]!=null)
            {
                mFragments[0].onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
        else if(f instanceof MapFragmentNew)
        {
            if(mFragments[1]!=null)
            {
                mFragments[1].onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

        }catch (Exception ex)
        {
            ex.toString();
        }


    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == RC_APP_UPDATE) {
                if (resultCode != RESULT_OK) {
                    AppUpdate appUpdate = mainActivityPresenter.getAllPlanData();
                    if(appUpdate!=null){
                        if(appUpdate.getIsForceUpdate()){
                            UpdateAppForceFromGoogle();
                            Toast.makeText(context, "App Update is Importamt to use the app", Toast.LENGTH_SHORT).show();
                        }else{
                            MyApplication.showSimpleSnackBar(context,"App Update Failed Please Update the App for Accurate Performance");
                        }
                    }
                }
            }
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

            if(f instanceof HomeFragment){
                mFragments[0].onActivityResult(requestCode, resultCode, data);
            }
            else if(f instanceof MapFragmentNew)
            {
             mFragments[1].onActivityResult(requestCode, resultCode, data);
            }
        }catch (Exception ex)
        {
            ex.toString();
            //Log.i("EX Ta",ex.toString());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cdt!=null){
            cdt.cancel();
        }

        try {

        if (mChatHeadView != null) mWindowManager.removeView(mChatHeadView);

        mFragments[0]=null;
        mFragments[1]=null;
        mFragments[2]=null;
        mFragments[3]=null;
        mFragments=null;

            SaveSharedPreference.setGoogleApi(context,true);
    }catch (Exception ex)
    {
        ex.toString();
    }

    }


    @Override
    protected void onStop() {
        super.onStop();


        if (mChatHeadView != null)
        {
            mChatHeadView.setVisibility(View.GONE);
        }

        LocalBroadcastManager.getInstance(context).unregisterReceiver(mBroadcastReceiverNotificationRefresh);
        LocalBroadcastManager.getInstance(context).unregisterReceiver(NutritionRefreshNotificationForeground);
    }

    private final BroadcastReceiver mBroadcastReceiverNotificationRefresh = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            try{

                //int Notification = intent.getIntExtra("ChatBadge",0);

                //mainActivityPresenter.getAllDataFromServer(context);

            }catch (Exception e){

            }

        }
    };

    @Override
    protected void onPause() {
        super.onPause();




    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mChatHeadView != null)
        {
            mChatHeadView.setVisibility(View.VISIBLE);
        }


       // Toast.makeText(MainLeaderActivity.this, "Notification Now on Resume", Toast.LENGTH_LONG).show();




    }

    public  void   manageChatHead()
    {




        mChatHeadView = LayoutInflater.from(this).inflate(R.layout.layout_live_video, null);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        try {
            mWindowManager.addView(mChatHeadView, params);
        }catch (Exception ex){
            ex.toString();
        }


        final ImageView chatHeadImage = (ImageView) mChatHeadView.findViewById(R.id.liveVideo);
        chatHeadImage.setOnTouchListener(new View.OnTouchListener() {
            private int lastAction;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();

                        lastAction = event.getAction();
                        return true;
                    case MotionEvent.ACTION_UP:
                        //As we implemented on touch listener with ACTION_MOVE,
                        //we have to check if the previous action was ACTION_DOWN
                        //to identify if the user clicked the view or not.
                        if (isAClick(initialTouchX, event.getRawX(), initialTouchY,event.getRawY()))  {
                            //Open the chat conversation click.

                            Toast.makeText(MainLeaderActivity.this, "Live View Clicked", Toast.LENGTH_SHORT).show();

                        }
                        lastAction = event.getAction();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mChatHeadView, params);
                        lastAction = event.getAction();
                        return true;
                }
                return false;
            }
        });


    }



    public void exportDatabase() {


        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(getPackageName() + ".realm")
                .schemaVersion(2)
                .modules(new DB_User_Module())
                .build();
        // init realm
        Realm realm = Realm.getInstance(config);

        File exportRealmFile = null;
        // get or create an "export.realm" file
        exportRealmFile = new File(getExternalCacheDir(), "export.realm");

        // if "export.realm" already exists, delete
        exportRealmFile.delete();

        // copy current realm to "export.realm"
        realm.writeCopyTo(exportRealmFile);

        realm.close();



        // init email intent and add export.realm as attachment
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "kh.m.umerjavaid@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Ream data base");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        Uri u = Uri.fromFile(exportRealmFile);
        intent.putExtra(Intent.EXTRA_STREAM, u);

        // start email intent
        startActivity(Intent.createChooser(intent, "YOUR CHOOSER TITLE"));
    }


    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
    }

    @Override
    public void setAllData(String message) {

        if(mFragments[0]!=null)
        {

            HomeFragment homeFragment = (HomeFragment) mFragments[0];
            homeFragment.updateNotify();

        }
       if(NotificationString.equals("nutrition")){

      /*      List<N_WeekDB> nutritionData = mainActivityPresenter.getNutritionData(context);
            List<DayNutritionMealOptionsDB> dayNutritionMealOptionsDB = mainActivityPresenter.getNutritionCurrentMealOptions(nutritionData);
            if(dayNutritionMealOptionsDB.size()>0){
                Intent nutrition = new Intent(MainLeaderActivity.this, NutritionNewUIMealActivity.class);
                nutrition.putExtra("MealType", dayNutritionMealOptionsDB.get(0).getMealTypeSV());
                startActivity(nutrition);
                NotificationString="abc";
                HomeFragment homeFragment = (HomeFragment) mFragments[0];
                homeFragment.updateNotify();
                //finish();
            }else{

            }*/

        }

        if(NotificationString.equals("workout")){

      /*      Intent nutrition = new Intent(MainLeaderActivity.this, WorkOutNewUIActivity.class);
            nutrition.putExtra("selected_week", weekID);
            startActivity(nutrition);
            NotificationString="abc";
            //finish();
            HomeFragment homeFragment = (HomeFragment) mFragments[0];
            homeFragment.updateNotify();*/

        }
        if(NotificationString.equals("chat")){

            Intent nutrition = new Intent(MainLeaderActivity.this, ChatActivity.class);
            startActivity(nutrition);
            NotificationString="abc";
            //finish();

        }

        ProgressManager.hideProgress();

    }

    @Override
    public void setFailed(String message) {

        try{
            ProgressManager.hideProgress();
            if(mFragments[0]!=null)
            {
                HomeFragment homeFragment = (HomeFragment) mFragments[0];
                homeFragment.ApiFail();
            }

            if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
                //MyApplication.showSimpleSnackBar(context, "No Internet Connection");
            }
            else if(message.equals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 118 path $.result.Home.Activities.weeklyActivities")){

            }
            else if(message.equals("Unauthorized")){
                SaveSharedPreference.setUsertodaySteps(MainLeaderActivity.this,0);
                SaveSharedPreference.setSensorSteps(MainLeaderActivity.this,0);
                LogOutUnautorizedUser();
            }
            else{
                MyApplication.showSimpleSnackBar(context, getResources().getString(R.string.General_RequestTimedOut));
            }
        }catch (Exception e){

        }

    }



    @Override
    public void setSyncAllDataSucess(String message, String NutritionPDF, TruncateTable truncateTable, TruncateId truncateId) {
        String version = null;
        try {

            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
            Log.i("AppVersion",version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        try{
            if(truncateTable!=null || truncateId!=null){
                SyncTable syncTable =new SyncTable();
                syncTable.setUserId(Integer.valueOf(mUser.getId()));
                syncTable.setTruncateTable(truncateTable);
                syncTable.setTruncateId(truncateId);
                mainActivityPresenter.SyncTable(mUser.getToken(),syncTable);
            }
        }catch (Exception e){

        }

        try{
            SaveSharedPreference.setAppVersion(context,version);
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    CounterContest(true);
                    SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
                    String currentDateandTime = format.format(new Date());
                    Competition CompitionDate = Repository.getCompitionDate();
                /*Competition  CompitionDate = Repository.getCompitionDate(mUser.getContestID());
                SaveSharedPreference.setApiSyncedDate(context,convertTimeToUTC());
                SaveSharedPreference.setNutritionPDF(context,NutritionPDF);*/
                    if(mFragments[0]!=null)
                    {
                        HomeFragment homeFragment = (HomeFragment) mFragments[0];
                        homeFragment.updateNotify();
                        SaveSharedPreference.setApiSyncedDate(context,convertTimeToUTC());

                        if(SaveSharedPreference.getNutritionPDF(context).isEmpty()){
                            SaveSharedPreference.setNutritionPDF(context,NutritionPDF);
                        }
                        if(!SaveSharedPreference.getNutritionPDF(context).equals(NutritionPDF)){
                            File file = new File(context.getExternalFilesDir(null), "Recipe");
                            File downloadedFile = new File(file.getAbsolutePath(), "RecipeBook");
                            if(downloadedFile.exists()){
                                downloadedFile.delete();
                            }
                            SaveSharedPreference.setNutritionPDF(context,NutritionPDF);
                        }

                    }
                    //MyApplication.showSimpleSnackBarSucess(context,"API Syncy Works");


                    if(NotificationString.equals("nutrition")){

                        if(CompitionDate!=null) {
                            if (CompitionDate.getStartDate() != null) {
                                Boolean ContestStatus = getCompitionStartDate(context,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                                if(ContestStatus) {
                                    HomeFragment homeFragment = (HomeFragment) mFragments[0];
                                    homeFragment.updateNotify();
                                    N_Days_V nutritionCurrentDayIngrdiantData = mainActivityPresenter.getNutritionIngrdiantDataV3(context);
                                    Intent nutrition = new Intent(MainLeaderActivity.this, NutritionNewUIMealActivity.class);
                                    nutrition.putExtra("MealTypeID", nutritionCurrentDayIngrdiantData.getmMeals().get(0).getMealId());
                                    startActivity(nutrition);
                                    NotificationString = "abc";
                                }else{
                                    DialogeBoxContestDate();
                                }
                            }else{
                                DialogeBoxContestDate();
                            }
                        }else{
                            DialogeBoxContestDate();
                        }
                    }

                    if(NotificationString.equals("workout")){
                        if(CompitionDate!=null) {
                            if (CompitionDate.getStartDate() != null) {
                                Boolean ContestStatus = getCompitionStartDate(context,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                                if(ContestStatus) {
                                    List<W_Plans> traningDataV3 = mainActivityPresenter.getTrainingWeek(context, convertUTCToLocalTime(CompitionDate.getStartDate()));
                                    int currentWeekPosition = 0;
                                    for (int i = 0; i < traningDataV3.size(); i++) {
                                        if (traningDataV3.get(i).getmWeek() == weekID) {
                                            currentWeekPosition = i;
                                        }
                                    }
                                    Intent nutrition = new Intent(MainLeaderActivity.this, WorkOutNewUIDyActivity.class);
                                    nutrition.putExtra("WeekID", weekID);
                                    nutrition.putExtra("CurrentDayID", traningDataV3.get(currentWeekPosition).getmWDays().get(0).getmDay());
                                    startActivity(nutrition);
                                    NotificationString = "abc";
                                }else{
                                    DialogeBoxContestDate();
                                }
                            }else{
                                DialogeBoxContestDate();
                            }
                        }else{
                            DialogeBoxContestDate();
                        }
                    }

                    if(NotificationString.equals("chat")){

                        Intent nutrition = new Intent(MainLeaderActivity.this, ChatActivity.class);
                        startActivity(nutrition);
                        NotificationString="abc";
                        //finish();
                    }


                  //MyApplication.showSimpleSnackBarSucess(context,"Sync API Sucess");

                }
            });
        }catch (Exception e){
        }
    }

    @Override
    public void setSyncAllDataFailed(String message) {

        try{
            ProgressManager.hideProgress();
            if(mFragments[0]!=null)
            {
                HomeFragment homeFragment = (HomeFragment) mFragments[0];
                homeFragment.ApiFail();
            }
            if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
                MyApplication.showSimpleSnackBar(context, getResources().getString(R.string.General_NoInternetConnection));
            }
            else if(message.equals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 118 path $.result.Home.Activities.weeklyActivities")){

            }
            else if(message.equals("Unauthorized")){

                SaveSharedPreference.setUsertodaySteps(MainLeaderActivity.this,0);
                SaveSharedPreference.setSensorSteps(MainLeaderActivity.this,0);
                LogOutUnautorizedUser();
            }
            else{
                //MyApplication.showSimpleSnackBar(context, "Feed not refreshed...");
            }
        }catch (Exception e){

        }

    }

    @Override
    public void setSyncTableSucess(String message) {
       // MyApplication.showSimpleSnackBarSucess(context,"Sync Table Sucess");
    }

    @Override
    public void setSyncTableFailed(String message) {
       // MyApplication.showSimpleSnackBar(context, "Sync Table Failed");
    }

    @Override
    public void setLeaderBoardAllData(String message) {
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(context, getResources().getString(R.string.General_NoInternetConnection));
        }
        else{
            //MyApplication.showSimpleSnackBarSucess(context, message);

        }


    }

    @Override
    public void setLeaderBoardAllDataFailed(String message) {

        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(context, getResources().getString(R.string.General_NoInternetConnection));
        }
        else{
            MyApplication.showSimpleSnackBar(context, getResources().getString(R.string.General_RequestTimedOut));
        }

    }

    @Override
    public void setFcmScucess(String message) {
        //MyApplication.showSimpleSnackBarSucess(context, "FCM S");

        try{
            SaveSharedPreference.setLoggedInFCM(context,false);
        }catch (Exception e){

        }

    }

    @Override
    public void setFcmFail(String message) {

        //MyApplication.showSimpleSnackBar(context, "FCM Not Updated");
    }

    @Override
    public void setBadgeCountSucess(String message) {

      /*  Intent intent = new Intent();
        intent.putExtra("ChatBadge",badgeCount);
        intent.setAction("com.rikskampen.CUSTOM_INTENT_CHAT_BADGE");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);*/

        SaveSharedPreference.setChatNotificationCounter(this, Integer.parseInt(message));
     /*if(!message.equals("null")){
          SaveSharedPreference.setChatNotificationCounter(this, Integer.parseInt(message));
      }else {
          SaveSharedPreference.setChatNotificationCounter(this, 0);
      }*/
        Intent intent = new Intent();
        intent.putExtra("ChatBadge",Integer.parseInt(message));
        intent.setAction("com.rikskampen.CUSTOM_INTENT_CHAT_BADGE");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public void setBadgeCountFailed(String message) {
        //MyApplication.showSimpleSnackBar(context, "Badge Count failed");
    }

    @Override
    public void setTodayQuestionsSucess(String message) {
        if(mainActivityPresenter!=null){
            mainActivityPresenter.getDailyDiary(context);
        }

        //MyApplication.showSimpleSnackBarSucess(context,"Today QuestionResponse Lives");
    }

    @Override
    public void setTodayQuestionsFailed(String message) {
        //MyApplication.showSimpleSnackBar(context,"Today QuestionResponse Failed");
    }

    @Override
    public void setAllQuestionsSucess(String message) {
        //MyApplication.showSimpleSnackBarSucess(context,"All QuestionResponse Lives");
    }

    @Override
    public void setAllQuestionsFailed(String message) {
        //MyApplication.showSimpleSnackBar(context,"All QuestionResponse Failed");
    }

    @Override
    public void setDailyDiarySucess(String message) {
        //MyApplication.showSimpleSnackBarSucess(context,"Daily Diaries Lives");
        try{
            HomeFragment homeFragment = (HomeFragment) mFragments[0];
            if(homeFragment!=null){
                homeFragment.updateNotifyDiary();
            }
        }catch (Exception e){

        }
    }

    @Override
    public void setDailyDiaryFailed(String message) {
        /*if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){

            MyApplication.showSimpleSnackBar(context, "No Internet Connection");
        }*/
    }

    @Override
    public void setSyncPastActivitiesSucess(String message) {
        /*if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){

            MyApplication.showSimpleSnackBar(context, "No Internet Connection");
        }*/
    }

    @Override
    public void setSyncPastActivitiesFailed(String message) {
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){

            MyApplication.showSimpleSnackBar(context, getResources().getString(R.string.General_NoInternetConnection));
        }else if(message.equals("Unauthorized")){
            SaveSharedPreference.setUsertodaySteps(MainLeaderActivity.this,0);
            SaveSharedPreference.setSensorSteps(MainLeaderActivity.this,0);
            LogOutUnautorizedUser();

        }
    }

    @Override
    public void setUpdateAppsucess(String message) {

        try {
            AppUpdate appUpdate = mainActivityPresenter.getAllPlanData();
            String version = null;
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
            //Log.i("AppVersion",version);

            if(appUpdate!=null){
                if(!appUpdate.getIsForceUpdate()){
                    UpdateAppNormalFromGoogle();
                }else{
                    UpdateAppForceFromGoogle();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void UpdateAppNormalFromGoogle(){
        mAppUpdateManager = AppUpdateManagerFactory.create(this);
        AppUpdateManagerFactory.create(this);
        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){

                try {
                    mAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, AppUpdateType.FLEXIBLE, MainLeaderActivity.this, RC_APP_UPDATE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
                popupSnackbarForCompleteUpdate(context);
            } else {
                //Log.e(TAG, "checkForAppUpdateAvailability: something else");
            }
        });
    }


    public void UpdateAppForceFromGoogle(){
        mAppUpdateManager = AppUpdateManagerFactory.create(this);
        AppUpdateManagerFactory.create(this);
        mAppUpdateManager.registerListener(installStateUpdatedListener);

        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {

            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){

                try {
                    mAppUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, AppUpdateType.IMMEDIATE, MainLeaderActivity.this, RC_APP_UPDATE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }

            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
                popupSnackbarForCompleteUpdate(context);
            } else {
                //Log.e(TAG, "checkForAppUpdateAvailability: something else");
            }
        });
    }

    @Override
    public void setUpdateAppFailed(String message) {

    }

    @Override
    public void setUpdateUserPermissionAndVersionSucess(String message) {

        //MyApplication.showSimpleSnackBarSucess(context,message);
    }

    @Override
    public void setUpdateUserPermissionAndVersionFailed(String message) {

        //MyApplication.showSimpleSnackBar(context,message);
    }



    @Override
    public void setPresenter(MainActivityContract.Presenter mPresenter) {



    }

    private void LogOutUnautorizedUser(){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                mLocalService.deleteAll();

            }
        });
        try{

            if (Constants.isMyServiceRunning(StepCountingService.class, context)) {
                ActivityFragment.newInstance().stopGPSService(context);
            }
            SaveSharedPreference.setApiSyncedDate(context,convertStaticTimeToUTC());
            //MyApplication.showSimpleSnackBar(context, "Your Logged in Some where else");
            Toast.makeText(context, getResources().getString(R.string.LoginModule_Session_Timed_Out), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainLeaderActivity.this, LoginActivity.class);
            startActivity(intent);

            SaveSharedPreference.setLoggedIn(MainLeaderActivity.this, false);

            finish();

        }catch (Exception e){

        }


        /*LayoutInflater li = LayoutInflater.from(MainLeaderActivity.this);

        View promptsView = li.inflate(R.layout.dialog_box_logout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainLeaderActivity.this);

        builder.setView(promptsView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        mLocalService.deleteAll();

                    }
                });
                SaveSharedPreference.setApiSyncedDate(context,convertStaticTimeToUTC());
                MyApplication.showSimpleSnackBar(context, "Your Logged in Some where else");
                Intent intent = new Intent(MainLeaderActivity.this, LoginActivity.class);
                startActivity(intent);

                SaveSharedPreference.setLoggedIn(MainLeaderActivity.this, false);

                finish();


            }
        });

        builder.show();*/

    }
    public void DialogeBoxContestDate(){

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_box_contest_date_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(promptsView);

        final TextView contestStartTime = (TextView) promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        // ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();

        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate.getStartDate()!=null){
            ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);

            contestStartTime.setText(getResources().getString(R.string.Competition_Start_In)+ contestWeekDayTimeModel.getDays()+" "+getResources().getString(R.string.Competition_Time_Ticker_Days)+ contestWeekDayTimeModel.getHours()+" "+getResources().getString(R.string.Competition_Time_Ticker_Hours)+ contestWeekDayTimeModel.getMiniutes()+" "+getResources().getString(R.string.Competition_Time_Ticker_Minutes));

        }else{
            contestStartTime.setText(getResources().getString(R.string.Competition_Start_Soon));
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder

                alertDialog.dismiss();
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }



    public void CounterContest(boolean status){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            long total_millis =0;
            Competition CompitionDate = Repository.getCompitionDate();

            if(CompitionDate.getStartDate()!=null){

                Boolean ContestStatus = getCompitionStartDate(context,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);

                if(ContestStatus){

                    if(cdt!=null){
                        cdt.cancel();
                    }

                }else{
                    ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);

                    if(cdt!=null){
                        total_millis = (contestWeekDayTimeModel.getStratTimeInMilli() - contestWeekDayTimeModel.getEndTimeInMilli());
                        cdt.cancel();
                    }else{
                        total_millis = (contestWeekDayTimeModel.getStratTimeInMilli() - contestWeekDayTimeModel.getEndTimeInMilli());
                    }
                    cdt = new CountDownTimer( total_millis,1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            //You can compute the millisUntilFinished on hours/minutes/seconds
                            //mCompititionCounter.setVisibility(View.VISIBLE);


                            long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                            millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                            long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                            millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                            long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                            millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                            long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                     /* mDayCounter.setText(days+"");
                        mHourCounter.setText(hours+"");
                        mMiniuteCounter.setText(minutes+"");
                        mSecondsCounter.setText(seconds+"");*/

                            Intent intent = new Intent();
                            intent.putExtra("Days",days+"");
                            intent.putExtra("Hours",hours+"");
                            intent.putExtra("Minutes",minutes+"");
                            intent.putExtra("Seconds",seconds+"");
                            intent.setAction("com.tutorialspoint.CUSTOM_COMPITITION_COUNTER");
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

                        }
                        @Override
                        public void onFinish() {
                            cdt.cancel();
                            startAlarm();
                            startAlarmWakeUIAtMidNight();
                            startAlarmDateChange();
                            //startAlarmUpdateActivityWithGoogle();
                            startRepatingAlarm();
                            startAlarmPostActivityChange();
                            startAlarmTodayLiveVideoCall();
                            Intent intent = new Intent();
                            intent.setAction("com.tutorialspoint.CUSTOM_COMPITITION_FINISH");
                            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        }
                    };
                    if(cdt!=null){
                        cdt.start();
                    }
                }
            }else{

            }
        }catch (Exception e){

        }

    }

    public void fitSignIn(){
        if (oAuthPermissionsApproved()) {
            //subscribe();
            ReadStepsData();
            readHistoryData(getDateWeekBefore());
        }else{
            GoogleSignIn.requestPermissions(this,1,getGoogleAccount(), fitnessOptions);
        }
    }



    public boolean oAuthPermissionsApproved(){

        return GoogleSignIn.hasPermissions(getGoogleAccount(), fitnessOptions);
    }

    public GoogleSignInAccount getGoogleAccount(){
        return GoogleSignIn.getAccountForExtension(context, fitnessOptions);
    }
    public void subscribe(){

        Fitness.getRecordingClient(context, getGoogleAccount())
                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("Subscribtion","Sucess");
                        //Toast.makeText(context, "Google Fit Subscribed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Subscribtion","Failed");
                        SaveSharedPreference.setGoogleFitStatus(context,false);
                        Toast.makeText(context, "Google Fit Failed", Toast.LENGTH_SHORT).show();

                    }
                });


    }

    public void ReadStepsData(){
        Fitness.getHistoryClient(context, getGoogleAccount())
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(new OnSuccessListener<DataSet>() {
                    @Override
                    public void onSuccess(DataSet dataSet) {
                        int totalSteps = 0;
                        if(!dataSet.isEmpty()){
                            totalSteps= dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                        }
                        SaveSharedPreference.setGoogleFitTodayStepsSteps(context,totalSteps);
                        //Toast.makeText(context, "Feteching Google Fit Data", Toast.LENGTH_SHORT).show();
                        /*Intent intent = new Intent();
                        intent.setAction("com.Rikskampen.CUSTOM_INTENT_GOOGLE_FIT_STEPS");
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);*/
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("GoogleFitSteps", e.toString());
                        Toast.makeText(context, "Google Fit Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public DataReadRequest queryFitnessData(String StartDateToGetGoogleHistory){
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        String googleFitActivityDate = convertTimeFormat(StartDateToGetGoogleHistory);

        String googleFitActivityTimeStartRange = convertTimeDateTimeFormat(googleFitActivityDate + " 00:00:00");
        //ConvertLocalTime To UTC and Long MiliSeconds
        long StartDate =  convertDynamicTimeToUTCtoTimeMili(googleFitActivityTimeStartRange);
        long EndDate = convertDynamicTimeToUTCtoTimeMili(currentDateandTime);
        //long EndDate = convertDynamicTimeToUTCtoTimeMili(convertTimeDateTimeFormat(getYesterdayDateString()));
        Log.i("StartTimeInString ",googleFitActivityTimeStartRange);
        Log.i("EndTimeInString ",currentDateandTime);
        Log.i("StartTimeInMili ", String.valueOf(StartDate));
        Log.i("EndTimeInMili ", String.valueOf(EndDate));

     /*   long StartDate =  convertDynamicTimeToTimeMili(StartDateToGetGoogleHistory);
        long EndDate = convertDynamicTimeToTimeMili(currentDateandTime);*/

        // Creating Query with Start Date and End Date  to get ACtivity Data
        return new DataReadRequest.Builder()
                // The data request can specify multiple data types to return, effectively
                // combining multiple data queries into one call.
                // In this example, it's very unlikely that the request is for several hundred
                // datapoints each consisting of a few steps and a timestamp.  The more likely
                // scenario is wanting to see how many steps were walked per day, for 7 days.
                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                // Analogous to a "Group By" in SQL, defines how data should be aggregated.
                // bucketByTime allows for a time span, whereas bucketBySession would allow
                // bucketing by "sessions", which would need to be defined in code.
                .bucketByTime(1, TimeUnit.DAYS)
                .setTimeRange(StartDate, EndDate, TimeUnit.MILLISECONDS)
                .build();
    }

    public void readHistoryData(String StartDateToGetGoogleHistory){
        //Sending Query to History API
        DataReadRequest dataReadRequest =queryFitnessData(StartDateToGetGoogleHistory);
        Fitness.getHistoryClient(this, getGoogleAccount())
                .readData(dataReadRequest)
                .addOnSuccessListener(new OnSuccessListener<DataReadResponse>() {
                    @Override
                    public void onSuccess(DataReadResponse dataReadResponse) {
                        //Toast.makeText(context, "History Api Sucess", Toast.LENGTH_SHORT).show();
                        printData(dataReadResponse);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("HistoryAPi", "There was a problem reading the data.", e);
                        Toast.makeText(context, "History Api Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void printData(DataReadResponse dataReadResponse){
// [START parse_read_data_result]
        // If the DataReadRequest object specified aggregated data, dataReadResult will be returned
        // as buckets containing DataSets, instead of just DataSets.

        if(!dataReadResponse.getBuckets().isEmpty()){
            Log.i("HistoryAPI", "Number of returned buckets of DataSets is: " + dataReadResponse.getBuckets().size());
            for(int i=0;i<dataReadResponse.getBuckets().size();i++){
                dumpDataSet(dataReadResponse.getBuckets().get(i).getDataSets());
            }
        }else if(!dataReadResponse.getDataSets().isEmpty()){
            /*for(int i=0;i<dataReadResponse.getDataSets().size();i++){
                dumpDataSet(dataReadResponse.getDataSets());
            }*/
        }
        // [END parse_read_data_result]
    }

    public void dumpDataSet(List<DataSet> dataSet){
        //creating DateFormat for converting time from local timezone to GMT
       List<ActivityDaily> activityDailyList = new ArrayList();
       String GoogleFitActivityDateTime;
        for(int k=0;k<dataSet.size();k++){
            Log.i("HistoryAPI", "Data returned for Data type: "+dataSet.get(k).getDataType().getName());
            for(int i=0;i<dataSet.get(k).getDataPoints().size();i++){
                Log.i("HistoryAPI","Data point: ");
                Log.i("HistoryAPI, ","\tType: "+dataSet.get(k).getDataPoints().get(i).getDataType().getName());
                Log.i("HistoryAPI, ","\tStart: "+getDateAndTimeFromMiliSeconds(dataSet.get(k).getDataPoints().get(i).getStartTime(TimeUnit.MILLISECONDS)));
                Log.i("HistoryAPI, ","\tEnd: "+getDateAndTimeFromMiliSeconds(dataSet.get(k).getDataPoints().get(i).getEndTime(TimeUnit.MILLISECONDS)));
                GoogleFitActivityDateTime =getDateAndTimeFromMiliSeconds(dataSet.get(k).getDataPoints().get(i).getEndTime(TimeUnit.MILLISECONDS));
                for(int j=0;j<dataSet.get(k).getDataPoints().size();j++){
                    Log.i("HistoryAPI", "\tField: "+dataSet.get(k).getDataPoints().get(i).getDataType().getFields());
                    for(int l=0;l<dataSet.get(k).getDataPoints().get(i).getDataType().getFields().size();l++) {
                        Log.i("HistoryAPI", "\tValue: " + dataSet.get(k).getDataPoints().get(i).getValue(dataSet.get(k).getDataPoints().get(i).getDataType().getFields().get(l)));
                        int stepsCount = dataSet.get(k).getDataPoints().get(i).getValue(dataSet.get(k).getDataPoints().get(i).getDataType().getFields().get(l)).asInt();

                        String DisCountDB=null;
                        int DisValueDB=0;
                        String CalCountDB=null;
                        int CalValueDB=0;
                        if(stepsCount>0){
                            DisCountDB = String.format("%.0f",Math.abs(getDistanceNow(stepsCount,mUser.getHeight_in_cm())));
                            DisValueDB = Integer.parseInt(DisCountDB);
                            CalCountDB = String.format("%.0f", Math.abs(CaloriesCalulatorFromSteps(mUser.getHeight_in_cm(),mUser.getWeight(),stepsCount)));
                            CalValueDB = Integer.parseInt(CalCountDB);
                        }else{
                            DisValueDB =0;
                            CalValueDB =0;
                        }
                        ActivityDaily activityDaily = new ActivityDaily();
                        activityDaily.setmSteps(stepsCount);
                        activityDaily.setmStars(Integer.parseInt(mUser.getStars_count()));
                        activityDaily.setmCalories(CalValueDB);
                        activityDaily.setmDistance(DisValueDB);
                        activityDaily.setmDate(convertDynamicTimeToUTC(GoogleFitActivityDateTime));//Change Date Time
                        activityDaily.setSyncedWithServer(false);

                        //activityDailyList.add(activityDaily);

                        Repository.AddStepCounterGoogleFitPastActiviesLocalV3(activityDaily);
                    }
                }
            }
        }
    }
    public void AddGoogleFitActivityToList(){

    }

    @Override
    public void onRealmSuccess() {

    }

    @Override
    public void onRealmError(Throwable e) {

    }
}
