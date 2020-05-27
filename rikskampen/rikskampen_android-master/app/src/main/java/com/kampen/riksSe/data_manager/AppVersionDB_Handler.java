package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.UpdateAppVersion.AppUpdate;

import io.realm.Realm;
import io.realm.RealmResults;

public class AppVersionDB_Handler {

    public static void saveAppVersionUpdate(AppUpdate appUpdate, Realm dataBase) {

        try{
            dataBase.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    realm.delete(AppUpdate.class);
                    realm.insertOrUpdate(appUpdate);

                }
            });
        }catch (Exception e){

        }

    }



}
