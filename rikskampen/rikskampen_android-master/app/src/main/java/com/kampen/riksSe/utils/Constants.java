package com.kampen.riksSe.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
//import com.kampenSE.SeRikskampen.GlideApp;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.kampen.riksSe.GlideApp;
import com.kampen.riksSe.api.remote_api.APIUrl;
import com.kampen.riksSe.login.ModelsV3.RemoteUser;
import com.kampen.riksSe.api.remote_api.models.all_data_remote.home.activities.Remote_Star;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.model.Stars_Model;
import com.kampen.riksSe.user.model.Realm_User;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import io.realm.RealmList;


public class Constants {



    public final static  String MAP_KEY="AIzaSyDDjH_kMgOdbyoJ6_46pw_ZntAC9K1qvr4";  //"AIzaSyAy11QPMQf14buf7SlIvjRuWWMiRHQSVb0";//"AIzaSyDZb6POB0XTX0m2eNMbgXruT56dyRsTDXQ";                //"AIzaSyDpGipfKQXGO1JU9Z4A1dMD3BZxjh5dJDg";
    public final static  int   MAP_RADIUS=1000;                //"AIzaSyDpGipfKQXGO1JU9Z4A1dMD3BZxjh5dJDg";



    public static String START_ACTION = "service.action.start";

    public static String STOP_ACTION = "service.action.stop";

    public final static   String USER_EMAIL_TAG="_email";
    public final static   String USER_TODAY_DATE="localDate";


    public final static   String USER_PASS_TAG="_pass";

    public final static   String DAILY_ACTIVITY_DATE_TAG="user_activity_time";

    public final static   String ACTIVITY_ID="mId";
    public final static   String PLAN_ID="mPlanId";

    public final static   String USER_ROLE_TAG="Contestant";

    public final static   String NAVIGATING_FROM_TAG="From";

    public final static   String NAVIGATING_FROM_VALUE="notification";

    public static final String DefaultDate = "1975-11-12";





    public static boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{1,4})$").matcher(email).matches();
    }


    public static  final   String   DATE_FORMAT="yyyy-MM-dd";
    public static  final   String   TIME_FORMAT="HH:mm:ss";
    public static  final   String   TIME_YEAR="yyyy";
    public static  final   String   TIME_MONTH="MM";
    public static  final   String   TIME_HOUR="HH";
    public static  final   String   TIME_MINIUTE="mm";
    public static  final   String   TIME_SECOND="ss";
    public static  final   String   TIME_FORMAT_HR_MIN="HH:mm a";


    public static  final   String   DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static  final   String   DATE_TIME_UI_FORMAT="EEEE, MMMM d, yyyy";
    public static  final   String   DATE_TIME_FORMAT_UTC="MMM dd, yyy h:mm a zz";
    public static  final   String   DATE_TIME_ZONE_FORMAT="yyyy-MM-dd'T'HH:mm:ssZ ";
    public static  final   String   DATE_DAY_FORMAT="EEE";
    public static  final   String   DATE_NUMBER_FORMAT="d";


    public static void hideSoftKeyboard(View view,Context context) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public  static   int convertDpToPx(int dp,Context context){
        return Math.round(dp*(context.getResources().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));

    }


    public static String getAndroidVersion(int sdk) {
        switch (sdk) {
            case 1:  return                          "(Android 1.0)";
            case 2:  return  "Petit Four"          + "(Android 1.1)";
            case 3:  return  "Cupcake"             + "(Android 1.5)";
            case 4:  return  "Donut"               + "(Android 1.6)";
            case 5:  return  "Eclair"              + "(Android 2.0)";
            case 6:  return  "Eclair"              + "(Android 2.0.1)";
            case 7:  return  "Eclair"              + "(Android 2.1)";
            case 8:  return  "Froyo"               + "(Android 2.2)";
            case 9:  return  "Gingerbread"         + "(Android 2.3)";
            case 10: return  "Gingerbread"         + "(Android 2.3.3)";
            case 11: return  "Honeycomb"           + "(Android 3.0)";
            case 12: return  "Honeycomb"           + "(Android 3.1)";
            case 13: return  "Honeycomb"           + "(Android 3.2)";
            case 14: return  "Ice Cream Sandwich"  + "(Android 4.0)";
            case 15: return  "Ice Cream Sandwich"  + "(Android 4.0.3)";
            case 16: return  "Jelly Bean"          + "(Android 4.1)";
            case 17: return  "Jelly Bean"          + "(Android 4.2)";
            case 18: return  "Jelly Bean"          + "(Android 4.3)";
            case 19: return  "KitKat"              + "(Android 4.4)";
            case 20: return  "KitKat Watch"        + "(Android 4.4)";
            case 21: return  "Lollipop"            + "(Android 5.0)";
            case 22: return  "Lollipop"            + "(Android 5.1)";
            case 23: return  "Marshmallow"         + "(Android 6.0)";
            case 24: return  "Nougat"              + "(Android 7.0)";
            case 25: return  "Nougat"              + "(Android 7.1.1)";
            case 26: return  "Oreo"                + "(Android 8.0)";
            case 27: return  "Oreo"                + "(Android 8.1)";
            case 28: return  "Pie"                 + "(Android 9.0)";
            case 29: return  "Q"                   + "(Android 10.0)";
            default: return  "";
        }
    }

    public static String getLocalIpV6() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    System.out.println("ip1--:" + inetAddress);
                    System.out.println("ip2--:" + inetAddress.getHostAddress());

                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        return ipaddress;
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }private static final String OPPO_COLOROS_POWER_PACKAGE = "com.coloros.oppoguardelf";
    private static final String OPPO_COLOROS_POWER_ACTIVITY = "com.coloros.powermanager.fuelgaue.PowerConsumptionActivity";

    public static final Intent[] POWERMANAGER_INTENTS = {
            new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
            new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
            new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
            new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity"))
    };

    public void loadImage(final ImageView imageView, final String imageUrl) {

        /*Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
                .build();*/

        Picasso.get()
                .load(imageUrl)
                /* .transform(transformation)*/
                .resize(500, 500)
                .centerInside()
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }
                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")) {
                            updatedImageUrl = imageUrl.replace("https", "http");
                        } else {
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });
    }

    public static BitmapDescriptor getMarkerIcon(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }
    public static RecyclerView.LayoutManager  getHorizontalLayoutManager(Context context)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        return layoutManager;
    }


    public static RecyclerView.LayoutManager  getVerticalLayoutManager(Context context)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        return layoutManager;
    }

    public  static RemoteUser   DB_To_R_USER(Realm_User user)
    {
        RemoteUser  rUser=new RemoteUser();

        rUser.setFirstname(user.getF_name());
        rUser.setLastname(user.getL_name());
        rUser.setEmail(user.getEmail());
        rUser.setPassword(user.getPass());

        return rUser;

    }


    public static String encodeToBase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }


    public static Bitmap decodeToBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }


    public  static byte[]   BitmapToBytes(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, stream);
        final byte[] byteArray = stream.toByteArray();

        return byteArray;
    }


    public static File persistImage(Bitmap bitmap, String name,Context context) {

        File filesDir = context.getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os = null;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            //Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }
        return imageFile;
    }

    public static void setBadge(Context context, int count) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    public static String getLauncherClassName(Context context) {

        PackageManager pm = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resolveInfos) {
            String pkgName = resolveInfo.activityInfo.applicationInfo.packageName;
            if (pkgName.equalsIgnoreCase(context.getPackageName())) {
                String className = resolveInfo.activityInfo.name;
                return className;
            }
        }
        return null;
    }

    public static File bitmapToFile(Bitmap bmp,Context context) {
        try {
            int size = 0;
            ByteArrayOutputStream bos = new ByteArrayOutputStream(size);
            bmp.compress(Bitmap.CompressFormat.PNG, 80, bos);
            byte[] bArr = bos.toByteArray();
            bos.flush();
            bos.close();

            FileOutputStream fos = new FileOutputStream("mdroid.png");
            fos.write(bArr);
            fos.flush();
            fos.close();

            File mFile = new File(context.getFilesDir().getAbsolutePath(), "mdroid.png");
            return mFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isMyServiceRunning(Class<?> serviceClass,Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public   static    class  Converter
    {


        public  static int parseStrInt(String value)
        {
            try {

                return   Integer.parseInt(value);
            }catch (NumberFormatException ex)
            {

            }


            return  0;
        }

    }


    public static double meterDistanceBetweenPoints(double lat_a, double lng_a, double lat_b, double lng_b) {
        Location selected_location=new Location("locationA");
        selected_location.setLatitude(lat_a);
        selected_location.setLongitude(lng_a);
        Location near_locations=new Location("locationB");
        near_locations.setLatitude(lat_b);
        near_locations.setLongitude(lng_b);
        double distance=selected_location.distanceTo(near_locations);

        return distance;
    }

    public static void saveJasonToFile(Context context, String mJsonResponse,String name) {
        try {
            FileWriter file = new FileWriter(getFullFilePath(context,name));
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }


    private static String getFullFilePath(Context context, String filename) {
        File directory = context.getExternalFilesDir(null);
        File file = new File(directory, filename);
        if (!file.canRead()) {
            // error handling
        }
        return file.getAbsolutePath();
    }



    public  static   void  setProfileImage(String path, byte[] data, ImageView imageView,Context context)
    {


        if(data!=null)
        {
            byte []  profileData=data;
            if(profileData!=null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(profileData, 0, profileData.length);
                imageView.setImageBitmap(bmp);
            }
        }

        else if(path!=null && path.length()>0)
        {

            String imageURL=path.trim();

            if(imageURL.contains(APIUrl.IMAFE_BASE_URL))
            {

                BaseTarget target = new BaseTarget<BitmapDrawable>() {
                    @Override
                    public void onResourceReady(BitmapDrawable bitmap, Transition<? super BitmapDrawable> transition) {

                        imageView.setImageDrawable(bitmap);
                    }

                    @Override
                    public void getSize(SizeReadyCallback cb) {
                        cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL);
                    }

                    @Override
                    public void removeCallback(SizeReadyCallback cb) {

                    }
                };

                GlideApp
                        .with(context)
                        .load(imageURL)
                        .into(target);
            }
            else
            {

                Bitmap selectedImage = BitmapFactory.decodeFile(path);
                if(selectedImage!=null)
                {
                    imageView.setImageBitmap(selectedImage);
                }

            }

        }
        else
        {

        }


    }


    public static   void  Sort_StarsModel(RealmList<Stars_Model> list)
    {
        Collections.sort(list, new Comparator<Stars_Model>(){
            public int compare(Stars_Model obj1, Stars_Model obj2) {

                return obj1.getId().compareToIgnoreCase(obj2.getId());

            }
        });


    }


    public static void  Sort_StarsRemote(List<Remote_Star> list)
    {
        Collections.sort(list, new Comparator<Remote_Star>(){
            public int compare(Remote_Star obj1, Remote_Star obj2) {

                return obj1.getId().compareToIgnoreCase(obj2.getId());

            }
        });
    }




    public static final String MULTIPART_FORM_DATA = "multipart/form-data";


}
