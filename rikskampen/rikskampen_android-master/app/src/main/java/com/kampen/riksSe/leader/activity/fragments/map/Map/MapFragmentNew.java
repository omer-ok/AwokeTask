package com.kampen.riksSe.leader.activity.fragments.map.Map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.ui.IconGenerator;
import com.kampen.riksSe.GetPlacesLib.Place;
import com.kampen.riksSe.GoogleDirectionLib.model.Direction;
import com.kampen.riksSe.GoogleDirectionLib.model.Route;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepsProvider;
import com.kampen.riksSe.leader.activity.fragments.map.CustomSupportMapFragment;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.RouteAndStarDataModel;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.StarPickInfoWindowModel;
import com.kampen.riksSe.leader.activity.fragments.map.tracking.GPS_Service;
import com.kampen.riksSe.leader.activity.fragments.map.tracking.SimpleLocation;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.GPS_Util;
import com.kampen.riksSe.utils.PermissionUtils;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.kampen.riksSe.FCM.Constants.CHANNEL_ID;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_VALUE;
import static com.kampen.riksSe.utils.Constants.getMarkerIcon;
import static com.kampen.riksSe.utils.GPS_Util.createDrawableFromView;
import static com.kampen.riksSe.utils.GPS_Util.getMarkersFromNMeters;
import static com.kampen.riksSe.utils.GPS_Util.getRouteDistance;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.DialogeBoxContestEndDate;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class MapFragmentNew extends Fragment implements  StepsProvider.StepsResult,GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        CustomSupportMapFragment.OnMapFragmentReadyListener, GPS_Service.LocationResultUpdate, MapFragContract._View{

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    boolean isAppHasAccessLocationPermission;
    boolean isAppHasAccessBackGroundLocationPermission;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 2;
    private boolean starChaseClicked = false;
    private boolean isSetZoomLevelRequired=false;
    private SimpleLocation locationProvider;
    boolean isGPSEnable = false;


    private static final Drawable TRANSPARENT_DRAWABLE = new ColorDrawable(Color.TRANSPARENT);
    Polyline userChasingTrackPolyline;


    final int MSG_START_TIMER = 0;
    final int MSG_STOP_TIMER = 1;
    final int MSG_UPDATE_TIMER = 2;

    Stopwatch timer = new Stopwatch();
    final int REFRESH_RATE = 100;


    final static double walkingFactor = 0.57;

    static double CaloriesBurnedPerMile;

    static double strip;

    static double stepCountMile; // step/mile

    static double conversationFactor;

    static double CaloriesBurned;


    int count = 0;

    int totalPickedStar =0;

    private Marker userMarker;
    private Marker HomeLocationMarker;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;

    private int mStarClicked = 1;
    private View mStarContainer;
    private View mValuesContainer;
    public View mPlayBtn;
    private View mPauseBtn;
    public View mStopBtn;
    public View mFinishBtn;
    private View mStarItem1;
    private View mStarItem2;
    private View mStarItem3;
    private View mStarItem4;
    private View mStarItem5;
    private View mHomeBtn;
    private View mEditBtn;
    private View mSaveBtn;
    private View mCancelBtn;
    private ImageView mstar1GrayBtn;
    private ImageView mstar1GoldBtn;
    private ImageView mstar2GrayBtn;
    private ImageView mstar2GoldBtn;
    private ImageView mstar3GrayBtn;
    private ImageView mstar3GoldBtn;
    private ImageView mstar4GrayBtn;
    private ImageView mstar4GoldBtn;
    private ImageView mstar5GrayBtn;
    private ImageView mstar5GoldBtn;
    private ImageView mEditBtnGray;
    private ImageView mEditBtnGold;
    private ImageView mHomeBtnGray;
    private ImageView mHomeBtnGold;
    private TextView mstepView;
    private TextView mcaloriesView;
    private TextView mdistanceView;
    private TextView starValut;

    private Chronometer mstopeWatchView;
    private StepsProvider mStepsProvider;
   // public  long StopWatchTime;
    public  long StopWatchPauseTime;
    GPS_Service mService;
    private RouteAndStarDataModel routeAndStarDataModel;

    public int StepCounter=0;
    public int previouStep=0;
    public boolean startClicked = false;

    //private Realm_User mUser;

    private boolean isStart;

    MapFragPresenter mapFragPresenter;

    private  Marker currentMarker=null;

    Boolean HomeBtnClick = false;

    int HomeDistance=0;

    private View ChatNotification;

    NotificationBadge badge;

    int ChatValueCounter;

   // int userStepsToSubtract;

    AlertDialog alertDialog;

    private IconGenerator mIconGenerator;

    ContestWeekDayTimeModel contestWeekDayTimeModel;

    String currentDateandTime;

    View mCompititionCounterMap;
    TextView mDayCounter,mHourCounter,mMiniuteCounter,mSecondsCounter,Heading;
    Boolean ContestStatus;
    View TimerView;

    public MapFragmentNew() {

    }

    public static MapFragmentNew newInstance() {
        MapFragmentNew fragment = new MapFragmentNew();
        return fragment;
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_TIMER:
                    timer.start(); //start timer
                    mHandler.sendEmptyMessage(MSG_UPDATE_TIMER);
                    break;

                case MSG_UPDATE_TIMER:
                    mstopeWatchView.setText(""+ timer.getElapsedTimeSecs());
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIMER,REFRESH_RATE); //text view is updated every second,
                    break;
                //though the timer is still running
                case MSG_STOP_TIMER:
                    mHandler.removeMessages(MSG_UPDATE_TIMER); // no more updates.
                    timer.stop();//stop timer
                    mstopeWatchView.setText(""+ timer.getElapsedTimeSecs());
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_new, container, false);

        // create SupportMapFragment, and listen for onMapfragmentReady callback
        mMapFragment = CustomSupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.mapFragment, mMapFragment).commit();
        setRetainInstance(true);



        init(view);

        if (GPS_Util.checkIfGPSEnabledOrNot(getContext())) {

            isGPSEnable = true;

            if (Constants.isMyServiceRunning(GPS_Service.class, getContext())) {


                getActivity().bindService(new Intent(getContext(), GPS_Service.class), mConnection, Context.BIND_AUTO_CREATE);

                starChaseClicked=true;

            }


        } else {

            isGPSEnable = false;
        }


        return view;


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        View mStartBtn = view.findViewById(R.id.PlayChaseing);
        View mPauseBtn = view.findViewById(R.id.PauseChaseing);
        View mStopBtn = view.findViewById(R.id.StopChaseing);
        View mFinishBtn = view.findViewById(R.id.FinishChaseing);
        View mSaveBtn = view.findViewById(R.id.SaveHome);
        View mCancelBtn = view.findViewById(R.id.CancelChaseing);
        badge = view.findViewById(R.id.chatNotify);
        ChatNotification=view.findViewById(R.id.chatLayout);
        //chatNotifyValue=(TextView) view.findViewById(R.id.chatNotify);
        mCompititionCounterMap = view.findViewById(R.id.compititionCounterMap);
        mDayCounter = view.findViewById(R.id.dayCounterMap);
        mHourCounter = view.findViewById(R.id.hourCounterMap);
        mMiniuteCounter = view.findViewById(R.id.miniuteCounterMap);
        mSecondsCounter = view.findViewById(R.id.secondsCounterMap);
        Heading = view.findViewById(R.id.hedaing);
        TimerView = view.findViewById(R.id.timerView);
        mapFragPresenter = new MapFragPresenter(MapFragmentNew.this);
        mapFragPresenter.setStarsHomeEditButtonClicks(mStarItem1,mStarItem2,mStarItem3,mStarItem4,mStarItem5,mHomeBtn,mEditBtn);
        mapFragPresenter.setStartPauseStopChaseButtonClick(mStartBtn,mPauseBtn,mFinishBtn,mSaveBtn,mStopBtn,mCancelBtn);

        if(Constants.isMyServiceRunning(GPS_Service.class, getContext())){
            startClicked=true;
        }else{
            startClicked=false;
        }

        if(startClicked){
            //SaveSharedPreference.setStepsToSubstract(getContext(),userStepsToSubtract);
            //SaveSharedPreference.setStepsToSubstract(getContext(),0);
            long StopWatchTime = SaveSharedPreference.getStopWatchTime(getContext());
            StopWatchPauseTime = SaveSharedPreference.getUserPauseTime(getContext());
            StopWatchTime = StopWatchTime - (StopWatchPauseTime - SystemClock.elapsedRealtime());
            mstopeWatchView.setBase(StopWatchTime);
            mstopeWatchView.start();
        }


        badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));


        ChatNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    Intent intent =new Intent(getContext(), ChatActivity.class);

                    startActivity(intent);
                    badge.setNumber(0);

                }catch (Exception e){

                }

            }
        });

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();

            if(CompitionDate!=null){
                if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){

                    ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                    Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                    if(ContestStatus && !ContestEndStatus){
                        mCompititionCounterMap.setVisibility(View.GONE);
                    }else if(ContestEndStatus){
                        mCompititionCounterMap.setVisibility(View.GONE);
                    }else if(!ContestStatus){
                        mCompititionCounterMap.setVisibility(View.VISIBLE);
                    }
                }else{
               /* mCompititionCounterMap.setVisibility(View.VISIBLE);
                TimerView.setVisibility(View.GONE);
                Heading.setText("Tävlingsstartdatum tilldelas inte");*/
                }

                if(CompitionDate.getmCompetitionStatus()!=null){
                    if(CompitionDate.getmCompetitionStatus()){
                        ChatNotification.setVisibility(View.VISIBLE);
                    }else{
                        ChatNotification.setVisibility(View.GONE);
                    }
                }
            }
        }catch (Exception ex){

        }


/*
        ProgressManager.showProgress(getContext(),"Map is Loading...");

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                ProgressManager.hideProgress();
            }
        }, 8000);

*/

    }
    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiverBadges,
                new IntentFilter("com.rikskampen.CUSTOM_INTENT_CHAT_BADGE"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(CountDownCompitition,
                new IntentFilter("com.tutorialspoint.CUSTOM_COMPITITION_COUNTER"));
    }

    @Override
    public void onStop() {

        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiverBadges);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(CountDownCompitition);

    }
    private final BroadcastReceiver CountDownCompitition = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            try{
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String days = intent.getStringExtra("Days");
                        String hours = intent.getStringExtra("Hours");
                        String minutes = intent.getStringExtra("Minutes");
                        String seconds = intent.getStringExtra("Seconds");



                        mDayCounter.setText(days+"");
                        mHourCounter.setText(hours+"");
                        mMiniuteCounter.setText(minutes+"");
                        mSecondsCounter.setText(seconds+"");

                        if(days.equals("0") && hours.equals("0") && minutes.equals("0") && seconds.equals("0")){
                            mCompititionCounterMap.setVisibility(View.GONE);
                        }
                    }
                });


            }catch (Exception e){

            }

        }
    };
    private final BroadcastReceiver mBroadcastReceiverBadges = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            try{

                int BadgeCount = intent.getIntExtra("ChatBadge",0);



                badge.setNumber(BadgeCount);

            }catch (Exception e){

            }

        }
    };

    @Override
    public void onResume() {
        super.onResume();

        /*ChatValueCounter = SaveSharedPreference.getChatNotificationCounter(getContext());
        chatNotifyValue.setText(ChatValueCounter+"");*/
        try{
            badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));
            /*if(SaveSharedPreference.getChatNotifictationCounterZero(getContext())){
                badge.setNumber(SaveSharedPreference.getChatNotificationCounterCurrent(getContext()));
            }else{
                badge.setNumber(0);
            }*/

        }catch (Exception e){

        }

    }

    public void init(View view){

        mStarContainer = view.findViewById(R.id.StarStrip);
        mValuesContainer = view.findViewById(R.id.ValueStrip);

        mStarItem1 = view.findViewById(R.id.starItem1);
        mStarItem2 = view.findViewById(R.id.starItem2);
        mStarItem3 = view.findViewById(R.id.starItem3);
        mStarItem4 = view.findViewById(R.id.starItem4);
        mStarItem5 = view.findViewById(R.id.starItem5);

        mstar1GrayBtn = view.findViewById(R.id.star1Button);
        mstar1GoldBtn = view.findViewById(R.id.star1ButtonGold);
        mstar2GrayBtn = view.findViewById(R.id.star2Button);
        mstar2GoldBtn = view.findViewById(R.id.star2ButtonGold);
        mstar3GrayBtn = view.findViewById(R.id.star3Button);
        mstar3GoldBtn = view.findViewById(R.id.star3ButtonGold);
        mstar4GrayBtn = view.findViewById(R.id.star4Button);
        mstar4GoldBtn = view.findViewById(R.id.star4ButtonGold);
        mstar5GrayBtn = view.findViewById(R.id.star5Button);
        mstar5GoldBtn = view.findViewById(R.id.star5ButtonGold);

        mHomeBtn = view.findViewById(R.id.homebtn);
        mHomeBtnGray = view.findViewById(R.id.homebtnic);
        mHomeBtnGold = view.findViewById(R.id.homebtnicGold);
        mEditBtn = view.findViewById(R.id.homebtnedit);
        mEditBtnGray = view.findViewById(R.id.homebtneditic);
        mEditBtnGold = view.findViewById(R.id.homebtnediticGold);
        mPlayBtn = view.findViewById(R.id.PlayChaseing);
        mPauseBtn = view.findViewById(R.id.PauseChaseing);
        mStopBtn = view.findViewById(R.id.StopChaseing);
        mSaveBtn = view.findViewById(R.id.SaveHome);
        mFinishBtn = view.findViewById(R.id.FinishChaseing);
        mCancelBtn = view.findViewById(R.id.CancelChaseing);
        mstepView = view.findViewById(R.id.StepCounter);
        mcaloriesView = view.findViewById(R.id.CaloriesCounter);
        mdistanceView = view.findViewById(R.id.DistanceCounter);
        starValut = view.findViewById(R.id.starNumber);

        //mstopeWatchView = view.findViewById(R.id.StopWatchCounter);


        mStepsProvider = new StepsProvider(getActivity());

        mStepsProvider.setStepListener(MapFragmentNew.this);

        mstopeWatchView = view.findViewById(R.id.StopWatchCounter);

        mstopeWatchView.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                                                         @Override
                                                         public void onChronometerTick(Chronometer chronometerChanged) {
                                                             mstopeWatchView = chronometerChanged;
                                                         }
                                                     }
        );

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (requestCode != LOCATION_PERMISSION_REQUEST_CODE && requestCode != BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE) {

                    return;
                }
            }else{
                if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {

                    return;
                }
            }


            //BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE

            if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                SaveSharedPreference.setLocationPermissionForeground(getContext(),true);
                isGPSEnable = true;

                if (mMap != null)
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

                        return;
                    }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                            != PackageManager.PERMISSION_GRANTED)  {

                        //isAppHasAccessLocationPermission=false;
                        // Permission to access the locationProvider is missing.

                        this.requestPermissions(new String[] {Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                                BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE);

                    }
                }
                mMap.setMyLocationEnabled(true);

                ProgressManager.showProgress(getContext(),"");
                ProgressManager.hideProgress();
                getCurrentLocation();
                try{
                    String version = null;
                    try {
                        PackageInfo pInfo =getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                        version = pInfo.versionName;
                        Log.i("AppVersion",version);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    Realm_User mUser = provideUserLocal(getContext());
                    mapFragPresenter.UpdateUserPermissionAndVersion(getContext(),mUser,version);
                }catch (Exception e){

                }
            } else {
                //Display the missing permission error dialog when the fragments resume.
                //mPermissionDenied = true;
            }

            if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                SaveSharedPreference.setLocationPermissionBackground(getContext(),true);

                try{
                    String version = null;
                    try {
                        PackageInfo pInfo =getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                        version = pInfo.versionName;
                        Log.i("AppVersion",version);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    Realm_User mUser = provideUserLocal(getContext());
                    mapFragPresenter.UpdateUserPermissionAndVersion(getContext(),mUser,version);
                }catch (Exception e){

                }
            }
        }catch (Exception ex)
        {
            ex.toString();
        }
    }
    @Override
    public boolean onMyLocationButtonClick() { return false; }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;

        try {


            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            map.getUiSettings().setMyLocationButtonEnabled(true);

            map.getUiSettings().setZoomControlsEnabled(false);

            mMap.setOnCameraIdleListener(onCameraIdleListener);
            try {

                Settings.Secure.setLocationProviderEnabled(getActivity().getContentResolver(), LocationManager.GPS_PROVIDER, true);

            } catch (Exception e) {

            }

            enableMyLocation();


            if (isAppHasAccessLocationPermission) {

                getCurrentLocation();
                if(userMarker!=null){
                    userMarker.setVisible(false);
                }


                if (!starChaseClicked && userMarker==null)
                {

                    getCurrentLocation();
                    if(userMarker!=null){
                        userMarker.setVisible(false);
                    }

                }
                else
                {

                    getCurrentLocation();
                    if(userMarker!=null){
                        userMarker.setVisible(false);
                    }

                    if (Constants.isMyServiceRunning(GPS_Service.class, getContext())) {

                        mStarContainer.setVisibility(View.GONE);
                        mValuesContainer.setVisibility(View.VISIBLE);
                        mStopBtn.setVisibility(View.VISIBLE);
                        //routeAndStarDataModel received from service and drawn on Map
                        if (routeAndStarDataModel != null) {
                            LatLng latLng = new LatLng(routeAndStarDataModel.getUserCurrentLocation().getLatitude(), routeAndStarDataModel.getUserCurrentLocation().getLongitude());
                            if(!mService.isAllStarsPicked()) {
                                addUpdateStarsToMap(mService.getLastStarPickedNumber());
                                addUserTrackToMap(routeAndStarDataModel.getUserChasedRoot());
                            }
                            addUserMarkerToMap(latLng);
                            if(mService.isAllStarsPicked()) {
                                //Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                                starValut.setText(mService.getLastStarPickedNumber()-1 + "");
                                //stopClick(null);
                            }
                            else{
                                starValut.setText(mService.getLastStarPickedNumberActual() + "");
                            }
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));

                            mStarClicked=routeAndStarDataModel.getTotalStars();
                        }
                        startClicked =true;
                    }
                    else
                    {

                        addUserMarkerToMap(userMarker.getPosition());
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userMarker.getPosition(), 14));

                    }

                }

            }

        } catch (Exception ex) {

            ex.toString();
        }


    }



    private void enableMyLocation() {


        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)  {

            isAppHasAccessLocationPermission=false;
            // Permission to access the locationProvider is missing.
            SaveSharedPreference.setLocationPermissionForeground(getContext(),false);
            this.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);

        } else if (mMap != null) {
            // Access to the locationProvider has been granted to the app.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        != PackageManager.PERMISSION_GRANTED)  {

                    //isAppHasAccessLocationPermission=false;
                    // Permission to access the locationProvider is missing.
                    SaveSharedPreference.setLocationPermissionBackground(getContext(),false);
                    this.requestPermissions(new String[] {Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                            BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE);

                }
            }
            mMap.setMyLocationEnabled(true);

            isAppHasAccessLocationPermission=true;
            getCurrentLocation();
            if(userMarker!=null){
                userMarker.setVisible(false);
            }

        }
    }


    private void showInfoDialogeEditHomeSelect(){


        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_edit_home, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        alertDialog.show();

    }

    private void showInfoDialogeSaveHomeSelect(){


        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_save_home, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        alertDialog.show();

    }

    private void showInfoDialogeStarSelect(String StarNum){


        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_start_chase, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView startDialog = promptsView.findViewById(R.id.startDialog);
        final TextView startDialog1 = promptsView.findViewById(R.id.startDialog1);

        if(Locale.getDefault().getLanguage().equals("en")){
            String selectedStarDisplayText = getResources().getString(R.string.MapModule_SelectedStar);
            String GeneralStar = getResources().getString(R.string.General_Stars);
            String[] firstPhaseText = selectedStarDisplayText.split("%@");

            firstPhaseText[1].replaceAll("/n","");
            firstPhaseText[1].replaceAll("%@","");
            final SpannableStringBuilder sb = new SpannableStringBuilder(firstPhaseText[0]+StarNum.concat(" KM"));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder(firstPhaseText[1]+StarNum.concat(" "+GeneralStar));

            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 18, 22, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss,13,21, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            startDialog.setText(sb);
            startDialog1.setText(sb1);
        }

        if(Locale.getDefault().getLanguage().equals("sv")){

            String selectedStarDisplayText = getResources().getString(R.string.MapModule_SelectedStar);
            String GeneralStar = getResources().getString(R.string.General_Stars);
            String[] firstPhaseText = selectedStarDisplayText.split("%@");

            firstPhaseText[1].replaceAll("/n","");
            firstPhaseText[1].replaceAll("%@","");

            final SpannableStringBuilder sb = new SpannableStringBuilder(firstPhaseText[0]+StarNum.concat(" KM"));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder(firstPhaseText[1]+StarNum.concat(" "+GeneralStar));

            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 12, 16, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss,24,35, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            startDialog.setText(sb);
            startDialog1.setText(sb1);
        }






        alertDialog.show();

    }

    private void showInfoDialogeStarPicked(String StarNum){

        /*int Star = mStarClicked;
        Star++;*/
        int starInc = mStarClicked - mService.getLastStarPickedNumberActual();
        String Starstr = String.valueOf(starInc);
        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_star_picked, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //alertDialog.dismiss();

        final TextView starNumber = promptsView.findViewById(R.id.starNumber);
        final TextView starTxtDialog = promptsView.findViewById(R.id.starDialog1);
        final TextView startTxtDialog1 = promptsView.findViewById(R.id.starDialog2);

        if(Locale.getDefault().getLanguage().equals("en")){
            String remainingStarDisplayText = getResources().getString(R.string.MapModule_AcheivedStarRemainingStar);
            String GeneralStar = getResources().getString(R.string.General_Stars);
            String[] firstPhaseText = remainingStarDisplayText.split("%@");

            final SpannableStringBuilder sb = new SpannableStringBuilder(Starstr.concat(" "+GeneralStar+" "+firstPhaseText[0]));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder(firstPhaseText[1].concat(String.valueOf(mStarClicked)).concat(" KM".concat(firstPhaseText[2])));
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss, 18, 22, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            starNumber.setText(StarNum);
            starTxtDialog.setText(sb);//2 stars are remaining
            startTxtDialog1.setText(sb1);//on your selected 3 Km path
        }

        if(Locale.getDefault().getLanguage().equals("sv")){
            String remainingStarDisplayText = getResources().getString(R.string.MapModule_AcheivedStarRemainingStar);
            String GeneralStar = getResources().getString(R.string.General_Stars);
            String[] firstPhaseText = remainingStarDisplayText.split("%@");
           // String[] SecondphaseText = firstPhaseText[1].split("%@");

            final SpannableStringBuilder sb = new SpannableStringBuilder(Starstr.concat(" "+GeneralStar+" "+firstPhaseText[0]));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder(firstPhaseText[1].concat(String.valueOf(mStarClicked)).concat("KM".concat(firstPhaseText[2])));
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss, 14, 17, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            starNumber.setText(StarNum);
            starTxtDialog.setText(sb);//2 stars are remaining
            startTxtDialog1.setText(sb1);//on your selected 3 Km path
        }



        alertDialog.show();



    }

    private void showInfoDialogeStarPickedHome(String StarNum){


        /*int Star = mStarClicked;
        Star++;*/
        int starInc = HomeDistance - mService.getLastStarPickedNumberActual();
        String Starstr = String.valueOf(starInc);
        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_star_picked, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView starNumber = promptsView.findViewById(R.id.starNumber);
        final TextView starTxtDialog = promptsView.findViewById(R.id.starDialog1);
        final TextView startTxtDialog1 = promptsView.findViewById(R.id.starDialog2);

        if(Locale.getDefault().getLanguage().equals("en")){
            String remainingStarDisplayText = getResources().getString(R.string.MapModule_AcheivedStarRemainingStar);
            String GeneralStar = getResources().getString(R.string.General_Stars);
            String[] firstPhaseText = remainingStarDisplayText.split("%@");

            final SpannableStringBuilder sb = new SpannableStringBuilder(Starstr.concat(" "+GeneralStar+" "+firstPhaseText[0]));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder(firstPhaseText[1].concat(String.valueOf(mStarClicked)).concat(" KM".concat(firstPhaseText[2])));
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 0, 8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss, 18, 22, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            starNumber.setText(StarNum);
            starTxtDialog.setText(sb);//2 stars are remaining
            startTxtDialog1.setText(sb1);//on your selected 3 Km path
        }

        if(Locale.getDefault().getLanguage().equals("sv")){
            String remainingStarDisplayText = getResources().getString(R.string.MapModule_AcheivedStarRemainingStar);
            String GeneralStar = getResources().getString(R.string.General_Stars);
            String[] firstPhaseText = remainingStarDisplayText.split("%@");
            // String[] SecondphaseText = firstPhaseText[1].split("%@");

            final SpannableStringBuilder sb = new SpannableStringBuilder(Starstr.concat(" "+GeneralStar+" "+firstPhaseText[0]));
            final SpannableStringBuilder sb1 = new SpannableStringBuilder(firstPhaseText[1].concat(String.valueOf(mStarClicked)).concat("KM".concat(firstPhaseText[2])));
            final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
            sb.setSpan(bss, 0, 11, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            sb1.setSpan(bss, 14, 17, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            starNumber.setText(StarNum);
            starTxtDialog.setText(sb);//2 stars are remaining
            startTxtDialog1.setText(sb1);//on your selected 3 Km path
        }


        alertDialog.show();

    }






    private void showInfoDialogeStarPickedCongratulations(String StarNum){


        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_star_congratulations, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(promptsView);
        alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        final TextView startTxtDialog1 = promptsView.findViewById(R.id.starNumber);

        final SpannableStringBuilder sb = new SpannableStringBuilder(StarNum);
        //final SpannableStringBuilder sb1 = new SpannableStringBuilder(StarNum);
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        sb.setSpan(bss, 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        startTxtDialog1.setText(StarNum);


        alertDialog.show();

    }





    private void getCurrentLocation() {


        isSetZoomLevelRequired=true;

        try {
            createLocationProvider();
            locationProvider.beginUpdates();
            locationProvider.setListener(new SimpleLocation.Listener() {

                public void onPositionChanged() {

                    if (locationProvider == null) return;
                    //LatLng latLng = new LatLng(58.657869, 14.958790);
                     LatLng latLng = new LatLng(locationProvider.getLatitude(), locationProvider.getLongitude());

                    addUserMarkerToMap(latLng);

                    if (isSetZoomLevelRequired)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userMarker.getPosition(), 14));

                    isSetZoomLevelRequired = false;

                    if (locationProvider != null) {
                        locationProvider.endUpdates();
                    }
                    userMarker.setVisible(false);

                }

                @Override
                public void onGPSStatusChanged(boolean status) {

                    try{
                        if (status) {
                            if (locationProvider != null) {
                                locationProvider.endUpdates();
                            }

                            getCurrentLocation();

                        } else {
                            Toast.makeText(getContext(), "Please do not turn off gps", Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception ex){

                    }

                }

            });

        } catch (Exception ex) {
            //ex.toString();
            Log.i("Map Error ::" ,ex.toString());
            FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(ex.getStackTrace()));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }
    }

    private void createLocationProvider() {
        try {

            boolean requireFineGranularity = true;
            boolean passiveMode = false;
            long updateIntervalInMilliseconds = 500;
            boolean requireNewLocation = true;
            locationProvider = new SimpleLocation(getContext(), requireFineGranularity, passiveMode, updateIntervalInMilliseconds, requireNewLocation);

        } catch (Exception ex) {
            ex.toString();
        }

    }

    private void addUserMarkerToMap(LatLng latLng)
    {
        try {

            if(userMarker!=null)
            {
                //userMarker.remove();
                userMarker.setVisible(false);
            }

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.anchor(0.5f, 1f);

            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(Color.HSVToColor( new float[]{0, 0f, 100f})));
            /*IconGenerator mIconGenerator = new IconGenerator(getContext());
            mIconGenerator.setBackground(TRANSPARENT_DRAWABLE);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));*/
            //markerOptions.icon(getMarkerIcon(R.color.transparent));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_transparent_marker));
            markerOptions.getPosition();

            userMarker = mMap.addMarker(markerOptions);

        }catch (Exception ex){
            Log.i("MapEX",ex.toString());
        }
    }

    private void addUserHomeMarkerToMap(LatLng latLng)
    {
        try {

           /* if(userMarker!=null)
            {
                userMarker.remove();
            }
*/
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.anchor(0.5f, 1f);
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.home_color));
            markerOptions.getPosition();

            HomeLocationMarker = mMap.addMarker(markerOptions);

        }catch (Exception ex){}

    }


    private void addUserStarInfoMarkerToMap(LatLng latLng,String StarNum)
    {
        try {

           /* if(userMarker!=null)
            {
                userMarker.remove();
            }
*/
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            //markerOptions.title("Current Position");
            markerOptions.anchor(0.5f, 1f);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markerOptions.getPosition();
            UserStarInfoMarkerWindowView customInfoWindow = new UserStarInfoMarkerWindowView(getContext());
            mMap.setInfoWindowAdapter(customInfoWindow);
            userMarker = mMap.addMarker(markerOptions);
            userMarker.setTag(StarNum);
            userMarker.showInfoWindow();


        }catch (Exception ex){}

    }


    private void addUserStarPickedInfoMarkerToMap(LatLng latLng,String StarNum)
    {
        try {

           /* if(userMarker!=null)
            {
                userMarker.remove();
            }
*/

            int starInc = mStarClicked - mService.getLastStarPickedNumberActual();


            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            //markerOptions.title("Current Position");
            markerOptions.anchor(0.5f, 1f);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markerOptions.getPosition();
            UserStarPickedInfoMarkerWindowView customInfoWindow = new UserStarPickedInfoMarkerWindowView(getContext());


            StarPickInfoWindowModel starPickInfoWindowModel = new StarPickInfoWindowModel();
            starPickInfoWindowModel.setCurrentPickedStar(String.valueOf(starInc));
            starPickInfoWindowModel.setMstarClicked(String.valueOf(mStarClicked));
            starPickInfoWindowModel.setOldStarPicked(StarNum);


            mMap.setInfoWindowAdapter(customInfoWindow);
            userMarker = mMap.addMarker(markerOptions);
            userMarker.setTag(starPickInfoWindowModel);
            userMarker.showInfoWindow();


        }catch (Exception ex){
            Log.i("Ex",ex.toString());
        }

    }

    private void addUserStarPickedHomeInfoMarkerToMap(LatLng latLng,String StarNum)
    {
        try {

           /* if(userMarker!=null)
            {
                userMarker.remove();
            }
*/

            int starInc = HomeDistance - mService.getLastStarPickedNumberActual();


            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            //markerOptions.title("Current Position");
            markerOptions.anchor(0.5f, 1f);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markerOptions.getPosition();
            UserStarPickedInfoMarkerWindowView customInfoWindow = new UserStarPickedInfoMarkerWindowView(getContext());


            StarPickInfoWindowModel starPickInfoWindowModel = new StarPickInfoWindowModel();
            starPickInfoWindowModel.setCurrentPickedStar(String.valueOf(starInc));
            starPickInfoWindowModel.setMstarClicked(String.valueOf(mStarClicked));
            starPickInfoWindowModel.setOldStarPicked(StarNum);


            mMap.setInfoWindowAdapter(customInfoWindow);
            userMarker = mMap.addMarker(markerOptions);
            userMarker.setTag(starPickInfoWindowModel);
            userMarker.showInfoWindow();


        }catch (Exception ex){
            Log.i("Ex",ex.toString());
        }

    }

    private boolean isUserPositionLocated() {

        return userMarker == null ? false : true;
    }

    private LatLng getUserPosition() {
        LatLng latLng = null;

        if (isUserPositionLocated()) {
            latLng = userMarker.getPosition();
        }

        return latLng;
    }

    @Override
    public void onMapFragmentReady() {
        try {

            mMapFragment.getMapAsync(this);

        } catch (Exception ex) {
            ex.toString();
        }
    }

    private void starClicked(int starNum) {

        // ProgressManager.showProgress(getContext(),"Searching...");

        mStarClicked = starNum;

        if (!isUserPositionLocated()) {
            Toast.makeText(getContext(), getResources().getString(R.string.progress_dialog_message), Toast.LENGTH_SHORT).show();

            return;
        }


        // mStarClicked = 1;


        //mCancelBtn.setVisibility(View.VISIBLE);

        if (mapFragPresenter != null) {
            ProgressManager.showProgress(getContext(),getResources().getString(R.string.progress_dialog_message));
            mPlayBtn.setVisibility(View.GONE);
            //LatLng latLng = new LatLng(58.657869, 14.958790);
            //mapFragPresenter.getPlacesInRadius(starNum * Constants.MAP_RADIUS, 10, latLng);
            //LatLng latLng =new LatLng(58.673864,14.959445);
            mapFragPresenter.getPlacesInRadius(starNum * Constants.MAP_RADIUS, 10, getUserPosition());
            //mapFragPresenter.getPlacesInRadius(starNum * Constants.MAP_RADIUS, 10, latLng);
        }
    }

    @Override
    public void starOneClick(View view) {

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {

                Boolean ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                if (ContestStatus && !ContestEndStatus) {

                    try {
                        userMarker.setVisible(false);
                        HomeBtnClick = false;
                        mHomeBtn.setEnabled(true);
                        mMap.setOnCameraMoveListener(null);
                        mSaveBtn.setVisibility(View.GONE);
                        mstar1GrayBtn.setVisibility(View.INVISIBLE);
                        mstar1GoldBtn.setVisibility(View.VISIBLE);


                        mstar2GrayBtn.setVisibility(View.VISIBLE);
                        mstar2GoldBtn.setVisibility(View.INVISIBLE);
                        mstar3GrayBtn.setVisibility(View.VISIBLE);
                        mstar3GoldBtn.setVisibility(View.INVISIBLE);
                        mstar4GrayBtn.setVisibility(View.VISIBLE);
                        mstar4GoldBtn.setVisibility(View.INVISIBLE);
                        mstar5GrayBtn.setVisibility(View.VISIBLE);
                        mstar5GoldBtn.setVisibility(View.INVISIBLE);
                        mHomeBtnGold.setVisibility(View.INVISIBLE);
                        mHomeBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGold.setVisibility(View.INVISIBLE);
                        getCurrentLocation();

                        if (mMap != null) {
                            mMap.clear();

                            //addUserStarInfoMarkerToMap(userMarker.getPosition());
                        }

                        starClicked(1);
                    } catch (Exception e) {

                        mstar1GrayBtn.setVisibility(View.VISIBLE);
                        mstar1GoldBtn.setVisibility(View.GONE);
                        MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.MapModule_Something_Went_Wrong));

                    }

                }else if(ContestEndStatus){

                    DialogeBoxContestEndDate(getContext(),mUser);
                }else if(!ContestStatus){

                    DialogeBoxContestDate();
                }
                else {

                    DialogeBoxContestDate();

                }
            }else{
                DialogeBoxContestDate();
            }
        }catch (Exception ex){

        }



    }

    @Override
    public void starTwoClick(View view) {

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {

                Boolean ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                if (ContestStatus && !ContestEndStatus) {

                    try {

                        HomeBtnClick = false;
                        mHomeBtn.setEnabled(true);
                        mMap.setOnCameraMoveListener(null);
                        mSaveBtn.setVisibility(View.GONE);
                        mstar2GrayBtn.setVisibility(View.INVISIBLE);
                        mstar2GoldBtn.setVisibility(View.VISIBLE);

                        mstar1GrayBtn.setVisibility(View.VISIBLE);
                        mstar1GoldBtn.setVisibility(View.INVISIBLE);
                        mstar3GrayBtn.setVisibility(View.VISIBLE);
                        mstar3GoldBtn.setVisibility(View.INVISIBLE);
                        mstar4GrayBtn.setVisibility(View.VISIBLE);
                        mstar4GoldBtn.setVisibility(View.INVISIBLE);
                        mstar5GrayBtn.setVisibility(View.VISIBLE);
                        mstar5GoldBtn.setVisibility(View.INVISIBLE);
                        mHomeBtnGold.setVisibility(View.INVISIBLE);
                        mHomeBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGold.setVisibility(View.INVISIBLE);
                        getCurrentLocation();
                        userMarker.setVisible(false);

                        if (mMap != null) {
                            mMap.clear();

                            //addUserMarkerToMap(userMarker.getPosition());
                        }

                        starClicked(2);

                    } catch (Exception e) {

                        mstar2GrayBtn.setVisibility(View.VISIBLE);
                        mstar2GoldBtn.setVisibility(View.GONE);
                        MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.MapModule_Something_Went_Wrong));

                    }

                }else if(ContestEndStatus){
                    DialogeBoxContestEndDate(getContext(),mUser);
                }else if(!ContestStatus){

                    DialogeBoxContestDate();
                } else {

                    DialogeBoxContestDate();

                }
            }else{
                DialogeBoxContestDate();
            }

        }catch (Exception ex){

        }


    }

    @Override
    public void starThreeClick(View view) {


        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {
                Boolean ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                if (ContestStatus && !ContestEndStatus) {

                    try {
                        HomeBtnClick = false;
                        mHomeBtn.setEnabled(true);
                        mMap.setOnCameraMoveListener(null);
                        mSaveBtn.setVisibility(View.GONE);
                        mstar3GrayBtn.setVisibility(View.INVISIBLE);
                        mstar3GoldBtn.setVisibility(View.VISIBLE);

                        mstar1GrayBtn.setVisibility(View.VISIBLE);
                        mstar1GoldBtn.setVisibility(View.INVISIBLE);
                        mstar2GrayBtn.setVisibility(View.VISIBLE);
                        mstar2GoldBtn.setVisibility(View.INVISIBLE);

                        mstar4GrayBtn.setVisibility(View.VISIBLE);
                        mstar4GoldBtn.setVisibility(View.INVISIBLE);
                        mstar5GrayBtn.setVisibility(View.VISIBLE);
                        mstar5GoldBtn.setVisibility(View.INVISIBLE);
                        mHomeBtnGold.setVisibility(View.INVISIBLE);
                        mHomeBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGold.setVisibility(View.INVISIBLE);
                        getCurrentLocation();
                        userMarker.setVisible(false);

                        if (mMap != null) {
                            mMap.clear();

                            //addUserMarkerToMap(userMarker.getPosition());
                        }


                        starClicked(3);

                    } catch (Exception e) {

                        mstar3GrayBtn.setVisibility(View.VISIBLE);
                        mstar3GoldBtn.setVisibility(View.GONE);
                        MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.MapModule_Something_Went_Wrong));

                    }


                }else if(ContestEndStatus){
                    DialogeBoxContestEndDate(getContext(),mUser);
                }else if(!ContestStatus){

                    DialogeBoxContestDate();
                } else {

                    DialogeBoxContestDate();

                }
            }else{
                DialogeBoxContestDate();
            }
        }catch (Exception e){

        }



    }

    @Override
    public void starFourClick(View view) {

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {
                Boolean ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                if (ContestStatus && !ContestEndStatus) {

                    try {
                        HomeBtnClick = false;
                        mHomeBtn.setEnabled(true);
                        mMap.setOnCameraMoveListener(null);
                        mSaveBtn.setVisibility(View.GONE);
                        mstar4GrayBtn.setVisibility(View.INVISIBLE);
                        mstar4GoldBtn.setVisibility(View.VISIBLE);

                        mstar1GrayBtn.setVisibility(View.VISIBLE);
                        mstar1GoldBtn.setVisibility(View.INVISIBLE);
                        mstar2GrayBtn.setVisibility(View.VISIBLE);
                        mstar2GoldBtn.setVisibility(View.INVISIBLE);
                        mstar3GrayBtn.setVisibility(View.VISIBLE);
                        mstar3GoldBtn.setVisibility(View.INVISIBLE);

                        mstar5GrayBtn.setVisibility(View.VISIBLE);
                        mstar5GoldBtn.setVisibility(View.INVISIBLE);
                        mHomeBtnGray.setVisibility(View.VISIBLE);
                        mHomeBtnGold.setVisibility(View.INVISIBLE);

                        mEditBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGold.setVisibility(View.GONE);
                        getCurrentLocation();
                        userMarker.setVisible(false);
                        if (mMap != null) {
                            mMap.clear();

                            //addUserMarkerToMap(userMarker.getPosition());
                        }

                        starClicked(4);
                    } catch (Exception e) {

                        mstar4GrayBtn.setVisibility(View.VISIBLE);
                        mstar4GoldBtn.setVisibility(View.GONE);
                        MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.MapModule_Something_Went_Wrong));

                    }

                }else if(ContestEndStatus){
                    DialogeBoxContestEndDate(getContext(),mUser);
                }else if(!ContestStatus){

                    DialogeBoxContestDate();
                } else {

                    DialogeBoxContestDate();

                }
            }else{
                DialogeBoxContestDate();
            }
        }catch (Exception ex){

        }




    }

    @Override
    public void starFiveClick(View view) {


        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {
                Boolean ContestStatus = getCompitionStartDate(getContext(), CompitionDate.getStartDate(), currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),CompitionDate.getmEndDate(),currentDateandTime);
                if (ContestStatus && !ContestEndStatus) {

                    try {
                        HomeBtnClick = false;
                        mHomeBtn.setEnabled(true);
                        mMap.setOnCameraMoveListener(null);
                        mSaveBtn.setVisibility(View.GONE);
                        mstar5GrayBtn.setVisibility(View.INVISIBLE);
                        mstar5GoldBtn.setVisibility(View.VISIBLE);

                        mstar1GrayBtn.setVisibility(View.VISIBLE);
                        mstar1GoldBtn.setVisibility(View.INVISIBLE);
                        mstar2GrayBtn.setVisibility(View.VISIBLE);
                        mstar2GoldBtn.setVisibility(View.INVISIBLE);
                        mstar3GrayBtn.setVisibility(View.VISIBLE);
                        mstar3GoldBtn.setVisibility(View.INVISIBLE);
                        mstar4GrayBtn.setVisibility(View.VISIBLE);
                        mstar4GoldBtn.setVisibility(View.INVISIBLE);

                        mHomeBtnGold.setVisibility(View.INVISIBLE);
                        mHomeBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGold.setVisibility(View.INVISIBLE);
                        getCurrentLocation();
                        userMarker.setVisible(false);

                        if (mMap != null) {
                            mMap.clear();

                            //addUserMarkerToMap(userMarker.getPosition());
                        }

                        starClicked(5);
                    } catch (Exception e) {

                        mstar5GrayBtn.setVisibility(View.VISIBLE);
                        mstar5GoldBtn.setVisibility(View.GONE);
                        MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.MapModule_Something_Went_Wrong));

                    }

                }else if(ContestEndStatus){
                    DialogeBoxContestEndDate(getContext(),mUser);
                }else if(!ContestStatus){
                    DialogeBoxContestDate();
                } else {
                    DialogeBoxContestDate();
                }
            }else{
                DialogeBoxContestDate();
            }
        }catch (Exception ex){

        }

    }

    @Override
    public void homeClick(View view) {

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {
                Boolean ContestStatus = getCompitionStartDate(getContext(), CompitionDate.getStartDate(), currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),CompitionDate.getmEndDate(),currentDateandTime);
                if (ContestStatus && !ContestEndStatus) {

                    try {
                        // mMap.setOnCameraMoveListener(null);
                        //mMap.setOnCameraIdleListener(null);
                        HomeBtnClick = true;
                        mMap.setOnCameraMoveListener(null);
                        getCurrentLocation();
                        userMarker.setVisible(false);
                        //mEditBtn.setEnabled(false);
                        mEditBtnGray.setVisibility(View.VISIBLE);
                        mEditBtnGold.setVisibility(View.GONE);
                        mHomeBtnGray.setVisibility(View.INVISIBLE);
                        mHomeBtnGold.setVisibility(View.VISIBLE);

                        mstar1GrayBtn.setVisibility(View.VISIBLE);
                        mstar1GoldBtn.setVisibility(View.INVISIBLE);
                        mstar2GrayBtn.setVisibility(View.VISIBLE);
                        mstar2GoldBtn.setVisibility(View.INVISIBLE);
                        mstar3GrayBtn.setVisibility(View.VISIBLE);
                        mstar3GoldBtn.setVisibility(View.INVISIBLE);
                        mstar4GrayBtn.setVisibility(View.VISIBLE);
                        mstar4GoldBtn.setVisibility(View.INVISIBLE);
                        mstar5GrayBtn.setVisibility(View.VISIBLE);
                        mstar5GoldBtn.setVisibility(View.INVISIBLE);
                        mSaveBtn.setVisibility(View.GONE);


                        if (mMap != null) {
                            mMap.clear();

                            //addUserMarkerToMap(userMarker.getPosition());
                        }


                        LatLng homeCord = SaveSharedPreference.getLastKnownLocation(getContext());

                        if (homeCord == null) {
                            //MyApplication.showSimpleSnackBar(getContext(),"Save Your Home Location first");
                            SetDailogeHomeLocation();
                        } else {
                            mapFragPresenter.getHome(userMarker.getPosition(), homeCord);
                        }
                    } catch (Exception e) {
                        Log.i("home ex", e.toString());
                        mHomeBtnGray.setVisibility(View.VISIBLE);
                        mHomeBtnGold.setVisibility(View.INVISIBLE);
                        MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.MapModule_Something_Went_Wrong));
                    }

                }else if(ContestEndStatus){
                    DialogeBoxContestEndDate(getContext(),mUser);
                }else if(!ContestStatus){
                    DialogeBoxContestDate();
                } else {
                    DialogeBoxContestDate();
                }
            }else{
                DialogeBoxContestDate();
            }
        }catch (Exception e){

        }

    }

    @Override
    public void editClick(View view) {

        try{
            if(mMap!=null)
            {
                mMap.clear();

                //addUserMarkerToMap(userMarker.getPosition());
            }
            resetStarUI();
            HomeBtnClick =false;
            mHomeBtn.setEnabled(false);
            //HomeLocationMarker.setVisible(false);
        showInfoDialogeEditHomeSelect();
        mPlayBtn.setVisibility(View.GONE);
        mHomeBtnGold.setVisibility(View.INVISIBLE);
        mHomeBtnGray.setVisibility(View.VISIBLE);
        mSaveBtn.setVisibility(View.VISIBLE);
        //addUserHomeMarkerToMap(userMarker.getPosition());
        //userMarker.setVisible(true);
        mEditBtnGold.setVisibility(View.VISIBLE);
        mEditBtnGray.setVisibility(View.INVISIBLE);

        addUserHomeMarkerToMap(userMarker.getPosition());
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                LatLng midLatLng = mMap.getCameraPosition().target;

                HomeLocationMarker.setPosition(mMap.getCameraPosition().target);


                //addUserMarkerToMap(midLatLng);
                //Toast.makeText(getContext(), midLatLng.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }catch (Exception e){

        mHomeBtnGray.setVisibility(View.VISIBLE);
        mHomeBtnGold.setVisibility(View.INVISIBLE);
        MyApplication.showSimpleSnackBar(getContext(),getResources().getString(R.string.MapModule_Something_Went_Wrong));

    }

    }

    @Override
    public void playClick(View view) {

        mHomeBtn.setEnabled(false);
        mEditBtn.setEnabled(false);
        mStarContainer.setVisibility(View.GONE);
        mValuesContainer.setVisibility(View.VISIBLE);
        mPlayBtn.setVisibility(View.GONE);
        //mPauseBtn.setVisibility(View.VISIBLE);
        mStopBtn.setVisibility(View.VISIBLE);
        //mCancelBtn.setVisibility(View.GONE);
        //mHandler.sendEmptyMessage(MSG_START_TIMER);
        startClicked = true;
        SaveSharedPreference.setStopWatchTime(getContext(),0);
        long StopWatchTime = SaveSharedPreference.getStopWatchTime(getContext());
        if(StopWatchTime == 0){
            StopWatchTime = SystemClock.elapsedRealtime();
            SaveSharedPreference.setStopWatchTime(getContext(),StopWatchTime);
            mstopeWatchView.setBase(StopWatchTime);
        }
        else{
            StopWatchTime = StopWatchTime - (StopWatchPauseTime - SystemClock.elapsedRealtime());
            mstopeWatchView.setBase(StopWatchTime);
        }


        mstopeWatchView.start();
        isStart = true;

        if(locationProvider!=null)
        {
            locationProvider.endUpdates();
        }

        startGPSService();

        starChaseClicked=true;
        //getActivity().bindService(new Intent(getContext(), GPS_Service.class), mConnection, Context.BIND_AUTO_CREATE);
        //userMarker.setVisible(false);
    }

    private void startGPSService()
    {
        Intent startIntent = new Intent(getContext(), GPS_Service.class);
        startIntent.setAction(Constants.START_ACTION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            getContext().startForegroundService(startIntent);
        }
        else {
            getContext().startService(startIntent);
        }
        getActivity().bindService(new Intent(getContext(), GPS_Service.class), mConnection, Context.BIND_AUTO_CREATE);

    }

    public void stopGPSService()
    {
        Intent startIntent = new Intent(getContext(), GPS_Service.class);
        startIntent.setAction(Constants.STOP_ACTION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            getContext().startForegroundService(startIntent);

        }
        else getContext().startService(startIntent);

        getActivity().unbindService(mConnection);

    }
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            GPS_Service.LocalBinder binder = (GPS_Service.LocalBinder) iBinder;

            mService = binder.getService();
            mService.setLocationListener(MapFragmentNew.this);

            //Toast.makeText(getContext(), "Service Connected", Toast.LENGTH_SHORT).show();

            if(routeAndStarDataModel!=null)
                mService.setRouteAndStarDataModel(routeAndStarDataModel);
            else
                routeAndStarDataModel = mService.getRouteAndStarDataModel();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            mService.setLocationListener(null);
            mService = null;

            //  Toast.makeText(getContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

    public void SetDailogeConformationStop(){

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.dialog_box_stop_chaseing, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        final Button ContinueBtn = (Button) promptsView.findViewById(R.id.continueBtn);
        final Button EndBtn = (Button) promptsView.findViewById(R.id.endBtn);

        AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mHomeBtn.setEnabled(true);
                mEditBtn.setEnabled(true);
                userMarker.setVisible(false);

                mHomeBtnGray.setVisibility(View.VISIBLE);
                mHomeBtnGold.setVisibility(View.INVISIBLE);
                mEditBtnGray.setVisibility(View.VISIBLE);
                mEditBtnGold.setVisibility(View.GONE);



                mstar1GrayBtn.setVisibility(View.VISIBLE);
                mstar1GoldBtn.setVisibility(View.INVISIBLE);
                mstar2GrayBtn.setVisibility(View.VISIBLE);
                mstar2GoldBtn.setVisibility(View.INVISIBLE);
                mstar3GrayBtn.setVisibility(View.VISIBLE);
                mstar3GoldBtn.setVisibility(View.INVISIBLE);
                mstar4GrayBtn.setVisibility(View.VISIBLE);
                mstar4GoldBtn.setVisibility(View.INVISIBLE);
                mstar5GrayBtn.setVisibility(View.VISIBLE);
                mstar5GoldBtn.setVisibility(View.INVISIBLE);

                mPlayBtn.setVisibility(View.GONE);
                //mPauseBtn.setVisibility(View.GONE);
                mStopBtn.setVisibility(View.GONE);
                mFinishBtn.setVisibility(View.GONE);
                mStarContainer.setVisibility(View.VISIBLE);
                mValuesContainer.setVisibility(View.INVISIBLE);

                startClicked = false;
                StepCounter = 0;
                //StopWatchTime = 0;
                StopWatchPauseTime=0;
                mstopeWatchView.stop();
                //userStepsToSubtract=0;
                StepCounter=0;
                mstepView.setText("0");
                mcaloriesView.setText("0");
                mdistanceView.setText("0");
                starValut.setText("0");
                SaveSharedPreference.setStepsToSubstract(getContext(),0);
                SaveSharedPreference.setUserJourneySteps(getContext(),0);
                SaveSharedPreference.setUserJourneyDistance(getContext(),"0");
                SaveSharedPreference.setUserJourneyCalories(getContext(),"0");
                SaveSharedPreference.setStopWatchTime(getContext(),0);
                SaveSharedPreference.setUserStarCount(getContext(), 0);
                isStart = false;

                if(mMap!=null)
                {
                    mMap.clear();

                    addUserMarkerToMap(userMarker.getPosition());
                    userMarker.setVisible(false);
                }
                starChaseClicked = false;
//              routeAndStarDataModel.clear();
                stopGPSService();



                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

                String currentDateandTime = sdf.format(new Date());


                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date date = null;
                try {
                    date = sdf.parse(currentDateandTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sdf.setTimeZone(TimeZone.getDefault());
                String formattedDate = sdf.format(date);

                Realm_User user = mapFragPresenter.provideUserLocal(getContext());

                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(003);

                String JourneySteps = mstepView.getText().toString();
                String JourneyCalories = mcaloriesView.getText().toString();
                //String JourneyStars = starValut.getText().toString();
                String JourneyDistance = mdistanceView.getText().toString();
                String JourneyTimer = mstopeWatchView.getText().toString();

                /*String StarDBValue = mUser.getStars_count();

                int starFinalValue = 0;

                if (StarDBValue != null) {

                    starFinalValue = Integer.parseInt(StarDBValue) + Integer.parseInt(JourneyStars);

                }else{

                    starFinalValue = Integer.parseInt(JourneyStars);

                }
                if(starFinalValue != 0){

                    Intent intent = new Intent();
                    intent.putExtra("StarCount",starFinalValue);
                    intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

                }else{
                }

                int starDBTotalValue =starFinalValue;

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mapFragPresenter.updateStarUserLocal(String.valueOf(starDBTotalValue),mUser.getId());
                    }
                });*/

                //mapFragPresenter.AddStarSteps(user,JourneyStars,JourneySteps,JourneyCalories,formattedDate,JourneyDistance,JourneyTimer);
                alertDialog.dismiss();
            }
        });


        ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder


                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    @Override
    public void stopClick(View view) {

        SetDailogeConformationStop();
    }

    @Override
    public void finishClick(View view) {



        mHomeBtn.setEnabled(true);
        mEditBtn.setEnabled(true);

        mHomeBtnGray.setVisibility(View.VISIBLE);
        mHomeBtnGold.setVisibility(View.INVISIBLE);

        mEditBtnGray.setVisibility(View.VISIBLE);
        mEditBtnGold.setVisibility(View.GONE);



        mstar1GrayBtn.setVisibility(View.VISIBLE);
        mstar1GoldBtn.setVisibility(View.INVISIBLE);
        mstar2GrayBtn.setVisibility(View.VISIBLE);
        mstar2GoldBtn.setVisibility(View.INVISIBLE);
        mstar3GrayBtn.setVisibility(View.VISIBLE);
        mstar3GoldBtn.setVisibility(View.INVISIBLE);
        mstar4GrayBtn.setVisibility(View.VISIBLE);
        mstar4GoldBtn.setVisibility(View.INVISIBLE);
        mstar5GrayBtn.setVisibility(View.VISIBLE);
        mstar5GoldBtn.setVisibility(View.INVISIBLE);

        mPlayBtn.setVisibility(View.GONE);
        //mPauseBtn.setVisibility(View.GONE);
        mStopBtn.setVisibility(View.GONE);
        mFinishBtn.setVisibility(View.GONE);
        mStarContainer.setVisibility(View.VISIBLE);
        mValuesContainer.setVisibility(View.INVISIBLE);

        startClicked = false;
        StepCounter =0;
        //StopWatchTime = 0;
        StopWatchPauseTime=0;
        mstopeWatchView.stop();
       // userStepsToSubtract=0;
        StepCounter=0;
        isStart = false;
        mstepView.setText("0");
        mcaloriesView.setText("0");
        starValut.setText("0");
        SaveSharedPreference.setStepsToSubstract(getContext(),0);
        SaveSharedPreference.setUserJourneySteps(getContext(),0);
        SaveSharedPreference.setUserJourneyDistance(getContext(),"0");
        SaveSharedPreference.setUserJourneyCalories(getContext(),"0");
        SaveSharedPreference.setUserStarCount(getContext(), 0);

        SaveSharedPreference.setStopWatchTime(getContext(),0);

        if(mMap!=null)
        {
            mMap.clear();

            addUserMarkerToMap(userMarker.getPosition());
            userMarker.setVisible(false);
        }
        starChaseClicked = false;
//        routeAndStarDataModel.clear();
        stopGPSService();



        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        String currentDateandTime = sdf.format(new Date());

        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = sdf.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        String formattedDate = sdf.format(date);

        Realm_User user = mapFragPresenter.provideUserLocal(getContext());
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(003);


        String JourneySteps = mstepView.getText().toString();
        String JourneyCalories = mcaloriesView.getText().toString();
        //String JourneyStars = starValut.getText().toString();
        String JourneyDistance = mdistanceView.getText().toString();
        String JourneyTimer = mstopeWatchView.getText().toString();

        /*String StarDBValue = mUser.getStars_count();

        int starFinalValue = 0;

        if (StarDBValue != null) {

            starFinalValue = Integer.parseInt(StarDBValue) + Integer.parseInt(JourneyStars);

        }else{

            starFinalValue = Integer.parseInt(JourneyStars);

        }
        Intent intent = new Intent();
        intent.putExtra("StarCount",starFinalValue);
        intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);

        int starDBTotalValue =starFinalValue;

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SaveSharedPreference.setUserStarCount(getContext(),starDBTotalValue);
                mapFragPresenter.updateStarUserLocal(String.valueOf(starDBTotalValue),mUser.getId());

            }
        });*/

       // mapFragPresenter.AddStarSteps(user,JourneyStars,JourneySteps,JourneyCalories,formattedDate,JourneyDistance,JourneyTimer);


    }

    @Override
    public void saveClick(View view) {

        mHomeBtn.setEnabled(true);
        userMarker.setVisible(false);
        mEditBtnGold.setVisibility(View.VISIBLE);
        mEditBtnGray.setVisibility(View.INVISIBLE);

        userMarker.setVisible(false);
        mEditBtnGold.setVisibility(View.INVISIBLE);
        mEditBtnGray.setVisibility(View.VISIBLE);

        mSaveBtn.setVisibility(View.GONE);

        mMap.setOnCameraMoveListener(null);
        //mMap.setOnCameraIdleListener(null);
        getCurrentLocation();
        //HomeLocationMarker.setVisible(false);
        //HomeLocationMarker.setVisible(false);
        showInfoDialogeSaveHomeSelect();

        SaveSharedPreference.setLastKnownLocation(getContext(),HomeLocationMarker.getPosition().latitude,HomeLocationMarker.getPosition().longitude);

        //Toast.makeText(getContext(), "Save Home clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPause() {
        super.onPause();
        //userStepsToSubtract = SaveSharedPreference.getStepsToSubstract(getContext());
        //Toast.makeText(getContext(), "Map Pause ", Toast.LENGTH_SHORT).show();
        //SaveSharedPreference.setStepsToSubstract(getContext(),StepCounter);
        //SaveSharedPreference.setStepsToSubstract(getContext(),Integer.parseInt(mstepView.getText().toString()));
        SaveSharedPreference.setUserPauseTime(getContext(),SystemClock.elapsedRealtime());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                    != PackageManager.PERMISSION_GRANTED)  {

                //isAppHasAccessLocationPermission=false;
                // Permission to access the locationProvider is missing.
                SaveSharedPreference.setLocationPermissionBackground(getContext(),false);
                this.requestPermissions(new String[] {Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                        BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE);

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //SaveSharedPreference.setUserPauseTime(getContext(),SystemClock.elapsedRealtime());
        //StopWatchPauseTime = SystemClock.elapsedRealtime();
       // SaveSharedPreference.setUserPauseTime(getContext(),SystemClock.elapsedRealtime());
        //SaveSharedPreference.setStepsToSubstract(getContext(),userStepsToSubtract);
        //Toast.makeText(getContext(), "Map Destroy ", Toast.LENGTH_SHORT).show();
        //stopGPSService();
        /*StepCounter = 0;
       // StopWatchTime = 0;
        StopWatchPauseTime=0;
        mstopeWatchView.stop();
        userStepsToSubtract=0;
        StepCounter=0;
        mstepView.setText("0");
        mcaloriesView.setText("0");
        mdistanceView.setText("0");*/
        //SaveSharedPreference.setStepsToSubstract(getContext(),0);
    }

    @Override
    public void pauseClick(View view) {

        mPlayBtn.setVisibility(View.VISIBLE);
        mPauseBtn.setVisibility(View.GONE);
        mStopBtn.setVisibility(View.GONE);
        mCancelBtn.setVisibility(View.VISIBLE);
        startClicked =false;
        StopWatchPauseTime = SystemClock.elapsedRealtime();
        mstopeWatchView.stop();
    }

    @Override
    public void cancelClick(View view) {

        mstar1GrayBtn.setVisibility(View.VISIBLE);
        mstar1GoldBtn.setVisibility(View.INVISIBLE);
        mstar2GrayBtn.setVisibility(View.VISIBLE);
        mstar2GoldBtn.setVisibility(View.INVISIBLE);
        mstar3GrayBtn.setVisibility(View.VISIBLE);
        mstar3GoldBtn.setVisibility(View.INVISIBLE);
        mstar4GrayBtn.setVisibility(View.VISIBLE);
        mstar4GoldBtn.setVisibility(View.INVISIBLE);
        mstar5GrayBtn.setVisibility(View.VISIBLE);
        mstar5GoldBtn.setVisibility(View.INVISIBLE);

        mPlayBtn.setVisibility(View.GONE);
        mPauseBtn.setVisibility(View.GONE);
        mStopBtn.setVisibility(View.GONE);
        mStarContainer.setVisibility(View.VISIBLE);
        mValuesContainer.setVisibility(View.GONE);
        mCancelBtn.setVisibility(View.GONE);

        if(mMap!=null)
        {
            mMap.clear();

            addUserMarkerToMap(userMarker.getPosition());
        }

    }

    public void resetStarUI(){

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mstar1GrayBtn.setVisibility(View.VISIBLE);
                mstar1GoldBtn.setVisibility(View.INVISIBLE);
                mstar2GrayBtn.setVisibility(View.VISIBLE);
                mstar2GoldBtn.setVisibility(View.INVISIBLE);
                mstar3GrayBtn.setVisibility(View.VISIBLE);
                mstar3GoldBtn.setVisibility(View.INVISIBLE);
                mstar4GrayBtn.setVisibility(View.VISIBLE);
                mstar4GoldBtn.setVisibility(View.INVISIBLE);
                mstar5GrayBtn.setVisibility(View.VISIBLE);
                mstar5GoldBtn.setVisibility(View.INVISIBLE);

            }
        });

    }

    @Override
    public void starChaseClick(View view) {

    }

    @Override
    public void starStopClick(View view) {

    }

    @Override
    public void onMapPathFailed(String errorMessage) {


        //MyApplication.showSimpleSnackBar(getContext(),errorMessage);

        if(errorMessage.equals("com.kampen.riksSe.GetPlacesLib.PlacesException: Result is null")) {
            MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.General_NoInternetConnection));
            ProgressManager.hideProgress();
            resetStarUI();
        }
        else{
            MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.MapModule_No_Road_Accessable));
            ProgressManager.hideProgress();
            resetStarUI();
            //Log.i("LeaderBoard API S",errorMessage);
        }
    }

    @Override
    public void setPlaces(List<Place> places) {

        getActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {


                        //Toast.makeText(getContext(), " Place :: "+places.size(), Toast.LENGTH_SHORT).show();

                       /* for(int i=0; i<places.size(); i++){
                            Log.i("", String.valueOf(places.get(i)));
                        }*/

                        mapFragPresenter.getRequiredPaths(places,userMarker.getPosition());

                    }

                }

        );




    }




    public void SetDailogeHomeLocation(){

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.dialog_box_home, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        final Button ContinueBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button EndBtn = (Button) promptsView.findViewById(R.id.okBtn);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editClick(null);
                alertDialog.dismiss();
            }
        });


        ContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder


                alertDialog.dismiss();

            }
        });

        alertDialog.show();

    }



    @Override
    public void setDirections(ArrayList<Direction> direction) {

        mapFragPresenter.getRequiredRoutesFromDirections(direction);

    }

    @Override
    public void setBestRoutes(ArrayList<Route> bestRoutes,int distance) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                for(int i=0; i<bestRoutes.size();i++)
                {
                    if(bestRoutes.get(i)!=null) {
                        List<LatLng> route = GPS_Util.getRoutePath(bestRoutes.get(i));

                        route = GPS_Util.getMarkersEveryNMeters(route, distance);

                        List<LatLng>  tempPath=new ArrayList<>();
                        List<Double>  starDistances=new ArrayList<>();



                        List<LatLng> starPositions=new ArrayList<>();

                       /* starDistances.add((double) 100);
                        starDistances.add((double) 200);
                        starDistances.add((double) 300);
                        starDistances.add((double) 400);
                        starDistances.add((double) 500);*/

                        starDistances.add((double) 1000);
                        starDistances.add((double) 2000);
                        starDistances.add((double) 3000);
                        starDistances.add((double) 4000);
                        starDistances.add((double) 5000);


                        starPositions=getMarkersFromNMeters(route, starDistances.subList(0,mStarClicked));
                        //starPositions.addAll(getMarkersFromNMeters(route, starDistances.subList(0,mStarClicked).get(0)));
                        // addGreenPolyLineToMap(route,i+1);
                        //addStarsToMap(route.get(route.size()-1));

                        ProgressManager.hideProgress();

                        if(starPositions == null || starPositions.size() ==0){

                            MyApplication.showSimpleSnackBar(getContext()," Stars not found");
                            resetStarUI();

                            return;

                        }

                        routeAndStarDataModel=new RouteAndStarDataModel();
                        routeAndStarDataModel.setTotalStars(mStarClicked);
                        routeAndStarDataModel.setBestRoute(route);
                        routeAndStarDataModel.setStarPositions(starPositions);
                        //routeAndStarDataModel.setReturnRoute(returnRoute);
                        routeAndStarDataModel.setUserCurrentLocation(userMarker.getPosition().latitude,userMarker.getPosition().longitude);
                        addUpdateStarsToMap(1);

                        mPlayBtn.setVisibility(View.VISIBLE);
                        showInfoDialogeStarSelect(String.valueOf(mStarClicked));

                        //addUserStarInfoMarkerToMap(userMarker.getPosition(),String.valueOf(mStarClicked));
                    }
                }
            }
        });
    }


    private void addUpdateStarsToMap(int starNum)
    {

        if(currentMarker!=null)
        {
            currentMarker.setVisible(false);
            currentMarker.remove();
        }

        View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
        TextView numTxt = (TextView) marker.findViewById(R.id.text);
        ImageView starImage = (ImageView) marker.findViewById(R.id.starButton);
        numTxt.setText("" + (starNum));
        starImage.setImageResource(R.mipmap.star_final);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(routeAndStarDataModel.getStarPositions().get(0));
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.title("Star Location");

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), marker)));

        markerOptions.getPosition();

        currentMarker=mMap.addMarker(markerOptions);




    }



    private void addUpdateStarsToMap(int starNum, LatLng Lat)
    {

        if(currentMarker!=null)
        {
            currentMarker.setVisible(false);
            currentMarker.remove();
        }

        View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
        TextView numTxt = (TextView) marker.findViewById(R.id.text);
        ImageView starImage = (ImageView) marker.findViewById(R.id.starButton);
        numTxt.setText("" + (starNum));
        starImage.setImageResource(R.mipmap.star_final);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(Lat);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.title("Star Location");

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), marker)));

        markerOptions.getPosition();

        currentMarker=mMap.addMarker(markerOptions);




    }


    /*private void addStarsToMap(LatLng stars)
    {
        // clear the previously added starts


            View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
            TextView numTxt = (TextView) marker.findViewById(R.id.text);
            ImageView starImage = (ImageView) marker.findViewById(R.id.starButton);
            numTxt.setText("" + mStarClicked);
            starImage.setImageResource(R.mipmap.star_final);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(stars);
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.title("Star Location");

            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), marker)));

            markerOptions.getPosition();

            starsMarkerLists.add(mMap.addMarker(markerOptions));
    }*/

    public static final int PATTERN_DASH_LENGTH_PX = 20;
    public static final int PATTERN_GAP_LENGTH_PX = 10;
    public static final PatternItem DOT = new Dot();
    public static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    public static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    public static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DOT);

    private Polyline addGreenPolyLineToMap(List<LatLng> userTrack,int color)
    {

        PolylineOptions polylineOptions=new PolylineOptions();
        switch (color)
        {
            case 1: {
                polylineOptions.color(Color.GREEN);
                break;
            }
            case 2: {
                polylineOptions.color(Color.RED);
                break;
            }

            case 3: {
                polylineOptions.color(Color.BLUE);
                break;
            }
            case 4: {
                polylineOptions.color(Color.YELLOW);
                break;
            }
            case 5: {
                polylineOptions.color(Color.BLACK);
                break;
            }
        }


        polylineOptions.pattern(PATTERN_POLYGON_ALPHA);

        polylineOptions.addAll(userTrack);

        return mMap.addPolyline(polylineOptions);

    }

    @Override
    public void setDirectionAndRoutes(Direction direction) {

    }


    @Override
    public void setBestRoute(Route bestRoute) {

    }



    @Override
    public void setAllRoute(List<LatLng> bestRoute, List<LatLng> returnRoute, List<LatLng> starPositions) {

        /*getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                addStarsToMap(starPositions);

                //addPolyLineForPath(bestRoute,returnRoute);

                routeAndStarDataModel=new RouteAndStarDataModel();
                routeAndStarDataModel.setBestRoute(bestRoute);
                routeAndStarDataModel.setStarPositions(starPositions);
                routeAndStarDataModel.setReturnRoute(returnRoute);
                routeAndStarDataModel.setUserCurrentLocation(userMarker.getPosition().latitude,userMarker.getPosition().longitude);

                ProgressManager.hideProgress();


            }
        });*/

    }

    @Override
    public void setAddActivity(String message) {

    }

    @Override
    public void setAddActivityFailed(String message) {

    }

    @Override
    public void setStarStepChaseSucess(String message) {
        //MyApplication.showSimpleSnackBarSucess(getContext(),message);
    }

    @Override
    public void setStarStepChaseFailed(String message) {

    }

    /*@Override
    public void setHomeRouteSucess(Route routeSucess) {

        if(currentMarker!=null)
        {
            currentMarker.setVisible(false);
            currentMarker.remove();
        }

        int homeDistance = getRouteDistance(routeSucess);

        if(homeDistance >=1000){

            View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
            TextView numTxt = (TextView) marker.findViewById(R.id.text);
            ImageView starImage = (ImageView) marker.findViewById(R.id.starButton);
            numTxt.setText("" + (getRouteDistance(routeSucess)/1000));
            starImage.setImageResource(R.mipmap.star_final);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(SaveSharedPreference.getLastKnownLocation(getContext()));
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.title("Star Location");

            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), marker)));

            markerOptions.getPosition();

            currentMarker=mMap.addMarker(markerOptions);

            List<LatLng> homelat =new ArrayList<>();
            homelat.add(SaveSharedPreference.getLastKnownLocation(getContext()));

            routeAndStarDataModel=new RouteAndStarDataModel();
            routeAndStarDataModel.setTotalStars(1);
            routeAndStarDataModel.setBestRoute(GPS_Util.getRoutePath(routeSucess));
            routeAndStarDataModel.setStarPositions(homelat);
            //routeAndStarDataModel.setReturnRoute(returnRoute);
            routeAndStarDataModel.setUserCurrentLocation(userMarker.getPosition().latitude,userMarker.getPosition().longitude);

            mPlayBtn.setVisibility(View.VISIBLE);

        }
        else{
            mEditBtn.setEnabled(true);
            MyApplication.showSimpleSnackBar(getContext(),"Your Home Location is less than 1 KM Plz try again");
        }



    }
*/

    @Override
    public void setHomeRouteSucess(Route routeSucess) {

        if(currentMarker!=null)
        {
            currentMarker.setVisible(false);
            currentMarker.remove();
        }
        //userMarker.setVisible(false);
        int homeDistance = getRouteDistance(routeSucess);

        if(homeDistance >=1000){

            List<Double>  starDistances=new ArrayList<>();


            int numberOfStarsToPick=homeDistance/1000;

            HomeDistance=numberOfStarsToPick;

            for(int i=1; i<=numberOfStarsToPick; i++)
            {
                starDistances.add((double) (i*1000));
            }



            View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
            TextView numTxt = (TextView) marker.findViewById(R.id.text);
            ImageView starImage = (ImageView) marker.findViewById(R.id.starButton);
            //numTxt.setText("" + homeDistance/1000);
            starImage.setImageResource(R.drawable.home_color);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(SaveSharedPreference.getLastKnownLocation(getContext()));
            markerOptions.anchor(0.5f, 0.5f);
            markerOptions.title("Star Location");

            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(), marker)));

            markerOptions.getPosition();


            List<LatLng> bestRoute=GPS_Util.getRoutePath(routeSucess);

            mMap.addMarker(markerOptions);

            List<LatLng> homelat =new ArrayList<>();
            homelat=getMarkersFromNMeters(bestRoute,starDistances);
            homelat.add(SaveSharedPreference.getLastKnownLocation(getContext()));

            routeAndStarDataModel=new RouteAndStarDataModel();
            routeAndStarDataModel.setTotalStars(numberOfStarsToPick);
            routeAndStarDataModel.setBestRoute(bestRoute);
            routeAndStarDataModel.setStarPositions(homelat);
            //routeAndStarDataModel.setReturnRoute(returnRoute);
            routeAndStarDataModel.setUserCurrentLocation(userMarker.getPosition().latitude,userMarker.getPosition().longitude);

            addUpdateStarsToMap(1);
            mPlayBtn.setVisibility(View.VISIBLE);
            showInfoDialogeStarSelect(String.valueOf(numberOfStarsToPick));
            //addUserStarInfoMarkerToMap(userMarker.getPosition(),String.valueOf(numberOfStarsToPick));
        }
        else{
            mEditBtn.setEnabled(true);

            MyApplication.showSimpleSnackBar(getContext(),getResources().getString(R.string.MapModule_Home_Failed));
        }



    }

    @Override
    public void setHomeRouteFail(String routefail) {

        if(routefail.equals("com.kampen.riksSe.GetPlacesLib.PlacesException: Result is null")) {
            MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.General_NoInternetConnection));
            ProgressManager.hideProgress();
            resetStarUI();
        }
        else{
            MyApplication.showSimpleSnackBar(getContext(), routefail);
            // Log.i("LeaderBoard API S",errorMessage);
        }

    }

    @Override
    public void setUpdateUserPermissionAndVersionSucess(String message) {

    }

    @Override
    public void setUpdateUserPermissionAndVersionFailed(String message) {

    }

    @Override
    public void setPresenter(MapFragContract.Presenter mPresenter) {

    }

    @Override
    public void onLocationUpdate(LatLng latLng, List<LatLng> userTrack) {


        try {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    // Toast.makeText(getContext(), ""+latLng.latitude+" "+latLng.longitude, Toast.LENGTH_SHORT).show();

                    if (userMarker != null && !userMarker.isVisible()) {
                        userMarker.setVisible(true);
                    }

                    userMarker.setPosition(latLng);


                    if(userChasingTrackPolyline!=null)
                    {
                        userChasingTrackPolyline.remove();
                    }

                    userChasingTrackPolyline=addUserTrackToMap(userTrack);
                    //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userMarker.getPosition(), 14));

                    if(userTrack.size()>1) {
                        double toPrint = SphericalUtil.computeDistanceBetween( userTrack.get(userTrack.size()-2) , latLng);
                        //mdistanceView.setText((int) toPrint + "");
                    }


                }
            });
        } catch (Exception ex) {
            FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(ex.getStackTrace()));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }

    }



    private  Polyline addUserTrackToMap(List<LatLng> userTrack)
    {
        PolylineOptions polylineOptions=new PolylineOptions();
        polylineOptions.color(Color.BLUE);
        polylineOptions.addAll(userTrack);
        return mMap.addPolyline(polylineOptions);

    }





    @Override
    public void onStarPicked(LatLng location, int starNumber) {



        /*count+=1;
        totalPickedStar++;


        starValut.setText(String.valueOf(count));

        addUpdateStarsToMap(routeAndStarDataModel.getStarPositions(),starNumber);*/

    }

    @Override
    public void onStarPickedNew(LatLng location,LatLng starLocation, int oldStarNumber, int newStarNumber) {



        try {
            count+=1;

            totalPickedStar++;


            starValut.setText(String.valueOf(oldStarNumber));

            addUpdateStarsToMap(newStarNumber,starLocation);

            if(HomeBtnClick)
            {

                if(alertDialog!=null){
                    alertDialog.dismiss();
                }
                userMarker.hideInfoWindow();
                showInfoDialogeStarPickedHome(String.valueOf(oldStarNumber));

                //userMarker.hideInfoWindow();
                //addUserStarPickedHomeInfoMarkerToMap(userMarker.getPosition(),String.valueOf(oldStarNumber));

            }else{

                if(alertDialog!=null){
                    alertDialog.dismiss();
                }
                userMarker.hideInfoWindow();

                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(003);
                sendNotification(oldStarNumber);
                showInfoDialogeStarPicked(String.valueOf(oldStarNumber));
                Realm_User mUser = provideUserLocal(getContext());
                String StarDBValue = mUser.getStars_count();

                int starFinalValue = 0;

                if (StarDBValue != null) {

                    starFinalValue = Integer.parseInt(StarDBValue) + oldStarNumber;

                }else{

                    starFinalValue = oldStarNumber;

                }
                Intent intent = new Intent();
                intent.putExtra("StarCount",starFinalValue);
                intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                int finalStarFinalValue = starFinalValue;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SaveSharedPreference.setUserStarCount(getContext(), finalStarFinalValue);
                        Realm_User mUser = provideUserLocal(getContext());
                        mapFragPresenter.updateStarUserLocal(String.valueOf(finalStarFinalValue),mUser.getId());

                    }
                });
                //userMarker.hideInfoWindow();
                //addUserStarPickedInfoMarkerToMap(userMarker.getPosition(),String.valueOf(oldStarNumber));

            }

        }catch (Exception ex){
            Log.i("Map Excep",ex.toString());
        }





    }

    @Override
    public void allStarsPicked(LatLng starLocation) {

        try{
            if(currentMarker!=null)
            {
                currentMarker.setVisible(false);
            }
            if(mService.isAllStarsPicked())
                starValut.setText(""+(mService.getLastStarPickedNumberActual()));
            else starValut.setText(""+(mService.getLastStarPickedNumberActual()));


            //stopClick(null);



            String FinalStarNum = starValut.getText().toString();

            if(FinalStarNum.equals("0")){

            }else{
                //starValut.setText(mStarClicked+"");
                //SetDailogeConformationStop();
                // stopGPSService();

                userMarker.hideInfoWindow();
                if(alertDialog!=null){
                    alertDialog.dismiss();
                }
                Realm_User mUser = provideUserLocal(getContext());
                String StarDBValue = mUser.getStars_count();

                int starFinalValue = 0;

                if (StarDBValue != null) {

                    starFinalValue = Integer.parseInt(StarDBValue) + 1;

                }else{

                    starFinalValue = mService.getLastStarPickedNumberActual();

                }
                Intent intent = new Intent();
                intent.putExtra("StarCount",starFinalValue);
                intent.setAction("com.tutorialspoint.CUSTOM_INTENT");
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                int finalStarFinalValue = starFinalValue;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SaveSharedPreference.setUserStarCount(getContext(), finalStarFinalValue);
                        Realm_User mUser = provideUserLocal(getContext());
                        mapFragPresenter.updateStarUserLocal(String.valueOf(finalStarFinalValue),mUser.getId());
                    }
                });
                NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(003);
                sendNotificationCongratulations(mService.getLastStarPickedNumberActual());
                showInfoDialogeStarPickedCongratulations(mService.getLastStarPickedNumberActual()+"");
                mFinishBtn.setVisibility(View.VISIBLE);
                mStopBtn.setVisibility(View.GONE);
            }
        }catch (Exception ex){

        }


    }

    @Override
    public void onSteps(int steps) {

        try {

            if (startClicked) {
                Realm_User mUser = provideUserLocal(getContext());
               // userStepsToSubtract = SaveSharedPreference.getStepsToSubstract(getContext());
                if(SaveSharedPreference.getStepsToSubstract(getContext()) == 0){

                    SaveSharedPreference.setStepsToSubstract(getContext(),steps);
                    StepCounter =0;
                }
                else{
                    StepCounter = Math.abs(steps) - Math.abs(SaveSharedPreference.getStepsToSubstract(getContext()));
                }

                if(StepCounter>=0){

                    mstepView.setText(StepCounter + "");
                    mcaloriesView.setText(String.format("%.2f", CaloriesCalulatorFromSteps(mUser.getHeight_in_cm(),mUser.getWeight(),StepCounter)));
                    mdistanceView.setText(String.format("%.1f",getDistanceNow(StepCounter,mUser.getHeight_in_cm())));
                    SaveSharedPreference.setUserJourneySteps(getContext(),StepCounter);
                    SaveSharedPreference.setUserJourneyDistance(getContext(),String.format("%.1f",getDistanceNow(StepCounter,mUser.getHeight_in_cm())));
                    SaveSharedPreference.setUserJourneyCalories(getContext(),String.format("%.2f", CaloriesCalulatorFromSteps(mUser.getHeight_in_cm(),mUser.getWeight(),StepCounter)));
                }
                /*getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {



                            //startGPSService();


                    }


                });*/
            }
            } catch(Exception ex){
                Log.i("MapStepsEx", ex.toString());
            }


    }

    public double CaloriesCalulatorFromSteps(double height ,double weight,double stepsCount){


        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);

        strip = height * 0.415;

        stepCountMile = 160934.4 / strip;

        conversationFactor = CaloriesBurnedPerMile / stepCountMile;

        CaloriesBurned = stepsCount * conversationFactor;

        return CaloriesBurned;
    }


    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                LatLng latLng = mMap.getCameraPosition().target;
                Geocoder geocoder = new Geocoder(getContext());

                try {
                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        String locality = addressList.get(0).getAddressLine(0);
                        String country = addressList.get(0).getCountryName();
                        if (!locality.isEmpty() && !country.isEmpty())
                            //resutText.setText(locality + "  " + country);
                            Toast.makeText(getContext(), locality  + country, Toast.LENGTH_SHORT).show();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
    }
    //function to determine the distance run in kilometers using average step length for men and number of steps
    //standard hight in CM = 100; for male
    public float getDistanceRun(long steps,int hightCm){
        float distance = (float)(steps*hightCm)/(float)100000;
        float distanceMeters = distance*1000;
        return distanceMeters;
    }
    public float getDistanceNow(long steps,double hightCM){
        double stepLenght = (hightCM * 0.415);
        float Distance = (float) (stepLenght * steps);
        return Distance/100;
    }
    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }



    public void DialogeBoxContestDate(){

        try{
            LayoutInflater li = LayoutInflater.from(getContext());
            View promptsView = li.inflate(R.layout.dialog_box_contest_date_activity, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setView(promptsView);

            final TextView contestStartTime = (TextView) promptsView.findViewById(R.id.textView1);

            final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
            final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());

            ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();
            Realm_User mUser = provideUserLocal(getContext());
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {

                // mUser.getContestStartDate()
                contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(CompitionDate.getStartDate(), currentDateandTime);


                contestStartTime.setText(getResources().getString(R.string.Competition_Start_In)+" "+ contestWeekDayTimeModel.getDays()+" "+getResources().getString(R.string.Competition_Time_Ticker_Days)+" "+ contestWeekDayTimeModel.getHours()+" "+getResources().getString(R.string.Competition_Time_Ticker_Hours)+" "+ contestWeekDayTimeModel.getMiniutes()+" "+getResources().getString(R.string.Competition_Time_Ticker_Minutes));

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
            alertDialog.setCanceledOnTouchOutside(true);
            alertDialog.show();
        }catch (Exception ex){

        }

    }
    private void sendNotification(/*String title*/int star/*, String messageBody*/) {
        int starInc = mStarClicked - mService.getLastStarPickedNumberActual();
        String Starstr = String.valueOf(starInc);
        String title = getResources().getString(R.string.MoreModule_You_Got_Notification)+star +" \u2b50";
        String messageBody = getResources().getString(R.string.MoreModule_Remaining_are_Notification)+Starstr +" \u2b50";
        Intent notificationIntent = new Intent(getContext(), MainLeaderActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(NAVIGATING_FROM_TAG,NAVIGATING_FROM_VALUE);
        notificationIntent.putExtras(bundle);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pIntent = PendingIntent.getActivity(getContext(), 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
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
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "005")
                .setSmallIcon(R.drawable.ic_app_launch)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        notificationManager.notify(003, builder.build());
    }

    private void sendNotificationCongratulations(/*String title*/int star/*, String messageBody*/) {

        String title = getResources().getString(R.string.MoreModule_Congratulations_Notification)+star+" \u2b50";
        String messageBody = "";
        Intent notificationIntent = new Intent(getContext(), MainLeaderActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(NAVIGATING_FROM_TAG,NAVIGATING_FROM_VALUE);
        notificationIntent.putExtras(bundle);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pIntent = PendingIntent.getActivity(getContext(), 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
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
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "005")
                .setSmallIcon(R.drawable.ic_app_launch)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setContentIntent(pIntent)
                .setAutoCancel(true);

        notificationManager.notify(003, builder.build());
    }
}
