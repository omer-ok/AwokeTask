package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail;

import android.content.Context;

import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Day;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Video;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.WeekWorkOutContract;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.List;

public class VideoPlayPresenter implements VideoPlayContract.Presenter{


    VideoPlayContract.View mView;

    private Context mContext;

    public   VideoPlayPresenter(VideoPlayContract.View  _view,Context context)
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
    public List<W_Video> getTrainingVideo(int planID, int videoID){
        W_Day wDay = Repository.getTraningDayDataV3(planID);
        return wDay.getmWVideos();
    }


   /* public T_WeekDB getTraingData(Context context, int weekID) {
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

    }*/

    @Override
    public T_WeekDB getTraingRelatedVideData(Context context, int weekID) {
        Realm_User user = provideUserLocal(context);

        TrainingsDB trainingsDataDB = Repository.getTrainingsData(user.getId());
        T_WeekDB weelDB = null;


        if (trainingsDataDB != null) {

            switch (weekID) {
                case 1: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek1();
                    break;
                }
                case 2: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek2();
                    break;
                }

                case 3: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek3();
                    break;
                }

                case 4: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek4();
                    break;
                }

                case 5: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek5();
                    break;
                }

                case 6: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek6();
                    break;
                }

                case 7: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek7();
                    break;
                }

                case 8: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek8();
                    break;
                }

                case 9: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek9();
                    break;
                }

                case 10: {
                    weelDB = trainingsDataDB.getWeeklyActivities().getWeek10();
                    break;
                }

            }
        }

        return weelDB;
    }
}
