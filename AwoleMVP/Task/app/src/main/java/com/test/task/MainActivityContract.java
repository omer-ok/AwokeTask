package com.test.task;

import android.content.Context;

import com.test.task.Model.Flash;
import com.test.task.PresenterLayer.BasePresenter;
import com.test.task.PresenterLayer.BaseView;


public class MainActivityContract {

    interface View extends BaseView<Presenter> {

        void setAllFlashDataSucess(String message, Flash flash);
        void setAllFlashDataFailed(String message);

        void setAllHomeDataSucess(String message, Flash flash);
        void setAllHomeDataFailed(String message);
    }

    interface Presenter extends BasePresenter {

        void getAllFlashDataFromServer(Context context);
        void getAllHomeDataFromServer(Context context,String PageNo);


    }

}
