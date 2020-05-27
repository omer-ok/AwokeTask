package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class DailyActivityContract {

    interface View extends BaseView<Presenter> {




        void setDailyActivity(String message);
        void setDailyActivityFailed(String message);

    }




    interface Presenter extends BasePresenter {



    }

}
