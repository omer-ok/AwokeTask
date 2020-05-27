package com.kampen.riksSe.leader.activity.fragments.map.tracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GpsLocationReceiver extends BroadcastReceiver  {


    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().matches("android.location.KEY_PROVIDER_ENABLED"))
        {
            //Toast.makeText(context, "GPS changes", Toast.LENGTH_SHORT).show();

            Intent intent1=new Intent("location_status_changed");

            intent1.putExtras(intent);

            context.sendBroadcast(intent1);
        }
    }

}
