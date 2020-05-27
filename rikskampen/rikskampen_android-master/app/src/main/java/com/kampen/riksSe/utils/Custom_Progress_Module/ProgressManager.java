package com.kampen.riksSe.utils.Custom_Progress_Module;

import android.content.Context;
import android.util.Log;

public class ProgressManager {



    public static CustomProgressDialogue progress;


    public static void showProgress(Context activityContex, String message)
    {

        try {
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
        }catch (Exception ex){
            Log.i("ProgressError",ex.toString());
        }
        progress=new CustomProgressDialogue(activityContex,message);

        progress.setCancelable(false);

        progress.show();

    }


    public static void hideProgress()
    {
        try
        {
            //hideProgress();
            progress.hide();
            progress.dismiss();

        }catch (Exception ex)
        {
            Log.i("ProgressError",ex.toString());
        }
    }
}
