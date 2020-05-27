package com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantScheduledLiveVideoCallActivityContract;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ContestantReScheduleLiveVideoCallActivityPresenter implements ContestantReScheduleLiveVideoCallActivityContract.Presenter{



    @NonNull
    private final ContestantReScheduleLiveVideoCallActivityContract.View mContestantReScheduledLiveVideoCallActivityView;



    public ContestantReScheduleLiveVideoCallActivityPresenter(@NonNull ContestantReScheduleLiveVideoCallActivityContract.View ContestantReScheduledLiveVideoCallActivityView)
    {
       // this.mTrainerHomeActivityView = mTrainerHomeActivityView1;
        mContestantReScheduledLiveVideoCallActivityView = checkNotNull(ContestantReScheduledLiveVideoCallActivityView);

    }



    public Realm_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }
}
