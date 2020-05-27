package com.kampen.riksSe.UpdateLoginProfile;

import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.login.LoginContract;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class UpdateLoginProfilePresenter implements UpdateLoginProfileContract.Presenter{



    @NonNull
    private final UpdateLoginProfileContract.View  mUpdateLoginProfileView;



    public UpdateLoginProfilePresenter(@NonNull UpdateLoginProfileContract.View UpdateLoginProfileView)
    {
        mUpdateLoginProfileView = checkNotNull(UpdateLoginProfileView);

    }
    public void  updateUserLocal(Realm_User user)
    {

        Repository.updateUserLocal(user);

    }

    public Realm_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }


    @Override
    public void UpdateLoginProfile(Realm_User mUser,String DOB, String Gender, String Height, String weight, String Wasit,String GoalWeight) {

        Repository.UpdateLoginProfile(mUser,DOB,Gender,Height,weight,Wasit,GoalWeight, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mUpdateLoginProfileView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mUpdateLoginProfileView.UpdateLoginProfileSucess(status.getMsg());
                }
                else
                {
                    mUpdateLoginProfileView.UpdateLoginProfileFailed(status.getMsg());
                }


            }
        });
    }
}
