package com.kampen.riksSe.leader.activity.fragments.map.tracking;

import androidx.annotation.NonNull;


import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.GoogleDirectionLib.DirectionCallback;
import com.kampen.riksSe.GoogleDirectionLib.GoogleDirection;
import com.kampen.riksSe.GoogleDirectionLib.constant.TransportMode;
import com.kampen.riksSe.GoogleDirectionLib.model.Direction;
import com.kampen.riksSe.utils.Constants;

public class RoutesAndDirectionProvider implements DirectionCallback {

    private LatLng mSource;
    private  LatLng mDestination;

    private  String  ApiKey=Constants.MAP_KEY;

    private  DirectionResultListener  mDirectionResultListener;

    public   RoutesAndDirectionProvider(@NonNull LatLng source, @NonNull  LatLng destination)
    {
        mSource=source;
        mDestination=destination;
    }

    public void  provideDirectionAndRoute()
    {
        GoogleDirection.withServerKey(ApiKey)
                .from(mSource)
                .to(mDestination)
                .transportMode(TransportMode.WALKING)
                .execute(this);

    }


    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

        if (direction.isOK()) {

            if(mDirectionResultListener!=null)
            {

                mDirectionResultListener.onResultArrived(direction,rawBody);

            }

        }

    }

    @Override
    public void onDirectionFailure(Throwable t) {


    }


    public  interface  DirectionResultListener
    {


        public  void onResultArrived(Direction direction, String rawBody);

    }


    public void registerResultListener(DirectionResultListener listener)
    {

        mDirectionResultListener=listener;
    }

    public void unRegisterResultListener()
    {

        mDirectionResultListener=null;
    }


}
