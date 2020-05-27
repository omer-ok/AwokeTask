package com.kampen.riksSe.BroadCastReciver;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import static com.kampen.riksSe.utils.UtilityTz.CaloriesCalulatorFromSteps;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getDistanceNow;

public class UpdateCurrentActivityWithGoogleFit extends IntentService {

    final static double walkingFactor = 0.57;

    static double CaloriesBurnedPerMile;

    static double strip;

    static double stepCountMile; // step/mile

    static double conversationFactor;

    static double CaloriesBurned;
    private FitnessOptions fitnessOptions;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param //name Used to name the worker thread, important only for debugging.
     */
    public UpdateCurrentActivityWithGoogleFit() {
        super("DailyStepsService");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Toast.makeText(DailyStepsService.this, "Daily Steps Service....", Toast.LENGTH_LONG).show();

        final  Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try {
            //Toast.makeText(UpdateCurrentActivityWithGoogleFit.this, "Service Start", Toast.LENGTH_LONG).show();
            if (!Constants.isMyServiceRunning(StepCountingService.class, this)){
                fitnessOptions = FitnessOptions.builder()
                        .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                        .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                        .build();

                ReadStepsData(UpdateCurrentActivityWithGoogleFit.this);
                Realm_User mUser = provideUserLocal(mLocalService);
                int GoogleTodaySteps =  SaveSharedPreference.getGoogleFitTodaySteps(UpdateCurrentActivityWithGoogleFit.this);

                String DisCountDB=null;
                int DisValueDB=0;
                String CalCountDB=null;
                int CalValueDB=0;
                if(GoogleTodaySteps>0){
                    DisCountDB = String.format("%.0f",Math.abs(getDistanceNow(GoogleTodaySteps,mUser.getHeight_in_cm())));
                    DisValueDB = Integer.parseInt(DisCountDB);
                    CalCountDB = String.format("%.0f", Math.abs(CaloriesCalulatorFromSteps(mUser.getHeight_in_cm(),mUser.getWeight(),GoogleTodaySteps)));
                    CalValueDB = Integer.parseInt(CalCountDB);
                }else{
                    DisValueDB =0;
                    CalValueDB =0;
                }
                ActivityDaily activityDaily = new ActivityDaily();
                activityDaily.setmSteps(GoogleTodaySteps);
                activityDaily.setmStars(Integer.parseInt(mUser.getStars_count()));
                activityDaily.setmCalories(CalValueDB);
                activityDaily.setmDistance(DisValueDB);
                activityDaily.setmDate(convertTimeToUTC());
                activityDaily.setSyncedWithServer(false);

                AddStepCounterLocalV3(activityDaily);
               // Toast.makeText(UpdateCurrentActivityWithGoogleFit.this, "GoogleActivity Updated", Toast.LENGTH_LONG).show();
            }else{
              //  Toast.makeText(UpdateCurrentActivityWithGoogleFit.this, "Service Not Off", Toast.LENGTH_LONG).show();
            }


        }catch (Exception e){

            Log.i("",e.toString());
            Toast.makeText(UpdateCurrentActivityWithGoogleFit.this, e.toString(), Toast.LENGTH_LONG).show();

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



    public Realm_User provideUserLocal(Realm realm)
    {

        String[] params= SaveSharedPreference.getLoggedParams(UpdateCurrentActivityWithGoogleFit.this);

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
    public GoogleSignInAccount getGoogleAccount(Context context){
        return GoogleSignIn.getAccountForExtension(context, fitnessOptions);
    }

    public void ReadStepsData(Context context){
        Fitness.getHistoryClient(context, getGoogleAccount(context))
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(new OnSuccessListener<DataSet>() {
                    @Override
                    public void onSuccess(DataSet dataSet) {
                        int totalSteps = 0;
                        if(!dataSet.isEmpty()){
                            totalSteps= dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                        }
                        //Log.i("GoogleFitSteps", String.valueOf(totalSteps));
                     /*   Intent intent2 = new Intent();
                        intent2.putExtra("Steps",totalSteps);
                        intent2.setAction("com.Rikskampen.CUSTOM_INTENT_STEPS");
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent2);*/
                        SaveSharedPreference.setGoogleFitTodayStepsSteps(context,totalSteps);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("GoogleFitSteps", e.toString());
                    }
                });

    }

    public static void AddStepCounterLocalV3(ActivityDaily activityDaily){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try{

            final List<ActivityDaily> activityDailyCurrentDayRecord = new ArrayList();

            final List<ActivityDaily> activityDailyAllData = mLocalService.where(ActivityDaily.class)
                    .findAll();

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTimeStart = sdf.format(new Date());
            String currentDateandTimeEnd = sdf.format(new Date());
            for (int i = 0; i < activityDailyAllData.size(); i++){
                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart + " 00:00:00");
                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd + " 23:59:59");
                Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(activityDailyAllData.get(i).getmDate()));
                if ((ActiviyDateLocal.after(currentDateandTimeStartRange) || ActiviyDateLocal.equals(currentDateandTimeStartRange)) && (ActiviyDateLocal.before(currentDateandTimeEndRange) || ActiviyDateLocal.equals(currentDateandTimeEndRange))) {
                    activityDailyCurrentDayRecord.add(activityDailyAllData.get(i));
                }
            }
            if(activityDailyCurrentDayRecord.size()>0){

                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        try{
                            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                            String currentDateandTimeStart = sdf.format(new Date());
                            String currentDateandTimeEnd = sdf.format(new Date());
                            for(int i = 0; i<activityDailyCurrentDayRecord.size();i++){
                                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart+" 00:00:00");
                                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd+" 23:59:59");
                                Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityDailyCurrentDayRecord.get(i).getmDate()));
                                if((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))){


                                        activityDailyCurrentDayRecord.get(i).setmSteps(activityDaily.getmSteps());
                                        activityDailyCurrentDayRecord.get(i).setmStars(activityDaily.getmStars());
                                        activityDailyCurrentDayRecord.get(i).setmCalories(activityDaily.getmCalories());
                                        activityDailyCurrentDayRecord.get(i).setmDistance(activityDaily.getmDistance());
                                        activityDailyCurrentDayRecord.get(i).setmDate(activityDaily.getmDate());
                                        activityDailyCurrentDayRecord.get(i).setSyncedWithServer(false);

                                        realm.insertOrUpdate(activityDailyCurrentDayRecord.get(i));


                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i("ActivityRealmUpdateFail",e.toString());
                        }

                    }});

            }else{
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        activityDaily.setmMediaImage(null);
                        activityDaily.setSyncedWithServer(false);
                        realm.insertOrUpdate(activityDaily);
                    }
                });
            }
        }catch (Exception e){

           /* Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(e.getStackTrace()));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/
        }
    }




}
