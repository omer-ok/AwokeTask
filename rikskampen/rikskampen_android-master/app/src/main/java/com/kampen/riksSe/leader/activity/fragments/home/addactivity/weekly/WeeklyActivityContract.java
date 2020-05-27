package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class WeeklyActivityContract {

    interface View extends BaseView<Presenter> {


        void setActivitiesData();

        void setStartUpData(String message);
        void setStartUpDataFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void getActivitiesData();



    }


}
