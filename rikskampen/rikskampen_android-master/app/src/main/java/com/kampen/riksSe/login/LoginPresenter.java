package com.kampen.riksSe.login;

import android.content.Context;
import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter{



    @NonNull
    private final LoginContract.View  mLoginView;



    public LoginPresenter(@NonNull LoginContract.View loginView)
    {
        mLoginView = checkNotNull(loginView);

    }



    @Override
    public void performLogin(Context context,String email, String pass,String DeviceModel,String DeviceSDK,String ipAddress,String LoginTime,Boolean status) {

               Repository.getUser(context,email, pass, DeviceModel, DeviceSDK, ipAddress, LoginTime ,status ,new ResponseStatus() {
                   @Override
                   public void onResult(ResponseStatus status) {


                       if(mLoginView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                       {
                           mLoginView.setLogin(status.getMsg(),status.getLoginStatus(),status.getDeveloper(),status.getmUserRoleId());
                       }
                       else
                       {
                           mLoginView.setLoginFailed(status.getMsg());
                       }
                   }
               });


       /* Repository.getUserAllData(email, pass, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mLoginView!=null && status.getCode()==HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mLoginView.setLogin(status.getMsg());
                }
                else
                {
                    mLoginView.setLoginFailed(status.getMsg());
                }


            }
        });*/

    }


    @Override
    public String GetUserLocalWithoutAPI(String Email, String Pass) {





        return Repository.GetUserLocalWithoutAPI(Email,Pass);
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
