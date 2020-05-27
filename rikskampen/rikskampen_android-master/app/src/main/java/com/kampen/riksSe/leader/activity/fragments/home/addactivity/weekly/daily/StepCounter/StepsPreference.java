package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily.StepCounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.kampen.riksSe.R;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.kampen.riksSe.utils.Constants.POWERMANAGER_INTENTS;

public class StepsPreference extends AppCompatActivity {

    View mStepPermission;
    View mPowerManagePermission;
    Context mContext;
    Switch bightnessOnOff,mGoogleFitSwitch;

    private FitnessOptions fitnessOptions;
    OnDataPointListener mListener;

    private static final String TAG = "GoogleFitServices";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_preference);
        mContext = StepsPreference.this;

        mStepPermission = findViewById(R.id.StepPermission);

        bightnessOnOff = (Switch) findViewById(R.id.simpleSwitch);

        mGoogleFitSwitch = (Switch) findViewById(R.id.googleSwitch);
        
        //mGoogleFitSwitch.setChecked(SaveSharedPreference.getGoogleFitStatus(mContext));
        fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
                .build();
        if (oAuthPermissionsApproved()) {
            mGoogleFitSwitch.setChecked(true);
        }else{
            mGoogleFitSwitch.setChecked(false);
        }

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String manufacturer = Build.MANUFACTURER;
            if (!Settings.canDrawOverlays(this) || manufacturer.toUpperCase().equals("HUAWEI")) {
                mStepPermission.setVisibility(View.VISIBLE);
            } else {
                mStepPermission.setVisibility(View.GONE);
            }
        }*/

        mStepPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogeBoxStepPermision(mContext);
            }
        });


        bightnessOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position

                if(bightnessOnOff.isChecked()){
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    /*PowerManager powerManager = (PowerManager) mContext.getSystemService(POWER_SERVICE);
                    PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
                    wakeLock.acquire(10*60*1000L);*/
                    Toast.makeText(mContext, "Screen ON", Toast.LENGTH_SHORT).show();
                }else{
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    Toast.makeText(mContext, "Screen OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mGoogleFitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position

                if(mGoogleFitSwitch.isChecked()){

                    fitSignIn();
                    SaveSharedPreference.setGoogleFitStatus(mContext,true);
                }else{
                    SaveSharedPreference.setGoogleFitStatus(mContext,false);
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    public void onBackClick(View view) {

        finish();

    }

    public boolean DialogeBoxStepPermision(Context context){

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.step_permision_dialoge, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(promptsView);

        final View mDrawOverAppsPermission =  promptsView.findViewById(R.id.drawOverAppsPermission);
        final View mPowerManagePermission = promptsView.findViewById(R.id.powerManagePermission);
        final View mPowerManageHuaweiSteps = promptsView.findViewById(R.id.huawaiPowerDes);
        final View mBatteryOptimization = promptsView.findViewById(R.id.batteryOptimizationAppsPermission);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String manufacturer = Build.MANUFACTURER;
            if (manufacturer.toUpperCase().equals("HUAWEI")) {
                mPowerManageHuaweiSteps.setVisibility(View.VISIBLE);
            } else {
                mPowerManageHuaweiSteps.setVisibility(View.GONE);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(this)){
                SaveSharedPreference.setDrawOverAppsPermission(StepsPreference.this,false);
                mDrawOverAppsPermission.setVisibility(View.VISIBLE);
            }else{
                SaveSharedPreference.setDrawOverAppsPermission(StepsPreference.this,true);
                mDrawOverAppsPermission.setVisibility(View.GONE);
            }
        }

        mDrawOverAppsPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder

                try{
                    Intent intent = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                    }
                    startActivityForResult(intent, 0);
                }catch (Exception e){

                }
            }
        });



        String manufacturer = Build.MANUFACTURER;

        /*if(manufacturer.toUpperCase().equals("HUAWEI")){
            mPowerManagePermission.setVisibility(View.VISIBLE);
        }else{
            mPowerManagePermission.setVisibility(View.GONE);
        }

        if(manufacturer.toUpperCase().equals("SAMSUNG")){
            mBatteryOptimization.setVisibility(View.VISIBLE);
        }else{
            mBatteryOptimization.setVisibility(View.GONE);
        }*/


        mPowerManagePermission.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                try{
                    for (Intent intent : POWERMANAGER_INTENTS)
                        if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                            startActivity(intent);
                            break;
                        }
                    Toast.makeText(context, getResources().getString(R.string.General_Step_Counting_Toast), Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                    Toast.makeText(context, getResources().getString(R.string.General_Step_Counting_Toast), Toast.LENGTH_LONG).show();
                }
            }
        });


        /*mBatteryOptimization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder

                try{

                    String manufacturer = Build.MANUFACTURER;
                    if (manufacturer.toUpperCase().equals("SAMSUNG")) {
                        for (Intent intent : POWERMANAGER_INTENTS)
                            if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                                startActivity(intent);
                                break;
                            }
                    }
                }catch (Exception e){
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                }
            }
        });*/

        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            subscribe();
            ReadStepsData();
            // findFitnessDataSources();
        }
    }

    public void fitSignIn(){
        if (oAuthPermissionsApproved()) {
            subscribe();
            ReadStepsData();
            //findFitnessDataSources();
        }else{
            GoogleSignIn.requestPermissions(this,1,getGoogleAccount(), fitnessOptions);
        }
    }

    public boolean oAuthPermissionsApproved(){

        return GoogleSignIn.hasPermissions(getGoogleAccount(), fitnessOptions);
    }

    public GoogleSignInAccount getGoogleAccount(){
        return GoogleSignIn.getAccountForExtension(mContext, fitnessOptions);
    }
    public void subscribe(){

        Fitness.getRecordingClient(mContext, getGoogleAccount())
                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("Subscribtion","Sucess");
                        Toast.makeText(mContext, "Rikskampen Subscribed with Google Fit", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Subscribtion","Failed");
                        SaveSharedPreference.setGoogleFitStatus(mContext,false);
                        Toast.makeText(mContext, "Google Fit Failed", Toast.LENGTH_SHORT).show();
                        mGoogleFitSwitch.setChecked(false);
                    }
                });
    }

    public void ReadStepsData(){
        Fitness.getHistoryClient(mContext, getGoogleAccount())
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(new OnSuccessListener<DataSet>() {
                    @Override
                    public void onSuccess(DataSet dataSet) {
                        int totalSteps = 0;
                        if(!dataSet.isEmpty()){
                            totalSteps= dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                        }
                        Toast.makeText(mContext, "Google Fit Synced with Rikskampen", Toast.LENGTH_SHORT).show();
                        SaveSharedPreference.setGoogleFitTodayStepsSteps(mContext,totalSteps);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("GoogleFitSteps", e.toString());
                        Toast.makeText(mContext, "Google Fit Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void findFitnessDataSources(){
        // Note: Fitness.SensorsApi.findDataSources() requires the ACCESS_FINE_LOCATION permission.
        Fitness.getSensorsClient(mContext, GoogleSignIn.getLastSignedInAccount(mContext))
                .findDataSources(
                        new DataSourcesRequest.Builder()
                                .setDataTypes(DataType.TYPE_STEP_COUNT_DELTA)
                                .setDataSourceTypes(DataSource.TYPE_RAW)
                                .build())
                .addOnSuccessListener(
                        new OnSuccessListener<List<DataSource>>() {
                            @Override
                            public void onSuccess(List<DataSource> dataSources) {
                                for (DataSource dataSource : dataSources) {
                                    Log.i(TAG, "Data source found: " + dataSource.toString());
                                    Log.i(TAG, "Data Source type: " + dataSource.getDataType().getName());

                                    // Let's register a listener to receive Activity data!
                                    if (dataSource.getDataType().equals(DataType.TYPE_STEP_COUNT_DELTA)
                                            && mListener == null) {
                                        Log.i(TAG, "Data source for TYPE_STEP_COUNT_DELTA found!  Registering.");
                                        registerFitnessDataListener(dataSource, DataType.TYPE_STEP_COUNT_DELTA);
                                    }
                                }
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "failed", e);
                            }
                        });
    }


    public void registerFitnessDataListener(DataSource dataSource, DataType dataType){
        mListener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                for (Field field : dataPoint.getDataType().getFields()) {
                    Value val = dataPoint.getValue(field);
                    Log.i(TAG, "Detected DataPoint field: " + field.getName());
                    Log.i(TAG, "Detected DataPoint value: " + val);
                }
            }
        };

        Fitness.getSensorsClient(mContext, GoogleSignIn.getLastSignedInAccount(mContext))
                .add(
                        new SensorRequest.Builder()
                                .setDataSource(dataSource) // Optional but recommended for custom data sets.
                                .setDataType(dataType) // Can't be omitted.
                                .setSamplingRate(1, TimeUnit.SECONDS)
                                .build(),
                        mListener)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i(TAG, "Listener registered!");
                                } else {
                                    Log.e(TAG, "Listener not registered.", task.getException());
                                }
                            }
                        });
    }






}
