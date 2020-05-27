package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.LeaderBoardDB_Handler;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class LeaderBoardFragPresenter implements LeaderBoardContract.Presenter {


    @NonNull
    private  LeaderBoardContract.View  mMainView;


    public LeaderBoardFragPresenter(LeaderBoardContract.View  view)
    {
        this.mMainView=view;
    }


    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

    @Override
    public void getLeaderBoardAllData(Context context) {

        String [] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

        String currentDateandTime = sdf.format(new Date());

        String  token="bearer "+user.getToken();

        int stepCount = SaveSharedPreference.getUserTodaySteps(context);

        String caloriesCount = Repository.CaloriesCalulatorFromSteps(Double.parseDouble(String.valueOf(user.getHeight_in_cm())),Double.parseDouble(String.valueOf(user.getWeight())),Double.parseDouble(String.valueOf(stepCount)));

        String Distance = SaveSharedPreference.getDistance(context);

        String myDeviceModel = android.os.Build.MODEL;

        String utcTime = convertTimeToUTC();

        //String starCount = String.valueOf(SaveSharedPreference.getUserStarCount(context));
        String starCount = user.getStars_count();

        Competition CompitionDate = Repository.getCompitionDate();
        Boolean ContestStatus = getCompitionStartDate(context,CompitionDate.getStartDate(),currentDateandTime);


        Repository.getLeaderBoardAllData(user,CompitionDate,ContestStatus,user.getId(),starCount, String.valueOf(stepCount),caloriesCount,Distance,myDeviceModel,utcTime, token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if(mMainView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mMainView.setLeaderBoardAllData((LeaderBoardAllData) status.getData());
                }
                else
                {
                    mMainView.setLeaderBoardAllDataFailed(status.getMsg());
                }

            }
        });

    }

    @Override
    public void getLeaderBoardAllDataV3(Context context) {

        try{
            String [] params = SaveSharedPreference.getLoggedParams(context);

            Competition CompitionDate = Repository.getCompitionDate();

            Realm_User user=Repository.provideUserLocal(params[0],params[1]);

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

            String currentDateandTime = sdf.format(new Date());

            String  token="bearer "+user.getToken();

           // int stepCount = SaveSharedPreference.getUserTodaySteps(context);
            int stepCount = 0;
            String caloriesCount ="0.0";
            String Distance ="0.0";
            activityAdapterListModel todayActivity =  Repository.GetTodayActivityV1(context,convertUTCToLocalTime(CompitionDate.getStartDate()));

            if(todayActivity!=null){
                stepCount = todayActivity.getmSteps();
                caloriesCount = String.valueOf(todayActivity.getmCalories());
                Distance = String.valueOf(todayActivity.getmDistance());
            }


            String myDeviceModel = android.os.Build.MODEL;

            String utcTime = convertTimeToUTC();

            //String starCount = String.valueOf(SaveSharedPreference.getUserStarCount(context));
            String starCount = String.valueOf(user.getStars_count());


            Boolean ContestStatus = getCompitionStartDate(context,CompitionDate.getStartDate(),currentDateandTime);


            Repository.getLeaderBoardAllDataV3(user,CompitionDate,ContestStatus,user.getId(),starCount, String.valueOf(stepCount),caloriesCount,Distance,myDeviceModel,utcTime, token, new ResponseStatus() {
                @Override
                public void onResult(ResponseStatus status) {

                    if(mMainView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                    {
                        mMainView.setLeaderBoardAllDataSucessV3((LeaderBoardAllDataV3) status.getData());
                    }
                    else
                    {
                        mMainView.setLeaderBoardAllDataFailedFailedV3(status.getMsg());
                    }

                }
            });
        }catch (Exception e){

        }



    }
    @Override
    public void SyncPastActivities(Context context, List<ActivityDaily> pastActivitiesList) {
        Realm_User user = provideUserLocal(context);
        try {
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
        try{
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
        }catch (Exception e){

        }

        return null;
    }

    public LeaderBoardAllData getLeaderBoardAllDataLocal(Context context) {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        return LeaderBoardDB_Handler.getAllLeaderBoardData(mLocalService);


    }
    public LeaderBoardAllDataV3 getLeaderBoardAllDataLocalV3(Context context) {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        return LeaderBoardDB_Handler.getAllLeaderBoardDataV3(mLocalService);


    }


}
