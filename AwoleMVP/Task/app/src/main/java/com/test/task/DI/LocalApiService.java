package com.test.task.DI;



public class LocalApiService implements Service{


    public  static final   String  NAME="Realm_db";
    public  static final   String  PACKAGE_NAME="com.kampen.riksSE";






    public String getName() {
        return NAME;
    }




    public   LocalApiService getApiObject() {


       /* Realm  mRealm;
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(PACKAGE_NAME + ".realm")
                .schemaVersion(2)
                .modules(new DB_User_Module())
                .build();

         LocalApiService localApiService=new LocalApiService();
        localApiService.setmRealm(Realm.getInstance(config));*/

        return null;



    }

}
