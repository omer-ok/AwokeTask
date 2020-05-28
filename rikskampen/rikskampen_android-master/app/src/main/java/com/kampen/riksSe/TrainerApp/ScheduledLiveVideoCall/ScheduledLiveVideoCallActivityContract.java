package com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;

import java.util.List;

public class ScheduledLiveVideoCallActivityContract {

    interface View extends BaseView<Presenter> {


       void SetTrainerSchduleSucess(String mesage,List<ScheduledLiveVideoCall> scheduledLiveVideoCallList);
       void SetTrainerSchduleFiled(String mesage);

        void SetContestantSchduleSucess(String mesage);
        void SetContestantSchduleFiled(String mesage);

        void setLogoutSuccess(String message);
        void setLogoutFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void GetTrainerSchdule(Context context);
        void GetContestantSchdule(Context context);
        void GetTrainerSelectedSchdule(Context context, String Date);
        List<ScheduledLiveVideoCall> GetTrainerTodaySchduleLocalDB(Context context, String Date);
        List<ScheduledLiveVideoCall> GetTrainerFutureSchduleLocalDB(Context context, String Date);
        List<ScheduledLiveVideoCall> GetContestantSchduleLocalDB(Context context,String Date);
        void performLogout(Context context);

    }

}
