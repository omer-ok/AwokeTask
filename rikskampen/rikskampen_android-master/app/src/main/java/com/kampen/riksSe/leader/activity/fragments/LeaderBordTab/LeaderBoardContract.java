package com.kampen.riksSe.leader.activity.fragments.LeaderBordTab;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;

import java.util.List;


public class LeaderBoardContract {

    interface View extends BaseView<Presenter> {

        void setLeaderBoardAllData(LeaderBoardAllData leaderBoardAllData);
        void setLeaderBoardAllDataFailed(String message);

        void setLeaderBoardAllDataSucessV3(LeaderBoardAllDataV3 leaderBoardAllDataV3);
        void setLeaderBoardAllDataFailedFailedV3(String message);

        void setSyncPastActivitiesSucess(String message);
        void setSyncPastActivitiesFailed(String message);
    }

    interface Presenter extends BasePresenter {


        void getLeaderBoardAllData(Context context);
        void getLeaderBoardAllDataV3(Context context);

        void SyncPastActivities(Context context, List<ActivityDaily> pastActivitiesList);
        List<ActivityDaily> GetPastActivities(Context context);

    }

}
