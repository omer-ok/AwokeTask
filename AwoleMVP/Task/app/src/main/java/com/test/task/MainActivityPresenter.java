package com.test.task;

import android.annotation.SuppressLint;
import android.content.Context;


import androidx.annotation.NonNull;

import com.facebook.stetho.server.http.HttpStatus;

import com.test.task.Model.ResponseStatus;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;


public class MainActivityPresenter implements MainActivityContract.Presenter {


    @NonNull
    private final MainActivityContract.View mMainView;




    public MainActivityPresenter(@NonNull MainActivityContract.View flashView) {
        mMainView = checkNotNull(flashView);

    }

    @Override
    public void getAllFlashDataFromServer(Context context) {


        Repository.getFlashAllData( new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if (mMainView != null && status.getCode() == HttpStatus.HTTP_OK && status.getStatus().equals("OK")) {
                    mMainView.setAllFlashDataSucess(status.getMsg(),status.getFlash());
                } else {
                    mMainView.setAllFlashDataFailed(status.getMsg());
                }

            }
        });

    }

    @Override
    public void getAllHomeDataFromServer(Context context, String PageNo) {
        Repository.getHomeAllData(PageNo, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {

                if (mMainView != null && status.getCode() == HttpStatus.HTTP_OK && status.getStatus().equals("OK")) {
                    mMainView.setAllHomeDataSucess(status.getMsg(),status.getFlash());
                } else {
                    mMainView.setAllHomeDataFailed(status.getMsg());
                }

            }
        });

    }


}


