package com.kampen.riksSe.UpdateLoginProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.account.editprofile.EditPofileSimpleActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter.StepCountingService;
import com.kampen.riksSe.leader.activity.fragments.plans.IntroActivity;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import io.realm.Realm;

import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;

public class updateProfileStartTwoActivity extends AppCompatActivity implements UpdateLoginProfileContract.View{


    private UpdateLoginProfilePresenter updateLoginProfilePresenter;
    EditText mHeight,mWeight,mWaist,mGoalWeight;
    String DOB,Gender;
    Realm_User mUser;
    TextView mScreenViewOne,mScreenViewTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_start_two);

        updateLoginProfilePresenter = new UpdateLoginProfilePresenter(updateProfileStartTwoActivity.this);
        mUser = updateLoginProfilePresenter.provideUserLocal(updateProfileStartTwoActivity.this);

        DOB =getIntent().getStringExtra("DOB");
        Gender = getIntent().getStringExtra("Gender");

        mHeight = findViewById(R.id.hightValuetxt);
        mWeight = findViewById(R.id.weightValuetxt);
        mWaist = findViewById(R.id.waistValuetxt);
        mGoalWeight = findViewById(R.id.goalWeightValuetxt);
        mScreenViewOne = findViewById(R.id.screenViewOne);
        mScreenViewTwo = findViewById(R.id.screenViewTwo);

        mScreenViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Realm_User  user=provideUserLocal(updateProfileStartTwoActivity.this);

        if(user!=null){
            if(user.getHeight_in_cm()>0){
                mHeight.setText(user.getHeight_in_cm()+"");
            }
            if(user.getWeight()>0){
                mWeight.setText(user.getWeight()+"");
            }
            if(user.getWaist()>0){
                mWaist.setText(user.getWaist()+"");
            }
            if(user.getGoalweight()>0){
                mGoalWeight.setText(user.getGoalweight()+"");
            }
        }


    }

    public void onFinishClick(View view){

        String heightStr = mHeight.getText().toString();
        String weightStr = mWeight.getText().toString();
        String waistStr = mWaist.getText().toString();
        String GoalWeightStr = mGoalWeight.getText().toString();
        if(heightStr.isEmpty()){
            heightStr ="0";
        }
        if(weightStr.isEmpty()){
            weightStr = "0";
        }
        if(waistStr.isEmpty()){
            waistStr = "0";
        }
        if(GoalWeightStr.isEmpty()){
            GoalWeightStr = "0";
        }
        double Height = Double.parseDouble(heightStr);
        double Weight = Double.parseDouble(weightStr);
        double Waist = Double.parseDouble(waistStr);
        double GoalWeight = Double.parseDouble(GoalWeightStr);

        if(DOB.isEmpty()){
            MyApplication.showSimpleSnackBar(updateProfileStartTwoActivity.this,getResources().getString(R.string.UpdateProfile_EnterValidDOB));
            return;
        }
        if(Gender.isEmpty()){
            MyApplication.showSimpleSnackBar(updateProfileStartTwoActivity.this,getResources().getString(R.string.UpdateProfile_EnterValidGender));
            return;
        }

        if(Height == 0|| Height < 95 || Height > 250)
        {
            mHeight.setError(getResources().getString(R.string.UpdateProfile_EnterValidHeight));
            mHeight.requestFocus();
            return;
        }
        if (Weight == 0 || Weight < 20 || Weight > 200) {

            mWeight.setError(getResources().getString(R.string.UpdateProfile_EnterValidWeight));
            mWeight.requestFocus();
            return;
        }
        if(Waist==0 ||Waist < 38 || Waist >200) {

            mWaist.setError(getResources().getString(R.string.UpdateProfile_EnterValidWaist));
            mWaist.requestFocus();
            return;
        }
        if (GoalWeight == 0 || GoalWeight < 20 || GoalWeight > 200) {

            mGoalWeight.setError(getResources().getString(R.string.UpdateProfile_EnterValidGoalWeight));
            mGoalWeight.requestFocus();
            return;
        }
        ProgressManager.showProgress(updateProfileStartTwoActivity.this,getResources().getString(R.string.progress_dialog_message));
        updateLoginProfilePresenter.UpdateLoginProfile(mUser,DOB,Gender,mHeight.getText().toString(),mWeight.getText().toString(),mWaist.getText().toString(),mGoalWeight.getText().toString());

    }

    @Override
    public void UpdateLoginProfileSucess(String message) {
        ProgressManager.hideProgress();
        Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(NAVIGATING_FROM_TAG,"abc");
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void UpdateLoginProfileFailed(String message) {
        ProgressManager.hideProgress();

        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(updateProfileStartTwoActivity.this, getResources().getString(R.string.General_NoInternetConnection));
        }
        else if(message.equals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 118 path $.result.Home.Activities.weeklyActivities")){

        }
        else if(message.equals("Unauthorized")){

            LogOutUnautorizedUser();
        }
        else{
            MyApplication.showSimpleSnackBar(updateProfileStartTwoActivity.this,message);
        }
    }

    @Override
    public void setPresenter(UpdateLoginProfileContract.Presenter mPresenter) {

    }



    private void LogOutUnautorizedUser(){

        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mLocalService.deleteAll();

            }
        });
        if (Constants.isMyServiceRunning(StepCountingService.class, updateProfileStartTwoActivity.this)) {
            ActivityFragment.newInstance().stopGPSService(updateProfileStartTwoActivity.this);
        }
        Toast.makeText(updateProfileStartTwoActivity.this,  getResources().getString(R.string.LoginModule_Session_Timed_Out), Toast.LENGTH_SHORT).show();
        //MyApplication.showSimpleSnackBar(updateProfileStartTwoActivity.this, "Your Logged in Some where else");
        Intent intent = new Intent(updateProfileStartTwoActivity.this, LoginActivity.class);
        startActivity(intent);

        SaveSharedPreference.setLoggedIn(updateProfileStartTwoActivity.this, false);

        finish();
/*
        LayoutInflater li = LayoutInflater.from(updateProfileStartTwoActivity.this);

        View promptsView = li.inflate(R.layout.dialog_box_logout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(updateProfileStartTwoActivity.this);

        builder.setView(promptsView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {


                        mLocalService.deleteAll();

                    }
                });

                MyApplication.showSimpleSnackBar(updateProfileStartTwoActivity.this, "Your Logged in Some where else");
                Intent intent = new Intent(updateProfileStartTwoActivity.this, LoginActivity.class);
                startActivity(intent);

                SaveSharedPreference.setLoggedIn(updateProfileStartTwoActivity.this, false);

                finish();


            }
        });

        builder.show();
*/

    }


    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

}
