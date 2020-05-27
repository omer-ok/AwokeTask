package com.kampen.riksSe.DI;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.kampen.riksSe.SplashActivity;
import com.kampen.riksSe.api.remote_api.API_Provider;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.user.model.UserActivityData;
import com.kampen.riksSe.user.module.DB_Activity_Module;
import com.kampen.riksSe.user.module.DB_Activity_V3_Module;
import com.kampen.riksSe.user.module.DB_Allergies_Module;
import com.kampen.riksSe.user.module.DB_App_Version_Module;
import com.kampen.riksSe.user.module.DB_Daily_Diary_Module;
import com.kampen.riksSe.user.module.DB_ForgotPass_Module;
import com.kampen.riksSe.user.module.DB_LeaderBoard_Module;
import com.kampen.riksSe.user.module.DB_Nutrition_Module;
import com.kampen.riksSe.user.module.DB_Nutrition_Module_New;
import com.kampen.riksSe.user.module.DB_Nutrition_V3_Module;
import com.kampen.riksSe.user.module.DB_Plan_Module;
import com.kampen.riksSe.user.module.DB_TrainerSchdule_Module;
import com.kampen.riksSe.user.module.DB_Training_Module;
import com.kampen.riksSe.user.module.DB_Training_Module_New;
import com.kampen.riksSe.user.module.DB_Training_V3_Module;
import com.kampen.riksSe.user.module.DB_User_Module;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.nain.securerealmdb.key.RealmEncryptionKeyProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.kampen.riksSe.DI.LocalApiService.PACKAGE_NAME;

public class InitialContext {


    public Object lookup(String jndiName){

        if(jndiName.equalsIgnoreCase(RemoteApiService.NAME)){

            RemoteApiService remoteApiService= new RemoteApiService();

            remoteApiService.setApiService(API_Provider.provideApi());


            return remoteApiService;

        }
        else if (jndiName.equalsIgnoreCase(LocalApiService.NAME)){

            try{
                RealmEncryptionKeyProvider realmEncryptionKeyProvider = new RealmEncryptionKeyProvider(SplashActivity.contextsplash);
                byte[] encryptionKey = realmEncryptionKeyProvider.getSecureRealmKey();

                RealmConfiguration config = new RealmConfiguration.Builder()
                        .name(PACKAGE_NAME + ".realm")
                        .schemaVersion(18)
                        .migration(new MigrationRealmDB())
                        .encryptionKey(encryptionKey)
                        .modules(new DB_TrainerSchdule_Module(),new DB_App_Version_Module(),new DB_Daily_Diary_Module(),new DB_Activity_V3_Module(),new DB_Nutrition_V3_Module(),new DB_Training_V3_Module(),new DB_User_Module(),new DB_Activity_Module(),new DB_Nutrition_Module(),new DB_Training_Module(),new DB_Nutrition_Module_New(),new DB_Training_Module_New(),new DB_LeaderBoard_Module(),new DB_Plan_Module(),new DB_Allergies_Module(),new DB_ForgotPass_Module())
                        .build();
                Realm.setDefaultConfiguration(config);
                LocalApiService localApiService=new LocalApiService();
                Realm realmDB = Realm.getInstance(config);
                localApiService.setmRealm(realmDB);
                return localApiService;
            }catch (Exception e){
                Log.i("RealmSetepEx",e.toString());
            }
        }
        return null;
    }

/*    public Object lookup(String jndiName,String BaseURL){

        if(jndiName.equalsIgnoreCase(RemoteApiService.NAME)){

            RemoteApiService remoteApiService= new RemoteApiService();

            remoteApiService.setApiService(API_Provider.provideApi(BaseURL));

            return remoteApiService;

        }
        else if (jndiName.equalsIgnoreCase(LocalApiService.NAME)){

            RealmEncryptionKeyProvider realmEncryptionKeyProvider = new RealmEncryptionKeyProvider(SplashActivity.contextsplash);
            byte[] encryptionKey = realmEncryptionKeyProvider.getSecureRealmKey();

            //Log.i("EncryptionKEy",encryptionKey.toString());

            RealmConfiguration config = new RealmConfiguration.Builder()
                    .name(PACKAGE_NAME + ".realm")
                    .schemaVersion(9)
                    .migration(new MigrationRealmDB())
                    .encryptionKey(encryptionKey)
                    .modules(new DB_Activity_V3_Module(),new DB_Nutrition_V3_Module(),new DB_Training_V3_Module(),new DB_User_Module(),new DB_Activity_Module(),new DB_Nutrition_Module(),new DB_Training_Module(),new DB_Nutrition_Module_New(),new DB_Training_Module_New(),new DB_LeaderBoard_Module(),new DB_Plan_Module(),new DB_Allergies_Module(),new DB_ForgotPass_Module())
                    .build();


            LocalApiService localApiService=new LocalApiService();
            localApiService.setmRealm(Realm.getInstance(config));

            return localApiService;
        }
        return null;
    }*/

}
