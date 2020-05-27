package com.kampen.riksSe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import static com.kampen.riksSe.BaseActivity.ApplicationState.OnCreate_ENUM;
import static com.kampen.riksSe.BaseActivity.ApplicationState.OnDestroy_ENUM;
import static com.kampen.riksSe.BaseActivity.ApplicationState.OnPause_ENUM;
import static com.kampen.riksSe.BaseActivity.ApplicationState.OnResume_ENUM;
import static com.kampen.riksSe.BaseActivity.ApplicationState.OnStop_ENUM;

public class BaseActivity extends AppCompatActivity {


    public  enum ApplicationState{

        OnCreate_ENUM,
        OnResume_ENUM,
        OnPause_ENUM,
        OnStop_ENUM,
        OnDestroy_ENUM;

        public static ApplicationState toEnum (String myEnumString) {
            try {
                return valueOf(myEnumString);
            } catch (Exception ex) {
                // For error cases
                return OnCreate_ENUM;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SaveSharedPreference.setApplicationState(BaseActivity.this, OnCreate_ENUM);

    }


    @Override
    protected void onResume() {
        super.onResume();

        SaveSharedPreference.setApplicationState(BaseActivity.this, OnResume_ENUM);
    }


    @Override
    protected void onPause() {
        super.onPause();


        SaveSharedPreference.setApplicationState(BaseActivity.this, OnPause_ENUM);

    }


    @Override
    protected void onStop() {
        super.onStop();

        SaveSharedPreference.setApplicationState(BaseActivity.this, OnStop_ENUM);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        SaveSharedPreference.setApplicationState(BaseActivity.this, OnDestroy_ENUM);
    }


    public   ApplicationState  getAppSate()
    {
        return  SaveSharedPreference.getApplicationState(BaseActivity.this);
    }


    public  void showWaitProgressBar(String message)
    {

        try {
            ProgressManager.showProgress(BaseActivity.this, message);
        }catch (Exception ex)
        {
            ex.toString();
        }

    }


    public   void  hideWaitProgressBar()
    {
        try {
        ProgressManager.hideProgress();
        }catch (Exception ex)
        {
            ex.toString();
        }
    }

}
