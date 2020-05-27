package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;

public class VideoPlayContract {

    interface View extends BaseView<VideoPlayPresenter>
    {



    }
    interface Presenter extends BasePresenter {

        public T_WeekDB getTraingRelatedVideData(Context context, int weekID);

    }



}
