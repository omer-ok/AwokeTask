package com.kampen.riksSe.leader.activity.fragments.home.nutrition;

import android.content.Context;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Days_V;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionMealOptionsDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DaysDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;

public class WeekNutrition_Frag_Presenter implements WeekNutritionContract.Presenter{


    private WeekNutritionContract.View mView;

    private Context mContext;


    public WeekNutrition_Frag_Presenter(WeekNutritionContract.View _view, Context context) {

        this.mView = _view;
        this.mContext = context;

    }


    public void GetStartUpData(Realm_User user) {


    }


    public Realm_User provideUserLocal(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        return user;
    }


    public List<DayNutritionMealOptionsDB> getNutritionCurrentMealOptions(List<N_WeekDB> nutritionData){
        N_DaysDB n_daysDB = new N_DaysDB();
        List<DayNutritionMealOptionsDB> dayNutritionMealOptionsDB =new ArrayList<>();
        if(nutritionData!=null){

            for(int i=0; i<nutritionData.size();i++){
                if(nutritionData.get(i).getCurrentWeek().equals("present")){
                    n_daysDB =nutritionData.get(i).getDays();
                }
            }
            if(n_daysDB!=null && n_daysDB.getDay1().getCurrentDay().equals("present")){
                dayNutritionMealOptionsDB = n_daysDB.getDay1().getDayNutritionList();
            }else if(n_daysDB!=null && n_daysDB.getDay2().getCurrentDay().equals("present")){
                dayNutritionMealOptionsDB = n_daysDB.getDay2().getDayNutritionList();
            }else if(n_daysDB!=null && n_daysDB.getDay3().getCurrentDay().equals("present")){
                dayNutritionMealOptionsDB = n_daysDB.getDay3().getDayNutritionList();
            }else if(n_daysDB!=null && n_daysDB.getDay4().getCurrentDay().equals("present")){
                dayNutritionMealOptionsDB = n_daysDB.getDay4().getDayNutritionList();
            }else if(n_daysDB!=null && n_daysDB.getDay5().getCurrentDay().equals("present")){
                dayNutritionMealOptionsDB = n_daysDB.getDay5().getDayNutritionList();
            }else if(n_daysDB!=null && n_daysDB.getDay6().getCurrentDay().equals("present")){
                dayNutritionMealOptionsDB = n_daysDB.getDay6().getDayNutritionList();
            }else if(n_daysDB!=null && n_daysDB.getDay7().getCurrentDay().equals("present")){
                dayNutritionMealOptionsDB = n_daysDB.getDay7().getDayNutritionList();
            }
        }

        return dayNutritionMealOptionsDB;
    }

  /*  public String getCurrentNutritionDay(){


        return;
    }*/

    public List<MealType> getNutritionMealTypeDataV3(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        Realm_User mUser  = provideUserLocal(context);
        Competition CompitionDate = Repository.getCompitionDate();
        if(CompitionDate.getStartDate()!=null) {
            ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime,convertUTCToLocalTime(CompitionDate.getStartDate()));
            long currentDay = contestWeekDayTimeModel.getDays() + 1;
            long currentWeek = contestWeekDayTimeModel.getWeeks() + 1;
            N_Plans nutritionPlans = Repository.getNutritionDataV3((int) currentWeek);
            List<Integer> mealTypeID = new ArrayList();
            List<MealType> nutritionMealType = null;
            for (int i = 0; i < nutritionPlans.getmDays().size(); i++) {
                if (currentWeek == nutritionPlans.getmDays().get(i).getmWeek() && currentDay == nutritionPlans.getmDays().get(i).getmDay()) {
                    for (int j = 0; j < nutritionPlans.getmDays().get(i).getmMeals().size(); j++) {
                        mealTypeID.add(nutritionPlans.getmDays().get(i).getmMeals().get(j).getMealId());
                    }
                    nutritionMealType = Repository.getNutritionMealTypeV3(mealTypeID);
                }
            }
            return nutritionMealType;
        }
        return null;
    }
    public N_Days_V getNutritionIngrdiantDataV3(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());
        Realm_User mUser  = provideUserLocal(context);
        Competition CompitionDate = Repository.getCompitionDate();
        if(CompitionDate.getStartDate()!=null) {
            ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, convertUTCToLocalTime(CompitionDate.getStartDate()));
            long currentDay = contestWeekDayTimeModel.getDays() + 1;
            long currentWeek = contestWeekDayTimeModel.getWeeks() + 1;
            N_Plans nutritionPlans = Repository.getNutritionDataV3((int) currentWeek);
            N_Days_V nutritionCurrentDay = null;
            for (int i = 0; i < nutritionPlans.getmDays().size(); i++) {
                if (currentWeek == nutritionPlans.getmDays().get(i).getmWeek() && currentDay == nutritionPlans.getmDays().get(i).getmDay()) {
                    for (int j = 0; j < nutritionPlans.getmDays().get(i).getmMeals().size(); j++) {
                        nutritionCurrentDay = nutritionPlans.getmDays().get(i);
                    }
                }
            }
            return nutritionCurrentDay;
        }
        return null;
    }

    public List<N_WeekDB> getNutritionData(Context context)
    {
        Realm_User  user=provideUserLocal(context);

        NutritiousDB nutritiousDB = Repository.getNutritionData(user.getId());
        ArrayList<N_WeekDB>  n_weekDBArrayList=new ArrayList<>();

        if(nutritiousDB!=null)
        {

            if(nutritiousDB.getWeekNutrition().getWeek1()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek1());


            if(nutritiousDB.getWeekNutrition().getWeek2()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek2());


            if(nutritiousDB.getWeekNutrition().getWeek3()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek3());


            if(nutritiousDB.getWeekNutrition().getWeek4()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek4());


            if(nutritiousDB.getWeekNutrition().getWeek5()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek5());


            if(nutritiousDB.getWeekNutrition().getWeek6()!=null)
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




    public N_WeekDB getNutritionData(Context context, int weekID) {
        Realm_User user = provideUserLocal(context);

        NutritiousDB nutritiousDB = Repository.getNutritionData(user.getId());
        N_WeekDB weelDB = null;

       // MyApplication.nutritiousDB=nutritiousDB;

        if (nutritiousDB != null) {

            switch (weekID) {
                case 1: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek1();
                    break;
                }
                case 2: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek2();
                    break;
                }

                case 3: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek3();
                    break;
                }

                case 4: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek4();
                    break;
                }

                case 5: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek5();
                    break;
                }

                case 6: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek6();
                    break;
                }

                case 7: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek7();
                    break;
                }

                case 8: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek8();
                    break;
                }

                case 9: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek9();
                    break;
                }

                case 10: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek10();
                    break;
                }

            }
        }

            return weelDB;

        }


    public List<N_WeekDB> getNutritionWeekData(Context context)

    {
        Realm_User  user=provideUserLocal(context);

        NutritiousDB nutritiousDB= Repository.getNutritionData(user.getId());
        ArrayList<N_WeekDB> n_weekDBArrayList=new ArrayList<>();
        if(nutritiousDB!=null)
        {

            if(nutritiousDB.getWeekNutrition().getWeek1()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek1());


            if(nutritiousDB.getWeekNutrition().getWeek2()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek2());


            if(nutritiousDB.getWeekNutrition().getWeek3()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek3());


            if(nutritiousDB.getWeekNutrition().getWeek4()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek4());


            if(nutritiousDB.getWeekNutrition().getWeek5()!=null)
                n_weekDBArrayList.add(nutritiousDB.getWeekNutrition().getWeek5());


            if(nutritiousDB.getWeekNutrition().getWeek6()!=null)
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


    @Override
    public void getNutritions() {




    }

    @Override
    public void getAllStartUpData(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        String token = "bearer " + user.getToken();


        Repository.getUserAllData(user.getId(), token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mView.setStartUpData(status.getMsg());
                }
                else
                {
                    mView.setStartUpDataFailed(status.getMsg());
                }


            }
        });

    }
}

