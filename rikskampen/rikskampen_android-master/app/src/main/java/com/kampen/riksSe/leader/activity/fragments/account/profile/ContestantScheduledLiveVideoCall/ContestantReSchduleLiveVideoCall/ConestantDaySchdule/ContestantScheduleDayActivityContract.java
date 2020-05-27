package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.BookSchduleSlots;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.SchduleSlots;

import java.util.List;

public class ContestantScheduleDayActivityContract {

    interface View extends BaseView<Presenter> {

        void SetContestantReSchduleSucess(String mesage,List<SchduleSlots> ContestantDaySchdulesSlotsList );
        void SetContestantReSchduleFiled(String mesage);

        void SetContestantBookedSlotSucess(String message);
        void SetContestantBookedSlotFailed(String message);

        void SetContestantSchduleSucess(String mesage);
        void SetContestantSchduleFiled(String mesage);

        void SetContestantDeleteSchduleSucess(String mesage);
        void SetContestantDeleteSchduleFiled(String mesage);

    }

    interface Presenter extends BasePresenter {

        void GetContestantDaySchduleSlots(Context context, String Date);
        void PostBookedSchduleSlot(Context context, BookSchduleSlots bookSchduleSlots);
        void GetContestantSchdule(Context context);
        void DeleteContestantSchdule(Context context);
        ScheduledLiveVideoCall GetSchduleFromSlotID();
        List<SchduleSlots> GetContestantDaySchduleSlotsStatus(String SelectedDate,List<SchduleSlots> ContestantDaySchdulesSlotsList);
        Boolean GetBookedSlotOfWeek();

    }

}
