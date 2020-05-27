package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.adapter.WorkOutRelatedVideoListAdapter;
import com.wang.avi.AVLoadingIndicatorView;

public class WorkoutMotivationVideo extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, View.OnTouchListener{


    private boolean running=false;

    private MediaPlayer mediaPlayer;

    private TextView timeTextView,timeTextView1;

    boolean   isVolumeTrue=true;


    private AVLoadingIndicatorView triangleProgressView;

    private ImageView mReplayVideo,mNextBtn,mSpeaker,mMute;


    private static final float BEEP_VOLUME = 0.80f;


    private static final float NO_VOLUME = 0.0f;


    String VideoTitle,DecTxt,RapsTxt,SetsTxt,MusclePartTxt,VideoUrl;

    VideoView videoView = null;

    LinearLayout decription;

    LinearLayout Max,Min1,FullScreenMin,FullScreenMax;

    RelativeLayout back;

    TextView Title,Dec,Raps,Sets,MusclePart,TitleView,DecView,RapsView,SetsView,MusclePartView;

    RecyclerView RelatatedVideoRV;

    WorkOutRelatedVideoListAdapter workOutRelatedVideoListAdapter;

    VideoPlayPresenter videoPlayPresenter;

    private MediaController mediaController;

    int orientation;
    int videoId;
    String HttpLiveVideo;
    int weekId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        timeTextView=findViewById(R.id.timeTV);
        timeTextView1=findViewById(R.id.timeTV1);

        orientation = getResources().getConfiguration().orientation;

        triangleProgressView=findViewById(R.id.triangleProgressView);

        mReplayVideo=findViewById(R.id.replayVideo);
        mNextBtn=findViewById(R.id.nextVideo);

        decription = findViewById(R.id.Description);

        FullScreenMin = findViewById(R.id.fullScreenMin);
        FullScreenMax = findViewById(R.id.fullscreenMax);
        back = findViewById(R.id.top_bar);

        Max = findViewById(R.id.Max);
        Min1 = findViewById(R.id.Min1);

        triangleProgressView.show();

        Title = findViewById(R.id.Title);

        Dec = findViewById(R.id.Desc);

        RelatatedVideoRV =findViewById(R.id.RelatedVideoList);

        mSpeaker = findViewById(R.id.speaker);

        mMute = findViewById(R.id.mute);

        VideoTitle =getIntent().getStringExtra("VideoTitle");
        DecTxt =getIntent().getStringExtra("DecTxt");
        RapsTxt =getIntent().getStringExtra("RapsTxt");
        SetsTxt =getIntent().getStringExtra("SetsTxt");
        MusclePartTxt =getIntent().getStringExtra("MusclePartTxt");
        VideoUrl =getIntent().getStringExtra("VideoUrl");

        TitleView = findViewById(R.id.titleView);

        DecView = findViewById(R.id.decView);

        Raps = findViewById(R.id.rapsValue);
        RapsView = findViewById(R.id.rapView);

        Sets = findViewById(R.id.SetsValue);
        SetsView = findViewById(R.id.setView);

        MusclePart = findViewById(R.id.musclePart);
        MusclePartView = findViewById(R.id.musclegroupView);



        if(VideoUrl==null){
            TitleView.setVisibility(View.GONE);
            Title.setVisibility(View.GONE);
            DecView.setVisibility(View.GONE);
            Dec.setVisibility(View.GONE);
            RapsView.setVisibility(View.GONE);
            Raps.setVisibility(View.GONE);
            SetsView.setVisibility(View.GONE);
            Sets.setVisibility(View.GONE);
            MusclePartView.setVisibility(View.GONE);
            MusclePart.setVisibility(View.GONE);
            return;
        }


        try {
            videoView = (VideoView) findViewById(R.id.exerciseVideo);
            videoView.setOnCompletionListener(this);
            videoView.setOnPreparedListener(this);
            videoView.setOnTouchListener(this);
            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            HttpLiveVideo = VideoUrl;
            videoView.setVideoPath(VideoUrl);
            //videoView.setVideoPath("http://www.rikskampen.se/main_rikskampen/public/storage/recipes/August2019/1565017135.mp4");
            //videoView.setVideoURI(Uri.parse("https://www.rikskampen.se/main_rikskampen/public/storage/recipes/August2019/1565017135.mp4"));
            videoView.start();
          /*  Title.setText(VideoTitle);
            Dec.setText(VideoTitle);*/

            if(!VideoTitle.isEmpty() || VideoTitle!=null){
                TitleView.setVisibility(View.VISIBLE);
                Title.setVisibility(View.VISIBLE);
                Title.setText(VideoTitle);
            }else{
                TitleView.setVisibility(View.GONE);
                Title.setVisibility(View.GONE);
            }
            if(DecTxt!=null){
                DecView.setVisibility(View.VISIBLE);
                Dec.setVisibility(View.VISIBLE);
                Dec.setText(DecTxt);
            }else{
                DecView.setVisibility(View.GONE);
                Dec.setVisibility(View.GONE);
            }
            if(RapsTxt!=null){
                RapsView.setVisibility(View.VISIBLE);
                Raps.setVisibility(View.VISIBLE);
                Raps.setText(RapsTxt);
            }else{
                RapsView.setVisibility(View.GONE);
                Raps.setVisibility(View.GONE);
            }
            if(SetsTxt!=null){
                SetsView.setVisibility(View.VISIBLE);
                Sets.setVisibility(View.VISIBLE);
                Sets.setText(SetsTxt);
            }else{
                SetsView.setVisibility(View.GONE);
                Sets.setVisibility(View.GONE);
            }
            if(MusclePartTxt!=null){
                MusclePartView.setVisibility(View.VISIBLE);
                MusclePart.setVisibility(View.VISIBLE);
                MusclePart.setText(MusclePartTxt);
            }else{
                MusclePartView.setVisibility(View.GONE);
                MusclePart.setVisibility(View.GONE);
            }



        }
        catch(Exception ex){

        }


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



        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {

                mediaPlayer=mp;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        triangleProgressView.setVisibility(View.INVISIBLE);
                    }
                });


                running = true;
                final int duration = videoView.getDuration();

                new Thread(new Runnable() {
                    public void run() {
                        do{
                            timeTextView.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    timeTextView.setText(time+"");

                                }
                            });
                            timeTextView1.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    timeTextView1.setText(time+"");

                                }
                            });

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!running) break;
                        }
                        while(videoView.getCurrentPosition()<duration);
                    }
                }).start();
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
                videoView.setVideoPath(VideoUrl);
                videoView.start();

            }
        });

       /* mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                videoView.stopPlayback();
                triangleProgressView.show();
                mReplayVideo.setVisibility(View.GONE);
                mNextBtn.setVisibility(View.GONE);

                if(videoId == 0){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=1;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,1);
                    if(dailyVideo != null){

                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }


                }else if(videoId == 1){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=2;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,2);
                    if(dailyVideo !=null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());

                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }

                }else if(videoId==2){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=3;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,3);
                    if(dailyVideo != null ){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }

                }else if(videoId==3){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=4;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,4);
                    if(dailyVideo !=null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }


                }else if(videoId==4){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=5;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,5);
                    if(dailyVideo !=null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }

                }else if(videoId==5){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=6;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,6);
                    if(dailyVideo !=null){

                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }

                }else if(videoId==6){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=7;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,7);
                    if(dailyVideo !=null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }
                }else if(videoId == 7){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=8;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,8);
                    if(dailyVideo !=null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(WorkoutMotivationVideo.this,"W_Video Not Available");
                    }

                }else if(videoId == 8){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=9;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,9);
                    if(dailyVideo !=null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(VideoPlayActivity.this,"W_Video Not Available");
                    }

                }else if(videoId == 9){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=10;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,10);
                    if(dailyVideo!=null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(VideoPlayActivity.this,"W_Video Not Available");
                    }

                }else if(videoId ==10){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=11;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,11);
                    if(dailyVideo !=null){

                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(VideoPlayActivity.this,"W_Video Not Available");
                    }

                }else if(videoId ==11){
                    triangleProgressView.setVisibility(View.VISIBLE);
                    triangleProgressView.show();
                    videoId=12;
                    dailyVideo = Repository.getVideoDetail(weekId,dayId,0);
                    if(dailyVideo != null){
                        HttpLiveVideo = dailyVideo.getMediaVideo();
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                        videoView.setVideoPath(dailyVideo.getMediaVideo());
                        videoView.start();
                        Title.setText(dailyVideo.getTitle());
                        Dec.setText(dailyVideo.getDescription());
                    }else {
                        triangleProgressView.hide();
                        MyApplication.showSimpleSnackBar(VideoPlayActivity.this,"W_Video Not Available");
                    }

                }


            }
        });
*/

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
