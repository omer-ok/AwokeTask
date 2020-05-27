package com.kampen.riksSe.BroadCastReciver;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.PastActivitiesListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.PastActivityModel;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_DISTANCE;
import static com.kampen.riksSe.data_manager.Repository.API_HIT_FAILED;
import static com.kampen.riksSe.data_manager.Repository.DEVICE_MODEL;
import static com.kampen.riksSe.data_manager.Repository.STATUS_FAILED;
import static com.kampen.riksSe.data_manager.Repository.STATUS_SUCCESS;
import static com.kampen.riksSe.data_manager.Repository.SyncPastActivitiesLocal;
import static com.kampen.riksSe.data_manager.Repository.SyncPastActivitiesLocalV1;
import static com.kampen.riksSe.data_manager.Repository.TIME_ZONE;
import static com.kampen.riksSe.data_manager.Repository.USERID;
import static com.kampen.riksSe.utils.Constants.MULTIPART_FORM_DATA;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUriIntoFile;
import static com.kampen.riksSe.utils.UtilityTz.getAddressFromLatLong;

public class DailyActivitySyncService extends IntentService {

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
    public DailyActivitySyncService() {
        super("DailyStepsService");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Toast.makeText(DailyStepsService.this, "Daily Steps Service....", Toast.LENGTH_LONG).show();

        final  Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try {

            List<ActivityDaily> pastActivities = GetPastActivities();

            if(pastActivities.size()>0){
                Realm_User user = provideUserLocal(mLocalService);

                String userId = user.getId();
                String  token="bearer "+user.getToken();
                SyncPastActivities(userId,token,pastActivities);

            }



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


    public List<ActivityDaily> GetPastActivities() {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        final List<ActivityDaily>  PastActivities = mLocalService.where(ActivityDaily.class)
                .equalTo("isSyncedWithServer", false)
                .limit(9)
                .sort("mDate", Sort.ASCENDING)
                .findAll();

        if(PastActivities.size()>0){
            return PastActivities;
        }
        return null;
    }

    public static void SyncPastActivities(String userId,String token,List<ActivityDaily> pastActivitiesList){
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        String myDeviceModel = android.os.Build.MODEL;

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        //Log.d("Time zone","="+tz.getDisplayName());


        TreeMap<String, RequestBody> hm = new TreeMap();
        TreeMap<String, RequestBody> PastLatList = new TreeMap();
        TreeMap<String, RequestBody> PastLongList = new TreeMap();
        TreeMap<String, RequestBody> PastLocationAddressList = new TreeMap();
        TreeMap<String, RequestBody> PastStepsList = new TreeMap();
        TreeMap<String, RequestBody> PastStarsList = new TreeMap();
        TreeMap<String, RequestBody> PastCaloriesList = new TreeMap();
        TreeMap<String, RequestBody> PastDistanceList = new TreeMap();
        TreeMap<String, RequestBody> PastDateTimeList = new TreeMap();
        TreeMap<String, RequestBody> PastWeightList = new TreeMap();
        TreeMap<String, RequestBody> PastWaistList = new TreeMap();
        TreeMap<String, RequestBody> PastImageList = new TreeMap();
        TreeMap<String, Boolean> hm3 = new TreeMap();
        List<MultipartBody.Part> activityImageArray =new ArrayList();
        //List<ActivityDaily> PastActivitiesList =new ArrayList();
        /*List<RequestBody> PastStepsList =new ArrayList();
        List<RequestBody> PastStarsList =new ArrayList();
        List<RequestBody> PastCaloriesList =new ArrayList();
        List<RequestBody> PastDistanceList =new ArrayList();
        List<RequestBody> PastDateTimeList =new ArrayList();
        List<RequestBody> PastWeightList =new ArrayList();
        List<RequestBody> PastWaistList =new ArrayList();
        List<RequestBody> PastImageList =new ArrayList();*/
        RequestBody platform = createPartFromString("android");
        //hm.put("platform", platform);

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++){
                if(pastActivitiesList.get(i).getmLatitude()!=0 ){
                    RequestBody latitude = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmLatitude()));
                    PastLatList.put("activity_lat[" + i + "]", latitude);
                }
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                if(pastActivitiesList.get(i).getmLongitude()!=0 ){
                    RequestBody longitude = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmLongitude()));
                    PastLongList.put("activity_long[" + i + "]", longitude);
                }
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                if(pastActivitiesList.get(i).getmLocationAddress()!=null /*|| !pastActivitiesList.get(i).getmLocationAddress().equals("")*/){
                    RequestBody locationAddress = createPartFromString(pastActivitiesList.get(i).getmLocationAddress());
                    PastLocationAddressList.put("activity_location[" + i + "]", locationAddress);
                }/*else (pastActivitiesList.get(i).getmLatitude()!=0 &&pastActivitiesList.get(i).getmLongitude()!=0){
                    LatLng latLng =new LatLng(pastActivitiesList.get(i).getmLatitude(),pastActivitiesList.get(i).getmLongitude());
                    RequestBody locationAddress = createPartFromString(String.valueOf(getAddressFromLatLong(context,latLng)));
                    PastLocationAddressList.put("activity_location[" + i + "]", locationAddress);
                }*/
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody steps = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmSteps()));
                //hm.put("steps_count[]", steps);

                PastStepsList.put("steps_count[" + i + "]", steps);
                //PastStepsList.add(steps);
            }
        }
        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody stars = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmStars()));
                PastStarsList.put("stars_count[" + i + "]", stars);
            }
        }
        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody distance = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmDistance()));
                PastDistanceList.put("distance[" + i + "]", distance);
                //hm.put("distance[]", distance);
                //PastDistanceList.add(distance);
            }
        }
        if(pastActivitiesList != null){
            for(int i=0; i<pastActivitiesList.size();i++){
                RequestBody calories = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmCalories()));
                PastCaloriesList.put("calories["+ i +"]", calories);
                //PastCaloriesList.add(calories);
            }
        }
        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody dateTime = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmDate()));
                PastDateTimeList.put("user_activity_time["+ i +"]", dateTime);
                //PastDateTimeList.add(dateTime);
                //PastDateTimeList.add(dateTime);
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody weight = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmWeight()));
                PastWeightList.put("weight[" + i + "]", weight);
                //hm.put("weight[]", weight);
                //PastWeightList.add(weight);
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody waist = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmWaist()));
                PastWaistList.put("waist[" + i + "]", waist);

            }
        }


        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {


                if(pastActivitiesList.get(i).getmMediaImage()==null){

                }else if(pastActivitiesList.get(i).getmMediaImage().isEmpty()) {

                }else if(pastActivitiesList.get(i).getmMediaImage().contains("https")){
                    RequestBody mediaImage = createPartFromString(pastActivitiesList.get(i).getmMediaImage());
                    //hm3.put("is_server_activity_image [" + i + "]", true);
                    PastImageList.put("activity_image[" + i + "]", mediaImage);
                    //hm.put("activity_image[]", mediaImage);
                    //PastImageList.add(mediaImage);

                }else{
                    File finalFile = convertUriIntoFile(Uri.parse(pastActivitiesList.get(i).getmMediaImage()));
                    MultipartBody.Part body=null;
                    if(finalFile!=null)
                    {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), finalFile);
                        body = MultipartBody.Part.createFormData("activity_image[" + i + "]", finalFile.getName(), reqFile);
                        //body = MultipartBody.Part.create(reqFile);
                    }
                    //hm3.put("is_server_activity_image [" + i + "]", false);
                    activityImageArray.add(body);
                    //hm3.put("activity_image[" + i + "]", body);
                }
            }
        }



        HashMap<String, RequestBody> hm1 = new HashMap();
        RequestBody userID = createPartFromString(userId);
        RequestBody deviceModel = createPartFromString(myDeviceModel);
        RequestBody timezone = createPartFromString(tz.getID());

        hm.put(USERID, userID);
        hm.put(DEVICE_MODEL, deviceModel);
        hm.put(TIME_ZONE, timezone);





        Call<List<PastActivityModel>> call = mWebService.SyncPastActivities(token,hm,PastLocationAddressList,PastLatList,PastLongList,PastStepsList,PastStarsList,PastCaloriesList,PastDistanceList,PastDateTimeList,PastWeightList,PastWaistList,PastImageList,activityImageArray);


        call.enqueue(new Callback<List<PastActivityModel>>() {
            @Override
            public void onResponse(Call<List<PastActivityModel>> call, Response<List<PastActivityModel>> response) {

                List<PastActivityModel> obj = null;

                obj = response.body();


                if(response.isSuccessful()){

                    //SyncPastActivitiesLocal(obj ,pastActivitiesList);
                    SyncPastActivitiesLocalV1(obj,pastActivitiesList);

                }else
                {

                }

            }

            @Override
            public void onFailure(Call<List<PastActivityModel>> call, Throwable t) {

                t.toString();

                Log.i(t.toString(), "onFailure: ");



                Log.i("API Failed MSG :: ",t.getMessage());

            }
        });

    }
    @NonNull
    private static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }

    public Realm_User provideUserLocal(Realm realm)
    {

        String[] params= SaveSharedPreference.getLoggedParams(DailyActivitySyncService.this);

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
