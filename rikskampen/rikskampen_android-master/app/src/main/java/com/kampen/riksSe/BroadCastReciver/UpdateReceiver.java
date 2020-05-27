package com.kampen.riksSe.BroadCastReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.stats.GCoreWakefulBroadcastReceiver;

public class UpdateReceiver extends GCoreWakefulBroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


/*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(isConnected(context)){

                    Intent i= new Intent(context, DailyStepsServiceInternet.class);
                    startWakefulService(context,i);
                    //Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(context, "Internet Lost", Toast.LENGTH_SHORT).show();
                }
            }
        }, 8000);*/



    }

    /*public  boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }*/

/*
    private boolean isOnline(Context context) {

        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }



    public boolean isConnected(Context context) {
        boolean connected = false;
        try {

            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;

        } catch (Exception e) {
           *//* Log.e("Connectivity Exception", e.getMessage());*//*
        }
        return connected;
    }*/

}
