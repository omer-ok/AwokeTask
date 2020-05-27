package com.kampen.riksSe.login;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.kampen.riksSe.BaseActivity;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.ForgotPassword.ForgotPasswordActivity;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ScheduledLiveVideoCallActivity;
import com.kampen.riksSe.UpdateLoginProfile.updateProfileStartOneActivity;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.plans.IntroActivity;
import com.kampen.riksSe.login.ModelsV3.UserRoles;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginContract.View {


    private EditText mUserEmail;
    private EditText mUserPass;
    private LoginPresenter mPresenter;
    private CheckBox saveLoginCheckBox;
    private Boolean saveLogin;
    Button LoginBtn;
    private long mLastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mUserEmail = findViewById(R.id.editText_email);
        mUserPass = findViewById(R.id.editText_pass);
        LoginBtn = findViewById(R.id.button_login);

        saveLoginCheckBox = (CheckBox)findViewById(R.id.saveLoginCheckBox);

        LoginBtn.setOnClickListener(this);

        mPresenter = new LoginPresenter(LoginActivity.this);

        manageDateChanged();

        saveLogin =SaveSharedPreference.getUserRemmberMe(LoginActivity.this);

        if(saveLogin){

            saveLoginCheckBox.setChecked(true);
            String[] params=SaveSharedPreference.getLoggedParams(getApplicationContext());
            mUserEmail.setText(params[0].trim());
            mUserPass.setText(params[1].trim());
        }
        mLastClickTime = 0;


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //LoginBtn.setOnClickListener(null);
                LoginBtn.setEnabled(false);
                if(mUserEmail.getText().toString().trim().length()>0 && mUserPass.getText().toString().trim().length()>0) {


                    if (validateData()) {

                        mUserEmail.onEditorAction(EditorInfo.IME_ACTION_DONE);
                        mUserPass.onEditorAction(EditorInfo.IME_ACTION_DONE);


                        String tempUserEmail = mUserEmail.getText().toString().trim().toLowerCase();
                        String tempUserPass = mUserPass.getText().toString().trim();
                        try {
                            String myDeviceModel = android.os.Build.MODEL;
                            int myDeviceSDK = android.os.Build.VERSION.SDK_INT;
                            WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
                            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                            String currentDateandTime = sdf.format(new Date());
                            ProgressManager.showProgress(LoginActivity.this, getResources().getString(R.string.progress_dialog_message));
                            mPresenter.performLogin(LoginActivity.this,tempUserEmail.toLowerCase(),tempUserPass,myDeviceModel, String.valueOf(myDeviceSDK),ip,currentDateandTime,false);

                        } catch (NullPointerException ex) {
                            ex.toString();
                        }

                        if (saveLoginCheckBox.isChecked()){

                            SaveSharedPreference.setUserRemmberMe(LoginActivity.this, true);

                        } else {
                            SaveSharedPreference.setUserRemmberMe(LoginActivity.this, false);
                        }
                    }

                }else{
                    LoginBtn.setEnabled(true);
                    mUserEmail.requestFocus();
                    mUserEmail.setError(getResources().getString(R.string.LoginModule_EnterValidEmail));
                    mUserPass.requestFocus();
                    mUserPass.setError(getResources().getString(R.string.LoginModule_EnterValidPassword));
                }
            }
        });

    }



    /*public void onLoginClick(View view) {



    }*/

    public static String GetUserLocalWithoutAPI(String Email, String Pass)
    {

        Realm realm= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        final String EmailDB;
        final String[] PassDB = new String[1];
        final String[] Msg = new String[1];
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = realm.createObject(Realm_User.class);



                if(Email.equals(db_user.getEmail()) && Pass.equals(db_user.getPass())){


                  //  EmailDB = db_user.getEmail();
                    PassDB[0] = db_user.getPass();
                    Msg[0] = "Sucess";

                }
                else{
                    Msg[0] = "Failed";
                }
            }
        });

       // String Message = Msg[0];

        return Msg[0];
    }


    @Override
    public void onBackPressed() {

        finish();

    }

    private boolean validateData() {
        if (mUserEmail.getText().toString().trim().length() == 0) {
            mUserEmail.requestFocus();
            mUserEmail.setError(getResources().getString(R.string.LoginModule_EnterValidEmail));
            LoginBtn.setEnabled(true);
            return false;

        }

        if (!Constants.isValidEmailId(mUserEmail.getText().toString().trim())) {
            mUserEmail.requestFocus();
            mUserEmail.setError(getResources().getString(R.string.LoginModule_EnterValidEmail));
            LoginBtn.setEnabled(true);
            return false;
        }

        if (mUserPass.getText().toString().trim().length() == 0) {
            mUserPass.requestFocus();
            mUserPass.setError(getResources().getString(R.string.LoginModule_EnterValidPassword));
            LoginBtn.setEnabled(true);
            return false;

        }


        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mUserEmail = null;
        mUserPass = null;
    }


    public void onBackClick(View view) {
        finish();
    }

    public void onForgotPasswordClick(View view) {

        moveNext(ForgotPasswordActivity.class);

    }


    @Override
    public void setLogin(String message,Boolean loginStatus,Boolean isDeveloper,int userRoleId) {
        //LoginBtn.setEnabled(true);ProgressManager.hideProgress();

        UserRoles  userRoles = Repository.getUserRole(userRoleId);

        if(userRoles.getName().equals("Trainer")){
            SaveSharedPreference.setLoggedIn(LoginActivity.this,true,mUserEmail.getText().toString().trim().toLowerCase(),mUserPass.getText().toString());
            ProgressManager.hideProgress();
            moveNext(ScheduledLiveVideoCallActivity.class);
        }else if(userRoles.getName().equals("Contestant")){
            Realm_User realm_user = Repository.provideUserLocal(mUserEmail.getText().toString().trim().toLowerCase(),mUserPass.getText().toString());
            String stepsDB = realm_user.getSteps_count();
            if(stepsDB != null){
                SaveSharedPreference.setUsertodaySteps(LoginActivity.this, Math.abs(Integer.parseInt(stepsDB)));
                SaveSharedPreference.setUserLogInSteps(LoginActivity.this, Integer.parseInt(stepsDB));
            }else{
                SaveSharedPreference.setUsertodaySteps(LoginActivity.this, 0);
            }

        SaveSharedPreference.setLoggedInStepsFirstTime(LoginActivity.this,true);

        //hideWaitProgressBar();

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);

        String currentDateandTime = sdf.format(new Date());

        SaveSharedPreference.setUserLogInDate(LoginActivity.this,currentDateandTime);

        SaveSharedPreference.setStepDaySessionDate(LoginActivity.this,currentDateandTime);

        SaveSharedPreference.setLoggedInFirstTime(LoginActivity.this,true);

        SaveSharedPreference.setStepsForeGroundServiceDestroyStatus(LoginActivity.this,false);

        SaveSharedPreference.setGoogleApi(LoginActivity.this,false);

        SaveSharedPreference.setStepsDateTimeToFetechHistoryFromGoogleFit(LoginActivity.this,null);

        SaveSharedPreference.setLoggedIn(LoginActivity.this,true,mUserEmail.getText().toString().trim().toLowerCase(),mUserPass.getText().toString());

        SaveSharedPreference.saveUserToken(LoginActivity.this,message);

        SaveSharedPreference.setLoggedInFCM(LoginActivity.this,true);


        double height = realm_user.getHeight_in_cm();

        double weight = realm_user.getWeight();

        double GoalWeight = realm_user.getGoalweight();

        double waist = realm_user.getWaist();

        String DOB = realm_user.getDob();

        SaveSharedPreference.setLoggedUserTesterStatus(LoginActivity.this,isDeveloper);

            if(loginStatus){
                String myDeviceModel = android.os.Build.MODEL;
                String myDeviceSDK = android.os.Build.VERSION.SDK;
                WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
                SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTime1 = sdf.format(new Date());

                SetDailogeConformationLogin(mUserEmail.getText().toString().trim().toLowerCase(),mUserPass.getText().toString(),myDeviceModel,myDeviceSDK,ip,currentDateandTime1);

            }else if(SaveSharedPreference.getLoggedInSplash(LoginActivity.this)){
                SaveSharedPreference.setLoggedIn(LoginActivity.this,true);
                moveNext(IntroActivity.class);

            }else if(height<=0 || GoalWeight <=0 || weight <=0 || waist<=0||DOB==null){
                Intent intent = new Intent(getApplicationContext(), updateProfileStartOneActivity.class);
                intent.putExtra("ActivityStatus","BMI");
                startActivity(intent);
                finish();

            }else{
                SaveSharedPreference.setLoggedIn(LoginActivity.this,true);
                Intent intent = new Intent(getApplicationContext(), MainLeaderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString(NAVIGATING_FROM_TAG,"abc");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        }
    }


    public void SetDailogeConformationLogin(String email,String pasword,String DeviceModel,String DeviseSdk,String ip,String currentDateandTime){

        LayoutInflater li = LayoutInflater.from(LoginActivity.this);
        View promptsView = li.inflate(R.layout.dialog_box_login, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setView(promptsView);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtnlogin);
        final Button OkBtn = (Button) promptsView.findViewById(R.id.okbtnLogin);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        OkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.performLogin(LoginActivity.this,email.toLowerCase(), pasword,DeviceModel,DeviseSdk,ip,currentDateandTime,true);
                alertDialog.dismiss();
            }
        });


        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder
                ProgressManager.hideProgress();
                alertDialog.dismiss();
                Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        LoginBtn.setEnabled(true);
                        mLocalService.deleteAll();

                    }
                });

            }
        });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

    }


    @Override
    public void setLoginFailed(String message) {
        LoginBtn.setEnabled(true);
        //hideWaitProgressBar();
        //LoginBtn.setOnClickListener(this);
        ProgressManager.hideProgress();
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {

            MyApplication.showSimpleSnackBar(LoginActivity.this, getResources().getString(R.string.General_NoInternetConnection));
        }
        else{
            MyApplication.showSimpleSnackBar(LoginActivity.this, message);
        }

    }






    /*
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/


    public void manageDateChanged(){
        final PendingIntent mDateChangeSender;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(Intent.ACTION_DATE_CHANGED);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        mDateChangeSender = PendingIntent.getBroadcast(LoginActivity.this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), mDateChangeSender);

    }



    public void moveNext(Class value) {
        Intent intent = new Intent(getApplicationContext(), value);

        Bundle bundle=new Bundle();
        bundle.putString(NAVIGATING_FROM_TAG,"abc");
        bundle.putInt("selected_week",0);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(LoginContract.Presenter mPresenter) {


    }

    @Override
    public void onClick(View v) {
        // Preventing multiple clicks, using threshold of 1 second
        if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        //pressedOnClick(v);

    }

}
