package com.kampen.riksSe.BroadCastReciver;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.modelV3.Permissions;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.Calendar;
import java.util.HashMap;
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
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;

public class StepCountingStop extends IntentService {

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
    public StepCountingStop() {
        super("DailyStepsService");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Toast.makeText(DailyStepsService.this, "Daily Steps Service....", Toast.LENGTH_LONG).show();

        final  Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try {


            UpdateStepCounting(StepCountingStop.this,mLocalService);

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

    public void UpdateStepCounting(Context mContext,Realm mLocalService){

        Realm_User mUser = provideUserLocal(mLocalService);

        String AndroidOSVersion = Build.VERSION.RELEASE;

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        WifiManager wm = (WifiManager) mContext.getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        String device = Build.DEVICE;

            String version = null;
            try {

                PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
                version = pInfo.versionName;
                Log.i("AppVersion",version);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        Log.i("Agent",device+" "+model+" "+manufacturer);

        Permissions permissions = new Permissions();
        permissions.setCameraPermission(SaveSharedPreference.getCameraPermission(mContext));
        permissions.setLocationForeground(SaveSharedPreference.getLocationPermissionForeground(mContext));
        permissions.setLocationBackGround(SaveSharedPreference.getLocationPermissionBackground(mContext));
        permissions.setStepCounterPermission(SaveSharedPreference.getStepCounterPermission(mContext));
        permissions.setDrawOverOtherApps(SaveSharedPreference.getDrawOverAppsPermission(mContext));
        permissions.setStepsForegroundService(SaveSharedPreference.getStepsForeGroundService(mContext));

        UserPermissionAndVersion userPermissionAndVersion =new UserPermissionAndVersion();
        userPermissionAndVersion.setUserId(Integer.parseInt(mUser.getId()));
        userPermissionAndVersion.setTimezone(tz.getID());
        userPermissionAndVersion.setIpAddress(ip);
        userPermissionAndVersion.setAgent(manufacturer+" "+model);
        userPermissionAndVersion.setOperatingSystem("android");
        userPermissionAndVersion.setOperatingSystemVersion(AndroidOSVersion);
        userPermissionAndVersion.setPermissions(permissions);
        userPermissionAndVersion.setAppVersion(version);

        APIService mWebService;


        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        Call<Object> call = mWebService.UpdateUserPermissionAndVersion("bearer "+mUser.getToken(),userPermissionAndVersion);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Object obj = null;

                if(response.isSuccessful()){

                   // Toast.makeText(StepCountingStop.this, "Permission Upodated ", Toast.LENGTH_SHORT).show();

                }else{

                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                t.toString();

                Log.i(t.toString(), "onFailure: ");

               // Toast.makeText(StepCountingStop.this, "Permission Failed", Toast.LENGTH_SHORT).show();

                Log.i("API Failed MSG :: ",t.getMessage());

            }
        });

    }

    public Realm_User provideUserLocal(Realm realm)
    {

        String[] params= SaveSharedPreference.getLoggedParams(StepCountingStop.this);

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
