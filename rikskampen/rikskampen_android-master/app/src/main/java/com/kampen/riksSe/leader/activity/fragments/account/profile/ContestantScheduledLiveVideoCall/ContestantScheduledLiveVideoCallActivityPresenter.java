package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ScheduledLiveVideoCallActivityContract;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ContestantScheduledLiveVideoCallActivityPresenter implements ContestantScheduledLiveVideoCallActivityContract.Presenter{



    @NonNull
    private final ContestantScheduledLiveVideoCallActivityContract.View mContestantScheduledLiveVideoCallActivityView;



    public ContestantScheduledLiveVideoCallActivityPresenter(@NonNull ContestantScheduledLiveVideoCallActivityContract.View ContestantScheduledLiveVideoCallActivityView)
    {
       // this.mTrainerHomeActivityView = mTrainerHomeActivityView1;
        mContestantScheduledLiveVideoCallActivityView = checkNotNull(ContestantScheduledLiveVideoCallActivityView);

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


                            if(mContestantScheduledLiveVideoCallActivityView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mContestantScheduledLiveVideoCallActivityView.SetContestantSchduleSucess(status.getMsg());
                            }
                            else
                            {
                                mContestantScheduledLiveVideoCallActivityView.SetContestantSchduleFiled(status.getMsg());
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
    public void DeleteContestantSchdule(Context context ,int SchduleID) {
        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();
                    Repository.DeleteContestantSchdules(token,SchduleID,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mContestantScheduledLiveVideoCallActivityView!=null && status.getCode()==204 && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mContestantScheduledLiveVideoCallActivityView.SetContestantDeleteSchduleSucess(status.getMsg());
                            }
                            else
                            {
                                mContestantScheduledLiveVideoCallActivityView.SetContestantDeleteSchduleFiled(status.getMsg());
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
    public List<ScheduledLiveVideoCall> GetContestantTodaySchduleLocalDB(String Date) {
        return Repository.getContestantTodaySchdule(Date);
    }

    @Override
    public List<ScheduledLiveVideoCall> GetContestantFutureSchduleLocalDB(String Date) {
        return Repository.getContestantFutureSchdule(Date);
    }


    public Realm_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

}
