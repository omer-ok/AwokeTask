package com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

//import android.media.MediaPlayer;
//import android.widget.VideoView;

//import io.vov.vitamio.MediaPlayer;


import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;

import com.kampen.riksSe.leader.activity.fragments.home.nutrition.NutritionNewUIMealActivity;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.detail.adapter.DetailNutritionAdapter;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Meal;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.V3_Model.Recipe;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.DayNutritionDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_DayDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.N_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.nutrition.model.newP.NutritiousDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.DayTainingDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.VideoPlayPresenter;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.adapter.WorkOutRelatedVideoListAdapter;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;
import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.THRESHOLD;

public class RecipeVideoActivity extends AppCompatActivity  implements RecipeVideoContract.View, SensorEventListener {


    private boolean running = false;

    private MediaPlayer mediaPlayer;

    private TextView timeTextView, timeTextView1;

    boolean isVolumeTrue = true;

    private AVLoadingIndicatorView triangleProgressView;

    private ImageView mReplayVideo, mNextVideo, mSpeaker, mMute;


    private static final float BEEP_VOLUME = 0.80f;


    private static final float NO_VOLUME = 0.0f;

    DayTainingDB dailyVideo;

    VideoView videoView = null;

    LinearLayout decription;

    LinearLayout Max, Min1, FullScreenMin, FullScreenMax;
    RelativeLayout back;

    TextView Title, Dec, barTitle;

    private RecyclerView mNutritionDetailRV;

    private DetailNutritionAdapter mDetailNutritionItem;
    LinearLayoutManager mLayoutManager1;

    public N_WeekDB n_weekDB;
    public N_DayDB n_daysDB;


    int orientation;

    WorkOutRelatedVideoListAdapter workOutRelatedVideoListAdapter;

    VideoPlayPresenter videoPlayPresenter;
    ProgressDialog pDialog;
    String httpLiveUrl;
    String title;
    String Description;
    int WeekID;
    int DayID;
    int RecipeID;
    int MealID;
    int MealIngrediantID;
    String MealType;
    String MealName;
    Recipe recipeDb;
    List<Recipe> recipeList;
    int RecipePosition;
    ImageView mScreenChange;
    MediaPlayer.OnInfoListener onInfoToPlayStateListener;
    RecipeVideoPresenter recipeVideoPresenter;

    Boolean isFullScreenMode=false;

    private static final int THRESHOLD = 40;
    public static final int PORTRAIT = 0;
    public static final int LANDSCAPE = 270;
    public static final int REVERSE_PORTRAIT = 180;
    public static final int REVERSE_LANDSCAPE = 90;
    private int lastRotatedTo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Locale.getDefault().getLanguage().equals("en")){
            setContentView(R.layout.activity_recipe_play);
        }
        if(Locale.getDefault().getLanguage().equals("sv")){
            setContentView(R.layout.activity_recipe_play_sv);
        }


//        String httpLiveUrl;
        /*String httpLiveUrl = getIntent().getStringExtra("RecipeVideo");
        String title = getIntent().getStringExtra("title");
        String Description = getIntent().getStringExtra("Description");*/
        //List<IngredientsDB> ingredientsDBList = (List<IngredientsDB>) getIntent().getSerializableExtra("Ingrediants");

        /*MealType =getIntent().getStringExtra("RecipeID");
        MealName =getIntent().getStringExtra("MealName");
        WeekID = getIntent().getIntExtra("WeekID",1);
        DayID = getIntent().getIntExtra("DayID",1);*/
        MealID = getIntent().getIntExtra("MealID", 1);
        RecipeID = getIntent().getIntExtra("RecipeID", 1);
        MealIngrediantID = getIntent().getIntExtra("MealIngrediantID", 1);

        recipeVideoPresenter = new RecipeVideoPresenter(RecipeVideoActivity.this, RecipeVideoActivity.this);
        /*n_weekDB = getNutritionData(RecipeVideoActivity.this,WeekID);
        n_daysDB = n_weekDB.getDays().getDay1();*/

        /*DayNutritionDB dailyIngrediants = Repository.getRecipeIngrediantsDetail(WeekID,DayID,MealID,MealType,MealName);

        List<DayNutritionDB> dayNutritionDBList = Repository.getNextRecipeIngrediantsDetail(WeekID,DayID,MealType,MealName);
*/
        decription = findViewById(R.id.Description);
        mScreenChange = findViewById(R.id.ScreenChange);
        recipeList = recipeVideoPresenter.getRecipeMealDB(MealID, MealIngrediantID, RecipeID);

        for (int i = 0; i < recipeList.size(); i++) {
            if (recipeList.get(i).getmId() == RecipeID) {
                recipeDb = recipeList.get(i);
                RecipePosition = i;
            }

        }

        orientation = getResources().getConfiguration().orientation;

        mNutritionDetailRV = findViewById(R.id.IngrediantsList);

        Title = findViewById(R.id.Title);
        barTitle = findViewById(R.id.MealTitle);
        Dec = findViewById(R.id.Desc);

        timeTextView = findViewById(R.id.timeTV);
        timeTextView1 = findViewById(R.id.timeTV1);
        videoView = (VideoView) findViewById(R.id.exerciseVideo);

        FullScreenMin = findViewById(R.id.fullScreenMin);
        FullScreenMax = findViewById(R.id.fullscreenMax);
        back = findViewById(R.id.top_bar);

        Max = findViewById(R.id.Max);
        Min1 = findViewById(R.id.Min1);
        mSpeaker = findViewById(R.id.speaker);
        mMute = findViewById(R.id.mute);
        triangleProgressView = findViewById(R.id.triangleProgressView);
        mReplayVideo = findViewById(R.id.replayVideo);
        mNextVideo = findViewById(R.id.nextVideo);

        pDialog = new ProgressDialog(RecipeVideoActivity.this);
        // Set progressbar title
        pDialog.setTitle(getString(R.string.progress_dialog_title));
        // Set progressbar message
        pDialog.setMessage(getString(R.string.progress_dialog_message));

        pDialog.setCancelable(false);
        // Show progressbar
//        pDialog.show();

//        setContentView(R.layout.activity_videoplayer);

//        Intent intent = getIntent();
//        httpLiveUrl = intent.getStringExtra("EXTRA_URL");


        try {

            triangleProgressView.show();

        } catch (Exception ex) {

        }


        try {


            onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
                        setTimeDuration(mp);
                        triangleProgressView.show();
//                spinnerView.setVisibility(View.GONE);
                    }
                    if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
                        setTimeDuration(mp);
                        triangleProgressView.show();

                    }
                    if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
                        setTimeDuration(mp);
                        triangleProgressView.hide();
                    }
                    return false;
                }
            };

            if (recipeDb != null && recipeDb.getmIngredients().size() >= 0) {

                httpLiveUrl = recipeDb.getmMediaVideo();

                title = recipeDb.getmName();

                Description = recipeDb.getmDescription();

                mLayoutManager1 = new LinearLayoutManager(this);

                mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                mDetailNutritionItem = new DetailNutritionAdapter(RecipeVideoActivity.this, recipeDb.getmIngredients());

                mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                mNutritionDetailRV.setAdapter(mDetailNutritionItem);

            } else {


                //mNutritionDetailRV.setVisibility(View.GONE);


            }


            videoView.setVideoURI(Uri.parse(httpLiveUrl));
            //videoView.setVideoURI(Uri.parse("http://www.rikskampen.se/main_rikskampen/public/storage/recipes/August2019/1565017135.mp4"));
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.requestFocus();

            videoView.start();
            videoView.setOnInfoListener(onInfoToPlayStateListener);
            Title.setText(title);
            Dec.setText(Description);
            Dec.setMovementMethod(new ScrollingMovementMethod());

            //hideUI();


        } catch (Exception ex) {

        }

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (videoView.isPlaying()) {
                    if (mScreenChange.getVisibility() == View.GONE) {
                        mScreenChange.setVisibility(View.VISIBLE);
                    } else {
                        mScreenChange.setVisibility(View.GONE);
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            mScreenChange.setVisibility(View.GONE);
                        }
                    }, 3000);
                }else{
                    if(mScreenChange.getVisibility() == View.GONE){
                        mScreenChange.setVisibility(View.VISIBLE);
                    }else{
                        mScreenChange.setVisibility(View.GONE);
                    }

                    if(mReplayVideo.getVisibility() == View.GONE && mNextVideo.getVisibility() == View.GONE){
                        mReplayVideo.setVisibility(View.VISIBLE);
                        mNextVideo.setVisibility(View.VISIBLE);
                    }else{
                        mReplayVideo.setVisibility(View.GONE);
                        mNextVideo.setVisibility(View.GONE);
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            mReplayVideo.setVisibility(View.GONE);
                            mNextVideo.setVisibility(View.GONE);
                            mScreenChange.setVisibility(View.GONE);
                        }
                    }, 3000);
                }

                return false;
            }
        });

        Dec.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Dec.getParent().requestDisallowInterceptTouchEvent(true);

                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
//                pDialog.dismiss();
                mediaPlayer = mp;

                triangleProgressView.hide();
                setTimeDuration(mp);
                videoView.start();
                // hideUI();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        triangleProgressView.hide();
                        mScreenChange.setVisibility(View.VISIBLE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                mScreenChange.setVisibility(View.GONE);
                            }
                        }, 3000);
                    }
                });


                running = true;
                final int duration = (int) videoView.getDuration();

                new Thread(new Runnable() {
                    public void run() {
                        do {
                            timeTextView.post(new Runnable() {
                                public void run() {
                                    int time = (int) ((duration - videoView.getCurrentPosition()) / 1000);
                                    timeTextView.setText(time + "");

                                }
                            });
                            timeTextView1.post(new Runnable() {
                                public void run() {
                                    int time = (int) ((duration - videoView.getCurrentPosition()) / 1000);
                                    timeTextView1.setText(time + "");

                                }
                            });

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!running) break;
                        }
                        while (videoView.getCurrentPosition() < duration);
                    }
                }).start();

            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            // Close the progress bar and play the video
            public boolean onError(MediaPlayer mp, int x, int y) {
                // if (y == MediaPlayer.MEDIA_ERROR_UNSUPPORTED) {
//                pDialog.dismiss();
                // }
                return false;
            }
        });

        videoView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    //hideUI();
                }
            }
        });


        /*videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            public void onBufferingUpdate(MediaPlayer mp, int percent)
            {


                setTimeDuration(mp);
                triangleProgressView.show();

            }

        });*/

        mNextVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mReplayVideo.setVisibility(View.GONE);
                mNextVideo.setVisibility(View.GONE);

                if (RecipePosition < recipeList.size() - 1) {
                    RecipePosition += 1;
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    httpLiveUrl = recipeList.get(RecipePosition).getmMediaVideo();
                    videoView.setVideoURI(Uri.parse(recipeList.get(RecipePosition).getmMediaVideo()));
                    videoView.requestFocus();
                    videoView.start();
                    videoView.setOnInfoListener(onInfoToPlayStateListener);
                    Title.setText(recipeList.get(RecipePosition).getmName());
                    Dec.setText(recipeList.get(RecipePosition).getmDescription());
                    Dec.setMovementMethod(new ScrollingMovementMethod());
                    //barTitle.setText(recipeList.get(MealID).getmName());

                    mDetailNutritionItem.notifyDataSetChanged();

                    mLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);

                    mDetailNutritionItem = new DetailNutritionAdapter(RecipeVideoActivity.this, recipeList.get(RecipePosition).getmIngredients());

                    mNutritionDetailRV.setLayoutManager(mLayoutManager1);

                    mNutritionDetailRV.setAdapter(mDetailNutritionItem);

                } else {
                    mReplayVideo.setVisibility(View.VISIBLE);
                    MyApplication.showSimpleSnackBar(RecipeVideoActivity.this, "Nästa receptrecept är inte tillgänglig");
                }

            }
        });

        mReplayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                triangleProgressView.show();
                triangleProgressView.setVisibility(View.VISIBLE);
                mReplayVideo.setVisibility(View.GONE);
                mNextVideo.setVisibility(View.GONE);
                videoView.setVideoURI(Uri.parse(httpLiveUrl));
                videoView.requestFocus();
                videoView.start();
                videoView.setOnInfoListener(onInfoToPlayStateListener);

            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mReplayVideo.setVisibility(View.VISIBLE);
                mNextVideo.setVisibility(View.VISIBLE);

            }
        });

        OrientationEventListener orientationEventListener = new OrientationEventListener(RecipeVideoActivity.this,SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {

                /*if (android.provider.Settings.System.getInt(getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION, 0) == 1){
                   int orientationDevice=getResources().getConfiguration().orientation;
                    //Toast.makeText(getApplicationContext(), "Rotation ON", Toast.LENGTH_SHORT).show();
                    if ((orientation >= 0 && orientation < 60) || (orientation >= 150 && orientation <= 180) || (orientation >= 330 && orientation <= 359)) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);
                        back.setVisibility(View.VISIBLE);
                        videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));
                    } else if ((orientation >= 70 && orientation <= 90) || (orientation >= 240 && orientation <= 270)) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        back.setVisibility(View.GONE);
                        videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

                    }else{

                    }
                }
                else{
                    //Toast.makeText(getApplicationContext(), "Rotation OFF", Toast.LENGTH_SHORT).show();
                }*/

                if (android.provider.Settings.System.getInt(getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION, 0) == 1) {
                    int orientationDevice = getResources().getConfiguration().orientation;
                    if(orientationDevice == Configuration.ORIENTATION_LANDSCAPE){
                        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        back.setVisibility(View.GONE);
                        videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

                    }else{
                        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);
                        back.setVisibility(View.VISIBLE);
                        videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));
                    }

                }

                /*if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);
                    back.setVisibility(View.VISIBLE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));
                } else {
                    back.setVisibility(View.GONE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                }*/
            }
        };
        if (orientationEventListener.canDetectOrientation()) {
            orientationEventListener.enable();
        }
        /*SensorEventListener m_sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                orientation = getResources().getConfiguration().orientation;

                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);
                    back.setVisibility(View.VISIBLE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));
                } else {
                    back.setVisibility(View.GONE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(m_sensorEventListener, sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_NORMAL);*/
        mScreenChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    back.setVisibility(View.VISIBLE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                } else {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    back.setVisibility(View.GONE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });

        Max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮*/
                //Max.setVisibility(View.GONE);
                back.setVisibility(View.GONE);
                FullScreenMax.setVisibility(View.GONE);
                FullScreenMin.setVisibility(View.VISIBLE);
                videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            }
        });


        Min1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);


                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
                FullScreenMin.setVisibility(View.GONE);
                FullScreenMax.setVisibility(View.VISIBLE);
                // Max.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));


            }
        });

        videoView.setOnInfoListener(onInfoToPlayStateListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //  getMenuInflater().inflate(R.menu.menu_video_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    public void setTimeDuration(MediaPlayer mp) {
        final int duration = (int) mp.getDuration();
        int time = 0;
        time = (int) ((duration - videoView.getCurrentPosition()) / 1000);
        timeTextView.setText(time + "");
    }


    public void onVolumeClick(View view) {


        if (videoView.isPlaying()) {
            // W_Video is playing

            if (isVolumeTrue) {
                mediaPlayer.setVolume(NO_VOLUME, NO_VOLUME);

                isVolumeTrue = false;
                mMute.setVisibility(View.VISIBLE);
                mSpeaker.setVisibility(View.GONE);
            } else {
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                isVolumeTrue = true;


                mSpeaker.setVisibility(View.VISIBLE);
                mMute.setVisibility(View.GONE);
            }
        } else {
            // W_Video is either stopped or buffering
        }


    }

    public void onBackClick(View view) {

      /*  Intent intent =new Intent(RecipeVideoActivity.this, NutritionNewUIMealActivity.class);
        intent.putExtra("selected_week",WeekID);
        startActivity(intent);*/
        finish();

    }


    public Realm_User provideUserLocal(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        return user;
    }


    public N_WeekDB getNutritionData(Context context, int weekID) {
        Realm_User user = provideUserLocal(context);

        NutritiousDB nutritiousDB = Repository.getNutritionData(user.getId());
        N_WeekDB weelDB = null;

        // MyApplication.nutritiousDB=nutritiousDB;

        if (nutritiousDB != null) {

            switch (weekID) {
                case 1: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek1();
                    break;
                }
                case 2: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek2();
                    break;
                }

                case 3: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek3();
                    break;
                }

                case 4: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek4();
                    break;
                }

                case 5: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek5();
                    break;
                }

                case 6: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek6();
                    break;
                }

                case 7: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek7();
                    break;
                }

                case 8: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek8();
                    break;
                }

                case 9: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek9();
                    break;
                }

                case 10: {
                    weelDB = nutritiousDB.getWeekNutrition().getWeek10();
                    break;
                }

            }
        }

        return weelDB;

    }


    @Override
    public void setPresenter(RecipeVideoContract.Presenter mPresenter) {

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}