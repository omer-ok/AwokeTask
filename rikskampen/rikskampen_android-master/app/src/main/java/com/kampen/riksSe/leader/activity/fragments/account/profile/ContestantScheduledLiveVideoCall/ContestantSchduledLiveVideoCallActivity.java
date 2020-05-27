package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kampen.riksSe.BroadCastReciver.PostLiveVideoCallSchduleNotificationReceiver;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;

import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter.ContestantFutureScheduledLiveVideoCallAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter.ContestantTodayScheduledLiveVideoCallAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.DaySchduleActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ContestantReschduleLiveVideoCallActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;
import static com.kampen.riksSe.utils.UtilityTz.GetStartDateOfWeek;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToDay;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToHour;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToMiniute;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToMonth;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToSecond;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToYear;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class ContestantSchduledLiveVideoCallActivity extends AppCompatActivity implements ContestantScheduledLiveVideoCallActivityContract.View{

    ContestantScheduledLiveVideoCallActivityPresenter mContestantScheduledLiveVideoCallActivityPresenter;
    private ContestantTodayScheduledLiveVideoCallAdapter mContestantTodayScheduledLiveVideoCallAdapter;
    private ContestantFutureScheduledLiveVideoCallAdapter mContestantFutureScheduledLiveVideoCallAdapter;
    Context mContext;
    LinearLayoutManager mLayoutManagerTodaySchdule,mLayoutManagerFutureSchdule;

    public RecyclerView mContestantTodayScheduledLiveVideoCallRV,mContestantFutureScheduledLiveVideoCallRV;
    Realm_User mUser;
    TextView todaySchdule,FutureSchdule;
    FloatingActionButton mAddSchdule;
    SwipeRefreshLayout pullToRefresh;

    View noDataTodayView,noDataFutureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contestant_schduled_live_video_call);
        mContext =ContestantSchduledLiveVideoCallActivity.this;

        mContestantScheduledLiveVideoCallActivityPresenter = new ContestantScheduledLiveVideoCallActivityPresenter(ContestantSchduledLiveVideoCallActivity.this);

        mContestantTodayScheduledLiveVideoCallRV = findViewById(R.id.ContestantTodayScheduledLiveVideoCallRV);
        mContestantFutureScheduledLiveVideoCallRV = findViewById(R.id.ContestantFutureScheduledLiveVideoCallRV);
        todaySchdule = findViewById(R.id.todaySchduleHeading);
        FutureSchdule = findViewById(R.id.FutureSchduleHeading);
        noDataTodayView = findViewById(R.id.NoDataTodayView);
        noDataFutureView = findViewById(R.id.NoDataFutureView);
        mAddSchdule = findViewById(R.id.add_schdule);

        pullToRefresh = findViewById(R.id.pullToRefresh);

        mLayoutManagerTodaySchdule = new LinearLayoutManager(mContext);
        mLayoutManagerFutureSchdule = new LinearLayoutManager(mContext);
        mUser = mContestantScheduledLiveVideoCallActivityPresenter.provideUserLocal(mContext);

        mContestantScheduledLiveVideoCallActivityPresenter.GetContestantSchdule(mContext);



        mAddSchdule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
                String currentDate = sdf1.format(new Date());
                Intent intent = new Intent(mContext, DaySchduleActivity.class);
                intent.putExtra("SchduleDate",currentDate);
                startActivity(intent);
                finish();
            }
        });


        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mContestantScheduledLiveVideoCallActivityPresenter.GetContestantSchdule(mContext);
            }
        });

    }

    private final GCoreWakefulBroadcastReceiver DeleteSchduleSlot = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try{
                int SchduleID = intent.getIntExtra("SchduleID",0);


                mContestantScheduledLiveVideoCallActivityPresenter.DeleteContestantSchdule(mContext,SchduleID);


            }catch (Exception ex){
                Log.i("ReSchduleEX",ex.toString());
            }
        }
    };

    @Override
    public void SetContestantSchduleSucess(String mesage) {
        try{

            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDate = sdf1.format(new Date());
            List<ScheduledLiveVideoCall> mTodayScheduledLiveVideoCallList = mContestantScheduledLiveVideoCallActivityPresenter.GetContestantTodaySchduleLocalDB(currentDate);
            List<ScheduledLiveVideoCall> mFutureScheduledLiveVideoCallList = mContestantScheduledLiveVideoCallActivityPresenter.GetContestantFutureSchduleLocalDB(currentDate);
            if(mTodayScheduledLiveVideoCallList!=null ){
                noDataTodayView.setVisibility(View.GONE);
                mContestantTodayScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
                if(mContestantTodayScheduledLiveVideoCallAdapter !=null){
                    mContestantTodayScheduledLiveVideoCallAdapter.updateAdapter(mTodayScheduledLiveVideoCallList);
                }else{
                    mContestantTodayScheduledLiveVideoCallAdapter = new ContestantTodayScheduledLiveVideoCallAdapter(mContext, mTodayScheduledLiveVideoCallList);
                    mContestantTodayScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerTodaySchdule);
                    mContestantTodayScheduledLiveVideoCallRV.setAdapter(mContestantTodayScheduledLiveVideoCallAdapter);
                }

                startAlarmTodayLiveVideoChatActivity(mContext,mTodayScheduledLiveVideoCallList.get(0));
            }else{
            /*    todaySchdule.setVisibility(View.GONE);
                mContestantTodayScheduledLiveVideoCallRV.setVisibility(View.GONE);*/
                noDataTodayView.setVisibility(View.VISIBLE);
            }

            if(mFutureScheduledLiveVideoCallList!=null ){
                noDataFutureView.setVisibility(View.GONE);
                mContestantFutureScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
                if(mContestantFutureScheduledLiveVideoCallAdapter !=null){
                    mContestantFutureScheduledLiveVideoCallAdapter.updateAdapter(mFutureScheduledLiveVideoCallList);
                }else{
                    mContestantFutureScheduledLiveVideoCallAdapter = new ContestantFutureScheduledLiveVideoCallAdapter(mContext, mFutureScheduledLiveVideoCallList);
                    mContestantFutureScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerFutureSchdule);
                    mContestantFutureScheduledLiveVideoCallRV.setAdapter(mContestantFutureScheduledLiveVideoCallAdapter);
                }
            }else{
                //mContestantFutureScheduledLiveVideoCallRV.setVisibility(View.GONE);
                noDataFutureView.setVisibility(View.VISIBLE);
            }

            pullToRefresh.setRefreshing(false);
        }catch(Exception ex){
            Log.i("ContestantSchduleEx",ex.toString());
        }
    }



    @Override
    public void SetContestantSchduleFiled(String mesage) {
        try{

            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDate = sdf1.format(new Date());
            List<ScheduledLiveVideoCall> mTodayScheduledLiveVideoCallList = mContestantScheduledLiveVideoCallActivityPresenter.GetContestantTodaySchduleLocalDB(currentDate);
            List<ScheduledLiveVideoCall> mFutureScheduledLiveVideoCallList = mContestantScheduledLiveVideoCallActivityPresenter.GetContestantFutureSchduleLocalDB(currentDate);
            if(mTodayScheduledLiveVideoCallList!=null ){
                noDataTodayView.setVisibility(View.GONE);
                mContestantTodayScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
                if(mContestantTodayScheduledLiveVideoCallAdapter !=null){
                    mContestantTodayScheduledLiveVideoCallAdapter.updateAdapter(mTodayScheduledLiveVideoCallList);
                }else{
                    mContestantTodayScheduledLiveVideoCallAdapter = new ContestantTodayScheduledLiveVideoCallAdapter(mContext, mTodayScheduledLiveVideoCallList);
                    mContestantTodayScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerTodaySchdule);
                    mContestantTodayScheduledLiveVideoCallRV.setAdapter(mContestantTodayScheduledLiveVideoCallAdapter);
                }
            }else{
            /*    todaySchdule.setVisibility(View.GONE);
                mContestantTodayScheduledLiveVideoCallRV.setVisibility(View.GONE);*/
                noDataTodayView.setVisibility(View.VISIBLE);
            }

            if(mFutureScheduledLiveVideoCallList!=null ){
                noDataFutureView.setVisibility(View.GONE);
                mContestantFutureScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
                if(mContestantFutureScheduledLiveVideoCallAdapter !=null){
                    mContestantFutureScheduledLiveVideoCallAdapter.updateAdapter(mFutureScheduledLiveVideoCallList);
                }else{
                    mContestantFutureScheduledLiveVideoCallAdapter = new ContestantFutureScheduledLiveVideoCallAdapter(mContext, mFutureScheduledLiveVideoCallList);
                    mContestantFutureScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerFutureSchdule);
                    mContestantFutureScheduledLiveVideoCallRV.setAdapter(mContestantFutureScheduledLiveVideoCallAdapter);
                }
            }else{
                //mContestantFutureScheduledLiveVideoCallRV.setVisibility(View.GONE);
                noDataFutureView.setVisibility(View.VISIBLE);
            }

            pullToRefresh.setRefreshing(false);
        }catch(Exception ex){
            Log.i("ContestantSchduleEx",ex.toString());
        }
    }

    @Override
    public void SetContestantDeleteSchduleSucess(String mesage) {
        mContestantScheduledLiveVideoCallActivityPresenter.GetContestantSchdule(mContext);
        MyApplication.showSimpleSnackBarSucess(mContext,"Schdule Deleted");
    }

    @Override
    public void SetContestantDeleteSchduleFiled(String mesage) {
        MyApplication.showSimpleSnackBar(mContext,"Schdule Delete Failed");
    }

    @Override
    public void setPresenter(ContestantScheduledLiveVideoCallActivityContract.Presenter mPresenter) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onBackPressed() {

        finish();
    }

    public void onBackClick(View view) {

        finish();
    }
    public void UpdateNotifySchdule(){
        try{

            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDate = sdf1.format(new Date());
            List<ScheduledLiveVideoCall> mTodayScheduledLiveVideoCallList = mContestantScheduledLiveVideoCallActivityPresenter.GetContestantTodaySchduleLocalDB(currentDate);
            List<ScheduledLiveVideoCall> mFutureScheduledLiveVideoCallList = mContestantScheduledLiveVideoCallActivityPresenter.GetContestantFutureSchduleLocalDB(currentDate);
            if(mTodayScheduledLiveVideoCallList!=null ){
                todaySchdule.setVisibility(View.VISIBLE);
                mContestantTodayScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
                if(mContestantTodayScheduledLiveVideoCallAdapter !=null){
                    mContestantTodayScheduledLiveVideoCallAdapter.updateAdapter(mTodayScheduledLiveVideoCallList);
                }else{
                    mContestantTodayScheduledLiveVideoCallAdapter = new ContestantTodayScheduledLiveVideoCallAdapter(mContext, mTodayScheduledLiveVideoCallList);
                    mContestantTodayScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerTodaySchdule);
                    mContestantTodayScheduledLiveVideoCallRV.setAdapter(mContestantTodayScheduledLiveVideoCallAdapter);
                }
            }else{
                todaySchdule.setVisibility(View.GONE);
                mContestantTodayScheduledLiveVideoCallRV.setVisibility(View.GONE);
            }

            if(mFutureScheduledLiveVideoCallList!=null ){
                FutureSchdule.setVisibility(View.VISIBLE);
                mContestantFutureScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
                if(mContestantFutureScheduledLiveVideoCallAdapter !=null){
                    mContestantFutureScheduledLiveVideoCallAdapter.updateAdapter(mFutureScheduledLiveVideoCallList);
                }else{
                    mContestantFutureScheduledLiveVideoCallAdapter = new ContestantFutureScheduledLiveVideoCallAdapter(mContext, mFutureScheduledLiveVideoCallList);
                    mContestantFutureScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerFutureSchdule);
                    mContestantFutureScheduledLiveVideoCallRV.setAdapter(mContestantFutureScheduledLiveVideoCallAdapter);
                }
            }else{
                mContestantFutureScheduledLiveVideoCallRV.setVisibility(View.GONE);
                FutureSchdule.setVisibility(View.GONE);
            }

            pullToRefresh.setRefreshing(false);
        }catch(Exception ex){
            Log.i("ContestantSchduleEx",ex.toString());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(mContext).registerReceiver(DeleteSchduleSlot,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_DELETE_SCHDULE_SLOT"));


    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(DeleteSchduleSlot);
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
