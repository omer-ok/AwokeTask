package com.kampen.riksSe.ForgotPassword;

import android.content.Context;
import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.ForgotPassword.Model.User;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.ForgotPasswordDB_Handler;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import io.realm.Realm;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter{



    @NonNull
    private final ForgotPasswordContract.View  mForgotPassView;



    public ForgotPasswordPresenter(@NonNull ForgotPasswordContract.View ForgotPassView)
    {
        mForgotPassView = checkNotNull(ForgotPassView);

    }

    @Override
    public void performForgotPassword(String Email) {

        Repository.getForgotPasswordData(Email, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {



                if(mForgotPassView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mForgotPassView.setForgotPassword(status.getMsg(),status.getOTPCODE());
                }
                else
                {
                    mForgotPassView.setForgotPasswordFailed(status.getMsg()); ;
                }

            }
        });


    }


    public User getForgotPasswordAllDataLocal(Context context) {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        return ForgotPasswordDB_Handler.getForgotPasswordData(mLocalService);


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



}
