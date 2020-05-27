package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter.ContestantDaySchduleAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.Adapter.ContestantDayStatusZoneSchduleAdapter;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelDayList.DaySchduleList;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.BookSchduleSlots;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.SchduleSlots;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantSchduledLiveVideoCallActivity;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.adapter.SectionedGridRecyclerViewAdapter;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;


public class DaySchduleActivity extends AppCompatActivity implements ContestantScheduleDayActivityContract.View{

    ContestantDayStatusZoneSchduleAdapter contestantDaySchduleAdapter;
    RecyclerView DaySchduleRV;
    LinearLayoutManager mDaySchduleLayout;
    View mNoDataView;
    Context mContext;
    ContestantScheduleLiveActivityPresenter mContestantScheduleLiveActivityPresenter;
   /* List<String> DayTimes1,DayTimes2,DayTimes3;
    List<DaySchduleList> daySchduleLists;*/

    List<SchduleSlots> mContestantDaySchdulesSlotsList;
    String mCommentReschdule =null;

    int mReschduleSlot=0;
    String SelectedDate = null;
    int mSlotID =0;

    int reservedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_schdule);

        mContext = DaySchduleActivity.this;

        DaySchduleRV = findViewById(R.id.ScheduledLiveVideoCallDaySchduleRV);
        mNoDataView = findViewById(R.id.NoDataView);

        mContestantScheduleLiveActivityPresenter =new ContestantScheduleLiveActivityPresenter(DaySchduleActivity.this);
        Bundle bundle = getIntent().getExtras();
        SelectedDate = bundle.getString("SchduleDate");



        mContestantScheduleLiveActivityPresenter.GetContestantDaySchduleSlots(mContext,SelectedDate);
        Calendar startDate = Calendar.getInstance();
        //startDate.add(Calendar.MONTH, -1);
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
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
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                String SelectedDateStr = sdf.format(date.getTime());
                String Postion = String.valueOf(position);
                mContestantScheduleLiveActivityPresenter.GetContestantDaySchduleSlots(mContext,SelectedDateStr);
                SelectedDate = SelectedDateStr;
            }
        });

    }

    private final GCoreWakefulBroadcastReceiver BookSchduleSlot = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try{
                int slotID = intent.getIntExtra("slotID",0);

                Realm_User mUser = mContestantScheduleLiveActivityPresenter.provideUserLocal(mContext);
                Calendar cal = Calendar.getInstance();
                TimeZone tz = cal.getTimeZone();
                Competition CompitionDate = Repository.getCompitionDate();
                BookSchduleSlots bookSchduleSlots =new BookSchduleSlots();
                bookSchduleSlots.setUserId(Integer.valueOf(mUser.getId()));
                bookSchduleSlots.setSlotId(slotID);
                bookSchduleSlots.setContestId(CompitionDate.getId());
                bookSchduleSlots.setStatus("reserved");
                bookSchduleSlots.setTimezone(tz.getID());
                mContestantScheduleLiveActivityPresenter.PostBookedSchduleSlot(mContext,bookSchduleSlots);

            }catch (Exception ex){
                Log.i("ReSchduleEX",ex.toString());
            }
        }
    };


    private final GCoreWakefulBroadcastReceiver ReBookSchduleSlot = new GCoreWakefulBroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try{
                int slotID = intent.getIntExtra("slotID",0);
                String CommentReSchdule = intent.getStringExtra("Comment");

                if(CommentReSchdule.isEmpty()){
                    mCommentReschdule=null;
                }else{
                    mCommentReschdule = CommentReSchdule;
                }
                mContestantScheduleLiveActivityPresenter.DeleteContestantSchdule(mContext);
                mReschduleSlot = slotID;

            }catch (Exception ex){
                Log.i("ReSchduleEX",ex.toString());
            }
        }
    };
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(mContext, ContestantSchduledLiveVideoCallActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackClick(View view) {
        Intent intent = new Intent(mContext, ContestantSchduledLiveVideoCallActivity.class);
        startActivity(intent);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void SetContestantReSchduleSucess(String mesage,List<SchduleSlots> ContestantDaySchdulesSlotsList) {


        if(ContestantDaySchdulesSlotsList!=null){
            mNoDataView.setVisibility(View.GONE);


            List<String> DayStaus = new ArrayList();

            for(int i=0;i<ContestantDaySchdulesSlotsList.size();i++){
                DayStaus.add(ContestantDaySchdulesSlotsList.get(i).getPartsOfDay().toLowerCase());
            }
            List<String> DistinctDaystatus = DayStaus.stream()
                    .distinct()
                    .collect(Collectors.toList());

            List<SectionedGridRecyclerViewAdapter.Section> sections =
                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();
            for(int i=0;i<DistinctDaystatus.size();i++){
                for(int j=0;j<ContestantDaySchdulesSlotsList.size();j++){
                    if(ContestantDaySchdulesSlotsList.get(j).getPartsOfDay().toLowerCase().equals(DistinctDaystatus.get(i))){
                        sections.add(new SectionedGridRecyclerViewAdapter.Section(j, DistinctDaystatus.get(i).toUpperCase()));
                        break;
                    }
                }
            }
            List<SchduleSlots> mContestantDaySchdulesSlotsList =  mContestantScheduleLiveActivityPresenter.GetContestantDaySchduleSlotsStatus(SelectedDate,ContestantDaySchdulesSlotsList);

            for(int i=0;i<mContestantDaySchdulesSlotsList.size();i++){
                if(mContestantDaySchdulesSlotsList.get(i).getStatus().equals("reserved")){
                    reservedPosition = i;
                    mSlotID = mContestantDaySchdulesSlotsList.get(i).getId();
                }
            }
            Boolean WeekSlotBookStatus = mContestantScheduleLiveActivityPresenter.GetBookedSlotOfWeek();


            mDaySchduleLayout = new GridLayoutManager(mContext,2);
            mDaySchduleLayout.setOrientation(LinearLayoutManager.VERTICAL);
            contestantDaySchduleAdapter = new ContestantDayStatusZoneSchduleAdapter(mContext,reservedPosition,WeekSlotBookStatus,mContestantDaySchdulesSlotsList);
            DaySchduleRV.setLayoutManager(mDaySchduleLayout);
            //Add your adapter to the sectionAdapter
            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                    SectionedGridRecyclerViewAdapter(mContext,R.layout.schdule_headers,R.id.Header,DaySchduleRV,contestantDaySchduleAdapter);
            mSectionedAdapter.setSections(sections.toArray(dummy));

            DaySchduleRV.setAdapter(mSectionedAdapter);

        }else{
            mNoDataView.setVisibility(View.VISIBLE);
        }

            }


    @Override
    public void SetContestantReSchduleFiled(String mesage) {
       // MyApplication.showSimpleSnackBar(mContext,mesage);
        mNoDataView.setVisibility(View.VISIBLE);
    }

    @Override
    public void SetContestantBookedSlotSucess(String message) {

        //mContestantScheduleLiveActivityPresenter.GetContestantSchdule(mContext);
        mContestantScheduleLiveActivityPresenter.GetContestantSchdule(mContext);

    }

    @Override
    public void SetContestantBookedSlotFailed(String message) {

        MyApplication.showSimpleSnackBar(mContext,message);
    }

    @Override
    public void SetContestantSchduleSucess(String mesage) {
      /*  SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDate = sdf1.format(new Date());*/
        mContestantScheduleLiveActivityPresenter.GetContestantDaySchduleSlots(mContext,SelectedDate);
        MyApplication.showSimpleSnackBarSucess(mContext,"Schdule Booked");
    }

    @Override
    public void SetContestantSchduleFiled(String mesage) {

    }

    @Override
    public void SetContestantDeleteSchduleSucess(String mesage) {
        Realm_User mUser = mContestantScheduleLiveActivityPresenter.provideUserLocal(mContext);
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Competition CompitionDate = Repository.getCompitionDate();
        BookSchduleSlots bookSchduleSlots =new BookSchduleSlots();
        bookSchduleSlots.setUserId(Integer.valueOf(mUser.getId()));
        bookSchduleSlots.setSlotId(mReschduleSlot);
        bookSchduleSlots.setContestId(CompitionDate.getId());
        bookSchduleSlots.setComment(mCommentReschdule);
        bookSchduleSlots.setStatus("reserved");
        bookSchduleSlots.setTimezone(tz.getID());
        mContestantScheduleLiveActivityPresenter.PostBookedSchduleSlot(mContext,bookSchduleSlots);
        mReschduleSlot=0;
    }

    @Override
    public void SetContestantDeleteSchduleFiled(String mesage) {

    }

    @Override
    public void setPresenter(ContestantScheduleDayActivityContract.Presenter mPresenter) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(mContext).registerReceiver(BookSchduleSlot,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_BOOK_SCHDULE_SLOT"));

        LocalBroadcastManager.getInstance(mContext).registerReceiver(ReBookSchduleSlot,
                new IntentFilter("com.Rikskampen.CUSTOM_INTENT_RE_BOOK_SCHDULE_SLOT"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(BookSchduleSlot);
        LocalBroadcastManager.getInstance(mContext).unregisterReceiver(ReBookSchduleSlot);
    }
}
