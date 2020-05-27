package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.POWER_SERVICE;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class StepsProvider  implements SensorEventListener {


    private  Context mContext;

    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    private Sensor stepDetectorSensor;
    private int milestoneStep;



    private  StepsResult  stepsResult;

    private  int countSteps=0;

    public  StepsProvider(Context context)
    {
        mContext=context;
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
       // stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        sensorManager.registerListener(StepsProvider.this, stepCounterSensor,  0);
        sensorManager.registerListener(StepsProvider.this, stepDetectorSensor, 0);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

          countSteps = (int) event.values[0];

        }

        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {

           countSteps = (int) event.values[0];

        }

//Toast.makeText(mContext, "Step Counter Lives", Toast.LENGTH_SHORT).show();
       /* String [] params= SaveSharedPreference.getLoggedParams(mContext);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        if(user!=null){
            Competition CompitionDate = Repository.getCompitionDate(user.getContestID());
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());

            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDateandTime1 = sdf1.format(new Date());

            if(CompitionDate!=null){
                if(CompitionDate.getStartDate()!=null && CompitionDate.getmEndDate()!=null){
                    Boolean ContestStatus = getCompitionStartDate(mContext,CompitionDate.getStartDate(),currentDateandTime);
                    Boolean ContestEndStatus = getCompitionStartDate(mContext,CompitionDate.getmEndDate(),currentDateandTime);
                    if(ContestStatus && !ContestEndStatus){

                        if(SaveSharedPreference.getLoggedInFirstTime(mContext)){
                            SaveSharedPreference.setSensorSteps(mContext,countSteps);
                            Intent intent = new Intent();
                            intent.putExtra("StepsSensor",countSteps);
                            intent.setAction("com.Rikskampen.CUSTOM_INTENT_STEPSSENSOR");
                            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                        }else{

                        }

                    }else if(ContestEndStatus){


                    }else if(!ContestStatus){


                    }
                }else{


                }
            }else{


            }
        }
*/

        if(stepsResult!=null)
        {
            /*PowerManager powerManager = (PowerManager) mContext.getSystemService(POWER_SERVICE);
            PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
            wakeLock.acquire(10*60*1000L);*/
            stepsResult.onSteps(countSteps);
            //wakeLock.release();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void setStepListener(StepsResult  listener)
    {

        stepsResult=listener;
    }



    public  interface  StepsResult
    {
        public  void  onSteps(int steps);
    }
}
