package com.kampen.riksSe.leader.activity.fragments.map;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.GetPlacesLib.NRPlaces;
import com.kampen.riksSe.GetPlacesLib.Place;
import com.kampen.riksSe.GetPlacesLib.PlaceType;
import com.kampen.riksSe.GetPlacesLib.PlacesException;
import com.kampen.riksSe.GetPlacesLib.PlacesListener;
import com.kampen.riksSe.utils.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class PlacesProvider implements PlacesListener {



    public  static final  int  DEFAULT_NUM_PLACES=5;

    private LatLng  mTargetPosition;

    private int mRadius;

    private PlaceType mPlaceType;

    private String  mMapKey="";

    private  int   mMaxNumberOfPlaces=DEFAULT_NUM_PLACES;


    private  PlacesResultListener   mPlaceResultListener;



    public  PlacesProvider(LatLng  targetPosition,int radius, PlaceType placeType,String  mapKey)
    {

        setTargetPosition(targetPosition);
        setRadius(radius);
        mPlaceType=placeType;
        mMapKey=mapKey;


    }



    public  void providePlaces(int maxNumberOfPlaces)
    {

        setMaxNumberOfPlaces(maxNumberOfPlaces);

        try {
            new NRPlaces.Builder()
                    .listener(PlacesProvider.this)
                    .key(Constants.MAP_KEY)
                    .latlng(getTargetPosition().latitude, getTargetPosition().longitude)
                    .radius(getRadius())

                   // .type(PlaceType.)
                    .build()
                    .execute();
            Log.i("Place Lat",getTargetPosition().latitude+"Place Long  "+getTargetPosition().longitude);
        } catch (Exception ex) {
            ex.toString();
        }
    }


    public  void  setPlaceResultListener(PlacesResultListener listener)
    {

        mPlaceResultListener=listener;
    }

    @Override
    public void onPlacesFailure(PlacesException e) {


        if(mPlaceResultListener!=null)
        {
            mPlaceResultListener.onPlacesFailure(e);
        }

    }

    @Override
    public void onPlacesStart() {



    }

    @Override
    public void onPlacesSuccess(List<Place> places) {


         if(mPlaceResultListener!=null)
        {

            mPlaceResultListener.onPlacesSuccess(places);
            Log.i("plase size", String.valueOf(places.size()));

            /*if(places!=null && places.size()> getMaxNumberOfPlaces())
               places=places.subList(0, getMaxNumberOfPlaces());
                //places=places.subList((places.size())-getMaxNumberOfPlaces(), places.size());


                mPlaceResultListener.onPlacesSuccess(places);
*/        }


    }

    @Override
    public void onPlacesFinished() {



    }

    public LatLng getTargetPosition() {
        return mTargetPosition;
    }

    public void setTargetPosition(LatLng mTargetPosition) {
        this.mTargetPosition = mTargetPosition;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public int getMaxNumberOfPlaces() {
        return mMaxNumberOfPlaces;
    }

    public void setMaxNumberOfPlaces(int mMaxNumberOfPlaces) {
        this.mMaxNumberOfPlaces = mMaxNumberOfPlaces;
    }

    public  interface   PlacesResultListener
    {

        public void onPlacesSuccess(List<Place> places);

        public void onPlacesFailure(PlacesException e);
    }


}
