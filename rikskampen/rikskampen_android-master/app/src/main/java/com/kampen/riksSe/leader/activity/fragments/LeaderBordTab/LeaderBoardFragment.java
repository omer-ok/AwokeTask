package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.CaloriesDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StarsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StepsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.ContestantUser;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.StepsData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.TopContestant;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserCalories;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserStars;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserSteps;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import io.realm.Realm;

import static com.kampen.riksSe.utils.UtilityTz.convertStaticTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeaderBoardFragment
 * to handle interaction events.
 */


public class LeaderBoardFragment extends Fragment implements LeaderBoardContract.View {

    private RecyclerView mLeaderRecyclerView;
    private LeaderAdapterSteps mLeaderAdapter;
    private LeaderAdapterCalories mLeaderAdapterCal;
    private LeaderAdapterStar mLeaderAdapterStar;

    int ChatValueCounter;

    private TabLayout mTablayout;


    private ImageView  mProfileImage,mCalStepImage;

    private TextView   mCalStepTV,mPositionTV,userName,userSteps,userStars,userCalories,caloresUnit,distanceUnit,userdistance,userWeight,userWaist,userRank;

    private TextView tabLeft,tabRight,tabMiddle,tabLeftText,tabMiddleText,tabRightText;

    private View mtabLeftView,mtabMiddleView,mtabRightView;

    private View     steps_icon_container;

    private View      leftContainer1;

    private  ImageView profileImage;

    private View topView1,topView2,topView3,topView4,topView5;

    ImageView top1,top2,top3,top4,top5;
    TextView top1Name,top2Name,top3Name,top4Name,top5Name;

    public  static int selectedTab=0;

    private View ChatNotification;
    private TextView valueText;

    Realm_User mUser;

    LeaderBoardFragPresenter leaderBoardFragPresenter;
    SwipeRefreshLayout pullToRefresh;
    NotificationBadge badge;


    ArrayList<UserSteps> Steps;
    ArrayList<UserStars> Stars;
    ArrayList<UserCalories> Calories;

    List<TopContestant> topContestants1 = new ArrayList<>();
    List<TopContestant> topContestants2 = new ArrayList<>();
    List<TopContestant> topContestants3 = new ArrayList<>();

    LeaderBoardAllData leaderBoardAllDataUserValue;
    LeaderBoardAllDataV3 leaderBoardAllDataUserValueV3;

    Boolean ContestStatus;
    View mCompititionCounter;
    TextView mDayCounter,mHourCounter,mMiniuteCounter,mSecondsCounter;
    View TimerView;
    TextView Heading,mLeaderBoardTitle;
    Boolean mFragmentState;

   /* Boolean ContestStatus;
    ContestWeekDayTimeModel contestDayTimeModel;*/

    //String currentDateandTime;


    public LeaderBoardFragment() {

    }


    public static LeaderBoardFragment newInstance() {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home_leader, container, false);

    }



    public  void manageTabLayout()
    {


        mTablayout.addTab(mTablayout.newTab().setText("Steps"));
        mTablayout.addTab(mTablayout.newTab().setText("Calories"));

        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(mTablayout.getSelectedTabPosition() == 0){
                    if(mLeaderAdapter==null)
                    {

                        mLeaderAdapter = new LeaderAdapterSteps(getActivity());

                    }

                    mLeaderRecyclerView.setAdapter(mLeaderAdapter);

                }else if(mTablayout.getSelectedTabPosition() == 1) {

                    if(mLeaderAdapterCal==null)
                    {

                        mLeaderAdapterCal=new LeaderAdapterCalories(getActivity());

                    }

                    mLeaderRecyclerView.setAdapter(mLeaderAdapterCal);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);



        mProfileImage = rootView.findViewById(R.id.profile_image);
        //steps_icon_container = rootView.findViewById(R.id.steps_icon_container);
        leftContainer1 = rootView.findViewById(R.id.leftContainer1);
        mLeaderBoardTitle = rootView.findViewById(R.id.leaderBoardTitle);
        //mCalStepImage= rootView.findViewById(R.id.steps_icon);
        //mCalStepTV   = rootView.findViewById(R.id.progress);
        //mPositionTV   = rootView.findViewById(R.id.position);
        userName   = rootView.findViewById(R.id.textView6);
        userSteps   = rootView.findViewById(R.id.stepsValue);
        userStars   = rootView.findViewById(R.id.starValue);
        userCalories   = rootView.findViewById(R.id.calValue);
        userdistance   = rootView.findViewById(R.id.distanceValue);
        userWeight   = rootView.findViewById(R.id.weightValue);
        userWaist   = rootView.findViewById(R.id.waistValue);
        userRank   = rootView.findViewById(R.id.rankValue);
        caloresUnit   = rootView.findViewById(R.id.caloriesUnit);
        distanceUnit   = rootView.findViewById(R.id.distanceUnit);

        topView1 = rootView.findViewById(R.id.top1);
        topView2 = rootView.findViewById(R.id.top2);
        topView3 = rootView.findViewById(R.id.top3);
        topView4 = rootView.findViewById(R.id.top4);
        topView5 = rootView.findViewById(R.id.top5);

        top1 = rootView.findViewById(R.id.topPositionOne);
        top2 = rootView.findViewById(R.id.topPositionTwo);
        top3 = rootView.findViewById(R.id.topPositionThree);
        top4 = rootView.findViewById(R.id.topPositionFour);
        top5 = rootView.findViewById(R.id.topPositionFive);

        top1Name = rootView.findViewById(R.id.top1name);
        top2Name = rootView.findViewById(R.id.top2name);
        top3Name = rootView.findViewById(R.id.top3name);
        top4Name = rootView.findViewById(R.id.top4name);
        top5Name = rootView.findViewById(R.id.top5name);
        badge = rootView.findViewById(R.id.chatNotify);
        profileImage=rootView.findViewById(R.id.profileImage);

        mCompititionCounter = rootView.findViewById(R.id.compititionCounter);
        mDayCounter = rootView.findViewById(R.id.dayCounter);
        mHourCounter = rootView.findViewById(R.id.hourCounter);
        mMiniuteCounter = rootView.findViewById(R.id.miniuteCounter);
        mSecondsCounter = rootView.findViewById(R.id.secondsCounter);

        ChatNotification=rootView.findViewById(R.id.chatLayout);
        //chatNotifyValue=(TextView) rootView.findViewById(R.id.chatNotify);
        valueText=(TextView) rootView.findViewById(R.id.textlabel);
//        mCalStepImage.bringToFront();
        Heading = rootView.findViewById(R.id.hedaing);
        TimerView = rootView.findViewById(R.id.timerView);
        Steps = new ArrayList<>();
        Stars = new ArrayList<>();
        Calories = new ArrayList<>();

        pullToRefresh = rootView.findViewById(R.id.pullToRefresh);

        leaderBoardFragPresenter=new LeaderBoardFragPresenter(this);



        //ProgressManager.showProgress(getActivity(),"Uppdatering av Ledare");

        //leaderBoardFragPresenter.getLeaderBoardAllData(getActivity());
        mFragmentState=true;
        //leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());

        List<ActivityDaily> pastActivities = leaderBoardFragPresenter.GetPastActivities(getContext());

        if(pastActivities!=null){
            leaderBoardFragPresenter.SyncPastActivities(getContext(),pastActivities);
        }else{
            leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());
        }

        badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));

        /*try{

            if(SaveSharedPreference.getChatNotifictationCounterZero(getContext())){
                badge.setNumber(SaveSharedPreference.getChatNotificationCounterCurrent(getContext()));
            }else{
                badge.setNumber(0);
            }

        }catch (Exception e){

        }
*/








        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshData(); // your code

                //ProgressManager.showProgress(getActivity(),"Uppdatering av Ledare");

                //leaderBoardFragPresenter.getLeaderBoardAllData(getActivity());
                //leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());
                List<ActivityDaily> pastActivities = leaderBoardFragPresenter.GetPastActivities(getContext());

                if(pastActivities!=null){
                    leaderBoardFragPresenter.SyncPastActivities(getContext(),pastActivities);
                }else{
                    leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());
                }
            }
        });

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



        tabLeft=(TextView) rootView.findViewById(R.id.tabLeft);
        tabLeftText=(TextView) rootView.findViewById(R.id.tabLeft1);
        tabMiddle=rootView.findViewById(R.id.tabMiddle);
        tabRight=(TextView) rootView.findViewById(R.id.tabRight);

        mtabLeftView = rootView.findViewById(R.id.tabLeftView);
        mtabMiddleView = rootView.findViewById(R.id.tabMiddleView);
        mtabRightView = rootView.findViewById(R.id.tabRightView);

        mLeaderRecyclerView = (RecyclerView) rootView.findViewById(R.id.leaderRV);
        mTablayout          = (TabLayout) rootView.findViewById(R.id.step_cal_tab);

        String [] params=SaveSharedPreference.getLoggedParams(getContext());
        mUser=Repository.provideUserLocal(params[0],params[1]);


        LinearLayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

        //manageTabLayout();

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate!=null){
            if(CompitionDate.getmName()!=null){
                mLeaderBoardTitle.setText(getResources().getString(R.string.LeaderboardModule_leaderboard)+" "+getResources().getString(R.string.General_for)+" "+CompitionDate.getmName().toUpperCase());
            }else{
                mLeaderBoardTitle.setText(getResources().getString(R.string.LeaderboardModule_leaderboard));
            }

            if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                ContestStatus = getCompitionStartDate(getContext(), CompitionDate.getStartDate(),currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),CompitionDate.getmEndDate(),currentDateandTime);

                if(ContestStatus && !ContestEndStatus){
                    mCompititionCounter.setVisibility(View.GONE);
                }else if(ContestEndStatus){
                    mCompititionCounter.setVisibility(View.GONE);
                }else if(!ContestStatus){
                    mCompititionCounter.setVisibility(View.VISIBLE);
                }
            }else{
                /*mCompititionCounter.setVisibility(View.VISIBLE);
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



        mLeaderAdapter = new LeaderAdapterSteps(getActivity());
        mLeaderAdapterStar = new LeaderAdapterStar(getActivity());
        mLeaderAdapterCal= new LeaderAdapterCalories(getActivity());

       // mCalStepImage.setImageResource(R.mipmap.ic_feeet);
       // steps_icon_container.bringToFront();
//        mCalStepTV.setText(mUser.getF_name() + "");
        userName.setText(mUser.getF_name() );


        mLeaderRecyclerView.setLayoutManager(mLayoutManager1);
        mLeaderRecyclerView.setAdapter(mLeaderAdapter);


        manageTab();












    }

    @Override
    public void onPause() {
        super.onPause();
        mFragmentState=false;
    }

    @Override
    public void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiverBadges,
                new IntentFilter("com.rikskampen.CUSTOM_INTENT_CHAT_BADGE"));

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(CountDownCompitition,
                new IntentFilter("com.tutorialspoint.CUSTOM_COMPITITION_COUNTER"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(LeaderBoardRefreshClick,
                new IntentFilter("com.tutorialspoint.CUSTOM_LEADERBOARD_REFRESH_CLICK"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(LeaderBoardStateCheck,
                new IntentFilter("com.tutorialspoint.CUSTOM_LEADERBOARD_STAET_CHECK"));
    }


    @Override
    public void onStop() {

        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiverBadges);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(CountDownCompitition);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(LeaderBoardRefreshClick);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(LeaderBoardStateCheck);

    }

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
    private final BroadcastReceiver LeaderBoardRefreshClick = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            try{
                /*if(mFragmentState){
                    //ProgressManager.showProgress(getActivity(),"Uppdatering av Ledare");
                    //leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());
                    List<ActivityDaily> pastActivities = leaderBoardFragPresenter.GetPastActivities(getContext());

                    if(pastActivities!=null){
                        leaderBoardFragPresenter.SyncPastActivities(getContext(),pastActivities);
                    }else{
                        leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());
                    }
                    mFragmentState=false;
                }*/
                List<ActivityDaily> pastActivities = leaderBoardFragPresenter.GetPastActivities(getContext());

                if(pastActivities!=null){
                    leaderBoardFragPresenter.SyncPastActivities(getContext(),pastActivities);
                }else{
                    leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());
                }

            }catch (Exception e){

            }

        }
    };

    private final BroadcastReceiver LeaderBoardStateCheck = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            try{

                mFragmentState=true;

            }catch (Exception e){

            }

        }
    };

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
                            mCompititionCounter.setVisibility(View.GONE);
                        }
                    }
                });


            }catch (Exception e){

            }

        }
    };



    @Override
    public void onResume() {
        super.onResume();
        setUser();
        try{

            /*if(SaveSharedPreference.getChatNotifictationCounterZero(getContext())){
                badge.setNumber(SaveSharedPreference.getChatNotificationCounterCurrent(getContext()));
            }else{
                badge.setNumber(0);
            }*/

            badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));

        }catch (Exception e){

        }

        /*ChatValueCounter = SaveSharedPreference.getChatNotificationCounter(getContext());
        chatNotifyValue.setText(ChatValueCounter+"");*/

    }

    private  void setUser()
    {


        if(mUser!=null)
        {
            Constants.setProfileImage(mUser.getProfile_image(),mUser.getProfilePicData(),mProfileImage,getContext());
        }
    }



    private  void manageTab()
    {

        if(selectedTab==0)
        {
            tabLeft.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            tabLeft.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
            tabRight.setTextColor(Color.parseColor("#c8a167"));
            tabMiddle.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
            tabMiddle.setTextColor(Color.parseColor("#c8a167"));

            //setAnimationRight(tabLeft);
        }
        else if(selectedTab==1)
        {
            tabMiddle.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            tabMiddle.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
            tabRight.setTextColor(Color.parseColor("#c8a167"));
            tabLeft.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
            //setAnimationLeft(tabMiddle);
        }
        else
        {
            tabLeft.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
            tabRight.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            tabRight.setTextColor(Color.WHITE);
            tabMiddle.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
            tabMiddle.setTextColor(Color.parseColor("#c8a167"));
            //setAnimationLeft(tabRight);
        }



        tabLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mtabLeftView.bringToFront();
                setAnimationLeft(mtabLeftView);

                selectedTab=0;
                tabLeft.bringToFront();
                tabLeft.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                tabLeft.setTextColor(Color.WHITE);
                tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabRight.setTextColor(Color.parseColor("#c8a167"));
                tabMiddle.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabMiddle.setTextColor(Color.parseColor("#c8a167"));


                if(mLeaderAdapter==null)
                {

                    mLeaderAdapter=new LeaderAdapterSteps(getActivity());

                }




                int stepCount = SaveSharedPreference.getUserTodaySteps(getContext());

                String caloriesCount = Repository.CaloriesCalulatorFromSteps(Double.parseDouble(String.valueOf(mUser.getHeight_in_cm())),Double.parseDouble(String.valueOf(mUser.getWeight())),Double.parseDouble(String.valueOf(stepCount)));

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

                String currentDateandTime = sdf.format(new Date());

                String starCount = mUser.getStars_count();

                if(starCount ==null){
                    starCount="0";
                }



                try {

                    if(leaderBoardAllDataUserValueV3.getUserData()!=null){

                      /*  int calCount = Integer.parseInt(String.format("%.0f",leaderBoardAllDataUserValue.getUserData().getUser().getCalories()));

                        if(calCount >1000){
                            userCalories.setText(calCount/1000 + "");
                    }else{

                    }*/
                        userName.setText(mUser.getF_name());
                        long userStepsVal = leaderBoardAllDataUserValueV3.getUserData().getStepCount();

                        //userSteps.setText(userStepsVal+"");
                        if(userStepsVal < 1000){
                            userSteps.setText(userStepsVal+"");
                        }else if(userStepsVal < 1000000){
                            double stepsValue = Math.abs(userStepsVal)/1000.0;
                            userSteps.setText(String.format("%.2f",stepsValue)+""+"K");
                        }else if(userStepsVal < 100000000){
                            double stepsValue = Math.abs(userStepsVal)/1000000.0;
                            userSteps.setText(String.format("%.2f",stepsValue)+""+"M");
                        }else{
                            double stepsValue = Math.abs(userStepsVal)/100000000.0;
                            userSteps.setText(String.format("%.2f",stepsValue)+""+"B");
                        }
                        userStars.setText(leaderBoardAllDataUserValueV3.getUserData().getStarCount() + "");
                        double calories = leaderBoardAllDataUserValueV3.getUserData().getCaloriesCount();
                        if(calories>999){
                            caloresUnit.setText("(Kcal)");
                            double CaloriesValue = calories/1000.0;
                            userCalories.setText(String.format("%.2f",CaloriesValue) +""+"K");
                        }else{
                            caloresUnit.setText("(Kcal)");
                            userCalories.setText(calories+"");
                        }
                        double distance = leaderBoardAllDataUserValueV3.getUserData().getDistanceSum();
                        if(distance>999){
                            distanceUnit.setText("(Km)");
                            double DistanceValue = distance/1000.0;
                            userdistance.setText(String.format("%.2f",DistanceValue)+""+"K");
                        }else{
                            distanceUnit.setText("(Km)");
                            double DistanceValue = distance/1000.0;
                            userdistance.setText(String.format("%.2f",DistanceValue)+"");
                        }
                        userRank.setText("#"+leaderBoardAllDataUserValueV3.getUserData().getStepsRank() + "");
                        /*distanceUnit.setText(""+"("+leaderBoardAllDataUserValueV3.getUserData().getUser().getDistanceUnit()+")"+"");
                        caloresUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getCaloriesUnit()+")"+"");*/
                        userWeight.setText(SaveSharedPreference.getWeightToday(getContext()) + "");
                        userWaist.setText(SaveSharedPreference.getWaistToday(getContext()) + "");

                    }else{
                        userName.setText("0");
                        userSteps.setText("0");
                        userStars.setText("0");
                        userCalories.setText("0");
                        userRank.setText("0");

                    }

                }catch (Exception e){

                }


                mLeaderRecyclerView.setAdapter(mLeaderAdapter);


                try {

                    if(topContestants1.isEmpty()){
                        valueText.setText("");
                        setTop5ContestentView(topContestants1);
                        //setTop5ViewInvisable();
                    }else{
                        setTop5ContestentView(topContestants1);
                        valueText.setText("");
                        //setTop5ViewVisable();
                    }


                }catch (Exception ex){
                    ex.getStackTrace();
                }

            }
        });


        tabMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtabMiddleView.bringToFront();
                if(selectedTab==0){
                    setAnimationRight(mtabMiddleView);
                }else{
                    setAnimationLeft(mtabMiddleView);
                }

                selectedTab=1;
                tabMiddle.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                tabMiddle.setTextColor(Color.WHITE);
                tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabRight.setTextColor(Color.parseColor("#c8a167"));
                tabLeft.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));

                if(mLeaderAdapterStar==null)
                {

                    mLeaderAdapterStar=new LeaderAdapterStar(getActivity());

                }




                int stepCount = SaveSharedPreference.getUserTodaySteps(getContext());

                String caloriesCount = Repository.CaloriesCalulatorFromSteps(Double.parseDouble(String.valueOf(mUser.getHeight_in_cm())),Double.parseDouble(String.valueOf(mUser.getWeight())),Double.parseDouble(String.valueOf(stepCount)));

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

                String currentDateandTime = sdf.format(new Date());

                String starCount = mUser.getStars_count();
                if(starCount ==null){
                    starCount="0";
                }

                try{

                if(leaderBoardAllDataUserValueV3.getUserData()!=null){

                    userName.setText(mUser.getF_name());
                    //userSteps.setText(userStepsVal+"");
                    long userStepsVal = leaderBoardAllDataUserValueV3.getUserData().getStepCount();

                    //userSteps.setText(userStepsVal+"");
                    if(userStepsVal < 1000){
                        userSteps.setText(userStepsVal+"");
                    }else if(userStepsVal < 1000000){
                        double stepsValue = Math.abs(userStepsVal)/1000.0;
                        userSteps.setText(String.format("%.2f",stepsValue)+""+"K");
                    }else if(userStepsVal < 100000000){
                        double stepsValue = Math.abs(userStepsVal)/1000000.0;
                        userSteps.setText(String.format("%.2f",stepsValue)+""+"M");
                    }else{
                        double stepsValue = Math.abs(userStepsVal)/100000000.0;
                        userSteps.setText(String.format("%.2f",stepsValue)+""+"B");
                    }
                    userStars.setText(leaderBoardAllDataUserValueV3.getUserData().getStarCount() + "");
                    double calories = leaderBoardAllDataUserValueV3.getUserData().getCaloriesCount();
                    if(calories>999){
                        caloresUnit.setText("(Kcal)");
                        double CaloriesValue = calories/1000.0;
                        userCalories.setText(String.format("%.2f",CaloriesValue) +""+"K");
                    }else{
                        caloresUnit.setText("(Kcal)");
                        userCalories.setText(calories+"");
                    }
                    double distance = leaderBoardAllDataUserValueV3.getUserData().getDistanceSum();
                    if(distance>999){
                        distanceUnit.setText("(Km)");
                        double DistanceValue = distance/1000.0;
                        userdistance.setText(String.format("%.2f",DistanceValue)+""+"K");
                    }else{
                        distanceUnit.setText("(Km)");
                        double DistanceValue = distance/1000.0;
                        userdistance.setText(String.format("%.2f",DistanceValue)+"");
                    }
                    userRank.setText("#"+leaderBoardAllDataUserValueV3.getUserData().getStarsRank() + "");
              /*      distanceUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getDistanceUnit()+")"+"");
                    caloresUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getCaloriesUnit()+")"+"");*/
                    userWeight.setText(SaveSharedPreference.getWeightToday(getContext()) + "");
                    userWaist.setText(SaveSharedPreference.getWaistToday(getContext()) + "");
                }else{
                    userName.setText("0");
                    userSteps.setText("0");
                    userStars.setText("0");
                    userCalories.setText("0");
                    userRank.setText("0");

                }
                }catch (Exception e){

                }




                mLeaderRecyclerView.setAdapter(mLeaderAdapterStar);

                mLeaderAdapterStar.notifyDataSetChanged();

                try {

                    if(topContestants2.isEmpty()) {
                        valueText.setText("");
                        setTop5ContestentView(topContestants2);
                        //setTop5ViewInvisable();
                    }else{
                        setTop5ContestentView(topContestants2);
                        valueText.setText("");
                        //setTop5ViewVisable();
                    }

                }catch (Exception ex){
                    ex.getStackTrace();
                }
            }
        });

        tabRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mtabRightView.bringToFront();
                setAnimationRight(mtabRightView);
                selectedTab=1;
                tabLeft.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));
                tabRight.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                tabRight.setTextColor(Color.WHITE);
                tabMiddle.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabMiddle.setTextColor(Color.parseColor("#c8a167"));

                if(mLeaderAdapterCal==null)
                {

                    mLeaderAdapterCal=new LeaderAdapterCalories(getActivity());

                }




                int stepCount = SaveSharedPreference.getUserTodaySteps(getContext());

                String caloriesCount = Repository.CaloriesCalulatorFromSteps(Double.parseDouble(String.valueOf(mUser.getHeight_in_cm())),Double.parseDouble(String.valueOf(mUser.getWeight())),Double.parseDouble(String.valueOf(stepCount)));

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

                String currentDateandTime = sdf.format(new Date());

                String starCount = mUser.getStars_count();
                if(starCount ==null){
                    starCount="0";
                }



                try{
                if(leaderBoardAllDataUserValueV3.getUserData()!=null){
                    userName.setText(mUser.getF_name());
                    long userStepsVal = leaderBoardAllDataUserValueV3.getUserData().getStepCount();

                    //userSteps.setText(userStepsVal+"");
                    if(userStepsVal < 1000){
                        userSteps.setText(userStepsVal+"");
                    }else if(userStepsVal < 1000000){
                        double stepsValue = Math.abs(userStepsVal)/1000.0;
                        userSteps.setText(String.format("%.2f",stepsValue)+""+"K");
                    }else if(userStepsVal < 100000000){
                        double stepsValue = Math.abs(userStepsVal)/1000000.0;
                        userSteps.setText(String.format("%.2f",stepsValue)+""+"M");
                    }else{
                        double stepsValue = Math.abs(userStepsVal)/100000000.0;
                        userSteps.setText(String.format("%.2f",stepsValue)+""+"B");
                    }
                    userStars.setText(leaderBoardAllDataUserValueV3.getUserData().getStarCount() + "");
                    double calories = leaderBoardAllDataUserValueV3.getUserData().getCaloriesCount();
                    if(calories>999){
                        caloresUnit.setText("(Kcal)");
                        double CaloriesValue = calories/1000.0;
                        userCalories.setText(String.format("%.2f",CaloriesValue) +""+"K");
                    }else{
                        caloresUnit.setText("(Kcal)");
                        userCalories.setText(calories+"");
                    }
                    double distance = leaderBoardAllDataUserValueV3.getUserData().getDistanceSum();
                    if(distance>999){
                        distanceUnit.setText("(Km)");
                        double DistanceValue = distance/1000.0;
                        userdistance.setText(String.format("%.2f",DistanceValue)+""+"K");
                    }else{
                        distanceUnit.setText("(Km)");
                        double DistanceValue = distance/1000.0;
                        userdistance.setText(String.format("%.2f",DistanceValue)+"");
                    }
                    userRank.setText("#"+leaderBoardAllDataUserValueV3.getUserData().getCaloriesRank() + "");
                   /* distanceUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getDistanceUnit()+")"+"");
                    caloresUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getCaloriesUnit()+")"+"");
                    userWeight.setText(SaveSharedPreference.getWeightToday(getContext()) + "");
                    userWaist.setText(SaveSharedPreference.getWaistToday(getContext()) + "");*/
                }else{
                    userName.setText("0");
                    userSteps.setText("0");
                    userStars.setText("0");
                    userCalories.setText("0");
                    userRank.setText("0");

                }
                }catch (Exception e){

                }


                mLeaderRecyclerView.setAdapter(mLeaderAdapterCal);

                mLeaderAdapterCal.notifyDataSetChanged();

                try {

                    if(topContestants3.isEmpty()) {
                        setTop5ContestentView(topContestants3);
                        valueText.setText("");
                        //setTop5ViewInvisable();
                    }else{
                        setTop5ContestentView(topContestants3);
                        valueText.setText("");
                       //setTop5ViewVisable();
                    }

                }catch (Exception ex){
                    ex.getStackTrace();
                }


            }
        });




    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void setLeaderBoardAllData(LeaderBoardAllData leaderBoardAllData) {

        /*leaderBoardAllDataUserValue=leaderBoardAllData;

        tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
        tabLeft.setTextColor(Color.WHITE);
        tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
        tabRight.setTextColor(Color.parseColor("#c8a167"));
        tabMiddle.setBackgroundResource(R.drawable.tab_middle_unselected);
        tabMiddle.setTextColor(Color.parseColor("#c8a167"));


        if(mLeaderAdapter==null)
        {

            mLeaderAdapter=new LeaderAdapterSteps(getActivity());

        }


        selectedTab=0;

        int stepCount = SaveSharedPreference.getUserTodaySteps(getContext());

        String caloriesCount = Repository.CaloriesCalulatorFromSteps(Double.parseDouble(String.valueOf(mUser.getHeight_in_cm())),Double.parseDouble(String.valueOf(mUser.getWeight())),Double.parseDouble(String.valueOf(stepCount)));

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

        String currentDateandTime = sdf.format(new Date());

        String starCount = mUser.getStars_count();
        if(starCount == null){
            starCount="0";
        }



        try{
        if(leaderBoardAllDataUserValue.getUserData().getUser()!=null){

            userName.setText(mUser.getF_name());
            int userStepsVal = Integer.parseInt(leaderBoardAllDataUserValue.getUserData().getUser().getStepsCount());
            //userSteps.setText(userStepsVal+"");
            if(userStepsVal>1000){
                double stepsValue = Math.abs(userStepsVal)/1000.0;
                userSteps.setText(String.format("%.2f",stepsValue)+""+"K");
            }else{
                userSteps.setText(userStepsVal+"");
            }
            double calories = Double.parseDouble(leaderBoardAllDataUserValue.getUserData().getUser().getCalories());
            if(calories>999){
                userCalories.setText(leaderBoardAllDataUserValue.getUserData().getUser().getCalories() +""+"K");
            }else{
                userCalories.setText(leaderBoardAllDataUserValue.getUserData().getUser().getCalories()+"");
            }
            double distance = Double.parseDouble(leaderBoardAllDataUserValue.getUserData().getUser().getDistance());
            if(distance>999){
                userdistance.setText(leaderBoardAllDataUserValue.getUserData().getUser().getDistance()+""+"K");
            }else{
                userdistance.setText(leaderBoardAllDataUserValue.getUserData().getUser().getDistance()+"");
            }
            userStars.setText(leaderBoardAllDataUserValue.getUserData().getUser().getStarsCount() + "");
            userRank.setText("#"+leaderBoardAllDataUserValue.getUserData().getUser().getStepsRank() + "");
            distanceUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getDistanceUnit()+")"+"");
            caloresUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getCaloriesUnit()+")"+"");
            userWeight.setText(SaveSharedPreference.getWeightToday(getContext()) + "");
            userWaist.setText(SaveSharedPreference.getWaistToday(getContext()) + "");

        }else{
            userName.setText("0");
            userSteps.setText("0");
            userStars.setText("0");
            userCalories.setText("0");
            userRank.setText("0");

        }
        }catch (Exception e){

        }

        mLeaderRecyclerView.setAdapter(mLeaderAdapter);

        pullToRefresh.setRefreshing(false);

       if(leaderBoardAllData!=null)
       {
           setAllData(leaderBoardAllData);

           try {
               if(topContestants1.isEmpty()){
                   setTop5ContestentView(topContestants1);
                   valueText.setText("Kör för att ha en ledning");
                   //setTop5ViewInvisable();
               }else{
                   setTop5ContestentView(topContestants1);
                   valueText.setText("");
                   //setTop5ViewVisable();

                   }


           }catch (Exception ex){
               ex.getStackTrace();
           }
       }




        ProgressManager.hideProgress();*/

    }

    public void setTop5ViewInvisable(){
        top1Name.setText("");
        top1.setVisibility(View.INVISIBLE);
        top1Name.setVisibility(View.INVISIBLE);
        top2Name.setText("");
        top2.setVisibility(View.INVISIBLE);
        top2Name.setVisibility(View.INVISIBLE);
        top3Name.setText("");
        top3.setVisibility(View.INVISIBLE);
        top3Name.setVisibility(View.INVISIBLE);
        top4Name.setText("");
        top4.setVisibility(View.INVISIBLE);
        top4Name.setVisibility(View.INVISIBLE);
        top5Name.setText("");
        top5.setVisibility(View.INVISIBLE);
        top5Name.setVisibility(View.INVISIBLE);
    }
    public void setTop5ViewVisable(){
        top1.setVisibility(View.VISIBLE);
        top1Name.setVisibility(View.VISIBLE);
        top2.setVisibility(View.VISIBLE);
        top2Name.setVisibility(View.VISIBLE);
        top3.setVisibility(View.VISIBLE);
        top3Name.setVisibility(View.VISIBLE);
        top4.setVisibility(View.VISIBLE);
        top4Name.setVisibility(View.VISIBLE);
        top5.setVisibility(View.VISIBLE);
        top5Name.setVisibility(View.VISIBLE);
    }

    public void setTop5ContestentView(List<TopContestant> topContestants){

        if(topContestants.size() > 0) {
            top1.setVisibility(View.VISIBLE);
            top1Name.setVisibility(View.VISIBLE);
            loadImage(top1,topContestants.get(0).getProfileImage());
            top1Name.setText(topContestants.get(0).getName().substring(0,1).toUpperCase() + topContestants.get(0).getName().substring(1));

        }else{
            top1.setImageResource(R.drawable.user_avatar);
            top1Name.setText("");
            top1.setVisibility(View.INVISIBLE);
            top1Name.setVisibility(View.INVISIBLE);

        }
        if(topContestants.size() >1){
            top2.setVisibility(View.VISIBLE);
            top2Name.setVisibility(View.VISIBLE);
            loadImage(top2,topContestants.get(1).getProfileImage());
            top2Name.setText(topContestants.get(1).getName().substring(0,1).toUpperCase() + topContestants.get(1).getName().substring(1));

        }else{

            top2.setImageResource(R.drawable.user_avatar);
            top2Name.setText("");
            top2.setVisibility(View.INVISIBLE);
            top2Name.setVisibility(View.INVISIBLE);

        }
        if(topContestants.size() >2 ){
            top3.setVisibility(View.VISIBLE);
            top3Name.setVisibility(View.VISIBLE);
            loadImage(top3,topContestants.get(2).getProfileImage());
            top3Name.setText(topContestants.get(2).getName().substring(0,1).toUpperCase() + topContestants.get(2).getName().substring(1));

        }else{
            top3.setImageResource(R.drawable.user_avatar);
            top3Name.setText("");
            top3.setVisibility(View.INVISIBLE);
            top3Name.setVisibility(View.INVISIBLE);

        }
        if(topContestants.size() >3){
            top4.setVisibility(View.VISIBLE);
            top4Name.setVisibility(View.VISIBLE);
            loadImage(top4,topContestants.get(3).getProfileImage());
            top4Name.setText(topContestants.get(3).getName().substring(0,1).toUpperCase() + topContestants.get(3).getName().substring(1));


        }else{
            top4.setImageResource(R.drawable.user_avatar);
            top4Name.setText("");
            top4.setVisibility(View.INVISIBLE);
            top4Name.setVisibility(View.INVISIBLE);
        }
        if(topContestants.size()>4){
            top5.setVisibility(View.VISIBLE);
            top5Name.setVisibility(View.VISIBLE);
            loadImage(top5,topContestants.get(4).getProfileImage());
            top5Name.setText(topContestants.get(4).getName().substring(0,1).toUpperCase() + topContestants.get(4).getName().substring(1));

        }
        else{
            top5.setImageResource(R.drawable.user_avatar);
            top5Name.setText("");
            top5.setVisibility(View.INVISIBLE);
            top5Name.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void setLeaderBoardAllDataFailed(String message) {

       /* try {

            pullToRefresh.setRefreshing(false);
            if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
                MyApplication.showSimpleSnackBar(getContext(), "No Internet Connection");
                ProgressManager.hideProgress();
            }
            else if(message.equals("Unauthorized")){
                setTop5ViewInvisable();
                SaveSharedPreference.setUsertodaySteps(getContext(),0);
                SaveSharedPreference.setSensorSteps(getContext(),0);
                LogOutUnautorizedUser();

            }
            else{
                ProgressManager.hideProgress();
                Log.i("LeaderBoard API S","Feed not refreshed...");
                MyApplication.showSimpleSnackBar(getContext(), "Feed not refreshed..");
            }


            LeaderBoardAllData leaderBoardAllData = leaderBoardFragPresenter.getLeaderBoardAllDataLocal(getActivity());
            LeaderBoardAllDataV3 leaderBoardAllDataV3 = leaderBoardFragPresenter.getLeaderBoardAllDataLocalV3(getActivity());



            if (leaderBoardAllData != null)

                leaderBoardAllDataUserValue = leaderBoardAllData;

                setAllData(leaderBoardAllData);


            ProgressManager.hideProgress();

        }catch (Exception ex)
        {
            ex.toString();
        }*/

    }

    @Override
    public void setLeaderBoardAllDataSucessV3(LeaderBoardAllDataV3 leaderBoardAllDataV3) {
        leaderBoardAllDataUserValueV3=leaderBoardAllDataV3;
        mtabLeftView.bringToFront();
        setAnimationRight(mtabLeftView);
        tabLeft.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
        tabLeft.setTextColor(Color.WHITE);
        tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
        tabRight.setTextColor(Color.parseColor("#c8a167"));
        tabMiddle.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
        tabMiddle.setTextColor(Color.parseColor("#c8a167"));


        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime1 = sdf1.format(new Date());
        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate!=null){
            if(CompitionDate.getmName()!=null){
                mLeaderBoardTitle.setText(getResources().getString(R.string.LeaderboardModule_leaderboard)+" "+getResources().getString(R.string.General_for)+" "+CompitionDate.getmName().toUpperCase());
            }else{
                mLeaderBoardTitle.setText(getResources().getString(R.string.LeaderboardModule_leaderboard));
            }

            if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                ContestStatus = getCompitionStartDate(getContext(), CompitionDate.getStartDate(),currentDateandTime1);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),CompitionDate.getmEndDate(),currentDateandTime1);

                if(ContestStatus && !ContestEndStatus){
                    mCompititionCounter.setVisibility(View.GONE);
                }else if(ContestEndStatus){
                    mCompititionCounter.setVisibility(View.GONE);
                }else if(!ContestStatus){
                    mCompititionCounter.setVisibility(View.VISIBLE);
                }
            }else{
                /*mCompititionCounter.setVisibility(View.VISIBLE);
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

        if(mLeaderAdapter==null)
        {
            mLeaderAdapter=new LeaderAdapterSteps(getActivity());
        }


        selectedTab=0;


        try{


            int stepCount = SaveSharedPreference.getUserTodaySteps(getContext());

            String caloriesCount = Repository.CaloriesCalulatorFromSteps(Double.parseDouble(String.valueOf(mUser.getHeight_in_cm())),Double.parseDouble(String.valueOf(mUser.getWeight())),Double.parseDouble(String.valueOf(stepCount)));

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

            String currentDateandTime = sdf.format(new Date());

            String starCount = mUser.getStars_count();
            if(starCount == null){
                starCount="0";
            }


            if(leaderBoardAllDataUserValueV3.getUserData()!=null){

                userName.setText(mUser.getF_name());
                //int userStepsVal = leaderBoardAllDataUserValueV3.getUserData().getStepCount();
                //userSteps.setText(userStepsVal+"");
                long userStepsVal = leaderBoardAllDataUserValueV3.getUserData().getStepCount();

                //userSteps.setText(userStepsVal+"");
                if(userStepsVal < 1000){
                    userSteps.setText(userStepsVal+"");
                }else if(userStepsVal < 1000000){
                    double stepsValue = Math.abs(userStepsVal)/1000.0;
                    userSteps.setText(String.format("%.2f",stepsValue)+""+"K");
                }else if(userStepsVal < 100000000){
                    double stepsValue = Math.abs(userStepsVal)/1000000.0;
                    userSteps.setText(String.format("%.2f",stepsValue)+""+"M");
                }else{
                    double stepsValue = Math.abs(userStepsVal)/100000000.0;
                    userSteps.setText(String.format("%.2f",stepsValue)+""+"B");
                }
                double calories = leaderBoardAllDataUserValueV3.getUserData().getCaloriesCount();
                if(calories>999){
                    caloresUnit.setText("(Kcal)");
                    double CaloriesValue = calories/1000.0;
                    userCalories.setText(String.format("%.2f",CaloriesValue) +""+"K");
                }else{
                    caloresUnit.setText("(Kcal)");
                    userCalories.setText(calories+"");
                }
                double distance = leaderBoardAllDataUserValueV3.getUserData().getDistanceSum();
                if(distance>999){
                    distanceUnit.setText("(Km)");
                    double DistanceValue = distance/1000.0;
                    userdistance.setText(String.format("%.2f",DistanceValue)+""+"K");
                }else{
                    distanceUnit.setText("(Km)");
                    double DistanceValue = distance/1000.0;
                    userdistance.setText(String.format("%.2f",DistanceValue)+"");
                }
                userStars.setText(leaderBoardAllDataUserValueV3.getUserData().getStarCount() + "");
                userRank.setText("#"+leaderBoardAllDataUserValueV3.getUserData().getStepsRank() + "");
                /*distanceUnit.setText(""+"("+leaderBoardAllDataUserValueV3.getUserData().()+")"+"");
                caloresUnit.setText(""+"("+leaderBoardAllDataUserValue.getUserData().getUser().getCaloriesUnit()+")"+"");*/
                userWeight.setText(SaveSharedPreference.getWeightToday(getContext()) + "");
                userWaist.setText(SaveSharedPreference.getWaistToday(getContext()) + "");

            }else{
                userName.setText("0");
                userSteps.setText("0");
                userStars.setText("0");
                userCalories.setText("0");
                userRank.setText("0");

            }
        }catch (Exception e){

        }

        mLeaderRecyclerView.setAdapter(mLeaderAdapter);

        pullToRefresh.setRefreshing(false);

        if(leaderBoardAllDataV3!=null)
        {
            setAllDataV3(leaderBoardAllDataV3);


            try {
                if(topContestants1.isEmpty()){
                    setTop5ContestentView(topContestants1);
                    valueText.setText("");
                    //setTop5ViewInvisable();
                }else{
                    setTop5ContestentView(topContestants1);
                    valueText.setText("");
                    //setTop5ViewVisable();

                }


            }catch (Exception ex){
                ex.getStackTrace();
            }
        }


        //MyApplication.showSimpleSnackBarSucess(getContext(), "LeaderBoard Sucess");

        ProgressManager.hideProgress();
    }

    @Override
    public void setLeaderBoardAllDataFailedFailedV3(String message) {
        try {
            pullToRefresh.setRefreshing(false);
            if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
                MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.General_NoInternetConnection));
                ProgressManager.hideProgress();
            }
            else if(message.equals("Unauthorized")){
                setTop5ViewInvisable();
                SaveSharedPreference.setUsertodaySteps(getContext(),0);
                SaveSharedPreference.setSensorSteps(getContext(),0);
                LogOutUnautorizedUser();
            }
            else{
                ProgressManager.hideProgress();
                Log.i("LeaderBoard API S","Feed not refreshed...");
                MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.General_RequestTimedOut));
            }

            LeaderBoardAllDataV3 leaderBoardAllDataV3 = leaderBoardFragPresenter.getLeaderBoardAllDataLocalV3(getActivity());



            if (leaderBoardAllDataV3 != null)

                leaderBoardAllDataUserValueV3 = leaderBoardAllDataV3;

            setAllDataV3(leaderBoardAllDataV3);


            ProgressManager.hideProgress();

        }catch (Exception ex)
        {
            ex.toString();
        }
       // MyApplication.showSimpleSnackBar(getContext(), message);
    }

    @Override
    public void setSyncPastActivitiesSucess(String message) {
        //Toast.makeText(getContext(), "PastActivitiesSync LeaderBoard Lives", Toast.LENGTH_SHORT).show();
        leaderBoardFragPresenter.getLeaderBoardAllDataV3(getActivity());
       // MyApplication.showSimpleSnackBarSucess(getContext(), message);
    }

    @Override
    public void setSyncPastActivitiesFailed(String message) {
        pullToRefresh.setRefreshing(false);
        //MyApplication.showSimpleSnackBar(getContext(), message);
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(getContext(), getResources().getString(R.string.General_NoInternetConnection));
            ProgressManager.hideProgress();
        }else if(message.equals("Unauthorized")){
            setTop5ViewInvisable();
            SaveSharedPreference.setUsertodaySteps(getContext(),0);
            SaveSharedPreference.setSensorSteps(getContext(),0);
            LogOutUnautorizedUser();

        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private void loadImage(final ImageView imageView, final String imageUrl){

        GlideApp
                .with(LeaderBoardFragment.this)
                .load(imageUrl)
                .centerInside()
                .error(R.drawable.avatar_new)
                .into(imageView);
       /* Picasso.get()
                .load(imageUrl)
                .into(imageView , new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });*/
    }

    private void setAllDataV3(LeaderBoardAllDataV3 leaderBoardAllDataV3){
        try{

            if(leaderBoardAllDataV3.getStepsData()!=null){
                List<StepsDataV3> stepsData = new ArrayList<>();
                stepsData.addAll(leaderBoardAllDataV3.getStepsData());

                Collections.sort(stepsData);
                mLeaderAdapter.setData(stepsData);

                if(topContestants1!=null && topContestants1.size()>0)
                    topContestants1.clear();
                int count=0;
                for (int i = 0; i < stepsData.size(); i++) {
                    if(count<6) {
                        topContestants1.add(mapTop5ContestantUserToTopContestantSteps(stepsData.get(i)));
                    }else{
                        break;
                    }
                }
            }
            if(leaderBoardAllDataV3.getStarsData()!=null){
                List<StarsDataV3> starsDataV3List = new ArrayList<>();
                starsDataV3List.addAll(leaderBoardAllDataV3.getStarsData());

                Collections.sort(starsDataV3List);
                mLeaderAdapterStar.setData(starsDataV3List);

                if(topContestants2!=null && topContestants2.size()>0)
                    topContestants2.clear();

                int count=0;
                for (int i = 0; i < starsDataV3List.size(); i++) {
                    if(count<6){
                        topContestants2.add(mapTop5ContestantUserToTopContestantStars(starsDataV3List.get(i)));
                        count+=1;
                    }else{
                        break;
                    }

                }
            }

            if(leaderBoardAllDataV3.getCaloriesData()!=null){
                List<CaloriesDataV3> caloriesDataV3List = new ArrayList<>();
                caloriesDataV3List.addAll(leaderBoardAllDataV3.getCaloriesData());

                Collections.sort(caloriesDataV3List);
                mLeaderAdapterCal.setData(caloriesDataV3List);

                if(topContestants3!=null && topContestants3.size()>0)
                    topContestants3.clear();
                int count=0;
                for (int i = 0; i < caloriesDataV3List.size(); i++) {
                    if(count<6) {
                        topContestants3.add(mapTop5ContestantUserToTopContestantCalories(caloriesDataV3List.get(i)));
                    }else{
                        break;
                    }
                }
            }

        }catch (Exception e){

        }
    }

   /* private void setAllData(LeaderBoardAllData leaderBoardAllData)
    {
        try {


            if (leaderBoardAllData.getStepsData() != null) {
                List<TopContestant> topContestants = new ArrayList<>();

                for (int i = 0; i < leaderBoardAllData.getStepsData().size(); i++) {
                    topContestants.add(mapContestantUserToTopContestant(leaderBoardAllData.getStepsData().get(i).getUser()));
                }
                mLeaderAdapter.setData(topContestants);

                if(topContestants1!=null && topContestants1.size()>0)
                    topContestants1.clear();

                for (int i = 0; i < leaderBoardAllData.getStepsData().size(); i++) {

                    if(leaderBoardAllData.getStepsData().get(i).getUser().getPosition().equals("top")){

                        topContestants1.add(mapTop5ContestantUserToTopContestant(leaderBoardAllData.getStepsData().get(i).getUser()));

                    }
                }
            }


            if (leaderBoardAllData.getStarsData() != null) {
                List<TopContestant> topContestants = new ArrayList<>();


                for (int i = 0; i < leaderBoardAllData.getStarsData().size(); i++) {
                    topContestants.add(mapContestantUserToTopContestant(leaderBoardAllData.getStarsData().get(i).getUser()));
                }

                mLeaderAdapterStar.setData(topContestants);
                if(topContestants2!=null && topContestants2.size()>0)
                    topContestants2.clear();

                for (int i = 0; i < leaderBoardAllData.getStarsData().size(); i++) {

                    if(leaderBoardAllData.getStarsData().get(i).getUser().getPosition().equals("top")){

                        topContestants2.add(mapTop5ContestantUserToTopContestant(leaderBoardAllData.getStarsData().get(i).getUser()));

                    }
                }
            }


            if (leaderBoardAllData.getCaloriesData() != null) {
                List<TopContestant> topContestants = new ArrayList<>();


                for (int i = 0; i < leaderBoardAllData.getCaloriesData().size(); i++) {
                    topContestants.add(mapContestantUserToTopContestant(leaderBoardAllData.getCaloriesData().get(i).getUser()));
                }

                mLeaderAdapterCal.setData(topContestants);

                if(topContestants3!=null && topContestants3.size()>0)
                    topContestants3.clear();
                for (int i = 0; i < leaderBoardAllData.getCaloriesData().size(); i++) {

                    if(leaderBoardAllData.getCaloriesData().get(i).getUser().getPosition().equals("top")){

                        topContestants3.add(mapTop5ContestantUserToTopContestant(leaderBoardAllData.getCaloriesData().get(i).getUser()));

                    }
                }
            }
        }catch (Exception ex)
        {
            ex.toString();
        }
    }
*/

    @Override
    public void setPresenter(LeaderBoardContract.Presenter mPresenter) {


    }

    private void setAnimationLeft(View viewToAnimate) {
        // If the bound view wasn't previously displayed on screen, it's animated
        // if (position > mealNamePosition) {
        try{
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);

            viewToAnimate.startAnimation(animation);
        }catch (Exception e){

        }

        // mealNamePosition = position;
        // }
    }

    private void setAnimationRight(View viewToAnimate) {
        // If the bound view wasn't previously displayed on screen, it's animated
        // if (position > mealNamePosition) {
        try{
            Animation animation = AnimationUtils.loadAnimation(getContext(),  R.anim.slide_in_left);

            viewToAnimate.startAnimation(animation);
        }catch (Exception e){

        }

        // mealNamePosition = position;
        // }
    }

    public static TopContestant  mapContestantUserToTopContestant(ContestantUser contestantUser)
    {
        TopContestant topContestant=new TopContestant();

        topContestant.setId(contestantUser.getId().toString());
        //topContestant.setPosition(contestantUser.);
        topContestant.setCalories(contestantUser.getCalories());
        topContestant.setName(contestantUser.getFirstname()/*+" "+contestantUser.getLastname()*/);
        topContestant.setStars(contestantUser.getStarsCount());
        topContestant.setSteps(contestantUser.getStepsCount());
        topContestant.setProfileImage(contestantUser.getProfileImage());
        topContestant.setStepsRank(contestantUser.getStepsRank());
        topContestant.setStarRank(contestantUser.getStarsRank());
        topContestant.setCaloriesRank(contestantUser.getCaloriesRank());

        return  topContestant;
    }

    public static TopContestant  mapTop5ContestantUserToTopContestant(ContestantUser contestantUser)
    {
        TopContestant topContestant1=new TopContestant();

        topContestant1.setId(contestantUser.getId().toString());
        //topContestant.setPosition(contestantUser.);
        topContestant1.setCalories(contestantUser.getCalories());
        topContestant1.setName(contestantUser.getFirstname());
        topContestant1.setStars(contestantUser.getStarsCount());
        topContestant1.setSteps(contestantUser.getStepsCount());
        topContestant1.setProfileImage(contestantUser.getProfileImage());

        return  topContestant1;
    }
    public static TopContestant  mapTop5ContestantUserToTopContestantSteps(StepsDataV3 stepsDataV3)
    {
        TopContestant topContestant1=new TopContestant();

        topContestant1.setId(stepsDataV3.getId()+"");
        topContestant1.setName(stepsDataV3.getFirstName());
        topContestant1.setSteps(stepsDataV3.getCount()+"");
        topContestant1.setProfileImage(stepsDataV3.getProfileImage());

        return  topContestant1;
    }

    public static TopContestant  mapTop5ContestantUserToTopContestantStars(StarsDataV3 starsDataV3)
    {
        TopContestant topContestant1=new TopContestant();

        topContestant1.setId(starsDataV3.getId()+"");
        topContestant1.setName(starsDataV3.getFirstName());
        topContestant1.setStars(starsDataV3.getCount()+"");
        topContestant1.setProfileImage(starsDataV3.getProfileImage());

        return  topContestant1;
    }

    public static TopContestant  mapTop5ContestantUserToTopContestantCalories(CaloriesDataV3 caloriesDataV3)
    {
        TopContestant topContestant1=new TopContestant();

        topContestant1.setId(caloriesDataV3.getId()+"");
        topContestant1.setCalories(caloriesDataV3.getCount()+"");
        topContestant1.setName(caloriesDataV3.getFirstName());
        topContestant1.setProfileImage(caloriesDataV3.getProfileImage());

        return  topContestant1;
    }

    public void top5Contesteant(ContestantUser contestantUser){

    }





    private  void setTopFiveContestant()
    {
        // ToDo:

    }
    public float getDistanceNow(long steps,int hightCM){
        double stepLenght = (hightCM * 0.415);
        float Distance = (float) (stepLenght * steps);
        return Distance/100;
    }


    private void LogOutUnautorizedUser(){


        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                mLocalService.deleteAll();

            }
        });

        if (Constants.isMyServiceRunning(StepCountingService.class, getContext())) {
            ActivityFragment.newInstance().stopGPSService(getContext());
        }
        SaveSharedPreference.setApiSyncedDate(getContext(),convertStaticTimeToUTC());
        //MyApplication.showSimpleSnackBar(context, "Your Logged in Some where else");
        Toast.makeText(getContext(), getResources().getString(R.string.LoginModule_Session_Timed_Out), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

        SaveSharedPreference.setLoggedIn(getContext(), false);

        getActivity().finish();


       /* LayoutInflater li = LayoutInflater.from(getContext());

        View promptsView = li.inflate(R.layout.dialog_box_logout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

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
                SaveSharedPreference.setApiSyncedDate(getContext(),convertStaticTimeToUTC());
                MyApplication.showSimpleSnackBar(getContext(), "Your Logged in Some where else");
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);

                SaveSharedPreference.setLoggedIn(getContext(), false);

                getActivity().finish();


            }
        });

        builder.show();*/

    }

}
