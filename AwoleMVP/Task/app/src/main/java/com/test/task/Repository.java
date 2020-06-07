package com.test.task;

import android.util.Log;

import com.facebook.stetho.server.http.HttpStatus;
import com.test.task.DI.RemoteApiService;
import com.test.task.DI.ServiceLocator;
import com.test.task.Model.Flash;
import com.test.task.Model.ResponseStatus;
import com.test.task.PresenterLayer.APIService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {



    public  static    void   getFlashAllData(final ResponseStatus responseStatus)
    {
        APIService mWebService;


        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        Call<Flash> call = mWebService.getFlashData();


        call.enqueue(new Callback<Flash>() {
            @Override
            public void onResponse(Call<Flash> call, Response<Flash> response) {

                Flash obj = null;

                obj = response.body();

                if(obj!=null && obj.getSTATUS().getCODE()== HttpStatus.HTTP_OK  && obj.getSTATUS().getMESSAGE().equals("OK")) {

                    responseStatus.setCode(obj.getSTATUS().getCODE());
                    responseStatus.setMsg(obj.getSTATUS().getMESSAGE());
                    responseStatus.setStatus(obj.getSTATUS().getMESSAGE());
                    responseStatus.setFlash(obj);
                    responseStatus.onResult(responseStatus);

                }
                else
                {
                    responseStatus.setCode(obj.getSTATUS().getCODE());
                    responseStatus.setMsg(obj.getSTATUS().getMESSAGE());
                    responseStatus.setStatus(obj.getSTATUS().getMESSAGE());
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<Flash> call, Throwable t) {

                t.toString();

                Log.i(t.toString(), "onFailure: ");

                responseStatus.setCode(400);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus("Failed");
                responseStatus.onResult(responseStatus);

                Log.i("API Failed MSG :: ",t.getMessage());

            }
        });

    }



    public  static    void   getHomeAllData(String PageNo,final ResponseStatus responseStatus)
    {
        APIService mWebService;


        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();

        HashMap<String,String> hm=new HashMap();
        hm.put("PAGED", PageNo);

        Call<Flash> call = mWebService.getHomeData(hm);


        call.enqueue(new Callback<Flash>() {
            @Override
            public void onResponse(Call<Flash> call, Response<Flash> response) {

                Flash obj = null;

                obj = response.body();

                if(obj!=null && obj.getSTATUS().getCODE()== HttpStatus.HTTP_OK  && obj.getSTATUS().getMESSAGE().equals("OK")) {

                    responseStatus.setCode(obj.getSTATUS().getCODE());
                    responseStatus.setMsg(obj.getSTATUS().getMESSAGE());
                    responseStatus.setStatus(obj.getSTATUS().getMESSAGE());
                    responseStatus.setFlash(obj);
                    responseStatus.onResult(responseStatus);

                }
                else
                {
                    responseStatus.setCode(obj.getSTATUS().getCODE());
                    responseStatus.setMsg(obj.getSTATUS().getMESSAGE());
                    responseStatus.setStatus(obj.getSTATUS().getMESSAGE());
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<Flash> call, Throwable t) {

                t.toString();

                Log.i(t.toString(), "onFailure: ");

                responseStatus.setCode(400);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus("Failed");
                responseStatus.onResult(responseStatus);

                Log.i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

}
