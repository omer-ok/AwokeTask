package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.CaloriesDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StarsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StepsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.UserDataV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.BookSchduleSlots;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.SchduleSlots;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ContestantScheduleLiveActivityPresenter implements ContestantScheduleDayActivityContract.Presenter{



    @NonNull
    private final ContestantScheduleDayActivityContract.View mContestantScheduledDayActivityView;



    public ContestantScheduleLiveActivityPresenter(@NonNull ContestantScheduleDayActivityContract.View ContestantScheduledDayActivityView)
    {
       // this.mTrainerHomeActivityView = mTrainerHomeActivityView1;
        mContestantScheduledDayActivityView = checkNotNull(ContestantScheduledDayActivityView);

    }







    public Realm_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }


    @Override
    public void GetContestantDaySchduleSlots(Context context, String Date) {

        try {

            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                     Repository.getContestantDaySchdulesSlots(token,user.getTrainerID(),Date,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if(mContestantScheduledDayActivityView!=null && status.getCode() == HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mContestantScheduledDayActivityView.SetContestantReSchduleSucess(status.getMsg(),status.getContestantDaySchdulesSlotsList());
                            }
                            else
                            {
                                mContestantScheduledDayActivityView.SetContestantReSchduleFiled(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.i("TrainerSchduleExp",e.toString());
        }
    }

    @Override
    public void PostBookedSchduleSlot(Context context, BookSchduleSlots bookSchduleSlots) {
        try {

            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    Repository.getContestantBookSchduledSlot(token,bookSchduleSlots,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if(mContestantScheduledDayActivityView!=null && status.getCode() == 201 && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mContestantScheduledDayActivityView.SetContestantBookedSlotSucess(status.getMsg());
                            }
                            else
                            {
                                mContestantScheduledDayActivityView.SetContestantBookedSlotFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.i("TrainerSchduleExp",e.toString());
        }
    }

    @Override
    public void GetContestantSchdule(Context context) {

        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    Repository.getContestantSchdules(token,user.getId(),new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mContestantScheduledDayActivityView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mContestantScheduledDayActivityView.SetContestantSchduleSucess(status.getMsg());
                            }
                            else
                            {
                                mContestantScheduledDayActivityView.SetContestantSchduleFiled(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.i("TrainerSchduleExp",e.toString());
        }


    }

    @Override
    public void DeleteContestantSchdule(Context context) {
        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    ScheduledLiveVideoCall scheduledLiveVideoCall =  GetSchduleFromSlotID();

                    Repository.DeleteContestantSchdules(token,scheduledLiveVideoCall.getId(),new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mContestantScheduledDayActivityView!=null && status.getCode()==204 && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mContestantScheduledDayActivityView.SetContestantDeleteSchduleSucess(status.getMsg());
                            }
                            else
                            {
                                mContestantScheduledDayActivityView.SetContestantDeleteSchduleFiled(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.i("TrainerSchduleExp",e.toString());
        }

    }
/*

    public static void GetReservedSchduledId(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mLocalService.executeTransaction(realm -> {


            ScheduledLiveVideoCall scheduledLiveVideoCallDB = mLocalService.where(ScheduledLiveVideoCall.class)
                    .equalTo("status","reserved")
                    .contains("",)
                    .findFirst();


        });
    }
*/

    @Override
    public ScheduledLiveVideoCall GetSchduleFromSlotID() {
        return  Repository.GetSchduleFromSlotId();
    }

    @Override
    public List<SchduleSlots> GetContestantDaySchduleSlotsStatus(String SelectedDate,List<SchduleSlots> ContestantDaySchdulesSlotsList) {
        return Repository.GetContestantDaySchduleSlotsStatus(SelectedDate,ContestantDaySchdulesSlotsList);
    }

    @Override
    public Boolean GetBookedSlotOfWeek() {
        return Repository.getBookSlotsOfWeek();
    }

}
