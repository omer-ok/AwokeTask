package com.kampen.riksSe.DI;

import com.kampen.riksSe.api.remote_api.APIService;

public class RemoteApiService implements Service {


    public  static final   String  NAME="Network";


    private APIService  apiService;


    public String getName() {
        return NAME;
    }



    public RemoteApiService getApiObject() {

        /*RemoteApiService remoteApiService= new RemoteApiService();
        remoteApiService.setApiService(API_Provider.provideApi());*/
       return  null;

    }


    public APIService getApiService() {
        return apiService;
    }

    public void setApiService(APIService apiService) {
        this.apiService = apiService;
    }
}
