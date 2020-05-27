package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.Contestant;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.Trainer;
import com.kampen.riksSe.api.remote_api.ResponseStatus;

import java.util.List;

import io.realm.Realm;
import retrofit2.Response;

import static com.kampen.riksSe.data_manager.Repository.STATUS_FAILED;

public class TrainerSchduleDB_Handler {


    public static   boolean SaveTrainrSchdule(List<ScheduledLiveVideoCall> scheduledLiveVideoCallList, Realm dataBase, Response<List<ScheduledLiveVideoCall>> response, final ResponseStatus responseStatus)
    {

        dataBase.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.delete(ScheduledLiveVideoCall.class);
                realm.delete(Contestant.class);
                realm.delete(Trainer.class);
                realm.insertOrUpdate(scheduledLiveVideoCallList);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                responseStatus.setCode(response.code());
                responseStatus.setMsg(response.message());
                responseStatus.setStatus("success");
                responseStatus.onResult(responseStatus);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

                responseStatus.setCode(response.code());
                responseStatus.setMsg(response.message());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

            }
        });
        return  false;
    }


    public static void DeleteSpecificSchdule( Realm dataBase,int SchduleID,final ResponseStatus responseStatus){
        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                ScheduledLiveVideoCall scheduledLiveVideoCallDB = dataBase.where(ScheduledLiveVideoCall.class)
                        .equalTo("id",SchduleID)
                        .findFirst();

                if(scheduledLiveVideoCallDB!=null){
                    scheduledLiveVideoCallDB.deleteFromRealm();
                }
            }
        });
    }


}
