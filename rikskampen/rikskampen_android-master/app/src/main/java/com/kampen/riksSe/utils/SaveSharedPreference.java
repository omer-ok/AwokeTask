package com.kampen.riksSe.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.BaseActivity;
import com.kampen.riksSe.api.remote_api.APIUrl;

import static com.kampen.riksSe.api.remote_api.APIUrl.BASE_PROD_URL;
import static com.kampen.riksSe.api.remote_api.APIUrl.BASE_URL;
import static com.kampen.riksSe.utils.PreferencesUtility.API_SYNCED_DATE;
import static com.kampen.riksSe.utils.PreferencesUtility.APP_STATE_PREF;
import static com.kampen.riksSe.utils.PreferencesUtility.APP_STATUS;
import static com.kampen.riksSe.utils.PreferencesUtility.APP_VERSION;
import static com.kampen.riksSe.utils.PreferencesUtility.BASE_URL_SESSION;
import static com.kampen.riksSe.utils.PreferencesUtility.CAMERA_PERMISSION;
import static com.kampen.riksSe.utils.PreferencesUtility.CHASE_STAR;
import static com.kampen.riksSe.utils.PreferencesUtility.CHASE_STAR_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.CHASE_STAR_STEPS_CURRENT;
import static com.kampen.riksSe.utils.PreferencesUtility.CHAT_ACTIVTY_STATUS;
import static com.kampen.riksSe.utils.PreferencesUtility.CHAT_COUNT;
import static com.kampen.riksSe.utils.PreferencesUtility.CHAT_COUNT_CURRENT;
import static com.kampen.riksSe.utils.PreferencesUtility.CHAT_COUNT_ZERO;
import static com.kampen.riksSe.utils.PreferencesUtility.CURRENT_DATE_COMPITION;
import static com.kampen.riksSe.utils.PreferencesUtility.DAILY_DATE;
import static com.kampen.riksSe.utils.PreferencesUtility.DAILY_IMAGE;
import static com.kampen.riksSe.utils.PreferencesUtility.DAILY_LOCATION;
import static com.kampen.riksSe.utils.PreferencesUtility.DEVICE_STATUS;
import static com.kampen.riksSe.utils.PreferencesUtility.DISTANCE;
import static com.kampen.riksSe.utils.PreferencesUtility.DRAW_OVER_OTHER_APPS_PERMISSION;
import static com.kampen.riksSe.utils.PreferencesUtility.ENCRYPTION_KEY;
import static com.kampen.riksSe.utils.PreferencesUtility.FCM_TOKEN;
import static com.kampen.riksSe.utils.PreferencesUtility.GOOGLE_API;
import static com.kampen.riksSe.utils.PreferencesUtility.GOOGLE_FIT_STATUS;
import static com.kampen.riksSe.utils.PreferencesUtility.GOOGLE_FIT_TODAY_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.GOOGLE_FIT_TODAY_STEPS_ADD;
import static com.kampen.riksSe.utils.PreferencesUtility.JOURNEY_CALORIES;
import static com.kampen.riksSe.utils.PreferencesUtility.JOURNEY_CHASE_STAR;
import static com.kampen.riksSe.utils.PreferencesUtility.JOURNEY_DISTANCE;
import static com.kampen.riksSe.utils.PreferencesUtility.JOURNEY_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.LAN_IN_PREF;
import static com.kampen.riksSe.utils.PreferencesUtility.LAST_DAY_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.LAST_SCHDULE_SLOT_ID;
import static com.kampen.riksSe.utils.PreferencesUtility.LAT_IN_PREF;
import static com.kampen.riksSe.utils.PreferencesUtility.LOCATION_PERMISSION_BACKGROUND;
import static com.kampen.riksSe.utils.PreferencesUtility.LOCATION_PERMISSION_FOREGROUND;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_DATE;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_DATE_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_FCM;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_FIRST_TIME;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_PREF;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_PREF_Email;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_PREF_pass;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_SPLASH;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_IN_STEPS_FIRST_TIME;
import static com.kampen.riksSe.utils.PreferencesUtility.LOGGED_TESTER_USER;
import static com.kampen.riksSe.utils.PreferencesUtility.NEED_TO_UPDATE;
import static com.kampen.riksSe.utils.PreferencesUtility.NUTRITION_PDF_URL;
import static com.kampen.riksSe.utils.PreferencesUtility.POST_ACTIVITY_NOTIFICATION;
import static com.kampen.riksSe.utils.PreferencesUtility.REQUEST_CODE;
import static com.kampen.riksSe.utils.PreferencesUtility.SENSOR_STATIC_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.SENSOR_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.STEPS_DATE_TIME_TO_FETECH_HISTORY_FROM_GOOGLE_FIT;
import static com.kampen.riksSe.utils.PreferencesUtility.STEP_COUNTER_PERMISSION;
import static com.kampen.riksSe.utils.PreferencesUtility.STEP_COUNT_STOP_NOTIFY;
import static com.kampen.riksSe.utils.PreferencesUtility.STEP_FOREGROUND_SERVICE;
import static com.kampen.riksSe.utils.PreferencesUtility.STEP_FOREGROUND_SERVICE_DESTROY_STATUS;
import static com.kampen.riksSe.utils.PreferencesUtility.STEP_SESSION_DATE;
import static com.kampen.riksSe.utils.PreferencesUtility.STOP_WATCH_ON_PAUSE;
import static com.kampen.riksSe.utils.PreferencesUtility.STOP_WATCH_TIME;
import static com.kampen.riksSe.utils.PreferencesUtility.SYNCED_API_FIRST_TIME;
import static com.kampen.riksSe.utils.PreferencesUtility.TODAY_STEPS;
import static com.kampen.riksSe.utils.PreferencesUtility.TODAY_STEPS_DATE;
import static com.kampen.riksSe.utils.PreferencesUtility.TOKEN_PREF;
import static com.kampen.riksSe.utils.PreferencesUtility.USER_ID;
import static com.kampen.riksSe.utils.PreferencesUtility.REMMBER_ME;
import static com.kampen.riksSe.utils.PreferencesUtility.USER_STAR_CHASE_TRACK;
import static com.kampen.riksSe.utils.PreferencesUtility.WAIST_TODAY;
import static com.kampen.riksSe.utils.PreferencesUtility.WEIGHT_TODAY;
import static com.kampen.riksSe.utils.UtilityTz.convertStaticTimeToUTC;

public class SaveSharedPreference {



    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param //loggedIn
     */


    public static void setStopWatchTime(Context context, long StopWatchTime) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putLong(STOP_WATCH_TIME, StopWatchTime);
        editor.apply();
    }


    public static long getStopWatchTime(Context context){
        return getPreferences(context).getLong(STOP_WATCH_TIME, 0);
    }


    public static void setLoggedInSplash(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_SPLASH, loggedIn);
        editor.apply();
    }


    public static boolean getLoggedInSplash(Context context){
        return getPreferences(context).getBoolean(LOGGED_IN_SPLASH, true);
    }

    public static void setLocationPermissionBackground(Context context, boolean locationStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOCATION_PERMISSION_BACKGROUND, locationStatus);
        editor.apply();
    }

    public static boolean getLocationPermissionBackground(Context context){
        return getPreferences(context).getBoolean(LOCATION_PERMISSION_BACKGROUND, false);
    }

    public static void setLocationPermissionForeground(Context context, boolean locationStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOCATION_PERMISSION_FOREGROUND, locationStatus);
        editor.apply();
    }


    public static boolean getLocationPermissionForeground(Context context){
        return getPreferences(context).getBoolean(LOCATION_PERMISSION_FOREGROUND, false);
    }
    public static void setStepCounterPermission(Context context, boolean CameraStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(STEP_COUNTER_PERMISSION, CameraStatus);
        editor.apply();
    }

    public static boolean getStepCounterPermission(Context context){
        return getPreferences(context).getBoolean(STEP_COUNTER_PERMISSION, false);
    }
    public static void setStepsForeGroundService(Context context, boolean CameraStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(STEP_FOREGROUND_SERVICE, CameraStatus);
        editor.apply();
    }

    public static boolean getStepsForeGroundService(Context context){
        return getPreferences(context).getBoolean(STEP_FOREGROUND_SERVICE, false);
    }

    public static void setStepsForeGroundServiceDestroyStatus(Context context, boolean CameraStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(STEP_FOREGROUND_SERVICE_DESTROY_STATUS, CameraStatus);
        editor.apply();
    }

    public static boolean getStepsForeGroundServiceDestroyStatus(Context context){
        return getPreferences(context).getBoolean(STEP_FOREGROUND_SERVICE_DESTROY_STATUS, false);
    }

    public static void setStepsDateTimeToFetechHistoryFromGoogleFit(Context context,String dateTime){

        try {
            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putString(STEPS_DATE_TIME_TO_FETECH_HISTORY_FROM_GOOGLE_FIT,dateTime);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static String getStepsDateTimeToFetechHistoryFromGoogleFit(Context context){
        return getPreferences(context).getString(STEPS_DATE_TIME_TO_FETECH_HISTORY_FROM_GOOGLE_FIT,null);
    }

    public static void setStepCountingStopNotification(Context context, boolean CameraStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(STEP_COUNT_STOP_NOTIFY, CameraStatus);
        editor.apply();
    }

    public static boolean getStepCountingStopNotification(Context context){
        return getPreferences(context).getBoolean(STEP_COUNT_STOP_NOTIFY, false);
    }

    public static void setDrawOverAppsPermission(Context context, boolean DrawOverOtherAppsStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(DRAW_OVER_OTHER_APPS_PERMISSION, DrawOverOtherAppsStatus);
        editor.apply();
    }


    public static boolean getDrawOverAppsPermission(Context context){
        return getPreferences(context).getBoolean(DRAW_OVER_OTHER_APPS_PERMISSION, false);
    }

    public static void setCameraPermission(Context context, boolean CameraStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(CAMERA_PERMISSION, CameraStatus);
        editor.apply();
    }


    public static boolean getCameraPermission(Context context){
        return getPreferences(context).getBoolean(CAMERA_PERMISSION, false);
    }

    public static void setPostActivityNotificationStatus(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(POST_ACTIVITY_NOTIFICATION, loggedIn);
        editor.apply();
    }


    public static boolean getPostActivityNotificationStatus(Context context){
        return getPreferences(context).getBoolean(POST_ACTIVITY_NOTIFICATION, false);
    }

    public static void setLoggedInFCM(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_FCM, loggedIn);
        editor.apply();
    }


    public static boolean getLoggedInFCM(Context context){
        return getPreferences(context).getBoolean(LOGGED_IN_FCM, true);
    }


    public static void setSyncApiFirstTimeStatus(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(SYNCED_API_FIRST_TIME, loggedIn);
        editor.apply();
    }


    public static boolean getSyncApiFirstTimeStatus(Context context){
        return getPreferences(context).getBoolean(SYNCED_API_FIRST_TIME, true);
    }

    public static void setApplicationState(Context context, BaseActivity.ApplicationState applicationState) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(APP_STATE_PREF, applicationState.toString());
        editor.apply();
    }

    public static  BaseActivity.ApplicationState  getApplicationState(Context context) {


       return   BaseActivity.ApplicationState.toEnum(getPreferences(context).getString(APP_STATE_PREF, BaseActivity.ApplicationState.OnCreate_ENUM.toString()));
    }



    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static void setUsertodayStepsDate(Context context,String stepeDate){

        try {
            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putString(TODAY_STEPS_DATE,stepeDate);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static String getUserTodayStepsDate(Context context){
        return getPreferences(context).getString(TODAY_STEPS_DATE,"");
    }



    public static void setUsertodaySteps(Context context,int Steps){

        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(TODAY_STEPS,Steps);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static int getUserTodaySteps(Context context){
        return getPreferences(context).getInt(TODAY_STEPS, 0);
    }


    public static void setLastSchduleSlotID(Context context,int SlotID){

        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(LAST_SCHDULE_SLOT_ID,SlotID);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static int getLastSchduleSlotID(Context context){
        return getPreferences(context).getInt(LAST_SCHDULE_SLOT_ID, 0);
    }




    public static void setUserJourneySteps(Context context,int Steps){

        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(JOURNEY_STEPS,Steps);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static int getUserJourneySteps(Context context){
        return getPreferences(context).getInt(JOURNEY_STEPS, 0);
    }

    public static void setUserJourneyDistance(Context context,String Distance){

        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putString(JOURNEY_DISTANCE,Distance);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static String getUserJourneyDistance(Context context){
        return getPreferences(context).getString(JOURNEY_DISTANCE,"0");
    }

    public static void setUserJourneyCalories(Context context,String Calories){

        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putString(JOURNEY_CALORIES,Calories);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static String getUserJourneyCalories(Context context){
        return getPreferences(context).getString(JOURNEY_CALORIES, "0");
    }

    public static void setUserLastDaySteps(Context context,int Steps){

        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(LAST_DAY_STEPS,Steps);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static int getUserLastDaySteps(Context context){
        return getPreferences(context).getInt(LAST_DAY_STEPS , 0);
    }

    public static Boolean getChatActivityState(Context context){
        return getPreferences(context).getBoolean(CHAT_ACTIVTY_STATUS, false);
    }

    public static void setChatActivityState(Context context,Boolean chatStatus){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(CHAT_ACTIVTY_STATUS,chatStatus);
        editor.apply();}

    public static Boolean getAppKilledState(Context context){
        return getPreferences(context).getBoolean(APP_STATUS, false);
    }

    public static void setAppKilledState(Context context,Boolean appStatus){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(APP_STATUS,appStatus);
        editor.apply();}

    public static void setDeviceRestart(Context context, Boolean DeviceStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(DEVICE_STATUS,DeviceStatus);
        editor.apply();
    }

    public static Boolean getDeviceRestart(Context context) {
        return getPreferences(context).getBoolean(DEVICE_STATUS, false);
    }

    public static void setGoogleFitStatus(Context context, Boolean DeviceStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(GOOGLE_FIT_STATUS,DeviceStatus);
        editor.apply();
    }

    public static boolean getGoogleFitStatus(Context context) {
        return getPreferences(context).getBoolean(GOOGLE_FIT_STATUS, false);
    }

    public static void setGoogleFitTodayStepsSteps(Context context, int steps) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(GOOGLE_FIT_TODAY_STEPS,steps );
        editor.apply();
    }

    public static int getGoogleFitTodaySteps(Context context) {
        return getPreferences(context).getInt(GOOGLE_FIT_TODAY_STEPS, 0);
    }

    public static int getGoogleFitTodayStepsADD(Context context) {
        return getPreferences(context).getInt(GOOGLE_FIT_TODAY_STEPS_ADD, 0);
    }


    public static void setGoogleFitTodayStepsADD(Context context, int steps) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(GOOGLE_FIT_TODAY_STEPS_ADD,steps );
        editor.apply();
    }

    public static void setGoogleApi(Context context, boolean status) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(GOOGLE_API, status);
        editor.apply();
    }

    public static boolean getGoogleApi(Context context){
        return getPreferences(context).getBoolean(GOOGLE_API, false);
    }
    public static void setUserLogInSteps(Context context, int steps) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(LOGGED_IN_STEPS,steps );
        editor.apply();
    }

    public static int getUserLogInSteps(Context context) {
        return getPreferences(context).getInt(LOGGED_IN_STEPS, 0);
    }

    public static void setUserStarCount(Context context, int stars) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(CHASE_STAR,stars );
        editor.apply();
    }

    public static int getUserStarCount(Context context) {
        return getPreferences(context).getInt(CHASE_STAR, 0);
    }

    public static void setUserJourneyStarCount(Context context, int stars) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(JOURNEY_CHASE_STAR,stars );
        editor.apply();
    }

    public static int getUserJourneyStarCount(Context context) {
        return getPreferences(context).getInt(JOURNEY_CHASE_STAR, 0);
    }

    public static void setRequestCode(Context context, int requestCode) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(REQUEST_CODE,requestCode );
        editor.apply();
    }

    public static int getRequestCode(Context context) {
        return getPreferences(context).getInt(REQUEST_CODE, 0);
    }


    public static void setBaseURl(Context context,String baseURl){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(BASE_URL_SESSION,baseURl);
        editor.apply();
    }

    public static String getBaseURl(Context context) {
        return getPreferences(context).getString(BASE_URL_SESSION, BASE_PROD_URL);
    }

    public static void setApiSyncedDate(Context context,String date){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(API_SYNCED_DATE,date);
        editor.apply();
    }

    public static String getApiSyncedDate(Context context) {
        return getPreferences(context).getString(API_SYNCED_DATE,convertStaticTimeToUTC());
    }

    public static void setChatNotificationCounter(Context context,int chatNotifyCount){

        try {
            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(CHAT_COUNT,chatNotifyCount);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static int getChatNotificationCounter(Context context) {
        return getPreferences(context).getInt(CHAT_COUNT,0);
    }

    public static void setChatNotificationCounterCurrent(Context context,int chatNotifyCount){

        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putInt(CHAT_COUNT_CURRENT,chatNotifyCount);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static int getChatNotificationCounterCurrent(Context context) {
        return getPreferences(context).getInt(CHAT_COUNT_CURRENT,0);
    }

    public static void setChatNotifictationCounterZero(Context context,Boolean chatNotifyCount){
        try {

            SharedPreferences.Editor editor = getPreferences(context).edit();
            editor.putBoolean(CHAT_COUNT_ZERO,chatNotifyCount);
            editor.apply();
        }catch (Exception ex){

        }
    }

    public static Boolean getChatNotifictationCounterZero(Context context){
        return getPreferences(context).getBoolean(CHAT_COUNT_ZERO,false);
    }

    public static void setUserRemmberMe(Context context,Boolean remmberME){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(REMMBER_ME,remmberME);
        editor.apply();
    }

    public static Boolean getUserRemmberMe(Context context) {
        return getPreferences(context).getBoolean(REMMBER_ME,false);
    }

    public static void setUserFcmToken(Context context,String FcmToken){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(FCM_TOKEN,FcmToken);
        editor.apply();
    }

    public static String getUserFcmToken(Context context) {
        return getPreferences(context).getString(FCM_TOKEN, "");
    }

    public static void setAppVersion(Context context,String appVersion){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(APP_VERSION,appVersion);
        editor.apply();
    }

    public static String getAppVersion(Context context) {
        return getPreferences(context).getString(APP_VERSION, "");
    }

    public static void setNutritionPDF(Context context,String nutritionPdf){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(NUTRITION_PDF_URL,nutritionPdf);
        editor.apply();
    }

    public static String getNutritionPDF(Context context) {
        return getPreferences(context).getString(NUTRITION_PDF_URL, "");
    }
    public static void setDailyImage(Context context,String bitmapBase64String){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(DAILY_IMAGE,bitmapBase64String);
        editor.apply();
    }

    public static String getDailyImage(Context context) {
        return getPreferences(context).getString(DAILY_IMAGE, "");
    }


    public static void setLocation(Context context,String Location){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(DAILY_LOCATION,Location);
        editor.apply();
    }

    public static String getLocation(Context context) {
        return getPreferences(context).getString(DAILY_LOCATION, "");
    }


    public static void setDailyDate(Context context,String Date){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(DAILY_DATE,Date);
        editor.apply();
    }

    public static String getDailyDate(Context context) {
        return getPreferences(context).getString(DAILY_DATE, "");
    }


    public static void setWeightToday(Context context,String weight){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(WEIGHT_TODAY,weight);
        editor.apply();
    }

    public static String getWeightToday(Context context) {
        return getPreferences(context).getString(WEIGHT_TODAY, "N/A");
    }

    public static void setWaistToday(Context context,String waist){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(WAIST_TODAY,waist);
        editor.apply();
    }

    public static String getWaistToday(Context context) {
        return getPreferences(context).getString(WAIST_TODAY, "N/A");
    }


    public static void setUserLogInDate(Context context, String date) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LOGGED_IN_DATE, date);
        editor.apply();
    }

    public static String getUserLogInDate(Context context) {
        return getPreferences(context).getString(LOGGED_IN_DATE, "");
    }


    public static void setStepDaySessionDate(Context context, String date) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(STEP_SESSION_DATE, date);
        editor.apply();
    }

    public static String getStepDaySessionDate(Context context) {
        return getPreferences(context).getString(STEP_SESSION_DATE, "");
    }



    public static void setSensorSteps(Context context, int steps) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(SENSOR_STEPS,steps);
        editor.apply();
    }

    public static int getSensorSteps(Context context) {
        return getPreferences(context).getInt(SENSOR_STEPS, 0);
    }



    public static void setSensorStaticSteps(Context context, int steps) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(SENSOR_STATIC_STEPS,steps);
        editor.apply();
    }

    public static int getSensorStaticSteps(Context context) {
        return getPreferences(context).getInt(SENSOR_STATIC_STEPS, 0);
    }





    public static void setDistance(Context context,String distance){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(DISTANCE,distance);
        editor.apply();
    }

    public static String getDistance(Context context){
        return getPreferences(context).getString(DISTANCE,"");
    }



    public static void setStepsToSubstract(Context context, int steps) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(CHASE_STAR_STEPS,steps );
        editor.apply();
    }

    public static int getStepsToSubstract(Context context) {
        return getPreferences(context).getInt(CHASE_STAR_STEPS, 0);
    }

    public static void setStepsToSubstractCurrent(Context context, int steps) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(CHASE_STAR_STEPS_CURRENT,steps );
        editor.apply();
    }

    public static int getStepsToSubstractCurrent(Context context) {
        return getPreferences(context).getInt(CHASE_STAR_STEPS_CURRENT, 0);
    }


    public static long getUserPauseTime(Context context){
        return getPreferences(context).getLong(STOP_WATCH_ON_PAUSE,0);
    }

    public static void setUserPauseTime(Context context, long PauseTime){
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putLong(STOP_WATCH_ON_PAUSE,PauseTime );
        editor.apply();
    }



    public static void setLoggedIn(Context context, boolean loggedIn,String email,String pass) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.putString(LOGGED_IN_PREF_Email,email);
        editor.putString(LOGGED_IN_PREF_pass,pass);
        editor.apply();
    }



    public static boolean getLoggedUserTesterStatus(Context context){
        return getPreferences(context).getBoolean(LOGGED_TESTER_USER, false);
    }

    public static void setLoggedUserTesterStatus(Context context, boolean LoggedUserTesterStatus) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_TESTER_USER, LoggedUserTesterStatus);
        editor.apply();
    }


    public static void setLoggedInFirstTime(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_FIRST_TIME, loggedIn);
        editor.apply();
    }


    public static boolean getLoggedInFirstTime(Context context){
        return getPreferences(context).getBoolean(LOGGED_IN_FIRST_TIME, false);
    }




    public static void setLoggedInStepsFirstTime(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_STEPS_FIRST_TIME, loggedIn);
        editor.apply();
    }


    public static boolean getLoggedInStepsFirstTime(Context context){
        return getPreferences(context).getBoolean(LOGGED_IN_STEPS_FIRST_TIME, false);
    }

    public static void setLastKnownLocation(Context context, double   lat,double   lan) {

        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LAT_IN_PREF, lat+"");
        editor.putString(LAN_IN_PREF,lan+"");
        editor.apply();

    }


    public static LatLng getLastKnownLocation(Context context) {

        String lat= getPreferences(context).getString(LAT_IN_PREF, "0.0");
        String lan= getPreferences(context).getString(LAN_IN_PREF, "0.0");

        LatLng latLng=null;

        if(lat.equals("0.0") && lan.equals("0.0")) {

        }
        else
        {
            latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lan));
        }

        return  latLng;
    }



    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static String[] getLoggedParams(Context context) {
        String email= getPreferences(context).getString(LOGGED_IN_PREF_Email, "");
        String pass= getPreferences(context).getString(LOGGED_IN_PREF_pass, "");

        return  new String []{email,pass};
    }


    public static void saveUserToken(Context context, String token) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(TOKEN_PREF, token);
        editor.apply();
    }


    public static String getUserToken(Context context) {
        return getPreferences(context).getString(TOKEN_PREF, "");
    }



    public static void setcurrentDateCompitition(Context context, String currentDate) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(CURRENT_DATE_COMPITION, currentDate);
        editor.apply();
    }


    public static String getcurrentDateCompitition(Context context) {
        return getPreferences(context).getString(CURRENT_DATE_COMPITION, "");
    }


    public static void saveUserID(Context context, String token) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(USER_ID, token);
        editor.apply();
    }


    public static String getUserID(Context context) {
        return getPreferences(context).getString(USER_ID, "");
    }



}