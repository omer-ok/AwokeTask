package com.kampen.riksSe.leader.activity.fragments.account.editprofile;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class EditProfileContract {

    interface View extends BaseView<Presenter> {

        void setEditProfile(String message);
        void setEditProfileFailed(String message);

        void UpdateEditProfileSucess(String message);
        void UpdateEditProfileFailed(String message);


    }

    interface Presenter extends BasePresenter {

        void performEditProfile(String f_name, String l_name, String user_pass, String email);
        void updateProfile(String userID,String token);
    }

}
