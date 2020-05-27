package com.kampen.riksSe.BroadCastReciver;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.data_manager.LeaderBoardDB_Handler;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_DISTANCE;
import static com.kampen.riksSe.data_manager.Repository.DEVICE_MODEL;
import static com.kampen.riksSe.data_manager.Repository.STATUS_SUCCESS;
import static com.kampen.riksSe.data_manager.Repository.TIME_ZONE;
import static com.kampen.riksSe.data_manager.Repository.USERID;
import static com.kampen.riksSe.utils.UtilityTz.convertDateToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertDynamicTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertStaticTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class DailyStepsService extends IntentService {

    final static double walkingFactor = 0.57;

    static double CaloriesBurnedPerMile;

    static double strip;

    static double stepCountMile; // step/mile

    static double conversationFactor;

    static double CaloriesBurned;



    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param //name Used to name the worker thread, important only for debugging.
     */
    public DailyStepsService() {
        super("DailyStepsService");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Toast.makeText(DailyStepsService.this, "Daily Steps Service....", Toast.LENGTH_LONG).show();

        final  Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try {

            /*SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDateandTime2 = sdf1.format(new Date());
            String lastStepCountDate = SaveSharedPreference.getUserTodayStepsDate(DailyStepsService.this);
            if(currentDateandTime2.equals(lastStepCountDate)){
                UpdateStepsData(mLocalService);
            }else{

                //Toast.makeText(this, "Previous Date change Step Sync", Toast.LENGTH_SHORT).show();
            }*/
            int stepCount = SaveSharedPreference.getUserTodaySteps(DailyStepsService.this);
            String utcTime = convertTimeToUTC();
            //String starCount = String.valueOf(SaveSharedPreference.getUserStarCount(DailyStepsService.this));
            //UpdateStepsData(mLocalService,stepCount,utcTime);


        }catch (Exception e){

            Log.i("",e.toString());

        }

        return START_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        MyBroadcastReceiver.completeWakefulIntent(intent);
    }


    public void UpdateStepsData(Realm mLocalService,int stepCount,String utcTime){

        APIService mWebService;
        //Realm mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        Realm_User user = provideUserLocal(mLocalService);

        String userId = user.getId();
        //int stepCount = SaveSharedPreference.getUserTodaySteps(DailyStepsService.this);
        String caloriesCount = String.valueOf(Math.abs(CaloriesCalulatorFromSteps(user.getHeight_in_cm(),user.getWeight(),stepCount)));
        String Distance = SaveSharedPreference.getDistance(DailyStepsService.this);

        /*String starCount = user.getStars_count();

        if(starCount ==null){
            starCount="0";
        }*/

        Calendar cal = Calendar.getInstance();

        TimeZone tz = cal.getTimeZone();

       // String starCount = String.valueOf(SaveSharedPreference.getUserStarCount(DailyStepsService.this));

        String myDeviceModel = android.os.Build.MODEL;

        //String utcTime = convertTimeToUTC();
        /*SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime2 = sdf1.format(new Date());
        String lastStepCountDate = SaveSharedPreference.getUserTodayStepsDate(DailyStepsService.this);
        String utcTime;
        if(lastStepCountDate.equals(currentDateandTime2)){
            utcTime = convertTimeToUTC();
        }else{
            utcTime = convertDynamicTimeToUTC(lastStepCountDate+" "+"23:59:00");
        }*/




        String  token="bearer "+user.getToken();

        HashMap<String,String> hm = new HashMap();
        hm.put(USERID, userId);
        hm.put("steps_count", String.valueOf(stepCount));
        hm.put("stars_count", user.getStars_count());
        hm.put("calories", caloriesCount);
        hm.put(ACTIVITY_DISTANCE, Distance);
        hm.put(DEVICE_MODEL, myDeviceModel);
        hm.put("user_activity_time", utcTime);
        hm.put(TIME_ZONE, tz.getID());
        hm.put("unit", "high");



        Call<LeaderBoardAllDataV3> call = mWebService.getLeaderBoardAllDataV3(token,hm);


        String finalStarCount = user.getStars_count();
        call.enqueue(new Callback<LeaderBoardAllDataV3>() {
            @Override
            public void onResponse(Call<LeaderBoardAllDataV3> call, Response<LeaderBoardAllDataV3> response) {

                LeaderBoardAllDataV3 obj = null;

                obj = (LeaderBoardAllDataV3) response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    //Toast.makeText(DailyStepsService.this, "Scheduler Data Synced", Toast.LENGTH_SHORT).show();
                    int lastDaySteps = SaveSharedPreference.getUserTodaySteps(DailyStepsService.this);
                    SaveSharedPreference.setUserLastDaySteps(DailyStepsService.this,lastDaySteps);
                    SaveSharedPreference.setUsertodaySteps(DailyStepsService.this,0);
                    SaveSharedPreference.setUserStarCount(DailyStepsService.this,0);
                    SaveSharedPreference.setDistance(DailyStepsService.this,"");

                }
                else if(obj!=null)
                {
                    int lastDaySteps = SaveSharedPreference.getUserTodaySteps(DailyStepsService.this);
                    SaveSharedPreference.setUserLastDaySteps(DailyStepsService.this,lastDaySteps);
                    SaveSharedPreference.setUsertodaySteps(DailyStepsService.this,0);
                    SaveSharedPreference.setUserStarCount(DailyStepsService.this,0);
                    SaveSharedPreference.setDistance(DailyStepsService.this,"");
                    AddActivityStepsLocal(user, String.valueOf(stepCount), finalStarCount,caloriesCount,Distance,utcTime,false);
                }
                else
                {
                    int lastDaySteps = SaveSharedPreference.getUserTodaySteps(DailyStepsService.this);
                    SaveSharedPreference.setUserLastDaySteps(DailyStepsService.this,lastDaySteps);
                    SaveSharedPreference.setUsertodaySteps(DailyStepsService.this,0);
                    SaveSharedPreference.setUserStarCount(DailyStepsService.this,0);
                    SaveSharedPreference.setDistance(DailyStepsService.this,"");
                    AddActivityStepsLocal(user, String.valueOf(stepCount), finalStarCount,caloriesCount,Distance,utcTime,false);
                }



            }

            @Override
            public void onFailure(Call<LeaderBoardAllDataV3> call, Throwable t) {

                t.toString();

                Log.i(t.toString(), "onFailure: ");
                int lastDaySteps = SaveSharedPreference.getUserTodaySteps(DailyStepsService.this);
                SaveSharedPreference.setUserLastDaySteps(DailyStepsService.this,lastDaySteps);
                SaveSharedPreference.setUsertodaySteps(DailyStepsService.this,0);
                SaveSharedPreference.setUserStarCount(DailyStepsService.this,0);
                SaveSharedPreference.setDistance(DailyStepsService.this,"");
                AddActivityStepsLocal(user, String.valueOf(stepCount), finalStarCount,caloriesCount,Distance,utcTime,false);

                Log.i("API Failed MSG :: ",t.getMessage());

            }
        });

    }
    public Realm_User provideUserLocal(Realm realm)
    {

        String[] params= SaveSharedPreference.getLoggedParams(DailyStepsService.this);

        final RealmResults<Realm_User> user = realm.where(Realm_User.class)
                .equalTo(Constants.USER_EMAIL_TAG,params[0].trim())
                .and()
                .equalTo(Constants.USER_PASS_TAG,params[1].trim())
                .findAll();

        Realm_User user1=null;

        if(user.size()>0) {


            user1 = user.get(0);

        }

        return  user1;

    }

    public double CaloriesCalulatorFromSteps(double height ,double weight,double stepsCount){


        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);

        strip = height * 0.415;

        stepCountMile = 160934.4 / strip;

        conversationFactor = CaloriesBurnedPerMile / stepCountMile;

        CaloriesBurned = stepsCount * conversationFactor;

        return CaloriesBurned;
    }


    public static  void AddActivityStepsLocal(final Realm_User  user,final String steps_count,final String stars_count,final String calories,final String distance,final String user_activity_time,Boolean SyncedWithServer){

        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        final UserActivityData userActivityData = mLocalService.where(UserActivityData.class)
                .equalTo(Constants.DAILY_ACTIVITY_DATE_TAG,user_activity_time)
                .findFirst();

        if(userActivityData!= null){

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     UserActivityData db_user = userActivityData;

                                                     db_user.setLocatStepCount(steps_count);
                                                     db_user.setLocatStarCount(stars_count);
                                                     db_user.setLocatCalCount(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setDistance(distance);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
            );

        }
        else{

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     UserActivityData db_user = realm.createObject(UserActivityData.class);

                                                     db_user.setLocatStepCount(steps_count);
                                                     db_user.setLocatStarCount(stars_count);
                                                     db_user.setLocatCalCount(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setDistance(distance);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
            );

        }

    }


    /*public static  void AddActivityStepsLocal(final Realm_User  user,final String steps_count,final String stars_count,final String calories,final String distance,final String user_activity_time,Boolean SyncedWithServer){

        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        final Realm_User Date= mLocalService.where(Realm_User.class)
                .equalTo(Constants.DAILY_ACTIVITY_DATE_TAG,user_activity_time)
                .findFirst();

        if(Date!= null){

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     Realm_User db_user = user;

                                                     db_user.setSteps_count(steps_count);
                                                     db_user.setStars_count(stars_count);
                                                     db_user.setCalories(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setDistance(distance);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
            );

        }
        else{

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     Realm_User db_user = realm.createObject(Realm_User.class);

                                                     db_user.setSteps_count(steps_count);
                                                     db_user.setStars_count(stars_count);
                                                     db_user.setCalories(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setDistance(distance);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
            );

        }

    }*/


}
