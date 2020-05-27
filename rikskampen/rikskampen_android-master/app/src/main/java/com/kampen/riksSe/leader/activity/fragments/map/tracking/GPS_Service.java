package com.kampen.riksSe.leader.activity.fragments.map.tracking;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.R;
import com.kampen.riksSe.SplashActivity;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.RouteAndStarDataModel;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;


import java.util.List;

import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_TAG;
import static com.kampen.riksSe.utils.Constants.NAVIGATING_FROM_VALUE;

public class GPS_Service  extends Service {


    Context c = this;

    private  int mNotificationId = 002;
    NotificationManager notificationManager;
    NotificationClass nc = new NotificationClass();

    private SimpleLocation locationProvider;

    private static int testValue=10000;
    private static int defaultValue=25;

    public static final int START_PICKUP_RADIUS_IN_METRES=defaultValue;

    private LocationResultUpdate  locationResultUpdate;

    private  LatLng starPositionOnMap =new LatLng(0,0);

    private RouteAndStarDataModel routeAndStarDataModel;

    private  int lastStarPickedNumber=0;

    private  boolean isAllStarsPicked=false;

    private final IBinder mBinder = new LocalBinder();

    public int getLastStarPickedNumber() {
        return lastStarPickedNumber+1;
    }

    public int getLastStarPickedNumberActual() {
        return lastStarPickedNumber;
    }

    public boolean isAllStarsPicked() {
        return isAllStarsPicked;
    }


    public RouteAndStarDataModel getRouteAndStarDataModel() {

        return routeAndStarDataModel;

    }

    public void setRouteAndStarDataModel(RouteAndStarDataModel routeAndStarDataModel) {

        this.routeAndStarDataModel = routeAndStarDataModel;
    }


    public class LocalBinder extends Binder {
        public   GPS_Service getService() {
            // Return this instance of LocalService so clients can call public methods
            return GPS_Service.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



    @Override
    public void onCreate() {
        super.onCreate();

        ForegroundServiceInitialize();
        //ForegroundServiceInitialize1();
        provideLocationServices();

    }


    private void provideLocationServices()
    {
        try {
            boolean requireFineGranularity = true;
            boolean passiveMode = true;
            long updateIntervalInMilliseconds = 10 * 60 * 1000;
            boolean requireNewLocation = true;
            locationProvider = new SimpleLocation(GPS_Service.this, requireFineGranularity, passiveMode, updateIntervalInMilliseconds, requireNewLocation);
        } catch (Exception ex) {
            ex.toString();
        }

        locationProvider.setListener(new SimpleLocation.Listener() {

            public void onPositionChanged() {

                try {

                    if (routeAndStarDataModel.getStarPositions().size() > 0) {
                        starPositionOnMap = routeAndStarDataModel.getStarPositions().get(0);
                        onPositionChangeEvent();
                    } else {
                    /*if (locationResultUpdate != null) {
                        locationResultUpdate.allStarsPicked();
                    }*/
                    }
                }catch (Exception ex)
                {

                }

            }

            @Override
            public void onGPSStatusChanged(boolean status) {

                if (status) {
                    if(locationProvider!=null)
                    {
                        locationProvider.endUpdates();
                    }
                    provideLocationServices();
                }

            }

        });
    }


    private void onPositionChangeEvent()
    {

        LatLng pickedStarLocation=null;

        if (locationResultUpdate != null) {
            if (Constants.meterDistanceBetweenPoints(starPositionOnMap.latitude, starPositionOnMap.longitude, locationProvider.getLatitude(), locationProvider.getLongitude()) <= START_PICKUP_RADIUS_IN_METRES) {
            //if (Constants.meterDistanceBetweenPoints(starPositionOnMap.latitude, starPositionOnMap.longitude, 58.657869, 14.958790) <= START_PICKUP_RADIUS_IN_METRES) {

                if(routeAndStarDataModel.getStarPositions().size()>0) {
                    pickedStarLocation = routeAndStarDataModel.getStarPositions().remove(0);

                    if(routeAndStarDataModel.getStarPositions().size()>0)
                    pickedStarLocation= routeAndStarDataModel.getStarPositions().get(0);
                    lastStarPickedNumber+=1;
                }

                if(lastStarPickedNumber>=routeAndStarDataModel.getTotalStars())
                {
                    locationResultUpdate.allStarsPicked(pickedStarLocation);
                    isAllStarsPicked=true;
                    try {
                        locationProvider.endUpdates();
                    }catch (Exception ex){}
                }
                else
                {
                    locationResultUpdate.onStarPickedNew(new LatLng(locationProvider.getLatitude(), locationProvider.getLongitude()),
                            pickedStarLocation ,lastStarPickedNumber,lastStarPickedNumber+1);
                }

            }
            else
            {
                LatLng latLng=new LatLng(locationProvider.getLatitude(), locationProvider.getLongitude());

                if(routeAndStarDataModel!=null)
                {
                    routeAndStarDataModel.getUserChasedRoot().add(latLng);
                }

                locationResultUpdate.onLocationUpdate(latLng,routeAndStarDataModel.getUserChasedRoot());
            }
        }
        else
        {
            //Toast.makeText(GPS_Service.this, "test", Toast.LENGTH_SHORT).show();

            if (Constants.meterDistanceBetweenPoints(starPositionOnMap.latitude, starPositionOnMap.longitude, locationProvider.getLatitude(), locationProvider.getLongitude()) <= START_PICKUP_RADIUS_IN_METRES) {
            //if (Constants.meterDistanceBetweenPoints(starPositionOnMap.latitude, starPositionOnMap.longitude, 58.657869, 14.958790) <= START_PICKUP_RADIUS_IN_METRES) {

                if(routeAndStarDataModel.getStarPositions().size()>0) {
                    routeAndStarDataModel.getStarPositions().remove(0);
                    lastStarPickedNumber+=1;
                }

                if(lastStarPickedNumber>=routeAndStarDataModel.getTotalStars())
                {

                    isAllStarsPicked=true;
                    try {
                        locationProvider.endUpdates();
                    }catch (Exception ex){}
                }

            } else
            {
                LatLng latLng=new LatLng(locationProvider.getLatitude(), locationProvider.getLongitude());

                if(routeAndStarDataModel!=null)
                {
                    routeAndStarDataModel.getUserChasedRoot().add(latLng);
                }
            }
        }

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        if(intent.getAction()==Constants.START_ACTION)
        {
            ForegroundServiceInitialize();
            //ForegroundServiceInitialize1();
        }
        else
        {
            stopForeground(true);
            stopSelf();
            return START_NOT_STICKY;
        }


        locationProvider.beginUpdates();

        isAllStarsPicked=false;

        return START_STICKY;
    }







    @Override
    public void onDestroy() {
        super.onDestroy();


        //Toast.makeText(GPS_Service.this, "Service onDestroy ", Toast.LENGTH_SHORT).show();

        locationProvider.endUpdates();
        locationProvider = null;


        try {
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(mNotificationId);
        }catch (Exception ex )
        {

        }

    }



    public void ForegroundServiceInitialize() {

        Intent notificationIntent = new Intent(GPS_Service.this, SplashActivity.class);


        Bundle bundle=new Bundle();
        bundle.putString(NAVIGATING_FROM_TAG,NAVIGATING_FROM_VALUE);
        notificationIntent.putExtras(bundle);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pIntent = PendingIntent.getActivity(GPS_Service.this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Creating Channel
            nc.createMainNotificationChannel(this);
            //building Notification.
            Notification.Builder notifi = new Notification.Builder(getApplicationContext(), nc.getMainNotificationId());
            notifi.setSmallIcon(R.drawable.ic_app_launch);
            notifi.setContentTitle("Reach to Your Goal");
            notifi.setContentText("gps tracking");
            /*notifi.setContentTitle("STARS CHASING");
            notifi.setContentText("STEPS = "+SaveSharedPreference.getUserJourneySteps(c)+" | "+"DISTANCE = "+SaveSharedPreference.getUserJourneyDistance(c)+" | "+"CALORIES = "+SaveSharedPreference.getUserJourneyCalories(c)+"");
           */ notifi.setContentIntent(pIntent);
            //getting notification object from notification builder.
            Notification n = notifi.build();



            NotificationManager mNotificationManager =
                    (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(mNotificationId, n);

            //  startting foreground
            startForeground(mNotificationId, n);


        } else {
            //for devices less than API Level 26
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("Reach to Your Goal")
                    .setContentText("gps tracking")
                    //.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                    /*.setContentTitle("STARS CHASING")
                    .setContentText("STEPS = "+SaveSharedPreference.getUserJourneySteps(c)+" | "+"DISTANCE = "+SaveSharedPreference.getUserJourneyDistance(c)+" | "+"CALORIES = "+SaveSharedPreference.getUserJourneyCalories(c)+"")
                   */ .setSmallIcon(R.drawable.ic_app_launch)
                    .setContentIntent(pIntent)
                    .setOngoing(true).build();

            startForeground(mNotificationId, notification);
        }
    }


    public void setStarPositionOnMap(LatLng latLng)
    {
        starPositionOnMap =latLng;
    }




    public void setLocationListener(LocationResultUpdate listener)
    {
        locationResultUpdate=listener;
    }


    public interface   LocationResultUpdate
    {
        public  void onLocationUpdate(LatLng latLng, List<LatLng> userTrack);
        public  void onStarPicked(LatLng location, int starNumber);
        public  void onStarPickedNew(LatLng location, LatLng starLocation, int oldStarNumber, int newStarNumber);
        public  void allStarsPicked(LatLng starLocation);
    }


}
