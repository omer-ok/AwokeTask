package com.kampen.riksSe.leader.activity.fragments.map.Map;

import android.content.Context;
import androidx.annotation.NonNull;

import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;


import com.facebook.stetho.server.http.HttpStatus;
import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.GetPlacesLib.Place;
import com.kampen.riksSe.GetPlacesLib.PlacesException;
import com.kampen.riksSe.GoogleDirectionLib.DirectionCallback;
import com.kampen.riksSe.GoogleDirectionLib.GoogleDirection;
import com.kampen.riksSe.GoogleDirectionLib.constant.AvoidType;
import com.kampen.riksSe.GoogleDirectionLib.constant.TransportMode;
import com.kampen.riksSe.GoogleDirectionLib.model.Direction;
import com.kampen.riksSe.GoogleDirectionLib.model.Route;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.map.PlacesProvider;
import com.kampen.riksSe.leader.activity.modelV3.Permissions;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.GPS_Util;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;


import io.realm.Realm;

import static android.content.Context.WIFI_SERVICE;
import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;
import static com.kampen.riksSe.utils.GPS_Util.getMarkersFromNMeters;
import static com.kampen.riksSe.utils.GPS_Util.getRouteDistance;
import static com.kampen.riksSe.utils.GPS_Util.getRoutePath;

enum NetworkTry
{
    FIRST_TRY,
    SECOND_TRY
}

public class MapFragPresenter  implements  MapFragContract.Presenter{




    @NonNull
    private   MapFragContract._View    mMapFragView;

    public static final int DEFAULT=500;

    private  int selectedStarRadius=DEFAULT;


    private PlacesProvider mPlacesProvider;
    private int apiCallsCounter = 0;



    public MapFragPresenter(MapFragContract._View mapFragView)
    {

        mMapFragView = checkNotNull(mapFragView);
    }


    @Override
    public void setStarButtonClicks(View star1, View star2, View star3, View star4,View star5) {


        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starOneClick(v);
            }
        });

        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starTwoClick(v);

            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starThreeClick(v);

            }
        });


        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starFourClick(v);

            }
        });

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starFiveClick(v);

            }
        });

    }

    @Override
    public void UpdateUserPermissionAndVersion(Context context,Realm_User mUser,String AppVersion) {
        try{

            String AndroidOSVersion = Build.VERSION.RELEASE;

            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();
            WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

            String manufacturer = Build.MANUFACTURER;
            String model = Build.MODEL;
            String device = Build.DEVICE;

            Log.i("Agent",device+" "+model+" "+manufacturer);

            Permissions permissions = new Permissions();
            permissions.setCameraPermission(SaveSharedPreference.getCameraPermission(context));
            permissions.setLocationForeground(SaveSharedPreference.getLocationPermissionForeground(context));
            permissions.setLocationBackGround(SaveSharedPreference.getLocationPermissionBackground(context));
            permissions.setStepCounterPermission(SaveSharedPreference.getStepCounterPermission(context));
            permissions.setDrawOverOtherApps(SaveSharedPreference.getDrawOverAppsPermission(context));
            permissions.setStepsForegroundService(SaveSharedPreference.getStepsForeGroundService(context));

            UserPermissionAndVersion userPermissionAndVersion =new UserPermissionAndVersion();
            userPermissionAndVersion.setUserId(Integer.parseInt(mUser.getId()));
            userPermissionAndVersion.setTimezone(tz.getID());
            userPermissionAndVersion.setIpAddress(ip);
            userPermissionAndVersion.setAgent(manufacturer+" "+model);
            userPermissionAndVersion.setOperatingSystem("android");
            userPermissionAndVersion.setOperatingSystemVersion(AndroidOSVersion);
            userPermissionAndVersion.setPermissions(permissions);

            userPermissionAndVersion.setAppVersion(AppVersion);


            Realm_User user = provideUserLocal(context);
            if(user!=null) {
                if (user.getToken() != null) {
                    String token = "bearer " + user.getToken();
                    Repository.UpdateUserPermissionAndVersion(token,userPermissionAndVersion,new ResponseStatus() {
                        @Override
                        public void onResult(ResponseStatus status) {

                            if (mMapFragView != null && status.getCode() == 201 && status.getStatus().equals(Repository.STATUS_SUCCESS)) {
                                mMapFragView.setUpdateUserPermissionAndVersionSucess(status.getMsg());
                            } else {
                                mMapFragView.setUpdateUserPermissionAndVersionFailed(status.getMsg());
                            }
                        }
                    });
                }
            }
        }catch (Exception e){

        }
    }

    @Override
    public void setStarsHomeEditButtonClicks(View star1, View star2, View star3, View star4, View star5, View home, View edit) {

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starOneClick(v);

            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starTwoClick(v);

            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starThreeClick(v);

            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starFourClick(v);

            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.starFiveClick(v);

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.homeClick(v);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                    mMapFragView.editClick(v);

            }
        });




    }

    @Override
    public void setStartPauseStopChaseButtonClick(View start, View pause,View finish,View save, View stop,View cancel) {


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.playClick(v);
                }

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.pauseClick(v);
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.stopClick(v);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.cancelClick(v);
                }

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.finishClick(v);
                }

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.saveClick(v);
                }

            }
        });

    }

    @Override
    public void setChaseAndStopChase(View chase,View stop) {


        chase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.starChaseClick(v);
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mMapFragView!=null)
                {
                    mMapFragView.starStopClick(v);
                }

            }
        });


    }


    private int getRadiusForPlaces(int radius)
    {
        switch (radius)
        {
            case 1000:
                return  1200;
            case 2000:
                return  2200;
            case 3000:
                return  3200;
            case 4000:
                return  4200;
            case  5000:
                return   5200;
        }

        return 1000;
    }

    @Override
    public void getPlacesInRadius(int radius, int numberOfPlaces, LatLng latLng) {


        selectedStarRadius=getRadiusForPlaces(radius);
        //selectedStarRadius=10000;

        //radius*3 to get longer routes
        mPlacesProvider=new PlacesProvider(latLng,getRadiusForPlaces(radius),null,Constants.MAP_KEY);
        //mPlacesProvider=new PlacesProvider(latLng,10000,null,Constants.MAP_KEY);

        mPlacesProvider.setPlaceResultListener(new PlacesProvider.PlacesResultListener() {
            @Override
            public void onPlacesSuccess(List<Place> places) {

                if(mMapFragView!=null)
                {
                    mMapFragView.setPlaces(places);
                }

            }

            @Override
            public void onPlacesFailure(PlacesException e) {


                if(mMapFragView!=null)
                {
                    mMapFragView.onMapPathFailed(e.toString());
                }

            }
        });


        mPlacesProvider.providePlaces(numberOfPlaces);


    }

    public void getHome( LatLng currentPlace,LatLng homePlace){



        getDirections(currentPlace,homePlace, new DirectionCallback() {
            @Override
            public void onDirectionSuccess(Direction direction, String rawBody) {

               if(direction.isOK() && direction.getRouteList()!=null && direction.getRouteList().size()>0)
               {
                   Route  homeRoute =   direction.getRouteList().get(0);
                   mMapFragView.setHomeRouteSucess(homeRoute);
               }
            }

            @Override
            public void onDirectionFailure(Throwable t) {

                mMapFragView.setHomeRouteFail("No Internet Connection");
            }
        });



    }




    public void getRequiredPaths(List<Place> places,LatLng startPosition){

        ArrayList<Direction>  directionArrayList=new ArrayList<>();

        for(int i = 0; i< places.size(); i++){
        //for(int i = 0; i< 1; i++){

            Place  destPlace=places.get(i);
            getDirections(startPosition, new LatLng(destPlace.getLatitude(), destPlace.getLongitude()), new DirectionCallback() {

                @Override
                public void onDirectionSuccess(Direction direction, String rawBody) {

                    directionArrayList.add(direction);

                    /*if(directionArrayList.size()==1)
                    {
                        mMapFragView.setDirections(directionArrayList);
                    }*/
                    apiCallsCounter += 1;
                    if (apiCallsCounter == places.size()){
                        mMapFragView.setDirections(directionArrayList);
                        apiCallsCounter = 0;
                    }
                }

                @Override
                public void onDirectionFailure(Throwable t) {

/*                    directionArrayList.add((Direction) null);

                    if (directionArrayList.size() == 1) {
                        mMapFragView.setDirections(directionArrayList);
                    }*/
                    apiCallsCounter += 1;
                    if (apiCallsCounter == places.size()){
                        mMapFragView.setDirections(directionArrayList);
                        apiCallsCounter = 0;
                    }
                }

            });
        }
    }


        public  void   getRequiredRoutesFromDirections(final ArrayList<Direction> directionArrayList)
        {

           new Thread(new Runnable() {
               @Override
               public void run() {


                   Log.i("Direction list ", String.valueOf(directionArrayList.size()));
                   ArrayList<Direction>  directionArrayListFiltered = filterDirectionList(directionArrayList);

                   ArrayList<Route> routeArrayList=new ArrayList<>();

                   ArrayList<Route> finalRouteArrayList=new ArrayList<>();

                   for(int i=0; i<directionArrayListFiltered.size();i++)
                   {
                       routeArrayList.add((Route)getBestRoute_(directionArrayListFiltered.get(i)));
                   }

                   routeArrayList=filterRoutesList(routeArrayList);

                   if(routeArrayList != null && routeArrayList.size() > 0){
                       finalRouteArrayList.add(routeArrayList.get(0));

                       mMapFragView.setBestRoutes(finalRouteArrayList,selectedStarRadius);
                   }else{
                       mMapFragView.onMapPathFailed("Ingen väg är tillgänglig på grund av motorväg eller vattenpassage.");
                   }




               }
           }).start();



        }


   /* public  ArrayList<Direction> filterDirectionList(ArrayList<Direction> directionArrayList)
    {
        for(int i=0; i<directionArrayList.size();i++)
        {
            if(directionArrayList.get(i)==null) {
                directionArrayList.remove(i);
                i=0;
            }
        }

        return  directionArrayList;
    }
*/


        public  ArrayList<Direction> filterDirectionList(ArrayList<Direction> directionArrayList)
        {


            try {

            if(MapConstants.HIGHWAYS.size()==0){

                MapConstants.init();


            }




            for(int i=0; i<directionArrayList.size();i++) {

                if (directionArrayList.get(i) == null) {
                    directionArrayList.remove(i);
                    i = 0;

                }
                for (int k = 0; k < directionArrayList.get(i).getRouteList().size(); k++) {

                 //   for(int l=0; l <directionArrayList.get(i).getRouteList().get(k).getLegList().size();l++){

                        for (int j = 0; directionArrayList.get(i).getRouteList().size()!=0 && j < directionArrayList.get(i).getRouteList().get(k).getLegList().get(0).getStepList().size() ; j++) {

                                   //int stepsListSize=directionArrayList.get(i).getRouteList().get(k).getLegList().get(0).getStepList().size();

                                   if(  directionArrayList.get(i).getRouteList().get(k).getLegList().get(0).getStepList().get(j).getManeuver()!=null && directionArrayList.get(i).getRouteList().get(k).getLegList().get(0).getStepList().get(j).getManeuver().equals("ferry")){

                                       directionArrayList.get(i).getRouteList().remove(k);
                                       if(directionArrayList.get(i).getRouteList().size()==0)
                                       {
                                           directionArrayList.remove(i);
                                       }
                                       k=0;
                                       j=0;
                                       i=0;
                                       //break;
                                    }
                                    else if( MapConstants.isRouteOnHighWay(directionArrayList.get(i).getRouteList().get(k).getLegList().get(0).getStepList().get(j).getHtmlInstruction())){
                                       directionArrayList.get(i).getRouteList().remove(k);

                                       if(directionArrayList.get(i).getRouteList().size()==0)
                                       {
                                           directionArrayList.remove(i);
                                       }

                                       k = 0;
                                       j=0;
                                       i=0;
                                       //break;

                                    }


                            }

                        }
                //}



            }

            if(directionArrayList==null || directionArrayList.get(0).getRouteList().size() ==0 ){
                mMapFragView.onMapPathFailed("Ingen väg är tillgänglig på grund av motorväg eller vattenpassage.");
            }

               } catch (Exception e) {

                    mMapFragView.onMapPathFailed("Ingen väg är tillgänglig på grund av motorväg eller vattenpassage.");
                    //Log.i("Route Error",e.toString());
                }


            //directionArrayListFiltered.add(directionArrayList.get(0));

            return  directionArrayList;
        }


    public  ArrayList<Route> filterRoutesList(ArrayList<Route> routes)
    {
        routes.removeAll(Collections.singleton(null));
        return routes;
        /*for(int i=0; i<routes.size();i++)
        {
            if(routes.get(i)==null ) {
                routes.remove(i);
               i=0;
            }
        }

        return  routes;*/
    }


    @Override
    public void AddActivity(Realm_User user, String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location, String Distance,String weight,String Wasit) {

        String  token="bearer "+user.getToken();

        Repository.addActivity(user,token, activity_title,  activity_type_id,  steps_count,  day_name,  stars_count,  calories,  user_activity_time,  activity_image, activity_lat,  activity_long,  activity_location,Distance,weight,Wasit, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mMapFragView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mMapFragView.setAddActivity(status.getMsg());
                }
                else
                {
                    mMapFragView.setAddActivityFailed(status.getMsg());
                }
            }
        });

    }

    @Override
    public void AddStarSteps(Realm_User user, String starCount, String stepCount, String calories,String Date,String distance,String timer) {

        String  token="bearer "+user.getToken();

        Repository.addChaseStarAndSteps(user,token,starCount,stepCount,calories,Date, distance,timer,new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mMapFragView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mMapFragView.setStarStepChaseSucess(status.getMsg());
                }
                else
                {
                    mMapFragView.setStarStepChaseFailed(status.getMsg());
                }
            }
        });


    }

    public void updateStarUserLocal( String starCount,String userID)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        try {
            final Realm_User updateStarLocal= mLocalService.where(Realm_User.class)
                    .equalTo("id",userID)
                    .findFirst();

            if(updateStarLocal != null){

                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                         updateStarLocal.setStars_count(starCount);

                        realm.insertOrUpdate(updateStarLocal);

                    }
                });
            }

        }catch (Exception ex)
        {
            ex.toString();

        }

    }


   /* public Integer GetTodayActivityData(String DateLocal){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        final RealmResults<UserActivityData> Date = mLocalService.where(UserActivityData.class)
                .equalTo(Constants.USER_TODAY_DATE,DateLocal)
                .findAll();

        int StarCount = 0;

        if(Date !=null && Date.size()>0){
            StarCount = Date.get(0).getLocatStarCount();
        }


        return StarCount;
    }
*/

    public void getRoutesFromSelectedPlace(LatLng start,List<Place> places,Context context,NetworkTry tryToFetchData)
    {

        Place place=places.get(GPS_Util.getRandom(places.size()-1));


        getDirections(start, new LatLng(place.getLatitude(), place.getLongitude()), new DirectionCallback() {
            @Override
            public void onDirectionSuccess(Direction direction, String rawBody) {

                if(mMapFragView!=null)
                    mMapFragView.setDirectionAndRoutes(direction);
            }
            @Override
            public void onDirectionFailure(Throwable t) {

                if(GPS_Util.isNetworkAvailable(context) && tryToFetchData==NetworkTry.FIRST_TRY) {
                    getRoutesFromSelectedPlace(start, places,context,NetworkTry.SECOND_TRY);
                }
                else
                {
                    if(mMapFragView!=null)
                        mMapFragView.onMapPathFailed(context.getResources().getString(R.string.MapModule_Something_Went_Wrong));
                }

            }
        });
    }

    public  void getDirections(LatLng origin, LatLng destination,DirectionCallback directionCallback) {

        GoogleDirection.withServerKey(Constants.MAP_KEY)
                .from(origin)
                .to(destination)
                .language("en")
                .alternativeRoute(true)
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.HIGHWAYS)
                .avoid("ferries|highways|tolls")
                .transportMode(TransportMode.WALKING)
                .execute(directionCallback);

        /*Log.i("direction origin", String.valueOf(origin));
        Log.i("direction destination", String.valueOf(destination));*/

    }



    public Route  getBestRoute_(Direction direction) {

        if (direction != null && direction.isOK() && direction.getRouteList() != null && direction.getRouteList().size() > 0) {

                Route bestRoute = null;
                int routeDistance=0;

                for (int h = 0; h < direction.getRouteList().size(); h++) {

                    Route route = direction.getRouteList().get(h);
                    routeDistance = getRouteDistance(route);

                    if (routeDistance >= selectedStarRadius) {

                        bestRoute=route;
                        return  bestRoute;

                    }
                }

                   return  null;

        } else {
                   return  null; }

    }

    public void  getBestRoute(Direction direction) {

        if (direction != null && direction.isOK() && direction.getRouteList() != null && direction.getRouteList().size() > 0) {


            ((Runnable) () -> {


                int routeDistance=0;

                List<Route>  routeList=new ArrayList<>();

                for (int h = 0; h < direction.getRouteList().size(); h++) {

                    Route route = direction.getRouteList().get(h);
                    routeDistance = getRouteDistance(route);

                    if (routeDistance >= selectedStarRadius) {
                        routeList.add(route);
                    }
                }

                Route bestRoute = null;
                if (routeList.size() > 0) {

                        bestRoute = routeList.get(GPS_Util.getRandom(routeList.size() - 1));

                        if (mMapFragView != null) {
                            mMapFragView.setBestRoute(bestRoute);
                        }

                }
                else
                {

                    if(mMapFragView!=null)
                        mMapFragView.onMapPathFailed("Please Try again...");
                }




            }).run();


        } else {



        }

    }



    public  void  getReturnRouteAndStarPositions(@NonNull Route bestRoute,Context context,NetworkTry networkTry)
    {

        List<LatLng> pathToDraw= getRouteToDrawInLatLng(bestRoute);


        getDirectionsPathFromWebServiceSingle(pathToDraw.get(pathToDraw.size() - 1), pathToDraw.get(0),

                new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {

                        if (direction != null ) {

                            if(direction.getRouteList()!=null) {
                                List<Route>  routeList=direction.getRouteList();


                                int routeDistance=0;

                                List<Route>  routeListToSelect=new ArrayList<>();

                                for (int h = 0; h < direction.getRouteList().size(); h++) {

                                    Route route = direction.getRouteList().get(h);
                                    routeDistance = getRouteDistance(route);

                                    if (routeDistance >= selectedStarRadius) {
                                        routeListToSelect.add(route);
                                    }
                                }

                                if (routeListToSelect.size() > 0) {
                                    Random random = new Random();
                                    Route returnRoute= routeListToSelect.get(GPS_Util.getRandom(routeList.size()-1));

                                    List<LatLng> pathToReturn=  getRoutePath(returnRoute);
                                    getAllRouteAndStarPositions(pathToDraw,pathToReturn);

                                }
                                else
                                {

                                    if(GPS_Util.isNetworkAvailable(context) && networkTry==NetworkTry.FIRST_TRY)
                                    {
                                        getReturnRouteAndStarPositions(bestRoute,context,NetworkTry.SECOND_TRY);
                                    }
                                    else
                                    {
                                        if(mMapFragView!=null)
                                            mMapFragView.onMapPathFailed(context.getResources().getString(R.string.MapModule_Something_Went_Wrong));
                                    }

                                }
                            }
                        }
                        else
                        {
                            if(GPS_Util.isNetworkAvailable(context)  && networkTry==NetworkTry.FIRST_TRY)
                            {
                                getReturnRouteAndStarPositions(bestRoute,context,NetworkTry.SECOND_TRY);
                            }
                            else
                            {
                                if(mMapFragView!=null)
                                    mMapFragView.onMapPathFailed(context.getResources().getString(R.string.MapModule_Something_Went_Wrong));
                            }
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {


                        if(GPS_Util.isNetworkAvailable(context) && networkTry==NetworkTry.FIRST_TRY)
                        {
                            getReturnRouteAndStarPositions(bestRoute,context,NetworkTry.SECOND_TRY);
                        }
                        else
                        {
                            if(mMapFragView!=null)
                                mMapFragView.onMapPathFailed(context.getResources().getString(R.string.MapModule_Something_Went_Wrong));
                        }
                    }
                }
        );


    }


    private  void getStarsFromRouteNewFrag(List<LatLng> pathToDraw,List<LatLng> returnRoute)
    {

        List<LatLng>  tempPath=new ArrayList<>();
        List<Double>  starDistances=new ArrayList<>();

        tempPath.addAll(pathToDraw);
        tempPath.addAll(returnRoute);

        int radius=selectedStarRadius;

        List<LatLng> starPositions=null;



            starDistances.add((double) 100);
            starDistances.add((double) 200);
            starDistances.add((double) 300);
            starDistances.add((double) 400);
            starDistances.add((double) 500);

            starPositions=getMarkersFromNMeters(tempPath, starDistances.subList(0,radius/1000));

        if(mMapFragView!=null)
        {
            mMapFragView.setAllRoute(pathToDraw,returnRoute,starPositions);
        }

    }

    private  void getAllRouteAndStarPositions(List<LatLng> pathToDraw,List<LatLng> returnRoute)
    {

        List<LatLng>  tempPath=new ArrayList<>();
        List<Double>  starDistances=new ArrayList<>();

        tempPath.addAll(pathToDraw);
        tempPath.addAll(returnRoute);

        int radius=selectedStarRadius;

        List<LatLng> starPositions=null;


        if(radius==500)
        {

            starDistances.add((double) 800);



        }
        if(radius==1000)
        {
            starDistances.add((double) 1000);
            starDistances.add((double) 1700);
            //just for testing
             /*starDistances.add((double) 200);
             starDistances.add((double) 250);*/
        }

        if(radius==1500)
        {
            starDistances.add((double) 900);
            starDistances.add((double) 1500);
            starDistances.add((double) 2700);
        }

        if(radius==2000)
        {

            starDistances.add((double) 1000);
            starDistances.add((double) 2000);
            starDistances.add((double) 3000);
            starDistances.add((double) 3800);

        }

        if(radius>=2500)
        {


            starDistances.add((double) 1000);
            starDistances.add((double) 2000);
            starDistances.add((double) 3000);
            starDistances.add((double) 4000);
            starDistances.add((double) 4600);
        }


        starPositions=getMarkersFromNMeters(tempPath, starDistances);

        if(mMapFragView!=null)
        {
            mMapFragView.setAllRoute(pathToDraw,returnRoute,starPositions);
        }

    }


    private List<LatLng> getRouteToDrawInLatLng( Route bestRoute )
    {
        List<LatLng> path = getRoutePath(bestRoute);
        List<LatLng> pathToDraw = GPS_Util.getMarkersEveryNMeters(path, selectedStarRadius);

        return pathToDraw;
    }



    public  void getDirectionsPathFromWebServiceSingle(LatLng origin, LatLng destination,DirectionCallback directionCallback) {

        GoogleDirection.withServerKey(Constants.MAP_KEY)
                .from(origin)
                .to(destination)
                .alternativeRoute(true)
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.HIGHWAYS)
                .transportMode(TransportMode.WALKING)
                .execute(directionCallback);


    }


    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }


    public    void updateUserStartsSteps(Context context , String starsPicked)
    {

        Realm_User realm_user=provideUserLocal(context);

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = realm_user;


                db_user.setStars_count(starsPicked+"");
                //db_user.setStars_count(starsPicked+"");

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public    void updateUserDistances(Context context , String distance)
    {

        Realm_User realm_user=provideUserLocal(context);

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = realm_user;


                db_user.setDistance(distance);
                //db_user.setStars_count(starsPicked+"");

                realm.insertOrUpdate(db_user);

            }
        });
    }


}
