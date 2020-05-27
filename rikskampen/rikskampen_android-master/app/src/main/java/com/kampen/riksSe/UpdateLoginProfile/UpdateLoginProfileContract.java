package com.kampen.riksSe.UpdateLoginProfile;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.user.model.Realm_User;

public class UpdateLoginProfileContract {

    interface View extends BaseView<Presenter> {



       void UpdateLoginProfileSucess(String message);
        void UpdateLoginProfileFailed(String message);

    }

    interface Presenter extends BasePresenter {


        void UpdateLoginProfile(Realm_User mUser,String DOB,String Gender, String Height, String weight, String Wasit,String GoalWeight);

    }

}
