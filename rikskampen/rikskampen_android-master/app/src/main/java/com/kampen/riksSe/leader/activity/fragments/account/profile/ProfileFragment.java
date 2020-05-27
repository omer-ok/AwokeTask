package com.kampen.riksSe.leader.activity.fragments.account.profile;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.google.firebase.iid.FirebaseInstanceId;
import com.kampen.riksSe.BroadCastReciver.UpdateReceiver;
import com.kampen.riksSe.ChangePassword.ResetPasswordActivity;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;


import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ScheduledLiveVideoCallActivity;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.LeaderBoardFragment;
import com.kampen.riksSe.leader.activity.fragments.account.editprofile.EditPofileSimpleActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.AddAllergies;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.AddAllergiesV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantSchduledLiveVideoCallActivity;
import com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall.LiveVideoCall;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;

import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.NutritionNewUIMealActivity;
import com.kampen.riksSe.leader.activity.fragments.map.tracking.GPS_Service;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

import static com.kampen.riksSe.api.remote_api.APIUrl.BASE_PROD_URL;
import static com.kampen.riksSe.api.remote_api.APIUrl.BASE_TEST_URL;
import static com.kampen.riksSe.api.remote_api.APIUrl.BASE_URL;
import static com.kampen.riksSe.api.remote_api.APIUrl.context;
import static com.kampen.riksSe.utils.UtilityTz.convertStaticTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class ProfileFragment extends Fragment implements  ProfileContract.View{




    private  View  mProfileLayout,mVideoLayout,mSettingsLayout,mFaqBtn,mabout,mprivacyPolicy,AddAllergiesbtn,ChangePAsswordbtn;

    private ImageView profileImage;//,userImage;

    private  View  mLogoutButton,chatLayout;

    private   ProfilePresenter  mProfilePresenter;

    NotificationBadge badge;

    private View ChatNotification;

    Realm_User mUser;

    GPS_Service mService;

    private TextView profileNameText;

    //ToggleButton TesterOnOff;

    Boolean TesterON=false;

    //TextView mdeveloperStatus;

    View mCompititionCounter;

    TextView mDayCounter,mHourCounter,mMiniuteCounter,mSecondsCounter;

    Boolean ContestStatus;

    TextView Heading;

    View TimerView;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mLogoutButton=view.findViewById(R.id.logoutButton);
        mProfileLayout=view.findViewById(R.id.profileLayout);
        //userImage=view.findViewById(R.id.messages);
        mVideoLayout=view.findViewById(R.id.videoLayout);
        chatLayout=view.findViewById(R.id.chatLayout);
        //mSettingsLayout=view.findViewById(R.id.settingsLayout);
        mabout=view.findViewById(R.id.abouttab);
        mFaqBtn=view.findViewById(R.id.faq);
        profileImage=view.findViewById(R.id.profileImage);
        mprivacyPolicy = view.findViewById(R.id.privacy);
        AddAllergiesbtn = view.findViewById(R.id.addAlergies);
        ChangePAsswordbtn = view.findViewById(R.id.ChangePass);
        badge = view.findViewById(R.id.chatNotify);
        //TesterOnOff = (ToggleButton)view.findViewById(R.id.debugSwitch);
        String [] params=SaveSharedPreference.getLoggedParams(getContext());
        profileNameText =view.findViewById(R.id.Profiletxt);
        //mdeveloperStatus =view.findViewById(R.id.developerStatus);
        mUser=Repository.provideUserLocal(params[0],params[1]);
        mCompititionCounter = view.findViewById(R.id.compititionCounter);
        mDayCounter = view.findViewById(R.id.dayCounter);
        mHourCounter = view.findViewById(R.id.hourCounter);
        mMiniuteCounter = view.findViewById(R.id.miniuteCounter);
        mSecondsCounter = view.findViewById(R.id.secondsCounter);
        Heading = view.findViewById(R.id.hedaing);
        TimerView = view.findViewById(R.id.timerView);


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

        manageClicks();

        badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));
        mProfilePresenter=new ProfilePresenter(ProfileFragment.this);
        //loadImage(userImage,mUser.getTrainerPhoto());

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mProfilePresenter.getChatDataFromServer(getContext());


            }
        });
        if(SaveSharedPreference.getLoggedUserTesterStatus(getContext())){
         /*   TesterOnOff.setVisibility(View.VISIBLE);
            mdeveloperStatus.setVisibility(View.VISIBLE);*/
            mVideoLayout.setVisibility(View.VISIBLE);
        }else{
           /* TesterOnOff.setVisibility(View.GONE);
            mdeveloperStatus.setVisibility(View.GONE);*/
            mVideoLayout.setVisibility(View.GONE);
        }

   /*     if(SaveSharedPreference.getBaseURl(getContext()).equals(BASE_PROD_URL)){
            TesterOnOff.setChecked(false);
            mdeveloperStatus.setText("Production");
        }else{
            TesterOnOff.setChecked(true);
            mdeveloperStatus.setText("Test Envo");
        }

        TesterOnOff.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //toggle.toggle();
                if (TesterOnOff.isChecked()) {
                    mProfilePresenter.performLogout(getContext());
                    TesterON=true;
                    mdeveloperStatus.setText("Test Envo");

                } else {
                    mProfilePresenter.performLogout(getContext());
                    TesterON=false;
                    mdeveloperStatus.setText("Production");
                }
            }
        });*/
    }




    @Override
    public void onResume() {
        super.onResume();

        mProfilePresenter.getUserProfilePhoto(getActivity());
       // loadImage(userImage,user.getTrainerPhoto());
        badge.setNumber(SaveSharedPreference.getChatNotificationCounter(getContext()));
    }




    private  void manageClicks()
    {
        mProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), EditPofileSimpleActivity.class);
                intent.putExtra("ActivityStatus","Profile");
                startActivity(intent);
                //getActivity().finish();


            }
        });

        mFaqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Faq.class);
                startActivity(intent);

            }
        });


        mVideoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA}, 1);
                }else{
                  /*  Intent i = new Intent(getActivity(), LiveVideoCall.class);
                    startActivity(i);*/

                    Intent i = new Intent(getActivity(), ContestantSchduledLiveVideoCallActivity.class);
                    startActivity(i);
                }
            }
        });

        mabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), About.class);
                startActivity(i);

            }
        });

        AddAllergiesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), AddAllergiesV3.class);
                startActivity(i);

            }
        });

        ChangePAsswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String [] params= SaveSharedPreference.getLoggedParams(getContext());

                Realm_User user= Repository.provideUserLocal(params[0],params[1]);

                Intent i = new Intent(getActivity(), ResetPasswordActivity.class);
                i.putExtra("Status","InAPP");
                i.putExtra("Email",user.getEmail());
                startActivity(i);

            }
        });

        mprivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PrivacyPolicy.class);
                startActivity(i);
            }
        });

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Logga ut")
                        .setMessage(getResources().getString(R.string.MoreModuel_Logout_Sure))
                        .setPositiveButton("Ja", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ProgressManager.showProgress(getContext(),getResources().getString(R.string.progress_dialog_message));
                                //mProfilePresenter.performLogout(getContext());

                                List<ActivityDaily> pastActivities = mProfilePresenter.GetPastActivities(getContext());

                                if(pastActivities!=null){
                                    mProfilePresenter.SyncPastActivities(getContext(),pastActivities);
                                }else{
                                    mProfilePresenter.performLogout(getContext());
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });



        chatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mProfilePresenter.getChatDataFromServer(getContext());
                        Intent intent = new Intent(getActivity(),ChatActivity.class);
                        startActivity(intent);

                    }
                });



            }
        });


     /*   mSettingsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
*//*
                Intent intent = new Intent(getActivity(), PaymentSampleActivity.class);
                startActivity(intent);*//*

            }
        });
*/






    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {

            Intent i = new Intent(getActivity(), ContestantSchduledLiveVideoCallActivity.class);
            startActivity(i);
        }

    }


        @Override
    public void setProfile(Realm_User user) {



             Constants.setProfileImage(user.getProfile_image(),user.getProfilePicData(),profileImage,getContext());
             profileNameText.setText(user.getF_name());
            //loadImage(userImage,user.getTrainerPhoto());

       /* GlideApp
                .with(getContext())
                .load(user.getProfile_image())
                .into(profileImage);
*/
             /*String  profilePath=user.getProfile_image();

             if(profilePath!=null && profilePath.length()>0) {
                 Bitmap bmp = BitmapFactory.decodeFile(profilePath);
                 profileImage.setImageBitmap(bmp);
             }
             else if(user.getProfilePicData()!=null)
             {
                Bitmap bm= BitmapFactory.decodeByteArray(user.getProfilePicData(), 0, user.getProfilePicData().length);
                 profileImage.setImageBitmap(bm);
             }*/

    }


    private void loadImage(final ImageView imageView, final String imageUrl){


        GlideApp
                .with(getContext())
                .load(imageUrl)
                .centerInside()
                .error(R.drawable.avatar_new)
                .into(imageView);
    }

    @Override
    public void setLogoutSuccess(String message) {

        ActivityFragment.newInstance().stopGPSService(getContext());
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                // Stuff that updates the UI
                ProgressManager.hideProgress();

                //MyApplication.showSimpleSnackBarSucess(getContext(),"Logout sucess");

                /*Thread thread = new Thread(() -> {
                    try {
                        FirebaseInstanceId.getInstance().deleteInstanceId();
                        // SaveSharedPreference.setUserFcmToken(getContext(),"");
                    } catch (Exception e) {
                        Log.e("ERROR", Log.getStackTraceString(e));
                        //RxBus.getInstance().post(new OffPushError());
                    }
                });
                thread.start();*/



                SaveSharedPreference.setSyncApiFirstTimeStatus(getContext(),true);
                SaveSharedPreference.setUsertodaySteps(getContext(),0);
                SaveSharedPreference.setUserLogInSteps(getContext(),0);
                SaveSharedPreference.setDailyImage(getContext(),"");
                SaveSharedPreference.setDailyDate(getContext(),"");
                SaveSharedPreference.setLocation(getContext(),"");
                SaveSharedPreference.setApiSyncedDate(getContext(),convertStaticTimeToUTC());
                SaveSharedPreference.setStepsToSubstract(getContext(),0);
                SaveSharedPreference.setStopWatchTime(getContext(),0);
                //SaveSharedPreference.setApiSyncedDate(getContext(),convertStaticTimeToUTC());

                SaveSharedPreference.setChatNotificationCounter(getContext(),0);
                SaveSharedPreference.setLoggedInSplash(getContext(),true);
                SaveSharedPreference.setLoggedIn(getActivity(), false);
                SaveSharedPreference.setLoggedInFirstTime(getActivity(), false);
                SaveSharedPreference.setAppKilledState(getActivity(), false);
                //SaveSharedPreference.setLoggedIn(LoginActivity.this,true);

                SaveSharedPreference.setAppKilledState(getContext(), false);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

                /*PackageManager pm  = getContext().getPackageManager();
                ComponentName componentName = new ComponentName(getContext(), UpdateReceiver.class);
                pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);*/


                if(TesterON){
                    SaveSharedPreference.setBaseURl(getContext(),BASE_TEST_URL);
                }else{
                    SaveSharedPreference.setBaseURl(getContext(),BASE_PROD_URL);
                }

                getActivity().finish();


            }
        });



    }

    @Override
    public void setLogoutFailed(String message) {

        ProgressManager.hideProgress();


        /*SaveSharedPreference.setLoggedIn(getContext(),false);

        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);

        SaveSharedPreference.setLoggedIn(getActivity(), false);
        getActivity().finish();*/



        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(getContext(), "No Internet Connection");
            ProgressManager.hideProgress();
        }
        else{
            ProgressManager.hideProgress();
            MyApplication.showSimpleSnackBar(getContext(), message);
        }

    }

    @Override
    public void setChatTrainerSucess(String message) {


    }

    @Override
    public void setChatTrainerFailed(String message) {
       /*if(message.equals("Unauthorized")){
            Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
            mLocalService.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {


                    mLocalService.deleteAll();

                }
            });

            MyApplication.showSimpleSnackBar(getContext(), "Your Logged in Some where else");
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);

            SaveSharedPreference.setLoggedIn(getContext(), false);


        }*/

    }

    @Override
    public void setSyncPastActivitiesSucess(String message) {
        //Toast.makeText(getContext(), "PastActivitiesSync Logout Lives", Toast.LENGTH_SHORT).show();
        try{
            String version = null;
            try {

                PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                version = pInfo.versionName;
                Log.i("AppVersion",version);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            mProfilePresenter.UpdateUserPermissionAndVersion(context,mUser,version);
        }catch (Exception e){

        }
    }

    @Override
    public void setSyncPastActivitiesFailed(String message) {
        //Toast.makeText(getContext(), "PastActivitiesSync Logout Fail", Toast.LENGTH_SHORT).show();
        ProgressManager.hideProgress();
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(getContext(), "No Internet Connection");
            ProgressManager.hideProgress();
        }else if(message.equals("Unauthorized")){
            SaveSharedPreference.setUsertodaySteps(getContext(),0);
            SaveSharedPreference.setSensorSteps(getContext(),0);
            LogOutUnautorizedUser();

        }
    }

    @Override
    public void setUpdateUserPermissionAndVersionSucess(String message) {
        mProfilePresenter.performLogout(getContext());
    }

    @Override
    public void setUpdateUserPermissionAndVersionFailed(String message) {

    }

    @Override
    public void onStart() {
        super.onStart();
        // Toast.makeText(getContext(), "Home Prssed start", Toast.LENGTH_SHORT).show();
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
    private void LogOutUnautorizedUser(){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                mLocalService.deleteAll();

            }
        });
        try{

            if (Constants.isMyServiceRunning(StepCountingService.class, getContext())) {
                ActivityFragment.newInstance().stopGPSService(getContext());
            }
            SaveSharedPreference.setApiSyncedDate(getContext(),convertStaticTimeToUTC());
            //MyApplication.showSimpleSnackBar(context, "Your Logged in Some where else");
            Toast.makeText(getContext(),  getResources().getString(R.string.LoginModule_Session_Timed_Out), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);

            SaveSharedPreference.setLoggedIn(getContext(), false);

            getActivity().finish();

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
    @Override
    public void setPresenter(ProfileContract.Presenter mPresenter) {


    }
}
