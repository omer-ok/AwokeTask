package com.kampen.riksSe.leader.activity.fragments.chat;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.StorageReference;
import com.kampen.riksSe.DI.LocalApiService;
import com.kampen.riksSe.DI.ServiceLocator;
import com.kampen.riksSe.MyApplication;
import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.MainLeaderActivity;
import com.kampen.riksSe.login.LoginActivity;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.Custom_Progress_Module.ProgressManager;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;
import io.realm.Realm;
import me.leolin.shortcutbadger.ShortcutBadger;


import static com.kampen.riksSe.utils.Constants.setBadge;

public class ChatActivity extends AppCompatActivity implements ChatActivityContract.View{



    private ChatView mChatView;
    public StorageReference mStorageRef;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    TextView TrainerName;
    ImageView TrainerProfile;

    private DatabaseReference root ;
    private String temp_key,keyCheck;
    Realm_User user;
    int count = 0;
    long time = 0L;
    Boolean isLoadingMsgFirstTime=true;
    SwipeRefreshLayout pullToRefresh;

    final Handler handler = new Handler();

    private ChatActivityPresenter chatActivityPresenter;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mChatView=findViewById(R.id.chat_view);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back_arrow);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setImageResource(R.drawable.logo_actionbar);
        android.support.v7.app.ActionBar.LayoutParams layoutParams = new android.support.v7.app.ActionBar.LayoutParams(
                android.support.v7.app.ActionBar.LayoutParams.WRAP_CONTENT,
                android.support.v7.app.ActionBar.LayoutParams.WRAP_CONTENT, Gravity.START
                *//*| Gravity.CENTER_VERTICAL*//*);
        layoutParams.rightMargin = 0;
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);*/
       // actionBar.setBackgroundDrawable(new ColorDrawable(0x7D000000));





        chatActivityPresenter = new  ChatActivityPresenter(ChatActivity.this);

        ProgressManager.hideProgress();
        ProgressManager.showProgress(ChatActivity.this,"Pulling Messages Please Wait...");

        chatActivityPresenter.getChatDataFromServer(ChatActivity.this);


        FirebaseMessaging.getInstance().unsubscribeFromTopic("chats");

        TrainerProfile = findViewById(R.id.profileImage1);
        TrainerName = findViewById(R.id.trainername);

        ProgressManager.showProgress(ChatActivity.this,"Dra ut meddelanden Vänligen vänta...");



        try {




        }catch (Exception e){

        }

        String FCMID = SaveSharedPreference.getUserFcmToken(ChatActivity.this);
        int badgeCount = SaveSharedPreference.getChatNotificationCounter(ChatActivity.this);
        SaveSharedPreference.setChatActivityState(ChatActivity.this,true);

        chatActivityPresenter.setChatBadgesData(ChatActivity.this,"chat","0",FCMID, String.valueOf(0));







        handler.postDelayed(runnable, 300);
        time = System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        ShortcutBadger.removeCount(ChatActivity.this); //for 1.1.4+
        setBadge(this,0);




    }




    Runnable runnable=new Runnable() {
        @Override
        public void run() {

            if (time != 0L && System.currentTimeMillis() - time > 400) {
                ProgressManager.hideProgress();
                isLoadingMsgFirstTime = false;

            }
            else
            handler.postDelayed(runnable, 500);

        }
    };


    String date,reciverID,text,senderId,senderName,userType,profileLink;
    private ArrayList<ChatModel> my_list = new ArrayList<ChatModel>();
    private ArrayList<ChatModel> my_list2 = new ArrayList<ChatModel>();


    int preCount=0;

    private void append_chat_conversation(DataSnapshot dataSnapshot) {


        Iterator i = dataSnapshot.getChildren().iterator();


        try {

            while (i.hasNext()) {

                date = (String) ((DataSnapshot) i.next()).getValue();
                profileLink = (String) ((DataSnapshot) i.next()).getValue();
                reciverID = (String) ((DataSnapshot) i.next()).getValue();
                senderId = (String) ((DataSnapshot) i.next()).getValue();
                senderName = (String) ((DataSnapshot) i.next()).getValue();
                userType = (String) ((DataSnapshot) i.next()).getValue();
                text = (String) ((DataSnapshot) i.next()).getValue();

                ChatModel chatModelSender = new ChatModel();
                ChatModel chatModelReciver = new ChatModel();


                if(senderId.equals(user.getId())){
                //if(reciverID.equals(user.getId())){
                //if(userType.equals("contestant")){
                    long startDate=0;
                    try {
                        //String dateString = "30/09/2014";
                        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
                        Date date1 = sdf.parse(date);

                        startDate = date1.getTime();
                       /* if(startDate){

                        }*/

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    chatModelSender.setDateLong(startDate);
                    chatModelSender.setDate(date);
                    chatModelSender.setText(text);
                    chatModelSender.setUserID(senderId);
                    my_list.add(chatModelSender);
                }
                else{

                    long startDate=0;
                    try {
                        //String dateString = "30/09/2014";
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
                        Date date1 = sdf.parse(date);

                        startDate = date1.getTime();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    chatModelReciver.setDateLong(startDate);
                    chatModelReciver.setDate(date);
                    chatModelReciver.setText(text);
                    chatModelReciver.setUserID(reciverID);

                    my_list2.add(chatModelReciver);
                }


                count+=1;


            }


        }catch (Exception e){

        }


        //"2019-05-21T16:30:48+04:00"
        try {

            if(!my_list.isEmpty()) {
            for (int j = 0; j < my_list.size(); j++) {
                //  long Date = Long.parseLong(my_list.get(j).getDate());
             
                mChatView.addMessage(new ChatMessage(my_list.get(j).getText(), getTimeLong(my_list.get(j).getDate()), ChatMessage.Type.SENT));
                my_list.clear();

            }

            mChatView.removeMessage(count);

        }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
        if(!my_list2.isEmpty()) {

            long startDate=0;
            for (int j = 0; j < my_list2.size(); j++) {


                mChatView.addMessage(new ChatMessage(my_list2.get(j).getText(), getTimeLong(my_list2.get(j).getDate()) , ChatMessage.Type.RECEIVED));
                my_list2.clear();

            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }





        if(preCount==count)
            count+=1;

        preCount=count;

         //ProgressManager.hideProgress();

    }

    public void FireBaseInit(){



        mFirebaseDatabase.addChildEventListener(new ChildEventListener() {

                                                    @Override
                                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {

                                                        if(isLoadingMsgFirstTime){


                                                            time = System.currentTimeMillis();
                                                        }

                                                        append_chat_conversation(dataSnapshot);



                                                    }

                                                    @Override
                                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {

                                                        append_chat_conversation(dataSnapshot);


                                                    }

                                                    @Override
                                                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                                                    }

                                                    @Override
                                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                }
        );



    }
    public Realm_User provideUserLocal(Context context)
    {

        String [] params= SaveSharedPreference.getLoggedParams(context);

        Realm_User user= Repository.provideUserLocal(params[0],params[1]);

        return  user;
    }

    private void loadImage(final ImageView imageView, final String imageUrl){
        Picasso.get()
                .load(imageUrl)
                .into(imageView , new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        String updatedImageUrl;
                        if (imageUrl.contains("https")){
                            updatedImageUrl = imageUrl.replace("https", "http");
                        }else{
                            updatedImageUrl = imageUrl.replace("http", "https");
                        }
                        loadImage(imageView, updatedImageUrl);
                    }
                });
    }


    public long getTimeLong(String time){
        long startDate = 0;
        try {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
            Date date = sdf.parse(time);

            startDate = date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public void onBackPressed() {

        String FCMID = SaveSharedPreference.getUserFcmToken(ChatActivity.this);
        int badgeCount = SaveSharedPreference.getChatNotificationCounter(ChatActivity.this);
        chatActivityPresenter.setChatBadgesData(ChatActivity.this,"chat","0",FCMID, String.valueOf(badgeCount));
        SaveSharedPreference.setChatNotificationCounter(ChatActivity.this,0);
        SaveSharedPreference.setChatActivityState(ChatActivity.this,false);
        finish();
    }

    public void onBackClick(View view) {


        String FCMID = SaveSharedPreference.getUserFcmToken(ChatActivity.this);
        int badgeCount = SaveSharedPreference.getChatNotificationCounter(ChatActivity.this);
        chatActivityPresenter.setChatBadgesData(ChatActivity.this,"chat","0",FCMID, String.valueOf(badgeCount));
        SaveSharedPreference.setChatNotificationCounter(ChatActivity.this,0);
        SaveSharedPreference.setChatActivityState(ChatActivity.this,false);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        String FCMID = SaveSharedPreference.getUserFcmToken(ChatActivity.this);
        int badgeCount = SaveSharedPreference.getChatNotificationCounter(ChatActivity.this);
        chatActivityPresenter.setChatBadgesData(ChatActivity.this,"chat","0",FCMID, String.valueOf(badgeCount));
        SaveSharedPreference.setChatNotificationCounter(ChatActivity.this,0);
        SaveSharedPreference.setChatActivityState(ChatActivity.this,false);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String FCMID = SaveSharedPreference.getUserFcmToken(ChatActivity.this);
        int badgeCount = SaveSharedPreference.getChatNotificationCounter(ChatActivity.this);
        chatActivityPresenter.setChatBadgesData(ChatActivity.this,"chat","0",FCMID, String.valueOf(badgeCount));
        SaveSharedPreference.setChatNotificationCounter(ChatActivity.this,0);
        SaveSharedPreference.setChatActivityState(ChatActivity.this,false);
        finish();
    }

    @Override
    public void setChatTrainerSucess(String message) {

        ProgressManager.hideProgress();

        user=provideUserLocal(getApplicationContext());

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("chats").child(user.getTrainerID()+"_"+user.getId());

        FireBaseInit();

        TrainerName.setText(user.getTrainerName());

        loadImage(TrainerProfile,user.getTrainerPhoto());

        mChatView.setOnSentMessageListener(new ChatView.OnSentMessageListener(){
                                               @Override
                                               public boolean sendMessage(ChatMessage chatMessage){
                                                   // perform actual message sending


                                                   Map<String,Object> map = new HashMap<String, Object>();
                                                   temp_key = mFirebaseDatabase.push().getKey();
                                                   mFirebaseDatabase.updateChildren(map);


                                                   /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");*/
                                                   //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
                                                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
                                                   String currentDateandTime = sdf.format(new Date());

                                                   String timeWithoutGMT = currentDateandTime.replace("GMT","");



                                                   DatabaseReference message_root = mFirebaseDatabase.child(temp_key);
                                                   Map<String,Object> map2 = new HashMap<String, Object>();
                                                   map2.put("date",timeWithoutGMT);
                                                   map2.put("text",chatMessage.getMessage());
                                                   map2.put("receiverId",user.getTrainerID());
                                                   map2.put("senderId",user.getId());
                                                   map2.put("senderName",user.getF_name()+" "+user.getL_name());
                                                   map2.put("senderType","contestant");
                                                   map2.put("icon",user.getProfile_image());
                                                   message_root.updateChildren(map2);

                                                   //Log.i("ChatTime",timeWithoutGMT);

                                                   isLoadingMsgFirstTime=false;
                                                   return true;
                                               }
                                           }
        );

    }



    @Override
    public void setChatTrainerFailed(String message) {
        if(message.equals("Unable to resolve host \"api.rikskampen.se\": No address associated with hostname")) {
            MyApplication.showSimpleSnackBar(ChatActivity.this, "No Internet Connection");
        }
        else if(message.equals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 118 path $.result.Home.Activities.weeklyActivities")){

        }
        else if(message.equals("Unauthorized")){

            SaveSharedPreference.setUsertodaySteps(ChatActivity.this,0);
            SaveSharedPreference.setSensorSteps(ChatActivity.this,0);
            //LogOutUnautorizedUser();
        }
        else{
            MyApplication.showSimpleSnackBar(ChatActivity.this, "Feed not refreshed...");

        }
    }

    @Override
    public void setChatBadgeSucess(String message) {
        SaveSharedPreference.setChatNotificationCounter(ChatActivity.this,0);
        //MyApplication.showSimpleSnackBarSucess(ChatActivity.this,"Badge Count 0 to server");



    }

    @Override
    public void setChatBadgeFailed(String message) {

        MyApplication.showSimpleSnackBar(ChatActivity.this,message);
    }

    @Override
    public void setPresenter(ChatActivityContract.Presenter mPresenter) {

    }

    private void LogOutUnautorizedUser(){


        LayoutInflater li = LayoutInflater.from(ChatActivity.this);

        View promptsView = li.inflate(R.layout.dialog_box_logout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(ChatActivity.this);

        builder.setView(promptsView);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Realm mLocalService = ((LocalApiService)  ServiceLocator.getService(LocalApiService.NAME)).getmRealm();
                mLocalService.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {


                        mLocalService.deleteAll();

                    }
                });

                MyApplication.showSimpleSnackBar(ChatActivity.this,  getResources().getString(R.string.LoginModule_Session_Timed_Out));
                Intent intent = new Intent(ChatActivity.this, LoginActivity.class);
                startActivity(intent);

                SaveSharedPreference.setLoggedIn(ChatActivity.this, false);

                finish();


            }
        });

        builder.show();

    }
}
