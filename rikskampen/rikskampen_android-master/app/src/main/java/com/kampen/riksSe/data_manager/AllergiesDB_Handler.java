package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.Model.AddAllergiesData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.Model.AddAllergiesDetails;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.Alergy;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.AlergyResultData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.AllergyData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.UserAlergies;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AlergyResultDataV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.UserAllergyV3;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class AllergiesDB_Handler {

    public static   boolean saveAllDataSynced(AllergyData allergyData, Realm dataBase)
    {



        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {





                realm.delete(AllergyData.class);
                realm.delete(AlergyResultData.class);
                realm.delete(Alergy.class);
                realm.delete(UserAlergies.class);
                realm.insertOrUpdate(allergyData);



            }
        });

        return  false;
    }
    public static   boolean saveSearchedDataSynced(AlergyResultDataV3 alergyResultDataV3, Realm dataBase)
    {



        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {



                realm.delete(AllergyV3.class);
                realm.delete(UserAllergyV3.class);
                realm.delete(AlergyResultDataV3.class);
                realm.insertOrUpdate(alergyResultDataV3);



            }
        });

        return  false;
    }


    public static  RealmList<AddAllergiesDetails>  getAllAllergiesData1(Realm dataBase)
    {

        RealmList<AddAllergiesDetails> allergiesDetailsRealmList = null;

        final RealmResults<AddAllergiesData> AllergiesDetailsRealmResults= dataBase.where(AddAllergiesData.class)
                .findAll();


        if(AllergiesDetailsRealmResults !=null){

            allergiesDetailsRealmList= AllergiesDetailsRealmResults.get(0).getAllergiesList();
        }

        return  allergiesDetailsRealmList;
    }

    public static  RealmList<AllergyV3>  getAllAllergiesData(Realm dataBase) {

        RealmList<AllergyV3> allergiesDetailsRealmList = new RealmList<>();

        final RealmResults<AllergyV3> AllergiesDetailsRealmResults = dataBase.where(AllergyV3.class)
                .findAll();

        //RealmList<UserAllergyV3> userAllergiesDetailsRealmList = new RealmList<>();

        final RealmResults<UserAllergyV3> UserAllergiesList = dataBase.where(UserAllergyV3.class)
                .findAll();


        if (AllergiesDetailsRealmResults != null) {


            for (int i = 0; i < AllergiesDetailsRealmResults.size(); i++) {
                AllergyV3 allergyV3 = new AllergyV3();
                allergyV3.setRecentlySelected(false);
                allergyV3.setStatus("SystemAllrgies");
                allergyV3.setTitle(AllergiesDetailsRealmResults.get(i).getTitle());
                allergyV3.setAllergyId(AllergiesDetailsRealmResults.get(i).getAllergyId());
                allergiesDetailsRealmList.add(allergyV3);
            }
            for (int i = 0; i < UserAllergiesList.size(); i++) {
                AllergyV3 allergyV3 = new AllergyV3();
                allergyV3.setRecentlySelected(false);
                allergyV3.setStatus("UserAllergies");
                allergyV3.setTitle(UserAllergiesList.get(i).getTitle());
                allergyV3.setAllergyId(UserAllergiesList.get(i).getAllergyId());
                allergiesDetailsRealmList.add(allergyV3);
            }

            return allergiesDetailsRealmList;
        }
        return null;
    }

    public static  RealmList<UserAllergyV3>  getAllUserAllergiesData(Realm dataBase)
    {

        RealmList<UserAllergyV3> allergiesDetailsRealmList = new RealmList<>();

        final RealmResults<UserAllergyV3> AllergiesDetailsRealmResults= dataBase.where(UserAllergyV3.class)
                .findAll();


        if(AllergiesDetailsRealmResults !=null){

            allergiesDetailsRealmList.addAll(AllergiesDetailsRealmResults);
        }

        return  allergiesDetailsRealmList;
    }


    public static  RealmList<UserAllergyV3>  getAllUserAlergiesData(Realm dataBase)
    {

        RealmList<UserAllergyV3> allergiesDetailsRealmList = null;

        final RealmResults<UserAllergyV3> AllergiesDetailsRealmResults= dataBase.where(UserAllergyV3.class)
                .findAll();


        if(AllergiesDetailsRealmResults !=null){

            allergiesDetailsRealmList.addAll(AllergiesDetailsRealmResults);
        }

        return  allergiesDetailsRealmList;
    }

}
