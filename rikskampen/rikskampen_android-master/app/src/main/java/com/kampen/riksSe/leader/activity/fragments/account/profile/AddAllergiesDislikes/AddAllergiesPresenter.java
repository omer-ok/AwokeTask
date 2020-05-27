package com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes;

import android.content.Context;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.AllergiesDB_Handler;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AllergyV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.UserAllergyV3;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

import static com.bumptech.glide.util.Preconditions.checkNotNull;
import static com.kampen.riksSe.utils.UtilityTz.convertStaticTimeToUTC;

public class AddAllergiesPresenter implements AddAllergiesContract.Presenter{


    private final AddAllergiesContract.View mAllergiesView;
    private Context mContext;




    public AddAllergiesPresenter(AddAllergiesContract.View mAllergiesView, Context context) {
        mAllergiesView = checkNotNull(mAllergiesView);
        this.mAllergiesView = mAllergiesView;
        this.mContext = context;
    }



    public Realm_User provideUserLocal(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        return user;
    }


    @Override
    public void syncApiV3(Context context) {
        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        String token = "bearer " + user.getToken();

        String SyncDate = convertStaticTimeToUTC();

        Repository.SyncUserAllData(context,user.getId(), token,SyncDate, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mAllergiesView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {
                    mAllergiesView.setSyncSucessAPI(status.getMsg(),status.getNutritionPDFLINK());
                }
                else
                {
                    mAllergiesView.setSyncFailAPI(status.getMsg());
                }


            }
        });
    }

    @Override
    public void searchServerforAllergis(Context context, String Keyword) {

        Realm_User user = provideUserLocal(mContext);

        String  token = "bearer "+user.getToken();

        Repository.getAllergiesSearchedData(Keyword,user.getId(),token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mAllergiesView!=null && status.getCode()== HttpStatus.HTTP_OK  && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mAllergiesView.setSearchServerforAllergisSucess(status.getMsg(),status.getAllergiesName());

                }
                else
                {

                    mAllergiesView.setSearchServerforAllergisFailed(status.getMsg());

                }

            }
        });

    }

    @Override
    public void getAddAllergiesAllData(Context context) {


        Realm_User user = provideUserLocal(mContext);

        String  token = "bearer "+user.getToken();

        Repository.getAllergiesAllData(user.getId(),token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mAllergiesView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mAllergiesView.setUserAddedAllergiesSucess(status.getMsg());

                }
                else
                {

                    mAllergiesView.setUserAddedAllergiesFailed(status.getMsg());

                }
            }
        });
    }

    @Override
    public void addAllergiesServer(Context context, ArrayList<String> allergyID,ArrayList<String> other_allergy_name,ArrayList<String> deleted_allergy_id,ArrayList<String> other_deleted_allergy_id) {

        Realm_User user = provideUserLocal(mContext);

        String  token = "bearer "+user.getToken();

        Repository.ADDAllergiesAllData(user.getId(), allergyID, other_allergy_name, deleted_allergy_id, other_deleted_allergy_id,token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mAllergiesView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mAllergiesView.setAddAllergiesServerSucess(status.getMsg());

                }
                else
                {

                    mAllergiesView.setAddAllergiesServerFailed(status.getMsg());

                }

            }
        });

    }


    public RealmList<AllergyV3> getAllergiesAllDataLocal(Context context) {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        return AllergiesDB_Handler.getAllAllergiesData(mLocalService);


    }

    public RealmList<UserAllergyV3> getUserAllergiesAllDataLocal(Context context) {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        return AllergiesDB_Handler.getAllUserAlergiesData(mLocalService);


    }


}
