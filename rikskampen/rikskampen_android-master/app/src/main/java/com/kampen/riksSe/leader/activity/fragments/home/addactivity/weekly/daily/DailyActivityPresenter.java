package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily;

import android.content.Context;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayActivityList;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.A_WeekDB;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import io.realm.Realm;

public class DailyActivityPresenter implements DailyActivityContract.Presenter{

    private   DailyActivityContract.View   mView;

    private   Context  mContext;


    public   DailyActivityPresenter(DailyActivityContract.View  _view, Context context)
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


    public   static A_DayActivityList getDailyDetail(int weekId, int videoId)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        A_WeekDB a_weekDB=mLocalService.where(A_WeekDB.class)
                .equalTo("weekID",weekId+"")
                .findFirst();


        //  t_weekDB.getDays().

        A_DayDB a_dayDB=null;

        switch (weekId) {
            case 1: {
                a_dayDB = a_weekDB.getDays().getDay1();
                break;
            }
            case 2: {
                a_dayDB = a_weekDB.getDays().getDay2();
                break;
            }
            case 3: {
                a_dayDB = a_weekDB.getDays().getDay3();
                break;
            }
            case 4: {
                a_dayDB = a_weekDB.getDays().getDay4();
                break;
            }
            case 5: {
                a_dayDB = a_weekDB.getDays().getDay5();
                break;
            }
            case 6: {
                a_dayDB = a_weekDB.getDays().getDay6();
                break;
            }
            case 7: {
                a_dayDB = a_weekDB.getDays().getDay7();
                break;
            }

        }


        return   a_dayDB.getDayactivitesList().get(videoId);

    }


}
