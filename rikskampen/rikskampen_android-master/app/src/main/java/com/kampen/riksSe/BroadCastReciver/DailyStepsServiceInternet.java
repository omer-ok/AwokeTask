package com.kampen.riksSe.BroadCastReciver;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.api.remote_api.Generic_Result;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_CALORIES;
import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_DISTANCE;
import static com.kampen.riksSe.data_manager.Repository.ACTIVITY_TIME;
import static com.kampen.riksSe.data_manager.Repository.DEVICE_MODEL;
import static com.kampen.riksSe.data_manager.Repository.STARS_COUNT;
import static com.kampen.riksSe.data_manager.Repository.STATUS_SUCCESS;
import static com.kampen.riksSe.data_manager.Repository.STEPS_COUNT;
import static com.kampen.riksSe.data_manager.Repository.TIME_ZONE;
import static com.kampen.riksSe.data_manager.Repository.USERID;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;

public class DailyStepsServiceInternet extends IntentService {

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
    public DailyStepsServiceInternet() {
        super("DailyStepsServiceInternet");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Toast.makeText(DailyStepsService.this, "Daily Steps Service....", Toast.LENGTH_LONG).show();

        final Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

       // UpdateStepsData(mLocalService);

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

        UpdateReceiver.completeWakefulIntent(intent);
    }

    public void UpdateStepsData(Realm mLocalService){

        APIService mWebService;
        //Realm mLocalService;

        try {
            mWebService = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
            Realm_User user = provideUserLocal(mLocalService);

            if (user == null) {
                return;
            } else {

            }
            String userId = user.getId();

            final RealmResults<ActivityDaily> userActivityData = mLocalService.where(ActivityDaily.class)
                    .equalTo("SyncedWithServer", false)
                    .findAll();


            if (userActivityData != null && userActivityData.size() > 0) {


            } else {
                stopSelf();
                return;
            }

            String myDeviceModel = android.os.Build.MODEL;

            String token = "bearer " + user.getToken();

            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();
            //Log.d("Time zone","="+tz.getDisplayName());


            HashMap<String, String> hm = new HashMap();
            HashMap<String, MultipartBody.Part> hm3 = new HashMap();

            List<MultipartBody.Part> activityImageArray =new ArrayList();

            if (userActivityData != null) {
                for (int i = 0; i < userActivityData.size(); i++) {
                    hm.put("steps_count[" + i + "]", String.valueOf(userActivityData.get(i).getmSteps()));
                }
            }
            if (userActivityData != null) {
                for (int i = 0; i < userActivityData.size(); i++) {
                    hm.put("stars_count[" + i + "]", String.valueOf(userActivityData.get(i).getmStars()));
                }
            }
            if (userActivityData != null) {
                for (int i = 0; i < userActivityData.size(); i++) {
                    hm.put("distance[" + i + "]", String.valueOf(userActivityData.get(i).getmDistance()));
                }
            }
        if(userActivityData != null){
            for(int i=0; i<userActivityData.size();i++){
                hm.put("calories["+i+"]", String.valueOf(userActivityData.get(i).getmCalories()));
                //Log.i("calories",userActivityData.get(i).getLocatCalCount());
            }
        }
            if (userActivityData != null) {
                for (int i = 0; i < userActivityData.size(); i++) {
                    hm.put("user_activity_time[" + i + "]", userActivityData.get(i).getmDate());
                    //Log.i("user_activity_time",userActivityData.get(i).getUser_activity_time());
                }
            }

            if (userActivityData != null) {
                for (int i = 0; i < userActivityData.size(); i++) {
                    File finalFile = convertUriIntoFile(Uri.parse(userActivityData.get(i).getmMediaImage()));
                    MultipartBody.Part body=null;
                    if(finalFile!=null)
                    {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), finalFile);
                        body = MultipartBody.Part.createFormData("activity_image", finalFile.getName(), reqFile);
                    }
                    activityImageArray.add(body);

                }
            }



            HashMap<String, String> hm1 = new HashMap();
            hm1.put(USERID, userId);
           /* hm1.put("start_date", userActivityData.get(1).getUser_activity_time());
            hm1.put("end_date", userActivityData.get(userActivityData.size()-1).getUser_activity_time());*/
            hm1.put(DEVICE_MODEL, myDeviceModel);
            hm1.put(TIME_ZONE, tz.getID());

            Call<Generic_Result> call = mWebService.OfflineDataUpload(token, hm, hm1,activityImageArray);

            call.enqueue(new Callback<Generic_Result>() {
                @Override
                public void onResponse(Call<Generic_Result> call, Response<Generic_Result> response) {

                    Generic_Result obj = null;

                    obj = (Generic_Result) response.body();

                    if (obj != null && obj.getCode() == HttpStatus.HTTP_OK && obj.getStatus().equals(STATUS_SUCCESS)) {

                        //MyApplication.showSimpleSnackBarSucess(DailyStepsServiceInternet.this,"Offline data updated ");
                        Toast.makeText(DailyStepsServiceInternet.this, "Offline data updated", Toast.LENGTH_SHORT).show();
                        mLocalService.executeTransaction(new Realm.Transaction() {
                                                             @Override
                                                             public void execute(Realm realm) {

                                                                 RealmResults<ActivityDaily> db_user = userActivityData;

                                                                 for (int i = 0; i < userActivityData.size(); i++) {
                                                                     db_user.get(i).setSyncedWithServer(true);
                                                                 }
                                                                 realm.insertOrUpdate(db_user);
                                                                 stopSelf();
                                                                 return;
                                                             }
                                                         }
                        );

                    } else if (obj != null) {
                        stopSelf();
                        return;
                    } else {
                        stopSelf();
                        return;
                    }


                }

                @Override
                public void onFailure(Call<Generic_Result> call, Throwable t) {

                    t.toString();

                    Log.i(t.toString(), "onFailure: ");
                    stopSelf();
                    return;

                    //Log.i("API Failed MSG :: ", t.getMessage());

                }
            });

        }catch (Exception e){

        }

    }

    public File convertUriIntoFile(Uri uri) {
        File filePath = null;
        if (uri != null) {
            filePath =  new File(String.valueOf(uri));
        }
        return filePath;
    }

    public Realm_User provideUserLocal(Realm realm)
    {

        String[] params= SaveSharedPreference.getLoggedParams(DailyStepsServiceInternet.this);

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

    public float getDistanceNow(long steps,int hightCM){
        double stepLenght = (hightCM * 0.415);
        float Distance = (float) (stepLenght * steps);
        return Distance/100;
    }
}
