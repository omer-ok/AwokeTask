package com.kampen.riksSe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.material.snackbar.Snackbar;
import androidx.multidex.MultiDexApplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.facebook.stetho.Stetho;


import com.google.firebase.analytics.FirebaseAnalytics;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.ActivityFragment;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.login.LoginActivity;
/*import com.kampen.riksSe.payment.paypal.Settings;
import com.kampen.riksSe.payment.paypal.internal.ApiClient;
import com.kampen.riksSe.payment.paypal.internal.ApiClientRequestInterceptor;*/
import com.kampen.riksSe.user.model.Realm_User;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


//import retrofit.RestAdapter;

public class MyApplication  extends MultiDexApplication {

    private FirebaseAnalytics mFirebaseAnalytics;
    public  static Realm_User tempUser;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
//    private static ApiClient sApiClient;.
private static Context appContext;

    public static NutritiousDB nutritiousDB;




    @Override
    public void onCreate() {
        super.onCreate();

        try
        {
            appContext = getApplicationContext();
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/MontserratAlternates-Bold.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );
            sAnalytics = GoogleAnalytics.getInstance(this);
        }catch (Exception ex)
        {
            ex.toString();
        }


        try
        {
            Realm.init(this);


            Stetho.initialize(
                    Stetho.newInitializerBuilder(this).enableDumpapp(Stetho.defaultDumperPluginsProvider(this)) .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build()).build());


        }catch (Exception ex)
        {
            ex.toString();
        }

        Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException (Thread thread, Throwable e)
            {
                handleUncaughtException (thread, e);
            }
        });
    }

    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }
    public void handleUncaughtException (Thread thread, Throwable e)
    {
        e.printStackTrace(); // not all Android versions will print the stack trace automatically
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(appContext);

        ActivityFragment.newInstance().stopGPSService(appContext);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, String.valueOf(e.getStackTrace()));
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        /*Intent intent = new Intent (getApplicationContext(), MainLeaderActivity.class);
        intent.setAction (getPackageName()+"SEND_LOG"); // see step 5.
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
        startActivity (intent);*/

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                System.exit(1);
            }
        }, 2000);*/
        // kill off the crashed app
        System.exit(1);
    }

    public static void showSimpleSnackBar(Context mContext, String message)
    {
        View rootView=((AppCompatActivity)mContext).findViewById(android.R.id.content);

        Snackbar snackbar = Snackbar.make(rootView, "  "+message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        TextView textView = (TextView)snackBarView.findViewById(R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_error, 0, 0, 0);
        //textView.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.snackbar_icon_padding));
        snackBarView.setBackgroundColor(Color.parseColor("#EA5E67"));
        snackbar.show();
    }

    public static  void showSimpleSnackBarSucess(Context mContext, String message)
    {

        View rootView=((AppCompatActivity)mContext).findViewById(android.R.id.content);

        Snackbar snackbar = Snackbar.make(rootView, "  "+message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        TextView textView = (TextView)snackBarView.findViewById(R.id.snackbar_text);
        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_checked, 0, 0, 0);
        snackBarView.setBackgroundColor(Color.parseColor("#5DC465"));
        snackbar.show();
    }
    public static Context getAppContext() {
        return appContext;
    }

   /* public static ApiClient getApiClient(Context context) {


        if (sApiClient == null) {
            sApiClient = new RestAdapter.Builder()
                    .setEndpoint(Settings.getEnvironmentUrl(context))
                    .setRequestInterceptor(new ApiClientRequestInterceptor())
                    .build()
                    .create(ApiClient.class);
        }

        return sApiClient;
    }
*/
   /* public static void resetApiClient() {
        sApiClient = null;
    }
*/


}
