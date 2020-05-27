package com.kampen.riksSe.leader.activity.fragments.home;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.traings.PlansFragment;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.nex3z.notificationbadge.NotificationBadge;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.kampen.riksSe.utils.Constants.getAndroidVersion;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment
 * to handle interaction events.
 */
public class HomeFragment extends Fragment {


    //private ViewPager mViewPager;
    private HomePagerAdapter homePagerAdapter;
    String type = "0";

    //private   TabLayout mTablayout;

    private Fragment[] mFragments=new Fragment[2];

    private   TextView  tabLeft,tabRight;

    private View mtabLeftView,mtabRightView;

    private TextView chatNotifyValue;

    private View ChatNotification;

    private Activity myActivity;

    int ChatValueCounter=0;


    Fragment[] PAGES;
    TextView Heading;

    CountDownTimer cdt;

    public static String currentDateandTime;

    NotificationBadge badge;

    View mCompititionCounter;
    TextView mDayCounter,mHourCounter,mMiniuteCounter,mSecondsCounter;
    View TimerView;
    Button mUpdatePlan;

    Realm_User mUser;
    Boolean ContestStatus;
    long total_millis = 0;


    private final String[] PAGE_TITLES = new String[]{
            "ActivityDaily",
            "Health & Programs",


    };


    public  static int selectedTab=0;


    public Fragment[] getFragments() {


     PAGES = new Fragment[]{

             ActivityFragment.newInstance(),
            PlansFragment.newInstance(),


    };

        return PAGES;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // type = getArguments().getString("W_Plans");
        //Toast.makeText(getContext(), "Home Prssed create", Toast.LENGTH_SHORT).show();


        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //type = getArguments().getString("W_Plans");

       // String dayOfWeek = getActivity().getIntent().getStringExtra("W_Plans");
        //Toast.makeText(getContext(), "Home Prssed Viewcreate", Toast.LENGTH_SHORT).show();
        tabLeft=(TextView) view.findViewById(R.id.tabLeft);
        tabRight=(TextView) view.findViewById(R.id.tabRight);

        mtabLeftView = view.findViewById(R.id.tabLeftViewHome);
        mtabRightView =view.findViewById(R.id.tabRightViewHome);

        ChatNotification=view.findViewById(R.id.chatLayout);
        mUser = provideUserLocal(getContext());
        int otp=0;
        if(otp>0){

        }

        //chatNotifyValue=(TextView) view.findViewById(R.id.chatNotify);

        badge = view.findViewById(R.id.chatNotify);
        mCompititionCounter = view.findViewById(R.id.compititionCounter);
        mDayCounter = view.findViewById(R.id.dayCounter);
        mHourCounter = view.findViewById(R.id.hourCounter);
        mMiniuteCounter = view.findViewById(R.id.miniuteCounter);
        mSecondsCounter = view.findViewById(R.id.secondsCounter);
        Heading = view.findViewById(R.id.hedaing);
        TimerView = view.findViewById(R.id.timerView);
        mUpdatePlan = view.findViewById(R.id.update_package);

        //homePagerAdapter=new HomePagerAdapter(getChildFragmentManager(),getFragments());
       /* ChatValueCounter = SaveSharedPreference.getChatNotificationCounter(getContext());
        chatNotifyValue.setText(ChatValueCounter+"");*/

        badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));

       /*try {

           if(SaveSharedPreference.getChatNotifictationCounterZero(getContext())){
               badge.setNumber(SaveSharedPreference.getChatNotificationCounterCurrent(getContext()));
           }else{
               badge.setNumber(0);
               setBadge(getContext(), 0);
           }
       }catch(Exception e){

       }*/

        //CounterContest(false);

        tabLeft.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
        tabLeft.setTextColor(Color.WHITE);
        tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
        tabRight.setTextColor(Color.parseColor("#c8a167"));



        selectedTab=0;
        manageTab();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        Competition CompitionDate = Repository.getCompitionDate();

        if(CompitionDate!=null){
            if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){

                ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                if(ContestStatus && !ContestEndStatus){
                    mCompititionCounter.setVisibility(View.GONE);
                }else if(ContestEndStatus){
                    mCompititionCounter.setVisibility(View.GONE);
                }else if(!ContestStatus){
                    TimerView.setVisibility(View.VISIBLE);
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

        try {

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            mFragments[1] = PlansFragment.newInstance();

            transaction.add(R.id.viewpager, mFragments[1],PlansFragment.class.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.toString();
        }

        try {

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            mFragments[0] = ActivityFragment.newInstance();

            transaction.add(R.id.viewpager, mFragments[0],ActivityFragment.class.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.toString();
        }


    }






  @Override
  public void onStart() {
      super.onStart();
     // Toast.makeText(getContext(), "Home Prssed start", Toast.LENGTH_SHORT).show();
      LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mBroadcastReceiverBadges,
              new IntentFilter("com.rikskampen.CUSTOM_INTENT_CHAT_BADGE"));

      LocalBroadcastManager.getInstance(getActivity()).registerReceiver(homeclick,
              new IntentFilter("com.rikskampen.CUSTOM_INTENT_HOME_CLICK"));

      LocalBroadcastManager.getInstance(getActivity()).registerReceiver(CountDownCompitition,
              new IntentFilter("com.tutorialspoint.CUSTOM_COMPITITION_COUNTER"));
  }


    @Override
    public void onStop() {

        super.onStop();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mBroadcastReceiverBadges);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(homeclick);
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(CountDownCompitition);
         //Toast.makeText(getContext(), "Activiy frag Stop", Toast.LENGTH_SHORT).show();

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


    private final BroadcastReceiver homeclick= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Now you can call all your fragments method here

            mtabLeftView.bringToFront();
            tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
            tabRight.setTextColor(Color.parseColor("#c8a167"));
            tabLeft.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
            tabLeft.setTextColor(Color.WHITE);
            setAnimationLeft(mtabLeftView);
            selectedTab=0;

            try {
                if (mFragments[0] == null) {
                    mFragments[0] = ActivityFragment.newInstance();
                }
                addFragment(mFragments[0]);
                updateNotify();

            } catch (Exception ex) {
                ex.toString();
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
                            updateNotify();
                        }

                    }
                });


            }catch (Exception e){

            }

        }
    };

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();
        try{

            /*if(SaveSharedPreference.getChatNotifictationCounterZero(getContext())){
                badge.setNumber(SaveSharedPreference.getChatNotificationCounterCurrent(getContext()));
            }else{
                badge.setNumber(0);
                setBadge(getContext(), 0);
            }*/
            badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));

        }catch (Exception e){

        }


        //Toast.makeText(getContext(), "Home Prssed resume", Toast.LENGTH_SHORT).show();
        /*tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
        tabLeft.setTextColor(Color.WHITE);
        tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
        tabRight.setTextColor(Color.parseColor("#c8a167"));

        selectedTab=0;

        try {

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            mFragments[0] = ActivityFragment.newInstance();
            transaction.add(R.id.viewpager, mFragments[0],ActivityFragment.class.getName());
            transaction.commit();
        } catch (Exception ex) {
            ex.toString();
        }
*/
    }

    private  void manageTab()
    {


       /* mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if(selectedTab==0)
                {

                    tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
                    tabLeft.setTextColor(Color.WHITE);
                    tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
                    tabRight.setTextColor(Color.parseColor("#c8a167"));

                }

                else
                {

                    tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
                    tabLeft.setTextColor(Color.parseColor("#c8a167"));
                    tabRight.setBackgroundResource(R.drawable.tab_right_selected);
                    tabRight.setTextColor(Color.WHITE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });*/

/*
        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        if(selectedTab==0)
        {

            tabLeft.setBackgroundResource(R.drawable.tab_left_selected);
            tabLeft.setTextColor(Color.WHITE);
            tabRight.setBackgroundResource(R.drawable.tab_right_unselected);
            tabRight.setTextColor(Color.parseColor("#c8a167"));

        }

        else
        {

            tabLeft.setBackgroundResource(R.drawable.tab_left_unselected);
            tabLeft.setTextColor(Color.parseColor("#c8a167"));
            tabRight.setBackgroundResource(R.drawable.tab_right_selected);
            tabRight.setTextColor(Color.WHITE);
        }
*/







        tabLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtabLeftView.bringToFront();
                tabRight.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabRight.setTextColor(Color.parseColor("#c8a167"));
                tabLeft.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                tabLeft.setTextColor(Color.WHITE);

                setAnimationLeft(mtabLeftView);
                selectedTab=0;

                try {
                    if (mFragments[0] == null) {
                        mFragments[0] = ActivityFragment.newInstance();
                    }
                    addFragment(mFragments[0]);

                } catch (Exception ex) {
                    ex.toString();
                }

            }
        });


        tabRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mtabRightView.bringToFront();
                tabLeft.setBackgroundResource(R.drawable.tab_unselected_rounded_courners);
                tabLeft.setTextColor(Color.parseColor("#c8a167"));
                tabRight.setBackgroundResource(R.drawable.tab_selected_rounded_courners);
                tabRight.setTextColor(Color.WHITE);
                setAnimationRight(mtabRightView);
                selectedTab=1;

                try {
                    if (mFragments[1] == null) {
                        mFragments[1] = PlansFragment.newInstance();
                    }
                    addFragment(mFragments[1]);
                    Intent intent = new Intent();
                    intent.setAction("com.rikskampen.CUSTOM_INTENT_ViDEO_OFF_CLICK");
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                } catch (Exception ex) {
                    ex.toString();
                }
            }
        });


        ChatNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crashlytics.getInstance().crash(); // Force a crash
                Intent intent =new Intent(getContext(), ChatActivity.class);
                startActivity(intent);
                badge.setNumber(0);


            }
    });

    }

    private void setAnimationLeft(View viewToAnimate) {
        // If the bound view wasn't previously displayed on screen, it's animated
        // if (position > mealNamePosition) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);

        viewToAnimate.startAnimation(animation);
        // mealNamePosition = position;
        // }
    }

    private void setAnimationRight(View viewToAnimate) {
        // If the bound view wasn't previously displayed on screen, it's animated
        // if (position > mealNamePosition) {
        Animation animation = AnimationUtils.loadAnimation(getContext(),  R.anim.slide_in_left);

        viewToAnimate.startAnimation(animation);
        // mealNamePosition = position;
        // }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();


    }



    public class HomePagerAdapter extends FragmentPagerAdapter {

        Fragment[] PAGES;

        public HomePagerAdapter(FragmentManager fragmentManager,Fragment[] PAGES) {

            super(fragmentManager);

            this.PAGES=PAGES;
        }

        @Override
        public Fragment getItem(int position) {

            return PAGES[position];
        }

        @Override
        public int getCount() {

            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(mFragments[0]!=null)
        {
            mFragments[0].onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }





    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {

            if (mFragments[0] != null) {
                mFragments[0].onActivityResult(requestCode, resultCode, data);
            }
        }catch (Exception ex)
        {
            ex.toString();
        }

    }

    private  void  addFragment( Fragment selectedFragment)
    {

        try {


            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();


            Fragment fagmentToReplace=getActivity().getSupportFragmentManager().findFragmentByTag(selectedFragment.getClass().getName());

            if (fagmentToReplace != null) {

                // if(fagmentToReplace.isAdded())
                {
                    transaction.replace(R.id.viewpager, fagmentToReplace, selectedFragment.getClass().getName());
                }
            } else {
                transaction.add(R.id.viewpager, selectedFragment, selectedFragment.getClass().getName());
            }

            transaction.commit();


        }
        catch(Exception ex){
            Log.i("TabEx",ex.toString());
        }
    }

    public void updateNotifyDiary()
    {
        ActivityFragment activityFragment=(ActivityFragment) mFragments[0];

        activityFragment.updateNotifyDiary();
    }

    public void updateNotify()
    {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // Stuff that updates the UI

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTime = sdf.format(new Date());
                PlansFragment plansFragment=(PlansFragment) mFragments[1];

                plansFragment.updateNotify();

                ActivityFragment activityFragment=(ActivityFragment) mFragments[0];

                activityFragment.updateNotify();

                Competition CompitionDate = Repository.getCompitionDate();

                if(CompitionDate!=null){
                    if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){

                        ContestStatus = getCompitionStartDate(getContext(), convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);
                        Boolean ContestEndStatus = getCompitionStartDate(getContext(),convertUTCToLocalTime(CompitionDate.getmEndDate()),currentDateandTime);
                        if(ContestStatus && !ContestEndStatus){
                            mCompititionCounter.setVisibility(View.GONE);
                        }else if(ContestEndStatus){
                            mCompititionCounter.setVisibility(View.GONE);
                        }else if(!ContestStatus){
                            TimerView.setVisibility(View.VISIBLE);
                            mCompititionCounter.setVisibility(View.VISIBLE);
                        }
                    }else{
                       /* mCompititionCounter.setVisibility(View.VISIBLE);
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
            }
        });


    }

    public void ApiFail(){

        PlansFragment plansFragment=(PlansFragment) mFragments[1];

        plansFragment.ApiFail();

        ActivityFragment activityFragment=(ActivityFragment) mFragments[0];

        activityFragment.ApiFail();



    }

    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;

    }






}
