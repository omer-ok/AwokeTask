package com.test.task.DI;


import com.test.task.PresenterLayer.APIService;

public class RemoteApiService implements Service {


    public  static final   String  NAME="Network";


    private APIService apiService;


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
