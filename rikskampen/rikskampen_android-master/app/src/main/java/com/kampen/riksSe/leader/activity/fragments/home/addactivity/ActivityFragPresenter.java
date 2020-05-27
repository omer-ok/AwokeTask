package com.kampen.riksSe.leader.activity.fragments.home.addactivity;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;


import com.facebook.stetho.server.http.HttpStatus;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.common.api.GoogleApiClient;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.ResponseStatus;

import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AddDailyDiaryV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.diaries;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.QuestionResponse;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.AddUpdateDailyDiaryToServerV3;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DailyDiaryQuestionModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DiariesUpdateServer;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.ActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.MotivationVideos;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.VideoM;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.UserJourneyData;
import com.kampen.riksSe.leader.activity.modelV3.Permissions;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

import static android.content.Context.WIFI_SERVICE;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeForUI;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.loopj.android.http.AsyncHttpClient.log;

public class ActivityFragPresenter implements  ActivityFragContract.Presenter{


    private   ActivityFragContract.View   mView;

    private   Context  mContext;

    private Realm_User mUser;

    /*@NonNull
    private final ActivityFragContract.View  mActFragView;*/

    public ActivityFragPresenter(ActivityFragContract.View  _view,Context context)
    {

        this.mView=_view;
        this.mContext=context;

    }

    public ArrayList<Integer> GetTodayActivityData(String DateLocal){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        final RealmResults<UserJourneyData> Date= mLocalService.where(UserJourneyData.class)
                .equalTo(Constants.USER_TODAY_DATE,DateLocal)
                .findAll();

        ArrayList<Integer> StarCount = new ArrayList<>();

        if(Date !=null && Date.size()>0){
            for(int i=0; i<Date.size(); i++){

                StarCount.add(Date.get(i).getLocatStarCount());

            }
        }

        return StarCount;
    }


    public List<VideoM> getMotivationVideos(Context context){

        Realm_User user=provideUserLocal(context);

        MotivationVideos motivationVideosData= Repository.getMotivationVideosData(user.getId());
        List<VideoM> videoMArrayList = new ArrayList<>();

        if(motivationVideosData !=null ){


                videoMArrayList = motivationVideosData.getVideos();


            return videoMArrayList;
        }

        return videoMArrayList;
    }

    public List<MotivationalVideo> getMotivationVideosV3(){
        List<MotivationalVideo> motivationalVideoList = Repository.getMotivationVideosDataV3();
        //Collections.sort(motivationalVideoList);
        return motivationalVideoList;
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

    public activityAdapterListModel GetTodayActivityV1(Context context,String CompitionStartdate){
        mUser = provideUserLocal(context);
        SimpleDateFormat sdf3 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        List<ActivityDaily> activityDailySyncList = Repository.getActivitiesSyncData();
        List<activityAdapterListModel> activityDailySyncSortedList = new ArrayList();
        List<activityAdapterListModel> activityDailySyncWeekDaysSortedList = new ArrayList();
        try {
            long CurrentWeek = 0;
            for (int i = 0; i < activityDailySyncList.size(); i++) {
                Date ActivityDateandTime = sdf3.parse(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                Date CurrentDateandTime = sdf3.parse(CompitionStartdate);
                if (ActivityDateandTime.after(CurrentDateandTime)) {
                    activityAdapterListModel activityAdapterListModel1 = new activityAdapterListModel();
                    ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()), CompitionStartdate);
                    activityAdapterListModel1.setmDate(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                    activityAdapterListModel1.setmWeek(contestWeekDayTimeModel.getWeeks() + 1);
                    activityAdapterListModel1.setmDay(contestWeekDayTimeModel.getDays() + 1);
                    activityAdapterListModel1.setmMediaImage(activityDailySyncList.get(i).getmMediaImage());
                    activityAdapterListModel1.setmId(activityDailySyncList.get(i).getmId());
                    activityAdapterListModel1.setmLocationAddress(activityDailySyncList.get(i).getmLocationAddress());
                    activityAdapterListModel1.setmWeight(activityDailySyncList.get(i).getmWeight());
                    activityAdapterListModel1.setmWaist(activityDailySyncList.get(i).getmWaist());
                    activityAdapterListModel1.setmSteps(activityDailySyncList.get(i).getmSteps());
                    activityAdapterListModel1.setmCalories(activityDailySyncList.get(i).getmCalories());
                    activityAdapterListModel1.setmDistance(activityDailySyncList.get(i).getmDistance());
                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                    SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTimeStart = sdf.format(new Date());
                    String currentDateandTimeEnd = sdf.format(new Date());
                    try {
                        Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart + " 00:00:00");
                        Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd + " 23:59:59");
                        Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                        if ((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))) {
                            activityAdapterListModel1.setCurrentWeek("Present");
                            CurrentWeek = activityAdapterListModel1.getmWeek();
                        } else {
                            activityAdapterListModel1.setCurrentWeek("Past");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("ActivityHistory", e.toString());
                    }
                    activityDailySyncSortedList.add(activityAdapterListModel1);
                }
            }
            Collections.sort(activityDailySyncSortedList);
            for(int i =0; i<activityDailySyncSortedList.size();i++){
                SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
                SimpleDateFormat sdf4 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTimeStart = sdf2.format(new Date());
                String currentDateandTimeEnd = sdf2.format(new Date());
                Date currentDateandTimeStartRange = sdf4.parse(currentDateandTimeStart+" 00:00:00");
                Date currentDateandTimeEndRange = sdf4.parse(currentDateandTimeEnd+" 23:59:59");
                Date ActiviyDate = sdf4.parse(activityDailySyncSortedList.get(i).getmDate());
                if((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))){

                    return activityDailySyncSortedList.get(i);

                }
            }

        }catch (Exception ex ){
            Log.i("ExToday",ex.toString());
        }

            return null;
    }

    public activityAdapterListModel GetTodayActivityWithImage(List<activityAdapterListModel> activityDailySyncData ){
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

                    return activityDailySyncData.get(i);
                }
            }
        }catch (Exception ex){

        }
        return null;
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


   /* public ActivityFragPresenter(@NonNull ActivityFragContract.View actFragView)
    {
        mActFragView = checkNotNull(actFragView);

    }*/

    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;

    }

    public List<activityAdapterListModel> getSyncedActivityData(Context context,String CompitionStartdate){
        mUser = provideUserLocal(context);
        SimpleDateFormat sdf3 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        List<ActivityDaily> activityDailySyncList = Repository.getActivitiesSyncData();
        List<activityAdapterListModel> activityDailySyncSortedList = new ArrayList();
        List<activityAdapterListModel> activityDailySyncWeekDaysSortedList = new ArrayList();
        try {
            long CurrentWeek=0;
            for (int i = 0; i < activityDailySyncList.size(); i++) {
                Date ActivityDateandTime = sdf3.parse(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                Date CurrentDateandTime = sdf3.parse(CompitionStartdate);
                if(ActivityDateandTime.after(CurrentDateandTime)){
                    activityAdapterListModel activityAdapterListModel1 = new activityAdapterListModel();
                    ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()), CompitionStartdate);
                    activityAdapterListModel1.setmDate(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                    activityAdapterListModel1.setmWeek(contestWeekDayTimeModel.getWeeks()+1);
                    activityAdapterListModel1.setmDay(contestWeekDayTimeModel.getDays()+1);
                    activityAdapterListModel1.setmMediaImage(activityDailySyncList.get(i).getmMediaImage());
                    activityAdapterListModel1.setmId(activityDailySyncList.get(i).getmId());
                    activityAdapterListModel1.setmLocationAddress(activityDailySyncList.get(i).getmLocationAddress());
                    activityAdapterListModel1.setmWeight(activityDailySyncList.get(i).getmWeight());
                    activityAdapterListModel1.setmWaist(activityDailySyncList.get(i).getmWaist());
                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                    SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTimeStart = sdf.format(new Date());
                    String currentDateandTimeEnd = sdf.format(new Date());
                    try {
                        Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart+" 00:00:00");
                        Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd+" 23:59:59");
                        Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                        if((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))){
                            activityAdapterListModel1.setCurrentWeek("Present");
                            CurrentWeek= activityAdapterListModel1.getmWeek();
                        }else{
                            activityAdapterListModel1.setCurrentWeek("Past");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("ActivityHistory",e.toString());
                    }
                    activityDailySyncSortedList.add(activityAdapterListModel1);
                }
            }

            Collections.sort(activityDailySyncSortedList);

            for (int i = activityDailySyncSortedList.size() - 1; i >= 0; i--) {
                activityAdapterListModel activity = activityDailySyncSortedList.get(i);
                int WeekId = (int) activity.getmWeek();
                int index = -1;

                for (int j = 0; j < activityDailySyncWeekDaysSortedList.size(); j++) {
                    activityAdapterListModel activity1 = activityDailySyncWeekDaysSortedList.get(j);
                    if (activity1.getmWeek() == WeekId) {
                        index = j;
                        break;
                    }
                }
                if (index > -1) {
                    activityAdapterListModel activityInSortedList = activityDailySyncWeekDaysSortedList.get(index);
                    //if (activityInSortedList.getmWeek() == WeekId && activityInSortedList.getmMediaImage().isEmpty() && ! activity.getmMediaImage().isEmpty()) {
                    if (activityInSortedList.getmWeek() == WeekId && activityInSortedList.getmMediaImage()==null && activity.getmMediaImage()!=null) {
                        activityDailySyncWeekDaysSortedList.set(index, activity);
                    }
                } else {
                    activityDailySyncWeekDaysSortedList.add(activity);
                }
            }
            //Collections.sort(activityDailySyncSortedList);
            Collections.reverse(activityDailySyncWeekDaysSortedList);

            for(int i=0;i<activityDailySyncWeekDaysSortedList.size();i++){
                try {
                    //ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(convertUTCToLocalTime(activityDailySyncWeekDaysSortedList.get(i).getmDate()), CompitionStartdate);

                    if(CurrentWeek==activityDailySyncWeekDaysSortedList.get(i).getmWeek()){

                        activityDailySyncWeekDaysSortedList.get(i).setCurrentWeek("Present");

                    }else{
                        activityDailySyncWeekDaysSortedList.get(i).setCurrentWeek("Past");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i("ActivityHistory",e.toString());
                }
            }
        }catch (Exception e){
        Log.i("ExceptionActiviyHistory",e.toString());
    }
        return activityDailySyncWeekDaysSortedList;
    }











    @Override
    public void getActivitiesHistory() {



       /* Repository.getUserActivities("", new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mActFragView!=null )

                 mActFragView.setActivitiesHistory(getDummyData(),getDummyChartData());


            }
        });*/


       /* if(mActFragView!=null )
             mActFragView.setActivitiesHistory(getDummyData(),getDummyChartData());*/

    }


    public DailyDiaryQuestionModel getDailyQuestions(){

        //DailyDiaryQuestionModel dailyDiaryQuestionModel =Repository.getDailyDiary();


        return Repository.getDailyDiary();
    }

    @Override
    public void AddActivity(Realm_User user, String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location,String distance,String weight,String Wasit) {

        String  token="bearer "+user.getToken();



        Repository.addActivity(user,token, activity_title,  activity_type_id,  steps_count,  day_name,  stars_count,  calories,  user_activity_time,  activity_image, activity_lat,  activity_long,  activity_location,distance, weight,Wasit,new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mView.setAddActivity(status.getMsg());
                }
                else
                {
                    mView.setAddActivityFailed(status.getMsg());
                }


            }
        });

    }

    @Override
    public void AddActivityV3(Realm_User user, String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location, String distance, String weight, String Wasit) {
        String  token="bearer "+user.getToken();

        Repository.addActivityV3(user,token, activity_title,  activity_type_id,  steps_count,  day_name,  stars_count,  calories,  user_activity_time,  activity_image, activity_lat,  activity_long,  activity_location,distance, weight,Wasit,new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mView.setAddActivityV3(status.getMsg());
                }
                else
                {
                    mView.setAddActivityFailedV3(status.getMsg());
                }

            }
        });
    }

    @Override
    public void AddDailyActivtyV3(Realm_User user,String DayDescription, Boolean DayStatus, List<QuestionResponse> questionResponseList) {

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        String  token="bearer "+user.getToken();

        final diaries diaryDB = mLocalService.where(diaries.class)
                .equalTo("mDiary_Date",currentDateandTime)
                .findFirst();

        if(diaryDB!=null){
            if(diaryDB.getMid()==null){
                List<DiariesUpdateServer> diaryList =new ArrayList();
                DiariesUpdateServer diariesUpdateServer = new DiariesUpdateServer();
                AddUpdateDailyDiaryToServerV3 addUpdateDailyDiaryToServerV3 =new AddUpdateDailyDiaryToServerV3();
                diariesUpdateServer.setmUserId(Integer.parseInt(user.getId()));
                diariesUpdateServer.setmDiary_Date(currentDateandTime);
                diariesUpdateServer.setDayDescription(DayDescription);
                diariesUpdateServer.setDayStatus(DayStatus);
                diariesUpdateServer.setQuestions(questionResponseList);
                diaryList.add(diariesUpdateServer);
                addUpdateDailyDiaryToServerV3.setDiary(diaryList);
                Repository.AddDailyDiary(token,addUpdateDailyDiaryToServerV3,new ResponseStatus() {
                    @Override
                    public void onResult(ResponseStatus status) {

                        if(mView!=null && status.getCode()== 201 && status.getStatus().equals(Repository.STATUS_SUCCESS))
                        {
                            mView.setAddDailyActivitySucess(status.getMsg());
                        }
                        else
                        {
                            mView.setAddDailyActivityFailed(status.getMsg());
                        }

                    }
                });
            }else{
                DiariesUpdateServer diariesUpdateServer = new DiariesUpdateServer();
                diariesUpdateServer.setmUserId(Integer.parseInt(user.getId()));
                diariesUpdateServer.setmDiary_Date(currentDateandTime);
                diariesUpdateServer.setDayDescription(DayDescription);
                diariesUpdateServer.setDayStatus(DayStatus);
                diariesUpdateServer.setQuestions(questionResponseList);

                Repository.UpdateDailyDiary(token,diariesUpdateServer, String.valueOf(diaryDB.getMid()),new ResponseStatus() {
                    @Override
                    public void onResult(ResponseStatus status) {

                        if(mView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                        {
                            mView.setUpdateDailyActivitySucess(status.getMsg());
                        }
                        else
                        {
                            mView.setUpdateDailyActivityFailed(status.getMsg());
                        }

                    }
                });
            }

        }else{
            Toast.makeText(mContext, "Diary Empty", Toast.LENGTH_SHORT).show();
        }




    }
    @Override
    public void UpdateUserPermissionAndVersion(Context context,Realm_User mUser,String AppVersion) {
        try{

            String AndroidOSVersion = Build.VERSION.RELEASE;

            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();
            WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

            String manufacturer = Build.MANUFACTURER;
            String model = Build.MODEL;
            String device = Build.DEVICE;

            Log.i("Agent",device+" "+model+" "+manufacturer);

            Permissions permissions = new Permissions();
            permissions.setCameraPermission(SaveSharedPreference.getCameraPermission(context));
            permissions.setLocationForeground(SaveSharedPreference.getLocationPermissionForeground(context));
            permissions.setLocationBackGround(SaveSharedPreference.getLocationPermissionBackground(context));
            permissions.setStepCounterPermission(SaveSharedPreference.getStepCounterPermission(context));
            permissions.setDrawOverOtherApps(SaveSharedPreference.getDrawOverAppsPermission(context));
            permissions.setStepsForegroundService(SaveSharedPreference.getStepsForeGroundService(mContext));

            UserPermissionAndVersion userPermissionAndVersion =new UserPermissionAndVersion();
            userPermissionAndVersion.setUserId(Integer.parseInt(mUser.getId()));
            userPermissionAndVersion.setTimezone(tz.getID());
            userPermissionAndVersion.setIpAddress(ip);
            userPermissionAndVersion.setAgent(manufacturer+" "+model);
            userPermissionAndVersion.setOperatingSystem("android");
            userPermissionAndVersion.setOperatingSystemVersion(AndroidOSVersion);
            userPermissionAndVersion.setPermissions(permissions);

            userPermissionAndVersion.setAppVersion(AppVersion);


            Realm_User user = provideUserLocal(context);
            if(user!=null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();
                    Repository.UpdateUserPermissionAndVersion(token,userPermissionAndVersion,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if (mView != null && status.getCode() == 201 && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                                mView.setUpdateUserPermissionAndVersionSucess(status.getMsg());
                            } else {
                                mView.setUpdateUserPermissionAndVersionFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){

        }
    }

    @Override
    public void GetTodayStepsFromGoogleFit(Context context, GoogleApiClient googleApiClient) {
        Repository.getGoogleFitTodaySteps(context,googleApiClient,new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if (mView != null && status.getCode() == 200 && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                    mView.GetTodayStepsFromGoogleFitSucess(status.getMsg(),status.getTodayStepsFromGoogleFit());
                } else {
                    mView.GetTodayStepsFromGoogleFitFailed(status.getMsg());
                }
            }
        });
    }

    @Override
    public void AddStepCounterV3(ActivityDaily activityDaily) {
        Repository.AddStepCounterLocalV3(activityDaily);
    }

    @Override
    public ActivityDaily GetStepCounterV3() {
        return Repository.getStepCounterLocalV3();
    }

    @Override
    public void AddUserHeightWeight(Realm_User user,String weight, String height) {

        String  token="bearer "+user.getToken();

        Repository.UpdateWeightHeight(user.getId(),token, weight, height,new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mView.UserHeightWeightSync(status.getMsg());
                }
                else
                {
                    mView.UserHeightWeightSyncFailed(status.getMsg());
                }


            }
        });

    }

    @Override
    public void SyncPastActivities(Context context,List<ActivityDaily> pastActivitiesList) {
        Realm_User user = provideUserLocal(context);
        try {
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    Repository.SyncPastActivities(context, user.getId(), token, pastActivitiesList, new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if (mView != null && status.getCode() == HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                                mView.setSyncPastActivitiesSucess(status.getMsg());
                            } else {
                                mView.setSyncPastActivitiesFailed(status.getMsg());
                            }
                        }
                    });

                }
            }
        }catch (Exception e){
            Log.i("PastActiviExp",e.toString());
        }
    }

    @Override
    public List<ActivityDaily> GetPastActivities(Context context) {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        final List<ActivityDaily>  PastActivities = mLocalService.where(ActivityDaily.class)
                .equalTo("isSyncedWithServer", false)
                .limit(9)
                .sort("mDate", Sort.ASCENDING)
                .findAll();

        if(PastActivities.size()>0){
            //Collections.sort(PastActivities);
            return PastActivities;
        }
        return null;
    }


    private void  setTodayActivity()
    {



    }


    private  DailyActivityModel  getTodayActivity()
    {


             return  null;
    }


    private ArrayList<DailyActivityModel> getDummyData()
    {

        ArrayList<DailyActivityModel>  dailyPicks=new ArrayList<>();

        try {

           /* final RealmResults<DB_DailyFitnessPic> user = mDataBase.where(DB_DailyFitnessPic.class)
                    .equalTo("user_email", SaveSharedPreference.getUserID(tempFragment.getContext()))

                    .findAll();


            if (user.size() > 0) {

                for (int i = 0; i < user.size(); i++) {
                    DailyActivityModel dailyPick = new DailyActivityModel();

                    dailyPicks.add(dailyPick);

                }

            }*/

            if (dailyPicks.size() == 0)
            {
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
                dailyPicks.add(new DailyActivityModel());
            }



        }catch (Exception ex)
        {
            ex.toString();
        }



        return  dailyPicks;


    }



    public  LineData getDummyChartData()
    {
        final float range=10;
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }

        for (int i = 5; i < 10; i++) {

            values.add(new Entry(i,0));
        }
        LineDataSet set1;


        // create a dataset and give it a type
        set1 = new LineDataSet(values, "");

        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(true);
        set1.setDrawCircles(false);
        set1.setLineWidth(1.8f);
        set1.setCircleRadius(4f);
        set1.setCircleColor(Color.GREEN);
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.GREEN);
        set1.setFillColor(Color.GREEN);
        set1.setFillAlpha(100);
        set1.setDrawHorizontalHighlightIndicator(false);

           /* set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });*/

        // create a data object with the data sets
        LineData data = new LineData(set1);
        //data.setValueTypeface(tfLight);
        data.setValueTextSize(9f);
        data.setDrawValues(false);


        return data;
    }






}
