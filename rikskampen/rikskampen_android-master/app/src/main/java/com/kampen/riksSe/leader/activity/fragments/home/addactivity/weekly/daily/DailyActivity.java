package com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.daily;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kampen.riksSe.R;
import com.kampen.riksSe.data_manager.Repository;
import com.kampen.riksSe.leader.activity.fragments.home.addactivity.weekly.model.WeeklyActivityModel;
import com.kampen.riksSe.user.model.Realm_User;
import com.kampen.riksSe.utils.SaveSharedPreference;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DailyActivity extends AppCompatActivity implements DailyActivityContract.View {


    WeeklyActivityModel weeklyActivityModel;

    ImageView yourPick;

    TextView location,activityDateTime,stepView,starView,caloriesView,weightView,distanceview,waistview;

    DailyActivityPresenter dailyActivityPresenter;

    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_activity);


        yourPick = findViewById(R.id.yourPick);
        location =findViewById(R.id.Location);
        activityDateTime = findViewById(R.id.activityDateTime);
        stepView = findViewById(R.id.stepsCount);
        starView = findViewById(R.id.starNumber);
        caloriesView = findViewById(R.id.caloriesCountValue);
        weightView = findViewById(R.id.weightCountValue);
        distanceview = findViewById(R.id.distanceCountValue);
        waistview = findViewById(R.id.waistCountValue);
        weightView = findViewById(R.id.weightCountValue);

        dailyActivityPresenter = new DailyActivityPresenter(DailyActivity.this,getApplicationContext());

       // List<A_WeekDB> ActivityData = getDailyData(DailyActivity.this);



        String dayNo = getIntent().getStringExtra("SelectedDay");
        String weekNo = getIntent().getStringExtra("SelectedWeek");
        String Steps = getIntent().getStringExtra("Steps");
        String Location = getIntent().getStringExtra("Location");
        String Image = getIntent().getStringExtra("Image");
        String DateTime = getIntent().getStringExtra("DateTime");
        String Star = getIntent().getStringExtra("Star");
        String Calories = getIntent().getStringExtra("calories");
        String Weight = getIntent().getStringExtra("Weight");
        String distance = getIntent().getStringExtra("distance");
        String Waist = getIntent().getStringExtra("waist");






       // Toast.makeText(getApplicationContext(), "W_Day & Week Selected is ! " + dayNo + " " + weekNo, Toast.LENGTH_LONG).show();

        /*GlideApp
                .with(getApplicationContext())
                .load(Image)
                .into(yourPick);
        steps.setText(Steps);
        location.setText(Location);*/

        stepView.setText(Steps);
        starView.setText(Star);
        caloriesView.setText(Calories);
        weightView.setText(Weight);
        distanceview.setText(distance);
        waistview.setText(Waist);

        loadImage(yourPick,Image);
        location.setText(Location);
        activityDateTime.setText(DateTime);


        yourPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    yourPick.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    yourPick.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    yourPick.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    yourPick.setScaleType(ImageView.ScaleType.FIT_XY);
                }*/

                /*Intent intent = new Intent(DailyActivity.this, DailyImageActivity.class);
                intent.putExtra("Image",Image);
                startActivity(intent);*/
            }
        });

/*
        if(Image.equals("0")){

            GlideApp
                    .with(getApplicationContext())
                    .load(Image)
                    .into(yourPick);
            steps.setText(Steps);
            location.setText(Location);
        }else{
            GlideApp
                    .with(getApplicationContext())
                    .load(R.drawable.ic_profile_holder)
                    .into(yourPick);
            steps.setText(Steps);
            location.setText(Location);
        }
*/

    }

    public Realm_User provideUserLocal(Context context) {

        String[] params = SaveSharedPreference.getLoggedParams(context);

        Realm_User user = Repository.provideUserLocal(params[0], params[1]);

        return user;
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



    public void onBackClick(View view) {

        finish();
    }

    @Override
    public void setDailyActivity(String message) {

    }

    @Override
    public void setDailyActivityFailed(String message) {

    }

    @Override
    public void setPresenter(DailyActivityContract.Presenter mPresenter) {

    }
}
