package com.test.task.DI;




public class InitialContext {


    public Object lookup(String jndiName) {

        if (jndiName.equalsIgnoreCase(RemoteApiService.NAME)) {

            RemoteApiService remoteApiService = new RemoteApiService();

            remoteApiService.setApiService(API_Provider.provideApi());


            return remoteApiService;

        } else if (jndiName.equalsIgnoreCase(LocalApiService.NAME)) {

/*            try{
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
        }*/
            return null;
        }
        return null;
    }
}

