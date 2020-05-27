package com.kampen.riksSe.data_manager;

import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.api.remote_api.V2_api_model.AllHomeDataNew;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.api.remote_api.V2_api_model.Result;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateId;
import com.kampen.riksSe.api.remote_api.V2_api_model.TruncateTable;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.AllData;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities.Remote_DayActivity;
import com.kampen.riksSe.leader.activity.MainActivityContract;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayActivityList;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.ActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.MotivationVideos;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.VideoM;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Ingredient;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MainIngredient;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Recipe;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.MealIngrediantsOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_weeklyActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Day;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Video;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.WorkoutType;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.DayTainingDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_weeklyActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static android.content.Context.POWER_SERVICE;
import static com.kampen.riksSe.data_manager.Repository.API_HIT_FAILED;
import static com.kampen.riksSe.data_manager.Repository.STATUS_FAILED;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.convertDateToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class NutritionsAndTrainingsDB_Handler {





    public  static List<WeekTrainingModel> getNutritions(Realm realm,  String userEmail)
    {


        final RealmResults<WeekTrainingModel> NutritionsList = realm.where(WeekTrainingModel.class)
                .equalTo(Constants.USER_EMAIL_TAG,userEmail)

                .findAll();



        return  NutritionsList;

    }

    public  static void setDailyActivity(Realm realm, Remote_DayActivity remote_dayActivity, String userEmail)
    {

       /* DailyActivityModel dailyActivityModel= getDailyActivity(realm,remote_dayActivity.getDateTime(),userEmail);

        if(dailyActivityModel!=null)
        {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    dailyActivityModel.setSteps(remote_dayActivity.getSteps());
                    dailyActivityModel.setCaloriesBurned(remote_dayActivity.getCalories());
                    dailyActivityModel.setLat(remote_dayActivity.getLat());
                    dailyActivityModel.setLng(remote_dayActivity.getLng());
                    dailyActivityModel.setPlaceName(remote_dayActivity.getLocationName());
                    dailyActivityModel.setTakenTimeDate(remote_dayActivity.getDateTime());
                    dailyActivityModel.setPicData(remote_dayActivity.getImageData());

                    if(remote_dayActivity.getStars()!=null && remote_dayActivity.getStars().size()>0)
                    {


                        if(dailyActivityModel.getStars()!=null && dailyActivityModel.getStars().size()>0)
                        {
                            Constants.Sort_StarsModel(dailyActivityModel.getStars());
                            Constants.Sort_StarsRemote(remote_dayActivity.getStars());

                            for(int i=0; i<remote_dayActivity.getStars().size();i++) {

                                //Stars_Model stars_model=re
                            }
                        }
                        else
                        {

                        }


                }
            });


        }
        else
        {

        }*/



    }

    public static void saveAllActivitiesDataV3(Context context,Result allData, Realm dataBase){

        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try{

                    if(allData.getTruncateTable()!=null){
                        if(allData.getTruncateTable().isActivities()){
                            // DELETE ALL ACTIVIIES AND INSERT IF ANY COMEING FROM SERVER
                            realm.delete(ActivityDaily.class);
                        }
                    }

                    // OLD CODE IF TRUNCATE IS FALSE WILL CHECK FOR LOCAL ACTIVITY ENTRIES AND UPDATE WITH SERVER ENTRY IF ENTRY IS NOT FOUND WILL INSERT NEW ACTIVITY
                    if(allData.getTruncateId()!=null){
                        if(allData.getTruncateId().getActivities().size()>0){
                            for(int i=0;i<allData.getTruncateId().getActivities().size();i++){
                                final List<ActivityDaily> activiyIDList = realm.where(ActivityDaily.class)
                                        .equalTo("mId", allData.getTruncateId().getActivities().get(i))
                                        .findAll();
                                if (activiyIDList.size() > 0) {
                                    for (int j = 0; j < activiyIDList.size(); j++) {
                                        if (activiyIDList.get(j) != null) {
                                            activiyIDList.get(j).deleteFromRealm();
                                        }
                                    }
                                }
                            }
                        }
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                    SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    final List<ActivityDaily> AllActivitiesLocal = new ArrayList();

                    final List<ActivityDaily> activityDailyAllDataLocal= dataBase.where(ActivityDaily.class)
                            .findAll();
                    AllActivitiesLocal.addAll(activityDailyAllDataLocal);
                    if(AllActivitiesLocal.size()>0){
                        for(int i =0; i<allData.getActivities().size();i++){
                            Date ActivityDateTemp = sdf1.parse(convertUTCToLocalTime(allData.getActivities().get(i).getmDate()));
                            String ActivityDateFinal  = sdf.format(ActivityDateTemp);
                            Date ServerActivityDateandTimeStartRange = sdf1.parse(ActivityDateFinal + " 00:00:00");
                            Date ServerActivityDateandTimeEndRange = sdf1.parse(ActivityDateFinal + " 23:59:59");
                            int index = -1;
                            for(int j = 0; j<AllActivitiesLocal.size();j++){
                                Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(AllActivitiesLocal.get(j).getmDate()));
                                if((ActiviyDateLocal.after(ServerActivityDateandTimeStartRange) || ActiviyDateLocal.equals(ServerActivityDateandTimeStartRange)) && (ActiviyDateLocal.before(ServerActivityDateandTimeEndRange) || ActiviyDateLocal.equals(ServerActivityDateandTimeEndRange))) {
                                    index = j;
                                    break;
                                }
                            }
                            if(index>=0){
                                Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(AllActivitiesLocal.get(index).getmDate()));
                                Date ActivityServerDateTemp = sdf1.parse(convertUTCToLocalTime(allData.getActivities().get(i).getmDate()));
                                if(ActiviyDateLocal.equals(ActivityServerDateTemp)){
                                    AllActivitiesLocal.get(index).setmId(allData.getActivities().get(i).getmId());
                                    AllActivitiesLocal.get(index).setmLocationAddress(allData.getActivities().get(i).getmLocationAddress());
                                    AllActivitiesLocal.get(index).setmMediaImage(allData.getActivities().get(i).getmMediaImage());
                                    AllActivitiesLocal.get(index).setmSteps(allData.getActivities().get(i).getmSteps());
                                    AllActivitiesLocal.get(index).setmStars(allData.getActivities().get(i).getmStars());
                                    AllActivitiesLocal.get(index).setmCalories(allData.getActivities().get(i).getmCalories());
                                    AllActivitiesLocal.get(index).setmDistance(allData.getActivities().get(i).getmDistance());
                                    AllActivitiesLocal.get(index).setmDate(allData.getActivities().get(i).getmDate());
                                    AllActivitiesLocal.get(index).setmWeight(allData.getActivities().get(i).getmWeight());
                                    AllActivitiesLocal.get(index).setmWaist(allData.getActivities().get(i).getmWaist());
                                    AllActivitiesLocal.get(index).setmLongitude(allData.getActivities().get(i).getmLongitude());
                                    AllActivitiesLocal.get(index).setmLatitude(allData.getActivities().get(i).getmLatitude());
                                    AllActivitiesLocal.get(index).setSyncedWithServer(true);

                                    realm.insertOrUpdate(AllActivitiesLocal.get(index));
                                }

                            }else{
                                ActivityDaily activityDaily = new ActivityDaily();
                                activityDaily =allData.getActivities().get(i);
                                activityDaily.setSyncedWithServer(true);
                                realm.insertOrUpdate(activityDaily);
                            }
                        }
                    }else{
                        List<ActivityDaily> activityDailyList = new ArrayList();
                        activityDailyList.addAll(allData.getActivities());
                        if(activityDailyList.size()>0){
                            for(int i=0;i<activityDailyList.size();i++){
                                activityDailyList.get(i).setSyncedWithServer(true);
                            }
                        }
                        realm.insertOrUpdate(activityDailyList);
                    }


                }catch (Exception e){

                }

                }
            });
        }

    public static void saveAllActivitiesData(Context context,Result allData, Realm dataBase){

        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

             /*   PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
                wakeLock.acquire(20*60*1000L);*/

                try{
                    final List<ActivityDaily> activityDailyCurrentDayRecord = new ArrayList();

                    final List<ActivityDaily> activityDailyAllData= dataBase.where(ActivityDaily.class)
                            .findAll();

                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                    SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTimeStart = sdf.format(new Date());
                    String currentDateandTimeEnd = sdf.format(new Date());
                    for (int i = 0; i < activityDailyAllData.size(); i++){
                        Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart + " 00:00:00");
                        Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd + " 23:59:59");
                        Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(activityDailyAllData.get(i).getmDate()));
                        if ((ActiviyDateLocal.after(currentDateandTimeStartRange) || ActiviyDateLocal.equals(currentDateandTimeStartRange)) && (ActiviyDateLocal.before(currentDateandTimeEndRange) || ActiviyDateLocal.equals(currentDateandTimeEndRange))) {
                            activityDailyCurrentDayRecord.add(activityDailyAllData.get(i));
                        }
                    }

                    if(activityDailyCurrentDayRecord.size()>0){

                        try{
                            for(int i = 0; i<activityDailyCurrentDayRecord.size();i++){
                                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart+" 00:00:00");
                                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd+" 23:59:59");
                                Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(activityDailyCurrentDayRecord.get(i).getmDate()));
                                if ((ActiviyDateLocal.after(currentDateandTimeStartRange) || ActiviyDateLocal.equals(currentDateandTimeStartRange)) && (ActiviyDateLocal.before(currentDateandTimeEndRange) || ActiviyDateLocal.equals(currentDateandTimeEndRange))){
                                    for(int j =0; j<allData.getActivities().size();j++){
                                        Date ActiviyDateServer = sdf1.parse(convertUTCToLocalTime(allData.getActivities().get(j).getmDate()));
                                        if ((ActiviyDateServer.after(currentDateandTimeStartRange) || ActiviyDateServer.equals(currentDateandTimeStartRange)) && (ActiviyDateServer.before(currentDateandTimeEndRange) || ActiviyDateServer.equals(currentDateandTimeEndRange))) {

                                            activityDailyCurrentDayRecord.get(i).setmId(allData.getActivities().get(j).getmId());
                                            activityDailyCurrentDayRecord.get(i).setmLocationAddress(allData.getActivities().get(j).getmLocationAddress());
                                            if(allData.getActivities().get(j).getmMediaImage()!=null){
                                                activityDailyCurrentDayRecord.get(i).setmMediaImage(allData.getActivities().get(j).getmMediaImage());
                                            }else{
                                                activityDailyCurrentDayRecord.get(i).setmMediaImage(null);
                                            }
                                            activityDailyCurrentDayRecord.get(i).setmSteps(allData.getActivities().get(j).getmSteps());
                                            activityDailyCurrentDayRecord.get(i).setmStars(allData.getActivities().get(j).getmStars());
                                            activityDailyCurrentDayRecord.get(i).setmCalories(allData.getActivities().get(j).getmCalories());
                                            activityDailyCurrentDayRecord.get(i).setmDistance(allData.getActivities().get(j).getmDistance());
                                            activityDailyCurrentDayRecord.get(i).setmDate(allData.getActivities().get(j).getmDate());
                                            activityDailyCurrentDayRecord.get(i).setmWeight(allData.getActivities().get(j).getmWeight());
                                            activityDailyCurrentDayRecord.get(i).setmWaist(allData.getActivities().get(j).getmWaist());
                                            activityDailyCurrentDayRecord.get(i).setSyncedWithServer(true);

                                            realm.insertOrUpdate(activityDailyCurrentDayRecord.get(i));

                                        }else{
                                            //realm.insertOrUpdate(allData.getActivities());
                                        }
                                    }
                                }else{
                                    //realm.insertOrUpdate(allData.getActivities());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.i("RealmUpdateFail",e.toString());
                        }

                    }else{
                        realm.insertOrUpdate(allData.getActivities());
                    }

                }catch (Exception e){

                }

            }
        });

    }


    public static   boolean saveAllSyncedData(Context context, AllHomeDataNew allHomeDataNew, Result allData, Realm dataBase,final ResponseStatus responseStatus)
    {

        dataBase.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

               /* PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
                PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":MyWakelockTag");
                wakeLock.acquire(20*60*1000L);*/

                realm.delete(MealType.class);
                realm.insertOrUpdate(allData.getNutritionPlans().getMealTypes());

                realm.delete(WorkoutType.class);
                realm.insertOrUpdate(allData.getTrainingPlans().getmWorkoutTypes());

                realm.delete(Competition.class);
                realm.insertOrUpdate(allData.getCompetition());


                if(allData.getTruncateTable()!=null){
                    if(allData.getTruncateTable().isRecipesBooks()){
                        File file = new File(context.getExternalFilesDir(null), "Recipe");
                        File downloadedFile = new File(file.getAbsolutePath(), "RecipeBook");
                        if(downloadedFile.exists()){
                            downloadedFile.delete();
                        }
                        SaveSharedPreference.setNutritionPDF(context,allData.getNutritionPlanPDF());
                    }
                    if(allData.getTruncateTable().isMotivationalVideos()){
                        realm.delete(MotivationalVideo.class);
                    }
                    if(allData.getTruncateTable().isNutritionPlans()){
                        realm.delete(Ingredient.class);
                        realm.delete(Recipe.class);
                        realm.delete(MainIngredient.class);
                        realm.delete(Meal.class);
                        realm.delete(N_Days_V.class);
                        realm.delete(N_Plans.class);
                    }

                    if(allData.getTruncateTable().isWorkoutPlans()){
                        realm.delete(W_Video.class);
                        realm.delete(W_Day.class);
                        realm.delete(W_Plans.class);
                    }

                }

                if(allData.getTruncateId()!=null){
                    if(allData.getTruncateId().getMotivationalVideos().size()>0){
                        for(int i=0;i<allData.getTruncateId().getMotivationalVideos().size();i++){
                            final List<MotivationalVideo> motivationalVideo = realm.where(MotivationalVideo.class)
                                    .equalTo("mId", allData.getTruncateId().getMotivationalVideos().get(i))
                                    .findAll();
                            if (motivationalVideo.size() > 0) {
                                for (int j = 0; j < motivationalVideo.size(); j++) {
                                    if (motivationalVideo.get(j) != null) {
                                        motivationalVideo.get(j).deleteFromRealm();
                                    }
                                }
                            }
                        }
                    }
                    if(allData.getTruncateId().getNutritionPlans().size()>0){
                        for(int i=0;i<allData.getTruncateId().getNutritionPlans().size();i++){
                            final List<N_Days_V> nutrition= realm.where(N_Days_V.class)
                                    .equalTo(Constants.PLAN_ID,allData.getTruncateId().getNutritionPlans().get(i))
                                    .findAll();
                            if(nutrition.size()>0){
                                for (int k = 0; k < nutrition.size(); k++) {
                                    nutrition.get(k).deleteFromRealm();
                                }
                            }
                        }
                    }
                    if(allData.getTruncateId().getWorkoutPlans().size()>0){
                        for(int i=0;i<allData.getTruncateId().getWorkoutPlans().size();i++){
                            final List<W_Day> training= realm.where(W_Day.class)
                                    .equalTo(Constants.PLAN_ID,allData.getTruncateId().getWorkoutPlans().get(i))
                                    .findAll();
                            if(training.size()>0){
                                for(int k=0;k<training.size();k++){
                                    training.get(k).deleteFromRealm();
                                }
                            }
                        }
                    }

                }




                if(allData.getMotivationalVideos().size()>0){
                    for(int i=0;i<allData.getMotivationalVideos().size();i++) {
                        if (allData.getMotivationalVideos().get(i).getStatus().equals("deleted")) {
                            final List<MotivationalVideo> motivationalVideo = realm.where(MotivationalVideo.class)
                                    .equalTo("mId", allData.getMotivationalVideos().get(i).getId())
                                    .findAll();

                            if (motivationalVideo.size() > 0) {
                                for (int j = 0; j < motivationalVideo.size(); j++) {
                                    if (motivationalVideo.get(j) != null) {
                                        motivationalVideo.get(j).deleteFromRealm();
                                    }
                                }
                            }
                        }else {
                            realm.insertOrUpdate(allData.getMotivationalVideos().get(i));
                        }
                    }
                }else{

                }



                if(allData.getNutritionPlans().getPlans().size()>0){
                    for(int i=0;i<allData.getNutritionPlans().getPlans().size();i++){
                        for(int j=0;j<allData.getNutritionPlans().getPlans().get(i).getmDays().size();j++){
                            if(allData.getNutritionPlans().getPlans().get(i).getmDays().get(j).getStatus()!=null){
                                if(allData.getNutritionPlans().getPlans().get(i).getmDays().get(j).getStatus().equals("deleted")){
                                    final List<N_Days_V> nutrition= realm.where(N_Days_V.class)
                                            .equalTo(Constants.PLAN_ID,allData.getNutritionPlans().getPlans().get(i).getmDays().get(j).getmPlanId())
                                            .findAll();
                                    if(nutrition.size()>0){
                                        for (int k = 0; k < nutrition.size(); k++) {
                                            nutrition.get(k).deleteFromRealm();
                                        }
                                    }
                                }else{
                                    realm.insertOrUpdate(allData.getNutritionPlans().getPlans().get(i));
                                }
                            }
                        }
                    }
                }else{

                   //Log.i("NutritionPlan",allData.getNutritionPlans().getPlans().size()+"");

                }

                if(SaveSharedPreference.getSyncApiFirstTimeStatus(context)){
                    realm.insertOrUpdate(allData.getTrainingPlans().getmWPlans());
                    for(int i=0;i<allData.getTrainingPlans().getmWPlans().size();i++){
                        int numberOfdaysToBeDeleted = 0;
                        for(int j=0;j<allData.getTrainingPlans().getmWPlans().get(i).getmWDays().size();j++){
                            if(allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j).getStatus().equals("deleted")){
                                numberOfdaysToBeDeleted += 1;
                                final List<W_Day> training= realm.where(W_Day.class)
                                        .equalTo(Constants.PLAN_ID,allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j).getmPlanId())
                                        .findAll();

                                if(training.size()>0){
                                    for(int k=0;k<training.size();k++){
                                        training.get(k).deleteFromRealm();
                                    }
                                }
                            }
                        }
                        if(numberOfdaysToBeDeleted==7){
                            final W_Plans trainingweeks= realm.where(W_Plans.class)
                                    .equalTo("mWeek",allData.getTrainingPlans().getmWPlans().get(i).getmWeek())
                                    .findFirst();
                            if(trainingweeks!=null){
                                trainingweeks.deleteFromRealm();
                            }
                        }
                    }
                }else{
                    for(int i=0;i<allData.getTrainingPlans().getmWPlans().size();i++){
                        int numberOfdaysToBeDeleted = 0;
                        for(int j=0;j<allData.getTrainingPlans().getmWPlans().get(i).getmWDays().size();j++){
                            if(allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j).getStatus().equals("deleted")){
                                numberOfdaysToBeDeleted += 1;
                                final List<W_Day> training= realm.where(W_Day.class)
                                        .equalTo(Constants.PLAN_ID,allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j).getmPlanId())
                                        .findAll();

                                if(training.size()>0){
                                    for(int k=0;k<training.size();k++){
                                        training.get(k).deleteFromRealm();
                                    }
                                }
                            }else if(allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j).getStatus().equals("updated")){
                                final W_Plans training= realm.where(W_Plans.class)
                                        .equalTo("mWeek",allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j).getmWeek())
                                        .findFirst();

                                   Boolean dayFound=false;

                                       for(int f=0;f<training.getmWDays().size();f++){
                                           if(training.getmWDays().get(f).getmDay()==allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j).getmDay()){
                                               training.getmWDays().add(f,allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j));
                                               dayFound = true;
                                               break;
                                           }
                                       }
                                       if(!dayFound){
                                           training.getmWDays().add(allData.getTrainingPlans().getmWPlans().get(i).getmWDays().get(j));
                                           realm.insertOrUpdate(training);
                                       }
                            }else{
                                realm.insertOrUpdate(allData.getTrainingPlans().getmWPlans().get(i));
                            }
                        }
                        if(numberOfdaysToBeDeleted==7){
                            final W_Plans trainingweeks= realm.where(W_Plans.class)
                                    .equalTo("mWeek",allData.getTrainingPlans().getmWPlans().get(i).getmWeek())
                                    .findFirst();
                            if(trainingweeks!=null){
                                trainingweeks.deleteFromRealm();
                            }
                        }
                    }
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                SaveSharedPreference.setSyncApiFirstTimeStatus(context,false);
                if(allData.getTruncateId()!=null){
                    TruncateId truncateId =new TruncateId();
                    /*truncateId.setActivities(allData.getTruncateId().getActivities());
                    truncateId.setMotivationalVideos(allData.getTruncateId().getMotivationalVideos());
                    truncateId.setNutritionPlans(allData.getTruncateId().getWorkoutPlans());
                    truncateId.setWorkoutPlans(allData.getTruncateId().getMotivationalVideos());*/
                    truncateId.setActivities(null);
                    truncateId.setMotivationalVideos(null);
                    truncateId.setNutritionPlans(null);
                    truncateId.setWorkoutPlans(null);
                    responseStatus.setTruncateId(null);
                }
                if(allData.getTruncateTable()!=null){
                    TruncateTable truncateTable =new TruncateTable();
                    if(allData.getTruncateTable().isActivities()){
                        truncateTable.setActivities(false);
                    }
                    if(allData.getTruncateTable().isMotivationalVideos()){
                        truncateTable.setMotivationalVideos(false);
                    }
                    if(allData.getTruncateTable().isRecipesBooks()){
                        truncateTable.setRecipesBooks(false);
                    }
                    if(allData.getTruncateTable().isNutritionPlans()){
                        truncateTable.setNutritionPlans(false);
                    }
                    if(allData.getTruncateTable().isWorkoutPlans()){
                        truncateTable.setWorkoutPlans(false);
                    }
                    responseStatus.setTruncateTable(truncateTable);
                }

                responseStatus.setCode(allHomeDataNew.getCode());
                responseStatus.setMsg(allHomeDataNew.getMessage());
                responseStatus.setStatus(allHomeDataNew.getStatus());
                responseStatus.setNutritionPDFLINK(allData.getNutritionPlanPDF());
                responseStatus.onResult(responseStatus);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg("Local DB Not Synced");
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

            }
        });

        return  false;
    }

    public static   boolean saveAllDataSynced(AllData allData,Realm dataBase)
    {



        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.delete(ActivitiesDB.class);
                realm.delete(A_WeekDB.class);
                realm.delete(A_DayDB.class);
                realm.delete(MotivationVideos.class);
                realm.delete(VideoM.class);
                realm.delete(NutritiousDB.class);
                realm.delete(DayNutritionMealOptionsDB.class);
                realm.delete(MealIngrediantsOptionsDB.class);
                realm.delete(N_WeekDB.class);
                realm.delete(N_DayDB.class);
                realm.delete(N_weeklyActivitiesDB.class);
                realm.delete(TrainingsDB.class);
                realm.delete(T_WeekDB.class);
                realm.delete(T_DayDB.class);
                realm.delete(T_weeklyActivitiesDB.class);

                realm.insertOrUpdate(allData.getHomeData().getActivities());
                realm.insertOrUpdate(allData.getHomeData().getNutritious());
                realm.insertOrUpdate(allData.getHomeData().getTrainings());
                realm.insertOrUpdate(allData.getHomeData().getMotivationVideos());



            }
        });

        return  false;
}

    public static void mapRemoteNutritionToLocal(NutritiousDB nutritiousDB,AllData allData,Realm DB)
    {
        /* Nutritious remoteNutritions = allData.getHomeData().getNutritious();

        nutritiousDB.setUserID(remoteNutritions.getUserID());

         N_weeklyActivities weekNutrition=remoteNutritions.getWeekNutrition();

         //N_weeklyActivitiesDB weeklyActivitiesDB=DB.createObject(N_weeklyActivitiesDB.class);

         N_weeklyActivitiesDB weeklyActivitiesDB=new N_weeklyActivitiesDB();

         N_Week week1=weekNutrition.getWeek1();

         if(week1!=null)
         {
             N_WeekDB  n_weekDB=new N_WeekDB();
             n_weekDB.setImagePath(week1.getImagePath());
             n_weekDB.setWeekID(week1.getWeekID());
             n_weekDB.setWeekName(week1.getWeekName());

             N_DayDB  n_dayDB=new N_DayDB();

             n_weekDB.setDays();
         }

        weeklyActivitiesDB.setWeek1();

        nutritiousDB.setWeekNutrition();*/

    }

}
