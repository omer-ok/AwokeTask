package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanData;
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanDetails;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class PlansDB_Handler {

    public static   boolean saveAllDataSynced(PlanData plans, Realm dataBase)
    {



        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {





                realm.delete(PlanData.class);
                realm.delete(PlanDetails.class);
                realm.insertOrUpdate(plans);



            }
        });

        return  false;
    }


    public static  RealmList<PlanDetails>  getAllPlanData(Realm dataBase)
    {

        RealmList<PlanDetails> planDetailsRealmList = null;

        final RealmResults<PlanData> planDetailsRealmResults= dataBase.where(PlanData.class)
                .findAll();


        if(planDetailsRealmResults !=null){

            planDetailsRealmList= planDetailsRealmResults.get(0).getPlanDetailsList();
        }

        return  planDetailsRealmList;
    }

}
