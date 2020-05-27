package com.kampen.riksSe.data_manager;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.api.remote_api.Generic_Result;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.WeekTrainingModel;

import java.util.HashMap;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kampen.riksSe.data_manager.Repository.API_HIT_FAILED;
import static com.kampen.riksSe.data_manager.Repository.API_HIT_SUCCESS;
import static com.kampen.riksSe.data_manager.Repository.STATUS_FAILED;
import static com.kampen.riksSe.data_manager.Repository.STATUS_SUCCESS;
import static com.kampen.riksSe.data_manager.Repository.TOKEN;

public class TrainingAndNutritionRepository {







    public  static  void getNutritions(String token,final ResponseStatus responseStatus,String userId)
    {



            APIService mWebService;
            Realm mLocalService;

            mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
            mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

            HashMap<String,String> hm=new HashMap();
            hm.put(TOKEN,token);

            Call<Generic_Result<Object>> call = mWebService.getNutritions(hm);



            call.enqueue(new Callback<Generic_Result<Object>>() {
                @Override
                public void onResponse(Call<Generic_Result<Object>> call, Response<Generic_Result<Object>> response) {

                    Generic_Result<Object> obj = null;

                    obj = response.body();


                    if(obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                        responseStatus.setData(obj);
                    }

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);


                }

                @Override
                public void onFailure(Call<Generic_Result<Object>> call, Throwable t) {

                    t.toString();

                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(t.getMessage());
                    responseStatus.setStatus(STATUS_FAILED);

                    responseStatus.onResult(responseStatus);


                }
            });



              List<WeekTrainingModel> nutritions= NutritionsAndTrainingsDB_Handler.getNutritions(mLocalService,userId);


               responseStatus.setCode(API_HIT_SUCCESS);
               responseStatus.setMsg("");
               responseStatus.setStatus(STATUS_SUCCESS);
               responseStatus.setData(nutritions);
               responseStatus.onResult(responseStatus);



    }




    public  static  void    getTrainings()
    {



    }




}
