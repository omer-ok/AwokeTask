package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.ForgotPassword.Model.ForgotPasswordData;
import com.kampen.riksSe.ForgotPassword.Model.Result;
import com.kampen.riksSe.ForgotPassword.Model.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class ForgotPasswordDB_Handler {

    public static   boolean saveAllDataSynced(ForgotPasswordData forgotPasswordData, Realm dataBase)
    {



        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {





                realm.delete(Result.class);
                realm.delete(User.class);
                realm.insertOrUpdate(forgotPasswordData);



            }
        });

        return  false;
    }


    public static  User  getForgotPasswordData(Realm dataBase)
    {

        User user = null;

        final RealmResults<ForgotPasswordData> resultDB= dataBase.where(ForgotPasswordData.class)
                .findAll();


        if(resultDB !=null){

            for(int i = 0; i<resultDB.size();i++){
                user =resultDB.get(i).getResult();
            }

        }

        return  user;
    }

}
