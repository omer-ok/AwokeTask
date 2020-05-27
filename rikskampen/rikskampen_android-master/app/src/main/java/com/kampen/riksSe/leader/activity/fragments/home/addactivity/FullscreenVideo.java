package com.kampen.riksSe.leader.activity.fragments.home.addactivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.kampen.riksSe.R;
import com.wang.avi.AVLoadingIndicatorView;

public class FullscreenVideo extends AppCompatActivity {

    VideoView videoView;
    private MediaPlayer mediaPlayer;
    private AVLoadingIndicatorView triangleProgressView;

    int orientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_video_activity);

        String VideoURL = getIntent().getStringExtra("VideoURL");
        int SeekPosition = getIntent().getIntExtra("SeekPosition",0);

        orientation = getResources().getConfiguration().orientation;
        videoView = findViewById(R.id.exerciseVideo);
        triangleProgressView=findViewById(R.id.triangleProgressView);

        triangleProgressView.show();
        MediaController mediaController = new MediaController(FullscreenVideo.this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        //videoView.setVideoPath("http://riks.pt2019.ae/riksAssets/videos/RK1small.mp4");
        //"https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4"

        videoView.setVideoPath(VideoURL);
        videoView.seekTo(SeekPosition);
        //videoView.setVideoURI(Uri.parse(dailyVideo.getMediaVideo()));
        videoView.start();
        /*if (FullscreenVideo.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
            //Max.setVisibility(View.GONE);

            videoView.setScaleY(700);
            videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        } else {
            // In portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮

            //Max.setVisibility(View.VISIBLE);
        }*/
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {

                mediaPlayer=mp;


               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        triangleProgressView.hide();
                    }
                });


               // running = true;
                final int duration = videoView.getDuration();

               /* new Thread(new Runnable() {
                    public void run() {
                        do{
                            *//*timeTextView.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    timeTextView.setText(time+"");

                                }
                            });*//*
                 *//*  timeTextView1.post(new Runnable() {
                                public void run() {
                                    int time = (duration - videoView.getCurrentPosition())/1000;
                                    timeTextView1.setText(time+"");

                                }
                            });*//*

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!running) break;
                        }
                        while(videoView.getCurrentPosition()<duration);
                    }
                }).start();*/
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {




            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if (FullscreenVideo.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    // In landscape
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
                    //Max.setVisibility(View.GONE);

                    videoView.setScaleY(700);
                    videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

                } else {
                    // In portrait
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮

                    //Max.setVisibility(View.VISIBLE);
                }*/
            }
        });
    }

}
