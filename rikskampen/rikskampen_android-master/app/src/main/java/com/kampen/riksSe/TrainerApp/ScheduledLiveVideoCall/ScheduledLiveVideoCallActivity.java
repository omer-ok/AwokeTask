package com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter.ContestantTodayScheduledLiveVideoCallAdapter;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.Adapter.TrainerScheduledLiveVideoCallAdapter;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantSchduledLiveVideoCallActivity;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.login.ModelsV3.UserRoles;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class ScheduledLiveVideoCallActivity extends AppCompatActivity implements  ScheduledLiveVideoCallActivityContract.View {

    Context mContext;
    ScheduledLiveVideoCallActivityPresenter scheduledLiveVideoCallActivityPresenter;
    LinearLayoutManager mLayoutManagerSchdule;
    public RecyclerView mScheduledLiveVideoCallRV;
    private TrainerScheduledLiveVideoCallAdapter mTrainerScheduledLiveVideoCallAdapter;
    private ContestantTodayScheduledLiveVideoCallAdapter mContestantTodayScheduledLiveVideoCallAdapter;
    View NoDataView;
    Realm_User mUser;
    FloatingActionButton mLogOutTrainer;
    SwipeRefreshLayout pullToRefresh;
    String GlobaleSelectedDate =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_home);
        mContext= ScheduledLiveVideoCallActivity.this;
        scheduledLiveVideoCallActivityPresenter = new ScheduledLiveVideoCallActivityPresenter(ScheduledLiveVideoCallActivity.this);
        mScheduledLiveVideoCallRV = findViewById(R.id.ScheduledLiveVideoCallRV);
        mLogOutTrainer = findViewById(R.id.logout_trainer);
        NoDataView = findViewById(R.id.NoDataView1);
        mLayoutManagerSchdule = new LinearLayoutManager(mContext);
        mUser = scheduledLiveVideoCallActivityPresenter.provideUserLocal(mContext);
        pullToRefresh = findViewById(R.id.pullToRefresh);

       // UserRoles userRoles = Repository.getUserRole(mUser.getUserRoleID());

     /*   if(userRoles!=null){
            if(userRoles.getName().equals("Trainer")){

            }else if(userRoles.getName().equals("Contestant")){
               // scheduledLiveVideoCallActivityPresenter.GetContestantSchdule(mContext);
            }
        }*/
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA}, 1);
            }
        }else{
                  /*  Intent i = new Intent(getActivity(), LiveVideoCall.class);
                    startActivity(i);*/

     /*       Intent i = new Intent(mContext, ContestantSchduledLiveVideoCallActivity.class);
            startActivity(i);*/
        }

        scheduledLiveVideoCallActivityPresenter.GetTrainerSchdule(mContext);


        Calendar startDate = Calendar.getInstance();
        //startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_WEEK_IN_MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate,endDate)
                .datesNumberOnScreen(5)   // Number of Dates cells shown on screen (default to 5).
                .configure()    // starts configuration.
                .formatTopText("EEE")       // default to "MMM".
                .formatMiddleText("dd")    // default to "dd".
                /*.formatBottomText("EEE") */   // default to "EEE".
                .showTopText(true)              // show or hide TopText (default to true).
                .showBottomText(false)
                .end()          // ends configuration.
                .defaultSelectedDate(startDate)    // Date to be selected at start (default to current day `Calendar.getInstance()`).
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                //do something
                //  Toast.makeText(mContext, , Toast.LENGTH_SHORT).show();
                List<ScheduledLiveVideoCall> scheduledLiveVideoCallList=new ArrayList();
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                String SelectedDateStr = sdf.format(date.getTime());
                String currentDate = sdf.format(new Date());
                Boolean CurrentDateStatus =false;
                GlobaleSelectedDate =SelectedDateStr;
                if(SelectedDateStr.equals(currentDate)){
                    scheduledLiveVideoCallList = scheduledLiveVideoCallActivityPresenter.GetTrainerTodaySchduleLocalDB(mContext,currentDate);
                    CurrentDateStatus=true;
                }else{
                    scheduledLiveVideoCallList = scheduledLiveVideoCallActivityPresenter.GetTrainerFutureSchduleLocalDB(mContext,SelectedDateStr);
                    CurrentDateStatus=false;
                }

                if(scheduledLiveVideoCallList !=null){
                    mScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
                    NoDataView.setVisibility(View.GONE);
                    if(mTrainerScheduledLiveVideoCallAdapter !=null){
                        mTrainerScheduledLiveVideoCallAdapter.updateAdapter(scheduledLiveVideoCallList,CurrentDateStatus);
                    }else{
                        mTrainerScheduledLiveVideoCallAdapter = new TrainerScheduledLiveVideoCallAdapter(mContext,CurrentDateStatus, scheduledLiveVideoCallList);
                        mScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerSchdule);
                        mScheduledLiveVideoCallRV.setAdapter(mTrainerScheduledLiveVideoCallAdapter);
                    }

                }else{
                    mScheduledLiveVideoCallRV.setVisibility(View.GONE);
                    NoDataView.setVisibility(View.VISIBLE);
                }
            }
        });


        mLogOutTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Logga ut")
                        .setMessage(getResources().getString(R.string.MoreModuel_Logout_Sure))
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ProgressManager.showProgress(mContext,getResources().getString(R.string.progress_dialog_message));

                                scheduledLiveVideoCallActivityPresenter.performLogout(mContext);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                scheduledLiveVideoCallActivityPresenter.GetTrainerSchdule(mContext);
            }
        });
    }

    @Override
    public void SetTrainerSchduleSucess(String mesage) {

        //MyApplication.showSimpleSnackBarSucess(mContext,mesage);
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDate = sdf1.format(new Date());
        List<ScheduledLiveVideoCall> scheduledLiveVideoCallList=new ArrayList();
        Boolean CurrentDateStatus =false;
        if(GlobaleSelectedDate!=null){
            if(GlobaleSelectedDate.equals(currentDate)){
                scheduledLiveVideoCallList = scheduledLiveVideoCallActivityPresenter.GetTrainerTodaySchduleLocalDB(mContext,currentDate);
                CurrentDateStatus=true;
            }else{
                scheduledLiveVideoCallList = scheduledLiveVideoCallActivityPresenter.GetTrainerFutureSchduleLocalDB(mContext,GlobaleSelectedDate);
                CurrentDateStatus=false;
            }
        }else{
            scheduledLiveVideoCallList = scheduledLiveVideoCallActivityPresenter.GetTrainerTodaySchduleLocalDB(mContext,currentDate);
            CurrentDateStatus=true;
        }



        if(scheduledLiveVideoCallList !=null){
            mScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
            NoDataView.setVisibility(View.GONE);
            if(mTrainerScheduledLiveVideoCallAdapter !=null){
                mTrainerScheduledLiveVideoCallAdapter.updateAdapter(scheduledLiveVideoCallList,CurrentDateStatus);
            }else{
                mTrainerScheduledLiveVideoCallAdapter = new TrainerScheduledLiveVideoCallAdapter(mContext,CurrentDateStatus, scheduledLiveVideoCallList);
                mScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerSchdule);
                mScheduledLiveVideoCallRV.setAdapter(mTrainerScheduledLiveVideoCallAdapter);
            }
        }else{
            mScheduledLiveVideoCallRV.setVisibility(View.GONE);
            NoDataView.setVisibility(View.VISIBLE);
        }
        pullToRefresh.setRefreshing(false);
    }

    @Override
    public void SetTrainerSchduleFiled(String mesage) {
        MyApplication.showSimpleSnackBar(mContext,mesage);
        pullToRefresh.setRefreshing(false);
    }

    @Override
    public void SetContestantSchduleSucess(String mesage) {
/*        List<ScheduledLiveVideoCall> scheduledLiveVideoCallList = scheduledLiveVideoCallActivityPresenter.GetContestantSchduleLocalDB(mContext);
        if(scheduledLiveVideoCallList !=null){
            mScheduledLiveVideoCallRV.setVisibility(View.VISIBLE);
            NoDataView.setVisibility(View.GONE);
            if(mContestantScheduledLiveVideoCallAdapter !=null){
                mContestantScheduledLiveVideoCallAdapter.updateAdapter(scheduledLiveVideoCallList);
            }else{
                mContestantScheduledLiveVideoCallAdapter = new ContestantScheduledLiveVideoCallAdapter(mContext, scheduledLiveVideoCallList);
                mScheduledLiveVideoCallRV.setLayoutManager(mLayoutManagerSchdule);
                mScheduledLiveVideoCallRV.setAdapter(mContestantScheduledLiveVideoCallAdapter);
            }
        }else{
            mScheduledLiveVideoCallRV.setVisibility(View.GONE);
            NoDataView.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public void SetContestantSchduleFiled(String mesage) {

    }

    @Override
    public void setLogoutSuccess(String message) {
        ProgressManager.hideProgress();
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void setLogoutFailed(String message) {
        ProgressManager.hideProgress();
        MyApplication.showSimpleSnackBar(mContext,message);
    }

    @Override
    public void setPresenter(ScheduledLiveVideoCallActivityContract.Presenter mPresenter) {

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

           /* Intent i = new Intent(mContext, ContestantSchduledLiveVideoCallActivity.class);
            startActivity(i);*/
        }

    }
}
