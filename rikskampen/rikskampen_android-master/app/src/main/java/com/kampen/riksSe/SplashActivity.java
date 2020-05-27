package com.kampen.riksSe;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ScheduledLiveVideoCallActivity;
import com.kampen.riksSe.UpdateLoginProfile.updateProfileStartOneActivity;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.chat.ChatActivity;
import com.kampen.riksSe.leader.activity.fragments.map.tracking.GPS_Service;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.login.ModelsV3.UserRoles;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_VALUE;

public class SplashActivity extends AppCompatActivity {

    private Realm mRealm;
    public  static Context contextsplash;

/*
* BACKGROUND NOTIFICATIONS WILL BE HANDLED FROM THIS METHOD
* */
    private void logIntent(Intent intent)
    {
        Log.d("SPLASH_SCREEN", intent.toString());
        Bundle extrasBundle = intent.getExtras();

        if (null != extrasBundle) {
            if(intent.hasExtra("chat") == true){

               // SaveSharedPreference.setChatNotificationCounter(this, Integer.parseInt(intent.getStringExtra("badge")));
                intent = new Intent(SplashActivity.this, ChatActivity.class);
                startActivity(intent);
                finish();
            }else if(intent.hasExtra("workout") == true){
                int weekID = 0;
                String nutrition = intent.getStringExtra("week");
                if(nutrition== null){
                    weekID =1;
                }else{
                    weekID = Integer.parseInt(nutrition);
                }
                intent = new Intent(SplashActivity.this, MainLeaderActivity.class);
                intent.putExtra("From", "workout");
                intent.putExtra("selected_week", weekID);
                startActivity(intent);
                finish();
            }else if(intent.hasExtra("nutrition") == true){
                int weekID = 0;
                String nutrition = intent.getStringExtra("week");
                if(nutrition== null){
                    weekID =1;
                }else{
                    weekID = Integer.parseInt(nutrition);
                }
                intent = new Intent(SplashActivity.this, MainLeaderActivity.class);
                intent.putExtra("From", "nutrition");
                intent.putExtra("selected_week", weekID);
                startActivity(intent);
                finish();
            }else{
                startMainActivity();
            }
        }else{
            startMainActivity();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            contextsplash=SplashActivity.this;

           if(SaveSharedPreference.getLoggedStatus(getApplicationContext()))
            {
                setUpDB();

                String[] params=SaveSharedPreference.getLoggedParams(getApplicationContext());

                final RealmResults<Realm_User> user = mRealm.where(Realm_User.class)
                        .equalTo(Constants.USER_EMAIL_TAG,params[0].trim())
                        .and()
                        .equalTo(Constants.USER_PASS_TAG,params[1].trim())
                        .findAll();


                if(user.size()>0) {
                    UserRoles userRoles = Repository.getUserRole(user.get(0).getUserRoleID());
                    if(userRoles!=null){
                        if(userRoles.getName().equals("Trainer")){
                            Intent intent = new Intent(getApplicationContext(), ScheduledLiveVideoCallActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(userRoles.getName().equals("Contestant")){

                            double height = user.get(0).getHeight_in_cm();
                            double weight = user.get(0).getWeight();
                            double waist = user.get(0).getWaist();
                            if(height<=0 || weight <=0 || waist<=0){
                                Intent intent = new Intent(getApplicationContext(), updateProfileStartOneActivity.class);
                                intent.putExtra("ActivityStatus","BMI");
                                startActivity(intent);
                                finish();
                            }else{
                                startMainActivity();
                            }
                        }
                    }else{
                        double height = user.get(0).getHeight_in_cm();
                        double weight = user.get(0).getWeight();
                        double waist = user.get(0).getWaist();
                        if(height<=0 || weight <=0 || waist<=0){
                            Intent intent = new Intent(getApplicationContext(), updateProfileStartOneActivity.class);
                            intent.putExtra("ActivityStatus","BMI");
                            startActivity(intent);
                            finish();
                        }else{
                            startMainActivity();
                        }
                    }
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

        }
        else
        {
            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
            finish();
        }



    }


    private void startMainActivity(){
        if (Constants.isMyServiceRunning(GPS_Service.class, SplashActivity.this)) {
            Intent intent = new Intent(SplashActivity.this, MainLeaderActivity.class);
            //SaveSharedPreference.setUserStepsUpdateActivity(SplashActivity.this,true);
            Bundle bundle = new Bundle();
            bundle.putString(NAVIGATING_FROM_TAG, NAVIGATING_FROM_VALUE);
            bundle.putInt("selected_week", 0);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(SplashActivity.this, MainLeaderActivity.class);
            //SaveSharedPreference.setUserStepsUpdateActivity(SplashActivity.this,true);
            Bundle bundle = new Bundle();
            bundle.putString(NAVIGATING_FROM_TAG, "abc");
            bundle.putInt("selected_week", 0);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

    }
    private void  setUpDB()
    {
        try {
            mRealm = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        }catch (Exception e){

        }

    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
       // Toast.makeText(SplashActivity.this, "Notification Now on PostResume Splash", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
       //logIntent(getIntent());
        super.onResume();
    }


}
