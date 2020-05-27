package com.kampen.riksSe.leader.activity.fragments.home.addactivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.facebook.stetho.server.http.HttpStatus;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.api.remote_api.Generic_Result;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.account.editprofile.EditPofileSimpleActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.QuestionResponse;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.DailyActivityAdapter;

import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.DailyDiaryAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.MotivationVideosAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.RecyclerItemClickListener;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DailyDiaryQuestionModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
//import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepsProvider;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepsPreference;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.UserJourneyData;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.PermissionUtils;
import com.kampen.riksSe.leader.activity.fragments.map.tracking.SimpleLocation;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.GPS_Util;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


import in.mayanknagwanshi.imagepicker.imagePicker.ImagePicker;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static android.content.Context.MODE_PRIVATE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
import static androidx.core.content.PermissionChecker.checkSelfPermission;
import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_CALORIES;
import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_TIME;
import static com.kampen.riksSe.data_manager.Repository.STARS_COUNT;
import static com.kampen.riksSe.data_manager.Repository.STEPS_COUNT;
import static com.kampen.riksSe.data_manager.Repository.USERID;
import static com.kampen.riksSe.utils.Constants.encodeToBase64;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.DialogeBoxContestEndDate;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeForUI;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;


public class ActivityFragment extends Fragment implements /*StepsProvider.StepsResult ,*/StepCountingService.StepsCountResult, ActivityFragContract.View,ActivityCompat.OnRequestPermissionsResultCallback, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, View.OnTouchListener {


    private ImagePicker imagePicker;

    private RecyclerView dailyPickRecyclerView, motivationVideosRV,dailyDiaryQuestionRV;

    private DailyActivityAdapter dailyPicAdapter;

    private MotivationVideosAdapter motivationVideosAdapter;

    private DailyDiaryAdapter dailyDiaryAdapter;

    private LineChart chart;

    private ImageView mDailyPick;

    private TextView locationName, dateTime, userName, TodayActivityHeading;

    public final static String TAG_ = "ActivityDaily Fragment";

    private ImageView mProfileImage;

   // private StepsProvider mStepsProvider;

    private StepCountingService stepCountingService;

    VideoView videoView = null;

    private TextView stepsCountValue,stepsUnit, StarNum, calNum,calUnit,weightNum,WaistNum, distanceNum,distanceUnit;

    CircularProgressBar circularProgressBar;

    ActivityFragPresenter mActivityFragPresenter;


    private String tempLocationName = "";

    private Bitmap DailyBitmap;

    private String tempTimeDate = "";

    private String Lat_temp = "";

    private Bitmap CurrentActivityImage;
    private File CurrentActivityFile;

    private String Long_temp = "";

    private Realm_User mUser;

    private boolean running = false;

    private UserJourneyData mJUser;

    String todayWeight,todayWaist;

    SimpleLocation simpleLocation = null;

    private ActivityFragPresenter activityFragPresenter;

    SwipeRefreshLayout pullToRefresh;

    final static double walkingFactor = 0.57;

    static double CaloriesBurnedPerMile;

    static double strip;

    static double stepCountMile; // step/mile

    static double conversationFactor;

    static double CaloriesBurned;

    static String activityCalories;

    static String activityDistance;

    static NumberFormat formatter = new DecimalFormat("#0.00");

    static double distance;

    EditText weightEditTxt;

    private MediaPlayer mediaPlayer;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;

    File finalFile;

    private AVLoadingIndicatorView triangleProgressView;
    String MotivationVideo, MotivationVideoPic;
    View profileView;
    private TextView timeTextView, timeTextView1;
    public ImageView playBtn, motivationThumpline;
    View MotivationView;

    List<MotivationalVideo> MotivationVideoDataV3 = null;


    List<activityAdapterListModel> activityDailySyncData = null;


    int ChatValueCounter;

    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    Boolean AppKilledState;

    private int milestoneStep=0;

    int stepsValue;

    Context mContext;
    ScrollView mScrollView;

    View mCounterlayout,mMiddlelayout;
    MediaController mediaController;
    private ImageView mReplayVideo,mNextVideo;
    int MotivationVideoPosition;
    View noActivityDataView;

    Button mThumpUP,mThumbDown;
    EditText mDayDescriptionText;
    View mRoot;
    Boolean thumbStatus;
    View mDailyDiaryLayout;
    Button mStepsSettings;




    boolean stepCounterSensorstatus;

    private FirebaseAnalytics mFirebaseAnalytics;
    public int StepCountfinal;

    public ActivityFragment() {


    }
    static IntentFilter  s_intentFilter;
    static {
        s_intentFilter = new IntentFilter();
        /*s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_RESTARTED);*/
        s_intentFilter.addAction(Intent.ACTION_DATE_CHANGED);
    }

    static IntentFilter  s_intentFilter1;
    static {
        s_intentFilter1 = new IntentFilter();
        /*s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_RESTARTED);*/
        s_intentFilter1.addAction(Intent.ACTION_SHUTDOWN);
    }

    static IntentFilter  s_intentFilter_screen_open;
    static {
        s_intentFilter_screen_open = new IntentFilter();
        /*s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_PACKAGE_RESTARTED);*/
        s_intentFilter_screen_open.addAction(Intent.ACTION_SCREEN_ON);
    }



    public static ActivityFragment newInstance() {
        ActivityFragment fragment = new ActivityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_activity, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activityFragPresenter = new ActivityFragPresenter(ActivityFragment.this, mContext);
        mUser = activityFragPresenter.provideUserLocal(mContext);

        dailyPickRecyclerView = view.findViewById(R.id.dailyPicRV);
        motivationVideosRV = view.findViewById(R.id.motivatnaltxtRV);
        dailyDiaryQuestionRV = view.findViewById(R.id.checklistRV);
        mDailyPick = view.findViewById(R.id.dailypick);
        TodayActivityHeading = view.findViewById(R.id.textView5);
        stepsCountValue = view.findViewById(R.id.stepsCountValue);
        stepsUnit = view.findViewById(R.id.StepsUnit);
        weightNum = view.findViewById(R.id.weightNumber);
        WaistNum = view.findViewById(R.id.waistNumber);
        calNum = view.findViewById(R.id.caloriesNumber);
        calUnit = view.findViewById(R.id.CalUnit);
        distanceUnit = view.findViewById(R.id.DisUnit);
        locationName = view.findViewById(R.id.locationName1);
        distanceNum = view.findViewById(R.id.distanceNumber);
        dateTime = view.findViewById(R.id.datetime1);
        StarNum = view.findViewById(R.id.starNumber);
        mProfileImage = view.findViewById(R.id.profile_image);
        userName = view.findViewById(R.id.textView6);
        profileView = view.findViewById(R.id.profilelayout);
        playBtn = view.findViewById(R.id.playMotivationVideo);
        mCounterlayout = view.findViewById(R.id.counterlayout);
        mMiddlelayout = view.findViewById(R.id.middlelayout);
        MotivationView = view.findViewById(R.id.motivationParentView);
        mScrollView = view.findViewById(R.id.scrollView);
        mRoot = view.findViewById(R.id.root);
        videoView = view.findViewById(R.id.motivatnalVideo);
        videoView = view.findViewById(R.id.motivatnalVideo);
        motivationThumpline = view.findViewById(R.id.imgMotivationVideo);
        mReplayVideo=view.findViewById(R.id.replayVideo);
        mNextVideo=view.findViewById(R.id.nextVideo);
        noActivityDataView=view.findViewById(R.id.NoDataView1);
        triangleProgressView = view.findViewById(R.id.triangleProgressView);
        mThumpUP = view.findViewById(R.id.thumbUp);
        mThumbDown = view.findViewById(R.id.thumbDown);
        mDayDescriptionText = view.findViewById(R.id.dayDescriptionText);
        mDailyDiaryLayout = view.findViewById(R.id.checklistlayout);
        mStepsSettings = view.findViewById(R.id.stepsSettings);


        mediaController = new MediaController(getContext());
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

        String currentDateandTime1 = sdf1.format(new Date());
        Competition CompitionDate = Repository.getCompitionDate();
      /*  if(CompitionDate!=null){
            if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                Boolean ContestStatus = getCompitionStartDate(mContext,CompitionDate.getStartDate(),currentDateandTime1);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),CompitionDate.getmEndDate(),currentDateandTime1);

                if(ContestStatus && !ContestEndStatus){
                    mCounterlayout.setVisibility(View.VISIBLE);
                    mMiddlelayout.setVisibility(View.VISIBLE);
                    mDailyDiaryLayout.setVisibility(View.VISIBLE);
                    startGPSService();
                }else if(ContestEndStatus){
                    mCounterlayout.setVisibility(View.GONE);
                    mMiddlelayout.setVisibility(View.GONE);
                    mDailyDiaryLayout.setVisibility(View.GONE);
                }else if(!ContestStatus){
                    mCounterlayout.setVisibility(View.GONE);
                    mMiddlelayout.setVisibility(View.GONE);
                    mDailyDiaryLayout.setVisibility(View.GONE);
                }
            }else{
                mCounterlayout.setVisibility(View.GONE);
                mMiddlelayout.setVisibility(View.GONE);
                mDailyDiaryLayout.setVisibility(View.GONE);
            }
        }else{
            mCounterlayout.setVisibility(View.GONE);
            mMiddlelayout.setVisibility(View.GONE);
            mDailyDiaryLayout.setVisibility(View.GONE);
        }*/




/*        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addApi(Fitness.HISTORY_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .build();


        GetGoogleFitTodaysteps getGoogleFitTodaysteps = new GetGoogleFitTodaysteps();
        getGoogleFitTodaysteps.execute();*/

    /*    oAuthPermissionsApproved();
        subscribe();
        ReadStepsData();*/


        try {
            MotivationVideoDataV3 = activityFragPresenter.getMotivationVideosV3();


            activityDailySyncData = activityFragPresenter.getSyncedActivityData(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
            if(activityDailySyncData.size()>0){
                noActivityDataView.setVisibility(View.GONE);
                dailyPickRecyclerView.setVisibility(View.VISIBLE);
                dailyPicAdapter = new DailyActivityAdapter(mContext, activityDailySyncData);

                dailyPickRecyclerView.setLayoutManager(Constants.getHorizontalLayoutManager(getActivity()));

                dailyPickRecyclerView.setAdapter(dailyPicAdapter);

                for(int i =0; i<activityDailySyncData.size();i++){
                    if(activityDailySyncData.get(i).getCurrentWeek().equals("Present")){
                        dailyPickRecyclerView.getLayoutManager().scrollToPosition(i);
                        break;
                    }
                }
            }else{
                noActivityDataView.setVisibility(View.VISIBLE);
                dailyPickRecyclerView.setVisibility(View.GONE);
            }


            if(MotivationVideoDataV3.isEmpty()){
                motivationThumpline.setImageResource(0);
                motivationVideosRV.setVisibility(View.GONE);
            } else {
                motivationVideosRV.setVisibility(View.VISIBLE);
                motivationThumpline.setVisibility(View.VISIBLE);

                MotivationVideo = MotivationVideoDataV3.get(0).getMediaUrl();
                MotivationVideoPosition=0;
                MotivationVideoPic = MotivationVideoDataV3.get(0).getMediaThumb();

                loadImage(motivationThumpline, MotivationVideoPic);

                motivationVideosAdapter = new MotivationVideosAdapter(mContext, MotivationVideoDataV3);

                motivationVideosRV.setLayoutManager(Constants.getHorizontalLayoutManager(getActivity()));

                motivationVideosRV.setAdapter(motivationVideosAdapter);
            }

        } catch (Exception e) {
            Log.i("MotivatEX",e.toString());
        }




        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        String currentDateandTime = sdf.format(new Date());
        ShowStepsCounterView(CompitionDate);

        //int todaySteps = Math.abs(SaveSharedPreference.getUserTodaySteps(mContext));
     /*   //List<activityAdapterListModel> activityDailySyncData = activityFragPresenter.getSyncedActivityData(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
        int todaySteps = 6690;
        //activityAdapterListModel todayActivity =  activityFragPresenter.GetTodayActivityV1(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
        *//*if(todayActivity!=null){
            todaySteps = todayActivity.getmSteps();
        }else{
            todaySteps = 0;
        }*//*
            stepsCountValue.setText(Math.abs(todaySteps)+"");
            if(todaySteps < 1000){
                stepsCountValue.setText(todaySteps+"");
            }else if(todaySteps < 1000000){
               // Log.i("StepValue",todaySteps+"");
                double stepsValue = Math.abs(todaySteps)/1000.0;
                stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"K");
                Log.i("steps", String.valueOf(todaySteps));

            }else if(todaySteps < 100000000){
                double stepsValue = Math.abs(todaySteps)/1000000.0;
                stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"M");
                Log.i("steps", String.valueOf(todaySteps));
            }else{
                double stepsValue = Math.abs(todaySteps)/100000000.0;
                stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"B");
                Log.i("steps", String.valueOf(todaySteps));
            }

            String CalCount = String.format("%.0f", Math.abs(CaloriesCalulatorFromSteps(mUser.getHeight_in_cm(),mUser.getWeight(),todaySteps)));
            double CalValue = Integer.parseInt(CalCount);
            if(CalValue >999){
                //Log.i("CalValue",CalValue+"");
                calUnit.setText("(Kcal)");
                double CaloriesValue = CalValue/1000.0;
                calNum.setText(String.format("%.2f",CaloriesValue)+""+"K");
                Log.i("CAl", String.valueOf(CaloriesValue));

            }else{
                //Log.i("CalValue",CalValue+"");
                calUnit.setText("(Kcal)");
                calNum.setText(CalValue+"");
                Log.i("CAl", String.valueOf(CalValue));
            }
            String DisCount = String.format("%.0f",Math.abs(getDistanceNow(todaySteps,mUser.getHeight_in_cm())));
            int DisValue = Integer.parseInt(DisCount);
            if(DisValue > 999){
                //Log.i("DistanceValue",DisValue+"");
                distanceUnit.setText("(Km)");
                double DistanceValue = DisValue/1000.0;
                distanceNum.setText(String.format("%.2f",DistanceValue)+""+"K");
                Log.i("DistanceValue", String.valueOf(DisCount));
                //SaveSharedPreference.setDistance(mContext, String.format("%.1f",DistanceValue)+"");
            }else{
                distanceUnit.setText("(Km)");
                double DistanceValue = DisValue/1000.0;
                distanceNum.setText(String.format("%.2f",DistanceValue)+"");
                Log.i("DistanceValue", String.valueOf(DisCount));
            }

            SaveSharedPreference.setDistance(mContext, getDistanceNow(todaySteps,mUser.getHeight_in_cm())+"");*/
            /*
            tring todayWeight = SaveSharedPreference.getWeightToday(mContext);
            String todayWaist = SaveSharedPreference.getWaistToday(mContext);

            if(!todayWeight.equals("0.0")){
                weightNum.setText(todayWeight);
            }else{
                weightNum.setText("N/A");
            }
            if(!todayWaist.equals("0")){
                WaistNum.setText(todayWaist);
            }else{
                WaistNum.setText("N/A");
            }
            */


        String StarDBValue = mUser.getStars_count();

        String UserLogInDate = SaveSharedPreference.getUserLogInDate(mContext);

        if (UserLogInDate.equals(currentDateandTime)) {

            if (mUser.getSteps_count() == null) {
                milestoneStep = 0;
            } else {
                milestoneStep = Math.abs(Integer.parseInt(mUser.getSteps_count()));
            }
            if (StarDBValue == null) {
                StarNum.setText("0");
            } else {
                StarNum.setText(Math.abs(Integer.parseInt(mUser.getStars_count())) + "");
            }

            String activityImage = null;
            String activityLocation = null;
            String activityTime = null;
            String activityWeight = null;
            String activityWaist = null;

            try{
                for(int i =0; i<activityDailySyncData.size();i++){
                    SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
                    SimpleDateFormat sdf3 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTimeStart = sdf2.format(new Date());
                    String currentDateandTimeEnd = sdf2.format(new Date());
                    Date currentDateandTimeStartRange = sdf3.parse(currentDateandTimeStart+" 00:00:00");
                    Date currentDateandTimeEndRange = sdf3.parse(currentDateandTimeEnd+" 23:59:59");
                    Date ActiviyDate = sdf3.parse(activityDailySyncData.get(i).getmDate());
                    if((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))){
                        activityImage = activityDailySyncData.get(i).getmMediaImage();
                        activityLocation = activityDailySyncData.get(i).getmLocationAddress();
                        activityTime = convertTimeForUI(activityDailySyncData.get(i).getmDate());
                        activityWeight = String.valueOf(activityDailySyncData.get(i).getmWeight());
                        activityWaist = String.valueOf(activityDailySyncData.get(i).getmWaist());
                    }
                }
            }catch (Exception ex){

            }


            if(activityImage !=null || activityLocation !=null || activityTime !=null || activityWeight !=null || activityWaist !=null){

                loadImageDailyActivity(mDailyPick,activityImage);
                locationName.setText(activityLocation);
                dateTime.setText(activityTime);
                if(!activityWeight.equals("0.0")){
                    weightNum.setText(activityWeight);
                }else{
                    weightNum.setText(getResources().getString(R.string.General_NA));
                }
                if(!activityWaist.equals("0")){
                    WaistNum.setText(activityWaist);
                }else{
                    WaistNum.setText(getResources().getString(R.string.General_NA));
                }
            }

        } else {
            // StepsResetDAteChangedUI(currentDateandTime);
        }

        DailyDiaryQuestionModel dailyDiaryQuestionModel = activityFragPresenter.getDailyQuestions();

        if(dailyDiaryQuestionModel.getmQuestions()!=null){

            dailyDiaryAdapter = new DailyDiaryAdapter(mContext, dailyDiaryQuestionModel.getmQuestions());

            dailyDiaryQuestionRV.setLayoutManager(Constants.getVerticalLayoutManager(getActivity()));

            dailyDiaryQuestionRV.setAdapter(dailyDiaryAdapter);

        }
        if(dailyDiaryQuestionModel.ismDayStatus()!=null){
            if(dailyDiaryQuestionModel.ismDayStatus()){
                thumbStatus = true;
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumpUP.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                }*/
                mThumpUP.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);
            }else if(!dailyDiaryQuestionModel.ismDayStatus()){
                thumbStatus = false;
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumbDown.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                }*/
                mThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
                mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
            }
        }else {
            thumbStatus = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mThumpUP.setBackgroundTintList(null);
                mThumbDown.setBackgroundTintList(null);
                mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
                mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);
            }

        }
        if(dailyDiaryQuestionModel.getmDayDescription()!=null){
            mDayDescriptionText.setText(dailyDiaryQuestionModel.getmDayDescription());
        }else{
            mDayDescriptionText.setText("");
        }
        /*mDayDescriptionText.setScroller(new Scroller(mContext));
        mDayDescriptionText.setVerticalScrollBarEnabled(true);
        mDayDescriptionText.setMovementMethod(new ScrollingMovementMethod());


        mScrollView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mDayDescriptionText.getParent().requestDisallowInterceptTouchEvent(false);

                return false;
            }
        });
        mDayDescriptionText.setOnTouchListener(new View.OnTouchListener() {

            @Overrideh
            public boolean onTouch(View v, MotionEvent event) {

                mDayDescriptionText.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });*/

        pullToRefresh = view.findViewById(R.id.pullToRefresh);

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //ProgressManager.showProgress(mContext, "Vänta ...");

                MainLeaderActivity activity = (MainLeaderActivity) mContext;

                activity.getAllDataCallFromFragment();

            }
        });


        videoView.setOnCompletionListener(this);
        videoView.setOnPreparedListener(this);
        videoView.setOnTouchListener(this);


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    //motivationVideosAdapter.MotivationVideosURL;
                    videoView.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.GONE);
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();


                    mediaController.setAnchorView(videoView);
                    mediaController.setMediaPlayer(videoView);
                    videoView.setMediaController(mediaController);


                    videoView.setVideoPath(MotivationVideo);
                    //videoView.setVideoURI(Uri.parse(dailyVideo.getMediaVideo()));
                    videoView.start();

                } catch (Exception ex) {

                }

            }
        });

        mStepsSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, StepsPreference.class);
                startActivity(i);
            }
        });


        motivationVideosRV.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        try {

                            videoView.setVisibility(View.GONE);
                            MotivationVideoDataV3 = activityFragPresenter.getMotivationVideosV3();
                            playBtn.setVisibility(View.GONE);
                            motivationThumpline.setVisibility(View.VISIBLE);
                            triangleProgressView.show();

                            videoView.stopPlayback();
                            videoView.clearAnimation();
                            videoView.clearFocus();

                            MotivationVideo = MotivationVideoDataV3.get(position).getMediaUrl();
                            MotivationVideoPic = MotivationVideoDataV3.get(position).getMediaThumb();
                            MotivationVideoPosition=position;
                            loadImage(motivationThumpline, MotivationVideoPic);
                            mediaController = new MediaController(getContext());
                            mediaController.setAnchorView(MotivationView);
                            videoView.setVisibility(View.VISIBLE);
                            videoView.setMediaController(mediaController);
                            videoView.setVideoPath(MotivationVideo);
                            videoView.start();
                            mediaController.hide();


                        } catch (Exception ex) {

                        }

                    }
                })
        );

        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
               // int scrollY = rootScrollView.getScrollY(); // For ScrollView
                //int scrollX = rootScrollView.getScrollX(); // For HorizontalScrollView
                // DO SOMETHING WITH THE SCROLL COORDINATES

                mediaController.hide();


            }
        });


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {

                mediaPlayer = mp;


                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        motivationThumpline.setVisibility(View.GONE);
                        videoView.setVisibility(View.VISIBLE);
                        triangleProgressView.hide();
                    }
                });


                running = true;
                final int duration = videoView.getDuration();

               /* new Thread(new Runnable() {
                    public void run() {
                        do{
                            *//*timeTextView.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    timeTextView.setText(time+"");

                                }
                            });*//*
                 *//*  timeTextView1.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    timeTextView1.setText(time+"");

                                }
                            });*//*

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!running) break;
                        }
                        while(videoView.getCurrentPosition()<duration);
                    }
                }).start();*/
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                try {
                    videoView.stopPlayback();
                    if (MotivationVideoDataV3.isEmpty()) {

                    } else {



                        //loadImage(motivationThumpline, MotivationVideoPic);
                        mReplayVideo.setVisibility(View.VISIBLE);
                        mNextVideo.setVisibility(View.VISIBLE);
                        //playBtn.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {

                    Log.i("Motivation W_Video Log", e.toString());
                }

            }
        });

        mReplayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motivationThumpline.setVisibility(View.VISIBLE);
                MotivationVideo = MotivationVideoDataV3.get(MotivationVideoPosition).getMediaUrl();

                MotivationVideoPic = MotivationVideoDataV3.get(MotivationVideoPosition).getMediaThumb();

                loadImage(motivationThumpline, MotivationVideoPic);
                mReplayVideo.setVisibility(View.GONE);
                mNextVideo.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.GONE);
                triangleProgressView.setVisibility(View.VISIBLE);
                triangleProgressView.show();


                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);


                videoView.setVideoPath(MotivationVideo);
                //videoView.setVideoURI(Uri.parse(dailyVideo.getMediaVideo()));
                videoView.start();
            }
        });

        mNextVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MotivationVideoPosition<MotivationVideoDataV3.size()-1){
                    MotivationVideoPosition+=1;
                }else{
                    MotivationVideoPosition=0;
                }

                motivationThumpline.setVisibility(View.VISIBLE);


                MotivationVideo = MotivationVideoDataV3.get(MotivationVideoPosition).getMediaUrl();

                MotivationVideoPic = MotivationVideoDataV3.get(MotivationVideoPosition).getMediaThumb();

                loadImage(motivationThumpline, MotivationVideoPic);
                mReplayVideo.setVisibility(View.GONE);
                mNextVideo.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.GONE);
                triangleProgressView.setVisibility(View.VISIBLE);
                triangleProgressView.show();


                mediaController.setAnchorView(videoView);
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);


                videoView.setVideoPath(MotivationVideo);
                //videoView.setVideoURI(Uri.parse(dailyVideo.getMediaVideo()));
                videoView.start();
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /*Intent fullView = new Intent(mContext,FullscreenVideo.class);
                fullView.putExtra("VideoURL",MotivationVideo);
                fullView.putExtra("SeekPosition",videoView.getCurrentPosition());
                startActivity(fullView);*/

            }
        });


        imagePicker = new ImagePicker();

      /*  mStepsProvider = new StepsProvider(getActivity());

        mStepsProvider.setStepListener(ActivityFragment.this);
*/




        mDailyPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTime = sdf.format(new Date());
                Competition CompitionDate = Repository.getCompitionDate();



               if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                   Boolean ContestStatus = getCompitionStartDate(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                   Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);

                   if(ContestStatus && !ContestEndStatus){

                       ProgressManager.showProgress(mContext, "...");
                       ProgressManager.hideProgress();
                       enableMyLocation();
                       ProgressManager.showProgress(mContext, "...");
                       ProgressManager.hideProgress();

                   }else if(ContestEndStatus){
                       DialogeBoxContestEndDate(mContext,mUser);
                   } else{

                       DialogeBoxContestDate();

                   }
               }else{
                   //DialogeBoxContestDate();
               }

            }
        });

        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditPofileSimpleActivity.class);
                startActivity(intent);
            }
        });

        mRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mDayDescriptionText.clearFocus();
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }catch (Exception e){

                }
            }
        });




        mDayDescriptionText.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.
                                try{
                                    mDayDescriptionText.clearFocus();
                                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                }catch (Exception e){

                                }

                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
        );

        mDayDescriptionText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try{
                    if (hasFocus) {
                        //got focus
                        //Toast.makeText(mContext, "KeyBoard Open Day", Toast.LENGTH_SHORT).show();
                    } else {
                        //lost focus
                        //Toast.makeText(mContext, "KeyBoard Close Day", Toast.LENGTH_SHORT).show();
                        String mDayDescription = mDayDescriptionText.getText().toString();
                        if(mDayDescription.isEmpty()){
                            mDayDescription=null;
                        }
                        List<QuestionResponse> questionResponseList =new ArrayList();
                        for(int i=0;i<dailyDiaryAdapter.mDailyDiaryQuestionArrayList.size();i++){
                            QuestionResponse questionResponse =new QuestionResponse();
                            questionResponse.setId(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).getmId());
                            questionResponse.setResponse(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).ismResponse());
                            questionResponseList.add(questionResponse);
                        }
                        activityFragPresenter.AddDailyActivtyV3(mUser,mDayDescription,thumbStatus,questionResponseList);
                    }
                }catch (Exception e){

                }
            }
        });
        mThumpUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    thumbStatus = true;
                  /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mThumpUP.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                        mThumbDown.setBackgroundTintList(null);
                    }*/
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);
                    String mDayDescription = mDayDescriptionText.getText().toString();
                    if(mDayDescription.isEmpty()){
                        mDayDescription=null;
                    }
                    List<QuestionResponse> questionResponseList =new ArrayList();
                    for(int i=0;i<dailyDiaryAdapter.mDailyDiaryQuestionArrayList.size();i++){
                        QuestionResponse questionResponse =new QuestionResponse();
                        questionResponse.setId(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).getmId());
                        questionResponse.setResponse(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).ismResponse());
                        questionResponseList.add(questionResponse);
                    }
                    activityFragPresenter.AddDailyActivtyV3(mUser,mDayDescription,true, questionResponseList);
                }catch (Exception e){

                }
            }
        });

        mThumbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    thumbStatus = false;
                   /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mThumbDown.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                        mThumpUP.setBackgroundTintList(null);
                    }*/
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
                    String mDayDescription = mDayDescriptionText.getText().toString();
                    if(mDayDescription.isEmpty()){
                        mDayDescription=null;
                    }
                    List<QuestionResponse> questionResponseList =new ArrayList();
                    for(int i=0;i<dailyDiaryAdapter.mDailyDiaryQuestionArrayList.size();i++){
                        QuestionResponse questionResponse =new QuestionResponse();
                        questionResponse.setId(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).getmId());
                        questionResponse.setResponse(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).ismResponse());
                        questionResponseList.add(questionResponse);
                    }
                    activityFragPresenter.AddDailyActivtyV3(mUser,mDayDescription,false, questionResponseList);
                }catch (Exception e){

                }
            }
        });


    }

    private void setUser() {

        if (mUser != null) {
            Constants.setProfileImage(mUser.getProfile_image(), mUser.getProfilePicData(), mProfileImage, getContext());
        }
    }

    private void loadImage(final ImageView imageView, final String imageUrl) {

       /* Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
                .build();*/
        GlideApp
                .with(mContext)
                .load(imageUrl)
              /*  .skipMemoryCache( true )
                .diskCacheStrategy(DiskCacheStrategy.NONE)*/
                //.error(R.drawable.avatar_new)
                .into(imageView);

        /*Picasso.get()
                .load(imageUrl)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")) {
                            updatedImageUrl = imageUrl.replace("https", "http");
                        } else {
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });*/
    }

    private void loadImageDailyActivity(final ImageView imageView, final String imageUrl) {

       /* Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
                .build();*/
        GlideApp
                .with(mContext)
                .load(imageUrl)
                /*.skipMemoryCache( true )
                .diskCacheStrategy(DiskCacheStrategy.NONE)*/
                .error(R.drawable.icon_profile_pics)
                .into(imageView);

        /*Picasso.get()
                .load(imageUrl)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")) {
                            updatedImageUrl = imageUrl.replace("https", "http");
                        } else {
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });*/
    }

    private void chatInIt() {
        chart.setViewPortOffsets(0, 0, 0, 0);
        //chart.setBackgroundColor(Color.rgb(20, 20, 20));

        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);


        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
        //y.setTypeface(tfLight);
        y.setLabelCount(0, false);
        y.setTextColor(Color.TRANSPARENT);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.GREEN);

        chart.getAxisRight().setEnabled(false);


        chart.getLegend().setEnabled(false);

        chart.animateXY(2000, 2000);


    }


    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int STEPCOUNT_PERMISSION_REQUEST_CODE = 1;

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            /*|| ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED*/) {
            SaveSharedPreference.setLocationPermissionForeground(mContext,false);
            SaveSharedPreference.setCameraPermission(mContext,false);
            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA/*,Manifest.permission.RECORD_AUDIO*/}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {


            //isReadStoragePermissionGranted();

            startPickingImage();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mContext = context;

    }


    @Override
    public void onDetach() {
        super.onDetach();
        dailyPickRecyclerView = null;
        dailyPicAdapter = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        AppKilledState = false;
        SaveSharedPreference.setAppKilledState(mContext, AppKilledState);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mYourBroadcastReceiver,
                new IntentFilter("com.tutorialspoint.CUSTOM_INTENT"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(DateChangeReceiver,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_ACTIVITY_DATE_CHANGE"));
    /*    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiverSteps,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_STEPSSENSOR"));*/
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiverSteps,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_STEPS"));

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(VideoOFfclick,
                new IntentFilter("com.rikskampen.CUSTOM_INTENT_ViDEO_OFF_CLICK"));

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(CountDownCompitition,
                new IntentFilter("com.tutorialspoint.CUSTOM_COMPITITION_COUNTER"));

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(DailyDiaryQuestionclick,
                new IntentFilter("com.tutorialspoint.CUSTOM_DAILY_DIARY_QUESTION"));

     /*   LocalBroadcastManager.getInstance(getActivity()).registerReceiver(DateChangeUIReset,
                new IntentFilter("com.tutorialspoint.CUSTOM_DATE_CHANGE_UI_RESET"));*/

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(CompitionCounterFinish,
                new IntentFilter("com.tutorialspoint.CUSTOM_COMPITITION_FINISH"));

        //LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiverSteps,s_intentFilter1);

        mContext.registerReceiver(m_timeChangedReceiver, s_intentFilter);

       // mContext.registerReceiver(ScreenOpen, s_intentFilter_screen_open);


    }





    @Override
    public void onStop() {

        super.onStop();



        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mYourBroadcastReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(DateChangeReceiver);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(VideoOFfclick);

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(CountDownCompitition);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(DailyDiaryQuestionclick);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(PhoneRest);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(CompitionCounterFinish);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiverSteps);
        mContext.unregisterReceiver(m_timeChangedReceiver);

    }

/*    private final GCoreWakefulBroadcastReceiver ScreenOpen = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(Intent.ACTION_SCREEN_ON) *//*||
                    action.equals(Intent.ACTION_TIMEZONE_CHANGED)*//*) {
                //Toast.makeText(mContext, "Screen On Servise Lives ", Toast.LENGTH_LONG).show();
                if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                    Competition CompitionDate = Repository.getCompitionDate();
                    if (CompitionDate != null) {
                        if (CompitionDate.getStartDate() != null && CompitionDate.getmEndDate() != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                            String currentDateandTime = sdf.format(new Date());
                            Boolean ContestStatus = getCompitionStartDate(mContext, CompitionDate.getStartDate(), currentDateandTime);
                            Boolean ContestEndStatus = getCompitionStartDate(getContext(), CompitionDate.getmEndDate(), currentDateandTime);
                            if (ContestStatus && !ContestEndStatus) {
                                startGPSService();
                                // getActivity().bindService(new Intent(getContext(), StepCountingService.class), mConnection, Context.BIND_AUTO_CREATE);
                            }
                        }

                    }
                }
            }
        }
    };*/

    private final GCoreWakefulBroadcastReceiver PhoneRest = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here
            final String action = intent.getAction();
            if (action == Intent.ACTION_SHUTDOWN) {
                // Your tasks for shut down
            } else {
                // Your tasks for boot up
               /* PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
                wakeLock.acquire(10*60*1000L);

                Toast.makeText(context, "DeviceReboot Complete", Toast.LENGTH_LONG).show();
                startGPSService();*/
            }



        }
    };

    private final GCoreWakefulBroadcastReceiver CompitionCounterFinish = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here
            SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDateandTime1 = sdf2.format(new Date());

            StepsResetDAteChanged(currentDateandTime1);

        }
    };

    private final GCoreWakefulBroadcastReceiver DateChangeReceiver = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            //Toast.makeText(context, "DateChanged BraodCast Lives UI", Toast.LENGTH_LONG).show();
            StepsResetDAteChangedUI();

            //updateNotify();
            //updateNotifyDiary();

        }
    };

    private final GCoreWakefulBroadcastReceiver m_timeChangedReceiver = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(Intent.ACTION_DATE_CHANGED)) {
               /*SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                String NextDayDateAndTime = sdf.format(new Date());
                String TodayDateAndTime = SaveSharedPreference.getUserLogInDate(context);
                if(!TodayDateAndTime.equals(NextDayDateAndTime)){

                }*/
                StepsResetDAteChangedUI();
                updateNotify();
            }
        }
    };

    private final GCoreWakefulBroadcastReceiver DailyDiaryQuestionclick = new GCoreWakefulBroadcastReceiver()  {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            String mDayDescription = mDayDescriptionText.getText().toString();
            if(mDayDescription.isEmpty()){
                mDayDescription=null;
            }
            List<QuestionResponse> questionResponseList =new ArrayList();
            for(int i=0;i<dailyDiaryAdapter.mDailyDiaryQuestionArrayList.size();i++){
                QuestionResponse questionResponse =new QuestionResponse();
                questionResponse.setId(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).getmId());
                questionResponse.setResponse(dailyDiaryAdapter.mDailyDiaryQuestionArrayList.get(i).ismResponse());
                questionResponseList.add(questionResponse);
            }
            activityFragPresenter.AddDailyActivtyV3(mUser,mDayDescription,thumbStatus, questionResponseList);
        }
    };

    private final BroadcastReceiver VideoOFfclick = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            videoView.pause();
            mediaController.hide();
        }
    };

    private final GCoreWakefulBroadcastReceiver CountDownCompitition = new GCoreWakefulBroadcastReceiver() {
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
                        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                        String currentDateandTime1 = sdf1.format(new Date());
                        SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
                        String currentDateandTime2 = sdf1.format(new Date());
                        Competition CompitionDate = Repository.getCompitionDate();
                        if(CompitionDate!=null) {
                            if (CompitionDate.getStartDate() != null && CompitionDate.getmEndDate() != null) {
                                Boolean ContestStatus = getCompitionStartDate(mContext, convertUTCToLocalTime(CompitionDate.getStartDate()), currentDateandTime1);
                                Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime1);

                                if (ContestStatus && !ContestEndStatus) {
                                    if(days.equals("0") && hours.equals("0") && minutes.equals("0") && seconds.equals("0")){
                                        mCounterlayout.setVisibility(View.VISIBLE);
                                        mMiddlelayout.setVisibility(View.VISIBLE);
                                        StepsResetDAteChanged(currentDateandTime2);
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                            if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                                                if(ContextCompat.checkSelfPermission(mContext,
                                                        Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED){
                                                    if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                                                        startGPSService(mContext);
                                                    }

                                                }else if(ContextCompat.checkSelfPermission(mContext,
                                                        Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
                                                    //ask for permission
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                                        SaveSharedPreference.setStepCounterPermission(mContext,false);
                                                        requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 5);
                                                    }
                                                }
                                            }else{
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                                    if(ContextCompat.checkSelfPermission(mContext,
                                                            Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
                                                        //ask for permission
                                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                                            SaveSharedPreference.setStepCounterPermission(mContext,false);
                                                            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 5);

                                                        }
                                                    }
                                                }
                                            }
                                        }else{
                                            if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                                                startGPSService(mContext);
                                            }
                                        }
                                    }
                                }else if(ContestEndStatus){
                                    mCounterlayout.setVisibility(View.GONE);
                                    mMiddlelayout.setVisibility(View.GONE);
                                    StepsResetDAteChanged(currentDateandTime2);
                                }
                            }
                        }
                    }
                });

            }catch (Exception e){

            }

        }
    };


    private final GCoreWakefulBroadcastReceiver mYourBroadcastReceiver = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            int StarValue = intent.getIntExtra("StarCount", 0);

            StarNum.setText(StarValue + "");

            //SaveSharedPreference.setUserStarCount(mContext,StarValue);

        }
    };

    private final GCoreWakefulBroadcastReceiver mBroadcastReceiverSteps = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try{
               /* PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
                wakeLock.acquire(10*60*1000L);*/

                int steps = intent.getIntExtra("Steps",0);

                Realm_User User = provideUserLocal(mContext);

                if(steps>=0){

                        if(steps < 1000){
                            stepsCountValue.setText(steps+"");
                        }else if(steps < 1000000){
                            double stepsValue = Math.abs(steps)/1000.0;
                            stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"K");
                        }else if(steps < 100000000){
                            double stepsValue = Math.abs(steps)/1000000.0;
                            stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"M");
                        }else{
                            double stepsValue = Math.abs(steps)/100000000.0;
                            stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"B");
                        }
                        String CalCount = String.format("%.0f", Math.abs(CaloriesCalulatorFromSteps(User.getHeight_in_cm(),User.getWeight(),steps)));
                        int CalValue = Integer.parseInt(CalCount);
                        if(CalValue >1000){
                            calUnit.setText("(Kcal)");
                            double CaloriesValue = CalValue/1000.0;
                            calNum.setText(String.format("%.2f",CaloriesValue)+""+"K");
                            //Log.i("calories K ",String.format("%.2f",CaloriesValue)+""+"K" );
                            //Toast.makeText(mContext,String.format("%.2f",CaloriesValue)+""+"K" , Toast.LENGTH_SHORT).show();
                        }
                        else{
                            calUnit.setText("(Kcal)");
                            calNum.setText(CalValue+"");
                            //Log.i("calories",CalValue+"");
                            //Toast.makeText(mContext,CalValue+"" , Toast.LENGTH_SHORT).show();
                        }
                        String DisCount = String.format("%.0f",Math.abs(getDistanceNow(steps,User.getHeight_in_cm())));
                        int DisValue = Integer.parseInt(DisCount);

                        if(DisValue > 1000){
                            distanceUnit.setText("(Km)");
                            double DistanceValue = DisValue/1000.0;
                            distanceNum.setText(String.format("%.2f",DistanceValue)+""+"K");
                            //SaveSharedPreference.setDistance(mContext, String.format("%.1f",DistanceValue)+"");
                        }
                        else{
                            distanceUnit.setText("(M)");
                            distanceNum.setText(DisValue+"");
                            distanceUnit.setText("(Km)");
                            double DistanceValue = DisValue/1000.0;
                            distanceNum.setText(String.format("%.2f",DistanceValue)+"");
                        }


                        //wakeLock.release();
                        //startGPSService();
                }
            }catch (Exception e){
                Log.i("ActivityLocal",e.toString());
         /*       mFirebaseAnalytics = FirebaseAnalytics.getInstance(mContext);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(e.getStackTrace()));
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                stopGPSService(mContext);*/
                //Toast.makeText(mContext, "ActivityLocal", Toast.LENGTH_SHORT).show();
            }
        }
    };



    public void StepsResetDAteChanged(String CurrentDate){

        /*getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {*/

                milestoneStep = 0;
                StarNum.setText("0");
                stepsCountValue.setText("0");
                calNum.setText("0");
                distanceNum.setText("0");
                mDailyPick.setImageResource(R.drawable.icon_profile_pics);
                locationName.setText("");
                dateTime.setText("");
                WaistNum.setText(getResources().getString(R.string.General_NA));
                weightNum.setText(getResources().getString(R.string.General_NA));

                SaveSharedPreference.setUserLogInSteps(mContext,0);
                SaveSharedPreference.setUsertodaySteps(mContext,0);
                SaveSharedPreference.setUserStarCount(mContext,0);
                SaveSharedPreference.setWaistToday(mContext,"0");
                SaveSharedPreference.setWeightToday(mContext,"0");
                SaveSharedPreference.setDailyDate(mContext,"");
                SaveSharedPreference.setDailyImage(mContext,"");
                SaveSharedPreference.setLocation(mContext,"");
                activityFragPresenter.updateStarUserLocal("0",mUser.getId());
                activityFragPresenter.updateStepsUserLocal("0",mUser.getId());
                activityFragPresenter.updateActivityDataLocal(null,null,null,mUser.getId());
                SaveSharedPreference.setUserLogInDate(mContext,CurrentDate);
                SaveSharedPreference.setStepDaySessionDate(mContext,CurrentDate);

/*
            }
        });*/


    }
    public void StepsResetDAteChangedUI(){


        milestoneStep = 0;
        StarNum.setText("0");
        stepsCountValue.setText("0");
        calNum.setText("0");
        distanceNum.setText("0");
        mDailyPick.setImageResource(R.drawable.icon_profile_pics);
        locationName.setText("");
        dateTime.setText("");
        WaistNum.setText("N/A");
        weightNum.setText("N/A");

        mDayDescriptionText.setText("");
        thumbStatus = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mThumpUP.setBackgroundTintList(null);
            mThumbDown.setBackgroundTintList(null);
            mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
            mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);
        }
    }



    public void onAddPicture() {

    }

    public boolean onBackPressed() {

        return false;


    }

    public void startPickingImage() {


        if (GPS_Util.checkIfGPSEnabledOrNot(MainLeaderActivity.context)) {

           /* try {
                imagePicker.withActivity(getActivity()) //calling from activity
                        .chooseFromGallery(false) //default is true
                        .chooseFromCamera(true) //default is true
                        .withCompression(true) //default is true
                        .start();
            } catch (Exception ex) {
                ex.toString();
            }*/
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            /*fileUri = getOutputMediaFileUri();
            intent.putExtra( MediaStore.EXTRA_OUTPUT, fileUri )*/
            startActivityForResult(cameraIntent, CAMERA_REQUEST);

            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                if (checkSelfPermission(mContext,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }*/
            try {
                boolean requireFineGranularity = true;
                boolean passiveMode = true;
                long updateIntervalInMilliseconds = 10 * 60 * 1000;
                boolean requireNewLocation = true;
                simpleLocation = new SimpleLocation(getActivity(), requireFineGranularity, passiveMode, updateIntervalInMilliseconds, requireNewLocation);
            } catch (Exception ex) {
                ex.toString();
            }


            simpleLocation.setListener(new SimpleLocation.Listener() {

                public void onPositionChanged() {


                    getAddressFromLocation(simpleLocation.getLatitude(), simpleLocation.getLongitude(),
                            MainLeaderActivity.context, new GeocoderHandler());


                    simpleLocation.endUpdates();

                }

                @Override
                public void onGPSStatusChanged(boolean status) {

                }

            });

            simpleLocation.beginUpdates();

        }


    }

    private static final String TAG = "LocationAddress";

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onStepsService(int steps) {

    }


    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            String Lat;
            String Long;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    Lat = bundle.getString("lat");
                    Long = bundle.getString("long");
                    break;
                default:
                    locationAddress = "N/A";
                    Lat = String.valueOf(simpleLocation.getLatitude());
                    Long = String.valueOf(simpleLocation.getLongitude());
            }

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());
            String utcTime = convertTimeToUTC();

            if (locationAddress == null) {
                tempLocationName = "N/A";
            } else {
                tempLocationName = locationAddress;
            }

            tempTimeDate = utcTime;
            Lat_temp = Lat;
            Long_temp = Long;


        }
    }


    public static void getAddressFromLocation(final double latitude, final double longitude,
                                              final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;

                try {
                    List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);

                        result = address.getAddressLine(0);

                    }
                } catch (IOException e) {

                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        bundle.putString("lat", String.valueOf(latitude));
                        bundle.putString("long", String.valueOf(longitude));
                        message.setData(bundle);

                    } else {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        bundle.putString("lat", String.valueOf(latitude));
                        bundle.putString("long", String.valueOf(longitude));
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }


    @SuppressLint("WrongConstant")
    public boolean isReadStoragePermissionGranted() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                ProgressManager.showProgress(mContext, "...");
                ProgressManager.hideProgress();
                isWriteStoragePermissionGranted();
                startPickingImage();
                ProgressManager.showProgress(mContext, "...");
                ProgressManager.hideProgress();
                return true;
            } else {


                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);

                return false;
            }
        } else {


            ProgressManager.hideProgress();
            startPickingImage();


            return true;
        }
    }

    @SuppressLint("WrongConstant")
    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


                startPickingImage();
                ProgressManager.hideProgress();
                return true;

            } else {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                ProgressManager.hideProgress();
                return false;

            }

        } else { //permission is automatically granted on sdk<23 upon installation

            startPickingImage();
            ProgressManager.hideProgress();
            return true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //startGPSService();
        ProgressManager.hideProgress();
        AppKilledState = true;
        SaveSharedPreference.setAppKilledState(mContext, AppKilledState);
        //Toast.makeText(mContext, "App Destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        //videoView.resume();
        updateNotify();
        //setUser();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDayDescriptionText.clearFocus();
        ProgressManager.hideProgress();
        //Toast.makeText(mContext, "App Pause", Toast.LENGTH_SHORT).show();
    }
    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE)
        {
           startPickingImage();
        }


    }*/

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);



        ProgressManager.hideProgress();
        try {
            if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {


                ProgressManager.hideProgress();
                if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                        Manifest.permission.ACCESS_FINE_LOCATION) && PermissionUtils.isPermissionGranted(permissions, grantResults,
                        Manifest.permission.CAMERA)) {

                    ProgressManager.hideProgress();
                    //isReadStoragePermissionGranted();


                    SaveSharedPreference.setCameraPermission(mContext,true);
                    SaveSharedPreference.setLocationPermissionForeground(mContext,true);

                    startPickingImage();
                    try{
                        String version = null;
                        try {

                            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
                            version = pInfo.versionName;
                            Log.i("AppVersion",version);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        activityFragPresenter.UpdateUserPermissionAndVersion(mContext,mUser,version);
                    }catch (Exception e){

                    }

                } else {

                    SaveSharedPreference.setCameraPermission(mContext,false);
                    SaveSharedPreference.setLocationPermissionForeground(mContext,false);
                }

            }
        } catch (Exception ex) {
            ex.toString();
        }


        switch (requestCode) {
            case 3:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //isWriteStoragePermissionGranted();

                    startPickingImage();
                } else {


                }
                break;

            case 2:


                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    ProgressManager.hideProgress();
                    startPickingImage();

                } else {

                }
                break;
            case 5:


                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //ProgressManager.hideProgress();
                    SaveSharedPreference.setStepCounterPermission(mContext,true);
                    if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                        startGPSService(mContext);
                    }else{
                        stopGPSService(mContext);
                        startGPSService(mContext);
                    }

                    try{
                        String version = null;
                        try {
                            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
                            version = pInfo.versionName;
                            Log.i("AppVersion",version);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        activityFragPresenter.UpdateUserPermissionAndVersion(mContext,mUser,version);
                    }catch (Exception e){

                    }

                } else {
                    SaveSharedPreference.setStepCounterPermission(mContext,false);
                }
                break;
        }
    }
    private static File getOutputMediaFile(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Rikskampen");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    public Bitmap getbitmapFromPath(File image) {
        BitmapFactory.Options options;
        String imageInSD = null;
        Bitmap bitmap = null;
        try {
            imageInSD = image.getAbsolutePath();
            bitmap = BitmapFactory.decodeFile(imageInSD);
            return bitmap;
        } catch (OutOfMemoryError e) {
            try {
                options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                bitmap = BitmapFactory.decodeFile(imageInSD, options);
                return bitmap;
            } catch (Exception excepetion) {
                Log.e("", String.valueOf(excepetion));
            }
        }
        return bitmap;
    }

    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getActivity());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.png");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    /*
     * TO COMPRESS THE BITMAP
     * TO STORE IN TEMPORARY PATH WITH EXTERNAL PERMISSION
     * */
    public Uri getImageUriFromExternalPermission(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    /*
     * TO STORE FILE IN REAL|PERMANENT PATH
     * e.g. /storage/emulated/0/Pictures/FILENAME.EXTENSION
     * */
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    /*
     * TO COMPRESS THE BITMAP
     * TO STORE IN TEMPORARY PATH WITH INTERNAL PERMISSION
     * */
    public Uri getImageUriFromInternalPermission(Context inContext, Bitmap inImage) {
        ContextWrapper wrapper = new ContextWrapper(getContext());
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        File file = wrapper.getDir("Images",MODE_PRIVATE);
        file = new File(getActivity().getExternalFilesDir(null), currentDateandTime+"pic.jpg");

        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            inImage.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }

        // Parse the gallery image url to uri
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
        return savedImageURI;

    }

    /*
     * TO STORE FILE IN REAL|PERMANENT PATH
     * e.g. /storage/emulated/0/Pictures/FILENAME.EXTENSION
     *
     * */
    public File convertUriIntoFile(Uri uri) {
        File filePath = null;
        if (uri != null) {

            filePath =  new File(String.valueOf(uri));
        }
        return filePath;
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            mDailyPick.setImageBitmap(bitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUriFromInternalPermission(mContext, bitmap);
            finalFile = convertUriIntoFile(tempUri);


            try {

                if(tempLocationName.isEmpty() || tempLocationName==null){
                    tempLocationName="N/A";

                }

                if(Lat_temp.isEmpty() && Long_temp.isEmpty()){
                    Lat_temp = String.valueOf(simpleLocation.getLatitude());
                    Long_temp = String.valueOf(simpleLocation.getLongitude());
                }
                Competition CompitionDate = Repository.getCompitionDate();
                int todaySteps = 0;
                activityAdapterListModel todayActivity =  activityFragPresenter.GetTodayActivityV1(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
                if(todayActivity!=null){
                    todaySteps = todayActivity.getmSteps();
                }else{
                    todaySteps = 0;
                }


                DailyBitmap = bitmap;
                locationName.setText(tempLocationName + "");
                String utcTime = convertTimeToUTC();
                String LocalTime = convertUTCToLocalTime(utcTime);
                String LocalTimeUi = convertTimeForUI(LocalTime);
                dateTime.setText(LocalTimeUi + "");
                double height = mUser.getHeight_in_cm();
                double weight = mUser.getWeight();
                //double StepCount = Double.parseDouble(stepsCountValue.getText().toString().replace("(K)","").trim());
             /*   double StepCount = SaveSharedPreference.getUserTodaySteps(mContext);
                double StepCount = SaveSharedPreference.getUserTodaySteps(mContext);*/
                double StepCount = todaySteps;
                long StepCountInt = todaySteps;
                //int StepCountInt = Integer.parseInt((stepsCountValue.getText().toString()));
                String CaloriesAct = String.format("%.0f", CaloriesCalulatorFromSteps(height, weight, StepCount));
                String dictance = String.valueOf(SaveSharedPreference.getDistance(getContext()));
                String starCount = mUser.getStars_count();


                if(starCount == null){
                    starCount ="0";
                }
                DialogeBoxWeight(String.valueOf(StepCountInt),starCount,finalFile,CaloriesAct,dictance,tempLocationName,utcTime);


            }
            catch (Exception ex){
                Log.i("ActivityExcep",ex.toString());
            }

        }


    }


    public void DialogeBoxWeight(String StepCount, String starCount,File pictureFile ,String CaloriesBurned,String distance,String Location,String DateTimeUtc){

        LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_weight_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        final EditText weightEditTxt = promptsView
                .findViewById(R.id.editTextDialogUserInput);

        final EditText waistEditTxt = promptsView
                .findViewById(R.id.editTextDialogUserInput1);

        final Button CancelBtn = promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = promptsView.findViewById(R.id.okBtn);

        if(mUser.getWeight()>0){
            int weight = (int) mUser.getWeight();
            weightEditTxt.setText(weight+"");
        }
        if(mUser.getWeight()>0){
            int waist = (int) mUser.getWaist();
            waistEditTxt.setText(waist+"");
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder
                Double WeightInt=0.0;
                Double WaistInt=0.0;
                todayWeight = weightEditTxt.getText().toString().trim();
                todayWaist = waistEditTxt.getText().toString().trim();


                try{
                WeightInt = Double.parseDouble(todayWeight);
                WaistInt = Double.parseDouble(todayWaist);
                if (WeightInt == 0 || WeightInt < 20 || WeightInt > 200 ) {

                    weightEditTxt.setError(getResources().getString(R.string.UpdateProfile_EnterValidWeight));
                    weightEditTxt.requestFocus();
                    //MyApplication.showSimpleSnackBar(mContext, "Vikt saknas eller ogiltigt antal ");

                }else if(WaistInt==0 ||WaistInt < 38 || WaistInt >200) {
                    waistEditTxt.setError(getResources().getString(R.string.UpdateProfile_EnterValidWaist));
                    waistEditTxt.requestFocus();
                    //MyApplication.showSimpleSnackBar(mContext, "Midjan saknas eller ogiltigt nummer ");
                }
                else{

                    SaveSharedPreference.setWeightToday(mContext, String.valueOf(todayWeight));
                    SaveSharedPreference.setWaistToday(mContext, String.valueOf(WaistInt));

                    weightNum.setText(String.valueOf(todayWeight));
                    WaistNum.setText( String.valueOf(WaistInt));
                    ProgressManager.showProgress(mContext,getResources().getString(R.string.progress_dialog_message));
                    //activityFragPresenter.AddActivity(mUser, "Testing", "1", StepCount, "Monday", starCount, CaloriesBurned, tempTimeDate, pictureFile, Lat_temp, Long_temp, Location, distance,todayWeight,todayWaist);

                    List<ActivityDaily> pastActivities = activityFragPresenter.GetPastActivities(mContext);

                    if(pastActivities!=null){
                        activityFragPresenter.SyncPastActivities(mContext,pastActivities);
                    }else{
                        activityFragPresenter.AddActivityV3(mUser, "Android", "1", StepCount, "Monday", starCount, CaloriesBurned, DateTimeUtc, pictureFile, Lat_temp, Long_temp, Location, distance,todayWeight,todayWaist);
                    }
                    alertDialog.dismiss();
                }
                }catch (Exception e){
                    Log.i("ActivityException",e.toString());
                    ProgressManager.hideProgress();
                    alertDialog.dismiss();
                    mDailyPick.setImageResource(R.drawable.icon_profile_pics);
                    locationName.setText("");
                    dateTime.setText("");
                  //  MyApplication.showSimpleSnackBar(mContext, "Ange fält ");
                }


            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDailyPick.setImageResource(R.drawable.icon_profile_pics);
                locationName.setText("");
                dateTime.setText("");
                //MyApplication.showSimpleSnackBar(mContext,"Aktivitet avbruten");
                alertDialog.dismiss();
            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }


    public void DialogeBoxContestDate(){

        LayoutInflater li = LayoutInflater.from(getContext());
        View promptsView = li.inflate(R.layout.dialog_box_contest_date_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setView(promptsView);

        final TextView contestStartTime = promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = promptsView.findViewById(R.id.okBtn);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

       // ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();

        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate.getStartDate()!=null){
            ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);

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

    }



    public void ActivityUpdate(){

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

        double height = mUser.getHeight_in_cm();
        double weight = mUser.getWeight();
        double StepCount = Double.parseDouble(stepsCountValue.getText().toString());
        String CaloriesBurned = String.valueOf(CaloriesCalulatorFromSteps(height, weight, StepCount));
        String dictance = String.valueOf(mUser.getDistance());
        String starCount = StarNum.getText().toString();
        String Steps = stepsCountValue.getText().toString();

        //  Integer starCountDB = activityFragPresenter.GetTodayActivityData(formattedDate);

        /*if(starCount==null ){
            starCount = "0";
        }*/


        HitAPI(starCount,Steps,CaloriesBurned,currentDateandTime);

        // activityFragPresenter.AddStarSteps(mUser,starCount+"",Steps+"",CaloriesBurned,formattedDate);
        //  SaveSharedPreference.setUserStepsUpdateActivity(mContext,false);

    }

    public void HitAPI(String starCount,String Steps,String CaloriesBurned,String formattedDate){


        APIService mWebService;
        Realm mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        String   userId=mUser.getId();


        HashMap<String,String> hm=new HashMap();
        hm.put(USERID,userId);
        hm.put(STEPS_COUNT, starCount);
        hm.put(STARS_COUNT, Steps);
        hm.put(ACTIVITY_CALORIES, CaloriesBurned);
        hm.put(ACTIVITY_TIME,formattedDate);




        Call<Generic_Result<Object>> call = mWebService.AddStarSteps_(mUser.getToken(),hm);

        call.enqueue(new Callback<Generic_Result<Object>>() {
            @Override
            public void onResponse(Call<Generic_Result<Object>> call, Response<Generic_Result<Object>> response) {

                Generic_Result<Object> obj = null;

                obj = response.body();

                if(response.code()== HttpStatus.HTTP_OK) {

                    Toast.makeText(mContext, " Pull to Refresh Data Synced ", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<Generic_Result<Object>> call, Throwable t) {

                t.toString();

                Toast.makeText(mContext, " Pull to Refresh Data Not ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public double CaloriesCalulatorFromSteps(double height ,double weight,double stepsCount){


        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);

        strip = height * 0.415;

        stepCountMile = 160934.4 / strip;

        conversationFactor = CaloriesBurnedPerMile / stepCountMile;

        CaloriesBurned = stepsCount * conversationFactor;

        return CaloriesBurned;
    }

    public float getDistanceNow(long steps,double hightCM){
        double stepLenght = (hightCM * 0.415);
        //double stepLenght = (hightCM * 0.3619);
        float Distance = (float) (stepLenght * steps);
        return Distance/100;
    }


    //function to determine the distance run in kilometers using average step length for men and number of steps
    //standard hight in CM = 100; for male
    public float getDistanceRun(long steps,int hightCm){
        float distance = (float)(steps*hightCm)/(float)100000;
        float distanceMeters = distance*1000;
        return distanceMeters;
    }


    @Override
    public void setActivitiesHistory(Object data,LineData chartData) {


        ArrayList<DailyActivityModel>  list = (ArrayList<DailyActivityModel>) data;

        //dailyPicAdapter.setData(list);

        chart.setData(chartData);

        chart.invalidate();

    }


    public void startGPSService(Context context)
    {
        try{
            Intent startIntent = new Intent(context, StepCountingService.class);
            startIntent.setAction(Constants.START_ACTION);

        /*Intent startIntent = new Intent(mContext, DailyStepsServiceInternet.class);
        mContext.startService(startIntent);*/

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getActivity().startForegroundService(startIntent);
            }
            else {
                getActivity().startService(startIntent);
            }
        }catch (Exception e){

        }

       // getActivity().bindService(new Intent(context, StepCountingService.class), mConnection, Context.BIND_AUTO_CREATE);

    }

    public void stopGPSService(Context context)
    {
        Intent startIntent = new Intent(context, StepCountingService.class);

        startIntent.setAction(Constants.STOP_ACTION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            context.startForegroundService(startIntent);
        }
        else{
            context.startService(startIntent);
        }
        //getActivity().unbindService(mConnection);

    }
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            StepCountingService.LocalBinder binder = (StepCountingService.LocalBinder) iBinder;

            stepCountingService = binder.getService();
            stepCountingService.setstepsCountStepListener(ActivityFragment.this);
            stepCountingService.onCreate();

  /*          stepCounterSensorstatus=stepCountingService.sensorManager.registerListener(stepCountingService, stepCountingService.stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
            if(!stepCounterSensorstatus){
                Toast.makeText(mContext, "Step Not Working", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(mContext, "Step Working", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(mContext, "Service Connected", Toast.LENGTH_SHORT).show();*/
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            stepCountingService.setstepsCountStepListener(null);
            stepCountingService = null;


           // Toast.makeText(mContext, "Service Disconnected", Toast.LENGTH_SHORT).show();


        }
    };


    @Override
    public void setAddActivity(String message) {



    }

    @Override
    public void setAddActivityFailed(String message) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setAddActivityV3(String message) {

        ProgressManager.hideProgress();

        Competition CompitionDate = Repository.getCompitionDate();
        List<activityAdapterListModel> activityDailySyncData = activityFragPresenter.getSyncedActivityData(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));

        if(activityDailySyncData.size()>0){
            noActivityDataView.setVisibility(View.GONE);
            dailyPickRecyclerView.setVisibility(View.VISIBLE);
            MyApplication.showSimpleSnackBarSucess(mContext, message);
            MainLeaderActivity activity = (MainLeaderActivity) mContext;
            SaveSharedPreference.setDailyImage(mContext,encodeToBase64(DailyBitmap));
            SaveSharedPreference.setLocation(mContext,tempLocationName);
            SaveSharedPreference.setDailyDate(mContext,convertUTCToLocalTime(tempTimeDate) + "");
            String activityImage = null;
            String activityLocation = null;
            String activityTime = null;
            String activityWeight = null;
            String activityWaist = null;
            activityAdapterListModel todayActivity =  activityFragPresenter.GetTodayActivityV1(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
            if(todayActivity!=null){
                activityImage = todayActivity.getmMediaImage();
                activityLocation = todayActivity.getmLocationAddress();
                activityTime = convertTimeForUI(todayActivity.getmDate());
                activityWeight = String.valueOf(todayActivity.getmWeight());
                activityWaist = String.valueOf(todayActivity.getmWaist());


                if(activityImage !=null || activityLocation !=null || activityTime !=null || activityWeight !=null || activityWaist !=null){
                    loadImageDailyActivity(mDailyPick,activityImage);
                    locationName.setText(activityLocation);
                    dateTime.setText(activityTime);
                    if(!activityWeight.equals("0.0")){
                        weightNum.setText(activityWeight);
                    }else{
                        weightNum.setText(getResources().getString(R.string.General_NA));
                    }
                    if(!activityWaist.equals("0")){
                        WaistNum.setText(activityWaist);
                    }else{
                        WaistNum.setText(getResources().getString(R.string.General_NA));
                    }
                }
                updateNotify();
            }
        }else{
            noActivityDataView.setVisibility(View.VISIBLE);
            dailyPickRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void setAddActivityFailedV3(String message) {
        ProgressManager.hideProgress();

        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){

            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
        }
        else if(message.equals("Bad Request")){

            MyApplication.showSimpleSnackBar(mContext, message);


        }else{
           // MyApplication.showSimpleSnackBar(mContext, "Server Down");
        }


        Competition CompitionDate = Repository.getCompitionDate();
        List<activityAdapterListModel> activityDailySyncData = activityFragPresenter.getSyncedActivityData(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));

        if(activityDailySyncData.size()>0){
            noActivityDataView.setVisibility(View.GONE);
            dailyPickRecyclerView.setVisibility(View.VISIBLE);
            MyApplication.showSimpleSnackBarSucess(mContext, message);
            MainLeaderActivity activity = (MainLeaderActivity) mContext;
            SaveSharedPreference.setDailyImage(mContext,encodeToBase64(DailyBitmap));
            SaveSharedPreference.setLocation(mContext,tempLocationName);
            SaveSharedPreference.setDailyDate(mContext,convertUTCToLocalTime(tempTimeDate) + "");
            String activityImage = null;
            String activityLocation = null;
            String activityTime = null;
            String activityWeight = null;
            String activityWaist = null;
            activityAdapterListModel todayActivity =  activityFragPresenter.GetTodayActivityV1(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
            if(todayActivity!=null){
                activityImage = todayActivity.getmMediaImage();
                activityLocation = todayActivity.getmLocationAddress();
                activityTime = convertTimeForUI(todayActivity.getmDate());
                activityWeight = String.valueOf(todayActivity.getmWeight());
                activityWaist = String.valueOf(todayActivity.getmWaist());


                if(activityImage !=null || activityLocation !=null || activityTime !=null || activityWeight !=null || activityWaist !=null){
                    loadImageDailyActivity(mDailyPick,activityImage);
                    locationName.setText(activityLocation);
                    dateTime.setText(activityTime);
                    if(!activityWeight.equals("0.0")){
                        weightNum.setText(activityWeight);
                    }else{
                        weightNum.setText(getResources().getString(R.string.General_NA));
                    }
                    if(!activityWaist.equals("0")){
                        WaistNum.setText(activityWaist);
                    }else{
                        WaistNum.setText(getResources().getString(R.string.General_NA));
                    }
                }
                updateNotify();
            }
        }else{
            noActivityDataView.setVisibility(View.VISIBLE);
            dailyPickRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void UserHeightWeightSync(String message) {

    }

    @Override
    public void UserHeightWeightSyncFailed(String message) {

    }

    @Override
    public void setStarStepChaseSucess(String message) {

        //MyApplication.showSimpleSnackBarSucess(mContext, message);

        //Log.i("no Internet",message);
    }

    @Override
    public void setStarStepChaseFailed(String message) {
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){

            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
        }
    }

    @Override
    public void setAddDailyActivitySucess(String message) {
        //MyApplication.showSimpleSnackBarSucess(mContext,message);
    }

    @Override
    public void setAddDailyActivityFailed(String message) {
        //MyApplication.showSimpleSnackBar(mContext,message);
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){

          //MyApplication.showSimpleSnackBar(mContext, "No Internet Connection");
        }
    }

    @Override
    public void setUpdateDailyActivitySucess(String message) {
        //MyApplication.showSimpleSnackBarSucess(mContext,message);
    }

    @Override
    public void setUpdateDailyActivityFailed(String message) {
        //MyApplication.showSimpleSnackBar(mContext,message);
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){

            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
        }
        else if(message.equals("Bad Request")){

            MyApplication.showSimpleSnackBar(mContext, message);

        }else{
           // MyApplication.showSimpleSnackBar(mContext, "Server Down");
            //MainLeaderActivity activity= (MainLeaderActivity) mContext;
        }

    }

    @Override
    public void setSyncPastActivitiesSucess(String message) {

        //Toast.makeText(mContext, "SyncPastActivitiesFromAddActivity Lives", Toast.LENGTH_SHORT).show();

        double height = mUser.getHeight_in_cm();
        double weight = mUser.getWeight();
        //double StepCount = Double.parseDouble(stepsCountValue.getText().toString().replace("(K)","").trim());
        double StepCount = SaveSharedPreference.getUserTodaySteps(mContext);
        long StepCountInt = SaveSharedPreference.getUserTodaySteps(mContext);
        //int StepCountInt = Integer.parseInt((stepsCountValue.getText().toString()));
        String CaloriesAct = String.format("%.0f", CaloriesCalulatorFromSteps(height, weight, StepCount));
        String dictance = String.valueOf(SaveSharedPreference.getDistance(getContext()));
        String starCount = mUser.getStars_count();
        String utcTime = convertTimeToUTC();

        activityFragPresenter.AddActivityV3(mUser, "Android", "1", String.valueOf(StepCountInt), "Monday", starCount, CaloriesAct, utcTime,finalFile, Lat_temp, Long_temp, tempLocationName, dictance,todayWeight,todayWaist);

    }

    @Override
    public void setSyncPastActivitiesFailed(String message) {
        ProgressManager.hideProgress();
        //Toast.makeText(mContext, "SyncPastActivitiesFromAddActivity Fail", Toast.LENGTH_SHORT).show();
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")){
            double height = mUser.getHeight_in_cm();
            double weight = mUser.getWeight();
            //double StepCount = Double.parseDouble(stepsCountValue.getText().toString().replace("(K)","").trim());
            double StepCount = SaveSharedPreference.getUserTodaySteps(mContext);
            long StepCountInt = SaveSharedPreference.getUserTodaySteps(mContext);
            //int StepCountInt = Integer.parseInt((stepsCountValue.getText().toString()));
            String CaloriesAct = String.format("%.0f", CaloriesCalulatorFromSteps(height, weight, StepCount));
            String dictance = String.valueOf(SaveSharedPreference.getDistance(getContext()));
            String starCount = mUser.getStars_count();
            String utcTime = convertTimeToUTC();
            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
            activityFragPresenter.AddActivityV3(mUser, "Android", "1", String.valueOf(StepCountInt), "Monday", starCount, CaloriesAct, utcTime,finalFile, Lat_temp, Long_temp, tempLocationName, dictance,todayWeight,todayWaist);
        }else if(message.equals("Unable to resolve host \"apitest.rikskampen.se\": No address associated with hostname")){
            double height = mUser.getHeight_in_cm();
            double weight = mUser.getWeight();
            //double StepCount = Double.parseDouble(stepsCountValue.getText().toString().replace("(K)","").trim());
            double StepCount = SaveSharedPreference.getUserTodaySteps(mContext);
            long StepCountInt = SaveSharedPreference.getUserTodaySteps(mContext);
            //int StepCountInt = Integer.parseInt((stepsCountValue.getText().toString()));
            String CaloriesAct = String.format("%.0f", CaloriesCalulatorFromSteps(height, weight, StepCount));
            String dictance = String.valueOf(SaveSharedPreference.getDistance(getContext()));
            String starCount = mUser.getStars_count();
            String utcTime = convertTimeToUTC();
            MyApplication.showSimpleSnackBar(mContext, getResources().getString(R.string.General_NoInternetConnection));
            activityFragPresenter.AddActivityV3(mUser, "Android", "1", String.valueOf(StepCountInt), "Monday", starCount, CaloriesAct, utcTime,finalFile, Lat_temp, Long_temp, tempLocationName, dictance,todayWeight,todayWaist);
        }
    }

    @Override
    public void setUpdateUserPermissionAndVersionSucess(String message) {

    }

    @Override
    public void setUpdateUserPermissionAndVersionFailed(String message) {

    }

    @Override
    public void GetTodayStepsFromGoogleFitSucess(String message,String TodayStepsGoogleFit) {
        MyApplication.showSimpleSnackBarSucess(mContext,message);
        Toast.makeText(mContext, TodayStepsGoogleFit, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void GetTodayStepsFromGoogleFitFailed(String message) {
        MyApplication.showSimpleSnackBar(mContext,message);
    }

    @Override
    public void setPresenter(ActivityFragContract.Presenter mPresenter) {

    }



    public boolean isPermissionGranted() {
        boolean cameraPermissionGranted = ContextCompat.checkSelfPermission(MainLeaderActivity.context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

        boolean microPhonePermissionGranted = ContextCompat.checkSelfPermission(MainLeaderActivity.context, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED;

        return cameraPermissionGranted && microPhonePermissionGranted;
    }

    public void requestPermission() {

        boolean cameraPermissionGranted = ContextCompat.checkSelfPermission(MainLeaderActivity.context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

        boolean microPhonePermissionGranted = ContextCompat.checkSelfPermission(MainLeaderActivity.context, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED;


        final List<String> permissionList = new ArrayList();
        if (!cameraPermissionGranted) {
            permissionList.add(Manifest.permission.CAMERA);
        }
        if (!microPhonePermissionGranted) {
            permissionList.add(Manifest.permission.RECORD_AUDIO);
        }
        if (permissionList.size() > 0 )
        {


        }
    }



    public void updateNotify()
    {
        /*Intent googlefitService = new Intent(mContext, GoogleFitTodayStepsService.class);
        startWakefulService(mContext,googlefitService);*/


        Competition CompitionDate = Repository.getCompitionDate();

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime1 = sdf1.format(new Date());

        if(dailyPicAdapter!=null)
        {
            if(CompitionDate.getStartDate()!=null){
                List<activityAdapterListModel> activityDailySyncData = activityFragPresenter.getSyncedActivityData(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
                if(activityDailySyncData.size()>0){
                    noActivityDataView.setVisibility(View.GONE);
                    dailyPickRecyclerView.setVisibility(View.VISIBLE);
                    dailyPicAdapter.updateAdapter(activityDailySyncData);
                    String activityImage = null;
                    String activityLocation = null;
                    String activityTime = null;
                    String activityWeight = null;
                    String activityWaist = null;
                    activityAdapterListModel todayActivity =  activityFragPresenter.GetTodayActivityV1(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
                    if(todayActivity!=null){
                        activityImage = todayActivity.getmMediaImage();
                        activityLocation = todayActivity.getmLocationAddress();
                        activityTime = convertTimeForUI(todayActivity.getmDate());
                        activityWeight = String.valueOf(todayActivity.getmWeight());
                        activityWaist = String.valueOf(todayActivity.getmWaist());


                        if(activityImage !=null || activityLocation !=null || activityTime !=null || activityWeight !=null || activityWaist !=null){
                            loadImageDailyActivity(mDailyPick,activityImage);
                            locationName.setText(activityLocation);
                            dateTime.setText(activityTime);
                            if(!activityWeight.equals("0.0")){
                                weightNum.setText(activityWeight);
                            }else{
                                weightNum.setText(getResources().getString(R.string.General_NA));
                            }
                            if(!activityWaist.equals("0")){
                                WaistNum.setText(activityWaist);
                            }else{
                                WaistNum.setText(getResources().getString(R.string.General_NA));
                            }
                        }
                    }
                }else{
                    noActivityDataView.setVisibility(View.VISIBLE);
                    dailyPickRecyclerView.setVisibility(View.GONE);
                }
                for(int i =0; i<activityDailySyncData.size();i++){
                    if(activityDailySyncData.get(i).getCurrentWeek().equals("present")){
                        dailyPickRecyclerView.getLayoutManager().scrollToPosition(i);
                        break;
                    }
                }
                }else{
                    noActivityDataView.setVisibility(View.VISIBLE);
                    dailyPickRecyclerView.setVisibility(View.GONE);
                }
        }else{
            try{
                List<activityAdapterListModel> activityDailySyncData = activityFragPresenter.getSyncedActivityData(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
                if(activityDailySyncData.size()>0){
                    noActivityDataView.setVisibility(View.GONE);
                    dailyPickRecyclerView.setVisibility(View.VISIBLE);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                    mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    if(CompitionDate.getStartDate()!=null){
                        activityDailySyncData = activityFragPresenter.getSyncedActivityData(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
                        dailyPicAdapter = new DailyActivityAdapter(mContext, activityDailySyncData);
                        mLayoutManager.setAutoMeasureEnabled(true);

                        dailyPickRecyclerView.setLayoutManager(mLayoutManager);

                        dailyPickRecyclerView.setAdapter(dailyPicAdapter);

                        for(int i =0; i<activityDailySyncData.size();i++){
                            if(activityDailySyncData.get(i).getCurrentWeek().equals("Present")){
                                dailyPickRecyclerView.getLayoutManager().scrollToPosition(i);
                                break;
                            }
                        }
                    }
                    String activityImage = null;
                    String activityLocation = null;
                    String activityTime = null;
                    String activityWeight = null;
                    String activityWaist = null;
                    activityAdapterListModel todayActivity =  activityFragPresenter.GetTodayActivityV1(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()));
                    if(todayActivity!=null){
                        activityImage = todayActivity.getmMediaImage();
                        activityLocation = todayActivity.getmLocationAddress();
                        activityTime = convertTimeForUI(todayActivity.getmDate());
                        activityWeight = String.valueOf(todayActivity.getmWeight());
                        activityWaist = String.valueOf(todayActivity.getmWaist());


                        if(activityImage !=null || activityLocation !=null || activityTime !=null || activityWeight !=null || activityWaist !=null){
                            loadImageDailyActivity(mDailyPick,activityImage);
                            locationName.setText(activityLocation);
                            dateTime.setText(activityTime);
                            if(!activityWeight.equals("0.0")){
                                weightNum.setText(activityWeight);
                            }else{
                                weightNum.setText(getResources().getString(R.string.General_NA));
                            }
                            if(!activityWaist.equals("0")){
                                WaistNum.setText(activityWaist);
                            }else{
                                WaistNum.setText(getResources().getString(R.string.General_NA));
                            }
                        }
                    }
                }else{
                    noActivityDataView.setVisibility(View.VISIBLE);
                    dailyPickRecyclerView.setVisibility(View.GONE);
                }

            }catch (Exception e){
                Log.i("ActivityTimeEx",e.toString());
            }
        }

        if(motivationVideosAdapter!=null){
            List<MotivationalVideo> MotivationVideoDataV3 = activityFragPresenter.getMotivationVideosV3();

            if(MotivationVideoDataV3.isEmpty()){
                motivationThumpline.setImageResource(0);
                motivationVideosRV.setVisibility(View.GONE);
            }else{
                motivationVideosRV.setVisibility(View.VISIBLE);

                motivationVideosAdapter.updateAdapter(MotivationVideoDataV3);

                MotivationVideo = MotivationVideoDataV3.get(0).getMediaUrl();

                MotivationVideoPic = MotivationVideoDataV3.get(0).getMediaThumb();

                loadImage(motivationThumpline,MotivationVideoPic);

            }
        }else{
            try{
                motivationVideosRV.setVisibility(View.VISIBLE);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                List<MotivationalVideo> MotivationVideoDataV3 = activityFragPresenter.getMotivationVideosV3();
                MotivationVideo = MotivationVideoDataV3.get(0).getMediaUrl();

                MotivationVideoPic = MotivationVideoDataV3.get(0).getMediaThumb();

                loadImage(motivationThumpline, MotivationVideoPic);

                motivationVideosAdapter = new MotivationVideosAdapter(mContext, MotivationVideoDataV3);
                mLayoutManager.setAutoMeasureEnabled(true);
                motivationVideosRV.setLayoutManager(mLayoutManager);

                motivationVideosRV.setAdapter(motivationVideosAdapter);
            }catch (Exception e){

            }

        }

        if(CompitionDate!=null){
            if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                Boolean ContestStatus = getCompitionStartDate(mContext,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                if(ContestStatus && !ContestEndStatus){
                    mCounterlayout.setVisibility(View.VISIBLE);
                    mMiddlelayout.setVisibility(View.VISIBLE);
                    mDailyDiaryLayout.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                            if(ContextCompat.checkSelfPermission(mContext,
                                    Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED){
                                if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                                    startGPSService(mContext);
                                }

                            }else if(ContextCompat.checkSelfPermission(mContext,
                                    Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
                                //ask for permission
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                    SaveSharedPreference.setStepCounterPermission(mContext,false);
                                    requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 5);
                                }
                            }
                        }else{
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                if(ContextCompat.checkSelfPermission(mContext,
                                        Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
                                    //ask for permission
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                        SaveSharedPreference.setStepCounterPermission(mContext,false);
                                        requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 5);

                                    }
                                }
                            }
                        }
                    }else{
                        if (!Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                            startGPSService(mContext);
                        }
                    }

                   // getActivity().bindService(new Intent(getContext(), StepCountingService.class), mConnection, Context.BIND_AUTO_CREATE);

                }else if(ContestEndStatus){
                    if (Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                        stopGPSService(mContext);
                    }
                    mCounterlayout.setVisibility(View.GONE);
                    mMiddlelayout.setVisibility(View.GONE);
                    mDailyDiaryLayout.setVisibility(View.GONE);

                }else if(!ContestStatus){
                    if (Constants.isMyServiceRunning(StepCountingService.class, mContext)) {
                        stopGPSService(mContext);
                    }
                    mCounterlayout.setVisibility(View.GONE);
                    mMiddlelayout.setVisibility(View.GONE);
                    mDailyDiaryLayout.setVisibility(View.GONE);

                }
            }else{
             /* mCounterlayout.setVisibility(View.GONE);
                mMiddlelayout.setVisibility(View.GONE);
                mDailyDiaryLayout.setVisibility(View.GONE);*/
            }
        }else{
            /* mCounterlayout.setVisibility(View.GONE);
            mMiddlelayout.setVisibility(View.GONE);
            mDailyDiaryLayout.setVisibility(View.GONE);*/

        }

        try {
            DailyDiaryQuestionModel dailyDiaryQuestionModel = activityFragPresenter.getDailyQuestions();

            if(dailyDiaryQuestionModel.getmQuestions()!=null){

                dailyDiaryAdapter = new DailyDiaryAdapter(mContext, dailyDiaryQuestionModel.getmQuestions());

                dailyDiaryQuestionRV.setLayoutManager(Constants.getVerticalLayoutManager(getActivity()));

                dailyDiaryQuestionRV.setAdapter(dailyDiaryAdapter);
            }
            if(dailyDiaryQuestionModel.ismDayStatus()!=null){
                if(dailyDiaryQuestionModel.ismDayStatus()){
                    thumbStatus = true;
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumpUP.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                }*/
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);
                }else if(!dailyDiaryQuestionModel.ismDayStatus()){
                    thumbStatus = false;
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumbDown.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                }*/
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
                }
            }else{
                thumbStatus = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumpUP.setBackgroundTintList(null);
                    mThumbDown.setBackgroundTintList(null);
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);
                }

            }
            if(dailyDiaryQuestionModel.getmDayDescription()!=null){
                mDayDescriptionText.setText(dailyDiaryQuestionModel.getmDayDescription());
            }else{
                mDayDescriptionText.setText("");
            }

        }catch (Exception e){

        }
        //pullToRefresh.setEnabled(true);
        pullToRefresh.setRefreshing(false);
        ProgressManager.hideProgress();

    }

    public void updateNotifyDiary()
    {
        try {
            DailyDiaryQuestionModel dailyDiaryQuestionModel = activityFragPresenter.getDailyQuestions();

            if(dailyDiaryQuestionModel.getmQuestions()!=null){

                dailyDiaryAdapter = new DailyDiaryAdapter(mContext, dailyDiaryQuestionModel.getmQuestions());

                dailyDiaryQuestionRV.setLayoutManager(Constants.getVerticalLayoutManager(getActivity()));

                dailyDiaryQuestionRV.setAdapter(dailyDiaryAdapter);
            }
            if(dailyDiaryQuestionModel.ismDayStatus()!=null){
                if(dailyDiaryQuestionModel.ismDayStatus()){
                    thumbStatus = true;
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumpUP.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                }*/
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);
                }else if(!dailyDiaryQuestionModel.ismDayStatus()){
                    thumbStatus = false;
                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumbDown.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.colorPrimary));
                }*/
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumb_down_selected);
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
                }
            }else{
                thumbStatus = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mThumpUP.setBackgroundTintList(null);
                    mThumbDown.setBackgroundTintList(null);
                    mThumpUP.setBackgroundResource(R.drawable.ic_thumbup);
                    mThumbDown.setBackgroundResource(R.drawable.ic_thumbdown);

                }

            }
            if(dailyDiaryQuestionModel.getmDayDescription()!=null){
                mDayDescriptionText.setText(dailyDiaryQuestionModel.getmDayDescription());
            }else{
                mDayDescriptionText.setText("");
            }

        }catch (Exception e){

        }
    }

    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

    public void ApiFail(){

        updateNotify();
        pullToRefresh.setRefreshing(false);
        ProgressManager.hideProgress();
    }

    public void ShowStepsCounterView(Competition competition){
        int todaySteps = 0;
        activityAdapterListModel todayActivity =  activityFragPresenter.GetTodayActivityV1(mContext,convertUTCToLocalTime(competition.getStartDate()));
        if(todayActivity!=null){
            todaySteps = todayActivity.getmSteps();
        }else{
            todaySteps = 0;
        }
        stepsCountValue.setText(Math.abs(todaySteps)+"");
        if(todaySteps < 1000){
            stepsCountValue.setText(todaySteps+"");
        }else if(todaySteps < 1000000){
            // Log.i("StepValue",todaySteps+"");
            double stepsValue = Math.abs(todaySteps)/1000.0;
            stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"K");

        }else if(todaySteps < 100000000){
            double stepsValue = Math.abs(todaySteps)/1000000.0;
            stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"M");
        }else{
            double stepsValue = Math.abs(todaySteps)/100000000.0;
            stepsCountValue.setText(String.format("%.2f",stepsValue)+""+"B");
        }

        String CalCount = String.format("%.0f", Math.abs(CaloriesCalulatorFromSteps(mUser.getHeight_in_cm(),mUser.getWeight(),todaySteps)));
        double CalValue = Integer.parseInt(CalCount);
        if(CalValue >999){
            //Log.i("CalValue",CalValue+"");
            calUnit.setText("(Kcal)");
            double CaloriesValue = CalValue/1000.0;
            calNum.setText(String.format("%.2f",CaloriesValue)+""+"K");

        }else{
            //Log.i("CalValue",CalValue+"");
            calUnit.setText("(Kcal)");
            calNum.setText(CalValue+"");
        }
        String DisCount = String.format("%.0f",Math.abs(getDistanceNow(todaySteps,mUser.getHeight_in_cm())));
        int DisValue = Integer.parseInt(DisCount);
        if(DisValue > 999){
            //Log.i("DistanceValue",DisValue+"");
            distanceUnit.setText("(Km)");
            double DistanceValue = DisValue/1000.0;
            distanceNum.setText(String.format("%.2f",DistanceValue)+""+"K");
            //SaveSharedPreference.setDistance(mContext, String.format("%.1f",DistanceValue)+"");
        }else{
            distanceUnit.setText("(Km)");
            double DistanceValue = DisValue/1000.0;
            distanceNum.setText(String.format("%.2f",DistanceValue)+"");
        }

        SaveSharedPreference.setDistance(mContext, getDistanceNow(todaySteps,mUser.getHeight_in_cm())+"");

    }

}
