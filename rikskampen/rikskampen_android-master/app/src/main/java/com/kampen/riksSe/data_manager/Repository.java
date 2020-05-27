package com.kampen.riksSe.data_manager;

import androidx.annotation.NonNull;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kampen.riksSe.ForgotPassword.Model.ForgotPasswordData;
import com.kampen.riksSe.ChangePassword.ModelN.ChangePasswordData;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.UpdateAppVersion.AppUpdate;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.api.remote_api.V2_api_model.AllHomeDataNew;
import com.kampen.riksSe.api.remote_api.V2_api_model.Competition;
import com.kampen.riksSe.api.remote_api.V2_api_model.SyncTable;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.BadgeCountResult;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.AllergyResponce;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AddAllergiesV3Data;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AlergyResultDataV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.diaries;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.BookSchduleSlots;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.SchduleSlots;
import com.kampen.riksSe.leader.activity.fragments.chat.Model.ChatData;
import com.kampen.riksSe.leader.activity.fragments.chat.Model.ChatDetails;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.adapter.adapterListModel.activityAdapterListModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.AddUpdateDailyDiaryToServerV3;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DailyDiaryQuestionModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DiariesUpdateServer;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.MotivationalVideo;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.PastActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.QuestionResponceModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.ActivitiesDB;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.Add_Activity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.MotivationVideos;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.MealType;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.N_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Day;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Plans;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.WorkoutType;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.DayTainingDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.TrainingsDB;
import com.facebook.stetho.server.http.HttpStatus;


import org.json.JSONArray;
import org.json.JSONObject;

import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIUrl;
import com.kampen.riksSe.api.remote_api.Generic_Result;
import com.kampen.riksSe.api.remote_api.ResponseStatus;
import com.kampen.riksSe.login.ModelsV3.LoginResultModel;
import com.kampen.riksSe.login.ModelsV3.RemoteUser;
import com.kampen.riksSe.api.remote_api.models.RemoteUserResult;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.AllData;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.DailyActivityModel;
import com.kampen.riksSe.leader.activity.fragments.map.Modal.UserJourneyData;
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanData;
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanDetails;
import com.kampen.riksSe.leader.activity.modelV3.Question;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.login.ModelsV3.UserRoles;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceResponceModel.PayExInvoiceResponceData;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PayExInvoiceSendData;
import com.kampen.riksSe.payment.PayEx.PayExResponceModels.PayExAllResponceData;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.PayExSendData;
import com.kampen.riksSe.payment.PayEx.PromoCodeModels.PromoCodeData;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.util.Log.i;
import static com.kampen.riksSe.utils.Constants.MULTIPART_FORM_DATA;
import static com.kampen.riksSe.utils.UtilityTz.AddTimeinStaticTime;
import static com.kampen.riksSe.utils.UtilityTz.AddTimeinStaticTimeUI;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTime;
import static com.kampen.riksSe.utils.UtilityTz.GetStartDateOfWeek;
import static com.kampen.riksSe.utils.UtilityTz.convertDateTimeToTimeFormat;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeFormat;
import static com.kampen.riksSe.utils.UtilityTz.convertTimeToUTC;
import static com.kampen.riksSe.utils.UtilityTz.convertUTCToLocalTime;
import static com.kampen.riksSe.utils.UtilityTz.convertUriIntoFile;

public class Repository {



    public  static  final String F_SIGNUP_NAME ="fname";

    public  static  final String GENDER ="gender";

    public  static  final String DATE_OF_BIRTH ="dob";

    public  static  final String HEIGHT ="height";

    public  static  final String PROFILE_IMAGE ="image";

    public  static  final String WEIGHT ="weight";

    public  static  final String L_SIGNUP_NAME ="lname";

    public  static  final String EMAIL ="email";

    public  static  final String FCM ="fcm_id";

    public  static  final String DEVICE_MODEL ="agent";

    public  static  final String DEVICE_SDK ="operating_system";

    public  static  final String IP_ADDRESS ="ip_address";

    public  static  final String FCM_DATE_TIME ="last_activity_time";

    public  static  final String PASSWORD ="password";


    public  static  final String C_PASSWORD ="c_password";

    public  static  final String F_NAME ="fname";

    public  static  final String L_NAME ="lname";

    public  static  final String STATUS_SUCCESS ="success";

    public  static  final String STATUS_FAILED="failed";

    public  static  final int  API_HIT_FAILED =102;

    public  static  final int  API_HIT_SUCCESS =101;

    public  static  final String  TOKEN ="token";

    public  static  final String USERID ="user_id";
    public  static  final String TIME_ZONE ="timezone";



    public  static  final String CVC ="national_id";

    public  static  final String STEPS_COUNT ="steps_count";

    public  static  final String DAY_NAME ="day_name";

    public  static  final String STARS_COUNT ="stars_count";

    public  static  final String ACTIVITY_ID ="activity_type_id";
    public  static  final String ACTIVITY_TIME ="user_activity_time";
    public  static  final String ACTIVITY_TIMER ="timer";
    public  static  final String ACTIVITY_IMAGE ="activity_image";
    public  static  final String ACTIVITY_LAT ="activity_lat";
    public  static  final String ACTIVITY_LONG ="activity_long";
    public  static  final String ACTIVITY_LOCATION ="activity_location";
    public  static  final String ACTIVITY_CALORIES ="calories";
    public  static  final String ACTIVITY_TITLE ="activity_title";
    public  static  final String ACTIVITY_DISTANCE ="distance";
    public  static  final String ACTIVITY_WEIGHT_LOSS ="weight_loss";
    public  static  final String ACTIVITY_WAIST_LOSS ="waist";


    final static double walkingFactor = 0.57;

    static double CaloriesBurnedPerMile;

    static double strip;

    static double stepCountMile; // step/mile

    static double conversationFactor;

    static double CaloriesBurned;

   /* Login  Repository */


    public static   void TestItemCreation()
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Database_Manager.testItem(mLocalService,"1","umer","food","Juice","Unilever");


    }





  /*  public static  void getStartUpData( Realm_User user,String token, String userID,final ResponseStatus responseStatus){


        APIService mWebService;
        Realm      mLocalService;

        HashMap<String,String> hm=new HashMap();
        hm.put(USERID,"36");

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        Call<Generic_Result<Activities>> call = mWebService.getStartUpData("Bearer"+token,hm);


        call.enqueue(new Callback<Generic_Result<Activities>>() {
            @Override
            public void onResponse(Call<Generic_Result<Activities>> call, Response<Generic_Result<Activities>> response) {

                Generic_Result<Activities> obj = null;

                obj = response.body();
*//*

                Log.i("Login Response",response.body().getResult().getUser().getId());
                Log.i("Login Response",response.body().getResult().getUser().getToken());
*//*

                ResponseBody objError=response.errorBody();


                if(response!=null && response.code()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {



                 //   weeklyActivities remoteUser=obj.getResult().getWeekActivities();

                  *//*  if(remoteUser.getProfileImage()!=null && remoteUser.getProfileImage().length()>0) {

                        String  pathTemp=remoteUser.getProfileImage();

                        pathTemp=APIUrl.transformPath(pathTemp);

                        remoteUser.setProfileImage(APIUrl.BASE_URL + "/" + pathTemp);
                    }
*//*
                  Log.i("JSON Response", response.body().toString());
                   // addUserLocal(mLocalService, obj.getResult().getUser(),password,obj.getResult().getUser().getToken());

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);

                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);
                }
                else
                {


                    JSONObject objj=null;

                    if(objError!=null) {
                        try {
                            objj = new JSONObject(objError.string());

                            String status = objj.getString("status");
                            String message = objj.getString("message");
                            String code = objj.getString("code");

                            responseStatus.setCode(Integer.parseInt(code));
                            responseStatus.setMsg(message);
                            responseStatus.setStatus(status);

                            responseStatus.onResult(responseStatus);

                        } catch (Exception e) {
                            e.printStackTrace();

                            okhttp3.Response errorData=response.raw();

                            responseStatus.setCode(API_HIT_FAILED);
                            responseStatus.setMsg( errorData.message());
                            responseStatus.setStatus(STATUS_FAILED);
                            responseStatus.onResult(responseStatus);

                        }
                    }
                    else {

                        responseStatus.setCode(API_HIT_FAILED);
                        responseStatus.setMsg(response.message());
                        responseStatus.setStatus(STATUS_FAILED);

                        responseStatus.onResult(responseStatus);
                    }
                }



            }

            @Override
            public void onFailure(Call<Generic_Result<Activities>> call, Throwable t) {

                t.toString();



                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }

*/



    public  static    void   getUserProfile(String userID,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        mLocalService = ((LocalApiService) ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        HashMap<String,String> hm = new HashMap();
        hm.put(USERID,userID);

        Call<Generic_Result<RemoteUser>> call = mWebService.getUserUpdatedProfile(token,hm);


        call.enqueue(new Callback<Generic_Result<RemoteUser>>() {
                         @Override
                         public void onResponse(Call<Generic_Result<RemoteUser>> call, Response<Generic_Result<RemoteUser>> response) {

                             Generic_Result<RemoteUser> obj = null;

                             obj = response.body();



                             ResponseBody objError=response.errorBody();



                             if(response!=null && response.code()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {




                                 RemoteUser remoteUser=obj.getResult();

                                 if(remoteUser.getProfileImage()!=null && remoteUser.getProfileImage().length()>0) {




                                     String pathTemp = remoteUser.getProfileImage();

                                     //pathTemp = APIUrl.transformPath(pathTemp);

                                     remoteUser.setProfileImage(pathTemp);

                                 }

                                 UpdateUserLocal(mLocalService, obj.getResult(),obj.getResult().getToken());



                                 responseStatus.setCode(obj.getCode());
                                 responseStatus.setMsg(obj.getMsg());
                                 responseStatus.setStatus(obj.getStatus());

                                 responseStatus.onResult(responseStatus);

                             }
                             else if(obj!=null)
                             {
                                 responseStatus.setCode(obj.getCode());
                                 responseStatus.setMsg(obj.getMsg());
                                 responseStatus.setStatus(obj.getStatus());

                                 responseStatus.onResult(responseStatus);
                             }
                             else
                             {


                                 JSONObject objj=null;

                                 if(objError!=null) {
                                     try {
                                         objj = new JSONObject(objError.string());

                                         String status = objj.getString("status");
                                         String message = objj.getString("message");
                                         String code = objj.getString("code");

                                         responseStatus.setCode(Integer.parseInt(code));
                                         responseStatus.setMsg(message);
                                         responseStatus.setStatus(status);

                                         responseStatus.onResult(responseStatus);

                                     } catch (Exception e) {
                                         e.printStackTrace();

                                         okhttp3.Response errorData=response.raw();

                                         responseStatus.setCode(API_HIT_FAILED);
                                         responseStatus.setMsg( errorData.message());
                                         responseStatus.setStatus(STATUS_FAILED);
                                         responseStatus.onResult(responseStatus);

                                     }
                                 }
                                 else {

                                     responseStatus.setCode(API_HIT_FAILED);
                                     responseStatus.setMsg(response.message());
                                     responseStatus.setStatus(STATUS_FAILED);

                                     responseStatus.onResult(responseStatus);
                                 }
                             }
                         }


                         @Override
                         public void onFailure(Call<Generic_Result<RemoteUser>> call, Throwable t) {

                             t.toString();

                             i(t.toString(), "onFailure: ");

                             responseStatus.setCode(API_HIT_FAILED);
                             responseStatus.setMsg(t.getMessage());
                             responseStatus.setStatus(STATUS_FAILED);
                             responseStatus.onResult(responseStatus);


                         }
                     }
        );



    }




    public  static    void   getUser(Context context, String email, String password, String DeviceModel, String DeviceSDK, String ipAddress, String LoginTime, Boolean status, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;
/*        String BaseURL =  SaveSharedPreference.getBaseURl(context) == BASE_URL ? BASE_URL : BASE_TEST_URL;
        Log.i("BASE_URL_GET_USER", BaseURL);
        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME, BaseURL)).getApiService();*/
        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        mLocalService = ((LocalApiService) ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();

        HashMap<String,String> hm = new HashMap();
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);
        hm.put(DEVICE_MODEL, DeviceModel);
        hm.put(DEVICE_SDK, DeviceSDK);
        hm.put(IP_ADDRESS, ipAddress);
        hm.put("current_time",convertTimeToUTC());
        hm.put("timezone",tz.getID());
        hm.put("platform","android");
        HashMap<String,Boolean> hm1 = new HashMap();
        hm1.put("status",status);



        Call<LoginResultModel> call = mWebService.getUser(hm,hm1);


        call.enqueue(new Callback<LoginResultModel>() {
            @Override
            public void onResponse(Call<LoginResultModel> call, Response<LoginResultModel> response) {

                LoginResultModel obj = null;

                obj = response.body();



                ResponseBody objError=response.errorBody();



                if(response!=null && response.code()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {




                    RemoteUser remoteUser=obj.getResult();

                    if(remoteUser.getProfileImage()!=null && remoteUser.getProfileImage().length()>0) {




                            String pathTemp = remoteUser.getProfileImage();

                            //pathTemp = APIUrl.transformPath(pathTemp);

                            remoteUser.setProfileImage(pathTemp);

                        }

                    for(int i =0;i<obj.getRoles().size();i++){
                        if(obj.getResult().getUserRoleID()==obj.getRoles().get(i).getId()){
                            if(obj.getRoles().get(i).getName().equals("Trainer")){
                                AddTrainerLocal(mLocalService,obj,obj.getResult(),obj.getResult().getToken(),password,responseStatus);
                                break;
                            }else if(obj.getRoles().get(i).getName().equals("Contestant")){
                                addUserLocal(mLocalService,obj, obj.getResult(),password,obj.getResult().getToken(),responseStatus);
                                break;
                            }
                        }
                    }





                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);
                }
                else
                {


                    JSONObject objj=null;

                    if(objError!=null) {
                        try {
                            objj = new JSONObject(objError.string());

                            String status = objj.getString("status");
                            String message = objj.getString("message");
                            String code = objj.getString("code");

                            responseStatus.setCode(Integer.parseInt(code));
                            responseStatus.setMsg(message);
                            responseStatus.setStatus(status);

                            responseStatus.onResult(responseStatus);

                        } catch (Exception e) {
                            e.printStackTrace();

                            okhttp3.Response errorData=response.raw();

                            responseStatus.setCode(API_HIT_FAILED);
                            responseStatus.setMsg( errorData.message());
                            responseStatus.setStatus(STATUS_FAILED);
                            responseStatus.onResult(responseStatus);

                        }
                    }
                    else {

                        responseStatus.setCode(API_HIT_FAILED);
                        responseStatus.setMsg(response.message());
                        responseStatus.setStatus(STATUS_FAILED);

                        responseStatus.onResult(responseStatus);
                    }
                }
            }


            @Override
            public void onFailure(Call<LoginResultModel> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);


            }
        }
        );



    }


    public  static    void   setUserFcmToken(String token,String userID,String FcmToken, ResponseStatus responseStatus)
    {
        APIService mWebService;
       // Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
       // mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm =new HashMap();
        hm.put(USERID, userID);
        hm.put(FCM, FcmToken);



        Call<Generic_Result<Object>> call = mWebService.setFcmToken(token,hm);

        call.enqueue(new Callback<Generic_Result<Object>>() {
            @Override
            public void onResponse(Call<Generic_Result<Object>> call, Response<Generic_Result<Object>> response) {

                Generic_Result<Object> obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

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

    }


    public  static    void   getTrainerSchdules(String token,String userID, ResponseStatus responseStatus)
    {
        APIService mWebService;
         Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
         mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        Call<List<ScheduledLiveVideoCall>> call = mWebService.GetTrainerSchdule(token,userID);

        call.enqueue(new Callback<List<ScheduledLiveVideoCall>>() {
            @Override
            public void onResponse(Call<List<ScheduledLiveVideoCall>> call, Response<List<ScheduledLiveVideoCall>> response) {

                List<ScheduledLiveVideoCall> obj = null;

                obj = response.body();

                if(response.isSuccessful()){

                    TrainerSchduleDB_Handler.SaveTrainrSchdule(obj,mLocalService,response,responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<List<ScheduledLiveVideoCall>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
            }
        });

    }

    public  static    void   getContestantSchdules(String token,String userID, ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        Call<List<ScheduledLiveVideoCall>> call = mWebService.GetContestantSchdule(token,userID);

        call.enqueue(new Callback<List<ScheduledLiveVideoCall>>() {
            @Override
            public void onResponse(Call<List<ScheduledLiveVideoCall>> call, Response<List<ScheduledLiveVideoCall>> response) {

                List<ScheduledLiveVideoCall> obj = null;

                obj = response.body();

                if(response.isSuccessful()){

                    TrainerSchduleDB_Handler.SaveTrainrSchdule(obj,mLocalService,response,responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<List<ScheduledLiveVideoCall>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
            }
        });

    }


    public  static    void   DeleteContestantSchdules(String token,int SchduleID, ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        Call<Object> call = mWebService.DeleteContestantSchdule(token, String.valueOf(SchduleID));

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Object obj = null;

                obj = response.body();

                if(response.isSuccessful()){

                    TrainerSchduleDB_Handler.DeleteSpecificSchdule(mLocalService, SchduleID,responseStatus);

                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
            }
        });

    }


    public  static    void   getContestantDaySchdulesSlots(String token,String userID,String Date, ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        Call<List<SchduleSlots>> call = mWebService.GetContestantDaySchduleSlots(token,userID,Date);

        call.enqueue(new Callback<List<SchduleSlots>>() {
            @Override
            public void onResponse(Call<List<SchduleSlots>> call, Response<List<SchduleSlots>> response) {

                List<SchduleSlots> obj = null;

                obj = response.body();

                if(response.isSuccessful()){

                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.setContestantDaySchdulesSlotsList(obj);
                    responseStatus.onResult(responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<List<SchduleSlots>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
            }
        });

    }


    public  static    void   getContestantBookSchduledSlot(String token, BookSchduleSlots bookSchduleSlots, ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        Call<Object> call = mWebService.BookSchduleSlots(token,bookSchduleSlots);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Object obj = null;

                obj = response.body();

                if(response.isSuccessful()){

                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
            }
        });

    }


    public  static    void   getChatBadge(String token,String userID,String Fcm, ResponseStatus responseStatus)
    {
        APIService mWebService;
        //Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        // mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm =new HashMap();
        hm.put(USERID, userID);
        hm.put("fcm_token", Fcm);




        Call<BadgeCountResult> call = mWebService.getBadgeCountLogin(token,hm);

        call.enqueue(new Callback<BadgeCountResult>() {
            @Override
            public void onResponse(Call<BadgeCountResult> call, Response<BadgeCountResult> response) {

                BadgeCountResult obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {



                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setChatBadgeCount(String.valueOf(obj.getResult().getBadge()));
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<BadgeCountResult> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
            }
        });

    }


    //Forgot Password
    public  static    void   getForgotPasswordData(String Email,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm=new HashMap();
        hm.put(EMAIL, Email);




        Call<ForgotPasswordData> call = mWebService.getForgotPassword(hm);


        call.enqueue(new Callback<ForgotPasswordData>() {
            @Override
            public void onResponse(Call<ForgotPasswordData> call, Response<ForgotPasswordData> response) {

                ForgotPasswordData obj = null;

                obj = (ForgotPasswordData) response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    ForgotPasswordDB_Handler.saveAllDataSynced(obj,mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setData(obj);
                    responseStatus.setOTPCODE(String.valueOf(obj.getResult().getOtp()));
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<ForgotPasswordData> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });
    }


    //Change Password

    public  static    void   getChangePasswordData(String Email,String password,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm=new HashMap();
        hm.put(EMAIL, Email);
        hm.put(PASSWORD, password);
        hm.put(C_PASSWORD, password);




        Call<ChangePasswordData> call = mWebService.ChangePassword(hm);


        call.enqueue(new Callback<ChangePasswordData>() {
            @Override
            public void onResponse(Call<ChangePasswordData> call, Response<ChangePasswordData> response) {

                ChangePasswordData obj = null;

                obj = (ChangePasswordData) response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                   // ForgotPasswordDB_Handler.saveAllDataSynced(obj,mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setData(obj);
                   // responseStatus.setOTPCODE(String.valueOf(obj.getResult().getUser().getOtp()));
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<ChangePasswordData> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });
    }

    public static String CaloriesCalulatorFromSteps(double height ,double weight,double stepsCount){


        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);

        strip = height * 0.415;

        stepCountMile = 160934.4 / strip;

        conversationFactor = CaloriesBurnedPerMile / stepCountMile;

        CaloriesBurned = stepsCount * conversationFactor;

        //return String.format("%.2f",CaloriesBurned);
        return String.valueOf(CaloriesBurned);
    }



    //LeaderBoard Call

    public  static    void   getLeaderBoardAllData(Realm_User user,Competition competition,Boolean ContestStatus,String userId,String totalStarCount,String totalSteps,String totalCalories,String distance,String agent,String Date,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        //Log.d("Time zone","="+tz.getDisplayName());
        HashMap<String,String> hm=new HashMap();
        if(competition!=null) {
            if (competition.getStartDate() != null) {
                if(ContestStatus) {
                    hm.put(USERID, userId);
                    hm.put("steps_count", totalSteps);
                    hm.put("stars_count", totalStarCount);
                    hm.put("calories", totalCalories);
                    hm.put(ACTIVITY_DISTANCE, distance);
                    hm.put(DEVICE_MODEL, agent);
                    hm.put("user_activity_time", Date);
                    hm.put(TIME_ZONE, tz.getID());
                    //hm.put("unit", "high");
                }else{
                    hm.put(USERID, userId);
                    hm.put(DEVICE_MODEL, agent);
                    hm.put("user_activity_time", Date);
                    hm.put(TIME_ZONE, tz.getID());
                    hm.put("unit", "high");
                }
            }else{
                hm.put(USERID, userId);
                hm.put(DEVICE_MODEL, agent);
                hm.put("user_activity_time", Date);
                hm.put(TIME_ZONE, tz.getID());
                hm.put("unit", "high");
            }
        }else{

            hm.put(USERID, userId);
            hm.put(DEVICE_MODEL, agent);
            hm.put("user_activity_time", Date);
            hm.put(TIME_ZONE, tz.getID());
            hm.put("unit", "high");
        }





        Call<LeaderBoardAllData> call = mWebService.getLeaderBoardAllData(token,hm);


        call.enqueue(new Callback<LeaderBoardAllData>() {
            @Override
            public void onResponse(Call<LeaderBoardAllData> call, Response<LeaderBoardAllData> response) {

                LeaderBoardAllData obj = null;

                obj = (LeaderBoardAllData) response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {



                    LeaderBoardDB_Handler.saveLeaderBoardAllDataSynced(obj,mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setData(obj);
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                    AddActivityStepsLocal(String.valueOf(totalSteps), totalStarCount,totalCalories,distance,Date,false);
                }
                else
                {
                    AddActivityStepsLocal(String.valueOf(totalSteps), totalStarCount,totalCalories,distance,Date,false);
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<LeaderBoardAllData> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");
                AddActivityStepsLocal(String.valueOf(totalSteps), totalStarCount,totalCalories,distance,Date,false);
                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });
    }
    public  static    void   getLeaderBoardAllDataV3(Realm_User user,Competition competition,Boolean ContestStatus,String userId,String totalStarCount,String totalSteps,String totalCalories,String distance,String agent,String Date,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        //Log.d("Time zone","="+tz.getDisplayName());
        HashMap<String,String> hm=new HashMap();
        if(competition!=null) {
            if (competition.getStartDate() != null) {
                if(ContestStatus) {
                    hm.put(USERID, userId);
                    hm.put("steps_count", totalSteps);
                    hm.put("stars_count", totalStarCount);
                    hm.put("calories", totalCalories);
                    hm.put(ACTIVITY_DISTANCE, distance);
                    hm.put(DEVICE_MODEL, agent);
                    hm.put("user_activity_time", Date);
                    hm.put(TIME_ZONE, tz.getID());
                    hm.put("unit", "high");
                }else{
                    hm.put(USERID, userId);
                    hm.put(DEVICE_MODEL, agent);
                    hm.put(TIME_ZONE, tz.getID());
                    hm.put("unit", "high");
                }
            }else{
                hm.put(USERID, userId);
                hm.put(DEVICE_MODEL, agent);
                hm.put(TIME_ZONE, tz.getID());
                hm.put("unit", "high");
            }
        }else{
            hm.put(USERID, userId);
            hm.put(DEVICE_MODEL, agent);
            hm.put(TIME_ZONE, tz.getID());
            hm.put("unit", "high");
        }


        Call<LeaderBoardAllDataV3> call = mWebService.getLeaderBoardAllDataV3(token,hm);


        call.enqueue(new Callback<LeaderBoardAllDataV3>() {
            @Override
            public void onResponse(Call<LeaderBoardAllDataV3> call, Response<LeaderBoardAllDataV3> response) {

                LeaderBoardAllDataV3 obj = null;

                obj = (LeaderBoardAllDataV3) response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {



                    LeaderBoardDB_Handler.saveLeaderBoardAllDataSyncedV3(obj,mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setData(obj);
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                    AddActivityStepsLocal(String.valueOf(totalSteps), totalStarCount,totalCalories,distance,Date,false);
                }
                else
                {
                    AddActivityStepsLocal(String.valueOf(totalSteps), totalStarCount,totalCalories,distance,Date,false);
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<LeaderBoardAllDataV3> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");
                AddActivityStepsLocal(String.valueOf(totalSteps), totalStarCount,totalCalories,distance,Date,false);
                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });
    }


    public static  void AddActivityStepsLocal(final String steps_count,final String stars_count,final String calories,final String distance,final String user_activity_time,Boolean SyncedWithServer){

        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        final UserActivityData userActivityData= mLocalService.where(UserActivityData.class)
                .equalTo(Constants.DAILY_ACTIVITY_DATE_TAG,user_activity_time)
                .findFirst();

        if(userActivityData!= null){

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     UserActivityData db_user = userActivityData;

                                                     db_user.setLocatStepCount(steps_count);
                                                     db_user.setLocatStarCount(stars_count);
                                                     db_user.setLocatCalCount(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setDistance(distance);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
            );

        }
        else{

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     UserActivityData db_user = realm.createObject(UserActivityData.class);

                                                     db_user.setLocatStepCount(steps_count);
                                                     db_user.setLocatStarCount(stars_count);
                                                     db_user.setLocatCalCount(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setDistance(distance);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
                                             );

        }

    }

    public  static    void   ADDAllergiesAllData(String userId, ArrayList<String> allergyID,ArrayList<String> other_allergy_name,ArrayList<String> deleted_allergy_id,ArrayList<String> other_deleted_allergy_id, String token, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm1=new HashMap();
        hm1.put(USERID, userId);

        HashMap<String,String> hm=new HashMap();

        if(allergyID != null){
            for(int i=0; i<allergyID.size();i++){
                hm.put("allergy_id["+i+"]", allergyID.get(i));
            }
        }

        if(other_allergy_name !=null){
            for(int i=0; i<other_allergy_name.size();i++) {
                hm.put("other_allergy_name["+i+"]", other_allergy_name.get(i));
            }
        }

        if(deleted_allergy_id !=null){

            for(int i=0; i<deleted_allergy_id.size();i++) {
                hm.put("deleted_allergy_id["+i+"]", deleted_allergy_id.get(i));
            }
        }

        if(other_deleted_allergy_id !=null){

            for(int i=0; i<other_deleted_allergy_id.size();i++) {
                hm.put("other_deleted_allergy_id["+i+"]", other_deleted_allergy_id.get(i));
            }
        }

        //hm.put("other_deleted_allergy_id[]", other_deleted_allergy_id);

        Call<AllergyResponce> call = mWebService.AddAllergiesAllData(token,hm1,hm);


        call.enqueue(new Callback<AllergyResponce>() {
            @Override
            public void onResponse(Call<AllergyResponce> call, Response<AllergyResponce> response) {

                AllergyResponce obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    // NutritionsAndTrainingsDB_Handler.saveAllDataSynced(obj,mLocalService);

                   // AllergiesDB_Handler.saveAllDataSynced(obj,mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<AllergyResponce> call, Throwable t) {

                t.toString();

                //Log.i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

               // Log.i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

    public  static    void   getAllergiesSearchedData(String Keyword,String userId,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        HashMap<String,String> hm=new HashMap();
        hm.put(USERID, userId);
        hm.put("keyword", Keyword);


        Call<AddAllergiesV3Data> call = mWebService.getAllergiesSearchData(token,hm);


        call.enqueue(new Callback<AddAllergiesV3Data>() {
            @Override
            public void onResponse(Call<AddAllergiesV3Data> call, Response<AddAllergiesV3Data> response) {

                AddAllergiesV3Data obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK) {


                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.setAllergiesName(obj.getAllergyResultList());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<AddAllergiesV3Data> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });

    }



    public  static    void   getAllergiesAllData(String userId,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        HashMap<String,String> hm=new HashMap();
        hm.put(USERID, userId);




        Call<Generic_Result<AlergyResultDataV3>> call = mWebService.getAllergiesAllData(token,hm);


        call.enqueue(new Callback<Generic_Result<AlergyResultDataV3>>() {
            @Override
            public void onResponse(Call<Generic_Result<AlergyResultDataV3>> call, Response<Generic_Result<AlergyResultDataV3>> response) {

                Generic_Result<AlergyResultDataV3> obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                    AllergiesDB_Handler.saveSearchedDataSynced(obj.getResult(),mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<Generic_Result<AlergyResultDataV3>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }
    public  static    void   getAppUpdateVersion(final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Call<AppUpdate> call = mWebService.GetAppUpdateVersion();

        call.enqueue(new Callback<AppUpdate>() {
            @Override
            public void onResponse(Call<AppUpdate> call, Response<AppUpdate> response) {

                AppUpdate obj = null;

                obj = response.body();

                if(response.isSuccessful()){
                    AppVersionDB_Handler.saveAppVersionUpdate(obj,mLocalService);

                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<AppUpdate> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

    public  static    void   SyncTable(String token,SyncTable syncTable, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Call<Object> call = mWebService.SyncTableAPI(token,syncTable);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Object obj = null;

                obj = response.body();

                if(response.isSuccessful()){

                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });

    }

    public  static    void   UpdateUserPermissionAndVersion(String token, UserPermissionAndVersion userPermissionAndVersion, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Call<Object> call = mWebService.UpdateUserPermissionAndVersion(token,userPermissionAndVersion);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Object obj = null;

                if(response.isSuccessful()){
                    //AppVersionDB_Handler.saveAppVersionUpdate(obj,mLocalService);
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });
    }

    public  static    void   UpdateWeightHeight(String userId,String height,String Weight,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        HashMap<String,String> hm=new HashMap();
        hm.put("id", userId);
        hm.put(HEIGHT, height);
        hm.put(WEIGHT, Weight);

        Call<Generic_Result<RemoteUserResult>> call = mWebService.UpdateHeightWeight(token,hm);

        call.enqueue(new Callback<Generic_Result<RemoteUserResult>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUserResult>> call, Response<Generic_Result<RemoteUserResult>> response) {

                Generic_Result<RemoteUserResult> obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUserResult>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

    public static void SyncPastActivities(Context context,String userId,String token,List<ActivityDaily> pastActivitiesList,final ResponseStatus responseStatus){
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        String myDeviceModel = android.os.Build.MODEL;

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        //Log.d("Time zone","="+tz.getDisplayName());



        TreeMap<String, RequestBody> hm = new TreeMap();
        TreeMap<String, RequestBody> PastLatList = new TreeMap();
        TreeMap<String, RequestBody> PastLongList = new TreeMap();
        TreeMap<String, RequestBody> PastLocationAddressList = new TreeMap();
        TreeMap<String, RequestBody> PastStepsList = new TreeMap();
        TreeMap<String, RequestBody> PastStarsList = new TreeMap();
        TreeMap<String, RequestBody> PastCaloriesList = new TreeMap();
        TreeMap<String, RequestBody> PastDistanceList = new TreeMap();
        TreeMap<String, RequestBody> PastDateTimeList = new TreeMap();
        TreeMap<String, RequestBody> PastWeightList = new TreeMap();
        TreeMap<String, RequestBody> PastWaistList = new TreeMap();
        TreeMap<String, RequestBody> PastImageList = new TreeMap();
        TreeMap<String, Boolean> hm3 = new TreeMap();
        List<MultipartBody.Part> activityImageArray =new ArrayList();
        //List<ActivityDaily> PastActivitiesList =new ArrayList();
        /*List<RequestBody> PastStepsList =new ArrayList();
        List<RequestBody> PastStarsList =new ArrayList();
        List<RequestBody> PastCaloriesList =new ArrayList();
        List<RequestBody> PastDistanceList =new ArrayList();
        List<RequestBody> PastDateTimeList =new ArrayList();
        List<RequestBody> PastWeightList =new ArrayList();
        List<RequestBody> PastWaistList =new ArrayList();
        List<RequestBody> PastImageList =new ArrayList();*/
        RequestBody platform = createPartFromString("android");
        //hm.put("platform", platform);

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                if(pastActivitiesList.get(i).getmLatitude()!=0 ){
                    RequestBody latitude = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmLatitude()));
                    PastLatList.put("activity_lat[" + i + "]", latitude);
                }
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                if(pastActivitiesList.get(i).getmLongitude()!=0 ){
                    RequestBody longitude = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmLongitude()));
                    PastLongList.put("activity_long[" + i + "]", longitude);
                }
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                if(pastActivitiesList.get(i).getmLocationAddress()!=null /*|| !pastActivitiesList.get(i).getmLocationAddress().equals("")*/){
                    RequestBody locationAddress = createPartFromString(pastActivitiesList.get(i).getmLocationAddress());
                    PastLocationAddressList.put("activity_location[" + i + "]", locationAddress);
                }/*else (pastActivitiesList.get(i).getmLatitude()!=0 &&pastActivitiesList.get(i).getmLongitude()!=0){
                    LatLng latLng =new LatLng(pastActivitiesList.get(i).getmLatitude(),pastActivitiesList.get(i).getmLongitude());
                    RequestBody locationAddress = createPartFromString(String.valueOf(getAddressFromLatLong(context,latLng)));
                    PastLocationAddressList.put("activity_location[" + i + "]", locationAddress);
                }*/
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody steps = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmSteps()));
               // hm.put("steps_count[]", steps);

                PastStepsList.put("steps_count[" + i + "]", steps);
                //PastStepsList.add(steps);

            }
        }
        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody stars = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmStars()));
                PastStarsList.put("stars_count[" + i + "]", stars);

            }
        }
        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody distance = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmDistance()));
                PastDistanceList.put("distance[" + i + "]", distance);
                //hm.put("distance[]", distance);
                //PastDistanceList.add(distance);
            }
        }
        if(pastActivitiesList != null){
            for(int i=0; i<pastActivitiesList.size();i++){
                RequestBody calories = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmCalories()));
                PastCaloriesList.put("calories["+ i +"]", calories);
                //PastCaloriesList.add(calories);
            }
        }
        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody dateTime = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmDate()));
                PastDateTimeList.put("user_activity_time["+ i +"]", dateTime);
                //PastDateTimeList.add(dateTime);
                //PastDateTimeList.add(dateTime);
            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody weight = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmWeight()));
                PastWeightList.put("weight[" + i + "]", weight);
                //hm.put("weight[]", weight);
                //PastWeightList.add(weight);.

            }
        }

        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {
                RequestBody waist = createPartFromString(String.valueOf(pastActivitiesList.get(i).getmWaist()));
                PastWaistList.put("waist[" + i + "]", waist);
                //hm.put("waist[]", waist);
                //PastWaistList.add(waist);
            }
        }


        if (pastActivitiesList != null) {
            for (int i = 0; i < pastActivitiesList.size(); i++) {

                if(pastActivitiesList.get(i).getmMediaImage()==null){


                }else if(pastActivitiesList.get(i).getmMediaImage().isEmpty()){

                }else if(pastActivitiesList.get(i).getmMediaImage().contains("https")){
                    RequestBody mediaImage = createPartFromString(pastActivitiesList.get(i).getmMediaImage());
                    //hm3.put("is_server_activity_image [" + i + "]", true);
                    PastImageList.put("activity_image[" + i + "]", mediaImage);
                    //hm.put("activity_image[]", mediaImage);
                    //PastImageList.add(mediaImage);

                }else{
                    File finalFile = convertUriIntoFile(Uri.parse(pastActivitiesList.get(i).getmMediaImage()));
                    MultipartBody.Part body=null;
                    if(finalFile!=null)
                    {
                        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), finalFile);
                        body = MultipartBody.Part.createFormData("activity_image[" + i + "]", finalFile.getName(), reqFile);
                        //body = MultipartBody.Part.create(reqFile);
                    }
                    //hm3.put("is_server_activity_image [" + i + "]", false);
                    activityImageArray.add(body);
                    //hm3.put("activity_image[" + i + "]", body);
                }
            }
        }




        HashMap<String, RequestBody> hm1 = new HashMap();
        RequestBody userID = createPartFromString(userId);
        RequestBody deviceModel = createPartFromString(myDeviceModel);
        RequestBody timezone = createPartFromString(tz.getID());

        hm.put(USERID, userID);
        hm.put(DEVICE_MODEL, deviceModel);
        hm.put(TIME_ZONE, timezone);

/*

        Set<Map.Entry<String, RequestBody>> entries = PastStepsList.entrySet();

        List<Map.Entry<String, RequestBody>> listOfEntries = new ArrayList<Map.Entry<String, RequestBody>>(entries);

        Collections.sort(listOfEntries,keyComparator);

        for(int i=0;i<listOfEntries.size();i++){
            Log.i("StepSort",listOfEntries.get(i).getKey());
        }
*/

        Call<List<PastActivityModel>> call = mWebService.SyncPastActivities(token,hm,PastLocationAddressList,PastLatList,PastLongList,PastStepsList,PastStarsList,PastCaloriesList,PastDistanceList,PastDateTimeList,PastWeightList,PastWaistList,PastImageList,activityImageArray);


        call.enqueue(new Callback<List<PastActivityModel>>() {
            @Override
            public void onResponse(Call<List<PastActivityModel>> call, Response<List<PastActivityModel>> response) {

                List<PastActivityModel> obj = response.body();

                if(response.isSuccessful()){
                    //SyncPastActivitiesLocal(obj,pastActivitiesList);
                    SyncPastActivitiesLocalV1(obj,pastActivitiesList);
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_SUCCESS);
                    responseStatus.onResult(responseStatus);
                }else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }
            }

            @Override
            public void onFailure(Call<List<PastActivityModel>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });


    }

    public  static    void   SyncUserAllData(Context context,String userId,String token,String SyncDate,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        //Log.d("Time zone","="+tz.getDisplayName());
        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        String currentDateandTime = sdf1.format(new Date());
        Competition CompitionDate = Repository.getCompitionDate();
        ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(currentDateandTime, CompitionDate.getStartDate());
        //ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime("2019-11-19 00:00:00", mUser.getContestStartDate());
        long currentDay = contestWeekDayTimeModel.getDays()+1;
        long currentWeek = contestWeekDayTimeModel.getWeeks()+1;

        HashMap<String,String> hm=new HashMap();
        hm.put(USERID, userId);
        //hm.put(USERID, "224");
        hm.put("last_sync_date",SyncDate);
       // hm.put("current_date_time",currentDateandTime);
        hm.put("current_week", String.valueOf(currentWeek));
        hm.put("current_day", String.valueOf(currentDay));
        hm.put(TIME_ZONE, tz.getID());
        hm.put("unit", "high");


        Call<AllHomeDataNew> call = mWebService.SyncUserAllData(token,hm);


        call.enqueue(new Callback<AllHomeDataNew>() {
            @Override
            public void onResponse(Call<AllHomeDataNew> call, Response<AllHomeDataNew> response) {

                AllHomeDataNew obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {



                    //NutritionsAndTrainingsDB_Handler.saveAllActivitiesData(context,obj.getResult(),mLocalService);
                    NutritionsAndTrainingsDB_Handler.saveAllActivitiesDataV3(context,obj.getResult(),mLocalService);

                    NutritionsAndTrainingsDB_Handler.saveAllSyncedData(context,obj,obj.getResult(),mLocalService,responseStatus);

         /*         responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.setNutritionPDFLINK(obj.getResult().getNutritionPlanPDF());
                    responseStatus.onResult(responseStatus);*/
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<AllHomeDataNew> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

    public static void getGoogleFitTodaySteps(Context context,GoogleApiClient googleApiClient, final ResponseStatus responseStatus){
        long total = 0;

        PendingResult<DailyTotalResult> result = Fitness.HistoryApi.readDailyTotal(googleApiClient,DataType.TYPE_STEP_COUNT_DELTA);
        DailyTotalResult totalResult = result.await(30, TimeUnit.SECONDS);
        if (totalResult.getStatus().isSuccess()) {
            DataSet totalSet = totalResult.getTotal();
            total = totalSet.isEmpty()
                    ? 0
                    : totalSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
        } else {
            //Log.w(TAG, "There was a problem getting the step count.");
            responseStatus.setCode(400);
            responseStatus.setMsg("Today Steps Failed");
            responseStatus.setStatus(STATUS_FAILED);
            responseStatus.onResult(responseStatus);
        }

        //Log.i(TAG, "Total steps: " + total);
        responseStatus.setCode(200);
        responseStatus.setMsg("Today Steps Success");
        responseStatus.setStatus("success");
        responseStatus.setTodayStepsFromGoogleFit(String.valueOf(total));
        responseStatus.onResult(responseStatus);
    }
    public  static    void   getAllQuestions(String token,String userID, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        Call<List<Question>> call = mWebService.GetAllQuestionsV3(token,userID);

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {

                List<Question> obj = null;

                obj = response.body();

                if(response.isSuccessful()){
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                    DailyDairyDB_Handler.saveAllQuestionSynced(obj,mLocalService);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });

    }
    public  static    void   getTodayQuestions(String token, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Call<List<Question>> call = mWebService.GetTodayQuestionsV3(token);

        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {


                List<Question> obj = null;

                obj = response.body();

                if(response.isSuccessful()){


                    DailyDairyDB_Handler.saveDailyDiaryTodayQuestion(obj,mLocalService,response,responseStatus);

                }else{
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });

    }
    public  static    void   getDailyDiary(String token,String userID, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        Call<List<diaries>> call = mWebService.GetAllDiariesV3(token,userID);

        call.enqueue(new Callback<List<diaries>>() {
            @Override
            public void onResponse(Call<List<diaries>> call, Response<List<diaries>> response) {

                List<diaries> obj = null;

                obj = response.body();

                if(response.isSuccessful()){


                    DailyDairyDB_Handler.getAllDailyDiary(obj,mLocalService,response,responseStatus);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<List<diaries>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });

    }

    public  static    void   AddDailyDiary(String token, AddUpdateDailyDiaryToServerV3 AddDailyDiaryV3, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Call<List<diaries>> call = mWebService.AddDailyDiaryV3(token,AddDailyDiaryV3);

        call.enqueue(new Callback<List<diaries>>() {
            @Override
            public void onResponse(Call<List<diaries>> call, Response<List<diaries>> response) {

                List<diaries> obj = null;

                obj = response.body();

                if(response.isSuccessful()){
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                    DailyDairyDB_Handler.AddDailyDiary(obj,mLocalService);

                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            /*    if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {



                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }
*/


            }

            @Override
            public void onFailure(Call<List<diaries>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });
    }


    public  static    void   UpdateDailyDiary(String token, DiariesUpdateServer diariesUpdateServer, String dailyDiaryID, final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Call<diaries> call = mWebService.UpdateDailyDiaryV3(token,diariesUpdateServer,dailyDiaryID);

        call.enqueue(new Callback<diaries>() {
            @Override
            public void onResponse(Call<diaries> call, Response<diaries> response) {

                diaries obj = null;

                obj = response.body();

                if(response.isSuccessful()){
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus("success");
                    responseStatus.onResult(responseStatus);

                    DailyDairyDB_Handler.UpdateDailyDiary(obj,mLocalService);
                }else{
                    responseStatus.setCode(response.code());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }
            }

            @Override
            public void onFailure(Call<diaries> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }



    public  static    void   getUserAllData(String userId,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        Log.d("Time zone","="+tz.getDisplayName());

        HashMap<String,String> hm=new HashMap();
        hm.put(USERID, userId);
        hm.put(TIME_ZONE, tz.getID());
        hm.put("unit", "high");




        Call<Generic_Result<AllData>> call = mWebService.getUserAllData(token,hm);


        call.enqueue(new Callback<Generic_Result<AllData>>() {
            @Override
            public void onResponse(Call<Generic_Result<AllData>> call, Response<Generic_Result<AllData>> response) {

                Generic_Result<AllData> obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    NutritionsAndTrainingsDB_Handler.saveAllDataSynced(obj.getResult(),mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<Generic_Result<AllData>> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

    public  static    void   getChatBadgeData(String userId,String type,String week,String deviceID,String BadgeCount,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm = new HashMap();
        hm.put(USERID, userId);
        hm.put("type", type);
        hm.put("week", "0");
        hm.put("fcm_token", deviceID);
        hm.put("chat_count", BadgeCount);




        Call<ChatData> call = mWebService.getChatBadgeData(token,hm);


        call.enqueue(new Callback<ChatData>() {
            @Override
            public void onResponse(Call<ChatData> call, Response<ChatData> response) {

                ChatData obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    addChatDataLocal(mLocalService,userId,obj.getChatDetails());

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg("Sucess");
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<ChatData> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

    public  static    void   getChatData(String userId,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm=new HashMap();
        hm.put(USERID, userId);




        Call<ChatData> call = mWebService.getChatAllDataV3(token,hm);


        call.enqueue(new Callback<ChatData>() {
            @Override
            public void onResponse(Call<ChatData> call, Response<ChatData> response) {

                ChatData obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    //NutritionsAndTrainingsDB_Handler.saveAllDataSynced(obj.getResult(),mLocalService);

                    addChatDataLocal(mLocalService,userId,obj.getChatDetails());

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg("Sucess");
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<ChatData> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }






    //Promo Code

    public  static    void   getPromoCodeData(String userID, String PackageId, String PackagePrice, String DiscountCode,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm=new HashMap();
        hm.put(USERID, userID);
        hm.put("package_id", PackageId);
        hm.put("package_price", PackagePrice);
        hm.put("discount_code", DiscountCode);




        Call<PromoCodeData> call = mWebService.getPromoCodeData(token,hm);


        call.enqueue(new Callback<PromoCodeData>() {
            @Override
            public void onResponse(Call<PromoCodeData> call, Response<PromoCodeData> response) {

                PromoCodeData obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                   // NutritionsAndTrainingsDB_Handler.saveAllDataSynced(obj.getResult(),mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setDiscountedPacagePrice(String.valueOf(obj.getDiscountedPackagePrice()));
                    responseStatus.setDiscountedActualPrice(String.valueOf(obj.getDiscountedActualPrice()));
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setDiscountedPacagePrice("");
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }



            }

            @Override
            public void onFailure(Call<PromoCodeData> call, Throwable t) {

                t.toString();

                i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                i("API Failed MSG :: ",t.getMessage());

            }
        });



    }

    //W_Plans ActivityDaily

    public  static    void   getPlanData(String userID,String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();



        HashMap<String,String> hm=new HashMap();
        hm.put(USERID, userID);

        Call<PlanData> call = mWebService.getPlanData(token,hm);


        call.enqueue(new Callback<PlanData>() {
            @Override
            public void onResponse(Call<PlanData> call, Response<PlanData> response) {

                PlanData obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {


                    PlansDB_Handler.saveAllDataSynced(obj,mLocalService);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMessage());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }

            }

            @Override
            public void onFailure(Call<PlanData> call, Throwable t) {

                t.toString();

               // Log.i(t.toString(), "onFailure: ");

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);

                //Log.i("API Failed MSG :: ",t.getMessage());

            }
        });


    }




    public static String GetUserLocalWithoutAPI(String Email, String Pass)
    {

        Realm  realm= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        final String[] EmailDB = new String[1];
        final String[] PassDB = new String[1];
        final String[] Msg = new String[1];
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = realm.createObject(Realm_User.class);


                if(Email.equals(db_user.getEmail()) && Pass.equals(db_user.getPass())){


                    EmailDB[0] = db_user.getEmail();
                    PassDB[0] = db_user.getPass();
                    Msg[0] = "Sucess";

                }
                else{

                    Msg[0] = "Failed";
                }


            }
        });

        String Message = Msg[0];

     return Message;
    }



    public static String addUserLocalWithoutAPI(String fName, String lName, String Email, String Pass)
    {

        Realm  realm= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = realm.createObject(Realm_User.class);

                /*"token": "OTlrdUZzTTFTV0UyT21JOUREN1lnQU9KQmFFWmN3VXdFVE5zVmtyTQ==",
                        "id": 31,
                        "firstname": "umer",
                        "lastname": "javaid",
                        "email": "you1@gmail.com",
                        "gender": null,
                        "dateOfBirth": null,
                        "streetAddress": null,
                        "phonenumber": null,
                        "profileImage": "",
                        "thumbImage": "",
                        "height": "0.00",
                        "weight": "0.00",
                        "role_id": "3"*/

              //  db_user.setToken(token);
                db_user.setF_name(fName);
                db_user.setL_name(lName);
                db_user.setEmail(Email);
                db_user.setPass(Pass);
           /*     db_user.setUser_gender(Gender);
                db_user.setDob(DoB);
                db_user.setStreetAddress(Address);
                db_user.setPhonenumber(PhoneNo);
               // db_user.setProfile_image(userJson.getProfileImage());
                try{
                    db_user.setHeight_in_cm(Integer.parseInt(Height));}catch (Exception ex){}
                try{
                    db_user.setWeight(Integer.parseInt(Weight));}catch (Exception ex){}*/

                //db_user.setId(userJson.getId());




            }
        });

        return "Sucess";
    }


    public static void addChatDataLocal(Realm realm, String userID, ChatDetails userJson)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        try {

            final Realm_User updatechatLocal= mLocalService.where(Realm_User.class)
                    .equalTo("id",userID)
                    .findFirst();

            if(updatechatLocal != null) {

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        updatechatLocal.setTrainerName(userJson.getTrainerName());
                        updatechatLocal.setTrainerNumber(userJson.getTrainerNumber());
                        updatechatLocal.setTrainerID(userJson.getTrainerID());
                        updatechatLocal.setTrainerPhoto(userJson.getTrainerPhoto());

                        realm.insertOrUpdate(updatechatLocal);

                    }

                });

            }
        }catch (Exception ex)
        {
            ex.toString();
        }

    }

    public static   boolean UpdateUserLocal(Realm realm,RemoteUser userJson,String token)
    {



        try {


            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Realm_User db_user = realm.createObject(Realm_User.class);



                    db_user.setToken(token);
                    db_user.setF_name(userJson.getFirstname().toLowerCase());
                    db_user.setL_name(userJson.getLastname().toLowerCase());
                    db_user.setEmail(userJson.getEmail().toLowerCase());
                    db_user.setUser_gender(userJson.getGender());
                    db_user.setDob(userJson.getDateOfBirth());
                    db_user.setStreetAddress(userJson.getStreetAddress());
                    db_user.setPhonenumber(userJson.getPhonenumber());
                    db_user.setProfile_image(userJson.getProfileImage());
                    db_user.setThumbImage(userJson.getThumbImage());
                    db_user.setCvc(userJson.getCVC());
                    if(userJson.getStarCount()==null){
                        db_user.setStars_count("0");
                    }else{
                        db_user.setStars_count(userJson.getStarCount());
                    }
                    if(userJson.getStepCount()==null){
                        db_user.setSteps_count("0");
                    }else{
                        db_user.setSteps_count(userJson.getStepCount());
                    }
                    db_user.setTrainerName(userJson.getTrainerName());
                    db_user.setTrainerNumber(userJson.getTrainerNumber());
                    db_user.setTrainerPhoto(userJson.getTrainerPhoto());
                    db_user.setTrainerID(userJson.getTrainerID());
                    db_user.setPaymentMethods(userJson.getPaymentMethods());
                    db_user.setPaymentStatus(userJson.getPaymentStatus());
                    db_user.setActivity_image(userJson.getActivityImage());
                    db_user.setActivity_location(userJson.getLocation());
                    db_user.setActivityTime(userJson.getActivityTime());
                    db_user.setActivityWaist(userJson.getWaist());
                    db_user.setActivityWeight(userJson.getActivityWeight());

                    try {
                        db_user.setHeight_in_cm(Double.parseDouble(userJson.getHeight()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setWeight(Double.parseDouble(userJson.getWeight()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setGoalweight(Double.parseDouble(userJson.getGoalweight()));
                    } catch (Exception ex) {
                    }

                    db_user.setId(userJson.getId());


                }
            });

            //starUpdateFromLogin(userJson.getStarCount(),userJson.getStepCount());

        }catch (Exception ex)
        {
            ex.toString();

        }

        return  false;
    }

    public static void AddTrainerLocal(Realm realm,LoginResultModel obj,RemoteUser userJson,String token,String pass, final ResponseStatus responseStatus){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                    realm.deleteAll();

                    realm.insertOrUpdate(obj.getRoles());

                    Realm_User db_user = realm.createObject(Realm_User.class);

                    db_user.setToken(token);

                    if(userJson.getId()!=null){
                        db_user.setId(userJson.getId());
                    }
                    if(userJson.getFirstname()!=null){
                        db_user.setF_name(userJson.getFirstname().toLowerCase());
                    }
                    if(userJson.getLastname()!=null){
                        db_user.setL_name(userJson.getLastname().toLowerCase());
                    }
                    if(userJson.getEmail()!=null){
                        db_user.setEmail(userJson.getEmail().toLowerCase());
                    }
                    db_user.setPass(pass);
                    db_user.setUser_gender(userJson.getGender());
                    db_user.setDob(userJson.getDateOfBirth());
                    db_user.setStreetAddress(userJson.getStreetAddress());
                    db_user.setPhonenumber(userJson.getPhonenumber());
                    db_user.setProfile_image(userJson.getProfileImage());
                    db_user.setThumbImage(userJson.getThumbImage());
                    db_user.setUserRoleID(userJson.getUserRoleID());
                    db_user.setmDesignation(userJson.getmDesignation());


                /*RealmList<ContestantDB> contestantDBRealmList =new RealmList();

                for(int i=0;i<userJson.getContestant().size();i++){
                    ContestantDB contestantDB =new ContestantDB();
                    contestantDB.setId(userJson.getContestant().get(i).getId());
                    contestantDB.setName(userJson.getContestant().get(i).getName());
                    contestantDBRealmList.add(contestantDB);
                }
                db_user.setmContestantdb(contestantDBRealmList);*/

                    realm.insertOrUpdate(db_user);



            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                responseStatus.setCode(obj.getCode());
                responseStatus.setMsg(obj.getMsg());
                responseStatus.setStatus(obj.getStatus());
                responseStatus.setDeveloper(obj.getResult().getDeveloper());
                responseStatus.setLoginStatus(obj.getResult().getLogin());
                responseStatus.setmUserRoleId(obj.getResult().getUserRoleID());
                responseStatus.onResult(responseStatus);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(error.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
            }
        });
    }

    public static   boolean addUserLocal(Realm realm,LoginResultModel obj,RemoteUser userJson,String pass,String token, final ResponseStatus responseStatus)
    {



        try {

           /* realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    realm.deleteAll();


                }
            });*/
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    realm.deleteAll();


                    realm.insertOrUpdate(obj.getRoles());

                    Realm_User db_user = realm.createObject(Realm_User.class);



                    db_user.setToken(token);
                    db_user.setId(userJson.getId());
                    db_user.setF_name(userJson.getFirstname().toLowerCase());
                    db_user.setL_name(userJson.getLastname().toLowerCase());
                    db_user.setEmail(userJson.getEmail().toLowerCase());
                    db_user.setUser_gender(userJson.getGender());
                    db_user.setDob(userJson.getDateOfBirth());
                    db_user.setStreetAddress(userJson.getStreetAddress());
                    db_user.setPhonenumber(userJson.getPhonenumber());
                    db_user.setProfile_image(userJson.getProfileImage());
                    db_user.setThumbImage(userJson.getThumbImage());
                    db_user.setCvc(userJson.getCVC());
                    if(userJson.getStarCount()==null){
                        db_user.setStars_count("0");
                    }else{
                        db_user.setStars_count(userJson.getStarCount()+"");
                    }
                    if(userJson.getStepCount()==null){
                        db_user.setSteps_count("0");
                    }else{
                        db_user.setSteps_count(userJson.getStepCount()+"");
                    }
                    db_user.setTrainerName(userJson.getTrainerName());
                    db_user.setTrainerNumber(userJson.getTrainerNumber());
                    db_user.setTrainerPhoto(userJson.getTrainerPhoto());
                    db_user.setTrainerID(userJson.getTrainerID());
                    db_user.setPaymentMethods(userJson.getPaymentMethods());
                    db_user.setPaymentStatus(userJson.getPaymentStatus());
                    db_user.setActivity_image(userJson.getActivityImage());
                    db_user.setActivity_location(userJson.getLocation());
                    db_user.setActivityTime(userJson.getActivityTime());
                    db_user.setActivityWaist(userJson.getWaist()+"");
                    db_user.setActivityWeight(userJson.getActivityWeight()+"");
                    db_user.setAlreadyLogin(userJson.getAlreadyLogin());
                    db_user.setLogin(userJson.getLogin());
                    db_user.setContestStartDate(userJson.getContestStartDate()+"");
                    db_user.setContestEndDate(userJson.getContestEndDate()+"");
                    try {
                        db_user.setHeight_in_cm(Double.parseDouble(userJson.getHeight()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setWeight(Double.parseDouble(userJson.getWeight()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setWaist(Double.parseDouble(userJson.getWaist()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setGoalweight(Double.parseDouble(userJson.getGoalweight()));
                    } catch (Exception ex) {
                    }
                    db_user.setId(userJson.getId());
                    db_user.setPass(pass);
                    db_user.setContestID(userJson.getContestID());
                    db_user.setUserRoleID(userJson.getUserRoleID());
                    db_user.setmDesignation(userJson.getmDesignation());

                    realm.insertOrUpdate(db_user);

                    Competition db_user1 = realm.createObject(Competition.class);
                    db_user1.setId(userJson.getContestID());
                    db_user1.setStartDate(userJson.getContestStartDate());
                    realm.insertOrUpdate(db_user1);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.setDeveloper(obj.getResult().getDeveloper());
                    responseStatus.setLoginStatus(obj.getResult().getLogin());
                    responseStatus.setmUserRoleId(obj.getResult().getUserRoleID());
                    responseStatus.onResult(responseStatus);
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg("Db Insertion Failed");
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                }
            });

            //starUpdateFromLogin(userJson.getStarCount(),userJson.getStepCount());

        }catch (Exception ex)
        {
            ex.toString();

        }

        return  false;
    }


    public static   boolean updateStarUserLocal(String starCount,String userID,String token)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        try {
            final Realm_User updateStarLocal= mLocalService.where(Realm_User.class)
                    .equalTo("id",userID)
                    .findFirst();

            mLocalService.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Realm_User db_user = updateStarLocal;

                    db_user.setStars_count(starCount);

                    //db_user.setSteps_count(userJson.getStepCount());
                }
            });



        }catch (Exception ex)
        {
            ex.toString();

        }

        return  false;
    }

    /*End user Repository*/
   //Payment User

    public static   boolean addUserLocalPayment(Realm realm,RemoteUser userJson,String pass,String token)
    {


        try {


            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Realm_User db_user = realm.createObject(Realm_User.class);



                    db_user.setToken(token);
                    db_user.setF_name(userJson.getFirstname());
                    db_user.setL_name(userJson.getLastname());
                    db_user.setEmail(userJson.getEmail());
                    db_user.setUser_gender(userJson.getGender());
                    db_user.setDob(userJson.getDateOfBirth());
                    db_user.setStreetAddress(userJson.getStreetAddress());
                    db_user.setPhonenumber(userJson.getPhonenumber());
                    db_user.setProfile_image(userJson.getProfileImage());
                    db_user.setThumbImage(userJson.getThumbImage());
                    db_user.setCvc(userJson.getCVC());
                    db_user.setStars_count(userJson.getStarCount());
                    db_user.setSteps_count(userJson.getStepCount());
                    db_user.setTrainerName(userJson.getTrainerName());
                    db_user.setTrainerNumber(userJson.getTrainerNumber());
                    db_user.setTrainerID(userJson.getTrainerID());
                    db_user.setPaymentMethods(userJson.getPaymentMethods());
                    db_user.setPaymentStatus(userJson.getPaymentStatus());

                    try {
                        db_user.setHeight_in_cm(Double.parseDouble(userJson.getHeight()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setWeight(Double.parseDouble(userJson.getWeight()));
                    } catch (Exception ex) {
                    }

                    db_user.setId(userJson.getId());
                    db_user.setPass(pass);


                }
            });


        }catch (Exception ex)
        {
            ex.toString();
        }

        return  false;
    }




    /* SignUp  Repository*/


    public  static    void   signUpUser(String fname,String lname,String password,String email,String gender,String dob,String hight,String weight,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        HashMap<String,String> hm=new HashMap();
        hm.put(L_NAME,lname);
        hm.put(F_NAME,fname);
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);
        hm.put(C_PASSWORD,password);
        hm.put(GENDER,gender);
        hm.put(HEIGHT,hight);
        hm.put(WEIGHT,weight);
        hm.put("dateOfBirth",dob);
        hm.put("payment_status","false");


        Call<Generic_Result<RemoteUserResult>> call = mWebService.userSignUp(hm);

        call.enqueue(new Callback<Generic_Result<RemoteUserResult>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUserResult>> call, Response<Generic_Result<RemoteUserResult>> response) {

                Generic_Result<RemoteUserResult> obj = null;
//[size=176 text={"status":"failure","message":{"fname":["The fname field is requ…]
                obj = response.body();



                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                    RemoteUser remoteUser=obj.getResult().getUser();

                    if(remoteUser.getProfileImage()!=null && remoteUser.getProfileImage().length()>0)

                        remoteUser.setProfileImage(APIUrl.IMAFE_BASE_URL+remoteUser.getProfileImage());

                    //addUserLocal(mLocalService, obj.getResult().getUser(),password,obj.getResult().getUser().getToken());

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);
                }

                else
                {
                    ResponseBody objError=response.errorBody();

                    JSONObject objj=null;

                    if(objError!=null) {
                        try {
                            objj = new JSONObject(objError.string());

                            String status = objj.getString("status");
                            String message = objj.getString("message");
                            String code = objj.getString("code");

                            responseStatus.setCode(Integer.parseInt(code));
                            responseStatus.setMsg(message);
                            responseStatus.setStatus(status);

                            responseStatus.onResult(responseStatus);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {

                        responseStatus.setCode(API_HIT_FAILED);
                        responseStatus.setMsg(response.message());
                        responseStatus.setStatus(STATUS_FAILED);

                        responseStatus.onResult(responseStatus);
                    }
                }


            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUserResult>> call, Throwable t) {

                t.toString();



                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }


    /*End user Repository*/

    /* SignUp  Repository*/


    public  static    void   PaymentsignUpUser(String fname,String lname,String password,String email,String gender,String dob,String hight,String weight,String fnameDuo,String lnameDuo,String product_id,String address,String tax,String total,String payment_status,String payment_methods,String reference_no,String order_status,String emailDuo,String userID,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        HashMap<String,String> hm=new HashMap();
        hm.put(L_NAME,lname);
        hm.put(F_NAME,fname);
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);
        hm.put(C_PASSWORD,password);
        hm.put(GENDER,gender);
        hm.put(HEIGHT,hight);
        hm.put(WEIGHT,weight);
        hm.put("fnameDuo",fnameDuo);
        hm.put("lnameDuo",lnameDuo);
        hm.put("product_id",product_id);
        hm.put("address",address);
        hm.put("tax",tax);
        hm.put("total",total);
        hm.put("payment_status",payment_status);
        hm.put("payment_methods",payment_methods);
        hm.put("reference_no",reference_no);
        hm.put("order_status",order_status);
        hm.put("email",emailDuo);
        hm.put("userid",emailDuo);




        Call<Generic_Result<RemoteUserResult>> call = mWebService.userSignUp(hm);

        call.enqueue(new Callback<Generic_Result<RemoteUserResult>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUserResult>> call, Response<Generic_Result<RemoteUserResult>> response) {

                Generic_Result<RemoteUserResult> obj = null;
//[size=176 text={"status":"failure","message":{"fname":["The fname field is requ…]
                obj = response.body();



                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                    RemoteUser remoteUser=obj.getResult().getUser();

                    if(remoteUser.getProfileImage()!=null && remoteUser.getProfileImage().length()>0)

                        remoteUser.setProfileImage(APIUrl.IMAFE_BASE_URL+"/"+remoteUser.getProfileImage());

                    addUserLocalPayment(mLocalService, obj.getResult().getUser(),password,obj.getResult().getUser().getToken());

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);
                }

                else
                {
                    ResponseBody objError=response.errorBody();

                    JSONObject objj=null;

                    if(objError!=null) {
                        try {
                            objj = new JSONObject(objError.string());

                            String status = objj.getString("status");
                            String message = objj.getString("message");
                            String code = objj.getString("code");

                            responseStatus.setCode(Integer.parseInt(code));
                            responseStatus.setMsg(message);
                            responseStatus.setStatus(status);

                            responseStatus.onResult(responseStatus);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {

                        responseStatus.setCode(API_HIT_FAILED);
                        responseStatus.setMsg(response.message());
                        responseStatus.setStatus(STATUS_FAILED);

                        responseStatus.onResult(responseStatus);
                    }
                }


            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUserResult>> call, Throwable t) {

                t.toString();



                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }





    //PayEx ActivityDaily

    public  static    void   PayExPayment(PayExSendData payExSendData,final ResponseStatus responseStatus)
    {
        String token = "9aa265905ebc7d51dc7c89efacbcab79e72b2ba362f81c58e6a8680fe522d562";

        HashMap<String,PayExSendData> hm = new HashMap();
        hm.put("payment",payExSendData);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://api.externalintegration.payex.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService client = retrofit.create(APIService.class);

        Call<PayExAllResponceData> calltargetResponce = client.PayExPayements("application/json;charset=UTF-8", "Bearer "+token,payExSendData);

        calltargetResponce.enqueue(new Callback<PayExAllResponceData>() {
            @Override
            public void onResponse(Call<PayExAllResponceData> call, retrofit2.Response<PayExAllResponceData> response) {
                String responseSuccessful = String.valueOf(response.isSuccessful());
                PayExAllResponceData UserResponse = response.body();

                i("Responce Sucessfull::", responseSuccessful);
                i("JSON", response.message());
                i("Responce link", UserResponse.getOperations().get(2).getHref());

                responseStatus.setStatus(response.message());
                responseStatus.setCode(response.code());
                responseStatus.setMsg(UserResponse.getOperations().get(2).getHref());
                responseStatus.setOrdernumber(UserResponse.getPayment().getNumber());
                responseStatus.onResult(responseStatus);
            }
            @Override
            public void onFailure(Call<PayExAllResponceData> call, Throwable t) {
                responseStatus.setStatus(t.getMessage());
                responseStatus.onResult(responseStatus);
                i("Failed", t.toString());

            }
        });


    }

    //PayEx Invoice Payment

    public  static    void   PayExInvoicePayment(PayExInvoiceSendData payExInvoiceSendData, final ResponseStatus responseStatus)
    {
        String token = "9aa265905ebc7d51dc7c89efacbcab79e72b2ba362f81c58e6a8680fe522d562";




        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("https://api.externalintegration.payex.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService client = retrofit.create(APIService.class);

        Call<PayExInvoiceResponceData> calltargetResponce = client.PayExInvoicePayements("application/json;charset=UTF-8", "Bearer "+token,payExInvoiceSendData);

        calltargetResponce.enqueue(new Callback<PayExInvoiceResponceData>() {
            @Override
            public void onResponse(Call<PayExInvoiceResponceData> call, retrofit2.Response<PayExInvoiceResponceData> response) {
                String responseSuccessful = String.valueOf(response.isSuccessful());
                PayExInvoiceResponceData UserResponse = response.body();

                i("Responce Sucessfull::", responseSuccessful);
                i("JSON", response.message());
                i("Responce link", UserResponse.getOperations().get(3).getHref());

                responseStatus.setStatus(response.message());
                responseStatus.setCode(response.code());
                responseStatus.setMsg(UserResponse.getOperations().get(3).getHref());
                responseStatus.setOrdernumber(UserResponse.getPayment().getNumber());
                responseStatus.onResult(responseStatus);
            }
            @Override
            public void onFailure(Call<PayExInvoiceResponceData> call, Throwable t) {
                responseStatus.setStatus(t.getMessage());
                responseStatus.onResult(responseStatus);
                i("Failed", t.toString());

            }
        });


    }






    //Add ActivityDaily
    public  static    void   addActivity(Realm_User user,String token,String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location,String distance,String weight,String Wasit,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();

        MultipartBody.Part body=null;
        if(activity_image!=null)
        {
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), activity_image);
            body = MultipartBody.Part.createFormData("activity_image", activity_image.getName(), reqFile);
        }
       // String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();


        String   userId=user.getId();

        RequestBody title = createPartFromString(activity_title);
        RequestBody id = createPartFromString(activity_type_id);
        RequestBody steps = createPartFromString(steps_count);
        RequestBody days = createPartFromString(day_name);
        RequestBody star = createPartFromString(stars_count);
        RequestBody calor = createPartFromString(calories);
        RequestBody time = createPartFromString(user_activity_time);
        RequestBody Lat = createPartFromString(activity_lat);
        RequestBody Long = createPartFromString(activity_long);
        RequestBody Loc = createPartFromString(activity_location);
        RequestBody idBody = createPartFromString(userId);
        RequestBody distanc = createPartFromString(distance);
        RequestBody weightBody = createPartFromString(weight);
        RequestBody WasitBody = createPartFromString(Wasit);
        RequestBody TimeZone = createPartFromString(tz.getID());






        HashMap<String,RequestBody> hm=new HashMap();
        hm.put(USERID,idBody);
        hm.put(ACTIVITY_TITLE,title);
        hm.put(ACTIVITY_ID,id);
        hm.put(STEPS_COUNT,steps);
       // hm.put(DAY_NAME,days);
        hm.put(STARS_COUNT,star);
        hm.put(ACTIVITY_CALORIES,calor);
        hm.put(ACTIVITY_TIME,time);
      //  hm.put(ACTIVITY_IMAGE,activity_image);
        hm.put(ACTIVITY_LAT,Lat);
        hm.put(ACTIVITY_LONG,Long);
        hm.put(ACTIVITY_LOCATION,Loc);
        hm.put(ACTIVITY_DISTANCE,distanc);
        hm.put(ACTIVITY_WEIGHT_LOSS,weightBody);
        hm.put(ACTIVITY_WAIST_LOSS,WasitBody);
        hm.put(TIME_ZONE,TimeZone);



        Call<Generic_Result<Add_Activity>> call = mWebService.AddActivity_(token,hm,body);

        call.enqueue(new Callback<Generic_Result<Add_Activity>>() {
            @Override
            public void onResponse(Call<Generic_Result<Add_Activity>> call, Response<Generic_Result<Add_Activity>> response) {

                Generic_Result<Add_Activity> obj = null;

                obj = response.body();

                if(response.code()==HttpStatus.HTTP_OK) {

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);


                    //Log.i("ADD Act APi Scuess ::",obj.getMsg());

                    AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,true);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                    AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,false);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);

                    AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,false);

                }




            }

            @Override
            public void onFailure(Call<Generic_Result<Add_Activity>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);

                i("ADD Act APi Fail ::",t.getMessage());

                AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,false);
            }
        });


    }
    public  static    void   addActivityV3(Realm_User user,String token,String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location,String distance,String weight,String Wasit,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        ActivityDaily activityDaily =new ActivityDaily();
        activityDaily.setmTitle(activity_title);
        activityDaily.setmType(Integer.parseInt(activity_type_id));
        activityDaily.setmSteps(Integer.parseInt(steps_count));
        activityDaily.setmDate(user_activity_time);
        activityDaily.setmStars(Integer.parseInt(stars_count));
        activityDaily.setmCalories(Float.parseFloat(calories));
        activityDaily.setmDistance(Double.parseDouble(distance));
        activityDaily.setmMediaImage(activity_image.getAbsolutePath());
        activityDaily.setmLatitude(Double.parseDouble(activity_lat));
        activityDaily.setmLongitude(Double.parseDouble(activity_long));
        activityDaily.setmLocationAddress(activity_location);
        activityDaily.setmWeight(Double.parseDouble(weight));
        activityDaily.setmWaist(Integer.parseInt(Wasit));

        MultipartBody.Part body=null;
        if(activity_image!=null)
        {
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), activity_image);
            body = MultipartBody.Part.createFormData("activity_image", activity_image.getName(), reqFile);
        }
        // String user_activity_time, File activity_image, String activity_lat, String activity_long, String activity_location

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();


        String   userId=user.getId();

        RequestBody title = createPartFromString(activity_title);
        RequestBody id = createPartFromString(activity_type_id);
        RequestBody steps = createPartFromString(steps_count);
        RequestBody days = createPartFromString(day_name);
        RequestBody star = createPartFromString(stars_count);
        RequestBody calor = createPartFromString(calories);
        RequestBody time = createPartFromString(user_activity_time);
        RequestBody Lat = createPartFromString(activity_lat);
        RequestBody Long = createPartFromString(activity_long);
        RequestBody Loc = createPartFromString(activity_location);
        RequestBody idBody = createPartFromString(userId);
        RequestBody distanc = createPartFromString(distance);
        RequestBody weightBody = createPartFromString(weight);
        RequestBody WasitBody = createPartFromString(Wasit);
        RequestBody TimeZone = createPartFromString(tz.getID());





        HashMap<String,RequestBody> hm=new HashMap();
        hm.put(USERID,idBody);
        hm.put(ACTIVITY_TITLE,title);
        hm.put(ACTIVITY_ID,id);
        hm.put(STEPS_COUNT,steps);
        // hm.put(DAY_NAME,days);
        hm.put(STARS_COUNT,star);
        hm.put(ACTIVITY_CALORIES,calor);
        hm.put(ACTIVITY_TIME,time);
        //  hm.put(ACTIVITY_IMAGE,activity_image);
        hm.put(ACTIVITY_LAT,Lat);
        hm.put(ACTIVITY_LONG,Long);
        hm.put(ACTIVITY_LOCATION,Loc);
        hm.put(ACTIVITY_DISTANCE,distanc);
        hm.put("weight",weightBody);
        hm.put(ACTIVITY_WAIST_LOSS,WasitBody);
        hm.put(TIME_ZONE,TimeZone);



        Call<Generic_Result<ActivityDaily>> call = mWebService.AddActivityV3(token,hm,body);

        call.enqueue(new Callback<Generic_Result<ActivityDaily>>() {
            @Override
            public void onResponse(Call<Generic_Result<ActivityDaily>> call, Response<Generic_Result<ActivityDaily>> response) {

                Generic_Result<ActivityDaily> obj = null;

                obj = response.body();

                if(response.code()==HttpStatus.HTTP_OK) {

                    AddActivityLocalV3(user,obj.getResult(),activity_lat,activity_long,true,responseStatus);
               /*     final Handler handler = new Handler();
                    Generic_Result<ActivityDaily> finalObj = obj;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms

                        }
                    }, 5000);*/

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                    //Log.i("ADD Act APi Scuess ::",obj.getMsg());
                    //AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,true);
                }
                else if(obj!=null)
                {
                    try{
                        AddActivityLocalV3(user,obj.getResult(),activity_lat,activity_long,false,responseStatus);
                        responseStatus.setCode(obj.getCode());
                        responseStatus.setMsg(obj.getMsg());
                        responseStatus.setStatus(obj.getStatus());
                        responseStatus.onResult(responseStatus);
                    }catch (Exception e){

                    }

                    //AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,false);
                }
                else
                {
                    //AddActivityLocalV3(user,obj.getResult(),activity_lat,activity_long,false,responseStatus);
                   /* final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms

                        }
                    }, 5000);*/
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                    //AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,false);

                }




            }

            @Override
            public void onFailure(Call<Generic_Result<ActivityDaily>> call, Throwable t) {

                t.toString();

                AddActivityLocalV3(user,activityDaily,activity_lat,activity_long,false,responseStatus);

                //AddActivityLocal(user, Double.valueOf(weight),Double.valueOf(Wasit),activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_image.getAbsolutePath(),activity_lat,activity_long,activity_location,false);

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);




                i("ADD Act APi Fail ::",t.getMessage());
                //AddActivityLocalV3(obj.getResult(),true);

            }
        });


    }

    //Send User Steps to APi

    public  static    void   addActivitySteps(Realm_User user,String token,String activity_title, String activity_type_id, String steps_count, String day_name, String stars_count, String calories, String user_activity_time, String activity_lat, String activity_long, String activity_location,String distance,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();





        String   userId=user.getId();




        HashMap<String,String> hm=new HashMap();
        hm.put(USERID,userId);
        hm.put(ACTIVITY_TITLE,activity_title);
        hm.put(ACTIVITY_ID,activity_type_id);
        hm.put(STEPS_COUNT,steps_count);
        // hm.put(DAY_NAME,days);
        hm.put(STARS_COUNT,stars_count);
        hm.put(ACTIVITY_CALORIES,calories);
        hm.put(ACTIVITY_TIME,user_activity_time);
        //hm.put(ACTIVITY_IMAGE,"Null");
        hm.put(ACTIVITY_LAT,activity_lat);
        hm.put(ACTIVITY_LONG,activity_long);
        hm.put(ACTIVITY_LOCATION,activity_location);
        hm.put("distance",distance);




        Call<Generic_Result<Add_Activity>> call = mWebService.AddActivitySteps_(token,hm);

        call.enqueue(new Callback<Generic_Result<Add_Activity>>() {
            @Override
            public void onResponse(Call<Generic_Result<Add_Activity>> call, Response<Generic_Result<Add_Activity>> response) {

                Generic_Result<Add_Activity> obj = null;

                obj = response.body();

                if(response.code()==HttpStatus.HTTP_OK) {

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);


                    i("ADD Act APi Scuess ::",obj.getMsg());
                    AddActivityUserStepsLocal(user,activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_lat,activity_long,activity_location,true);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                    AddActivityUserStepsLocal(user,activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_lat,activity_long,activity_location,false);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                    AddActivityUserStepsLocal(user,activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_lat,activity_long,activity_location,false);

                }




            }

            @Override
            public void onFailure(Call<Generic_Result<Add_Activity>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);

                i("ADD Act APi Fail ::",t.getMessage());

                AddActivityUserStepsLocal(user,activity_title,activity_type_id,steps_count,day_name,stars_count,calories,user_activity_time,activity_lat,activity_long,activity_location,false);
            }
        });



    }


    public  static    void   addChaseStarAndSteps(Realm_User user,String token,  String stars_count,String steps_count, String calories,String Date,String distance,String timer,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();





        String   userId=user.getId();




        HashMap<String,String> hm=new HashMap();
        hm.put(USERID,userId);
        hm.put(STEPS_COUNT,steps_count);
        hm.put(STARS_COUNT,stars_count);
        hm.put(ACTIVITY_CALORIES,calories);
        hm.put(ACTIVITY_TIME,Date);
        hm.put(ACTIVITY_TIMER,timer);
        hm.put(ACTIVITY_DISTANCE,distance);





        Call<Generic_Result<Object>> call = mWebService.AddStarSteps_(token,hm);

        call.enqueue(new Callback<Generic_Result<Object>>() {
            @Override
            public void onResponse(Call<Generic_Result<Object>> call, Response<Generic_Result<Object>> response) {

                Generic_Result<Object> obj = null;

                obj = response.body();

                if(response.code()==HttpStatus.HTTP_OK) {

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);


                    i("ADD Act APi Scuess ::",obj.getMsg());
                    AddUserStepsStarsLocal(steps_count,stars_count,calories,Date,distance,timer,true);
                }
                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);
                    AddUserStepsStarsLocal(steps_count,stars_count,calories,Date,distance,timer,false);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                    AddUserStepsStarsLocal(steps_count,stars_count,calories,Date,distance,timer,false);

                }




            }

            @Override
            public void onFailure(Call<Generic_Result<Object>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);

                i("ADD Act APi Fail ::",t.getMessage());

                AddUserStepsStarsLocal(steps_count,stars_count,calories,Date,distance,timer,false);
            }
        });



    }








    /* Edit User  Repository*/

    public  static    void   editUser(String fname,String lname,String password,String email,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm      mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        HashMap<String,String> hm=new HashMap();
        hm.put(L_NAME,lname);
        hm.put(F_NAME,fname);
        hm.put(EMAIL,email);
        hm.put(PASSWORD,password);


        Call<Generic_Result<String>> call = mWebService.userUpdate_(hm);

        call.enqueue(new Callback<Generic_Result<String>>() {
            @Override
            public void onResponse(Call<Generic_Result<String>> call, Response<Generic_Result<String>> response) {

                Generic_Result<String> obj = null;

                obj = response.body();


                responseStatus.setCode(obj.getCode());
                responseStatus.setMsg(obj.getMsg());
                responseStatus.setStatus(obj.getStatus());
                responseStatus.onResult(responseStatus);


            }

            @Override
            public void onFailure(Call<Generic_Result<String>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }


    @NonNull
    private static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }



    public static MultipartBody uploadRequestBody(File file) {


        RequestBody photo = RequestBody.create(MediaType.parse("application/image"), file);
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("upload", file.getName(),photo)
                .build();
    }




    public static void UpdateLoginProfile(Realm_User mUser,String DOB, String Gender, String Height, String weight, String Wasit,String GoalWeight,final ResponseStatus responseStatus){

        APIService mWebService;
        Realm      mLocalService;
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();

        String  token = "bearer "+mUser.getToken();

        HashMap<String,String> hm=new HashMap();
        hm.put(F_SIGNUP_NAME,mUser.getF_name());
        hm.put(L_SIGNUP_NAME,mUser.getL_name());
        hm.put(GENDER,Gender);
        hm.put(DATE_OF_BIRTH,DOB);
        hm.put(HEIGHT,Height);
        hm.put(WEIGHT,weight);
        hm.put(EMAIL,mUser.getEmail());
        hm.put("waist",Wasit);
        hm.put("weight_goal",GoalWeight);
        hm.put(USERID,mUser.getId());

        Call<Generic_Result<RemoteUser>> call = mWebService.updateLoginProfile(token,hm);

        call.enqueue(new Callback<Generic_Result<RemoteUser>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUser>> call, Response<Generic_Result<RemoteUser>> response) {

                Generic_Result<RemoteUser> obj = null;

                obj = response.body();


                if(response.code() == HttpStatus.HTTP_OK) {

                    updateUserLocalProfileV3final(mLocalService,mUser,obj,obj.getResult(),true,responseStatus);
                    //updateUserLocalProfileLogin(mUser,Gender,DOB,Double.parseDouble(Height),Double.parseDouble(weight),Double.parseDouble(Wasit),true);

                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                    //updateUserLocalProfileLogin(mUser,Gender,DOB,Double.parseDouble(Height),Double.parseDouble(weight),false);
                }

            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUser>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);


            }
        });

    }

    public static void UpdatProfileWithoutImage(Realm_User mUser,String fname,String lname,String DOB, String Gender, String Height, String weight, String Wasit,String GoalWeight,final ResponseStatus responseStatus){

        APIService mWebService;
        Realm      mLocalService;
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();

        String  token = "bearer "+mUser.getToken();

        HashMap<String,String> hm=new HashMap();
        hm.put(F_SIGNUP_NAME,fname);
        hm.put(L_SIGNUP_NAME,lname);
        hm.put(GENDER,Gender);
        hm.put(DATE_OF_BIRTH,DOB);
        hm.put(HEIGHT,Height);
        hm.put(WEIGHT,weight);
        hm.put("waist",Wasit);
        hm.put("weight_goal",GoalWeight);
        hm.put(EMAIL,mUser.getEmail());
        hm.put("user_id",mUser.getId());

        Call<Generic_Result<RemoteUser>> call = mWebService.updateLoginProfile(token,hm);

        call.enqueue(new Callback<Generic_Result<RemoteUser>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUser>> call, Response<Generic_Result<RemoteUser>> response) {

                Generic_Result<RemoteUser> obj = null;

                obj = response.body();


                if(response.code() == HttpStatus.HTTP_OK) {


                    //updateUserLocalProfileLogin(mUser,Gender,DOB,Double.parseDouble(Height),Double.parseDouble(weight),Double.parseDouble(Wasit),true);

                    updateUserLocalProfileV3final(mLocalService,mUser,obj,obj.getResult(),true,responseStatus);
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                    //updateUserLocalProfileLogin(mUser,Gender,DOB,Double.parseDouble(Height),Double.parseDouble(weight),false);
                }

            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUser>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);


            }
        });

    }
    public  static    void   editUserProfileWeb(Realm_User user, String token, File imagePath, String fname, String lname, String pass, String cVc, String gender, String dob, String hight, String weight, String waist,String GoalWeight, final ResponseStatus responseStatus){


        APIService mWebService;
        Realm      mLocalService;
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        HashMap<String,RequestBody> hm=new HashMap();


        MultipartBody.Part body=null;

        if(imagePath!=null && imagePath.length()>0)
        {
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), imagePath);

            body = MultipartBody.Part.createFormData("image", imagePath.getName(), reqFile);
        }

        RequestBody lnameBody=null;
        if(lname!=null){
        lnameBody = createPartFromString(lname);
        }
        RequestBody fnameBody = createPartFromString(fname);
        RequestBody genderBody = createPartFromString(gender);
        RequestBody passBody = createPartFromString(pass);
        RequestBody dobBody = createPartFromString(dob);
        RequestBody heightBody = createPartFromString(hight);
        RequestBody weightBody = createPartFromString(weight);
        RequestBody cvc = createPartFromString(cVc);
        RequestBody waistBody = createPartFromString(waist);
        RequestBody goalWeightBody = createPartFromString(GoalWeight);

        String email=user.getEmail();

        String   userId=user.getId();

        RequestBody emailBody = createPartFromString(email);
        RequestBody idBody = createPartFromString(userId);




            hm.put(F_SIGNUP_NAME,fnameBody);
            hm.put(L_SIGNUP_NAME,lnameBody);
            hm.put(GENDER,genderBody);
            hm.put(DATE_OF_BIRTH,dobBody);
            hm.put(HEIGHT,heightBody);
            hm.put(WEIGHT,weightBody);
            hm.put(EMAIL,emailBody);
            hm.put(CVC,cvc);
            hm.put("user_id",idBody);
            hm.put("waist",waistBody);
            hm.put("weight_goal",goalWeightBody);



        Call<Generic_Result<RemoteUser>> call = mWebService.editProfile(token,hm,body);


        call.enqueue(new Callback<Generic_Result<RemoteUser>>() {
            @Override
            public void onResponse(Call<Generic_Result<RemoteUser>> call, Response<Generic_Result<RemoteUser>> response) {

                Generic_Result<RemoteUser> obj = null;

                obj = response.body();


                if(response.code()==HttpStatus.HTTP_OK) {


                    //updateUserLocalProfile(user,fname,lname,obj.getResult().getUser().getProfileImage(),gender,dob,cVc,Double.parseDouble(hight),Double.parseDouble(waist),Double.parseDouble(weight),true);
                    updateUserLocalProfileV3final(mLocalService,user,obj,obj.getResult(),true,responseStatus);
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());
                    responseStatus.onResult(responseStatus);

              /*    if(imagePath!=null) {

                    }else{
                        updateUserLocalProfile(user,fname,lname,user.getProfile_image(),gender,dob,cVc,Integer.parseInt(hight),Integer.parseInt(weight),true);
                    }*/
                    //updateUserLocalProfile(user, fname, lname, imagePath.getAbsolutePath(), gender, dob, cVc, Integer.parseInt(hight), Integer.parseInt(weight), true);
                }
                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg("Profile Not Updated");
                    responseStatus.setStatus(STATUS_FAILED);
                    responseStatus.onResult(responseStatus);
                    String tempPath="";
                    if(imagePath!=null)
                    {
                        tempPath=imagePath.getAbsolutePath();
                    }
                    //updateUserLocalProfile(user,fname,lname,tempPath,gender,dob,cVc,Double.parseDouble(hight),Double.parseDouble(weight),Double.parseDouble(waist),false);
                    updateUserLocalProfileV3final(mLocalService,user,obj,obj.getResult(),false,responseStatus);
                }


/*
                ResponseBody objError=response.errorBody();

                JSONObject objj=null;

                if(objError!=null) {
                    try {
                        objj = new JSONObject(objError.string());


                        String userLoginstatus=objj.getString("error");

                        if(userLoginstatus==null) {

                            String status = objj.getString("status");
                            String message = objj.getString("message");
                            String code = objj.getString("code");


                            responseStatus.setCode(Integer.parseInt(code));
                            responseStatus.setMsg(message);
                            responseStatus.setStatus(status);

                           // Log.i("API hit J F",message);
                        }
                        else
                        {
                            responseStatus.setCode(API_HIT_FAILED);
                            responseStatus.setMsg(userLoginstatus);
                            responseStatus.setStatus(STATUS_FAILED);
                            //Log.i("API hit F F",userLoginstatus);

                        }

                            responseStatus.onResult(responseStatus);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {

                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);

                    responseStatus.onResult(responseStatus);
                }



*/

            }

            @Override
            public void onFailure(Call<Generic_Result<RemoteUser>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);
                responseStatus.onResult(responseStatus);
                String tempPath="";
                if(imagePath!=null)
                {
                    tempPath=imagePath.getAbsolutePath();
                }

                //updateUserLocalProfile(user,fname,lname,tempPath,gender,dob,cVc,Double.parseDouble(hight),Double.parseDouble(weight),Double.parseDouble(waist),false);

            }
        });


    }
    public static void  SyncPastActivitiesLocalV1(List<PastActivityModel> pastActivitiesListSyncWithServer,List<ActivityDaily> pastActivitiesListLocal){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        List<ActivityDaily>  activityDailyAllData1 = new ArrayList();
        final List<ActivityDaily>  activityDailyAllData = mLocalService.where(ActivityDaily.class)
                .equalTo("isSyncedWithServer", false)
                .sort("mDate", Sort.ASCENDING)
                .findAll();

        activityDailyAllData1.addAll(activityDailyAllData);

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                try{
                    for(int i=0; i<pastActivitiesListSyncWithServer.size();i++){

                        if(pastActivitiesListSyncWithServer.get(i).getCode()==200){
                            activityDailyAllData1.get(i).setmId(pastActivitiesListSyncWithServer.get(i).getResult().getmId());
                            if(pastActivitiesListSyncWithServer.get(i).getResult().getmLocationAddress()!=null){
                                activityDailyAllData1.get(i).setmLocationAddress(pastActivitiesListSyncWithServer.get(i).getResult().getmLocationAddress());
                            }else{
                                activityDailyAllData1.get(i).setmLocationAddress("N/A");
                            }
                            if(pastActivitiesListSyncWithServer.get(i).getResult().getmMediaImage()!=null){
                                activityDailyAllData1.get(i).setmMediaImage(pastActivitiesListSyncWithServer.get(i).getResult().getmMediaImage());
                            }else{
                                activityDailyAllData1.get(i).setmMediaImage(null);
                            }
                            activityDailyAllData1.get(i).setmSteps(pastActivitiesListSyncWithServer.get(i).getResult().getmSteps());
                            activityDailyAllData1.get(i).setmStars(pastActivitiesListSyncWithServer.get(i).getResult().getmStars());
                            activityDailyAllData1.get(i).setmCalories(pastActivitiesListSyncWithServer.get(i).getResult().getmCalories());
                            activityDailyAllData1.get(i).setmDistance(pastActivitiesListSyncWithServer.get(i).getResult().getmDistance());
                            activityDailyAllData1.get(i).setmDate(pastActivitiesListSyncWithServer.get(i).getResult().getmDate());
                            activityDailyAllData1.get(i).setmWeight(pastActivitiesListSyncWithServer.get(i).getResult().getmWeight());
                            activityDailyAllData1.get(i).setmWaist(pastActivitiesListSyncWithServer.get(i).getResult().getmWaist());
                            activityDailyAllData1.get(i).setmLatitude(pastActivitiesListSyncWithServer.get(i).getResult().getmLatitude());
                            activityDailyAllData1.get(i).setmLongitude(pastActivitiesListSyncWithServer.get(i).getResult().getmLongitude());
                            activityDailyAllData1.get(i).setSyncedWithServer(true);

                            realm.insertOrUpdate(activityDailyAllData1.get(i));

                        }else{
                            activityDailyAllData1.get(i).setSyncedWithServer(true);
                            realm.insertOrUpdate(activityDailyAllData1.get(i));
                        }
                    }
                }catch (Exception e){
                    i("UpdateEx",e.toString());
                }
            }
        });
    }

    public static void  SyncPastActivitiesLocal(List<PastActivityModel> pastActivitiesListSyncWithServer,List<ActivityDaily> pastActivitiesListLocal){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

    }
    public static ActivityDaily getStepCounterLocalV3(){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        SimpleDateFormat sdf5 = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime = sdf5.format(new Date());

        final List<ActivityDaily> activityDaily1= mLocalService.where(ActivityDaily.class)
                .findAll();

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTimeStart = sdf.format(new Date());
            String currentDateandTimeEnd = sdf.format(new Date());
            for(int i = 0; i<activityDaily1.size();i++){
                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart+" 00:00:00");
                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd+" 23:59:59");
                Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityDaily1.get(i).getmDate()));
                if(ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange) && ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange)){

                    return activityDaily1.get(i);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            i("RealmUpdateFail",e.toString());
        }
        return null;
    }

    public static void AddStepCounterLocalV3(ActivityDaily activityDaily){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try{

            final List<ActivityDaily> activityDailyCurrentDayRecord = new ArrayList();

            final List<ActivityDaily> activityDailyAllData = mLocalService.where(ActivityDaily.class)
                    .findAll();

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTimeStart = sdf.format(new Date());
            String currentDateandTimeEnd = sdf.format(new Date());
            for (int i = 0; i < activityDailyAllData.size(); i++){
                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart + " 00:00:00");
                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd + " 23:59:59");
                Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(activityDailyAllData.get(i).getmDate()));
                if ((ActiviyDateLocal.after(currentDateandTimeStartRange) || ActiviyDateLocal.equals(currentDateandTimeStartRange)) && (ActiviyDateLocal.before(currentDateandTimeEndRange) || ActiviyDateLocal.equals(currentDateandTimeEndRange))) {
                    activityDailyCurrentDayRecord.add(activityDailyAllData.get(i));
                }
            }
            if(activityDailyCurrentDayRecord.size()>0){

                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        try{
                            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                            String currentDateandTimeStart = sdf.format(new Date());
                            String currentDateandTimeEnd = sdf.format(new Date());
                            for(int i = 0; i<activityDailyCurrentDayRecord.size();i++){
                                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart+" 00:00:00");
                                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd+" 23:59:59");
                                Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityDailyCurrentDayRecord.get(i).getmDate()));
                                if((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))){

                                    activityDailyCurrentDayRecord.get(i).setmSteps(activityDaily.getmSteps());
                                    activityDailyCurrentDayRecord.get(i).setmStars(activityDaily.getmStars());
                                    activityDailyCurrentDayRecord.get(i).setmCalories(activityDaily.getmCalories());
                                    activityDailyCurrentDayRecord.get(i).setmDistance(activityDaily.getmDistance());
                                    activityDailyCurrentDayRecord.get(i).setmDate(activityDaily.getmDate());
                                    activityDailyCurrentDayRecord.get(i).setSyncedWithServer(false);

                                    realm.insertOrUpdate(activityDailyCurrentDayRecord.get(i));

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            i("ActivityRealmUpdateFail",e.toString());
                        }

                    }});

            }else{
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        activityDaily.setmMediaImage(null);
                        activityDaily.setSyncedWithServer(false);
                        realm.insertOrUpdate(activityDaily);
                    }
                });
            }
        }catch (Exception e){

           /* Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(e.getStackTrace()));
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);*/
        }



       /* final List<ActivityDaily> activityDaily1= mLocalService.where(ActivityDaily.class)
                .contains("mDate",convertDateToUTC())
                .findAll();*/


    }


    public static void AddStepCounterGoogleFitPastActiviesLocalV3(ActivityDaily activityDaily){
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        try{

                final List<ActivityDaily> activityPastDayRecord = new ArrayList();

                final List<ActivityDaily> activityDailyAllData = mLocalService.where(ActivityDaily.class)
                        .findAll();

                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);

                String googleFitActivityDate = convertTimeFormat(activityDaily.getmDate());

                for (int i = 0; i < activityDailyAllData.size(); i++){
                    Date googleFitActivityTimeStartRange = sdf1.parse(googleFitActivityDate + " 00:00:00");
                    Date googleFitActivityTimeEndRange = sdf1.parse(googleFitActivityDate + " 23:59:59");
                    Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(activityDailyAllData.get(i).getmDate()));
                    if ((ActiviyDateLocal.after(googleFitActivityTimeStartRange) || ActiviyDateLocal.equals(googleFitActivityTimeStartRange)) && (ActiviyDateLocal.before(googleFitActivityTimeEndRange) || ActiviyDateLocal.equals(googleFitActivityTimeEndRange))) {
                        activityPastDayRecord.add(activityDailyAllData.get(i));
                    }
                }

                if(activityPastDayRecord.size()>0){

                    mLocalService.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            try{
                                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                                SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                                String googleFitActivityDate = convertTimeFormat(activityDaily.getmDate());
                                for(int i = 0; i<activityPastDayRecord.size();i++){
                                    Date googleFitActivityTimeStartRange = sdf1.parse(googleFitActivityDate + " 00:00:00");
                                    Date googleFitActivityTimeEndRange = sdf1.parse(googleFitActivityDate + " 23:59:59");
                                    Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityPastDayRecord.get(i).getmDate()));
                                    if((ActiviyDate.after(googleFitActivityTimeStartRange) || ActiviyDate.equals(googleFitActivityTimeStartRange)) && (ActiviyDate.before(googleFitActivityTimeEndRange) || ActiviyDate.equals(googleFitActivityTimeEndRange))){

                                        activityPastDayRecord.get(i).setmSteps(activityDaily.getmSteps());
                                        activityPastDayRecord.get(i).setmStars(activityDaily.getmStars());
                                        activityPastDayRecord.get(i).setmCalories(activityDaily.getmCalories());
                                        activityPastDayRecord.get(i).setmDistance(activityDaily.getmDistance());
                                        activityPastDayRecord.get(i).setmDate(activityDaily.getmDate());
                                        activityPastDayRecord.get(i).setSyncedWithServer(false);

                                        realm.insertOrUpdate(activityPastDayRecord.get(i));

                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                i("ActivityRealmUpdateFail",e.toString());
                            }

                        }});

                }else{
                    mLocalService.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            activityDaily.setmMediaImage(null);
                            activityDaily.setSyncedWithServer(false);
                            realm.insertOrUpdate(activityDaily);
                        }
                    });
                }

        }catch (Exception e){
            Log.i("GoogleFitLocal",e.toString());
        }
    }


    public static  Boolean AddActivityLocalV3(Realm_User user,ActivityDaily activityDaily,String Activity_Lat,String Activity_Long,Boolean SyncedWithServer,final ResponseStatus responseStatus) {
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        /*final List<ActivityDaily> activityDaily1= mLocalService.where(ActivityDaily.class)
                *//*.equalTo(Constants.DAILY_ACTIVITY_DATE_TAG,activityDaily.getmDate())*//*
                .contains("mDate",convertDateToUTC())
                .findAll();
*/
        try{

            mLocalService.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Realm_User db_user = user;

                    db_user.setWeight(activityDaily.getmWeight());
                    db_user.setWaist(activityDaily.getmWaist());

                    realm.insertOrUpdate(db_user);


                }
            });

            final List<ActivityDaily> activityDailyCurrentDayRecord = new ArrayList();

            final List<ActivityDaily> activityDailyAllData= mLocalService.where(ActivityDaily.class)
                    .findAll();

            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateandTimeStart = sdf.format(new Date());
            String currentDateandTimeEnd = sdf.format(new Date());
            for (int i = 0; i < activityDailyAllData.size(); i++){
                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart + " 00:00:00");
                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd + " 23:59:59");
                Date ActiviyDateLocal = sdf1.parse(convertUTCToLocalTime(activityDailyAllData.get(i).getmDate()));
                if ((ActiviyDateLocal.after(currentDateandTimeStartRange) || ActiviyDateLocal.equals(currentDateandTimeStartRange)) && (ActiviyDateLocal.before(currentDateandTimeEndRange) || ActiviyDateLocal.equals(currentDateandTimeEndRange))) {
                    activityDailyCurrentDayRecord.add(activityDailyAllData.get(i));
                }
            }
            if(activityDailyCurrentDayRecord.size()>0){
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        try{
                            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                            String currentDateandTimeStart = sdf.format(new Date());
                            String currentDateandTimeEnd = sdf.format(new Date());
                            for(int i = 0; i<activityDailyCurrentDayRecord.size();i++){

                                //ActivityDaily activityDaily2 = activityDaily1.get(i);
                                Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart+" 00:00:00");
                                Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd+" 23:59:59");
                                Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityDailyCurrentDayRecord.get(i).getmDate()));
                                if((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange)|| ActiviyDate.equals(currentDateandTimeEndRange))){

                                    //ActivityDaily updateActivityData = new ActivityDaily();
                                    activityDailyCurrentDayRecord.get(i).setmMediaImage(activityDaily.getmMediaImage());
                                    activityDailyCurrentDayRecord.get(i).setmWaist(activityDaily.getmWaist());
                                    activityDailyCurrentDayRecord.get(i).setmWeight(activityDaily.getmWeight());
                                    activityDailyCurrentDayRecord.get(i).setmLongitude(activityDaily.getmLongitude());
                                    activityDailyCurrentDayRecord.get(i).setmLatitude(activityDaily.getmLatitude());
                                    activityDailyCurrentDayRecord.get(i).setmSteps(activityDaily.getmSteps());
                                    activityDailyCurrentDayRecord.get(i).setmStars(activityDaily.getmStars());
                                    activityDailyCurrentDayRecord.get(i).setmCalories(activityDaily.getmCalories());
                                    activityDailyCurrentDayRecord.get(i).setmDistance(activityDaily.getmDistance());
                                    activityDailyCurrentDayRecord.get(i).setmDate(activityDaily.getmDate());
                                    activityDailyCurrentDayRecord.get(i).setmLocationAddress(activityDaily.getmLocationAddress());
                                    activityDailyCurrentDayRecord.get(i).setmTitle(activityDaily.getmTitle());
                                    activityDailyCurrentDayRecord.get(i).setmType(activityDaily.getmType());
                                    activityDailyCurrentDayRecord.get(i).setSyncedWithServer(SyncedWithServer);

                                    realm.insertOrUpdate(activityDailyCurrentDayRecord);

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            i("RealmUpdateFail",e.toString());
                        }

                    }});

            }else{
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        activityDaily.setSyncedWithServer(SyncedWithServer);
                        realm.insert(activityDaily);
                    }
                });
            }

        }catch (Exception e){

        }




        boolean localDbSync = false;


        return localDbSync;
    }

    public static  void AddActivityLocal(final Realm_User  user,final Double weight,final Double waist,final String activity_title,final String activity_type_id,final String steps_count,final String day_name,final String stars_count,final String calories,final String user_activity_time,final String activity_image,final String activity_lat,final String activity_long,final String activity_location,Boolean SyncedWithServer){

        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        final Realm_User Date= mLocalService.where(Realm_User.class)
                .equalTo(Constants.DAILY_ACTIVITY_DATE_TAG,user_activity_time)
                .findFirst();

            mLocalService.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Realm_User db_user = user;

                    db_user.setWeight(weight);
                    db_user.setWaist(waist);

                    realm.insertOrUpdate(db_user);


                }
            });

        if(Date!= null){

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     Realm_User db_user = user;

                                                     db_user.setActivity_title(activity_title);
                                                     db_user.setActivity_type_id(activity_type_id);
                                                     db_user.setSteps_count(steps_count);
                                                     db_user.setDay_name(day_name);
                                                     db_user.setStars_count(stars_count);
                                                     db_user.setCalories(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setActivity_image(activity_image);
                                                     db_user.setActivity_lat(activity_lat);
                                                     db_user.setActivity_long(activity_long);
                                                     db_user.setActivity_location(activity_location);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
                                             );

        }
        else{

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     Realm_User db_user = realm.createObject(Realm_User.class);

                                                     db_user.setActivity_title(activity_title);
                                                     db_user.setActivity_type_id(activity_type_id);
                                                     db_user.setSteps_count(steps_count);
                                                     db_user.setDay_name(day_name);
                                                     db_user.setStars_count(stars_count);
                                                     db_user.setCalories(calories);
                                                     db_user.setUser_activity_time(user_activity_time);
                                                     db_user.setActivity_image(activity_image);
                                                     db_user.setActivity_lat(activity_lat);
                                                     db_user.setActivity_long(activity_long);
                                                     db_user.setActivity_location(activity_location);
                                                     db_user.setWeight(weight);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);
                                                     //realm.insert(db_user);


                                                 }
                                             }
            );

        }

    }


    public static  void AddUserStepsStarsLocal(final String steps_count,final String stars_count,final String calories,final String user_activity_time,String distance,String timer,Boolean SyncedWithServer){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        final UserJourneyData Date= mLocalService.where(UserJourneyData.class)
                .equalTo(Constants.USER_TODAY_DATE,user_activity_time)

                .findFirst();


        mLocalService.executeTransaction(new Realm.Transaction() {
                                             @Override
                                             public void execute(Realm realm) {

                                                 UserJourneyData db_user = realm.createObject(UserJourneyData.class);

                                                 db_user.setLocatStepCount(Integer.valueOf(steps_count));
                                                 db_user.setLocatStarCount(Integer.parseInt(stars_count));
                                                 db_user.setLocatCalCount(Double.parseDouble(calories));
                                                 db_user.setLocalDate(user_activity_time);
                                                 db_user.setLocalDistance(distance);
                                                 db_user.setLocalTimer(timer);
                                                 db_user.setSyncedWithServer(SyncedWithServer);

                                                 //realm.insertOrUpdate(db_user);
                                                 realm.insert(db_user);
                                             }
                                         }
        );


        /*if(Date!= null){

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     UserJourneyData db_user = Date;

                                                     db_user.setLocatStepCount(Integer.valueOf(steps_count));
                                                     db_user.setLocatStarCount(Date.getLocatStarCount()+1);
                                                     db_user.setLocatCalCount(Double.parseDouble(calories));
                                                     db_user.setLocalDate(user_activity_time);
                                                     db_user.setLocalDistance(distance);
                                                     db_user.setLocalTimer(timer);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);
                                                     //realm.insert(db_user);
                                                 }
                                             }
            );

        }
        else{

            mLocalService.executeTransaction(new Realm.Transaction() {
                                                 @Override
                                                 public void execute(Realm realm) {

                                                     UserJourneyData db_user =realm.createObject(UserJourneyData.class);

                                                     db_user.setLocatStepCount(Integer.valueOf(steps_count));
                                                     db_user.setLocatStarCount(Integer.parseInt(stars_count));
                                                     db_user.setLocatCalCount(Double.parseDouble(calories));
                                                     db_user.setLocalDate(user_activity_time);
                                                     db_user.setLocalDistance(distance);
                                                     db_user.setLocalTimer(timer);
                                                     db_user.setSyncedWithServer(SyncedWithServer);

                                                     realm.insertOrUpdate(db_user);


                                                 }
                                             }
            );

        }
*/

    }

   /* public static void starUpdateFromLogin(String StarCount,String StepsCount){

        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime = sdf.format(new Date());


        mLocalService.executeTransaction(new Realm.Transaction() {
                                             @Override
                                             public void execute(Realm realm) {

                                                 UserActivityData db_user =realm.createObject(UserActivityData.class);

                                                 db_user.setLocatStepCount(Integer.valueOf(StepsCount));
                                                 db_user.setLocatStarCount(Integer.parseInt(StarCount));
                                                 db_user.setLocalDate(currentDateandTime);
                                                 db_user.setSyncedWithServer(true);
                                                 realm.insertOrUpdate(db_user);


                                             }
                                         }
        );


    }*/

    public static  void AddActivityUserStepsLocal(final Realm_User  user,final String activity_title,final String activity_type_id,final String steps_count,final String day_name,final String stars_count,final String calories,final String user_activity_time,final String activity_lat,final String activity_long,final String activity_location,Boolean SyncedWithServer){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
                                             @Override
                                             public void execute(Realm realm) {

                                                 Realm_User db_user = user;

                                                 db_user.setActivity_title(activity_title);
                                                 db_user.setActivity_type_id(activity_type_id);
                                                 db_user.setSteps_count(steps_count);
                                                 db_user.setDay_name(day_name);
                                                 db_user.setStars_count(stars_count);
                                                 db_user.setCalories(calories);
                                                 db_user.setUser_activity_time(user_activity_time);
                                                 db_user.setActivity_lat(activity_lat);
                                                 db_user.setActivity_long(activity_long);
                                                 db_user.setActivity_location(activity_location);

                                                 db_user.setSyncedWithServer(SyncedWithServer);

                                                 realm.insertOrUpdate(db_user);


                                             }
                                         }
        );

    }

    public static   void updateUserLocalProfileV3final(Realm Database,Realm_User user,Generic_Result<RemoteUser> obj,RemoteUser userJson,Boolean SyncedWithServer,final ResponseStatus responseStatus) {

        Database.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                try{


                    Realm_User db_user = user;

                    db_user.setF_name(userJson.getFirstname());

                    db_user.setProfile_image(userJson.getProfileImage());

                    db_user.setL_name(userJson.getLastname());

                    db_user.setUser_gender(userJson.getGender());

                    db_user.setDob(userJson.getDateOfBirth());

                    db_user.setCvc(userJson.getCVC());

                    try {
                        db_user.setHeight_in_cm(Double.parseDouble(userJson.getHeight()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setWeight(Double.parseDouble(userJson.getWeight()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setWaist(Double.parseDouble(userJson.getWaist()));
                    } catch (Exception ex) {
                    }
                    try {
                        db_user.setGoalweight(Double.parseDouble(userJson.getGoalweight()));
                    } catch (Exception ex) {
                    }
                    db_user.setSyncedWithServer(SyncedWithServer);

                    realm.insertOrUpdate(db_user);

                }catch (Exception e){
                    i("ProfileSave",e.toString());
                }

            }
        });

    }

        public static  void updateUserLocalProfile(final Realm_User  user,final String fname,final String lname,final String profile,final String gender,final String dob,final String cvc,final double hight,final double weight,final double waist,Boolean SyncedWithServer){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setF_name(fname);

                db_user.setProfile_image(profile);

                db_user.setL_name(lname);

                db_user.setUser_gender(gender);

                db_user.setDob(dob);

                db_user.setCvc(cvc);

                db_user.setHeight_in_cm(hight);

                db_user.setWeight(weight);

                db_user.setWaist(waist);

                db_user.setSyncedWithServer(SyncedWithServer);

                realm.insertOrUpdate(db_user);


            }
        });

    }

    public static  void updateUserLocalProfileLogin(final Realm_User  user,final String gender,final String dob,final double hight,final double weight,final double waist,Boolean SyncedWithServer){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;



                db_user.setUser_gender(gender);

                db_user.setDob(dob);

                db_user.setHeight_in_cm(hight);

                db_user.setWeight(weight);

                db_user.setWaist(waist);

                db_user.setSyncedWithServer(SyncedWithServer);

                realm.insertOrUpdate(db_user);


            }
        });

    }



    //Saveing Star Local

    public static   void InserUpdateStarCountLocal(final Realm_User user, final String StarCount)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setStars_count(StarCount);

                realm.insertOrUpdate(db_user);

            }
        });
    }



    public static   void updateUserLocalGender(final Realm_User user, final String gender)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setUser_gender(gender);

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public static   void updateUserLocalF_Name(final Realm_User user, final String fname)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setF_name(fname);

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public static   void updateUserLocalL_Name(final Realm_User user, final String lname)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setL_name(lname);

                realm.insertOrUpdate(db_user);

            }
        });
    }

    public static   void updateUserLocalPass(final Realm_User user, final String pass)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setPass(pass);

                realm.insertOrUpdate(db_user);

            }
        });
    }

    public static   void updateUserLocalWeight(final Realm_User user, final int weight)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setWeight(weight);

                realm.insertOrUpdate(db_user);

            }
        });
    }

    public static   void updateUserLocalHeight(final Realm_User user, final int height)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setHeight_in_cm(height);

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public static   void updateUserLocalDOB(final Realm_User user, final String dob)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setDob(dob);

                realm.insertOrUpdate(db_user);

            }
        });
    }

    public static   void updateUserLocal(final Realm_User user)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public static   void updateUserLocalWithRemoteUser(final RemoteUser  rUser,final Realm_User user)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setF_name(rUser.getFirstname());
                db_user.setL_name(rUser.getLastname());
                db_user.setEmail(rUser.getEmail());
                db_user.setPass(rUser.getPassword());

                realm.insertOrUpdate(db_user);

            }
        });
    }


    public static   void updateUserProfile(final Realm_User user, final byte[] profile)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        mLocalService.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Realm_User db_user = user;

                db_user.setProfilePicData(profile);

                realm.insertOrUpdate(db_user);

            }
        });
    }





    /* End Edit User  Repository*/






    /* User Activities */


    public  static    void   getUserActivities(String token,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm   mLocalService;

        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        HashMap<String,String> hm=new HashMap();
        hm.put(TOKEN,token);

        Call<Generic_Result<String>> call = mWebService.getActivities(hm);

        call.enqueue(new Callback<Generic_Result<String>>() {
            @Override
            public void onResponse(Call<Generic_Result<String>> call, Response<Generic_Result<String>> response) {

                Generic_Result<String> obj = null;

                obj = response.body();


                if(obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {

                    addActivitiesLocal(mLocalService,null);

                }

                responseStatus.setCode(obj.getCode());
                responseStatus.setMsg(obj.getMsg());
                responseStatus.setStatus(obj.getStatus());
                responseStatus.onResult(responseStatus);


            }

            @Override
            public void onFailure(Call<Generic_Result<String>> call, Throwable t) {

                t.toString();

                responseStatus.setCode(API_HIT_FAILED);
                responseStatus.setMsg(t.getMessage());
                responseStatus.setStatus(STATUS_FAILED);

                responseStatus.onResult(responseStatus);


            }
        });



    }


    public static   boolean addActivitiesLocal(Realm realm,final JSONArray list)
    {


        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                realm.where(DailyActivityModel.class)
                        .findAll()
                        .deleteAllFromRealm();


                realm.createAllFromJson(DailyActivityModel.class,list);

            }
        });


        return  false;
    }




    /* End User Activities */








    /* Logout  user Repository */



    public  static    void   logoutUser(Context context,Competition competition,Boolean ContestStatus,String token,String userID,String totalSteps,String totalStars,String totalCalories,String distance,String agent,String currentDate,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm mLocalService;

        //mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME,SaveSharedPreference.getBaseURl(context))).getApiService();
        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        //Log.d("Time zone","="+tz.getDisplayName());

        HashMap<String,String> hm=new HashMap();
        if(competition!=null) {
            if (competition.getStartDate() != null) {
                if(ContestStatus) {
                    hm.put(USERID,userID);
                    hm.put("steps_count",totalSteps);
                    hm.put("stars_count",totalStars);
                    hm.put("calories",totalCalories);
                    hm.put(ACTIVITY_DISTANCE,distance);
                    hm.put(DEVICE_MODEL, agent);
                    hm.put("user_activity_time",currentDate);
                    hm.put(TIME_ZONE, tz.getID());
                }else {
                    hm.put(USERID,userID);
                    hm.put(DEVICE_MODEL, agent);
                    hm.put(TIME_ZONE, tz.getID());
                }
            }else{
                hm.put(USERID,userID);
                hm.put(DEVICE_MODEL, agent);
                hm.put(TIME_ZONE, tz.getID());
            }
            }
        Call<Generic_Result<Object>> call = mWebService.logoutUser(token,hm);

        call.enqueue(new Callback<Generic_Result<Object>>() {
            @Override
            public void onResponse(Call<Generic_Result<Object>> call, Response<Generic_Result<Object>> response) {

                Generic_Result<Object> obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {
                    Generic_Result<Object> finalObj = obj;
                    mLocalService.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {


                            realm.deleteAll();

                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            responseStatus.setCode(finalObj.getCode());
                            responseStatus.setMsg(finalObj.getMsg());
                            responseStatus.setStatus(finalObj.getStatus());
                            responseStatus.onResult(responseStatus);
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            responseStatus.setCode(API_HIT_FAILED);
                            responseStatus.setMsg(finalObj.getMsg());
                            responseStatus.setStatus(STATUS_FAILED);
                            responseStatus.onResult(responseStatus);
                        }
                    });
                }

                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);
                }

                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);

                    responseStatus.onResult(responseStatus);
                }
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



    }



    public  static    void   logoutTrainer(Context context,String token,String userID,String agent,final ResponseStatus responseStatus)
    {
        APIService mWebService;
        Realm mLocalService;

        //mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME,SaveSharedPreference.getBaseURl(context))).getApiService();
        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();
        mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        //Log.d("Time zone","="+tz.getDisplayName());

        HashMap<String,String> hm=new HashMap();



        hm.put(USERID,userID);
        hm.put(DEVICE_MODEL, agent);
        hm.put(TIME_ZONE, tz.getID());
        Call<Generic_Result<Object>> call = mWebService.logoutUser(token,hm);

        call.enqueue(new Callback<Generic_Result<Object>>() {
            @Override
            public void onResponse(Call<Generic_Result<Object>> call, Response<Generic_Result<Object>> response) {

                Generic_Result<Object> obj = null;

                obj = response.body();

                if(obj!=null && obj.getCode()== HttpStatus.HTTP_OK  && obj.getStatus().equals(STATUS_SUCCESS)) {
                    Generic_Result<Object> finalObj = obj;
                    mLocalService.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {


                            realm.deleteAll();

                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            responseStatus.setCode(finalObj.getCode());
                            responseStatus.setMsg(finalObj.getMsg());
                            responseStatus.setStatus(finalObj.getStatus());
                            responseStatus.onResult(responseStatus);
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            responseStatus.setCode(API_HIT_FAILED);
                            responseStatus.setMsg(finalObj.getMsg());
                            responseStatus.setStatus(STATUS_FAILED);
                            responseStatus.onResult(responseStatus);
                        }
                    });
                }

                else if(obj!=null)
                {
                    responseStatus.setCode(obj.getCode());
                    responseStatus.setMsg(obj.getMsg());
                    responseStatus.setStatus(obj.getStatus());

                    responseStatus.onResult(responseStatus);
                }

                else
                {
                    responseStatus.setCode(API_HIT_FAILED);
                    responseStatus.setMsg(response.message());
                    responseStatus.setStatus(STATUS_FAILED);

                    responseStatus.onResult(responseStatus);
                }
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



    }






    /*End Logout  Repository*/






    /* Generic  Methods */



    
    public   static Realm_User provideUserLocal(String email, String pass)
    {

        try{
            Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

            mLocalService.refresh();

            RealmResults<Realm_User> userList=mLocalService.where(Realm_User.class)
                    .equalTo(Constants.USER_EMAIL_TAG,email)
                    .equalTo(Constants.USER_PASS_TAG,pass)
                    .findAll();


            Realm_User user = null;

            if(userList!=null && userList.size()>0) {

                user = userList.get(0);
            }


            return  user;

        }catch (Exception e){

        }
        return null;
    }
    public static UserRoles getUserRole(int userRoleID){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        UserRoles userRoles = mLocalService.where(UserRoles.class)
                .equalTo("id",userRoleID)
                .findFirst();
        if(userRoles!=null){
            return userRoles;
        }
        return null;
    }

    public static Competition getCompitionDate(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        Competition competitionDate = mLocalService.where(Competition.class)
                .findFirst();

        return competitionDate;
    }

    public static RealmResults<LeaderBoardAllData> getLeaderBoardAllData(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        RealmResults<LeaderBoardAllData> leaderBoardAllData =mLocalService.where(LeaderBoardAllData.class)
                                                             .findAllAsync();

        return leaderBoardAllData;
    }

    public static RealmResults<PlanDetails> getPlansAllData(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        RealmResults<PlanDetails> planDetails =mLocalService.where(PlanDetails.class)
                .findAllAsync();

        return planDetails;
    }

    public   static  List<ActivityDaily> getActivitiesSyncData()
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        List<ActivityDaily> activitiesSyncList = mLocalService.where(ActivityDaily.class)
                .findAll();

        return  activitiesSyncList;

    }





    public static List<SchduleSlots> GetContestantDaySchduleSlotsStatus(String SelectedDate,List<SchduleSlots> ContestantDaySchdulesSlotsList){
        try{
            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateTime = sdf1.format(new Date());

            SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
            String currentDate= sdf2.format(new Date());

            Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
            List<ScheduledLiveVideoCall> scheduledLiveVideoCallDBList = mLocalService.where(ScheduledLiveVideoCall.class)
                    .findAll();


            if(scheduledLiveVideoCallDBList!=null){
                for(int i=0;i<ContestantDaySchdulesSlotsList.size();i++){
                    ScheduledLiveVideoCall scheduledLiveVideoCallDB = mLocalService.where(ScheduledLiveVideoCall.class)
                            .equalTo("slotID",ContestantDaySchdulesSlotsList.get(i).getId())
                            .findFirst();
                    if(scheduledLiveVideoCallDB!=null){
                        if(scheduledLiveVideoCallDB.getStatus().equals("reserved") || scheduledLiveVideoCallDB.getStatus().equals("reschdule") ){
                            ContestantDaySchdulesSlotsList.get(i).setStatus("reserved");
                        }
                    }
                }
            }else{
                for(int i=0; i<ContestantDaySchdulesSlotsList.size();i++){
                    if(ContestantDaySchdulesSlotsList.get(i).getStatus().equals("reserved") || ContestantDaySchdulesSlotsList.get(i).getStatus().equals("reschdule") ){
                        ContestantDaySchdulesSlotsList.get(i).setStatus("available");
                    }
                }
            }

            if(currentDate.equals(SelectedDate)){
                if(ContestantDaySchdulesSlotsList!=null){

                    for(int i=0;i<ContestantDaySchdulesSlotsList.size();i++){
                        String EndTime = convertUTCToLocalTime(currentDate +" "+ContestantDaySchdulesSlotsList.get(i).getSessionEndsAt());
                        Date CurrentDeviceDateandTimeStartRange = sdf1.parse(currentDateTime);
                        Date ContestantTodayScheduledDateandTimeStartRange = sdf1.parse(EndTime);
                        Date ContestantTodayScheduledDateandTimeEndRange = sdf1.parse(currentDate + " 23:59:59");
                        Date ContestantTodayScheduledDateLocal = sdf1.parse(convertUTCToLocalTime(currentDate +" "+ContestantDaySchdulesSlotsList.get(i).getSessionStartsAt()));
                        if((CurrentDeviceDateandTimeStartRange.before(ContestantTodayScheduledDateLocal) || CurrentDeviceDateandTimeStartRange.equals(ContestantTodayScheduledDateLocal))) {

                        }else{
                            ContestantDaySchdulesSlotsList.get(i).setStatus("unavailable");
                        }
                    }
                }
            }

        }catch (Exception e){
            Log.i("SlotEx",e.toString());
        }



       return ContestantDaySchdulesSlotsList;
    }

    public static ScheduledLiveVideoCall GetSchduleFromSlotId(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            List<String> DaysOFWeek = GetStartDateOfWeek();
            for(int i=0;i<DaysOFWeek.size();i++){
                ScheduledLiveVideoCall scheduledLiveVideoCallList = mLocalService.where(ScheduledLiveVideoCall.class)
                        .contains("sessionStartsAt",DaysOFWeek.get(i))
                        .findFirst();
                if(scheduledLiveVideoCallList!=null){
                    return scheduledLiveVideoCallList;
                }
            }
        }

        return null;
    }

    public static Boolean getBookSlotsOfWeek(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            List<String> DaysOFWeek = GetStartDateOfWeek();
            for(int i=0;i<DaysOFWeek.size();i++){
                ScheduledLiveVideoCall scheduledLiveVideoCallList = mLocalService.where(ScheduledLiveVideoCall.class)
                        .contains("sessionStartsAt",DaysOFWeek.get(i))
                        .findFirst();
                if(scheduledLiveVideoCallList!=null){
                    return true;
                }
            }
        }


        return false;

    }

    public static List<ScheduledLiveVideoCall> getTraingTodaySchdule(String Date){
        try{

            Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateTime = sdf1.format(new Date());
            final List<ScheduledLiveVideoCall> TrainerTodayScheduledLiveVideoCallListUtcTime = new ArrayList();
            final List<ScheduledLiveVideoCall> TrainerTodayScheduledLiveVideoCallListLocalTime = new ArrayList();
            List<ScheduledLiveVideoCall> scheduledLiveVideoCallList = mLocalService.where(ScheduledLiveVideoCall.class)
                    .findAll();

            if(scheduledLiveVideoCallList!=null){
                TrainerTodayScheduledLiveVideoCallListUtcTime.addAll(scheduledLiveVideoCallList);

                if(TrainerTodayScheduledLiveVideoCallListUtcTime.size()>0){
                    for(int i=0;i<TrainerTodayScheduledLiveVideoCallListUtcTime.size();i++){
                        String EndTime = convertUTCToLocalTime(TrainerTodayScheduledLiveVideoCallListUtcTime.get(i).getSessionEndsAt());
                        Date CurrentDeviceDateandTimeStartRange = sdf1.parse(currentDateTime);
                        Date ContestantTodayScheduledDateandTimeStartRange = sdf1.parse(EndTime);
                        Date ContestantTodayScheduledDateandTimeEndRange = sdf1.parse(Date + " 23:59:59");
                        Date ContestantTodayScheduledDateLocal = sdf1.parse(convertUTCToLocalTime(TrainerTodayScheduledLiveVideoCallListUtcTime.get(i).getSessionStartsAt()));
                        if((CurrentDeviceDateandTimeStartRange.before(ContestantTodayScheduledDateandTimeStartRange) || CurrentDeviceDateandTimeStartRange.equals(ContestantTodayScheduledDateandTimeStartRange)) && (ContestantTodayScheduledDateLocal.before(ContestantTodayScheduledDateandTimeEndRange) || ContestantTodayScheduledDateLocal.equals(ContestantTodayScheduledDateandTimeEndRange))) {


                            TrainerTodayScheduledLiveVideoCallListLocalTime.add(TrainerTodayScheduledLiveVideoCallListUtcTime.get(i));


                        }
                    }
                    if(TrainerTodayScheduledLiveVideoCallListLocalTime.size()>0){
                        Collections.sort(TrainerTodayScheduledLiveVideoCallListLocalTime);
                        return TrainerTodayScheduledLiveVideoCallListLocalTime;
                    }else{
                        return null;
                    }

                }
            }

        }catch (Exception e){
            Log.i("TraingTodaySchduleEx",e.toString());
        }
        return null;
    }


    public static List<ScheduledLiveVideoCall> getTraingFutureSchdule(String Date){
        try{

            Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateTime = sdf1.format(new Date());
            final List<ScheduledLiveVideoCall> TrainerFutureScheduledLiveVideoCallListUtcTime = new ArrayList();
            final List<ScheduledLiveVideoCall> TrainerFutureScheduledLiveVideoCallListLocalTime = new ArrayList();
            List<ScheduledLiveVideoCall> scheduledLiveVideoCallList = mLocalService.where(ScheduledLiveVideoCall.class)
                    .contains("sessionStartsAt",Date)
                    .findAll();

            if(scheduledLiveVideoCallList!=null){
                TrainerFutureScheduledLiveVideoCallListUtcTime.addAll(scheduledLiveVideoCallList);
                Date ContestantTodayScheduledDateandTimeStartRange = sdf1.parse(Date + " 00:00:00");
                Date ContestantTodayScheduledDateandTimeEndRange = sdf1.parse(Date + " 23:59:59");
                if(TrainerFutureScheduledLiveVideoCallListUtcTime.size()>0){
                    for(int i=0;i<TrainerFutureScheduledLiveVideoCallListUtcTime.size();i++){
                        Date TrainerFutureScheduledDateLocal = sdf1.parse(convertUTCToLocalTime(TrainerFutureScheduledLiveVideoCallListUtcTime.get(i).getSessionStartsAt()));
                        if((TrainerFutureScheduledDateLocal.after(ContestantTodayScheduledDateandTimeStartRange) || TrainerFutureScheduledDateLocal.equals(ContestantTodayScheduledDateandTimeStartRange)) && (TrainerFutureScheduledDateLocal.before(ContestantTodayScheduledDateandTimeEndRange) || TrainerFutureScheduledDateLocal.equals(ContestantTodayScheduledDateandTimeEndRange))) {

                            TrainerFutureScheduledLiveVideoCallListLocalTime.add(TrainerFutureScheduledLiveVideoCallListUtcTime.get(i));
                        }else{

                        }
                    }
                    if(TrainerFutureScheduledLiveVideoCallListLocalTime.size()>0){
                        Collections.sort(TrainerFutureScheduledLiveVideoCallListLocalTime);
                        return TrainerFutureScheduledLiveVideoCallListLocalTime;
                    }else{
                        return null;
                    }
                }
            }
        }catch (Exception e){
            Log.i("TraingFutureSchduleEx",e.toString());
        }
        return null;
    }


    public static List<ScheduledLiveVideoCall> getContestantTodaySchdule(String Date){
        try{

            Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

            SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
            String currentDateTime = sdf1.format(new Date());
            final List<ScheduledLiveVideoCall> ContestantTodayScheduledLiveVideoCallListUtcTime = new ArrayList();
            final List<ScheduledLiveVideoCall> ContestantTodayScheduledLiveVideoCallListLocalTime = new ArrayList();
            List<ScheduledLiveVideoCall> scheduledLiveVideoCallList = mLocalService.where(ScheduledLiveVideoCall.class)
                    .findAll();

            if(scheduledLiveVideoCallList!=null){
                ContestantTodayScheduledLiveVideoCallListUtcTime.addAll(scheduledLiveVideoCallList);

                if(ContestantTodayScheduledLiveVideoCallListUtcTime.size()>0){
                    for(int i=0;i<ContestantTodayScheduledLiveVideoCallListUtcTime.size();i++){
                        String EndTime = convertUTCToLocalTime(ContestantTodayScheduledLiveVideoCallListUtcTime.get(i).getSessionEndsAt());
                        Date CurrentDeviceDateandTimeStartRange = sdf1.parse(currentDateTime);
                        Date ContestantTodayScheduledDateandTimeStartRange = sdf1.parse(EndTime);
                        Date ContestantTodayScheduledDateandTimeEndRange = sdf1.parse(Date + " 23:59:59");
                        Date ContestantTodayScheduledDateLocal = sdf1.parse(convertUTCToLocalTime(ContestantTodayScheduledLiveVideoCallListUtcTime.get(i).getSessionStartsAt()));
                        if((CurrentDeviceDateandTimeStartRange.before(ContestantTodayScheduledDateandTimeStartRange) || CurrentDeviceDateandTimeStartRange.equals(ContestantTodayScheduledDateandTimeStartRange)) && (ContestantTodayScheduledDateLocal.before(ContestantTodayScheduledDateandTimeEndRange) || ContestantTodayScheduledDateLocal.equals(ContestantTodayScheduledDateandTimeEndRange))) {


                            ContestantTodayScheduledLiveVideoCallListLocalTime.add(ContestantTodayScheduledLiveVideoCallListUtcTime.get(i));


                        }
                    }
                    if(ContestantTodayScheduledLiveVideoCallListLocalTime.size()>0){
                        return ContestantTodayScheduledLiveVideoCallListLocalTime;
                    }else{
                        return null;
                    }

                }
            }
        }catch (Exception e){
                Log.i("TodayEx",e.toString());
        }
        return null;
    }
    public static List<ScheduledLiveVideoCall> getContestantFutureSchdule(String Date){
        try{
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        final List<ScheduledLiveVideoCall> ContestantFutureScheduledLiveVideoCallListUtcTime = new ArrayList();
        final List<ScheduledLiveVideoCall> ContestantFutureScheduledLiveVideoCallListLocalTime = new ArrayList();
        List<ScheduledLiveVideoCall> scheduledLiveVideoCallList = mLocalService.where(ScheduledLiveVideoCall.class)
                .findAll();

        if(scheduledLiveVideoCallList!=null){
            ContestantFutureScheduledLiveVideoCallListUtcTime.addAll(scheduledLiveVideoCallList);
            Date ContestantTodayScheduledDateandTimeStartRange = sdf1.parse(Date + " 00:00:00");
            Date ContestantTodayScheduledDateandTimeEndRange = sdf1.parse(Date + " 23:59:59");
            if(ContestantFutureScheduledLiveVideoCallListUtcTime.size()>0){
                for(int i=0;i<ContestantFutureScheduledLiveVideoCallListUtcTime.size();i++){
                    Date ContestantTodayScheduledDateLocal = sdf1.parse(convertUTCToLocalTime(ContestantFutureScheduledLiveVideoCallListUtcTime.get(i).getSessionStartsAt()));
                    if((ContestantTodayScheduledDateLocal.after(ContestantTodayScheduledDateandTimeEndRange))) {
                        ContestantFutureScheduledLiveVideoCallListLocalTime.add(ContestantFutureScheduledLiveVideoCallListUtcTime.get(i));
                    }else{

                    }
                }
                if(ContestantFutureScheduledLiveVideoCallListLocalTime.size()>0){
                    return ContestantFutureScheduledLiveVideoCallListLocalTime;
                }else{
                    return null;
                }
            }
        }
    }catch (Exception e){

    }
        return null;
    }

    public static DailyDiaryQuestionModel getDailyDiary(){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        DailyDiaryQuestionModel dailyDiaryQuestionModel = new DailyDiaryQuestionModel();

        final diaries diary = mLocalService.where(diaries.class)
                .equalTo("mDiary_Date",currentDateandTime)
                .findFirst();
        if(diary!=null){
            if(diary.getMid()!=null){
                dailyDiaryQuestionModel.setId(diary.getMid());
                dailyDiaryQuestionModel.setmDayDescription(diary.getDayDescription());
                dailyDiaryQuestionModel.setDate(diary.getmDiary_Date());
                dailyDiaryQuestionModel.setmDayStatus(diary.getDayStatus());
            }

            List<QuestionResponceModel> questionResponseList =new ArrayList<>();
            for(int i=0; i<diary.getQuestions().size();i++){
                final Question question= mLocalService.where(Question.class)
                        .equalTo("id",diary.getQuestions().get(i).getId())
                        .findFirst();
                QuestionResponceModel questionResponceModel =new QuestionResponceModel();
                questionResponceModel.setmId(question.getId());
                questionResponceModel.setQuestion(question.getQuestion());
                questionResponceModel.setmResponse(diary.getQuestions().get(i).getResponse());
                questionResponceModel.setOrder_id(question.getOrder_id());
                questionResponseList.add(questionResponceModel);
            }
            dailyDiaryQuestionModel.setmQuestions(questionResponseList);
        }


        return dailyDiaryQuestionModel;
    }


    public static DailyDiaryQuestionModel getWeeklyDiary(String activtyTime){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        List<DailyDiaryQuestionModel> dailyDiaryQuestionModelList = new ArrayList();
        DailyDiaryQuestionModel dailyDiaryQuestionModel = new DailyDiaryQuestionModel();


            diaries diary = mLocalService.where(diaries.class)
                    .equalTo("mDiary_Date",activtyTime)
                    .findFirst();

            if(diary!=null){
                if(diary.getMid()!=null){
                    dailyDiaryQuestionModel.setId(diary.getMid());
                    dailyDiaryQuestionModel.setmDayDescription(diary.getDayDescription());
                    dailyDiaryQuestionModel.setDate(diary.getmDiary_Date());
                    dailyDiaryQuestionModel.setmDayStatus(diary.getDayStatus());
                }

                List<QuestionResponceModel> questionResponseList =new ArrayList<>();
                for(int i=0; i<diary.getQuestions().size();i++){
                    final Question question= mLocalService.where(Question.class)
                            .equalTo("id",diary.getQuestions().get(i).getId())
                            .findFirst();
                    QuestionResponceModel questionResponceModel =new QuestionResponceModel();
                    questionResponceModel.setmId(question.getId());
                    questionResponceModel.setQuestion(question.getQuestion());
                    questionResponceModel.setmResponse(diary.getQuestions().get(i).getResponse());
                    questionResponceModel.setOrder_id(question.getOrder_id());
                    questionResponseList.add(questionResponceModel);
                }
                dailyDiaryQuestionModel.setmQuestions(questionResponseList);
                dailyDiaryQuestionModelList.add(dailyDiaryQuestionModel);
            }


        return dailyDiaryQuestionModel;
    }



    public   static ActivitiesDB getActivitiesData(String  userId)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        ActivitiesDB activities=mLocalService.where(ActivitiesDB.class)
                .equalTo("userID",userId)
                .findFirst();


        return  activities;

    }
    public   static NutritiousDB getNutritionData(String  userId)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        NutritiousDB nutritiousDB=mLocalService.where(NutritiousDB.class)
                .equalTo("userID",userId)
                .findFirst();


        return  nutritiousDB;

    }


    public   static N_Plans getNutritionDataV3(int weedID)
    {

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        N_Plans nutritiousPlanDBV3 = mLocalService.where(N_Plans.class)
                .equalTo("mWeek",weedID)
                .findFirst();

        return  nutritiousPlanDBV3;

    }

    public static List<MealType> getNutritionMealTypeV3(List<Integer>  MealTypeID){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        List<MealType> nutritiousMealType= new ArrayList();

        for(int i=0;i<MealTypeID.size();i++){
            MealType nutritiousMealTypeDBV3 = mLocalService.where(MealType.class)
                    .equalTo("mId",MealTypeID.get(i))
                    .findFirst();
            nutritiousMealType.add(nutritiousMealTypeDBV3);
        }
        return nutritiousMealType;
    }

    public static List<Meal> getNutritionMealTypeV3(int  MealID){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        List<Meal> nutritiousMeal = mLocalService.where(Meal.class)
                .equalTo("mMealId",MealID)
                .findAll();

        return nutritiousMeal;
    }

    public   static List<W_Plans> getTraningDataV3()
    {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        List<W_Plans> traningDBV3 = mLocalService.where(W_Plans.class)
                .findAll();
        return  traningDBV3;

    }
    public   static List<WorkoutType> getTraningWorkOutTypeDataV3()
    {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        List<WorkoutType> traningWorkOutTypeDBV3 = mLocalService.where(WorkoutType.class)
                .findAll();

        return  traningWorkOutTypeDBV3;

    }

    public   static W_Day getTraningDayDataV3(int mPlanId)
    {
        Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        W_Day traningDBV3 = mLocalService.where(W_Day.class)
                .equalTo("mPlanId",mPlanId)
                .findFirst();

        return  traningDBV3;

    }

    public static List<WorkoutType> getTraningWorkOutTypeV3(List<Integer>  WorkOutTypeID){
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
        List<WorkoutType> traningMealType= new ArrayList();

        for(int i=0;i<WorkOutTypeID.size();i++){
            WorkoutType nutritiousDBV3 = mLocalService.where(WorkoutType.class)
                    .equalTo("mId",WorkOutTypeID.get(i))
                    .findFirst();
            traningMealType.add(nutritiousDBV3);
        }
        return traningMealType;
    }


    public   static TrainingsDB getTrainingsData(String  userId)
    {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        TrainingsDB trainingsDB = mLocalService.where(TrainingsDB.class)
                .equalTo("userID",userId)
                .findFirst();


        return  trainingsDB;
    }

    public   static MotivationVideos getMotivationVideosData(String  userId)
    {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        MotivationVideos motivationVideos=mLocalService.where(MotivationVideos.class)
                .equalTo("userID",userId)
                .findFirst();


        return  motivationVideos;
    }

    public   static List<MotivationalVideo> getMotivationVideosDataV3()
    {
        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        List<MotivationalVideo> motivationVideos=mLocalService.where(MotivationalVideo.class)
                .sort("mPostedDate",Sort.DESCENDING)
                .findAll();


        return  motivationVideos;
    }


    public   static DayNutritionDB getRecipeIngrediantsDetail(int week,int day, int mealID,String MealType,String MealName){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        N_DayDB n_dayDB = null;
        /*N_DayDB n_dayDB= mLocalService.where(N_DayDB.class)
                .equalTo("dayID",day)
                .findFirst();*/

        N_WeekDB n_weekDB= mLocalService.where(N_WeekDB.class)
                .equalTo("weekID",week)
                .findFirst();
        if(n_weekDB!=null){
            if(n_weekDB.getDays()!=null){
                if(n_weekDB.getDays().getDay1()!=null){
                    if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay1();
                    }
                }
                if(n_weekDB.getDays().getDay2()!=null){
                    if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay2();
                    }
                }
                if(n_weekDB.getDays().getDay3()!=null){
                    if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay3();
                    }
                }
                if(n_weekDB.getDays().getDay4()!=null){
                    if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay4();
                    }
                }
                if(n_weekDB.getDays().getDay5()!=null){
                    if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay5();
                    }
                }
                if(n_weekDB.getDays().getDay6()!=null){
                    if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay6();
                    }
                }
                if(n_weekDB.getDays().getDay7()!=null){
                    if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay7();
                    }
                }
            }

        }

        if(n_dayDB !=null){

            for(int i=0;i<n_dayDB.getDayNutritionList().size();i++){
                if(n_dayDB.getDayNutritionList().get(i).getMealTypeSV().equals(MealType)){
                    for(int j=0; j<n_dayDB.getDayNutritionList().get(i).getMealIngrediantsOptionsList().size();j++){
                        if(n_dayDB.getDayNutritionList().get(i).getMealIngrediantsOptionsList().get(j).getMealName_SV().equals(MealName)){
                            return n_dayDB.getDayNutritionList().get(i).getMealIngrediantsOptionsList().get(j).getMealOptionArrayList().get(mealID);
                        }
                    }
                }
            }
        }

        return null;

    }


    public   static List<DayNutritionDB> getNextRecipeIngrediantsDetail(int week,int day, String MealType, String MealName){

        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        N_DayDB n_dayDB = null;
        /*N_DayDB n_dayDB= mLocalService.where(N_DayDB.class)
                .equalTo("dayID",day)
                .findFirst();*/

        N_WeekDB n_weekDB= mLocalService.where(N_WeekDB.class)
                .equalTo("weekID",week)
                .findFirst();
        if(n_weekDB!=null){
            if(n_weekDB.getDays()!=null){
                if(n_weekDB.getDays().getDay1()!=null){
                    if(n_weekDB.getDays().getDay1().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay1();
                    }
                }
                if(n_weekDB.getDays().getDay2()!=null){
                    if(n_weekDB.getDays().getDay2().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay2();
                    }
                }
                if(n_weekDB.getDays().getDay3()!=null){
                    if(n_weekDB.getDays().getDay3().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay3();
                    }
                }
                if(n_weekDB.getDays().getDay4()!=null){
                    if(n_weekDB.getDays().getDay4().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay4();
                    }
                }
                if(n_weekDB.getDays().getDay5()!=null){
                    if(n_weekDB.getDays().getDay5().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay5();
                    }
                }
                if(n_weekDB.getDays().getDay6()!=null){
                    if(n_weekDB.getDays().getDay6().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay6();
                    }
                }
                if(n_weekDB.getDays().getDay7()!=null){
                    if(n_weekDB.getDays().getDay7().getCurrentDay().equals("present")){
                        n_dayDB = n_weekDB.getDays().getDay7();
                    }
                }
            }

        }

        if(n_dayDB !=null){

            for(int i=0;i<n_dayDB.getDayNutritionList().size();i++){
                if(n_dayDB.getDayNutritionList().get(i).getMealTypeSV().equals(MealType)){
                    for(int j=0; j<n_dayDB.getDayNutritionList().get(i).getMealIngrediantsOptionsList().size();j++){
                        if(n_dayDB.getDayNutritionList().get(i).getMealIngrediantsOptionsList().get(j).getMealName_SV().equals(MealName)){
                            return n_dayDB.getDayNutritionList().get(i).getMealIngrediantsOptionsList().get(j).getMealOptionArrayList();
                        }
                    }
                }
            }
        }

        return null;

    }
    public static activityAdapterListModel GetTodayActivityV1(Context context, String CompitionStartdate){
       // Realm_User mUser = provideUserLocal(context);
        SimpleDateFormat sdf3 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
        List<ActivityDaily> activityDailySyncList = Repository.getActivitiesSyncData();
        List<activityAdapterListModel> activityDailySyncSortedList = new ArrayList();
        List<activityAdapterListModel> activityDailySyncWeekDaysSortedList = new ArrayList();
        try {
            long CurrentWeek = 0;
            for (int i = 0; i < activityDailySyncList.size(); i++) {
                Date ActivityDateandTime = sdf3.parse(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                Date CurrentDateandTime = sdf3.parse(CompitionStartdate);
                if (ActivityDateandTime.after(CurrentDateandTime)) {
                    activityAdapterListModel activityAdapterListModel1 = new activityAdapterListModel();
                    ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTime(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()), CompitionStartdate);
                    activityAdapterListModel1.setmDate(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                    activityAdapterListModel1.setmWeek(contestWeekDayTimeModel.getWeeks() + 1);
                    activityAdapterListModel1.setmDay(contestWeekDayTimeModel.getDays() + 1);
                    activityAdapterListModel1.setmMediaImage(activityDailySyncList.get(i).getmMediaImage());
                    activityAdapterListModel1.setmId(activityDailySyncList.get(i).getmId());
                    activityAdapterListModel1.setmLocationAddress(activityDailySyncList.get(i).getmLocationAddress());
                    activityAdapterListModel1.setmWeight(activityDailySyncList.get(i).getmWeight());
                    activityAdapterListModel1.setmWaist(activityDailySyncList.get(i).getmWaist());
                    activityAdapterListModel1.setmSteps(activityDailySyncList.get(i).getmSteps());
                    activityAdapterListModel1.setmCalories(activityDailySyncList.get(i).getmCalories());
                    activityAdapterListModel1.setmDistance(activityDailySyncList.get(i).getmDistance());
                    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
                    SimpleDateFormat sdf1 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                    String currentDateandTimeStart = sdf.format(new Date());
                    String currentDateandTimeEnd = sdf.format(new Date());
                    try {
                        Date currentDateandTimeStartRange = sdf1.parse(currentDateandTimeStart + " 00:00:00");
                        Date currentDateandTimeEndRange = sdf1.parse(currentDateandTimeEnd + " 23:59:59");
                        Date ActiviyDate = sdf1.parse(convertUTCToLocalTime(activityDailySyncList.get(i).getmDate()));
                        if ((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))) {
                            activityAdapterListModel1.setCurrentWeek("Present");
                            CurrentWeek = activityAdapterListModel1.getmWeek();
                        } else {
                            activityAdapterListModel1.setCurrentWeek("Past");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i("ActivityHistory", e.toString());
                    }
                    activityDailySyncSortedList.add(activityAdapterListModel1);
                }
            }
            Collections.sort(activityDailySyncSortedList);
            for(int i =0; i<activityDailySyncSortedList.size();i++){
                SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT);
                SimpleDateFormat sdf4 = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
                String currentDateandTimeStart = sdf2.format(new Date());
                String currentDateandTimeEnd = sdf2.format(new Date());
                Date currentDateandTimeStartRange = sdf4.parse(currentDateandTimeStart+" 00:00:00");
                Date currentDateandTimeEndRange = sdf4.parse(currentDateandTimeEnd+" 23:59:59");
                Date ActiviyDate = sdf4.parse(activityDailySyncSortedList.get(i).getmDate());
                if((ActiviyDate.after(currentDateandTimeStartRange) || ActiviyDate.equals(currentDateandTimeStartRange)) && (ActiviyDate.before(currentDateandTimeEndRange) || ActiviyDate.equals(currentDateandTimeEndRange))){

                    return activityDailySyncSortedList.get(i);

                }
            }

        }catch (Exception ex ){
            Log.i("ExToday",ex.toString());
        }

        return null;
    }


    public   static DayNutritionDB getIngrediantsDetail(int weekId, int day, int videoId)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        N_WeekDB n_weekDB= mLocalService.where(N_WeekDB.class)
                .equalTo("weekID",weekId)

               .findFirst();


        N_DayDB n_dayDB=null;

        try {

            switch (day) {
                case 1: {
                    n_dayDB = n_weekDB.getDays().getDay1();
                    break;
                }
                case 2: {
                    n_dayDB = n_weekDB.getDays().getDay2();
                    break;
                }
                case 3: {
                    n_dayDB = n_weekDB.getDays().getDay3();
                    break;
                }
                case 4: {
                    n_dayDB = n_weekDB.getDays().getDay4();
                    break;
                }
                case 5: {
                    n_dayDB = n_weekDB.getDays().getDay5();
                    break;
                }
                case 6: {
                    n_dayDB = n_weekDB.getDays().getDay6();
                    break;
                }
                case 7: {
                    n_dayDB = n_weekDB.getDays().getDay7();
                    break;
                }

            }

            //return   n_dayDB.getDayNutritionList().get(videoId).getMealIngrediantsOptionsList().;

        }catch (Exception ex)
        {

        }

        return  null;

    }

    public   static List<DayTainingDB> getNextVideoDetail( int weekId, int day)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        T_WeekDB t_weekDB=mLocalService.where(T_WeekDB.class)
                .equalTo("weekID",weekId+"")
                .findFirst();


        //  t_weekDB.getDays().

        T_DayDB  t_dayDB=null;

        try {

            switch (day) {
                case 1: {
                    t_dayDB = t_weekDB.getDays().getDay1();
                    break;
                }
                case 2: {
                    t_dayDB = t_weekDB.getDays().getDay2();
                    break;
                }
                case 3: {
                    t_dayDB = t_weekDB.getDays().getDay3();
                    break;
                }
                case 4: {
                    t_dayDB = t_weekDB.getDays().getDay4();
                    break;
                }
                case 5: {
                    t_dayDB = t_weekDB.getDays().getDay5();
                    break;
                }
                case 6: {
                    t_dayDB = t_weekDB.getDays().getDay6();
                    break;
                }
                case 7: {
                    t_dayDB = t_weekDB.getDays().getDay7();
                    break;
                }

            }


            return   t_dayDB.getDayactivitesList();

        }catch (Exception ex)
        {

        }

        return  null;


    }

    public   static DayTainingDB getVideoDetail( int weekId, int day, int videoId)
    {


        Realm mLocalService= ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();


        T_WeekDB t_weekDB=mLocalService.where(T_WeekDB.class)
                .equalTo("weekID",weekId+"")
                .findFirst();


      //  t_weekDB.getDays().

        T_DayDB  t_dayDB=null;

        try {

            switch (day) {
                case 1: {
                    t_dayDB = t_weekDB.getDays().getDay1();
                    break;
                }
                case 2: {
                    t_dayDB = t_weekDB.getDays().getDay2();
                    break;
                }
                case 3: {
                    t_dayDB = t_weekDB.getDays().getDay3();
                    break;
                }
                case 4: {
                    t_dayDB = t_weekDB.getDays().getDay4();
                    break;
                }
                case 5: {
                    t_dayDB = t_weekDB.getDays().getDay5();
                    break;
                }
                case 6: {
                    t_dayDB = t_weekDB.getDays().getDay6();
                    break;
                }
                case 7: {
                    t_dayDB = t_weekDB.getDays().getDay7();
                    break;
                }

            }


            return   t_dayDB.getDayactivitesList().get(videoId);

        }catch (Exception ex)
        {

        }

        return  null;


    }


    /* End Generic  Methods */





}
