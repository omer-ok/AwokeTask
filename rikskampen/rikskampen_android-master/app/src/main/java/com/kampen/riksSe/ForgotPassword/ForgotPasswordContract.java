package com.kampen.riksSe.ForgotPassword;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class ForgotPasswordContract {

    interface View extends BaseView<Presenter> {

        void setForgotPassword(String message,String OTP);
        void setForgotPasswordFailed(String message);



    }

    interface Presenter extends BasePresenter {

        void performForgotPassword(String Email);



    }

}
