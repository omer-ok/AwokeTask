package com.kampen.riksSe.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.google.android.gms.maps.model.LatLng;
import com.kampen.riksSe.R;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.kampen.riksSe.utils.Constants.DATE_DAY_FORMAT;
import static com.kampen.riksSe.utils.Constants.DATE_FORMAT;
import static com.kampen.riksSe.utils.Constants.DATE_NUMBER_FORMAT;
import static com.kampen.riksSe.utils.Constants.DATE_TIME_FORMAT;
import static com.kampen.riksSe.utils.Constants.DATE_TIME_UI_FORMAT;
import static com.kampen.riksSe.utils.Constants.TIME_FORMAT;
import static com.kampen.riksSe.utils.Constants.TIME_FORMAT_HR_MIN;
import static com.kampen.riksSe.utils.Constants.TIME_HOUR;
import static com.kampen.riksSe.utils.Constants.TIME_MINIUTE;
import static com.kampen.riksSe.utils.Constants.TIME_MONTH;
import static com.kampen.riksSe.utils.Constants.TIME_SECOND;
import static com.kampen.riksSe.utils.Constants.TIME_YEAR;

public class UtilityTz {

    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;
    private static final long WEEK_MILLIS = 7 * DAY_MILLIS;
    private static final long MONTH_MILLIS = 4 * WEEK_MILLIS;
    private static final long YEAR_MILLIS = 12 * MONTH_MILLIS;

    final static double walkingFactor = 0.57;

    static double CaloriesBurnedPerMile;

    static double strip;

    static double stepCountMile; // step/mile

    static double conversationFactor;

    static double CaloriesBurned;

    public static final TimeZone utcTZ = TimeZone.getTimeZone("UTC");

    public static long toLocalTime(long time, TimeZone to) {
        return convertTime(time, utcTZ, to);
    }

    public static long toUTC(long time, TimeZone from) {
        return convertTime(time, from, utcTZ);
    }

    public static long convertTime(long time, TimeZone from, TimeZone to) {
        return time + getTimeZoneOffset(time, from, to);
    }



    public static String getAddressFromLatLong(Context context,LatLng latLng){
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0);
            return address;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static long getTimeZoneOffset(long time, TimeZone from, TimeZone to)    {
        int fromOffset = from.getOffset(time);
        int toOffset = to.getOffset(time);
        int diff = 0;

        if (fromOffset >= 0){
            if (toOffset > 0){
                toOffset = -1*toOffset;
            } else {
                toOffset = Math.abs(toOffset);
            }
            diff = (fromOffset+toOffset)*-1;
        } else {
            if (toOffset <= 0){
                toOffset = -1*Math.abs(toOffset);
            }
            diff = (Math.abs(fromOffset)+toOffset);
        }
        return diff;
    }
    public static ContestWeekDayTimeModel CompititionStartDateAndTimePopDaysHoursMinutes(String startDate, String endDate) {

        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date mstartDate = null;
        Date mendDate = null;
        try {
            mstartDate = format.parse(startDate);
            mendDate = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //milliseconds
        long different = mstartDate.getTime() - mendDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        //long weeksInMilli = daysInMilli * 7;

        /*long elapsedWeeks = different / weeksInMilli;
        different = different % weeksInMilli;*/

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();

        //contestWeekDayTimeModel.setWeeks(elapsedWeeks);
        contestWeekDayTimeModel.setDays(elapsedDays);
        contestWeekDayTimeModel.setHours(elapsedHours);
        contestWeekDayTimeModel.setMiniutes(elapsedMinutes);
        contestWeekDayTimeModel.setSeconds(elapsedSeconds);
        contestWeekDayTimeModel.setStratTimeInMilli(mstartDate.getTime());
        contestWeekDayTimeModel.setEndTimeInMilli(mendDate.getTime());

        return contestWeekDayTimeModel;
    /*    System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);*/
    }
    public static ContestWeekDayTimeModel CompititionStartDateAndTime(String startDate, String endDate) {

        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date mstartDate = null;
        Date mendDate = null;
        try {
            mstartDate = format.parse(startDate);
            mendDate = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //milliseconds
        long different = mstartDate.getTime() - mendDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long weeksInMilli = daysInMilli * 7;

        long elapsedWeeks = different / weeksInMilli;
        different = different % weeksInMilli;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();

        contestWeekDayTimeModel.setWeeks(elapsedWeeks);
        contestWeekDayTimeModel.setDays(elapsedDays);
        contestWeekDayTimeModel.setHours(elapsedHours);
        contestWeekDayTimeModel.setMiniutes(elapsedMinutes);
        contestWeekDayTimeModel.setSeconds(elapsedSeconds);
        contestWeekDayTimeModel.setStratTimeInMilli(mstartDate.getTime());
        contestWeekDayTimeModel.setEndTimeInMilli(mendDate.getTime());

        return contestWeekDayTimeModel;
    /*    System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);*/
    }

    public static ContestWeekDayTimeModel getWeekDayTime(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);

        LocalDate mstartDate = null;
        LocalDate mendDate = null;
        long numOfDaysBetween = 0;
        long numOfWeeksBetween = 0;
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                mstartDate= LocalDate.parse(startDate, dateTimeFormatter);
                mendDate= LocalDate.parse(endDate, dateTimeFormatter);
                numOfDaysBetween = ChronoUnit.DAYS.between(mstartDate, mendDate);
                numOfWeeksBetween = ChronoUnit.WEEKS.between(mstartDate, mendDate);
            }

            /*mstartDate = dateTimeFormatter.parse(startDate);
            mendDate = dateTimeFormatter.parse(endDate);*/
        } catch (Exception e) {
            e.printStackTrace();
        }



        ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();
        contestWeekDayTimeModel.setWeeks(numOfWeeksBetween);
        contestWeekDayTimeModel.setDays(numOfDaysBetween);
        /*contestWeekDayTimeModel.setHours(diffHours);
        contestWeekDayTimeModel.setMiniutes(diffMinutes);
        contestWeekDayTimeModel.setSeconds(diffSeconds);
        contestWeekDayTimeModel.setStratTimeInMilli(mstartDate.getTime());
        contestWeekDayTimeModel.setEndTimeInMilli(mendDate.getTime());*/

        return contestWeekDayTimeModel;
    }

    //Swedish
    public static String getTimeAgo(String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        String currentDateandTime = format.format(new Date());
        Date mstartDate=null;
        Date mcurrentDate=null;
        try {
            mstartDate = format.parse(timeStr);
            mcurrentDate = format.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = mstartDate.getTime();

        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = mcurrentDate.getTime();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "precis nu";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "för en minut sedan";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minuter sedan";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "en timme sedan";
        } else if (diff < (24 * HOUR_MILLIS)) {
            return diff / HOUR_MILLIS + " timmar sedan";
        } else if (diff < (48 * HOUR_MILLIS)) {
            return "i går";
        }else if(diff < (7 * DAY_MILLIS)){
            return diff / DAY_MILLIS + " dagar sedan";
        }
        else if(diff < (4 * WEEK_MILLIS)){
            return diff / WEEK_MILLIS + " vecka sedan";
        }
        else if(diff < 12 * MONTH_MILLIS){
            return diff / MONTH_MILLIS + " månader sedan";
        }
        else{
            return diff / YEAR_MILLIS + " år sedan";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static List<String> GetStartDateOfWeek(){
        // The French (France) week starts on MONDAY and ends on SUNDAY
        final ThisLocalizedWeek frenchWeek = new ThisLocalizedWeek(Locale.FRANCE);
        System.out.println(frenchWeek);


        System.out.println(frenchWeek.getFirstDay()); // 2018-01-15
        System.out.println(frenchWeek.getLastDay());  // 2018-01-21
        List<String> dates = new ArrayList();
        DateFormat df1 = new SimpleDateFormat(DATE_FORMAT);

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1 .parse(String.valueOf(frenchWeek.getFirstDay()));
            date2 = df1 .parse(String.valueOf(frenchWeek.getLastDay()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(df1.format(cal1.getTime()));
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }


    // English
/*    public static String getTimeAgo(String timeStr) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        String currentDateandTime = format.format(new Date());
        Date mstartDate=null;
        Date mcurrentDate=null;
        try {
            mstartDate = format.parse(timeStr);
            mcurrentDate = format.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = mstartDate.getTime();

        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000;
        }

        long now = mcurrentDate.getTime();
        if (time > now || time <= 0) {
            return null;
        }

        // TODO: localize
        final long diff = now - time;
        if (diff < MINUTE_MILLIS) {
            return "precis nu";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "för en minut sedan";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minuter sedan";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "en timme sedan";
        } else if (diff < (24 * HOUR_MILLIS)) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < (48 * HOUR_MILLIS)) {
            return "yesterday";
        }else if(diff < (7 * DAY_MILLIS)){
            return diff / DAY_MILLIS + " days ago";
        }
        else if(diff < (4 * WEEK_MILLIS)){
            return diff / WEEK_MILLIS + " week ago";
        }
        else if(diff < 12 * MONTH_MILLIS){
            return diff / MONTH_MILLIS + " months ago";
        }
        else{
            return diff / YEAR_MILLIS + " year ago";
        }
    }*/

    public static String timeAgo(String time_ago) {

        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        String currentDateandTime = format.format(new Date());
        Date mstartDate=null;
        Date mcurrentDate=null;
        try {
            mstartDate = format.parse(time_ago);
            mcurrentDate = format.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long cur_time = (Calendar.getInstance().getTimeInMillis()) / 1000;
        long time_elapsed = cur_time - mstartDate.getTime();
        long seconds = time_elapsed;
        int minutes = Math.round(time_elapsed / 60);
        int hours = Math.round(time_elapsed / 3600);
        int days = Math.round(time_elapsed / 86400);
        int weeks = Math.round(time_elapsed / 604800);
        int months = Math.round(time_elapsed / 2600640);
        int years = Math.round(time_elapsed / 31207680);

        // Seconds
        if (seconds <= 60) {
            return "just now";
        }
        //Minutes
        else if (minutes <= 60) {
            if (minutes == 1) {
                return "one minute ago";
            } else {
                return minutes + " minutes ago";
            }
        }
        //Hours
        else if (hours <= 24) {
            if (hours == 1) {
                return "an hour ago";
            } else {
                return hours + " hrs ago";
            }
        }
        //Days
        else if (days <= 7) {
            if (days == 1) {
                return "yesterday";
            } else {
                return days + " days ago";
            }
        }
        //Weeks
        else if (weeks <= 4.3) {
            if (weeks == 1) {
                return "a week ago";
            } else {
                return weeks + " weeks ago";
            }
        }
        //Months
        else if (months <= 12) {
            if (months == 1) {
                return "a month ago";
            } else {
                return months + " months ago";
            }
        }
        //Years
        else {
            if (years == 1) {
                return "one year ago";
            } else {
                return years + " years ago";
            }
        }
    }


    public static boolean DialogeBoxContestEndDate(Context context, Realm_User user){

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_box_contest_date_end_activity, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(promptsView);

        final TextView contestEnd = (TextView) promptsView.findViewById(R.id.textView1);

        final Button CancelBtn = (Button) promptsView.findViewById(R.id.cancelBtn);
        final Button okBtn = (Button) promptsView.findViewById(R.id.okBtn);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        // ContestWeekDayTimeModel contestWeekDayTimeModel =new ContestWeekDayTimeModel();

        Competition CompitionDate = Repository.getCompitionDate();

        Boolean ContestEndStatus = getCompitionStartDate(context,currentDateandTime,CompitionDate.getmEndDate());
        Boolean ContestEndStatus1 = getCompitionStartDate(context, "2020-03-09 00:00:00",CompitionDate.getmEndDate());
        if(ContestEndStatus1){
            contestEnd.setText(context.getResources().getString(R.string.General_PleaseUpdatePackage));
        }


        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //builder

                alertDialog.dismiss();
            }
        });

      /*  CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();
            }
        });*/
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

        return false;
    }


    public static Boolean getCompitionStartDate(Context context, String ContestStartDate, String CurrentDate){

        Date mContestStartDate= new Date();
        Date mCurrentDate = new Date();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.US);
             mContestStartDate = sdf.parse(ContestStartDate);
             mCurrentDate = sdf.parse(CurrentDate);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mCurrentDate.compareTo(mContestStartDate) >= 0) {
            //MyApplication.showSimpleSnackBarSucess(context,"Contest Started");
            return true;
        } else if (mCurrentDate.compareTo(mContestStartDate) < 0) {
            //MyApplication.showSimpleSnackBar(context,"Contest Not Started");
            return false;
        }
        return null;
    }

    public static String convertTimeToUTC(){

       //Date will return local time
        Date localTime = new Date();

        //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat(DATE_TIME_FORMAT);

        //getting GMT timezone, you can get any timezone e.g. UTC
        converter.setTimeZone(TimeZone.getTimeZone("GMT"));

        String outputText = converter.format(localTime);
        return outputText;
    }

    public static double CaloriesCalulatorFromSteps(double height ,double weight,double stepsCount){


        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);

        strip = height * 0.415;

        stepCountMile = 160934.4 / strip;

        conversationFactor = CaloriesBurnedPerMile / stepCountMile;

        CaloriesBurned = stepsCount * conversationFactor;

        return CaloriesBurned;
    }

    public static float getDistanceNow(long steps,double hightCM){
        double stepLenght = (hightCM * 0.415);
        //double stepLenght = (hightCM * 0.3619);
        float Distance = (float) (stepLenght * steps);
        return Distance/100;
    }

    public static String convertDateToUTC(){

        //Date will return local time
        Date localTime = new Date();

        //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat(DATE_FORMAT);

        //getting GMT timezone, you can get any timezone e.g. UTC
        converter.setTimeZone(TimeZone.getTimeZone("GMT"));

        String outputText = converter.format(localTime);

        return outputText;
    }

    public static File convertUriIntoFile(Uri uri) {
        File filePath = null;
        if (uri != null) {
            filePath =  new File(String.valueOf(uri));
        }
        return filePath;
    }

    public static String getDateWeekBefore(){

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTimeStr = sdf.format(new Date());

        Date currentDateandTime = null;
        try {
            currentDateandTime = sdf.parse(currentDateandTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDateandTime);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date WeekOldDate = calendar.getTime();

        return sdf.format(WeekOldDate);
    }

    public static String convertDynamicTimeToUTC(String Time){

        //Date will return local time
        //String Time ="2018-01-19 07:00:00";
        //Date localTime = "23:59:0";
        Date dt1 = null;
        //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat(DATE_TIME_FORMAT);

        try {
            dt1=converter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //getting GMT timezone, you can get any timezone e.g. UTC
        converter.setTimeZone(TimeZone.getTimeZone("GMT"));

        String outputText = converter.format(dt1);
        return outputText;
    }


    public static long convertDynamicTimeToUTCtoTimeMili(String Time){

        //Date will return local time
        //String Time ="2018-01-19 07:00:00";
        //Date localTime = "23:59:0";
        Date dt1 = null;
        //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat(DATE_TIME_FORMAT);

        try {
            dt1=converter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //getting GMT timezone, you can get any timezone e.g. UTC
        converter.setTimeZone(TimeZone.getTimeZone("GMT"));

      //  String outputText = converter.format(dt1);
        return dt1.getTime();
    }

    public static long convertDynamicTimeToTimeMili(String Time){

        //Date will return local time
        //String Time ="2018-01-19 07:00:00";
        //Date localTime = "23:59:0";
        Date dt1 = null;
        //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat(DATE_TIME_FORMAT);

        try {
            dt1=converter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //getting GMT timezone, you can get any timezone e.g. UTC
        converter.setTimeZone(TimeZone.getTimeZone("GMT"));

        //  String outputText = converter.format(dt1);
        return dt1.getTime();
    }

    public static String convertStaticTimeToUTC(){

        //Date will return local time
        String Time ="2018-01-19 07:00:00";
        //Date localTime = "23:59:0";
        Date dt1 = null;
        //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat(DATE_TIME_FORMAT);

        try {
            dt1=converter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //getting GMT timezone, you can get any timezone e.g. UTC
        converter.setTimeZone(TimeZone.getTimeZone("GMT"));

        String outputText = converter.format(dt1);
        return outputText;
    }

    public static String getDateAndTimeFromMiliSeconds(long miliseconds){
       //creating DateFormat for converting time from local timezone to GMT
        DateFormat converter = new SimpleDateFormat(DATE_TIME_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miliseconds);
        //converter.setTimeZone(TimeZone.getTimeZone("UTC"));

        String DateTime = converter.format(calendar.getTime());

        return DateTime;
    }


    public static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        //System.out.println(dateFormat.format(cal.getTime()));
        return dateFormat.format(cal.getTime());
    }


    public static String convertUTCToLocalTime(String utcTime){

        String ourdate = null;
        try {
            //Date will return local time
            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();
            String localTimeZone = tz.getDisplayName();

            SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(utcTime);

            TimeZone timeZone = TimeZone.getTimeZone(localTimeZone);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT); //this format changeable
            //dateFormatter.setTimeZone(timeZone);
            ourdate = dateFormatter.format(value);
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }

        return ourdate;
    }

    public static String convertTimeForUI(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(DATE_TIME_UI_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String convertDateTimeForUI(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(DATE_TIME_UI_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }
    public static String convertDateTimeForUIDay(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(DATE_DAY_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String convertDateTimeForUIDate(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(DATE_NUMBER_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String convertDateTimeToYear(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_YEAR);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }
    public static String convertDateTimeToMonth(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_MONTH);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }
    public static String convertDateTimeToDay(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(DATE_NUMBER_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String convertDateTimeToHour(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_HOUR);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String convertDateTimeToMiniute(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_MINIUTE);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String convertDateTimeToSecond(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_SECOND);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }


    public static String convertTimeFormat(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(DATE_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String convertDateTimeToTimeFormatUI(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_FORMAT_HR_MIN);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }
    public static String convertTimeToTimeFormatUI(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_FORMAT_HR_MIN);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }
    public static String convertDateTimeToTimeFormat(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatter.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatterUI.format(date);
        return ActivityDate;
    }

    public static String AddTimeinStaticTimeUI(String Time, int DurationInMin){
        SimpleDateFormat TimeFormatter = new SimpleDateFormat(TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(TIME_FORMAT_HR_MIN);
        Date date = null;
        String NewDateAdd = null;
        try {
            date = TimeFormatter.parse(Time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, DurationInMin);
            Date NewTime = calendar.getTime();
            NewDateAdd = dateFormatterUI.format(NewTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return NewDateAdd;
    }

    public static String AddTimeinStaticTime(String Time,int DurationInMin){
        SimpleDateFormat TimeFormatter = new SimpleDateFormat(TIME_FORMAT);

        Date date = null;
        String NewDateAdd = null;
        try {
            date = TimeFormatter.parse(Time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, DurationInMin);
            Date NewTime = calendar.getTime();
            NewDateAdd = TimeFormatter.format(NewTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return NewDateAdd;
    }

    public static String convertTimeDateTimeFormat(String Time){
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        SimpleDateFormat dateFormatterUI = new SimpleDateFormat(DATE_FORMAT);
        String ActivityDate=null;
        Date date = null;
        try {
            date = dateFormatterUI.parse(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ActivityDate = dateFormatter.format(date);
        return ActivityDate;
    }
    public static String convertUTCToLocalDate(String utcTime){

        String ourdate = null;
        try {
            //Date will return local time
            Calendar cal = Calendar.getInstance();
            TimeZone tz = cal.getTimeZone();
            String localTimeZone = tz.getDisplayName();

            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(utcTime);

            TimeZone timeZone = TimeZone.getTimeZone(localTimeZone);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT); //this format changeable
            //dateFormatter.setTimeZone(timeZone);
            ourdate = dateFormatter.format(value);
        } catch (ParseException ex) {
            Log.v("Exception", ex.getLocalizedMessage());
        }

        return ourdate;
    }
}
