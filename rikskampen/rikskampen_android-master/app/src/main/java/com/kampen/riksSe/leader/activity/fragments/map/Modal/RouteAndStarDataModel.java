package com.kampen.riksSe.leader.activity.fragments.map.Modal;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class RouteAndStarDataModel {

  private  int totalStars=0;
  private   List<LatLng>    bestRoute=new ArrayList<>();
  private   List<LatLng>    returnRoute=new ArrayList<>();
  private   List<LatLng>    starPositions=new ArrayList<>();
  private   Location        userCurrentLocation=new Location("current location");
  private   List<LatLng>    userChasedRoot= new ArrayList<>();


  public void clear()
  {
      bestRoute.clear();
      returnRoute.clear();
      starPositions.clear();
      userChasedRoot.clear();
  }


    public List<LatLng> getBestRoute() {
        return bestRoute;
    }

    public void setBestRoute(List<LatLng> bestRoute) {
        this.bestRoute = bestRoute;
    }

    public List<LatLng> getReturnRoute() {
        return returnRoute;
    }

    public void setReturnRoute(List<LatLng> returnRoute) {
        this.returnRoute = returnRoute;
    }

    public List<LatLng> getStarPositions() {
        return starPositions;
    }

    public void setStarPositions(List<LatLng> starPositions) {
        this.starPositions = starPositions;
    }

    public Location getUserCurrentLocation() {
        return userCurrentLocation;
    }

    public void setUserCurrentLocation(double latitude,double longitude) {
        this.userCurrentLocation.setLatitude(latitude);
        this.userCurrentLocation.setLongitude(longitude);
    }

    public List<LatLng> getUserChasedRoot() {
        return userChasedRoot;
    }

    public void setUserChasedRoot(List<LatLng> userChasedRoot) {
        this.userChasedRoot = userChasedRoot;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }
}
