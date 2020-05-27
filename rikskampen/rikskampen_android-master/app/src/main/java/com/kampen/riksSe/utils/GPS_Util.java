package com.kampen.riksSe.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;
import com.kampen.riksSe.GoogleDirectionLib.DirectionCallback;
import com.kampen.riksSe.GoogleDirectionLib.GoogleDirection;
import com.kampen.riksSe.GoogleDirectionLib.constant.TransportMode;
import com.kampen.riksSe.GoogleDirectionLib.model.Direction;
import com.kampen.riksSe.GoogleDirectionLib.model.Info;
import com.kampen.riksSe.GoogleDirectionLib.model.Leg;
import com.kampen.riksSe.GoogleDirectionLib.model.Route;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GPS_Util {



    public static boolean checkIfGPSEnabledOrNot(Context context)
    {


        boolean enabled=true;


        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps") && !provider.contains("network"))
        {

            enabled=false;


            // Build the alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainLeaderActivity.context);
            builder.setTitle("Location Services Not Active");
            builder.setMessage("Please enable Location Services and GPS");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {


                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();


        }


        return enabled;
    }


    //  sadgaghdfhdfhasjfgyhsd,gk
    //5 55 555 55


    public static List<LatLng> getMarkersEveryNMeters(List<LatLng> path, double distance) {


        if(path.size()==0) return  path;

        List<LatLng> res = new ArrayList();

        LatLng p0 = path.get(0);
        res.add(p0);
        if (path.size() > 2) {
            //Initialize temp variables for sum distance between points and
            //and save the previous point
            double tmp = 0;
            LatLng prev = p0;
            for (LatLng p : path) {
                //Sum the distance

                double toPrint=SphericalUtil.computeDistanceBetween(prev, p);
                System.out.print(toPrint+" ");
                tmp += toPrint;
                if (tmp < distance) {
                    //If it is less than certain value continue sum
                    prev = p;




                    res.add(p);


                }
                else
                {
                    break;
                }
            }

            //Add the last point of route
            //LatLng plast = path.get(path.size()-1);
            //res.add(plast);
        }

        return res;
    }


    public static LatLng getMarkersFromNMeters(List<LatLng> path, double distance) {


        if(path.size()==0) return  new LatLng(0,0);

        LatLng res = path.get(0);



        if (path.size() > 2) {
            //Initialize temp variables for sum distance between points and
            //and save the previous point
            double tmp = 0;
            LatLng prev = res;
            for (LatLng p : path) {
                //Sum the distance

                double toPrint=SphericalUtil.computeDistanceBetween(prev, p);
                System.out.print(toPrint+" ");
                tmp += toPrint;
                if (tmp < distance) {
                    //If it is less than certain value continue sum
                    prev = p;

                }
                else
                {
                    res=p;
                    break;
                }
            }

            //Add the last point of route
            //LatLng plast = path.get(path.size()-1);
            //res.add(plast);
        }


        return res;
    }


    public static List<LatLng> getMarkersFromNMeters(List<LatLng> path,List<Double> distanceList) {

        List<LatLng> starPositions=new ArrayList<>();

        if(path.size()==0)
        {
            starPositions.add(new LatLng(0,0));
            return starPositions;
        }

        LatLng res = path.get(0);


        double distance=distanceList.get(0);

        //distance+=200;

        if (path.size() > 2) {
            //Initialize temp variables for sum distance between points and
            //and save the previous point
            double tmp = 0;
            LatLng prev = res;
            for (LatLng p : path) {
                //Sum the distance

                double toPrint=SphericalUtil.computeDistanceBetween(prev, p);
                System.out.print(toPrint+" ");
                tmp += toPrint;
                if (tmp <= distance) {
                    //If it is less than certain value continue sum
                    prev = p;

                }
                else
                {
                    starPositions.add(p);

                    if(distanceList.size()>starPositions.size())
                    {
                        distance=distanceList.get(starPositions.size());
                    }
                    else {

                        break;
                    }
                }
            }


        }


        return starPositions;
    }


    public  static  List<LatLng>    getRoutePath(Route route)
    {
        List<LatLng> path=new ArrayList<>();

        List<Leg> legList = route.getLegList();

        for (int i = 0; i < route.getLegList().size(); i++) {

            Leg leg = legList.get(i);

            List<LatLng> legPoints = leg.getDirectionPoint();

            path.addAll(legPoints);


        }

        return path;
    }

    public static void getDirectionsPathFromWebService(LatLng origin, LatLng destination,int requiredDistance,DirectionResult directionResult) {


        List<LatLng> path = new ArrayList();

        GoogleDirection.withServerKey(Constants.MAP_KEY)
                .from(origin)
                //.and(waypoints)
                .to(destination)
                .transportMode(TransportMode.WALKING)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if(direction.isOK()) {



                            if (direction != null && direction.getRouteList()!=null && direction.getRouteList().size() > 0) {

                                if(direction.getRouteList()!=null) {
                                    for (int h = 0; h < direction.getRouteList().size(); h++) {

                                        Route route = direction.getRouteList().get(h);


                                        int routeDistance=getRouteDistance(route);

                                        if(routeDistance>requiredDistance) {

                                            for (int i = 0; i < route.getLegList().size(); i++) {

                                                List<Leg> legList = route.getLegList();

                                                for (int j = 0; j < legList.size(); j++) {

                                                    Leg leg = legList.get(j);

                                                    List<LatLng> legPoints = leg.getDirectionPoint();

                                                    path.addAll(legPoints);
                                                }

                                            }

                                            break;
                                        }

                                    }

                                }

                                directionResult.onDirectionResult(path);


                            }

                        }

                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {

                    } });




    }

    /*public static void getDirectionsPathFromWebService(LatLng origin, LatLng destination,MapFragPresenter.RouteResult routeResult,int requiredDistance) {


        List<LatLng> path = new ArrayList();

        GoogleDirection.withServerKey(Constants.MAP_KEY)
                .from(origin)
                //.and(waypoints)
                .to(destination)
                .transportMode(TransportMode.WALKING)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if(direction.isOK()) {



                            if (direction != null && direction.getRouteList()!=null && direction.getRouteList().size() > 0) {

                                if(direction.getRouteList()!=null) {

                                    for (int h = 0; h < direction.getRouteList().size(); h++) {

                                        Route route = direction.getRouteList().get(h);


                                        int routeDistance=getRouteDistance(route);

                                        if(requiredDistance >=requiredDistance)
                                        {
                                            routeResult.onRouteResult(route);
                                        }

                                    }

                                }
                                else
                                {
                                    routeResult.onRouteResult(null);
                                }

                            }
                            else
                            {
                                routeResult.onRouteResult(null);
                            }

                        }

                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {



                        routeResult.onRouteResult(null);


                    } });



    }
*/
    public static   int getRouteDistance(Route route)
    {

        int distance=0;

        List<Leg> legList = route.getLegList();

        for (int j = 0; j < legList.size(); j++) {

            Leg leg = legList.get(j);

            try {
                distance += Integer.parseInt(((Info) leg.getDistance()).getValue());
            }catch (Exception ex){}

        }


        return distance;

    }


    public static   LatLng getRouteDistanceLating(Route route,int maxDistance)
    {

        int distance=0;

        List<Leg> legList = route.getLegList();

        LatLng latLng=new LatLng(legList.get(0).getStartLocation().getLatitude(),legList.get(0).getStartLocation().getLongitude());

        for (int j = 0; j < legList.size(); j++) {

            Leg leg = legList.get(j);

            try {
                distance += Integer.parseInt(((Info) leg.getDistance()).getValue());
            }catch (Exception ex){}


            if(distance>=maxDistance)
            {
                latLng=new LatLng(leg.getStartLocation().getLatitude(),leg.getStartLocation().getLongitude());
                return latLng;
            }

        }


        return latLng;

    }

    public static int getRandom(int randomBound){

        Random random=new Random();
        int result=0;
        if(randomBound>1)
        {
            result= random.nextInt(randomBound);
        }


        return result;
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }



    public   interface   DirectionResult
    {

        public  void   onDirectionResult(List<LatLng> path);

    }

}
