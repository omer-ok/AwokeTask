package com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ScheduledLiveVideoCallActivityPresenter implements ScheduledLiveVideoCallActivityContract.Presenter{



    @NonNull
    private final ScheduledLiveVideoCallActivityContract.View  mScheduledLiveVideoCallActivityView;



    public ScheduledLiveVideoCallActivityPresenter(@NonNull ScheduledLiveVideoCallActivityContract.View ScheduledLiveVideoCallActivityView)
    {
       // this.mTrainerHomeActivityView = mTrainerHomeActivityView1;
        mScheduledLiveVideoCallActivityView = checkNotNull(ScheduledLiveVideoCallActivityView);

    }

    @Override
    public void GetTrainerSchdule(Context context) {

        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    Repository.getTrainerSchdules(token,user.getId(),new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mScheduledLiveVideoCallActivityView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                //mScheduledLiveVideoCallActivityView.SetTrainerSchduleSucess(status.getMsg());
                            }
                            else
                            {
                                mScheduledLiveVideoCallActivityView.SetTrainerSchduleFiled(status.getMsg());
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


                            if(mScheduledLiveVideoCallActivityView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mScheduledLiveVideoCallActivityView.SetContestantSchduleSucess(status.getMsg());
                            }
                            else
                            {
                                mScheduledLiveVideoCallActivityView.SetContestantSchduleFiled(status.getMsg());
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
    public void GetTrainerSelectedSchdule(Context context, String Date) {
        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();

                    Repository.getTrainerSelectedDateSchdules(token,user.getId(),Date,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mScheduledLiveVideoCallActivityView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mScheduledLiveVideoCallActivityView.SetTrainerSchduleSucess(status.getMsg(),status.getScheduledLiveVideoCallList());
                            }
                            else
                            {
                                mScheduledLiveVideoCallActivityView.SetTrainerSchduleFiled(status.getMsg());
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
    public List<ScheduledLiveVideoCall>  GetTrainerTodaySchduleLocalDB(Context context, String Date) {

        return Repository.getTraingTodaySchdule(Date);
    }

    @Override
    public List<ScheduledLiveVideoCall>  GetTrainerFutureSchduleLocalDB(Context context, String Date) {

        return Repository.getTraingFutureSchdule(Date);
    }

    @Override
    public List<ScheduledLiveVideoCall> GetContestantSchduleLocalDB(Context context,String Date) {
        return Repository.getContestantTodaySchdule(Date);
    }

    @Override
    public void performLogout(Context context) {
        try {
            Realm_User user = provideUserLocal(context);
            if (user != null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();
                    String myDeviceModel = android.os.Build.MODEL;
                    Repository.logoutTrainer(context,token,user.getId(),myDeviceModel,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {


                            if(mScheduledLiveVideoCallActivityView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                            {
                                mScheduledLiveVideoCallActivityView.setLogoutSuccess(status.getMsg());
                            }
                            else
                            {
                                mScheduledLiveVideoCallActivityView.setLogoutFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.i("TrainerSchduleExp",e.toString());
        }
    }


    public Realm_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

}
