package com.kampen.riksSe.leader.activity.fragments.plans;

import android.content.Context;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.data_manager.PlansDB_Handler;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanDetails;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;

import io.realm.Realm;
import io.realm.RealmList;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

public class PlanPresenter implements PlanContract.Presenter{


    private final PlanContract.View mPlanView;
    private Context mContext;




    public PlanPresenter(PlanContract.View mPlanView, Context context) {
        mPlanView = checkNotNull(mPlanView);
        this.mPlanView = mPlanView;
        this.mContext = context;
    }


    public Realm_User provideUserLocal(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        return user;
    }

    @Override
    public void getAllPlanData() {

        Realm_User user = provideUserLocal(mContext);

        String  token = "bearer "+user.getToken();

        Repository.getPlanData(user.getId(),token, new ResponseStatus() {
            @Override
            public void onResult(ResponseStatus status) {


                if(mPlanView!=null && status.getCode()== HttpStatus.HTTP_OK && status.getStatus().equals(Repository.STATUS_SUCCESS))
                {

                    mPlanView.setPlanSucessfull(status.getMsg());

                }
                else
                {

                    mPlanView.setPlanFailded(status.getMsg());

                }

            }
        });

    }


    public RealmList<PlanDetails> getPlansAllDataLocal(Context context) {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        return PlansDB_Handler.getAllPlanData(mLocalService);


    }




}
