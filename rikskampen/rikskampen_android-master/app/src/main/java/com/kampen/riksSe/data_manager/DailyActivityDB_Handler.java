package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities.Remote_DayActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.utils.Constants;

import io.realm.Realm;
import io.realm.RealmResults;

public class DailyActivityDB_Handler {





    public  static DailyActivityModel getDailyActivity(Realm realm,String date, String userEmail)
    {

        DailyActivityModel obj=null;

        final RealmResults<DailyActivityModel> dailyActivityModel = realm.where(DailyActivityModel.class)
                .equalTo(Constants.USER_EMAIL_TAG,userEmail)
                .and()
                .equalTo(Constants.DAILY_ACTIVITY_DATE_TAG,date)
                .findAll();

        if(dailyActivityModel.size() > 0)
        {
            obj=dailyActivityModel.get(0);
        }


        return  obj;

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


}
