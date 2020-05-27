package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly;

import android.content.Context;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.QuestionResponceModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.ActivitiesDB;
import com.kampen.riksSe.leader.activity.modelV3.Question;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.vov.vitamio.utils.Log;

import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeFormat;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.getCompitionStartDate;

public class WeeklyActivityPresenter implements  WeeklyActivityContract.Presenter{

    private WeeklyActivityContract.View mView;

    private Context mContext;


    public WeeklyActivityPresenter(WeeklyActivityContract.View _view, Context context) {

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


    public A_WeekDB getActivitiesData(Context context, int weekID) {
        Realm_User user = provideUserLocal(context);

        ActivitiesDB activitiesDB = Repository.getActivitiesData(user.getId());
        A_WeekDB weelDB = null;
        ArrayList<A_WeekDB>  n_weekDBArrayList=new ArrayList<>();

        if (activitiesDB != null) {

            switch (weekID) {
                case 1: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek1();

                    break;
                }
                case 2: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek2();
                    break;
                }

                case 3: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek3();
                    break;
                }

                case 4: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek4();
                    break;
                }

                case 5: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek5();
                    break;
                }

                case 6: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek6();
                    break;
                }

                case 7: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek7();
                    break;
                }

                case 8: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek8();
                    break;
                }

                case 9: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek9();
                    break;
                }

                case 10: {
                    weelDB = activitiesDB.getWeeklyActivities().getWeek10();
                    break;
                }

            }
        }

        return weelDB;

    }

    public List<activityAdapterListModel> getActivitySyncDayDetailData(Context context,int weekID){
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        Realm_User mUser = provideUserLocal(context);
        SimpleDateFormat sdf3 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        List<ActivityDaily> activityDailySyncList = Repository.getActivitiesSyncData();
        List<activityAdapterListModel> activityDailySyncSortedList = new ArrayList();
        List<activityAdapterListModel> activityDailySyncWeekDaysSortedList = new ArrayList();
        try {
            Competition CompitionDate = Repository.getCompitionDate();

            if(CompitionDate.getStartDate()!=null) {
                //Boolean ContestStatus = getCompitionStartDate(mContext, CompitionDate.getStartDate(), currentDateandTime);
                for (int i = 0; i < activityDailySyncList.size(); i++) {
                    Date ActivityDateandTime = sdf3.parse(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                    Date CurrentDateandTime = sdf3.parse(convertUTCToLocalTime(CompitionDate.getStartDate()));
                    if (ActivityDateandTime.after(CurrentDateandTime)) {

                        activityAdapterListModel activityAdapterListModel1 = new activityAdapterListModel();
                        ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()), convertUTCToLocalTime(CompitionDate.getStartDate()));
                        activityAdapterListModel1.setmDate(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                        activityAdapterListModel1.setmWeek(contestWeekDayTimeModel.getWeeks() + 1);
                        activityAdapterListModel1.setmDay(contestWeekDayTimeModel.getDays() + 1);
                        activityAdapterListModel1.setmMediaImage(activityDailySyncList.get(i).getmMediaImage());
                        activityAdapterListModel1.setmId(activityDailySyncList.get(i).getmId());
                        activityAdapterListModel1.setmSteps(activityDailySyncList.get(i).getmSteps());
                        activityAdapterListModel1.setmStars(activityDailySyncList.get(i).getmStars());
                        activityAdapterListModel1.setmDistance(activityDailySyncList.get(i).getmDistance());
                        activityAdapterListModel1.setmWeight(activityDailySyncList.get(i).getmWeight());
                        activityAdapterListModel1.setmWaist(activityDailySyncList.get(i).getmWaist());
                        activityAdapterListModel1.setmCalories(activityDailySyncList.get(i).getmCalories());
                        activityAdapterListModel1.setCaloriesUnit(activityDailySyncList.get(i).getCaloriesUnit());
                        activityAdapterListModel1.setDistanceUnit(activityDailySyncList.get(i).getDistanceUnit());
                        activityAdapterListModel1.setmLocationAddress(activityDailySyncList.get(i).getmLocationAddress());

                        activityDailySyncSortedList.add(activityAdapterListModel1);
                    }
                }
            }
            Collections.sort(activityDailySyncSortedList);
            for(int i=0; i<activityDailySyncSortedList.size();i++) {
                if (activityDailySyncSortedList.get(i).getmWeek() == weekID) {
                    activityDailySyncWeekDaysSortedList.add(activityDailySyncSortedList.get(i));
                }
            }
        }catch (Exception e){

            Log.i("ExceptionDate",e.toString());
        }
        Collections.sort(activityDailySyncWeekDaysSortedList);

        for(int i=0;i<activityDailySyncWeekDaysSortedList.size();i++){

            activityDailySyncWeekDaysSortedList.get(i).setDailyDiaryQuestionModel(Repository.getWeeklyDiary(convertTimeFormat(activityDailySyncWeekDaysSortedList.get(i).getmDate())));
        }
            return activityDailySyncWeekDaysSortedList;
    }

    public List<QuestionResponceModel> getTodayQuestion(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        List<Question> diaryTodayQuestion = mLocalService.where(Question.class)
                .findAll();
        List<QuestionResponceModel>  questionResponceModels = new ArrayList();
        if(diaryTodayQuestion!=null){

            for(int i=0; i<diaryTodayQuestion.size();i++){
                QuestionResponceModel questionResponceModel =new QuestionResponceModel();
                questionResponceModel.setQuestion(diaryTodayQuestion.get(i).getQuestion());
                questionResponceModel.setmResponse(false);
                questionResponceModels.add(questionResponceModel);
            }

            return questionResponceModels;
        }


        return null;
    }

    public static ArrayList<A_DayDB> getActivyDayDetail(int weekId)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        A_WeekDB a_weekDB=mLocalService.where(A_WeekDB.class)
                .equalTo("weekID",weekId+"")
                .findFirst();


        //  t_weekDB.getDays().
        ArrayList<A_DayDB> a_dayDBArrayList = new ArrayList<>();
        A_DayDB a_dayDB1=null;
        A_DayDB a_dayDB2=null;
        A_DayDB a_dayDB3=null;
        A_DayDB a_dayDB4=null;
        A_DayDB a_dayDB5=null;
        A_DayDB a_dayDB6=null;
        A_DayDB a_dayDB7=null;


        a_dayDB1 = a_weekDB.getDays().getDay1();
        a_dayDB2 = a_weekDB.getDays().getDay2();
        a_dayDB3 = a_weekDB.getDays().getDay3();
        a_dayDB4 = a_weekDB.getDays().getDay4();
        a_dayDB5 = a_weekDB.getDays().getDay5();
        a_dayDB6 = a_weekDB.getDays().getDay6();
        a_dayDB7 = a_weekDB.getDays().getDay7();
        a_dayDBArrayList.add(a_dayDB1);
        a_dayDBArrayList.add(a_dayDB2);
        a_dayDBArrayList.add(a_dayDB3);
        a_dayDBArrayList.add(a_dayDB4);
        a_dayDBArrayList.add(a_dayDB5);
        a_dayDBArrayList.add(a_dayDB6);
        a_dayDBArrayList.add(a_dayDB7);



        return   a_dayDBArrayList;

    }

    @Override
    public void getActivitiesData() {

    }
}
