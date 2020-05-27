package com.kampen.riksSe.data_manager;

import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.QuestionResponse;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.diaries;
import com.kampen.riksSe.leader.activity.modelV3.Question;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Response;

import static com.kampen.riksSe.data_manager.Repository.API_HIT_FAILED;
import static com.kampen.riksSe.data_manager.Repository.STATUS_FAILED;

public class DailyDairyDB_Handler {


    public static   boolean saveDailyDiaryTodayQuestion(List<Question> questionsList, Realm dataBase, Response<List<Question>> response, final ResponseStatus responseStatus)
    {

        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

             realm.insertOrUpdate(questionsList);

            }
        });

        dataBase.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                String currentDateandTime = sdf.format(new Date());

                final diaries diary= realm.where(diaries.class)
                        .equalTo("mDiary_Date",currentDateandTime)
                        .findFirst();

                if(diary==null){
                    diaries diariesobj=new diaries();
                    diariesobj.setMid(null);
                    diariesobj.setDayDescription(null);
                    diariesobj.setmDiary_Date(currentDateandTime);
                    diariesobj.setDayStatus(null);
                    RealmList<QuestionResponse> questionResponseList = new RealmList();
                    for(int i=0;i<questionsList.size();i++){
                        QuestionResponse questionResponse =new QuestionResponse();
                        questionResponse.setId(questionsList.get(i).getId());
                        questionResponse.setResponse(false);
                        questionResponseList.add(questionResponse);
                    }
                    diariesobj.setQuestions(questionResponseList);
                    realm.insertOrUpdate(diariesobj);
                }else{

                }
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

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(response.message());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

            }
        });


        return  false;
    }
    public static   boolean saveAllQuestionSynced(List<Question> questionsList, Realm dataBase)
    {
        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.insertOrUpdate(questionsList);

            }
        });
        return  false;
    }

    public static   boolean AddDailyDiary(List<diaries> diariesList, Realm dataBase)
    {

        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                /*SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_NEW);
                String currentDateandTime = sdf.format(new Date());

                final diaries diary = realm.where(diaries.class)
                        .equalTo("mDiary_Date",currentDateandTime)
                        .findFirst();

                if(diary!=null){
                    realm.insertOrUpdate(diariesList);
                }
*/
                realm.insertOrUpdate(diariesList);

            }
        });
        return  false;
    }

    public static   boolean UpdateDailyDiary(diaries diariesList, Realm dataBase)
    {

        dataBase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
               /* SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                String currentDateandTime = sdf.format(new Date());

                final diaries diary = realm.where(diaries.class)
                        .equalTo("mDiary_Date",currentDateandTime)
                        .findFirst();

                if(diary!=null){
                    realm.insertOrUpdate(diariesList);
                }*/
                realm.insertOrUpdate(diariesList);
            }
        });
        return  false;
    }

    public static   boolean getAllDailyDiary(List<diaries> diariesList, Realm dataBase,Response<List<diaries>> response, final ResponseStatus responseStatus)
    {

        dataBase.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.insertOrUpdate(diariesList);

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


}
