package com.kampen.riksSe.signup;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class SignupContract {

    interface View extends BaseView<Presenter> {

        void setSignup(String message);
        void setSignupFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void performSign_up(String f_name, String l_name, String user_pass,String email,String gender,String dob,String hight,String weight);

        String performOFflineSighUp(String fName, String lName, String Email, String Pass);

    }

}
