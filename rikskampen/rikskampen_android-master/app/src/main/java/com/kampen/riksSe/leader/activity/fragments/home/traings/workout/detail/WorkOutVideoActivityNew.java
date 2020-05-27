package com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.DayTainingDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.models.newP.T_WeekDB;
import com.kampen.riksSe.leader.activity.fragments.home.traings.workout.detail.adapter.WorkOutRelatedVideoListAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

//import android.media.MediaPlayer;
//import android.widget.VideoView;

public class WorkOutVideoActivityNew extends AppCompatActivity  implements VideoPlayContract.View{


    private boolean running=false;

    private MediaPlayer mediaPlayer;

    private TextView timeTextView,timeTextView1;

    boolean   isVolumeTrue=true;


    private AVLoadingIndicatorView triangleProgressView;

    private ImageView mReplayVideo,mSpeaker,mMute;


    private static final float BEEP_VOLUME = 0.80f;


    private static final float NO_VOLUME = 0.0f;

    DayTainingDB dailyVideo;

    VideoView videoView = null;

    LinearLayout decription;

    LinearLayout Max,Min1,FullScreenMin,back;

    TextView Title ,Dec;

    RecyclerView RelatatedVideoRV;

    int orientation;

    WorkOutRelatedVideoListAdapter workOutRelatedVideoListAdapter;

    VideoPlayPresenter videoPlayPresenter;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String httpLiveUrl;




        setContentView(R.layout.activity_recipe_play);

        int weekId=getIntent().getIntExtra("selected_week",1);
        int dayId=getIntent().getIntExtra("selected_day",1);
        int videoId=getIntent().getIntExtra("selected_video",1);


        orientation = getResources().getConfiguration().orientation;

        videoPlayPresenter =  new VideoPlayPresenter(WorkOutVideoActivityNew.this,getApplicationContext());

        dailyVideo = Repository.getVideoDetail(weekId,dayId,videoId);

        T_WeekDB t_weekDB = videoPlayPresenter.getTraingRelatedVideData(getApplicationContext(),weekId);


        if(dailyVideo==null)
            return;


        hideUI();
        mSpeaker = findViewById(R.id.speaker);
        mMute = findViewById(R.id.mute);
        triangleProgressView=findViewById(R.id.triangleProgressView);

        pDialog = new ProgressDialog(WorkOutVideoActivityNew.this);
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
        triangleProgressView.show();
        timeTextView=findViewById(R.id.timeTV);
        timeTextView1=findViewById(R.id.timeTV1);
        videoView = (VideoView) findViewById(R.id.exerciseVideo);
        videoView.setVideoURI(Uri.parse(dailyVideo.getMediaVideo()));
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        Title = findViewById(R.id.Title);
        Dec = findViewById(R.id.Desc);



        FullScreenMin = findViewById(R.id.fullScreenMin);
        back = findViewById(R.id.back);

        Max = findViewById(R.id.Max);
        Min1 = findViewById(R.id.Min1);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
            Max.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
            FullScreenMin.setVisibility(View.VISIBLE);
            videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        } else {
            // In portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮

            FullScreenMin.setVisibility(View.GONE);
            Max.setVisibility(View.VISIBLE);
        }




        /*if(RecipeVideo.equals("No URL")){

            return;
        }else {*/

        try {
            videoView = findViewById(R.id.exerciseVideo);
//            videoView.setVideoPath(RecipeVideo);
            videoView.start();
            Title.setText(dailyVideo.getTitle());
            Dec.setText(dailyVideo.getDescription());


        }
        catch(Exception ex){

        }
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
//                pDialog.dismiss();
                mediaPlayer=mp;

                triangleProgressView.hide();
                setTimeDuration(mp);
                videoView.start();
                hideUI();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        triangleProgressView.hide();
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
                            if(!running) break;
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

        videoView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    hideUI();
                }
            }
        });


        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            public void onBufferingUpdate(MediaPlayer mp, int percent)
            {


                setTimeDuration(mp);
                triangleProgressView.show();

            }

        });


        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

//                mReplayVideo.setVisibility(View.VISIBLE);


            }
        });

        Max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置横屏
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮
                Max.setVisibility(View.GONE);
                back.setVisibility(View.GONE);
                FullScreenMin.setVisibility(View.VISIBLE);
                videoView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));



            }
        });


        Min1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//常亮

                Min1.setVisibility(View.GONE);
                Max.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
            }
        });
        videoView.setOnInfoListener(onInfoToPlayStateListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_video_player, menu);
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

    private final MediaPlayer.OnInfoListener onInfoToPlayStateListener   = new MediaPlayer.OnInfoListener() {

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
    public void setTimeDuration(MediaPlayer mp){
        final int duration = (int) mp.getDuration();
        int time = 0;
        time = (int) ((duration - videoView.getCurrentPosition())/1000);
        timeTextView.setText(time+"");
    }
    public void hideUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

            getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(
                    new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                // set immersive mode sticky
                            }
                        }
                    });
        }
    }

    public void onVolumeClick(View view) {


        if ( videoView.isPlaying() )
        {
            // W_Video is playing

            if(isVolumeTrue)
            {
                videoView.setVolume(NO_VOLUME,NO_VOLUME);

                isVolumeTrue=false;
                mMute.setVisibility(View.VISIBLE);
                mSpeaker.setVisibility(View.GONE);
            }
            else
            {
                videoView.setVolume(BEEP_VOLUME,BEEP_VOLUME);
                isVolumeTrue=true;

                mSpeaker.setVisibility(View.VISIBLE);
                mMute.setVisibility(View.GONE);
            }
        }
        else
        {
            // W_Video is either stopped or buffering
        }




    }


    public void onBackClick(View view) {

        finish();

    }


    @Override
    public void setPresenter(VideoPlayPresenter mPresenter) {

    }
}