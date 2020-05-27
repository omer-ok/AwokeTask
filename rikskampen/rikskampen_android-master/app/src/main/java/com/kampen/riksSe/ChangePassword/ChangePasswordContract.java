package com.kampen.riksSe.ChangePassword;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class ChangePasswordContract {

    interface View extends BaseView<Presenter> {

        void setChangePaswordSucess(String message);
        void setChangePasswordFailed(String message);



    }

    interface Presenter extends BasePresenter {

        void performChangePasword(String Email,String Password);



    }

}
