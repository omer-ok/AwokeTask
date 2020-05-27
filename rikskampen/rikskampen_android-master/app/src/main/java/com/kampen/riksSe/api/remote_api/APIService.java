package com.kampen.riksSe.api.remote_api;

import com.kampen.riksSe.ForgotPassword.Model.ForgotPasswordData;
import com.kampen.riksSe.ChangePassword.ModelN.ChangePasswordData;
import com.kampen.riksSe.TrainerApp.ScheduledLiveVideoCall.ModelV3.ScheduledLiveVideoCall;
import com.kampen.riksSe.UpdateAppVersion.AppUpdate;
import com.kampen.riksSe.api.remote_api.V2_api_model.AllHomeDataNew;
import com.kampen.riksSe.api.remote_api.V2_api_model.SyncTable;
import com.kampen.riksSe.api.remote_api.models.NutritionModelStatus;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.BookSchduleSlots;
import com.kampen.riksSe.leader.activity.fragments.account.profile.ContestantScheduledLiveVideoCall.ContestantReSchduleLiveVideoCall.ConestantDaySchdule.ModelV3.SchduleSlots;
import com.kampen.riksSe.login.ModelsV3.LoginResultModel;
import com.kampen.riksSe.login.ModelsV3.RemoteUser;
import com.kampen.riksSe.api.remote_api.models.RemoteUserResult;

import com.kampen.riksSe.api.remote_api.models.all_data_remote.AllData;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.BadgeCountResult;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.modelV3.LeaderBoardAllDataV3;
import com.kampen.riksSe.leader.activity.fragments.LeaderBordTab.models.LeaderBoardAllData;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelNew.AllergyResponce;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AddAllergiesV3Data;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.AlergyResultDataV3;
import com.kampen.riksSe.leader.activity.fragments.account.profile.AddAllergiesDislikes.ModelsV3.diaries;
import com.kampen.riksSe.leader.activity.fragments.chat.Model.ChatData;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.ActivityDaily;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.AddUpdateDailyDiaryToServerV3;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.DiariesUpdateServer;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.V3_Model.PastActivityModel;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.newP.Add_Activity;
import com.kampen.riksSe.leader.activity.fragments.plans.Model.PlanData;
import com.kampen.riksSe.leader.activity.modelV3.Question;
import com.kampen.riksSe.leader.activity.modelV3.UserPermissionAndVersion;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceResponceModel.PayExInvoiceResponceData;
import com.kampen.riksSe.payment.PayEx.PayExInvoiceSendDataModel.PayExInvoiceSendData;
import com.kampen.riksSe.payment.PayEx.PayExResponceModels.PayExAllResponceData;
import com.kampen.riksSe.payment.PayEx.PayExSendDataModel.PayExSendData;
import com.kampen.riksSe.payment.PayEx.PromoCodeModels.PromoCodeData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService  {



    @FormUrlEncoded
    @POST("public/api/nutrition")
    Call<NutritionModelStatus> getStartUpData(@Header("Authorization") String Authorization, @FieldMap HashMap<String,String> user_id );

    @FormUrlEncoded
    @POST("public/api/nutrition")
    Call<NutritionModelStatus> getAppData(@Header("Authorization") String Authorization, @FieldMap HashMap<String,String> user_id );

    @POST("/psp/creditcard/payments/")
    Call<Object> getPayExSession(@Header("Content-Type") String type ,@Header("Authorization") String Betoken);

    @FormUrlEncoded
    @POST("public/index.php/api/login")
    Call<Generic_Result> userLogin(@FieldMap HashMap<String, String> loginHM

    );



    @FormUrlEncoded
    @POST("api/v3/userlogin")
    Call<LoginResultModel> getUser(@FieldMap HashMap<String, String> loginHM, @FieldMap HashMap<String, Boolean> loginHM1);

    @FormUrlEncoded
    @POST("api/v3/getuser")
    Call<Generic_Result<RemoteUser>> getUserUpdatedProfile(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/v3/userforgetPassword")
    Call<ForgotPasswordData> getForgotPassword(@FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/v3/fcmUpdate")
    Call<Generic_Result<Object>> setFcmToken(@Header("Authorization") String auth,@FieldMap HashMap<String, String> loginHM);


    @FormUrlEncoded
    @POST("api/v3/getbadges")
    Call<BadgeCountResult> getBadgeCountLogin(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/v3/userresetPassword")
    Call<ChangePasswordData> ChangePassword(@FieldMap HashMap<String, String> loginHM);



    @FormUrlEncoded
    @POST("api/startupandroid")
    Call<Generic_Result<AllData>> getUserAllData(@Header("Authorization") String auth,@FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/v3/startup_sync")
    Call<AllHomeDataNew> SyncUserAllData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/chatUserDetail")
    Call<ChatData> getChatAllData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);
    @FormUrlEncoded
    @POST("api/v3/trainerDetail")
    Call<ChatData> getChatAllDataV3(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);
    @FormUrlEncoded
    @POST("api/v3/updateUserBadge")
    Call<ChatData> getChatBadgeData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);


    @FormUrlEncoded
    @POST("api/v3/userAllergies")
    Call<Generic_Result<AlergyResultDataV3>> getAllergiesAllData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/v3/allergies")
    Call<AddAllergiesV3Data> getAllergiesSearchData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/v3/saveallergy")
    Call<AllergyResponce> AddAllergiesAllData(@Header("Authorization") String auth,  @FieldMap HashMap<String, String> loginHM,@FieldMap HashMap<String, String> loginHM1);



    @FormUrlEncoded
    @POST("api/promocode")
    Call<PromoCodeData> getPromoCodeData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/packages")
    Call<PlanData> getPlanData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);
    @FormUrlEncoded
    @POST("api/leaderBoard")
    Call<LeaderBoardAllData> getLeaderBoardAllData(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);

    @FormUrlEncoded
    @POST("api/v3/leaderBoard")
    Call<LeaderBoardAllDataV3> getLeaderBoardAllDataV3(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM);
    @FormUrlEncoded
    @POST("api/v3/update_activities")
    Call<Generic_Result> OfflineDataUpload(@Header("Authorization") String auth, @FieldMap HashMap<String, String> loginHM, @FieldMap HashMap<String, String> loginHM1, @Part List<MultipartBody.Part>  file);
/*

    @Multipart
    @POST("api/v3/past_activities")
    Call<List<PastActivityModel>> SyncPastActivities(@Header("Authorization") String auth, @PartMap() HashMap<String, RequestBody> loginHM, @Part List<MultipartBody.Part>  file);
*/
    @Multipart
    @POST("api/v3/past_activities")
    Call<List<PastActivityModel>> SyncPastActivities(@Header("Authorization") String auth, @PartMap() TreeMap<String, RequestBody> userDetails, @PartMap() TreeMap<String, RequestBody> PastLocationAddressList, @PartMap() TreeMap<String, RequestBody> PastLatitudeList, @PartMap() TreeMap<String, RequestBody> PastLongitudeList, @PartMap() TreeMap<String, RequestBody> PastStepsList, @PartMap() TreeMap<String, RequestBody> PastStarsList, @PartMap() TreeMap<String, RequestBody> PastCaloriesList, @PartMap() TreeMap<String, RequestBody> PastDistanceList, @PartMap() TreeMap<String, RequestBody> PastDateTimeList, @PartMap() TreeMap<String, RequestBody> PastWeightList, @PartMap() TreeMap<String, RequestBody> PastWaistList, @PartMap() TreeMap<String, RequestBody> PastImageList, @Part List<MultipartBody.Part>  file);



    @FormUrlEncoded
    @POST("api/usersignup")
    Call<Generic_Result<RemoteUserResult>> userSignUp(@FieldMap HashMap<String, String> signUpHM);



    @POST("/psp/creditcard/payments/")
    Call<PayExAllResponceData> PayExPayements(@Header("Content-Type") String type , @Header("Authorization") String Betoken,@Body PayExSendData PayExSendData);

    @POST("/psp/invoice/payments/")
    Call<PayExInvoiceResponceData> PayExInvoicePayements(@Header("Content-Type") String type , @Header("Authorization") String Betoken, @Body PayExInvoiceSendData payExInvoiceSendData);


    @FormUrlEncoded
    @POST("public/index.php/api/auth/userUpdate")
    Call<Generic_Result<String>> userUpdate_(

            @FieldMap HashMap<String, String> editProfileHM

    );
    @Multipart
    @POST("api/create_activity")
    Call<Generic_Result<Add_Activity>> AddActivity_(@Header("Authorization") String auth, @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    @Multipart
    @POST("api/v3/create_activity")
    Call<Generic_Result<ActivityDaily>> AddActivityV3(@Header("Authorization") String auth, @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    @GET("api/v3/questions")
    Call<List<Question>> GetTodayQuestionsV3(@Header("Authorization") String auth);

    @GET("api/v3/questions/user/{id}")
    Call<List<Question>> GetAllQuestionsV3(@Header("Authorization") String auth, @Path("id") String  id);

    @GET("api/v3/user_schedule/trainer/{id}")
    Call<List<ScheduledLiveVideoCall>> GetTrainerSchdule(@Header("Authorization") String auth, @Path("id") String  id);

    @GET("api/v3/user_schedule/user/{id}")
    Call<List<ScheduledLiveVideoCall>> GetContestantSchdule(@Header("Authorization") String auth, @Path("id") String  id);

    @DELETE("api/v3/user_schedule/{id}")
    Call<Object> DeleteContestantSchdule(@Header("Authorization") String auth, @Path("id") String  id);

    @GET("api/v3/schedule_slot/trainer/{id}/search?")
    Call<List<SchduleSlots>> GetContestantDaySchduleSlots(@Header("Authorization") String auth, @Path("id") String  id, @Query("schedule_date") String  date);

    @POST("api/v3/user_schedule")
    Call<Object> BookSchduleSlots(@Header("Authorization") String auth, @Body BookSchduleSlots bookSchduleSlots);

    @GET("api/v3/diaries/user/{id}")
    Call<List<diaries>> GetAllDiariesV3(@Header("Authorization") String auth, @Path("id") String  id);

    @POST("api/v3/diaries")
    Call<List<diaries>> AddDailyDiaryV3(@Header("Authorization") String auth, @Body AddUpdateDailyDiaryToServerV3 addDailyDiaryV3);

    @PATCH("api/v3/diary/{id}")
    Call<diaries>  UpdateDailyDiaryV3(@Header("Authorization") String auth, @Body DiariesUpdateServer diariesUpdateServer, @Path("id") String  id);

    @FormUrlEncoded
    @POST("api/create_activity")
    Call<Generic_Result<Add_Activity>> AddActivitySteps_(@Header("Authorization") String auth, @FieldMap Map<String, String> partMap);

    @FormUrlEncoded
    @POST("api/save_chasing_stars")
    Call<Generic_Result<Object>> AddStarSteps_(@Header("Authorization") String auth, @FieldMap Map<String, String> partMap);

    @GET("api/v3/get_latest_version/android")
    Call<AppUpdate> GetAppUpdateVersion();

    @POST("api/v3/user_application")
    Call<Object> UpdateUserPermissionAndVersion(@Header("Authorization") String auth, @Body UserPermissionAndVersion userPermissionAndVersion);

    @POST("api/v3/sync_table")
    Call<Object> SyncTableAPI(@Header("Authorization") String auth, @Body SyncTable syncTable);

    @Multipart
    @POST("api/v3/userupdateProfile")
    Call<Generic_Result<RemoteUser>>  editProfile(@Header("Authorization") String auth, @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("api/save_chasing_stars")
    Call<Generic_Result<RemoteUserResult>> UpdateHeightWeight(@Header("Authorization") String auth, @FieldMap Map<String, String> partMap);

    @FormUrlEncoded
    @POST("/auth/updateProfile")
    Call<Generic_Result<Object>> userUpdate(

            @FieldMap HashMap<String, String> editProfileHM

    );


    @FormUrlEncoded
    @POST("api/v3/userupdateProfile")
    Call<Generic_Result<RemoteUser>> updateLoginProfile(@Header("Authorization") String auth,@FieldMap HashMap<String, String> logoutHM);

    @FormUrlEncoded
    @POST("api/v3/userlogout")
    Call<Generic_Result<Object>> logoutUser(@Header("Authorization") String auth,@FieldMap HashMap<String, String> logoutHM);



    @FormUrlEncoded
    @POST("public/index.php/api/auth/activities")
    Call<Generic_Result<String>> getActivities(

            @FieldMap HashMap<String, String> activitesHM

    );


    @FormUrlEncoded
    @POST("public/index.php/api/auth/nutritions")
    Call<Generic_Result<Object>> getNutritions(

            @FieldMap HashMap<String, String> nutritionsHM

    );




}
