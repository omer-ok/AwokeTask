package com.kampen.riksSe.api.remote_api;

import android.app.Application;
import android.content.Context;

import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.utils.SaveSharedPreference;

public class APIUrl  {

    public static Context context = MyApplication.getAppContext();

    public static String BASE_PROD_URL ="http://api.rikskampen.se/public/";
    public static String BASE_TEST_URL ="http://apitest.rikskampen.se/public/";

    //public static String BASE_URL = "http://api.rikskampen.se/public/";
    public static String BASE_URL = "http://apitest.rikskampen.se/public/";

    //public static final String BASE_URL ="http://api.rikskampen.se/public/api/v3";
    public static final String IMAFE_BASE_URL ="https://www.rikskampen.se/main_rikskampen/public/";
    public static final String PAYEX_BASE_URL ="http://api.rikskampen.se/public/";


   /*public  static  String transformPath(String path)
    {

        String str = path;
        String findStr = "public/images";
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }

        if(count>1)
        {

            str=str.substring(findStr.length(),str.length());
        }

       return str;
    }*/

}
