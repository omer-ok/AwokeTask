package com.kampen.riksSe.data_manager;


import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.CaloriesDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StarsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.StepsDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.UserDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserCalories;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserData;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserStars;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.UserSteps;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;
import com.kampen.riksSe.utils.Constants;


import io.realm.Realm;
import io.realm.RealmResults;

public class LeaderBoardDB_Handler {

    public static   boolean saveLeaderBoardAllDataSynced(LeaderBoardAllData leaderBoardAllData, Realm dataBase)
    {



        dataBase.executeTransaction(realm -> {



            realm.delete(LeaderBoardAllData.class);
            realm.delete(UserData.class);
            realm.delete(UserSteps.class);
            realm.delete(UserStars.class);
            realm.delete(UserCalories.class);

            realm.insertOrUpdate(leaderBoardAllData);




        });

        return  false;
    }
    public static   boolean saveLeaderBoardAllDataSyncedV3(LeaderBoardAllDataV3 leaderBoardAllDataV3, Realm dataBase)
    {



        dataBase.executeTransaction(realm -> {


            realm.delete(LeaderBoardAllDataV3.class);
            realm.delete(UserDataV3.class);
            realm.delete(StepsDataV3.class);
            realm.delete(StarsDataV3.class);
            realm.delete(CaloriesDataV3.class);

            if(leaderBoardAllDataV3.getmCompetition()!=null){
                realm.delete(Competition.class);
                realm.insertOrUpdate(leaderBoardAllDataV3.getmCompetition());
            }
            realm.insertOrUpdate(leaderBoardAllDataV3);




        });

        return  false;
    }
    public static  LeaderBoardAllDataV3  getAllLeaderBoardDataV3(Realm dataBase)
    {
        LeaderBoardAllDataV3 leaderBoardAllDataV3=null;

        final RealmResults<LeaderBoardAllDataV3> leaderBoardAllDataListV3= dataBase.where(LeaderBoardAllDataV3.class)
                .findAll();

        if(leaderBoardAllDataListV3!=null)
        {
            leaderBoardAllDataV3=leaderBoardAllDataListV3.get(0);
        }

        return  leaderBoardAllDataV3;
    }

     public static  LeaderBoardAllData  getAllLeaderBoardData(Realm dataBase)
     {
         LeaderBoardAllData leaderBoardAllData=null;

         final RealmResults<LeaderBoardAllData> leaderBoardAllDataList= dataBase.where(LeaderBoardAllData.class)
                 .findAll();

         if(leaderBoardAllDataList!=null)
         {
             leaderBoardAllData=leaderBoardAllDataList.get(0);
         }

         return  leaderBoardAllData;
     }

}
