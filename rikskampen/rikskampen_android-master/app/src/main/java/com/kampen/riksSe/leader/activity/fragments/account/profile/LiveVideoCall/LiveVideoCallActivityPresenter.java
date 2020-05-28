package com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ContestantReScheduleLiveVideoCallActivityContract;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class LiveVideoCallActivityPresenter implements LiveVideoCallActivityContract.Presenter{



    @NonNull
    private final LiveVideoCallActivityContract.View mLiveVideoCallActivityView;



    public LiveVideoCallActivityPresenter(@NonNull LiveVideoCallActivityContract.View LiveVideoCallActivityView)
    {

        mLiveVideoCallActivityView = checkNotNull(LiveVideoCallActivityView);

    }



    public Realm_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

    @Override
    public void getLiveVideoCallAccessToken(Context context,String TrainerID,String userID) {
        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    Repository.getLiveVideoTokenSchdules(token,TrainerID,userID,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mLiveVideoCallActivityView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mLiveVideoCallActivityView.SetLiveVideoCallTokenSucess(status.getMsg(),status.getLiveVideoCallToken());
                            }
                            else
                            {
                                mLiveVideoCallActivityView.SetLiveVideoCallTokenFiled(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.i("LIveVIdeoExp",e.toString());
        }

    }
}
