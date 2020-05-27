package com.kampen.riksSe.leader.activity.fragments.home.traings;

import android.content.Context;
import android.util.Log;

import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.data_manager.TrainingAndNutritionRepository;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;

public class Plans_Frag_Presenter {




      private   Plans_Frag_Contract.View   mView;

      private   Context  mContext;


      public   Plans_Frag_Presenter(Plans_Frag_Contract.View  _view,Context context)
        {

            this.mView=_view;
            this.mContext=context;

        }


        public void GetStartUpData(Realm_User user ){




        }


        public Realm_User provideUserLocal(Context context)
        {

            String [] params=SaveSharedPreference.getLoggedParams(context);

            Realm_User user=Repository.provideUserLocal(params[0],params[1]);

            return  user;
        }

        public String getRecipeFilePath(Context context){
            Realm_User  user=provideUserLocal(context);

            NutritiousDB nutritiousDB = Repository.getNutritionData(user.getId());

            String RecipeSummary = null;

            if(nutritiousDB!=null) {

                if (nutritiousDB.getRecipeSchedule() != null) {

                    RecipeSummary = nutritiousDB.getRecipeSchedule();
                }
            }
            return RecipeSummary;
        }


        public List<DayNutritionMealOptionsDB> getNutritionCurrentMealOptions(List<N_WeekDB> nutritionData){
            N_DaysDB n_daysDB = new N_DaysDB();
            List<DayNutritionMealOptionsDB> dayNutritionMealOptionsDB =new ArrayList<>();
            if(nutritionData!=null){

                for(int i=0; i<nutritionData.size();i++){
                    if(nutritionData.get(i).getCurrentWeek().equals("present")){
                        n_daysDB = nutritionData.get(i).getDays();
                    }
                }
                if(n_daysDB!=null){

                    if(n_daysDB.getDay1()!=null && n_daysDB.getDay1().getCurrentDay().equals("present")){
                        dayNutritionMealOptionsDB = n_daysDB.getDay1().getDayNutritionList();
                    }else if(n_daysDB.getDay2()!=null && n_daysDB.getDay2().getCurrentDay().equals("present")){
                        dayNutritionMealOptionsDB = n_daysDB.getDay2().getDayNutritionList();
                    }else if(n_daysDB.getDay3()!=null && n_daysDB.getDay3().getCurrentDay().equals("present")){
                        dayNutritionMealOptionsDB = n_daysDB.getDay3().getDayNutritionList();
                    }else if(n_daysDB.getDay4()!=null && n_daysDB.getDay4().getCurrentDay().equals("present")){
                        dayNutritionMealOptionsDB = n_daysDB.getDay4().getDayNutritionList();
                    }else if(n_daysDB.getDay5()!=null && n_daysDB.getDay5().getCurrentDay().equals("present")){
                        dayNutritionMealOptionsDB = n_daysDB.getDay5().getDayNutritionList();
                    }else if(n_daysDB.getDay6()!=null && n_daysDB.getDay6().getCurrentDay().equals("present")){
                        dayNutritionMealOptionsDB = n_daysDB.getDay6().getDayNutritionList();
                    }else if(n_daysDB.getDay7()!=null && n_daysDB.getDay7().getCurrentDay().equals("present")){
                        dayNutritionMealOptionsDB = n_daysDB.getDay7().getDayNutritionList();
                    }

                }


            }

            return dayNutritionMealOptionsDB;
        }

    public List<MealType> getNutritionMealTypeDataV3(Context context,String CompitionStartDate){
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        Realm_User mUser  = provideUserLocal(context);
        ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, CompitionStartDate);
        //ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime("2019-11-19 00:00:00", mUser.getContestStartDate());
        long currentDay = contestWeekDayTimeModel.getDays()+1;
        long currentWeek = contestWeekDayTimeModel.getWeeks()+1;
        N_Plans nutritionPlans = Repository.getNutritionDataV3((int) currentWeek);
        List<Integer> mealTypeID = new ArrayList();
        List<N_Days_V> nutritionCurrentDay = new ArrayList();
        List<MealType> nutritionMealType = null;
        if(nutritionPlans!=null){
            if(nutritionPlans.getmDays()!=null){
                for(int i=0;i<nutritionPlans.getmDays().size();i++){
                    if(currentWeek==nutritionPlans.getmDays().get(i).getmWeek() && currentDay==nutritionPlans.getmDays().get(i).getmDay()){
                        for(int j=0;j<nutritionPlans.getmDays().get(i).getmMeals().size();j++){
                            mealTypeID.add(nutritionPlans.getmDays().get(i).getmMeals().get(j).getMealId());
                        }
                        nutritionMealType = Repository.getNutritionMealTypeV3(mealTypeID);
                    }
                }
            }
        }

          return nutritionMealType;
    }

    public List<MealType> getTrainingDataV3(Context context){

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        Realm_User mUser  = provideUserLocal(context);
        ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, mUser.getContestStartDate());
        N_Plans nutritionPlans = Repository.getNutritionDataV3((int) contestWeekDayTimeModel.getWeeks());
        List<Integer> mealTypeID = new ArrayList();
        List<N_Days_V> nutritionCurrentDay = new ArrayList();
        List<MealType> nutritionMealType = null;
        for(int i=0;i<nutritionPlans.getmDays().size();i++){
            if(contestWeekDayTimeModel.getWeeks()==nutritionPlans.getmDays().get(i).getmWeek() && contestWeekDayTimeModel.getDays()==nutritionPlans.getmDays().get(i).getmDay()){
                for(int j=0;j<nutritionPlans.getmDays().get(i).getmMeals().size();j++){
                    mealTypeID.add(nutritionPlans.getmDays().get(i).getmMeals().get(j).getMealId());
                }
                nutritionMealType = Repository.getNutritionMealTypeV3(mealTypeID);
            }
        }
        return nutritionMealType;
    }

    public List<W_Plans> getTrainingWeek(Context context,String CompitionStartDate){
          List<W_Plans> trainingPlanWeekDataV3 =new ArrayList();
          trainingPlanWeekDataV3.addAll(Repository.getTraningDataV3());
          Collections.sort(trainingPlanWeekDataV3);
          return trainingPlanWeekDataV3;
    }


    public List<N_WeekDB> getNutritionData(Context context)
    {
        Realm_User  user=provideUserLocal(context);

        NutritiousDB nutritiousDB = Repository.getNutritionData(user.getId());
        ArrayList<N_WeekDB>  n_weekDBArrayList=new ArrayList<>();

        if(nutritiousDB!=null && nutritiousDB.getWeekNutrition()!=null)
        {

            if(nutritiousDB.getWeekNutrition().getWeek1()!=null )
            n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek1());


            if(nutritiousDB.getWeekNutrition().getWeek2()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek2());


            if(nutritiousDB.getWeekNutrition().getWeek3()!=null )
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek3());


            if(nutritiousDB.getWeekNutrition().getWeek4()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek4());


            if(nutritiousDB.getWeekNutrition().getWeek5()!=null )
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek5());


            if(nutritiousDB.getWeekNutrition().getWeek6()!=null )
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek6());


            if(nutritiousDB.getWeekNutrition().getWeek7()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek7());


            if(nutritiousDB.getWeekNutrition().getWeek8()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek8());


            if(nutritiousDB.getWeekNutrition().getWeek9()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek9());


            if(nutritiousDB.getWeekNutrition().getWeek10()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek10());



        }

        return n_weekDBArrayList;

    }



    public List<T_WeekDB> getTrainingsData(Context context)

    {
        Realm_User  user=provideUserLocal(context);

        TrainingsDB trainingsData= Repository.getTrainingsData(user.getId());
        ArrayList<T_WeekDB>  n_weekDBArrayList=new ArrayList<>();
        if(trainingsData!=null)
        {

            if(trainingsData.getWeeklyActivities().getWeek1()!=null && trainingsData.getWeeklyActivities().getWeek1().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek1());

            if(trainingsData.getWeeklyActivities().getWeek2()!=null && trainingsData.getWeeklyActivities().getWeek2().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek2());

            if(trainingsData.getWeeklyActivities().getWeek3()!=null && trainingsData.getWeeklyActivities().getWeek3().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek3());

            if(trainingsData.getWeeklyActivities().getWeek4()!=null && trainingsData.getWeeklyActivities().getWeek4().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek4());

            if(trainingsData.getWeeklyActivities().getWeek5()!=null && trainingsData.getWeeklyActivities().getWeek5().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek5());

            if(trainingsData.getWeeklyActivities().getWeek6()!=null && trainingsData.getWeeklyActivities().getWeek6().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek6());

            if(trainingsData.getWeeklyActivities().getWeek7()!=null && trainingsData.getWeeklyActivities().getWeek7().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek7());

            if(trainingsData.getWeeklyActivities().getWeek8()!=null && trainingsData.getWeeklyActivities().getWeek8().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek8());

            if(trainingsData.getWeeklyActivities().getWeek9()!=null && trainingsData.getWeeklyActivities().getWeek9().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek9());


            if(trainingsData.getWeeklyActivities().getWeek10()!=null && trainingsData.getWeeklyActivities().getWeek10().getDays()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek10());
        }

        return n_weekDBArrayList;

    }




    public  void  getUserTrainings()
            {

                Realm_User user=provideUserLocal(mContext);

                TrainingAndNutritionRepository.getNutritions(user.getToken(), new ResponseStatus() {
                    @Override
                    public void onResult(ResponseStatus status) {


                        if(mView!=null)
                        {
                            mView.setNutritions((List<WeekTrainingModel>) status.getData());
                        }


                    }
                },user.getId());


            }



}
