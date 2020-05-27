package com.kampen.riksSe.leader.activity.fragments.account.profile;


import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;
import android.util.Log;

import androidx.annotation.NonNull;


import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.modelV3.Permissions;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.Sort;

import static android.content.Context.WIFI_SERVICE;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class ProfilePresenter implements ProfileContract.Presenter{



    @NonNull
    private final ProfileContract.View  mProfileView;



    public ProfilePresenter(@NonNull ProfileContract.View loginView)
    {

        mProfileView = checkNotNull(loginView);

    }


    @Override
    public void performLogout(Context context) {
        try{
            Realm_User user=provideUserLocal(context);
            Competition CompitionDate = Repository.getCompitionDate();
            int stepCount = 0;
            String caloriesCount ="0.0";
            String Distance ="0.0";

            activityAdapterListModel todayActivity =  Repository.GetTodayActivityV1(context,convertUTCToLocalTime(CompitionDate.getStartDate()));


            if(todayActivity!=null){
                stepCount = todayActivity.getmSteps();
                caloriesCount = String.valueOf(todayActivity.getmCalories());
                Distance = String.valueOf(todayActivity.getmDistance());
            }


            String utcTime = convertTimeToUTC();
            String myDeviceModel = android.os.Build.MODEL;

            //String starCount = String.valueOf(SaveSharedPreference.getUserStarCount(context));
            String starCount = user.getStars_count();
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());


            Boolean ContestStatus = getCompitionStartDate(context,convertUTCToLocalTime(CompitionDate.getStartDate()),currentDateandTime);

            Repository.logoutUser(context,CompitionDate,ContestStatus,user.getToken(),user.getId(), String.valueOf(stepCount),starCount,caloriesCount,Distance,myDeviceModel,utcTime, new ResponseStatus() {
                @Override
                public void onResult(ResponseStatus status) {


                    if(mProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                    {
                        mProfileView.setLogoutSuccess(status.getMsg());
                    }
                    else
                    {
                        mProfileView.setLogoutFailed(status.getMsg());
                    }

                }
            });
        }catch (Exception e){
            Log.i("logoutExcep",e.toString());
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

                            if (mProfileView != null && status.getCode() == HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                                mProfileView.setSyncPastActivitiesSucess(status.getMsg());
                            } else {
                                mProfileView.setSyncPastActivitiesFailed(status.getMsg());
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

    @Override
    public void UpdateUserPermissionAndVersion(Context context, Realm_User mUser, String AppVersion) {
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
            permissions.setStepsForegroundService(false);

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

                            if (mProfileView != null && status.getCode() == 201 && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                                mProfileView.setUpdateUserPermissionAndVersionSucess(status.getMsg());
                            } else {
                                mProfileView.setUpdateUserPermissionAndVersionFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){

        }
    }

    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }


    @Override
    public void getUserProfilePhoto(Context context) {


        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);


        if(mProfileView!=null && user!=null)
        {

               mProfileView.setProfile(user);
        }


    }

    @Override
    public void getChatDataFromServer(Context context) {
        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        String token = "bearer " + user.getToken();


        Repository.getChatData(user.getId(), token ,new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mProfileView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mProfileView.setChatTrainerSucess(status.getMsg());
                }
                else
                {
                    mProfileView.setChatTrainerFailed(status.getMsg());
                }


            }
        });

    }


}
