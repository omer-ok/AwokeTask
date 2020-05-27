package com.kampen.riksSe.login;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class LoginContract {

    interface View extends BaseView<Presenter> {

        void setLogin(String message,Boolean loginStatus,Boolean isDeveloper,int userRoleId);
        void setLoginFailed(String message);



    }

    interface Presenter extends BasePresenter {

        void performLogin(Context context, String username, String userpass, String DeviceModel, String DeviceSDK, String ipAddress, String LoginTime, Boolean status);



        String GetUserLocalWithoutAPI(String Email, String Pass);

    }

}
