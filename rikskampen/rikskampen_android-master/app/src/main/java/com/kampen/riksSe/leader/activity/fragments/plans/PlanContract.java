package com.kampen.riksSe.leader.activity.fragments.plans;

import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;

public class PlanContract {

    interface View extends BaseView<Presenter> {

       void setPlanSucessfull(String message);
       void setPlanFailded(String message);

    }

    interface Presenter extends BasePresenter {

        void getAllPlanData();

    }

}
