package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Day;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.V3_Model.W_Video;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.DayTainingDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;

import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.adapter.WorkOutRelatedVideoListAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;

public class VideoPlayActivity extends AppCompatActivity implements VideoPlayContract.View, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, View.OnTouchListener {


    private boolean running=false;

    private MediaPlayer mediaPlayer;

    private TextView    timeTextView,timeTextView1;

    boolean   isVolumeTrue=true;


    private AVLoadingIndicatorView triangleProgressView;

    private ImageView mReplayVideo,mNextBtn,mSpeaker,mMute;


    private static final float BEEP_VOLUME = 0.80f;


    private static final float NO_VOLUME = 0.0f;

    //DayTainingDB dailyVideo;

    VideoView videoView = null;

    LinearLayout decription;

    LinearLayout Max,Min1,FullScreenMin,FullScreenMax;

    ImageView mScreenChange;

    RelativeLayout back;

    TextView Title,Dec,Raps,Sets,MusclePart,TitleView,DecView,RapsView,SetsView,MusclePartView;

    RecyclerView RelatatedVideoRV;

    WorkOutRelatedVideoListAdapter workOutRelatedVideoListAdapter;

    VideoPlayPresenter videoPlayPresenter;

    private MediaController mediaController;

    int orientation;
    int videoId;
    String HttpLiveVideo;
    int planID;
    List<W_Video> wVideolist;
    W_Video wVideo;
    int VideoPosition;
    MediaPlayer.OnInfoListener onInfoToPlayStateListener;
    Boolean isFullScreenMode=false;
    Boolean isMinScreenMode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        timeTextView=findViewById(R.id.timeTV);
        timeTextView1=findViewById(R.id.timeTV1);



        triangleProgressView=findViewById(R.id.triangleProgressView);

        mReplayVideo=findViewById(R.id.replayVideo);

        mNextBtn=findViewById(R.id.nextVideo);

        decription = findViewById(R.id.Description);

        FullScreenMin = findViewById(R.id.fullScreenMin);

        mScreenChange = findViewById(R.id.ScreenChange);

        FullScreenMax = findViewById(R.id.fullscreenMax);

        back = findViewById(R.id.top_bar);

        Max = findViewById(R.id.Max);

        Min1 = findViewById(R.id.Min1);

        triangleProgressView.show();

        Title = findViewById(R.id.Title);
        TitleView = findViewById(R.id.titleView);

        Dec = findViewById(R.id.Desc);
        DecView = findViewById(R.id.decView);

        Raps = findViewById(R.id.rapsValue);
        RapsView = findViewById(R.id.rapView);

        Sets = findViewById(R.id.SetsValue);
        SetsView = findViewById(R.id.setView);

        MusclePart = findViewById(R.id.musclePart);
        MusclePartView = findViewById(R.id.musclegroupView);

        RelatatedVideoRV =findViewById(R.id.RelatedVideoList);

        mSpeaker = findViewById(R.id.speaker);

        mMute = findViewById(R.id.mute);




        planID=getIntent().getIntExtra("planID",1);
        videoId=getIntent().getIntExtra("VideoID",1);


         videoPlayPresenter =  new VideoPlayPresenter(VideoPlayActivity.this,getApplicationContext());

        wVideolist = videoPlayPresenter.getTrainingVideo(planID,videoId);


        List<W_Video> wVideoListSorted = new ArrayList();
        wVideoListSorted.addAll(wVideolist);

        Collections.sort(wVideoListSorted);
        for(int i=0;i<wVideoListSorted.size();i++){
            if(wVideoListSorted.get(i).getmId()==videoId){
                wVideo = wVideoListSorted.get(i);
                VideoPosition=i;
            }
        }

         if(wVideo==null)
             return;

         try {

             onInfoToPlayStateListener   = new MediaPlayer.OnInfoListener() {

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

             videoView = (VideoView) findViewById(R.id.exerciseVideo);
             videoView.setOnCompletionListener(this);
             videoView.setOnPreparedListener(this);
             videoView.setOnTouchListener(this);
             mediaController = new MediaController(this);
             mediaController.setAnchorView(videoView);
             videoView.setMediaController(mediaController);
             HttpLiveVideo = wVideo.getmUrl();
             videoView.setVideoPath(wVideo.getmUrl());

             //videoView.setVideoPath("http://www.rikskampen.se/main_rikskampen/public/storage/recipes/August2019/1565017135.mp4");
             //videoView.setVideoURI(Uri.parse("https://www.rikskampen.se/main_rikskampen/public/storage/recipes/August2019/1565017135.mp4"));
             videoView.start();
             videoView.setOnInfoListener(onInfoToPlayStateListener);
             if(wVideo.getmTitle()!=null){
                 TitleView.setVisibility(View.VISIBLE);
                 Title.setVisibility(View.VISIBLE);
                 Title.setText(wVideo.getmTitle());
             }else{
                 TitleView.setVisibility(View.GONE);
                 Title.setVisibility(View.GONE);
             }
             if( wVideo.getmDescription()!=null){
                 DecView.setVisibility(View.VISIBLE);
                 Dec.setVisibility(View.VISIBLE);
                 Dec.setText(wVideo.getmDescription());
             }else{
                 DecView.setVisibility(View.GONE);
                 Dec.setVisibility(View.GONE);
             }
             if(wVideo.getmReps()!=0 ){
                 RapsView.setVisibility(View.VISIBLE);
                 Raps.setVisibility(View.VISIBLE);
                 Raps.setText(wVideo.getmReps()+"");
             }else{
                 RapsView.setVisibility(View.GONE);
                 Raps.setVisibility(View.GONE);
             }
             if(wVideo.getmSets()!=0){
                 SetsView.setVisibility(View.VISIBLE);
                 Sets.setVisibility(View.VISIBLE);
                 Sets.setText(wVideo.getmSets()+"");
             }else{
                 SetsView.setVisibility(View.GONE);
                 Sets.setVisibility(View.GONE);
             }
             if(wVideo.getmBodyPart()!=null){
                 MusclePartView.setVisibility(View.VISIBLE);
                 MusclePart.setVisibility(View.VISIBLE);
                 MusclePart.setText(wVideo.getmBodyPart());
             }else{
                 MusclePartView.setVisibility(View.GONE);
                 MusclePart.setVisibility(View.GONE);
             }

         }
         catch(Exception ex){

         }

/*

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //In landscape
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
            //Max.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
            FullScreenMax.setVisibility(View.GONE);
            FullScreenMin.setVisibility(View.VISIBLE);
            videoView.setScaleY(700);
            videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        } else {
            //In portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
            FullScreenMax.setVisibility(View.VISIBLE);
            FullScreenMin.setVisibility(View.GONE);
            //Max.setVisibility(View.VISIBLE);
        }
*/

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (videoView.isPlaying())
                {
                    if(mScreenChange.getVisibility() == View.GONE){
                        mScreenChange.setVisibility(View.VISIBLE);
                    }else{
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

                    if(mReplayVideo.getVisibility() == View.GONE && mNextBtn.getVisibility() == View.GONE){
                        mReplayVideo.setVisibility(View.VISIBLE);
                        mNextBtn.setVisibility(View.VISIBLE);
                    }else{
                        mReplayVideo.setVisibility(View.GONE);
                        mNextBtn.setVisibility(View.GONE);
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms
                            mReplayVideo.setVisibility(View.GONE);
                            mNextBtn.setVisibility(View.GONE);
                            mScreenChange.setVisibility(View.GONE);
                        }
                    }, 3000);
                }

                return false;
            }
        });



        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer=mp;

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
                        do{


                            timeTextView.post(new Runnable() {
                                public void run() {
                                    int time = (int) ((duration - videoView.getCurrentPosition())/1000);
                                    timeTextView.setText(time+"");

                                }
                            });
                            timeTextView1.post(new Runnable() {
                                public void run() {
                                    int time = (int) ((duration - videoView.getCurrentPosition())/1000);
                                    timeTextView1.setText(time+"");

                                }
                            });

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!running)
                            {

                                break;
                            }else{
                     /*     runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Stuff that updates the UI
                                        if(videoView.getCurrentPosition()<duration){
                                            if(mReplayVideo.getVisibility() == View.VISIBLE && mNextBtn.getVisibility() == View.VISIBLE){
                                                mReplayVideo.setVisibility(View.GONE);
                                                mNextBtn.setVisibility(View.GONE);
                                            }
                                        }
                                    }
                                });*/
                            }
                        }
                        while(videoView.getCurrentPosition()<duration);


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

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                mReplayVideo.setVisibility(View.VISIBLE);
                mNextBtn.setVisibility(View.VISIBLE);


            }
        });

        mReplayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triangleProgressView.setVisibility(View.VISIBLE);
                triangleProgressView.show();
                mReplayVideo.setVisibility(View.GONE);
                mNextBtn.setVisibility(View.GONE);
                videoView.setVideoPath(HttpLiveVideo);
                videoView.start();
                videoView.setOnInfoListener(onInfoToPlayStateListener);
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //videoView.stopPlayback();

                mReplayVideo.setVisibility(View.GONE);
                mNextBtn.setVisibility(View.GONE);

                if(VideoPosition < wVideoListSorted.size()-1){
                    VideoPosition+=1;
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    HttpLiveVideo = wVideoListSorted.get(VideoPosition).getmUrl();
                    videoView.setMediaController(mediaController);
                    videoView.setVideoURI(Uri.parse(wVideoListSorted.get(VideoPosition).getmUrl()));
                    videoView.requestFocus();
                    videoView.start();
                    videoView.setOnInfoListener(onInfoToPlayStateListener);
                    if(wVideo.getmTitle()!=null){
                        TitleView.setVisibility(View.VISIBLE);
                        Title.setVisibility(View.VISIBLE);
                        Title.setText(wVideoListSorted.get(VideoPosition).getmTitle());
                    }else{
                        TitleView.setVisibility(View.GONE);
                        Title.setVisibility(View.GONE);
                    }
                    if(wVideo.getmDescription()!=null){
                        DecView.setVisibility(View.VISIBLE);
                        Dec.setVisibility(View.VISIBLE);
                        Dec.setText(wVideoListSorted.get(VideoPosition).getmDescription());
                    }else{
                        DecView.setVisibility(View.GONE);
                        Dec.setVisibility(View.GONE);
                    }
                    if(wVideo.getmReps()!=0 ){
                        RapsView.setVisibility(View.VISIBLE);
                        Raps.setVisibility(View.VISIBLE);
                        Raps.setText(wVideoListSorted.get(VideoPosition).getmReps()+"");
                    }else{
                        RapsView.setVisibility(View.GONE);
                        Raps.setVisibility(View.GONE);
                    }
                    if(wVideo.getmSets()!=0){
                        SetsView.setVisibility(View.VISIBLE);
                        Sets.setVisibility(View.VISIBLE);
                        Sets.setText(wVideoListSorted.get(VideoPosition).getmSets()+"");
                    }else{
                        SetsView.setVisibility(View.GONE);
                        Sets.setVisibility(View.GONE);
                    }
                    if(wVideo.getmBodyPart()!=null){
                        MusclePartView.setVisibility(View.VISIBLE);
                        MusclePart.setVisibility(View.VISIBLE);
                        MusclePart.setText(wVideoListSorted.get(VideoPosition).getmBodyPart());
                    }else{
                        MusclePartView.setVisibility(View.GONE);
                        MusclePart.setVisibility(View.GONE);
                    }
                }else{
                    triangleProgressView.hide();
                    mReplayVideo.setVisibility(View.VISIBLE);
                    MyApplication.showSimpleSnackBar(VideoPlayActivity.this,"Nästa video är inte tillgänglig");
                }

            }
        });



        Max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
                //Max.setVisibility(View.GONE);
                back.setVisibility(View.GONE);
                FullScreenMax.setVisibility(View.GONE);
                FullScreenMin.setVisibility(View.VISIBLE);
                videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

            }
        });
        OrientationEventListener orientationEventListener = new OrientationEventListener(VideoPlayActivity.this,SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {

              /*  if (android.provider.Settings.System.getInt(getContentResolver(),
                        Settings.System.ACCELEROMETER_ROTATION, 0) == 1){
                   int orientationDevice=getResources().getConfiguration().orientation;
                    //Toast.makeText(getApplicationContext(), "Rotation ON", Toast.LENGTH_SHORT).show();
                    if(isFullScreenMode){
                        if ((orientation >= 0 && orientation < 60) || (orientation >= 150 && orientation <= 180) || (orientation >= 330 && orientation <= 359)) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        *//*int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);
                        back.setVisibility(View.VISIBLE);
                        videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));*//*
                        } else if ((orientation >= 70 && orientation <= 90) || (orientation >= 240 && orientation <= 270)) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        *//*back.setVisibility(View.GONE);
                        videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));*//*

                        }else{

                        }
                    }else{
                        isFullScreenMode=true;

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
                    /*if(isFullScreenMode){
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
                    }else{
                        isFullScreenMode=true;
                        *//*if(orientationDevice == Configuration.ORIENTATION_LANDSCAPE){
                            isFullScreenMode=true;
                            isMinScreenMode=true;
                        }else{
                            isFullScreenMode=true;
                            isMinScreenMode=false;
                        }*//*
                    }*/
                }

            }
        };
        if (orientationEventListener.canDetectOrientation()) {
            orientationEventListener.enable();
        }

        mScreenChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientation = getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    int videoVieoHight = (int) getResources().getDimension(R.dimen.videoview_height);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
                    back.setVisibility(View.VISIBLE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));
                    isFullScreenMode=false;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                    //isMinScreenMode=true;
                }else{
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
                    back.setVisibility(View.GONE);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                    isFullScreenMode=false;
                    //isMinScreenMode=false;
                }
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
               //Max.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
                videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, videoVieoHight));


            }
        });


    }

    public void setTimeDuration(MediaPlayer mp){
        final int duration = (int) mp.getDuration();
        int time = 0;
        time = (int) ((duration - videoView.getCurrentPosition())/1000);
        timeTextView.setText(time+"");
    }


    public void onVolumeClick(View view) {


        if ( videoView.isPlaying() )
        {
            // W_Video is playing

            if(isVolumeTrue)
            {
                mediaPlayer.setVolume(NO_VOLUME,NO_VOLUME);

                isVolumeTrue=false;
                mMute.setVisibility(View.VISIBLE);
                mSpeaker.setVisibility(View.GONE);
            }
            else
            {
                mediaPlayer.setVolume(BEEP_VOLUME,BEEP_VOLUME);
                isVolumeTrue=true;


                mSpeaker.setVisibility(View.VISIBLE);
                mMute.setVisibility(View.GONE);
            }
        }
        else
        {
            //W_Video is either stopped or buffering
        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /*Intent intent =new Intent(VideoPlayActivity.this, WorkOutNewUIActivity.class);
        intent.putExtra("selected_week",weekId);
        startActivity(intent);*/
        finish();
    }

    public void onBackClick(View view) {

        /*Intent intent =new Intent(VideoPlayActivity.this, WorkOutNewUIActivity.class);
        intent.putExtra("selected_week",weekId);
        startActivity(intent);*/
        finish();

    }

    @Override
    public void setPresenter(VideoPlayPresenter mPresenter) {

    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
