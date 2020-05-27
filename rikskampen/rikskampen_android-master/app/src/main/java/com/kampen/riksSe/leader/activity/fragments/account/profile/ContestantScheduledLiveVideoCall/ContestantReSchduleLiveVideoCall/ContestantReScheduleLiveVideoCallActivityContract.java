package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;

import java.util.List;

public class ContestantReScheduleLiveVideoCallActivityContract {

    interface View extends BaseView<Presenter> {

        void SetContestantReSchduleSucess(String mesage);
        void SetContestantReSchduleFiled(String mesage);

    }

    interface Presenter extends BasePresenter {



    }

}
