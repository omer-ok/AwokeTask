package com.kampen.riksSe.leader.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;
import android.util.Log;

import androidx.annotation.NonNull;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.UpdateAppVersion.AppUpdate;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.api.remote_api.V2_api_model.SyncTable;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateId;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateTable;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.modelV3.Permissions;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.facebook.stetho.server.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static android.content.Context.WIFI_SERVICE;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;
import static com.kampen.riksSe.utils.Constants.getLocalIpV6;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class MainActivityPresenter implements MainActivityContract.Presenter{



    @NonNull
    private final MainActivityContract.View  mMainView;



    public MainActivityPresenter(@NonNull MainActivityContract.View loginView)
    {
        mMainView = checkNotNull(loginView);

    }

    @Override
    public void getAllDataFromServer(Context context) {



            String[] params = SaveSharedPreference.getLoggedParams(context);

            Realm_User user = Repository.provideUserLocal(params[0], params[1]);

            String token = "bearer " + user.getToken();


        Repository.getUserAllData(user.getId(), token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if(mMainView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mMainView.setAllData(status.getMsg());
                }
                else
                {
                    mMainView.setFailed(status.getMsg());
                }

            }
        });

    }

    public List<DayNutritionMealOptionsDB> getNutritionCurrentMealOptions(List<N_WeekDB> nutritionData){
        N_DaysDB n_daysDB = new N_DaysDB();
        List<DayNutritionMealOptionsDB> dayNutritionMealOptionsDB =new ArrayList<>();
        if(nutritionData!=null){

            for(int i=0; i<nutritionData.size();i++){
                if(nutritionData.get(i).getCurrentWeek().equals("present")){
                    n_daysDB = nutritionData.get(i).getDays();
                }
            }
            if(n_daysDB!=null){

                if(n_daysDB.getDay1()!=null && n_daysDB.getDay1().getCurrentDay().equals("present")){
                    dayNutritionMealOptionsDB = n_daysDB.getDay1().getDayNutritionList();
                }else if(n_daysDB.getDay2()!=null && n_daysDB.getDay2().getCurrentDay().equals("present")){
                    dayNutritionMealOptionsDB = n_daysDB.getDay2().getDayNutritionList();
                }else if(n_daysDB.getDay3()!=null && n_daysDB.getDay3().getCurrentDay().equals("present")){
                    dayNutritionMealOptionsDB = n_daysDB.getDay3().getDayNutritionList();
                }else if(n_daysDB.getDay4()!=null && n_daysDB.getDay4().getCurrentDay().equals("present")){
                    dayNutritionMealOptionsDB = n_daysDB.getDay4().getDayNutritionList();
                }else if(n_daysDB.getDay5()!=null && n_daysDB.getDay5().getCurrentDay().equals("present")){
                    dayNutritionMealOptionsDB = n_daysDB.getDay5().getDayNutritionList();
                }else if(n_daysDB.getDay6()!=null && n_daysDB.getDay6().getCurrentDay().equals("present")){
                    dayNutritionMealOptionsDB = n_daysDB.getDay6().getDayNutritionList();
                }else if(n_daysDB.getDay7()!=null && n_daysDB.getDay7().getCurrentDay().equals("present")){
                    dayNutritionMealOptionsDB = n_daysDB.getDay7().getDayNutritionList();
                }

            }


        }

        return dayNutritionMealOptionsDB;
    }


    public List<N_WeekDB> getNutritionData(Context context)
    {
        Realm_User  user=provideUserLocal(context);

        NutritiousDB nutritiousDB = Repository.getNutritionData(user.getId());
        ArrayList<N_WeekDB> n_weekDBArrayList=new ArrayList<>();

        if(nutritiousDB!=null && nutritiousDB.getWeekNutrition()!=null)
        {

            if(nutritiousDB.getWeekNutrition().getWeek1()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek1());


            if(nutritiousDB.getWeekNutrition().getWeek2()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek2());


            if(nutritiousDB.getWeekNutrition().getWeek3()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek3());


            if(nutritiousDB.getWeekNutrition().getWeek4()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek4());


            if(nutritiousDB.getWeekNutrition().getWeek5()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek5());


            if(nutritiousDB.getWeekNutrition().getWeek6()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek6());


            if(nutritiousDB.getWeekNutrition().getWeek7()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek7());


            if(nutritiousDB.getWeekNutrition().getWeek8()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek8());


            if(nutritiousDB.getWeekNutrition().getWeek9()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek9());


            if(nutritiousDB.getWeekNutrition().getWeek10()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek10());



        }

        return n_weekDBArrayList;

    }

    @Override
    public void SyncAllDataFromServer(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);
        try{
            if(user!=null){
                if(user.getToken()!=null){
                    String token = "bearer " + user.getToken();

                    String SyncDate = SaveSharedPreference.getApiSyncedDate(context);

                    Repository.SyncUserAllData(context,user.getId(), token,SyncDate, new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mMainView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mMainView.setSyncAllDataSucess(status.getMsg(),status.getNutritionPDFLINK(),status.getTruncateTable(),status.getTruncateId());
                            }
                            else
                            {
                                mMainView.setSyncAllDataFailed(status.getMsg());
                            }


                        }
                    });
                }

            }
        }catch (Exception e){

        }




    }
    public N_Days_V getNutritionIngrdiantDataV3(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        Realm_User mUser  = provideUserLocal(context);
        if(mUser!=null){
            Competition CompitionDate = Repository.getCompitionDate();
            if(CompitionDate.getStartDate()!=null) {
                ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, convertUTCToLocalTime(CompitionDate.getStartDate()));
                long currentDay = contestWeekDayTimeModel.getDays() + 1;
                long currentWeek = contestWeekDayTimeModel.getWeeks() + 1;
                N_Plans nutritionPlans = Repository.getNutritionDataV3((int) currentWeek);
                N_Days_V nutritionCurrentDay = null;
                for (int i = 0; i < nutritionPlans.getmDays().size(); i++) {
                    if (currentWeek == nutritionPlans.getmDays().get(i).getmWeek() && currentDay == nutritionPlans.getmDays().get(i).getmDay()) {
                        for (int j = 0; j < nutritionPlans.getmDays().get(i).getmMeals().size(); j++) {
                            nutritionCurrentDay = nutritionPlans.getmDays().get(i);
                        }
                    }
                }
                return nutritionCurrentDay;
            }
        }

        return null;
    }

    public List<W_Plans> getTrainingWeek(Context context, String CompitionStartDate){
        List<W_Plans> trainingPlanWeekDataV3 = Repository.getTraningDataV3();
        return trainingPlanWeekDataV3;
    }
    @Override
    public void getLeaderBoardAllData(Context context) {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        String  token="bearer "+user.getToken();

        int stepCount = SaveSharedPreference.getUserTodaySteps(context);

        String caloriesCount = Repository.CaloriesCalulatorFromSteps(Double.parseDouble(String.valueOf(user.getHeight_in_cm())),Double.parseDouble(String.valueOf(user.getWeight())),Double.parseDouble(String.valueOf(stepCount)));

        String Distance = SaveSharedPreference.getDistance(context);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        String currentDateandTime = sdf.format(new Date());
        String myDeviceModel = android.os.Build.MODEL;

       /* Repository.getLeaderBoardAllData(user,user.getId(),user.getStars_count(), String.valueOf(stepCount),caloriesCount,myDeviceModel,Distance,currentDateandTime, token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if(mMainView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mMainView.setLeaderBoardAllData(status.getMsg());
                }
                else
                {
                    mMainView.setLeaderBoardAllDataFailed(status.getMsg());
                }


            }
        });
*/
    }

    @Override
    public void uploadFcmToken(Context context,String FCM) {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        String  token="bearer "+user.getToken();

        Repository.setUserFcmToken(token,user.getId(), FCM, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mMainView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mMainView.setFcmScucess(status.getMsg());
                }
                else
                {
                    mMainView.setFcmFail(status.getMsg());
                }


            }
        });
    }

    @Override
    public void getBadgeCountToken(Context context,String Fcm) {
        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        String  token="bearer "+user.getToken();

        Repository.getChatBadge(token,user.getId(),Fcm, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mMainView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mMainView.setBadgeCountSucess(status.getChatBadgeCount());
                }
                else
                {
                    mMainView.setBadgeCountFailed(status.getMsg());
                }
            }
        });
    }

    @Override
    public void getTodayQuestions(Context context) {
        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        try{
            if(user!=null){
                if(user.getToken()!=null){
                    String  token="bearer "+user.getToken();

                    Repository.getTodayQuestions(token, new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mMainView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mMainView.setTodayQuestionsSucess(status.getChatBadgeCount());
                            }
                            else
                            {
                                mMainView.setTodayQuestionsFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){

        }
    }

    @Override
    public void getAllQuestions(Context context) {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);
        try {
            if (user != null) {
                if (user.getToken() != null) {
                    String  token="bearer "+user.getToken();

                    Repository.getAllQuestions(token,user.getId(), new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if(mMainView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mMainView.setAllQuestionsSucess(status.getMsg());
                            }
                            else
                            {
                                mMainView.setAllQuestionsFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){

        }

    }

    @Override
    public void getDailyDiary(Context context) {

        try{
            Realm_User user = provideUserLocal(context);
            if(user!=null){
                if(user.getToken()!=null){
                    String  token="bearer "+user.getToken();

                    Repository.getDailyDiary(token,user.getId(), new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if(mMainView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mMainView.setDailyDiarySucess(status.getMsg());
                            }
                            else
                            {
                                mMainView.setDailyDiaryFailed(status.getMsg());
                            }
                        }
                    });
                }

            }
        }catch (Exception e){

        }
    }

    @Override
    public void UpdateUserPermissionAndVersion(Context context,Realm_User mUser,String AppVersion) {
        try{

            String AndroidOSVersion = Build.VERSION.RELEASE;

            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();
            WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
            //String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
            String ip = getLocalIpV6();

            String manufacturer = Build.MANUFACTURER;
            String model = Build.MODEL;
            String device = Build.DEVICE;

           // Log.i("IPV6",ip);

            Permissions permissions = new Permissions();
            permissions.setCameraPermission(SaveSharedPreference.getCameraPermission(context));
            permissions.setLocationForeground(SaveSharedPreference.getLocationPermissionForeground(context));
            permissions.setLocationBackGround(SaveSharedPreference.getLocationPermissionBackground(context));
            permissions.setStepCounterPermission(SaveSharedPreference.getStepCounterPermission(context));
            permissions.setDrawOverOtherApps(SaveSharedPreference.getDrawOverAppsPermission(context));
            permissions.setStepsForegroundService(SaveSharedPreference.getStepsForeGroundService(context));

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

                            if (mMainView != null && status.getCode() == 201 && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                                mMainView.setUpdateUserPermissionAndVersionSucess(status.getMsg());
                            } else {
                                mMainView.setUpdateUserPermissionAndVersionFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){

        }
    }

    @Override
    public void SyncTable(String token,SyncTable syncTable) {
        try{
            Repository.SyncTable(token,syncTable ,new ResponseStatus() {
                @Override
                public void onResult(ResponseStatus status) {

                    if (mMainView != null && status.getCode() == 201 && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                        mMainView.setSyncTableSucess(status.getMsg());
                    } else {
                        mMainView.setSyncTableFailed(status.getMsg());
                    }
                }
            });
        }catch (Exception e){

        }
    }

    @Override
    public void getUpdateAppVersion() {
        try{
            Repository.getAppUpdateVersion( new ResponseStatus() {
                @Override
                public void onResult(ResponseStatus status) {

                    if (mMainView != null && status.getCode() == HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                        mMainView.setUpdateAppsucess(status.getMsg());
                    } else {
                        mMainView.setUpdateAppFailed(status.getMsg());
                    }
                }
            });
        }catch (Exception e){

        }

    }
    public AppUpdate getAllPlanData() {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        final AppUpdate appUpdateRealmResults = mLocalService.where(AppUpdate.class)
                .findFirst();

        return appUpdateRealmResults;
    }
    @Override
    public void SyncPastActivities(Context context,List<ActivityDaily> pastActivitiesList) {

        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    Repository.SyncPastActivities(context, user.getId(), token, pastActivitiesList, new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if (mMainView != null && status.getCode() == HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                                mMainView.setSyncPastActivitiesSucess(status.getMsg());
                            } else {
                                mMainView.setSyncPastActivitiesFailed(status.getMsg());
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
            /*List<ActivityDaily> PastActivitiesList = new ArrayList();
            PastActivitiesList = PastActivities;
            Collections.sort(PastActivitiesList)*/;
            return PastActivities;
        }
        return null;
    }


    public void updateStarUserLocal( String starCount,String userID)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        try {
            final Realm_User updateStarLocal = mLocalService.where(Realm_User.class)
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
    public Realm_User provideUserLocal(Context context)
    {

        try{
            String [] params= SaveSharedPreference.getLoggedParams(context);

            Realm_User user=Repository.provideUserLocal(params[0],params[1]);
            return user;
        }catch (Exception e){

        }


        return  null;
    }





}
