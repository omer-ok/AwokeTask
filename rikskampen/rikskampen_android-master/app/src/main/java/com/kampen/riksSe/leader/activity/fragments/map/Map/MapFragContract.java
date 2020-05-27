package com.kampen.riksSe.leader.activity.fragments.map.Map;

import android.content.Context;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.BasePresenter;
import com.kampen.riksSe.BaseView;
import com.kampen.riksSe.GetPlacesLib.Place;
import com.kampen.riksSe.GoogleDirectionLib.model.Direction;
import com.kampen.riksSe.GoogleDirectionLib.model.Route;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MapFragContract {


    interface   _View  extends BaseView<Presenter>
    {


        public void starOneClick(View view);
        public void starTwoClick(View view);
        public void starThreeClick(View view);
        public void starFourClick(View view);
        public void starFiveClick(View view);
        public void homeClick(View view);
        public void editClick(View view);
        public void playClick(View view);
        public void stopClick(View view);
        public void finishClick(View view);
        public void saveClick(View view);
        public void pauseClick(View view);
        public void cancelClick(View view);



        public void starChaseClick(View view);

        public void starStopClick(View view);

        public void onMapPathFailed(String errorMessage);

        public void setPlaces(List<Place> places);

        public void setDirectionAndRoutes(Direction direction);

        public void setDirections(ArrayList<Direction> direction);

        public void setBestRoute(Route bestRoute);

        public void setBestRoutes(ArrayList<Route> bestRoutes, int distance);

        public void setAllRoute(List<LatLng> bestRoute, List<LatLng> returnRoute, List<LatLng> starPositions);

        void setAddActivity(String message);

        void setAddActivityFailed(String message);

        public void setStarStepChaseSucess(String message);
        public void setStarStepChaseFailed(String message);

        public void setHomeRouteSucess(Route routeSucess);
        public void setHomeRouteFail(String routefail);

        void setUpdateUserPermissionAndVersionSucess(String message);
        void setUpdateUserPermissionAndVersionFailed(String message);



    }


    interface   Presenter  extends BasePresenter
    {

        public void setStarButtonClicks(View star1, View star2, View star3, View star4, View star5);

        public void setStarsHomeEditButtonClicks(View star1, View star2, View star3, View star4, View star5, View home, View edit);

        public void setStartPauseStopChaseButtonClick(View start,View finish,View save, View pause, View stop, View cancel);

        public void setChaseAndStopChase(View chase, View stop);

        public void getPlacesInRadius(int radius, int numberOfPlaces, LatLng latLng);

        void AddActivity(Realm_User user, String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location, String Distance, String weight,String Wasit);

        void AddStarSteps(Realm_User user, String starCount, String stepCount, String calories,String distance,String timer, String Date);
        void UpdateUserPermissionAndVersion(Context context, Realm_User mUser, String AppVersion);
    }


}
