package com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.account.profile.LiveVideoCall.ModelV3.LiveVideoCallToken;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.ContestWeekDayTimeModel;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;
import com.twilio.video.CameraCapturer;
import com.twilio.video.ConnectOptions;
import com.twilio.video.LocalAudioTrack;
import com.twilio.video.LocalDataTrack;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.RemoteAudioTrack;
import com.twilio.video.RemoteAudioTrackPublication;
import com.twilio.video.RemoteDataTrack;
import com.twilio.video.RemoteDataTrackPublication;
import com.twilio.video.RemoteParticipant;
import com.twilio.video.RemoteVideoTrack;
import com.twilio.video.RemoteVideoTrackPublication;
import com.twilio.video.Room;
import com.twilio.video.TwilioException;
import com.twilio.video.Video;
import com.twilio.video.VideoTrack;
import com.twilio.video.VideoView;


import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.kampen.riksSe.utils.Constants.DATE_TIME_FORMAT;
import static com.kampen.riksSe.utils.UtilityTz.CompititionStartDateAndTimePopDaysHoursMinutes;

public class LiveVideoCallActivity extends AppCompatActivity implements LiveVideoCallActivityContract.View {

    Context mContext;
    Room room;
    VideoView mPrimaryVideoView;
    VideoView mSecondaryVideoView;
    LocalAudioTrack localAudioTrack;
    LocalVideoTrack localVideoTrack;
    LocalDataTrack localDataTrack;

    // Required for all types of tokens
    String twilioAccountSid = "AC8bf5292efd86156d6af15f341d7ed6c8";
    String twilioApiKey = "SK4e8c396289546d1b0dabd0ca31ce0081";
    String twilioApiSecret = "YhSocIU4IKlIsRdBVXIAbqgveWDJ33U9";
    String AcessToken;

    private AudioManager audioManager;
    String identity;
    Realm_User mUser;
    private int previousAudioMode;
    String RoomName;
    FloatingActionButton MuteVoiceBtn,SwitchCamera;
    ViewGroup  mRoot;
    int windowwidth; // Actually the width of the RelativeLayout.
    int windowheight; // Actually the height of the RelativeLayout.
    private ImageView mImageView;
    private ViewGroup mRrootLayout;
    private int _xDelta;
    private int _yDelta;
    TextView mParticipetenName,mHourCounter,mMiniuteCounter,mSecondsCounter;
    ImageView mParticipetenStatus;
    CountDownTimer cdt = null;
    LiveVideoCallActivityPresenter mLiveVideoCallActivityPresenter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_video_call);
        mContext = LiveVideoCallActivity.this;
        mUser = provideUserLocal(mContext);

        Bundle bundle = getIntent().getExtras();
        String mSessionEndTime = bundle.getString("SessionEndTime");
        String mTrainerID = bundle.getString("TrainerID");
        String mContestantID = bundle.getString("ContestantID");
        // Rendering a local video track requires an implementation of VideoRenderer
        // Let's assume we have added a VideoView in our view hierarchy
        mRoot = (ViewGroup ) findViewById(R.id.root);
        mPrimaryVideoView = (VideoView) findViewById(R.id.primaryVideo);
        mSecondaryVideoView = (VideoView) mRoot.findViewById(R.id.secondaryVideo);
        MuteVoiceBtn = (FloatingActionButton) findViewById(R.id.mute_action_fab);
        SwitchCamera = (FloatingActionButton) findViewById(R.id.switch_camera_action_fab);
        mParticipetenName = (TextView) findViewById(R.id.particpetentName);
        mParticipetenStatus = (ImageView) findViewById(R.id.partisiptantStatus);
        mHourCounter = findViewById(R.id.hourCounter);
        mMiniuteCounter = findViewById(R.id.miniuteCounter);
        mSecondsCounter = findViewById(R.id.secondsCounter);

        //mSecondaryVideoView.setOnTouchListener(this);
        //mRoot.addView(mSecondaryVideoView);

        /*//Capture the width of the RelativeLayout once it is laid out.
        mRoot.post(new Runnable() {
            @Override
            public void run() {
                windowwidth = mRoot.getWidth();
                windowheight = mRoot.getHeight();
            }
        });*/

        /*
         * Needed for setting/abandoning audio focus during call
         */
        mLiveVideoCallActivityPresenter = new LiveVideoCallActivityPresenter(LiveVideoCallActivity.this);
        mLiveVideoCallActivityPresenter.getLiveVideoCallAccessToken(mContext,mTrainerID,mContestantID);
        CounterContest(mSessionEndTime);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setSpeakerphoneOn(true);

        // Create an audio track
        boolean enable = true;

        localAudioTrack = LocalAudioTrack.create(mContext, enable);

       // A video track requires an implementation of VideoCapturer
        CameraCapturer cameraCapturer = new CameraCapturer(mContext,
                CameraCapturer.CameraSource.FRONT_CAMERA);

        //Create a video track
        localVideoTrack = LocalVideoTrack.create(mContext, enable, cameraCapturer);
        localDataTrack =LocalDataTrack.create(mContext);

        //Room Name
        /*RoomName = mTrainerID+"_"+mContestantID;*/
        //RoomName = "Test Room";
        // Render a local video track to preview your camera
        localVideoTrack.addRenderer(mSecondaryVideoView);

        mSecondaryVideoView.bringToFront();


        MuteVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * Enable/disable the local audio track. The results of this operation are
                 * signaled to other Participants in the same Room. When an audio track is
                 * disabled, the audio is muted.
                 */
                if (localAudioTrack != null) {
                    boolean enable = !localAudioTrack.isEnabled();
                    localAudioTrack.enable(enable);
                    int icon = enable ?
                            R.drawable.ic_mic_white_24dp : R.drawable.ic_mic_off_black_24dp;
                    MuteVoiceBtn.setImageDrawable(ContextCompat.getDrawable(
                            mContext, icon));
                }
            }
        });


        SwitchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cameraCapturer != null) {
                    CameraCapturer.CameraSource cameraSource = cameraCapturer.getCameraSource();
                    cameraCapturer.switchCamera();
                    if (mSecondaryVideoView.getVisibility() == View.VISIBLE) {
                        mSecondaryVideoView.setMirror(cameraSource == CameraCapturer.CameraSource.BACK_CAMERA);
                    } else {
                        mSecondaryVideoView.setMirror(cameraSource == CameraCapturer.CameraSource.BACK_CAMERA);
                    }
                }
            }
        });


    }

    private Room.Listener roomListener() {
        return new Room.Listener() {
            @Override
            public void onConnected(Room room) {
                //Log.d(TAG,"Connected to " + room.getName());
                enableAudioFocus(true);
                enableVolumeControl(true);

                for (RemoteParticipant remoteParticipant :  room.getRemoteParticipants()) {
                    addRemoteParticipant(remoteParticipant);
                    break;
                }
            }

            @Override
            public void onConnectFailure(@NonNull Room room, @NonNull TwilioException twilioException) {

            }

            @Override
            public void onReconnecting(@NonNull Room room, @NonNull TwilioException twilioException) {

            }

            @Override
            public void onReconnected(@NonNull Room room) {

            }

            @Override
            public void onDisconnected(@NonNull Room room, @Nullable TwilioException twilioException) {

            }

            @Override
            public void onParticipantConnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant) {
               // remoteParticipant.setListener(remoteParticipantListener());
                addRemoteParticipant(remoteParticipant);
            }

            @Override
            public void onParticipantDisconnected(@NonNull Room room, @NonNull RemoteParticipant remoteParticipant) {
                mParticipetenName.setText("");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mParticipetenStatus.setBackgroundTintList(null);
                }
                mParticipetenStatus.setBackgroundResource(R.drawable.circle_offline_btn_background);
                Toast.makeText(mContext, remoteParticipant.getIdentity()+" is Offline", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRecordingStarted(@NonNull Room room) {

            }

            @Override
            public void onRecordingStopped(@NonNull Room room) {

            }
        };
    }

    public void connectToRoom(String roomName,String accessToken) {

        ConnectOptions.Builder connectOptionsBuilder = new ConnectOptions.Builder(accessToken)
                .roomName(roomName);

        /*
         * Add local audio track to connect options to share with participants.
         */
        if (localAudioTrack != null) {
            connectOptionsBuilder
                    .audioTracks(Collections.singletonList(localAudioTrack));
        }

        /*
         * Add local video track to connect options to share with participants.
         */
        if (localVideoTrack != null) {
            connectOptionsBuilder.videoTracks(Collections.singletonList(localVideoTrack));
        }

        if(localDataTrack != null){
            connectOptionsBuilder.dataTracks(Collections.singletonList(localDataTrack));
        }
        room = Video.connect(this, connectOptionsBuilder.build(), roomListener());


       /* ConnectOptions connectOptions = new ConnectOptions.Builder(accessToken)
                .roomName(roomName)
                .audioTracks(Collections.singletonList(localAudioTrack))
                .videoTracks(Collections.singletonList(localVideoTrack))
                .dataTracks(Collections.singletonList(localDataTrack))
                .build();
        room = Video.connect(mContext, connectOptions, roomListener());*/

    }


    private RemoteParticipant.Listener remoteParticipantListener() {
        return new RemoteParticipant.Listener() {
            @Override
            public void onAudioTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {

            }

            @Override
            public void onAudioTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {

            }

            @Override
            public void onAudioTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull RemoteAudioTrack remoteAudioTrack) {

            }

            @Override
            public void onAudioTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull TwilioException twilioException) {

            }

            @Override
            public void onAudioTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication, @NonNull RemoteAudioTrack remoteAudioTrack) {

            }

            @Override
            public void onVideoTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {

            }

            @Override
            public void onVideoTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {

            }

            @Override
            public void onVideoTrackSubscribed(RemoteParticipant participant,
                                               RemoteVideoTrackPublication remoteVideoTrackPublication,
                                               RemoteVideoTrack remoteVideoTrack) {
                addRemoteParticipantVideo(remoteVideoTrack);
            }

            @Override
            public void onVideoTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull TwilioException twilioException) {

            }

            @Override
            public void onVideoTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication, @NonNull RemoteVideoTrack remoteVideoTrack) {

            }

            @Override
            public void onDataTrackPublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication) {

            }

            @Override
            public void onDataTrackUnpublished(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication) {

            }

            @Override
            public void onDataTrackSubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull RemoteDataTrack remoteDataTrack) {

            }

            @Override
            public void onDataTrackSubscriptionFailed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull TwilioException twilioException) {

            }

            @Override
            public void onDataTrackUnsubscribed(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteDataTrackPublication remoteDataTrackPublication, @NonNull RemoteDataTrack remoteDataTrack) {

            }

            @Override
            public void onAudioTrackEnabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {

            }

            @Override
            public void onAudioTrackDisabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteAudioTrackPublication remoteAudioTrackPublication) {

            }

            @Override
            public void onVideoTrackEnabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {

            }

            @Override
            public void onVideoTrackDisabled(@NonNull RemoteParticipant remoteParticipant, @NonNull RemoteVideoTrackPublication remoteVideoTrackPublication) {

            }
        };
    }

    public String TokenGentrator(){
        // Required for Video
        String identity = mUser.getF_name();

        // Create Video grant
        VideoGrant grant = new VideoGrant().setRoom(RoomName);

        // Create access token
        AccessToken token = new AccessToken.Builder(
                twilioAccountSid,
                twilioApiKey,
                twilioApiSecret
        ).identity(identity).grant(grant).build();

        //System.out.println(token.toJwt());
        return token.toJwt();
    }

    private void addRemoteParticipant(RemoteParticipant remoteParticipant) {
        /*
         * This app only displays video for one additional participant per Room
         */
       /* if (thumbnailVideoView.getVisibility() == View.VISIBLE) {
            Snackbar.make(connectActionFab,
                    "Rendering multiple participants not supported in this app",
                    Snackbar.LENGTH_LONG)
                    .show();

            return;
        }
        remoteParticipantIdentity = remoteParticipant.getIdentity();
        statusTextView.setText("RemoteParticipant " + remoteParticipantIdentity + " joined");*/

        /*
         * Add remote participant renderer
         */
        if (remoteParticipant.getRemoteVideoTracks().size() > 0) {
            RemoteVideoTrackPublication remoteVideoTrackPublication =
                    remoteParticipant.getRemoteVideoTracks().get(0);

            /*
             * Only render video tracks that are subscribed to
             */
            if (remoteVideoTrackPublication.isTrackSubscribed()) {

                addRemoteParticipantVideo(remoteVideoTrackPublication.getRemoteVideoTrack());

            }
        }

        mParticipetenName.setText(remoteParticipant.getIdentity().substring(0,1).toUpperCase() + remoteParticipant.getIdentity().substring(1));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mParticipetenStatus.setBackgroundTintList(null);
        }
        mParticipetenStatus.setBackgroundResource(R.drawable.circle_online_btn_background);
        /*
         * Start listening for participant media events
         */
        remoteParticipant.setListener(remoteParticipantListener());
    }
    private void addRemoteParticipantVideo(VideoTrack videoTrack) {
      //  moveLocalVideoToThumbnailView();
        mPrimaryVideoView.setMirror(false);
        videoTrack.addRenderer(mPrimaryVideoView);
        mSecondaryVideoView.bringToFront();
    }
    public Realm_User provideUserLocal(Context context)
    {

        try{
            String [] params= SaveSharedPreference.getLoggedParams(context);

            Realm_User user= Repository.provideUserLocal(params[0],params[1]);
            return user;
        }catch (Exception e){

        }

        return  null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (localAudioTrack != null) {
            localAudioTrack.release();
            localAudioTrack = null;
        }
        if (localVideoTrack != null) {
            localVideoTrack.release();
            localVideoTrack = null;
        }

        // To disconnect from a Room, we call:
        if(room!=null){
            room.disconnect();
        }


        enableAudioFocus(false);
        enableVolumeControl(false);
    }

    @Override
    public void onBackPressed() {
        finish();
        if (localAudioTrack != null) {
            localAudioTrack.release();
            localAudioTrack = null;
        }
        if (localVideoTrack != null) {
            localVideoTrack.release();
            localVideoTrack = null;
        }

        // To disconnect from a Room, we call:
        room.disconnect();
        enableAudioFocus(false);
        enableVolumeControl(false);
    }

    public void onBackClick(View view) {

        finish();
// To disconnect from a Room, we call:
        room.disconnect();


        enableAudioFocus(false);
        enableVolumeControl(false);

        if (localAudioTrack != null) {
            localAudioTrack.release();
            localAudioTrack = null;
        }
        if (localVideoTrack != null) {
            localVideoTrack.release();
            localVideoTrack = null;
        }

    }

    private void removeParticipantVideo(VideoTrack videoTrack) {
        videoTrack.removeRenderer(mPrimaryVideoView);
    }

    private void enableAudioFocus(boolean focus) {
        if (focus) {
            previousAudioMode = audioManager.getMode();
            // Request audio focus before making any device switch.
            requestAudioFocus();
            /*
             * Use MODE_IN_COMMUNICATION as the default audio mode. It is required
             * to be in this mode when playout and/or recording starts for the best
             * possible VoIP performance. Some devices have difficulties with
             * speaker mode if this is not set.
             */
            audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        } else {
            audioManager.setMode(previousAudioMode);
            audioManager.abandonAudioFocus(null);
        }
    }

    private void requestAudioFocus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioAttributes playbackAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_VOICE_COMMUNICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();
            AudioFocusRequest focusRequest =
                    new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                            .setAudioAttributes(playbackAttributes)
                            .setAcceptsDelayedFocusGain(true)
                            .setOnAudioFocusChangeListener(
                                    i -> { })
                            .build();
            audioManager.requestAudioFocus(focusRequest);
        } else {
            audioManager.requestAudioFocus(null, AudioManager.STREAM_VOICE_CALL,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        }
    }


    public void CounterContest(String EndTime){

        try{

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
            String currentDateandTime = sdf.format(new Date());

           /* SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
            String currentDate = sdf1.format(new Date());*/

            long total_millis = 0;

            ContestWeekDayTimeModel contestWeekDayTimeModel = CompititionStartDateAndTimePopDaysHoursMinutes(EndTime,currentDateandTime);

            if(cdt!=null){
                total_millis = (contestWeekDayTimeModel.getStratTimeInMilli() - contestWeekDayTimeModel.getEndTimeInMilli());
                cdt.cancel();
            }else{
                total_millis = (contestWeekDayTimeModel.getStratTimeInMilli() - contestWeekDayTimeModel.getEndTimeInMilli());
            }

            cdt = new CountDownTimer( total_millis,1000) {
                @Override
                public void onTick(long millisUntilFinished) {


                    long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

                    long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);

                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                    mHourCounter.setText(hours+"h");
                    mMiniuteCounter.setText(" : "+minutes+"m");
                    mSecondsCounter.setText(" : "+seconds+"s");
                }
                @Override
                public void onFinish() {
                    cdt.cancel();
                    finish();
                    Toast.makeText(mContext, "Your Live Session is Expired", Toast.LENGTH_LONG).show();
                }
            };
            if(cdt!=null){
                cdt.start();
            }
        }catch (Exception e){

        }

    }

    private void enableVolumeControl(boolean volumeControl) {
        if (volumeControl) {
            /*
             * Enable changing the volume using the up/down keys during a conversation
             */
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
        } else {
            setVolumeControlStream(getVolumeControlStream());
        }
    }

    @Override
    public void SetLiveVideoCallTokenSucess(String mesage, LiveVideoCallToken liveVideoCallToken) {
        AcessToken=liveVideoCallToken.getToken();
        connectToRoom(liveVideoCallToken.getRoomName(),AcessToken);
    }

    @Override
    public void SetLiveVideoCallTokenFiled(String mesage) {

    }

    @Override
    public void setPresenter(LiveVideoCallActivityContract.Presenter mPresenter) {

    }

/*    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
        mRoot.invalidate();
        return true;
    }*/

}
