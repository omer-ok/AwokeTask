package com.kampen.riksSe.leader.activity.fragments.home.traings;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.WeekNutritionModel;

import java.util.List;

public class Plans_Frag_Contract {



    interface View extends BaseView<Presenter> {


        void setTrainings(List<WeekNutritionModel> list);
        void setNutritions(List<WeekTrainingModel> list);

        void setStartUpData(String message);
        void setStartUpDataFailed(String message);

    }

    interface Presenter extends BasePresenter {

        void getTrainings();
        void getNutritions();
        void getStartUpData();
    }



}
