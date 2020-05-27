package com.kampen.riksSe.leader.activity.fragments.home.traings.workout;

import android.content.Context;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.Repository;

import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.WorkoutType;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeekWorkOutPresenter implements WeekWorkOutContract.Presenter{

    WeekWorkOutContract.View mView;

    private Context mContext;

    public   WeekWorkOutPresenter(WeekWorkOutContract.View  _view,Context context)
    {

        this.mView=_view;
        this.mContext=context;

    }

    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

    public List<W_Plans> getTrainingWeek(){
        List<W_Plans> trainingPlanWeekDataV3 = new ArrayList();
        trainingPlanWeekDataV3.addAll(Repository.getTraningDataV3());
        Collections.sort(trainingPlanWeekDataV3);
        return trainingPlanWeekDataV3;
    }

    public List<WorkoutType> getTrainingWorkOutType(){
        List<WorkoutType> trainingPlanWorkOutDataV3 = Repository.getTraningWorkOutTypeDataV3();
        return trainingPlanWorkOutDataV3;
    }


    public T_WeekDB getTraingData(Context context, int weekID) {
        Realm_User user = provideUserLocal(context);

        TrainingsDB nutritiousDB = Repository.getTrainingsData(user.getId());
        T_WeekDB weelDB = null;


        if (nutritiousDB != null) {

            switch (weekID) {
                case 1: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek1();
                    break;
                }
                case 2: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek2();
                    break;
                }

                case 3: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek3();
                    break;
                }

                case 4: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek4();
                    break;
                }

                case 5: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek5();
                    break;
                }

                case 6: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek6();
                    break;
                }

                case 7: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek7();
                    break;
                }

                case 8: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek8();
                    break;
                }

                case 9: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek9();
                    break;
                }

                case 10: {
                    weelDB = nutritiousDB.getWeeklyActivities().getWeek10();
                    break;
                }

            }
        }

        return weelDB;

    }


    public List<T_WeekDB> getTrainingsData(Context context)

    {
        Realm_User  user=provideUserLocal(context);

        TrainingsDB trainingsData= Repository.getTrainingsData(user.getId());
        ArrayList<T_WeekDB>  n_weekDBArrayList=new ArrayList<>();
        if(trainingsData!=null)
        {

            if(trainingsData.getWeeklyActivities().getWeek1()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek1());

            if(trainingsData.getWeeklyActivities().getWeek2()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek2());

            if(trainingsData.getWeeklyActivities().getWeek3()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek3());

            if(trainingsData.getWeeklyActivities().getWeek4()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek4());

            if(trainingsData.getWeeklyActivities().getWeek5()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek5());

            if(trainingsData.getWeeklyActivities().getWeek6()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek6());

            if(trainingsData.getWeeklyActivities().getWeek7()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek7());

            if(trainingsData.getWeeklyActivities().getWeek8()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek8());

            if(trainingsData.getWeeklyActivities().getWeek9()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek9());


            if(trainingsData.getWeeklyActivities().getWeek10()!=null)
                n_weekDBArrayList.add(trainingsData.getWeeklyActivities().getWeek10());
        }

        return n_weekDBArrayList;

    }


    @Override
    public void getStartUpData(Context context) {

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
