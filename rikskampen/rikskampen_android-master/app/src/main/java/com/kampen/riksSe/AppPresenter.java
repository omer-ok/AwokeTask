package com.kampen.riksSe;

import android.content.Context;

import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

public class AppPresenter implements BasePresenter{



    public Realm_User provideUserLocal(Context context)
    {

        String [] params=SaveSharedPreference.getLoggedParams(context);

        Realm_User user=Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }



    public void  updateUserLocal(Realm_User user)
    {

            Repository.updateUserLocal(user);

    }

}
