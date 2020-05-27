package com.kampen.riksSe.BroadCastReciver;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import android.util.Log;

import com.facebook.stetho.server.http.HttpStatus;
import com.kampen.riksSe.DI.RemoteApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.api.remote_api.APIService;
import com.kampen.riksSe.api.remote_api.Generic_Result;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Constants;
import com.kampen.riksSe.utils.SaveSharedPreference;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
import static com.kampen.riksSe.utils.Constants.MULTIPART_FORM_DATA;

public class MyService extends Service {

/*
    public MyService() {
        super(MyService.class.getName());
    }
*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;


    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*Toast.makeText(MyService.this, " Service Start ", Toast.LENGTH_SHORT).show();

        final  Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();

        final Realm_User user = provideUserLocal(mLocalService);

        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime = sdf.format(new Date());

        final Realm_User userActivity= mLocalService.where(Realm_User.class)
                .equalTo(Constants.DAILY_ACTIVITY_DATE_TAG,currentDateandTime)
                .findFirst();






                //AddActivityToServer(userActivity,user.getId(),user);



         final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
        //Do something after 100ms

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(mWifi.isConnected()){

            //Toast.makeText(MyService.this, " Service Start ", Toast.LENGTH_SHORT).show();
            //HitStarChaseAPI();


        }else {

            stopSelf();
        }

           }
         }, 1000);
*/
    }

    // @Override
    protected void onHandleWork(@NonNull Intent intent) {



    }

   /* public void HitStarChaseAPI(Realm mLocalService){


        APIService mWebService;


        mWebService  = ((RemoteApiService) ServiceLocator.getService(RemoteApiService.NAME)).getApiService();


        Realm_User user = provideUserLocal(mLocalService);


        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
        String currentDateandTime = sdf.format(new Date());



        String userId=user.getId();


        final UserActivityData Date = mLocalService.where(UserActivityData.class)
                .equalTo(Constants.USER_TODAY_DATE,currentDateandTime)
                .findFirst();


        if(Date != null && !Date.getSyncedWithServer()){

        }else {
            stopSelf();
            return;
        }

        String  token="bearer "+user.getToken();
        HashMap<String,String> hm =new HashMap();
        hm.put(USERID,userId);
        hm.put(STEPS_COUNT, String.valueOf(Date.getLocatStepCount()));
        hm.put(STARS_COUNT, String.valueOf(Date.getLocatStarCount()));
        hm.put(ACTIVITY_CALORIES, String.valueOf(Date.getLocatCalCount()));
        hm.put(ACTIVITY_TIME,Date.getLocalDate());




                Call<Generic_Result<Object>> call = mWebService.AddStarSteps_(token,hm);

                call.enqueue(new Callback<Generic_Result<Object>>() {
                    @Override
                    public void onResponse(Call<Generic_Result<Object>> call, Response<Generic_Result<Object>> response) {

                        Generic_Result<Object> obj = null;

                        obj = response.body();

                        if(response.code()== HttpStatus.HTTP_OK) {

                            //Toast.makeText(MyService.this, " Data Synced ", Toast.LENGTH_SHORT).show();
                            mLocalService.executeTransaction(new Realm.Transaction() {
                                                                 @Override
                                                                 public void execute(Realm realm) {

                                                                     UserActivityData db_user = Date;

                                                                     db_user.setSyncedWithServer(true);

                                                                     realm.insertOrUpdate(db_user);
                                                                 }
                                                             }
                            );

                        }




                    }

                    @Override
                    public void onFailure(Call<Generic_Result<Object>> call, Throwable t) {

                        t.toString();


                    }
                });


            }
*/



    @NonNull
    private static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse(MULTIPART_FORM_DATA), descriptionString);
    }





    public Realm_User provideUserLocal(Realm realm)
    {

        String[] params= SaveSharedPreference.getLoggedParams(MyService.this);

        final RealmResults<Realm_User> user = realm.where(Realm_User.class)
                .equalTo(Constants.USER_EMAIL_TAG,params[0].trim())
                .and()
                .equalTo(Constants.USER_PASS_TAG,params[1].trim())
                .findAll();

        Realm_User user1=null;

        if(user.size()>0) {


            user1 = user.get(0);

        }

        return  user1;

    }


    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Rikskampen");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
