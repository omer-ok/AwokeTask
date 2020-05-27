package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;

import java.util.List;

public class ContestantScheduledLiveVideoCallActivityContract {

    interface View extends BaseView<Presenter> {

        void SetContestantSchduleSucess(String mesage);
        void SetContestantSchduleFiled(String mesage);

        void SetContestantDeleteSchduleSucess(String mesage);
        void SetContestantDeleteSchduleFiled(String mesage);

    }

    interface Presenter extends BasePresenter {

        void GetContestantSchdule(Context context);
        void DeleteContestantSchdule(Context context ,int SchduleID);

        List<ScheduledLiveVideoCall> GetContestantTodaySchduleLocalDB(String Date);
        List<ScheduledLiveVideoCall> GetContestantFutureSchduleLocalDB(String Date);

    }

}
