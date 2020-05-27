package com.kampen.riksSe.leader.activity.fragments.home.addactivity;


import android.content.Context;

import com.github.mikephil.charting.data.LineData;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.QuestionResponse;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.user.model.Realm_User;

import java.io.File;
import java.util.List;


public class ActivityFragContract   {

    interface View extends BaseView<ActivityFragContract.Presenter> {



        void setActivitiesHistory(Object data, LineData chartData);
        void setAddActivity(String message);
        void setAddActivityFailed(String message);

        void setAddActivityV3(String message);
        void setAddActivityFailedV3(String message);

        void UserHeightWeightSync(String message);
        void UserHeightWeightSyncFailed(String message);

        public void setStarStepChaseSucess(String message);
        public void setStarStepChaseFailed(String message);

        public void setAddDailyActivitySucess(String message);
        public void setAddDailyActivityFailed(String message);

        public void setUpdateDailyActivitySucess(String message);
        public void setUpdateDailyActivityFailed(String message);

        void setSyncPastActivitiesSucess(String message);
        void setSyncPastActivitiesFailed(String message);

        void setUpdateUserPermissionAndVersionSucess(String message);
        void setUpdateUserPermissionAndVersionFailed(String message);

        void GetTodayStepsFromGoogleFitSucess(String message, String TodayStepsGoogleFit);
        void GetTodayStepsFromGoogleFitFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void getActivitiesHistory();

        void AddActivity(Realm_User user, String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location, String Distance,String weight,String Wasit);
        void AddActivityV3(Realm_User user, String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location, String Distance,String weight,String Wasit);
        void AddDailyActivtyV3(Realm_User user,String DayDescription, Boolean DayStatus, List<QuestionResponse> questionResponseList);
        void AddStepCounterV3(ActivityDaily activityDaily);
        ActivityDaily GetStepCounterV3();
        void AddUserHeightWeight(Realm_User user,String weight,String Wasit);
        void SyncPastActivities(Context context, List<ActivityDaily> pastActivitiesList);
        List<ActivityDaily> GetPastActivities(Context context);
        void UpdateUserPermissionAndVersion(Context context, Realm_User mUser, String AppVersion);
        void GetTodayStepsFromGoogleFit(Context context, GoogleApiClient googleApiClient);
    }
}
