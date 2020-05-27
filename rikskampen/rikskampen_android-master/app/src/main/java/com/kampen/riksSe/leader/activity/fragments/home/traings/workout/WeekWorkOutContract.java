package com.kampen.riksSe.leader.activity.fragments.home.traings.workout;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class WeekWorkOutContract {

    interface View extends BaseView<WeekWorkOutPresenter>
    {

        void setStartUpData(String message);
        void setStartUpDataFailed(String message);

    }
    interface Presenter extends BasePresenter {


        void getStartUpData(Context context);
    }


}
