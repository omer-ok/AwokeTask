package com.kampen.riksSe.leader.activity.fragments.home.nutrition;

import android.content.Context;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;

import java.util.List;

public class WeekNutritionContract {



    interface View extends BaseView<Presenter> {


        void setNutritions(List<WeekTrainingModel> list);

        void setStartUpData(String message);
        void setStartUpDataFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void getNutritions();

        void getAllStartUpData(Context context);

    }



}
