package com.kampen.riksSe.leader.activity.fragments.account.profile;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.user.model.Realm_User;

import java.util.List;

public class ProfileContract {

    interface View extends BaseView<Presenter> {

        void setProfile(Realm_User user);

        void setLogoutSuccess(String message);
        void setLogoutFailed(String message);

        void setChatTrainerSucess(String message);
        void setChatTrainerFailed(String message);

        void setSyncPastActivitiesSucess(String message);
        void setSyncPastActivitiesFailed(String message);

        void setUpdateUserPermissionAndVersionSucess(String message);
        void setUpdateUserPermissionAndVersionFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void performLogout(Context context);

        void getUserProfilePhoto(Context context);

        void getChatDataFromServer(Context context);

        void SyncPastActivities(Context context, List<ActivityDaily> pastActivitiesList);

        List<ActivityDaily> GetPastActivities(Context context);

        void UpdateUserPermissionAndVersion(Context context, Realm_User mUser, String AppVersion);

    }

}
