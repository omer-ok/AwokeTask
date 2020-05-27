package com.kampen.riksSe.leader.activity;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.api.remote_api.V2_api_model.SyncTable;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateId;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateTable;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.user.model.Realm_User;

import java.util.List;


public class MainActivityContract {

    interface View extends BaseView<Presenter> {

        void setAllData(String message);
        void setFailed(String message);

        void setSyncAllDataSucess(String message, String NutritionPDFURL, TruncateTable truncateTable, TruncateId truncateId);
        void setSyncAllDataFailed(String message);

        void setLeaderBoardAllData(String message);
        void setLeaderBoardAllDataFailed(String message);

        void setFcmScucess(String message);
        void setFcmFail(String message);

        void setBadgeCountSucess(String message);
        void setBadgeCountFailed(String message);

        void setTodayQuestionsSucess(String message);
        void setTodayQuestionsFailed(String message);

        void setAllQuestionsSucess(String message);
        void setAllQuestionsFailed(String message);

        void setDailyDiarySucess(String message);
        void setDailyDiaryFailed(String message);

        void setSyncPastActivitiesSucess(String message);
        void setSyncPastActivitiesFailed(String message);

        void setUpdateAppsucess(String message);
        void setUpdateAppFailed(String message);

        void setUpdateUserPermissionAndVersionSucess(String message);
        void setUpdateUserPermissionAndVersionFailed(String message);

        void setSyncTableSucess(String message);
        void setSyncTableFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void getAllDataFromServer(Context context);
        void SyncAllDataFromServer(Context context);
        void getLeaderBoardAllData(Context context);
        void uploadFcmToken(Context context,String Fcm);
        void getBadgeCountToken(Context context,String Fcm);
        void getTodayQuestions(Context context);
        void getAllQuestions(Context context);
        void getDailyDiary(Context context);
        void UpdateUserPermissionAndVersion(Context context, Realm_User mUser, String AppVersion);

        void SyncTable(String token,SyncTable syncTable);


        void getUpdateAppVersion();

        void SyncPastActivities(Context context,List<ActivityDaily> pastActivitiesList);
        List<ActivityDaily> GetPastActivities(Context context);



    }
    public interface OnTransactionCallback {
        void onRealmSuccess();
        void onRealmError(final Throwable e);
    }
}
