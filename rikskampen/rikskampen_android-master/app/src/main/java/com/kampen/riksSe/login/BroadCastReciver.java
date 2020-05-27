package com.kampen.riksSe.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BroadCastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction()==Intent.ACTION_DATE_CHANGED){


       /*     SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String currentDateandTime = sdf.format(new Date());

            SaveSharedPreference.setUserLogInSteps(context,0);
            SaveSharedPreference.setUserLogInDate(context,currentDateandTime);*/


        }
    }
}
